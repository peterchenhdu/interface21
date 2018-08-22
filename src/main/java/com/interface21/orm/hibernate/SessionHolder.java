/*
 * Copyright (c) 2011-2025 PiChen
 */

package com.interface21.orm.hibernate;

import net.sf.hibernate.Session;
import net.sf.hibernate.Transaction;

/**
 * Session holder, wrapping a Hibernate Session and a Hibernate Transaction.
 * Features rollback-only support for nested Hibernate transactions.
 * <p>
 * <p>HibernateTransactionManager binds instances of this class
 * to the thread, for a given SessionFactory.
 * <p>
 * <p>Note: This is an SPI class, not intended to be used by applications.
 *
 * @author Juergen Hoeller
 * @see HibernateTransactionManager
 * @see HibernateTransactionObject
 * @see SessionFactoryUtils
 * @since 06.05.2003
 */
public class SessionHolder {

    private final Session session;

    private Transaction transaction;

    private boolean rollbackOnly;

    public SessionHolder(Session session) {
        this.session = session;
    }

    public Session getSession() {
        return session;
    }

    protected void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public void setRollbackOnly() {
        this.rollbackOnly = true;
    }

    public boolean isRollbackOnly() {
        return rollbackOnly;
    }

}
