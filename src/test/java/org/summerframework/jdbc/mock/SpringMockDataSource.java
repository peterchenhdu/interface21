/*
 * Copyright (c) 2011-2025 PiChen
 */
package org.summerframework.jdbc.mock;

import com.mockobjects.sql.MockDataSource;
import org.summerframework.jdbc.datasource.SmartDataSource;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.logging.Logger;

/**
 * @author <a href="mailto:tcook@interprisesoftware.com">Trevor D. Cook</a>
 * @version $Id$
 * @task enter type comments
 */
public class SpringMockDataSource
        extends MockDataSource
        implements SmartDataSource {

    /**
     * Constructor for SpringMockDataSource.
     */
    public SpringMockDataSource() {
        super();
    }

    /**
     * @see SmartDataSource#shouldClose(java.sql.Connection)
     */
    public boolean shouldClose(Connection conn) {
        return false;
    }

    public <T> T unwrap(Class<T> iface) throws SQLException {
        return null;
    }

    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        return false;
    }

    public Logger getParentLogger() throws SQLFeatureNotSupportedException {
        return null;
    }
}
