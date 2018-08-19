/*
 * Copyright (c) 2011-2025 PiChen
 */

/*
 * The Spring Framework is published under the terms
 * of the Apache Software License.
 */

package org.summerframework.transaction.interceptor;

import org.summerframework.transaction.TransactionDefinition;

/**
 * This interface adds a rollBackOn specification to TransactionDefinition.
 * As custom rollBackOn is only possible with AOP, this class resides
 * in the AOP transaction package.
 *
 * @author Rod Johnson
 * @version $Revision$
 * @see DefaultTransactionAttribute
 * @since 16-Mar-2003
 */
public interface TransactionAttribute extends TransactionDefinition {

    /**
     * Should we roll back on a checked exception?
     *
     * @param ex the exception to evaluate
     * @return boolean rollback or not
     */
    boolean rollbackOn(Throwable ex);

}
