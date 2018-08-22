
/*
 * Copyright (c) 2011-2025 PiChen
 */

package com.interface21.context;

import com.interface21.context.ApplicationEvent;
import com.interface21.context.ApplicationListener;

/**
 * Add as a bean. Should also listen.
 */
public class BeanThatListens implements ApplicationListener {

    private int events;

    /**
     * Constructor for BeanThatListeners.
     */
    public BeanThatListens() {
        super();
    }

    public void zero() {
        events = 0;
    }

    /**
     * @see ApplicationListener#onApplicationEvent(ApplicationEvent)
     */
    public void onApplicationEvent(ApplicationEvent e) {
        ++events;
        //System.out.println("Bean that listens heard event");
    }

    public int getEventCount() {
        return events;
    }

}
