/*
 * Copyright (c) 2011-2025 PiChen
 */

package org.summerframework.jndi;

import org.summerframework.beans.PropertyValues;
import org.summerframework.beans.factory.FactoryBean;
import org.summerframework.jdbc.core.JdbcTemplate;

/**
 * FactoryBean that looks up a JNDI object. Behaves like the object when
 * used as bean reference, e.g. for JdbcTemplate's dataSource property.
 * Note that switching to e.g. DriverManagerDataSource is just a matter
 * of configuration: replace the definition of this FactoryBean with a
 * DriverManagerDataSource definition!
 * <p>
 * <p>The typical usage will be to register this as singleton factory
 * (e.g. for a certain JNDI DataSource) in an application context,
 * and give bean references to application services that need it.
 * <p>
 * <p>Of course, service implementations can lookup e.g. a DataSource from
 * JNDI themselves, but this class enables central configuration of the
 * JNDI name, and easy switching to non-JNDI replacements. The latter can
 * be used for test setups, standalone clients, etc.
 *
 * @author Juergen Hoeller
 * @see JdbcTemplate#setDataSource
 * @since 22.05.2003
 */
public class JndiObjectFactoryBean extends AbstractJndiLocator implements FactoryBean {

    private Object jndiObject;

    protected void located(Object o) {
        this.jndiObject = o;
    }

    /**
     * Return the singleton JNDI object.
     */
    public Object getObject() {
        return this.jndiObject;
    }

    public boolean isSingleton() {
        return false;
    }

    public PropertyValues getPropertyValues() {
        return null;
    }

}
