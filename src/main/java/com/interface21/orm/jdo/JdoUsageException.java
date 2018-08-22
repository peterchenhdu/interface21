/*
 * Copyright (c) 2011-2025 PiChen
 */

package org.interface21.orm.jdo;

import org.interface21.dao.InvalidDataAccessApiUsageException;

/**
 * JDO exception that gets thrown on invalid API usage.
 *
 * @author Juergen Hoeller
 * @since 03.06.2003
 */
public class JdoUsageException extends InvalidDataAccessApiUsageException {

    public JdoUsageException(String s, Throwable ex) {
        super(s, ex);
    }

}
