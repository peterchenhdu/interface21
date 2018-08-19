/*
 * Copyright (c) 2011-2025 PiChen
 */

/*
 * The Spring Framework is published under the terms
 * of the Apache Software License.
 */

package org.summerframework.aop.framework;

import org.summerframework.core.NestedRuntimeException;

/**
 * @author Rod Johnson
 * @version $Revision$
 * @since 13-Mar-2003
 */
public class AopConfigException extends NestedRuntimeException {

    /**
     * @param mesg
     */
    public AopConfigException(String mesg) {
        super(mesg);
    }


}
