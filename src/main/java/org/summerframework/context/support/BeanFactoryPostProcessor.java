/*
 * Copyright (c) 2011-2025 PiChen
 */

package org.summerframework.context.support;

import org.summerframework.beans.factory.support.ListableBeanFactoryImpl;
import org.summerframework.context.ApplicationContextAware;
import org.summerframework.context.ApplicationContextException;

/**
 * Allows for custom modification of an application context's beans.
 * Useful for custom config files targetted at system administrators that
 * override bean properties configured in the application context.
 * <p>
 * <p>For reading "beanName.property=value" configuration from a
 * properties file, consider using PropertyResourceConfigurer.
 *
 * @author Juergen Hoeller
 * @see PropertyResourceConfigurer
 * @since 06.07.2003
 */
public interface BeanFactoryPostProcessor extends ApplicationContextAware {

    /**
     * Modify the application context's internal bean factory after its standard
     * initialization. All bean definitions will have been loaded, but no beans
     * will have been instantiated yet. This allows for overriding or adding
     * properties even to eager-initializing beans.
     *
     * @param beanFactory the bean factory used by the application context
     * @throws ApplicationContextException in case of initialization errors
     */
    void postProcessBeanFactory(ListableBeanFactoryImpl beanFactory) throws ApplicationContextException;

}
