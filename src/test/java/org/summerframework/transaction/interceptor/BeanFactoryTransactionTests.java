/*
 * Copyright (c) 2011-2025 PiChen
 */

/*
 * The Spring Framework is published under the terms
 * of the Apache Software License.
 */

package org.summerframework.transaction.interceptor;

import java.io.InputStream;
import java.lang.reflect.Proxy;

import junit.framework.TestCase;
import junit.framework.TestSuite;
import junit.textui.TestRunner;
import org.easymock.EasyMock;
import org.easymock.MockControl;

import org.summerframework.beans.ITestBean;
import org.summerframework.beans.factory.BeanFactory;
import org.summerframework.beans.factory.xml.XmlBeanFactory;
import org.summerframework.transaction.PlatformTransactionManager;
import org.summerframework.transaction.TransactionDefinition;
import org.summerframework.transaction.TransactionStatus;

/**
 * Test cases for AOP transaction management
 *
 * @author Rod Johnson
 * @version $Id$
 * @since 23-Apr-2003
 */
public class BeanFactoryTransactionTests extends TestCase {


    private BeanFactory factory;


    /**
     * @see junit.framework.TestCase#setUp()
     */
    protected void setUp() throws Exception {

        // Reset properties on raw target
        ITestBean test1 = (ITestBean) factory.getBean("target");
        test1.setAge(666);
    }

    public BeanFactoryTransactionTests(String s) {
        super(s);

        // Load from classpath, NOT a file path

        // TODO have a way of registering this at startup
        //PropertyEditorManager.registerEditor(TransactionAttributeSource.class, TransactionAttributeSourcePropertyEditor.class);

        InputStream is = getClass().getResourceAsStream("transactionalBeanFactory.xml");
        this.factory = new XmlBeanFactory(is, null);

    }

    // For profiling
//	public void testGetsAreNotTransactionalX() {
//		for (int i = 0; i < 10000; i++)
//			testGetsAreNotTransactional();
//	}

    public void testGetsAreNotTransactional() throws NoSuchMethodException {
        ITestBean test1 = (ITestBean) factory.getBean("txtest");
        assertTrue("test1 is a dynamic proxy", Proxy.isProxyClass(test1.getClass()));

        // Install facade
        MockControl ptmControl = EasyMock.controlFor(PlatformTransactionManager.class);
        PlatformTransactionManager ptm = (PlatformTransactionManager) ptmControl.getMock();
        // Expect no methods
        ptmControl.activate();
        PlatformTransactionManagerFacade.delegate = ptm;

        assertTrue("Age should not be " + test1.getAge(), test1.getAge() == 666);

        // Check no calls
        ptmControl.verify();

        // Install facade expecting a call
        ptmControl = EasyMock.controlFor(PlatformTransactionManager.class);
        ptm = (PlatformTransactionManager) ptmControl.getMock();
        TransactionStatus txStatus = new TransactionStatus(null, true);
        TransactionInterceptor txInterceptor = (TransactionInterceptor) factory.getBean("txInterceptor");
        MapTransactionAttributeSource txAttSrc = (MapTransactionAttributeSource) txInterceptor.getTransactionAttributeSource();
        ptm.getTransaction((TransactionDefinition) txAttSrc.methodMap.values().iterator().next());
        //ptm.getTransaction(null);
        ptmControl.setReturnValue(txStatus);
        ptm.commit(txStatus);
        ptmControl.setVoidCallable();
        ptmControl.activate();
        PlatformTransactionManagerFacade.delegate = ptm;

        // TODO same as old age to avoid ordering effect for now
        int age = 666;
        test1.setAge(age);
        ptmControl.verify();

        assertTrue(test1.getAge() == age);
    }

    public static void main(String[] args) {
        TestRunner.run(new TestSuite(BeanFactoryTransactionTests.class));
    }

    //public void test

}
