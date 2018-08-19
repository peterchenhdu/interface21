/*
 * Copyright (c) 2011-2025 PiChen
 */

/*
 * The Spring Framework is published under the terms
 * of the Apache Software License.
 */

package org.summerframework.aop.framework;


public interface Lockable {
    void lock();

    void unlock();

    boolean locked();
}