/*
 * Copyright (c) 2011-2025 PiChen
 */

package org.summerframework.jdbc.datasource;

import java.sql.Connection;

/**
 * Connection holder, wrapping a JDBC Connection.
 * Features rollback-only support for nested JDBC transactions.
 * <p>
 * <p>DataSourceTransactionManager binds instances of this class
 * to the thread, for a given DataSource.
 * <p>
 * <p>Note: This is an SPI class, not intended to be used by applications.
 *
 * @author Juergen Hoeller
 * @see DataSourceTransactionManager
 * @see DataSourceTransactionObject
 * @see DataSourceUtils
 * @since 06.05.2003
 */
public class ConnectionHolder {

    private final Connection connection;

    private boolean rollbackOnly;

    public ConnectionHolder(Connection connection) {
        this.connection = connection;
    }

    public Connection getConnection() {
        return connection;
    }

    public void setRollbackOnly() {
        this.rollbackOnly = true;
    }

    public boolean isRollbackOnly() {
        return rollbackOnly;
    }

}
