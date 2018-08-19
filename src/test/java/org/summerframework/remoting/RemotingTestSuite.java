/*
 * Copyright (c) 2011-2025 PiChen
 */

package org.summerframework.remoting;

import java.net.MalformedURLException;

import junit.framework.TestCase;

import org.summerframework.beans.ITestBean;
import org.summerframework.beans.TestBean;
import org.summerframework.remoting.caucho.BurlapProxyFactoryBean;
import org.summerframework.remoting.caucho.HessianProxyFactoryBean;
import org.summerframework.remoting.rmi.RmiProxyFactoryBean;
import org.summerframework.remoting.support.RemoteProxyFactoryBean;

/**
 * @author Juergen Hoeller
 * @since 16.05.2003
 */
public class RemotingTestSuite extends TestCase {

    public RemotingTestSuite(String msg) {
        super(msg);
    }

    public void testHessianProxyFactoryBean() throws Exception {
        HessianProxyFactoryBean factory = new HessianProxyFactoryBean();
        try {
            factory.setServiceInterface(TestBean.class);
            fail("Should have thrown IllegalArgumentException");
        } catch (IllegalArgumentException ex) {
            // expected
        }
        factory.setServiceInterface(ITestBean.class);
        factory.setServiceUrl("http://localhosta/testbean");
        factory.setUsername("test");
        factory.setPassword("bean");
        factory.afterPropertiesSet();
        assertTrue("Correct singleton value", factory.isSingleton());
        assertTrue("No property values", factory.getPropertyValues() == null);

        ITestBean bean = (ITestBean) factory.getObject();
        try {
            bean.setName("test");
            fail("Should have thrown RemoteAccessException");
        } catch (RemoteAccessException ex) {
            // expected
        }
    }

    public void testBurlapProxyFactoryBean() throws Exception {
        BurlapProxyFactoryBean factory = new BurlapProxyFactoryBean();
        factory.setServiceInterface(ITestBean.class);
        factory.setServiceUrl("http://localhosta/testbean");
        factory.afterPropertiesSet();
        ITestBean bean = (ITestBean) factory.getObject();
        try {
            bean.setName("test");
            fail("Should have thrown RemoteAccessException");
        } catch (RemoteAccessException ex) {
            // expected
        }
    }

    public void testRmiProxyFactoryBean() throws Exception {
        RmiProxyFactoryBean factory = new RmiProxyFactoryBean();
        factory.setServiceInterface(ITestBean.class);
        factory.setServiceUrl("rmi://localhosta/testbean");
        try {
            factory.afterPropertiesSet();
            fail("Should have thrown RemoteAccessException");
        } catch (RemoteAccessException ex) {
            // expected
            ex.printStackTrace();
        }
    }

    public void testInvalidProxyReturned() throws Exception {
        RemoteProxyFactoryBean factory = new RemoteProxyFactoryBean() {
            protected Object createProxy() throws MalformedURLException, RemoteAccessException {
                return "mock";
            }
        };
        factory.setServiceInterface(ITestBean.class);
        factory.setServiceUrl("rmi://localhost/testbean");
        try {
            factory.afterPropertiesSet();
            fail("Should have thrown IllegalArgumentException");
        } catch (IllegalArgumentException ex) {
            // expected
        }
    }

}
