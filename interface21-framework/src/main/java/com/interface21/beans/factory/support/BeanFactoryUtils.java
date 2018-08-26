/*
 * Copyright (c) 2011-2025 PiChen
 */

/*
 * The Spring Framework is published under the terms
 * of the Apache Software License.
 */

package com.interface21.beans.factory.support;

import com.interface21.beans.factory.HierarchicalBeanFactory;
import com.interface21.beans.factory.ListableBeanFactory;

import java.util.*;

/**
 * Convenience methods operating on bean factories.
 * Use to get a collection of beans of a given type
 * (rather than bean names), and for methods that return
 * bean instances, names or counts taking into account the
 * hierarchy a bean factory may participate in.
 *
 * @author Rod Johnson
 * @version $Id$
 * @since 04-Jul-2003
 */
public abstract class BeanFactoryUtils {

    /**
     * Return all beans of the given type or subtypes.
     * Useful convenience method when we don't care about
     * bean names.
     *
     * @param type type of bean to match. The return list
     *             will contain only this type
     * @param lbf
     * @return List return the empty list if there are no beans
     * of this type
     */
    public static List beansOfType(Class type, ListableBeanFactory lbf) {
        String[] beanNames = lbf.getBeanDefinitionNames(type);
        List<Object> l = new ArrayList<>(beanNames.length);
        for (String beanName : beanNames) {
            l.add(lbf.getBean(beanName));
        }
        return l;
    }

    /**
     * Like beansOfType() but also picks up beans defined in
     * ancestor bean factories if the current bean factory is a
     * HierarchicalBeanFactory
     *
     * @param type type of bean to match
     * @param lbf
     * @return List
     */
    public static List beansOfTypeIncludingAncestors(Class type, ListableBeanFactory lbf) {
        Collection beanNames = beanNamesIncludingAncestors(type, lbf);
        List<Object> l = new ArrayList<>(beanNames.size());
        Iterator itr = beanNames.iterator();
        for (int i = 0; itr.hasNext(); i++) {
            l.add(lbf.getBean((String) itr.next()));
        }
        return l;
    }

    /**
     * Count all bean definitions in any hierarchy in which this
     * factory participates. Includes counts of ancestor bean factories.
     * Beans that are "overridden" (specified in a descendant factory
     * with the same name) are counted only once.
     *
     * @param lbf
     * @return int count of beans including those defined in ancestor
     * factories
     */
    public static int countBeansIncludingAncestors(ListableBeanFactory lbf) {
        return beanNamesIncludingAncestors(lbf).size();
    }

    /**
     * Return all bean names in the factory, including
     * ancestor factories.
     *
     * @param lbf
     * @return Collection
     */
    public static Collection beanNamesIncludingAncestors(ListableBeanFactory lbf) {
        // Set of bean names: a set is used to ensure uniqueness
        Set s = new HashSet();
        String[] names = lbf.getBeanDefinitionNames();
        Collections.addAll(s, names);
        if (lbf instanceof HierarchicalBeanFactory) {
            HierarchicalBeanFactory hbf = (HierarchicalBeanFactory) lbf;
            if (hbf.getParentBeanFactory() != null && hbf.getParentBeanFactory() instanceof ListableBeanFactory) {
                s.addAll(beanNamesIncludingAncestors((ListableBeanFactory) hbf.getParentBeanFactory()));
            }
        }
        return s;
    }

    /**
     * Get all bean names including those defined in ancestor
     * factories for the given type
     *
     * @param type type beans must match
     * @param lbf  ListableBeanFactory. If this isn't also a HierarchicalBeanFactory,
     *             this method will return the same as it's own getBeanDefinitionNames() method.
     * @return Collection
     */
    public static Collection beanNamesIncludingAncestors(Class type, ListableBeanFactory lbf) {
        // Set of bean names: a set is used to ensure uniqueness
        Set s = new HashSet();
        String[] names = lbf.getBeanDefinitionNames(type);
        Collections.addAll(s, names);
        if (lbf instanceof HierarchicalBeanFactory) {
            HierarchicalBeanFactory hbf = (HierarchicalBeanFactory) lbf;
            if (hbf.getParentBeanFactory() != null && hbf.getParentBeanFactory() instanceof ListableBeanFactory) {
                s.addAll(beanNamesIncludingAncestors(type, (ListableBeanFactory) hbf.getParentBeanFactory()));
            }
        }
        return s;
    }

}
