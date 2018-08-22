/*
 * Copyright (c) 2011-2025 PiChen
 */

/*
 * The Spring Framework is published under the terms
 * of the Apache Software License.
 */

package org.interface21.transaction;

/**
 * Exception thrown when an attempt is made to begin a transaction
 * but this would amount to a nested transaction, which is not
 * supporting by the underlying transaction implementation.
 *
 * @author Rod Johnson
 * @version $Revision$
 * @since 17-Mar-2003
 */
public class NestedTransactionNotPermittedException extends CannotCreateTransactionException {

    public NestedTransactionNotPermittedException(String msg) {
        super(msg);
    }

    public NestedTransactionNotPermittedException(String msg, Throwable ex) {
        super(msg, ex);
    }

}
