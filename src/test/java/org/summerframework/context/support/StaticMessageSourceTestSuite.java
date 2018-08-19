/*
 * Copyright (c) 2011-2025 PiChen
 */

package org.summerframework.context.support;

import org.summerframework.beans.MutablePropertyValues;
import org.summerframework.beans.factory.LBIInit;

import org.summerframework.context.ACATest;
import org.summerframework.context.AbstractApplicationContextTests;
import org.summerframework.context.ApplicationContext;
import org.summerframework.context.BeanThatListens;
import org.summerframework.context.NoSuchMessageException;
import org.summerframework.context.MessageSourceResolvable;

import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import junit.framework.Assert;
import junit.framework.Test;
import junit.framework.TestSuite;
import org.summerframework.beans.TestBean;

/**
 * @author Rod Johnson/Tony Falabella
 * @version $RevisionId$
 */
public class StaticMessageSourceTestSuite
        extends AbstractApplicationContextTests {

    //~ Static variables/initializers ------------------------------------------

    protected static final String MSG_TXT1_US = "At '{1,time}' on \"{1,date}\", there was \"{2}\" on planet {0,number,integer}.";
    protected static final String MSG_TXT1_UK = "At '{1,time}' on \"{1,date}\", there was \"{2}\" on station number {0,number,integer}.";
    protected static final String MSG_TXT2_US = "This is a test message in the message catalog with no args.";
    protected static final String MSG_TXT3_US = "This is another test message in the message catalog with no args.";

    //~ Instance variables -----------------------------------------------------

    protected StaticApplicationContext sac;

    //~ Constructors -----------------------------------------------------------

    public StaticMessageSourceTestSuite() throws Exception {
    }

    //~ Methods ----------------------------------------------------------------

    public static void main(String[] args) {
        junit.textui.TestRunner.run(suite());

        //	junit.swingui.TestRunner.main(new String[] {PrototypeFactoryTests.class.getName() } );
    }

    public static Test suite() {
        return new TestSuite(StaticMessageSourceTestSuite.class);
    }

    /**
     * Overridden
     */
    public void testCount() throws Exception {
        // These are only checked for current Ctx (not parent ctx)
        assertCount(16);
    }

    public void testMessageSource() throws NoSuchMessageException {
        // Do nothing here since super is looking for errorCodes we
        // do NOT have in the Context
    }

    public void testGetMessageWithDefaultPassedInAndFoundInMsgCatalog() {
        // Try with Locale.US
        Assert.assertTrue("valid msg from staticMsgSource with default msg passed in returned msg from msg catalog for Locale.US",
                sac.getMessage("message.format.example2", null, "This is a default msg if not found in msg.cat.", Locale.US
                )
                        .equals("This is a test message in the message catalog with no args."));
    }

    public void testGetMessageWithDefaultPassedInAndNotFoundInMsgCatalog() {
        // Try with Locale.US
        Assert.assertTrue("bogus msg from staticMsgSource with default msg passed in returned default msg for Locale.US",
                sac.getMessage("bogus.message", null, "This is a default msg if not found in msg.cat.", Locale.US
                )
                        .equals("This is a default msg if not found in msg.cat."));
    }

    /**
     * We really are testing the AbstractNestingMessageSource class here.
     * The underlying implementation uses a hashMap to cache messageFormats
     * once a message has been asked for.  This test is an attempt to
     * make sure the cache is being used properly.
     *
     * @see AbstractNestingMessageSource for more details.
     */
    public void testGetMessageWithMessageAlreadyLookedFor()
            throws Exception {
        Object[] arguments = {
                new Integer(7), new Date(System.currentTimeMillis()),
                "a disturbance in the Force"
        };


        // The first time searching, we don't care about for this test
        // Try with Locale.US
        sac.getMessage("message.format.example1", arguments, Locale.US);


        // Now msg better be as expected
        Assert.assertTrue("2nd search within MsgFormat cache returned expected message for Locale.US",
                sac.getMessage("message.format.example1", arguments, Locale.US
                )
                        .indexOf("there was \"a disturbance in the Force\" on planet 7.") != -1);

        Object[] newArguments = {
                new Integer(8), new Date(System.currentTimeMillis()),
                "a disturbance in the Force"
        };


        // Now msg better be as expected even with different args
        Assert.assertTrue("2nd search within MsgFormat cache with different args returned expected message for Locale.US",
                sac.getMessage("message.format.example1", newArguments, Locale.US
                )
                        .indexOf("there was \"a disturbance in the Force\" on planet 8.") != -1);
    }

    /**
     * Example taken from the javadocs for the java.text.MessageFormat class
     */
    public void testGetMessageWithNoDefaultPassedInAndFoundInMsgCatalog()
            throws Exception {
        Object[] arguments = {
                new Integer(7), new Date(System.currentTimeMillis()),
                "a disturbance in the Force"
        };


		/*
         Try with Locale.US
		 Since the msg has a time value in it, we will use String.indexOf(...)
		 to just look for a substring without the time.  This is because it is
		 possible that by the time we store a time variable in this method
		 and the time the ResourceBundleMessageSource resolves the msg the
		 minutes of the time might not be the same.
		 */
        Assert.assertTrue("msg from staticMsgSource for Locale.US substituting args for placeholders is as expected",
                sac.getMessage("message.format.example1", arguments, Locale.US
                )
                        .indexOf("there was \"a disturbance in the Force\" on planet 7.") != -1);


        // Try with Locale.UK
        Assert.assertTrue("msg from staticMsgSource for Locale.UK substituting args for placeholders is as expected",
                sac.getMessage("message.format.example1", arguments, Locale.UK
                )
                        .indexOf("there was \"a disturbance in the Force\" on station number 7.") != -1);


        // Try with Locale.US - Use a different test msg that requires no args
        Assert.assertTrue("msg from staticMsgSource for Locale.US that requires no args is as expected",
                sac.getMessage("message.format.example2", null, Locale.US)
                        .equals("This is a test message in the message catalog with no args."));
    }

    public void testGetMessageWithNoDefaultPassedInAndNotFoundInMsgCatalog() {
        // Expecting an exception
        try {
            // Try with Locale.US
            sac.getMessage("bogus.message", null, Locale.US);

            Assert.fail("bogus msg from staticMsgSource for Locale.US without default msg should have thrown exception");
        } catch (NoSuchMessageException tExcept) {
            Assert.assertTrue("bogus msg from staticMsgSource for Locale.US without default msg threw expected exception",
                    true);
        }
    }

    public void testMessageSourceResolvable() {
        // first code valid
        String[] codes1 = new String[]{"message.format.example3", "message.format.example2"};
        MessageSourceResolvable resolvable1 = new MessageSourceResolvableImpl(codes1, null, "default");
        try {
            System.out.println(sac.getMessage(resolvable1, Locale.US));
            Assert.assertTrue("correct message retrieved", MSG_TXT3_US.equals(sac.getMessage(resolvable1, Locale.US)));
        } catch (NoSuchMessageException ex) {
            Assert.fail("Should not throw NoSuchMessageException");
        }

        // only second code valid
        String[] codes2 = new String[]{"message.format.example99", "message.format.example2"};
        MessageSourceResolvable resolvable2 = new MessageSourceResolvableImpl(codes2, null, "default");
        try {
            Assert.assertTrue("correct message retrieved", MSG_TXT2_US.equals(sac.getMessage(resolvable2, Locale.US)));
        } catch (NoSuchMessageException ex) {
            Assert.fail("Should not throw NoSuchMessageException");
        }

        // no code valid, but default given
        String[] codes3 = new String[]{"message.format.example99", "message.format.example98"};
        MessageSourceResolvable resolvable3 = new MessageSourceResolvableImpl(codes3, null, "default");
        try {
            Assert.assertTrue("correct message retrieved", "default".equals(sac.getMessage(resolvable3, Locale.US)));
        } catch (NoSuchMessageException ex) {
            Assert.fail("Should not throw NoSuchMessageException");
        }

        // no code valid, no default
        String[] codes4 = new String[]{"message.format.example99", "message.format.example98"};
        MessageSourceResolvable resolvable4 = new MessageSourceResolvableImpl(codes4, null);
        try {
            sac.getMessage(resolvable4, Locale.US);
            Assert.fail("Should have thrown NoSuchMessagException");
        } catch (NoSuchMessageException ex) {
            // expected
        }
    }

    /**
     * Run for each test
     */
    protected ApplicationContext createContext() throws Exception {
        StaticApplicationContext parent = new StaticApplicationContext();
        parent.addListener(parentListener);

        Map m = new HashMap();
        m.put("name", "Roderick");
        parent.registerPrototype("rod", TestBean.class,
                new MutablePropertyValues(m));
        m.put("name", "Albert");
        parent.registerPrototype("father", TestBean.class,
                new MutablePropertyValues(m));

        parent.rebuild();

        this.sac = new StaticApplicationContext(parent);
        sac.addListener(listener);

        sac.registerSingleton("beanThatListens", BeanThatListens.class,
                new MutablePropertyValues());

        sac.registerSingleton("aca", ACATest.class, new MutablePropertyValues());

        sac.registerPrototype("aca-prototype", ACATest.class,
                new MutablePropertyValues());


        // There are no properties to be set on the StaticApplicationContext
        // (unlike on the ResourceBundleMessageSource which at least has the "basename"
        // attribute to be set)
        sac.registerSingleton(
                AbstractApplicationContext.MESSAGE_SOURCE_BEAN_NAME,
                StaticMessageSource.class, null);

        LBIInit.createTestBeans(sac.defaultBeanFactory);
        sac.rebuild();

        StaticMessageSource staticMsgSrc = (StaticMessageSource) sac.getBean(
                AbstractApplicationContext.MESSAGE_SOURCE_BEAN_NAME);
        staticMsgSrc.addMessage("message.format.example1", Locale.US,
                MSG_TXT1_US);
        staticMsgSrc.addMessage("message.format.example2", Locale.US,
                MSG_TXT2_US);
        staticMsgSrc.addMessage("message.format.example3", Locale.US,
                MSG_TXT3_US);
        staticMsgSrc.addMessage("message.format.example1", Locale.UK,
                MSG_TXT1_UK);

        return sac;
    }

    protected void tearDown() {
    }
}