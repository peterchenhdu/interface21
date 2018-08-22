/*
 * Copyright (c) 2011-2025 PiChen
 */

/*
 * The Spring Framework is published under the terms
 * of the Apache Software License.
 */

package com.interface21.beans.factory;

import com.interface21.beans.factory.BeanFactory;
import com.interface21.beans.factory.BeanFactoryAware;
import com.interface21.beans.factory.DisposableBean;
import com.interface21.beans.factory.InitializingBean;

/**
 * Simple test of BeanFactory initialization
 * and lifecycle callbacks.
 *
 * @author Rod Johnson
 * @version $Revision$
 * @since 12-Mar-2003
 */
public class LifecycleBean implements InitializingBean, BeanFactoryAware, DisposableBean {

    private boolean inited;

    private BeanFactory owningFactory;

    private boolean destroyed;

    public void afterPropertiesSet() {
        this.inited = true;
    }

    public void setBeanFactory(BeanFactory beanFactory) {
        if (!inited)
            throw new RuntimeException("Factory didn't call afterPropertiesSet() before invoking setBeanFactory on lifecycle bean");
        this.owningFactory = beanFactory;
    }

    /**
     * Dummy business method that will fail unless the factory
     * managed the bean's lifecycle correctly
     */
    public void businessMethod() {
        if (!this.inited || this.owningFactory == null)
            throw new RuntimeException("Factory didn't initialize lifecycle object correctly");
    }

    public void destroy() {
        if (this.destroyed) {
            throw new IllegalStateException("Already destroyed");
        }
        this.destroyed = true;
    }

    public boolean isDestroyed() {
        return destroyed;
    }

}
