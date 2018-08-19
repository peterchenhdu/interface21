/*
 * Copyright (c) 2011-2025 PiChen
 */

package org.summerframework.orm.hibernate;

/**
 * Hibernate transaction object, representing a SessionHolder.
 * Used as transaction object by HibernateTransactionManager.
 * <p>
 * <p>Instances of this class are the transaction objects that
 * HibernateTransactionManager returns. They nest the thread-bound
 * SessionHolder internally.
 * <p>
 * <p>Note: This is an SPI class, not intended to be used by applications.
 *
 * @author Juergen Hoeller
 * @see HibernateTransactionManager
 * @see SessionHolder
 * @since 02.05.2003
 */
public class HibernateTransactionObject {

    private SessionHolder sessionHolder;

    private boolean newSessionHolder;

    private Integer previousIsolationLevel;

    protected HibernateTransactionObject(SessionHolder sessionHolder, boolean newSessionHolder) {
        this.sessionHolder = sessionHolder;
        this.newSessionHolder = newSessionHolder;
    }

    public boolean isNewSessionHolder() {
        return newSessionHolder;
    }

    public SessionHolder getSessionHolder() {
        return sessionHolder;
    }

    protected void setPreviousIsolationLevel(Integer previousIsolationLevel) {
        this.previousIsolationLevel = previousIsolationLevel;
    }

    public Integer getPreviousIsolationLevel() {
        return previousIsolationLevel;
    }

}
