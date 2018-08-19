
/*
 * Copyright (c) 2011-2025 PiChen
 */

package org.summerframework.context.support;

import java.io.IOException;
import java.io.InputStream;

import org.summerframework.beans.BeansException;
import org.summerframework.beans.factory.NoSuchBeanDefinitionException;
import org.summerframework.beans.factory.support.ListableBeanFactoryImpl;
import org.summerframework.beans.factory.xml.XmlBeanFactory;
import org.summerframework.context.ApplicationContext;
import org.summerframework.context.ApplicationContextException;

/**
 * Convenient abstract superclass for ApplicationContext implementations
 * drawing their configuration from XML documents containing bean definitions
 * understood by an XMLBeanFactory.
 *
 * @author Rod Johnson
 * @version $Revision$
 * @see XmlBeanFactory
 */
public abstract class AbstractXmlApplicationContext extends AbstractApplicationContext {

    /**
     * Default BeanFactory for this context
     */
    private XmlBeanFactory xmlBeanFactory;

    /**
     * Create a new AbstractXmlApplicationContext with no parent.
     */
    public AbstractXmlApplicationContext() {
    }

    /**
     * Create a new AbstractXmlApplicationContext with the given parent context.
     *
     * @param parent parent context
     */
    public AbstractXmlApplicationContext(ApplicationContext parent) {
        super(parent);
    }

    protected void refreshBeanFactory() throws ApplicationContextException {
        String identifier = "application context with display name [" + getDisplayName() + "]";
        InputStream is = null;
        try {
            // Supports remote as well as local URLs
            is = getInputStreamForBeanFactory();
            this.xmlBeanFactory = new XmlBeanFactory(getParent());
            this.xmlBeanFactory.setEntityResolver(new ResourceBaseEntityResolver(this));
            this.xmlBeanFactory.loadBeanDefinitions(is);
            if (logger.isInfoEnabled()) {
                logger.info("BeanFactory for application context: " + this.xmlBeanFactory);
            }
        } catch (IOException ex) {
            throw new ApplicationContextException("IOException parsing XML document for " + identifier, ex);
        } catch (NoSuchBeanDefinitionException ex) {
            throw new ApplicationContextException("Cannot load configuration: missing bean definition [" + ex.getBeanName() + "]", ex);
        } catch (BeansException ex) {
            throw new ApplicationContextException("Cannot load configuration: problem instantiating or initializing beans", ex);
        } finally {
            try {
                if (is != null)
                    is.close();
            } catch (IOException ex) {
                throw new ApplicationContextException("IOException closing stream for XML document for " + identifier, ex);
            }
        }
    }

    /**
     * Return the default BeanFactory for this context
     */
    public final ListableBeanFactoryImpl getBeanFactory() {
        return xmlBeanFactory;
    }

    /**
     * Open and return the input stream for the bean factory for this namespace.
     * If namespace is null, return the input stream for the default bean factory.
     *
     * @throws IOException if the required XML document isn't found
     */
    protected abstract InputStream getInputStreamForBeanFactory() throws IOException;

}
 