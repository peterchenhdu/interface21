
/*
 * Copyright (c) 2011-2025 PiChen
 */

package org.summerframework.beans;

public interface ITestBean {

    int getAge();

    void setAge(int age);

    String getName();

    void setName(String name);

    ITestBean getSpouse();

    void setSpouse(ITestBean spouse);

    /**
     * t null no error
     */
    void exceptional(Throwable t) throws Throwable;

    Object returnsThis();


}
