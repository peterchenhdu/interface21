/*
 * Copyright (c) 2011-2025 PiChen
 */

/*
 * The Spring Framework is published under the terms
 * of the Apache Software License.
 */

package org.summerframework.aop.framework;

import java.lang.reflect.Method;

import org.aopalliance.intercept.AttributeRegistry;
import org.aopalliance.intercept.MethodInterceptor;

/**
 * MethodPointcut implementation wrapping an
 * Interceptor that should always run.
 *
 * @author Rod Johnson
 * @version $Id$
 * @since 04-Apr-2003
 */
public class AlwaysInvoked extends AbstractMethodPointcut implements StaticMethodPointcut {

    public AlwaysInvoked(MethodInterceptor interceptor) {
        super(interceptor);
    }

    /**
     * @see StaticMethodPointcut#applies(java.lang.reflect.Method, AttributeRegistry)
     */
    public boolean applies(Method m, AttributeRegistry ar) {
        return true;
    }

}
