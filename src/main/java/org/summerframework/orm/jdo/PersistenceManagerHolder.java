/*
 * Copyright (c) 2011-2025 PiChen
 */

package org.summerframework.orm.jdo;

import javax.jdo.PersistenceManager;

/**
 * Holder wrapping a JDO PersistenceManager.
 * Features rollback-only support for nested JDO transactions.
 * <p>
 * <p>JdoTransactionManager binds instances of this class
 * to the thread, for a given PersistenceManagerFactory.
 * <p>
 * <p>Note: This is an SPI class, not intended to be used by applications.
 *
 * @author Juergen Hoeller
 * @see JdoTransactionManager
 * @see PersistenceManagerFactoryUtils
 * @since 03.06.2003
 */
public class PersistenceManagerHolder {

    private PersistenceManager persistenceManager;

    private boolean rollbackOnly;

    public PersistenceManagerHolder(PersistenceManager persistenceManager) {
        this.persistenceManager = persistenceManager;
    }

    public PersistenceManager getPersistenceManager() {
        return persistenceManager;
    }

    public void setRollbackOnly() {
        this.rollbackOnly = true;
    }

    public boolean isRollbackOnly() {
        return rollbackOnly;
    }

}
