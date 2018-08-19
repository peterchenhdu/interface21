/*
 * Copyright (c) 2011-2025 PiChen
 */

package org.summerframework.context.support;

import org.summerframework.context.ApplicationEvent;
import org.summerframework.context.ApplicationContext;

/**
 * Event raised when an ApplicationContext gets closed.
 *
 * @author Juergen Hoeller
 * @since 12.08.2003
 */
public class ContextClosedEvent extends ApplicationEvent {

    /**
     * Creates a new ContextClosedEvent.
     *
     * @param source the ApplicationContext
     */
    public ContextClosedEvent(ApplicationContext source) {
        super(source);
    }

    public ApplicationContext getApplicationContext() {
        return (ApplicationContext) getSource();
    }

}
