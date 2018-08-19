/*
 * Copyright (c) 2011-2025 PiChen
 */

package org.summerframework.context.support;

import org.summerframework.context.ApplicationEvent;
import org.summerframework.context.ApplicationContext;

/**
 * Event raised when an ApplicationContext gets initialized or refreshed.
 *
 * @author Juergen Hoeller
 * @since 04.03.2003
 */
public class ContextRefreshedEvent extends ApplicationEvent {

    /**
     * Creates a new ContextRefreshedEvent.
     *
     * @param source the ApplicationContext
     */
    public ContextRefreshedEvent(ApplicationContext source) {
        super(source);
    }

    public ApplicationContext getApplicationContext() {
        return (ApplicationContext) getSource();
    }

}
