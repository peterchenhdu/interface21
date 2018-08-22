/*
 * Copyright (c) 2011-2025 PiChen
 */

package org.interface21.context.support;

import org.interface21.context.ApplicationEvent;
import org.interface21.context.ApplicationListener;

/**
 * Simple listener for debug use only that logs messages
 * to the console.
 * <p>
 * <p>Note: The ApplicationContext implementations included
 * in the framework do quite heavy debug-level logging via
 * Log4J, including published events. Thus, this listener
 * isn't necessary for debug logging.
 *
 * @author Rod Johnson
 * @since January 21, 2001
 */
public class ConsoleListener implements ApplicationListener {

    public void onApplicationEvent(ApplicationEvent e) {
        System.out.println(e.toString());
    }

}
