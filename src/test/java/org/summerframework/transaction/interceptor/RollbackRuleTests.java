/*
 * Copyright (c) 2011-2025 PiChen
 */

/*
 * The Spring Framework is published under the terms
 * of the Apache Software License.
 */

package org.summerframework.transaction.interceptor;

import javax.ejb.EJBException;
import javax.servlet.ServletException;

import org.summerframework.beans.FatalBeanException;

import junit.framework.TestCase;

/**
 * @author Rod Johnson
 * @version $Revision$
 * @since 09-Apr-2003
 */
public class RollbackRuleTests extends TestCase {

    /**
     * Constructor for RollbackRuleTests.
     *
     * @param arg0
     */
    public RollbackRuleTests(String arg0) {
        super(arg0);
    }

    public void testFoundImmediately() {
        RollbackRuleAttribute rr = new RollbackRuleAttribute("java.lang.Exception");
        assertTrue(rr.getDepth(new Exception()) == 0);
    }

    public void testNotFound() {
        RollbackRuleAttribute rr = new RollbackRuleAttribute("javax.servlet.ServletException");
        assertTrue(rr.getDepth(new EJBException()) == -1);
    }

    public void testAncestry() {
        RollbackRuleAttribute rr = new RollbackRuleAttribute("java.lang.Exception");
        // Exception -> Runtime -> EJBException
        assertTrue(rr.getDepth(new EJBException()) == 2);
    }


    public void testAlwaysTrue() {
        RollbackRuleAttribute rr = new RollbackRuleAttribute("java.lang.Throwable");
        // Exception -> Runtime -> EJBException
        assertTrue(rr.getDepth(new EJBException()) > 0);
        assertTrue(rr.getDepth(new ServletException()) > 0);
        assertTrue(rr.getDepth(new FatalBeanException(null, null)) > 0);
        assertTrue(rr.getDepth(new RuntimeException()) > 0);
    }

}
