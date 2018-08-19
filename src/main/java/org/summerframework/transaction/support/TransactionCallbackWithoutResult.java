/*
 * Copyright (c) 2011-2025 PiChen
 */

package org.summerframework.transaction.support;

import org.summerframework.transaction.TransactionStatus;

/**
 * Simple convenience class for TransactionCallback implementation.
 * Allows for implementing a doInTransaction version without result,
 * i.e. without the need for a return statement.
 *
 * @author Juergen Hoeller
 * @see TransactionTemplate
 * @since 28.03.2003
 */
public abstract class TransactionCallbackWithoutResult implements TransactionCallback {

    public final Object doInTransaction(TransactionStatus status) {
        doInTransactionWithoutResult(status);
        return null;
    }

    /**
     * Gets called by TransactionTemplate.execute within a transactional context.
     * Does not need to care about transactions itself, although it can retrieve
     * and influence the status of the current transaction via the given status
     * object, e.g. setting rollback-only.
     * <p>
     * <p>A RuntimeException thrown by the callback is treated as application
     * exception that enforces a rollback. An exception gets propagated to the
     * caller of the template.
     * <p>
     * <p>Note when using JTA: JTA transactions only work with transactional
     * JNDI resources, so implementations need to use such resources if they
     * want transaction support.
     *
     * @param status associated transaction status
     * @see TransactionTemplate#execute
     */
    protected abstract void doInTransactionWithoutResult(TransactionStatus status);

}
