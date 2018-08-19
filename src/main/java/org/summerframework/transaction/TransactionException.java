/*
 * Copyright (c) 2011-2025 PiChen
 */

/*
 * The Spring Framework is published under the terms
 * of the Apache Software License.
 */

package org.summerframework.transaction;

import org.summerframework.core.NestedRuntimeException;

/**
 * Superclass for all transaction exceptions.
 *
 * @author Rod Johnson
 * @version $Revision$
 * @since 17-Mar-2003
 */
public abstract class TransactionException extends NestedRuntimeException {

    public TransactionException(String msg) {
        super(msg);
    }

    public TransactionException(String msg, Throwable ex) {
        super(msg, ex);
    }

}
