/*
 * Copyright (c) 2011-2025 PiChen
 */

/*
 * The Spring Framework is published under the terms
 * of the Apache Software License.
 */

package org.summerframework.aop.framework;

import org.summerframework.aop.interceptor.DebugInterceptor;

import junit.framework.TestCase;

/**
 * @author Rod Johnson
 * @version $Id$
 * @since 23-Jul-2003
 */
public class RegexpMethodPointcutTests extends TestCase {

    /**
     * Constructor for RegexpMethodPointcutTests.
     *
     * @param arg0
     */
    public RegexpMethodPointcutTests(String arg0) {
        super(arg0);
    }

    public void testExactMatch() throws Exception {
        RegexpMethodPointcut rpc = new RegexpMethodPointcut();
        DebugInterceptor di = new DebugInterceptor();
        rpc.setInterceptor(di);
        assertEquals(rpc.getInterceptor(), di);
        rpc.setPattern("java.lang.Object.hashCode");
        assertTrue(rpc.applies(Object.class.getMethod("hashCode", null), null));
    }

    public void testWildcard() throws Exception {
        RegexpMethodPointcut rpc = new RegexpMethodPointcut();
        DebugInterceptor di = new DebugInterceptor();
        rpc.setInterceptor(di);
        assertEquals(rpc.getInterceptor(), di);
        rpc.setPattern(".*Object.hashCode");
        assertTrue(rpc.applies(Object.class.getMethod("hashCode", null), null));
        assertFalse(rpc.applies(Object.class.getMethod("wait", null), null));
    }

    public void testWildcardForOneClass() throws Exception {
        RegexpMethodPointcut rpc = new RegexpMethodPointcut();
        DebugInterceptor di = new DebugInterceptor();
        rpc.setInterceptor(di);
        assertEquals(rpc.getInterceptor(), di);
        rpc.setPattern("java.lang.Object.*");
        assertTrue(rpc.applies(Object.class.getMethod("hashCode", null), null));
        assertTrue(rpc.applies(Object.class.getMethod("wait", null), null));
    }

    public void testMatchesObjectClass() throws Exception {
        RegexpMethodPointcut rpc = new RegexpMethodPointcut();
        DebugInterceptor di = new DebugInterceptor();
        rpc.setInterceptor(di);
        assertEquals(rpc.getInterceptor(), di);
        rpc.setPattern("java.lang.Object.*");
        assertTrue(rpc.applies(Exception.class.getMethod("hashCode", null), null));
        // Doesn't match a method from Throwable
        assertFalse(rpc.applies(Exception.class.getMethod("getMessage", null), null));
    }

}
