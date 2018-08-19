/*
 * Copyright (c) 2011-2025 PiChen
 */

/*
 * The Spring Framework is published under the terms
 * of the Apache Software License.
 */

package org.summerframework.transaction.interceptor;

/**
 * Tag class. Its class means it has the opposite
 * behaviour to the RollbackRule superclass.
 *
 * @author Rod Johnson
 * @version $Revision$
 * @since 09-Apr-2003
 */
public class NoRollbackRuleAttribute extends RollbackRuleAttribute {

    /**
     * @param exceptionName
     */
    public NoRollbackRuleAttribute(String exceptionName) {
        super(exceptionName);
    }

}
