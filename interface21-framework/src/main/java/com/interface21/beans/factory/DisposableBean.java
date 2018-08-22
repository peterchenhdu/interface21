/*
 * Copyright (c) 2011-2025 PiChen
 */

package com.interface21.beans.factory;

import com.interface21.context.ApplicationContext;

/**
 * Interface to be implemented by beans that want to release resources
 * on destruction. A BeanFactory is supposed to invoke the destroy
 * method if it disposes a cached singleton. An application context
 * is supposed to dispose all of its singletons on close.
 *
 * @author Juergen Hoeller
 * @see ApplicationContext#close
 * @since 12.08.2003
 */
public interface DisposableBean {

    /**
     * Invoked by a BeanFactory on destruction of a singleton.
     *
     * @throws Exception in case of shutdown errors.
     *                   Exceptions will get logged but not rethrown to allow
     *                   other beans to release their resources too.
     */
    void destroy() throws Exception;

}
