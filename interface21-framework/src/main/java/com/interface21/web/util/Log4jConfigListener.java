/*
 * Copyright (c) 2011-2025 PiChen
 */

package com.interface21.web.util;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Bootstrap listener for custom Log4J initialization in a web environment.
 * Simply delegates to Log4jWebConfigurer.
 * <p>
 * <p>Note: This listener should be placed before ContextLoaderListener
 * in web.xml, when using custom Log4J initialization.
 *
 * @author Juergen Hoeller
 * @see Log4jWebConfigurer
 * @see WebAppRootListener
 * @since 13.03.2003
 */
public class Log4jConfigListener implements ServletContextListener {

    public void contextInitialized(ServletContextEvent event) {
        Log4jWebConfigurer.initLogging(event.getServletContext());
    }

    public void contextDestroyed(ServletContextEvent event) {
        Log4jWebConfigurer.shutdownLogging(event.getServletContext());
    }

}
