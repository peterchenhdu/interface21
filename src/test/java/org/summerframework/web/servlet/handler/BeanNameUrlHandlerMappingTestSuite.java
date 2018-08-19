/*
 * Copyright (c) 2011-2025 PiChen
 */

package org.summerframework.web.servlet.handler;

import java.io.IOException;

import javax.servlet.ServletException;

import junit.framework.TestCase;

import org.summerframework.context.ApplicationContext;
import org.summerframework.context.support.ClassPathXmlApplicationContext;
import org.summerframework.web.mock.MockHttpServletRequest;
import org.summerframework.web.servlet.HandlerExecutionChain;
import org.summerframework.web.servlet.HandlerMapping;

/**
 * @author Rod Johnson
 */
public class BeanNameUrlHandlerMappingTestSuite extends TestCase {

    public static final String CONF = "/org/summerframework/web/servlet/handler/map1.xml";

    private HandlerMapping hm;

    private ApplicationContext ac;

    public BeanNameUrlHandlerMappingTestSuite() throws IOException {
        ac = new ClassPathXmlApplicationContext(CONF);
        hm = new BeanNameUrlHandlerMapping();
        hm.setApplicationContext(ac);
    }

    public void testRequestsWithHandlers() throws Exception {
        Object bean = ac.getBean("godCtrl");

        MockHttpServletRequest req = new MockHttpServletRequest(null, "GET", "/welcome.html");
        HandlerExecutionChain hec = hm.getHandler(req);
        assertTrue("Handler is correct bean", hec != null && hec.getHandler() == bean);

        req = new MockHttpServletRequest(null, "GET", "/show.html");
        hec = hm.getHandler(req);
        assertTrue("Handler is correct bean", hec != null && hec.getHandler() == bean);

        req = new MockHttpServletRequest(null, "GET", "/bookseats.html");
        hec = hm.getHandler(req);
        assertTrue("Handler is correct bean", hec != null && hec.getHandler() == bean);
    }

    public void testRequestsWithoutHandlers() throws Exception {
        MockHttpServletRequest req = new MockHttpServletRequest(null, "GET", "/nonsense.html");
        Object h = hm.getHandler(req);
        assertTrue("Handler is null", h == null);

        req = new MockHttpServletRequest(null, "GET", "/foo/bar/baz.html");
        h = hm.getHandler(req);
        assertTrue("Handler is null", h == null);
    }

    public void testAsteriskMatches() throws ServletException {
        Object bean = ac.getBean("godCtrl");

        MockHttpServletRequest req = new MockHttpServletRequest(null, "GET", "/test.html");
        HandlerExecutionChain hec = hm.getHandler(req);
        assertTrue("Handler is correct bean", hec != null && hec.getHandler() == bean);

        req = new MockHttpServletRequest(null, "GET", "/testarossa");
        hec = hm.getHandler(req);
        assertTrue("Handler is correct bean", hec != null && hec.getHandler() == bean);

        req = new MockHttpServletRequest(null, "GET", "/tes");
        hec = hm.getHandler(req);
        assertTrue("Handler is correct bean", hec == null);
    }

}
