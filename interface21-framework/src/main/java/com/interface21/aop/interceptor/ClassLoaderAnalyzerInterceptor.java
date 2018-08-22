/*
 * Copyright (c) 2011-2025 PiChen
 */

package com.interface21.aop.interceptor;

import com.interface21.util.ClassLoaderUtils;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Trivial classloader analyzer interceptor.
 *
 * @author Rod Johnson
 * @author Dmitriy Kopylenko
 * @version $Id$
 */
public class ClassLoaderAnalyzerInterceptor implements MethodInterceptor {

    protected final Log logger = LogFactory.getLog(getClass());

    public Object invoke(MethodInvocation pInvocation) throws Throwable {
        logger.debug("Begin class loader analysis");

        logger.info(ClassLoaderUtils.showClassLoaderHierarchy(
                pInvocation.getThis(),
                pInvocation.getThis().getClass().getName(),
                "\n",
                "-"));
        Object rval = pInvocation.proceed();

        logger.debug("End class loader analysis");
        return rval;
    }

}
