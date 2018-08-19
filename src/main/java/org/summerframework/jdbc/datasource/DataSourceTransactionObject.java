/*
 * Copyright (c) 2011-2025 PiChen
 */

package org.summerframework.jdbc.datasource;

/**
 * DataSource transaction object, representing a ConnectionHolder.
 * Used as transaction object by DataSourceTransactionManager.
 * <p>
 * <p>Note: This is an SPI class, not intended to be used by applications.
 *
 * @author Juergen Hoeller
 * @see DataSourceTransactionManager
 * @see ConnectionHolder
 * @since 02.05.2003
 */
public class DataSourceTransactionObject {

    private final ConnectionHolder connectionHolder;

    private Integer previousIsolationLevel;

    protected DataSourceTransactionObject(ConnectionHolder connectionHolder) {
        this.connectionHolder = connectionHolder;
    }

    public ConnectionHolder getConnectionHolder() {
        return connectionHolder;
    }

    protected void setPreviousIsolationLevel(Integer previousIsolationLevel) {
        this.previousIsolationLevel = previousIsolationLevel;
    }

    public Integer getPreviousIsolationLevel() {
        return previousIsolationLevel;
    }

}
