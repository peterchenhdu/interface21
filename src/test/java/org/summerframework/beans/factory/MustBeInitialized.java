/*
 * Copyright (c) 2011-2025 PiChen
 */

/*
 * The Spring Framework is published under the terms
 * of the Apache Software License.
 */

package org.summerframework.beans.factory;


/**
 * Simple test of BeanFactory initialization
 *
 * @author Rod Johnson
 * @version $Revision$
 * @since 12-Mar-2003
 */
public class MustBeInitialized implements InitializingBean {

    private boolean inited;

    /**
     * @see InitializingBean#afterPropertiesSet()
     */
    public void afterPropertiesSet() throws Exception {
        this.inited = true;
    }

    /**
     * Dummy business method that will fail unless the factory
     * managed the bean's lifecycle correctly
     */
    public void businessMethod() {
        if (!this.inited)
            throw new RuntimeException("Factory didn't call afterPropertiesSet() on MustBeInitialized object");
    }

}
