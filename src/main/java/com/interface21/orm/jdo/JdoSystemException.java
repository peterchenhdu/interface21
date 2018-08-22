/*
 * Copyright (c) 2011-2025 PiChen
 */

package org.interface21.orm.jdo;

import org.interface21.dao.UncategorizedDataAccessException;

/**
 * JDO exception that gets thrown on system errors.
 *
 * @author Juergen Hoeller
 * @since 03.06.2003
 */
public class JdoSystemException extends UncategorizedDataAccessException {

    public JdoSystemException(String s, Throwable ex) {
        super(s, ex);
    }

}
