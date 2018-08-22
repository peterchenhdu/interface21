/*
 * Copyright (c) 2011-2025 PiChen
 */

/*
 * The Spring Framework is published under the terms
 * of the Apache Software License.
 */

package com.interface21.beans.factory.support;

import com.interface21.beans.factory.BeanFactory;

/**
 * Interface to be implemented by objects that can load BeanFactories
 * (usually on behalf of application components such as EJBs).
 *
 * @author Rod Johnson
 * @version $Id$
 * @since 20-Jul-2003
 */
public interface BeanFactoryLoader {

    /**
     * Load the BeanFactory.
     *
     * @return BeanFactory loaded BeanFactory. Never returns null.
     * @throws BootstrapException if a BeanFactory cannot be loaded
     */
    BeanFactory loadBeanFactory() throws BootstrapException;

}
