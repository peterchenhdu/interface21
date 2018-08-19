/*
 * Copyright (c) 2011-2025 PiChen
 */

/*
 * The Spring Framework is published under the terms
 * of the Apache Software License.
 */

package org.summerframework.web.servlet.view.xslt;

import java.util.Locale;

import org.w3c.dom.Element;

import junit.framework.TestCase;

/**
 * @author Rod Johnson
 * @version $Id$
 * @since 26-Jul-2003
 */
public class FormatHelperTests extends TestCase {

    /**
     * Constructor for FormatHelperTests.
     *
     * @param arg0
     */
    public FormatHelperTests(String arg0) {
        super(arg0);
    }

    /*
     * Test for Node dateTimeElement(long, String, String)
     */
    public void testUkDateTimeElement() {
        long t = System.currentTimeMillis();
        Element e = (Element) FormatHelper.dateTimeElement(t, Locale.UK);
        assertTrue(e.getTagName().equals("formatted-date"));
        Element monthEle = (Element) e.getElementsByTagName("month").item(0);
        // TODO finish this test case
    }


}
