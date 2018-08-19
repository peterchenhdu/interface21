/*
 * Copyright (c) 2011-2025 PiChen
 */

package org.summerframework.aop.framework;


import org.summerframework.core.TimeStamped;

/**
 *
 */
public class TimestampIntroductionInterceptor extends DelegatingIntroductionInterceptor
        implements TimeStamped {

    private long ts;


    public TimestampIntroductionInterceptor(long ts) {
        this();
        this.ts = ts;
    }

    public void setTime(long ts) {
        this.ts = ts;
    }

    /**
     * Constructor for TestAspectInterface.
     *
     * @param interfacesToPublish
     */
    public TimestampIntroductionInterceptor() {
    }

    /**
     * @see TimeStamped#getTimeStamp()
     */
    public long getTimeStamp() {
        System.out.println("Invoked getTimeStamp method");
        return ts;
    }

}
