/*
 * Copyright (c) 2011-2025 PiChen
 */

package org.summerframework.web.context.support;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.summerframework.context.ApplicationEvent;
import org.summerframework.context.ApplicationListener;
import org.summerframework.util.ResponseTimeMonitorImpl;

/**
 * Listener that logs the response times of web requests.
 *
 * @author Rod Johnson
 * @version $RevisionId$
 * @since January 21, 2001
 */
public class PerformanceMonitorListener implements ApplicationListener {

    protected final Log logger = LogFactory.getLog(getClass());

    private ResponseTimeMonitorImpl responseTimeMonitor;

    public PerformanceMonitorListener() {
        responseTimeMonitor = new ResponseTimeMonitorImpl();
    }

    public void onApplicationEvent(ApplicationEvent event) {
        if (event instanceof RequestHandledEvent) {
            RequestHandledEvent rhe = (RequestHandledEvent) event;
            // Could use one monitor per URL
            responseTimeMonitor.recordResponseTime(rhe.getTimeMillis());
            if (logger.isInfoEnabled()) {
                // Stringifying objects is expensive. Don't do it unless it will show.
                logger.info("PerformanceMonitorListener: last=" + rhe.getTimeMillis() + "ms; " + responseTimeMonitor + "; client was " + rhe.getIpAddress());
            }
        }
    }

}
