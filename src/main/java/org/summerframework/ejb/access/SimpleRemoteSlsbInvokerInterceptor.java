/*
 * Copyright (c) 2011-2025 PiChen
 */

package org.summerframework.ejb.access;

import java.lang.reflect.InvocationTargetException;

import javax.ejb.EJBObject;

import org.aopalliance.intercept.AspectException;
import org.aopalliance.intercept.MethodInvocation;

/**
 * Basic remote invoker for EJBs.
 * "Creates" a new EJB instance for each invocation.
 *
 * @version $Revision$
 */
public class SimpleRemoteSlsbInvokerInterceptor extends AbstractRemoteSlsbInvokerInterceptor {

    /**
     * JavaBean constructor
     */
    public SimpleRemoteSlsbInvokerInterceptor() {
    }

    /**
     * Convenient constructor for programmatic use.
     *
     * @param jndiName
     * @param inContainer
     * @throws org.aopalliance.intercept.AspectException
     */
    public SimpleRemoteSlsbInvokerInterceptor(String jndiName, boolean inContainer) throws AspectException {
        setJndiName(jndiName);
        setInContainer(inContainer);
        try {
            afterPropertiesSet();
        } catch (Exception ex) {
            throw new AspectException("Failed to create EJB invoker interceptor", ex);
        }
    }

    /**
     * This is the last invoker in the chain
     *
     * @see org.aopalliance.intercept.MethodInterceptor#invoke(org.aopalliance.intercept.MethodInvocation)
     */
    public Object invoke(MethodInvocation invocation) throws Throwable {
        EJBObject ejb = newSessionBeanInstance();
        try {
            return invocation.getMethod().invoke(ejb, invocation.getArguments());
        } catch (InvocationTargetException ex) {
            logger.warn(ex + " thrown invoking remote EJB method " + invocation.getMethod());
            throw ex.getTargetException();
        } catch (Throwable t) {
            throw new AspectException("Failed to invoke remote EJB", t);
        }
    }

}
