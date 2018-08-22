/*
 * Copyright (c) 2011-2025 PiChen
 */

/*
 * The Spring Framework is published under the terms
 * of the Apache Software License.
 */

package org.interface21.ejb.support;

import java.io.InputStream;

import javax.naming.NamingException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.interface21.beans.factory.BeanDefinitionStoreException;
import org.interface21.beans.factory.BeanFactory;
import org.interface21.beans.factory.ListableBeanFactory;
import org.interface21.beans.factory.support.BeanFactoryLoader;
import org.interface21.beans.factory.support.BootstrapException;
import org.interface21.beans.factory.xml.XmlBeanFactory;
import org.interface21.jndi.JndiTemplate;

/**
 * Implementation of the BeanFactoryLoader interface useful in EJBs
 * (although not tied to the EJB API).
 * <p>
 * <p>This class will look for the JNDI environment key
 * "java:comp/env/ejb/BeanFactoryPath" for the classpath location
 * of an XML bean factory definition.
 *
 * @author Rod Johnson
 * @version $Id$
 * @since 20-Jul-2003
 */
public class XmlBeanFactoryLoader implements BeanFactoryLoader {

    public static final String BEAN_FACTORY_PATH_ENVIRONMENT_KEY = "java:comp/env/ejb/BeanFactoryPath";

    private final Log logger = LogFactory.getLog(getClass());

    /**
     * Load the bean factory.
     *
     * @throws BootstrapException if the JNDI key is missing or if
     *                            the factory cannot be loaded from this location
     */
    public BeanFactory loadBeanFactory() throws BootstrapException {
        JndiTemplate jt = new JndiTemplate();
        String beanFactoryPath = null;
        try {
            beanFactoryPath = (String) jt.lookup(BEAN_FACTORY_PATH_ENVIRONMENT_KEY);
            logger.info("BeanFactoryPath from JNDI is '" + beanFactoryPath + "'");
            InputStream is = getClass().getResourceAsStream(beanFactoryPath);
            if (is == null)
                throw new BootstrapException("Cannot load bean factory path '" + beanFactoryPath + "'", null);
            ListableBeanFactory beanFactory = new XmlBeanFactory(is);
            logger.info("Loaded BeanFactory [" + beanFactory + "]");
            return beanFactory;
        } catch (NamingException ex) {
            throw new BootstrapException("Define an environment variable 'ejb/BeanFactoryPath' containing the location on the classpath of an XmlBeanFactory"
                    + ex.getMessage(), ex);
        } catch (BeanDefinitionStoreException ex) {
            throw new BootstrapException("Found resource at '" + beanFactoryPath + "' but it's not a valid Spring bean definition XML file: " + ex.getMessage(), null);
        }
    }

}
