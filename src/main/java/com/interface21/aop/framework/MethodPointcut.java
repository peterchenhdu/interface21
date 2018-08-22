/*
 * Copyright (c) 2011-2025 PiChen
 */

/*
 * The Spring Framework is published under the terms
 * of the Apache Software License.
 */

package org.interface21.aop.framework;

import org.aopalliance.intercept.MethodInterceptor;

/**
 * Interface to be implemented by objects that can cause
 * conditional invocation of an Interceptor depending on
 * the method, arguments and attributes passed.
 *
 * @author Rod Johnson
 * @version $Id$
 * @since 03-Apr-2003
 */
public interface MethodPointcut {

    /**
     * Return the interceptor to run conditionally
     *
     * @return MethodInterceptor
     */
    MethodInterceptor getInterceptor();

    /**
     * Arbitrary int value. Depends on other values.
     * Higher values cause interceptors to be invoked earlier
     * in the invocation chain.
     * @return int
     */
    //int getPrecedence();
}
