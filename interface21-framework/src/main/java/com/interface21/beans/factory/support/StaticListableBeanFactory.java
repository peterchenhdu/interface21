/*
 * Copyright (c) 2011-2025 PiChen
 */

package com.interface21.beans.factory.support;

import com.interface21.beans.BeansException;
import com.interface21.beans.factory.BeanFactory;
import com.interface21.beans.factory.BeanNotOfRequiredTypeException;
import com.interface21.beans.factory.ListableBeanFactory;
import com.interface21.beans.factory.NoSuchBeanDefinitionException;

import java.util.*;

/**
 * Singleton only.
 * Allows beans to be registered by name programmatically.
 * Mainly useful for testing.
 *
 * @author Rod Johnson
 * @version $Id$
 * @since 06-Jan-03
 */
public class StaticListableBeanFactory implements ListableBeanFactory {

    /**
     * Map from bean name to bean instance
     */
    private Map beans = new HashMap();


    /**
     * @see ListableBeanFactory#getBeanDefinitionCount()
     */
    public int getBeanDefinitionCount() {
        return beans.size();
    }

    /**
     * @see ListableBeanFactory#getBeanDefinitionNames()
     */
    public String[] getBeanDefinitionNames() {
        return (String[]) beans.keySet().toArray(new String[beans.keySet().size()]);
    }

    /**
     * @see ListableBeanFactory#getBeanDefinitionNames(Class)
     */
    public String[] getBeanDefinitionNames(Class type) {

        Set keys = beans.keySet();
        List matches = new LinkedList();
        for (Object key : keys) {
            String name = (String) key;
            Class clazz = beans.get(name).getClass();
            if (type.isAssignableFrom(clazz)) {
                //log4jCategory.debug("Added " + name + " of type " + type);
                matches.add(name);
            }
        }
        return (String[]) matches.toArray(new String[matches.size()]);
    }

    /**
     * @see BeanFactory#getBean(String, Class)
     */
    public Object getBean(String name, Class requiredType) throws BeansException {
        Object bean = getBean(name);

        if (!requiredType.isAssignableFrom(bean.getClass()))
            throw new BeanNotOfRequiredTypeException(name, requiredType, bean);

        return bean;
    }

    /**
     * @see BeanFactory#getBean(String)
     */
    public Object getBean(String name) throws BeansException {
        Object bean = this.beans.get(name);
        if (bean == null)
            throw new NoSuchBeanDefinitionException(name, "No such bean");
        return bean;
    }

    /**
     * Add a new singleton bean
     */
    public void addBean(String name, Object bean) {
        this.beans.put(name, bean);
    }

    /**
     * @see BeanFactory#isSingleton(java.lang.String)
     */
    public boolean isSingleton(String name) {
        return true;
    }

    public String[] getAliases(String name) {
        return null;
    }

}
