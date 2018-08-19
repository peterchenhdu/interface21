/*
 * Copyright (c) 2011-2025 PiChen
 */

/*
 * The Spring Framework is published under the terms
 * of the Apache Software License.
 */

package org.summerframework.aop.framework;

import org.aopalliance.intercept.MethodInterceptor;

/**
 * Abstract convenience superclass for implementations of
 * DynamicMethodPointcut or StaticMethodPointcut. Handles
 * interceptor.
 *
 * @author Rod Johnson
 * @version $Id$
 * @since July 22, 2003
 */
public abstract class AbstractMethodPointcut implements MethodPointcut {

    private MethodInterceptor interceptor;

    protected AbstractMethodPointcut() {
    }

    protected AbstractMethodPointcut(MethodInterceptor interceptor) {
        this.interceptor = interceptor;
    }

    /**
     * @see MethodPointcut#getInterceptor()
     */
    public MethodInterceptor getInterceptor() {
        return this.interceptor;
    }

    public void setInterceptor(MethodInterceptor interceptor) {
        this.interceptor = interceptor;
    }

}
