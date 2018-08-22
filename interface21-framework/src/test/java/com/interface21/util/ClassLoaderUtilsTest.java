/*
 * Copyright (c) 2011-2025 PiChen
 */

package com.interface21.util;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * @author PiChen
 * @since 2018/8/19 11:05
 */
public class ClassLoaderUtilsTest {
//    private static final Log LOGGER = LogFactory.getLog(ClassLoaderUtilsTest.class);

    @Test
    public void getResourceAsStream() throws Exception {
        InputStream is = ClassLoaderUtils.getResourceAsStream(ClassLoaderUtilsTest.class, "log4j.properties");
        BufferedReader in = new BufferedReader(new InputStreamReader(is));
        in.lines().forEach(System.out::println);
    }

    @Test
    public void showClassLoaderHierarchy() throws Exception {
        System.out.print(ClassLoaderUtils.showClassLoaderHierarchy(
                new ClassLoaderUtilsTest(),
                "test",
                "\n",
                "----"));
    }

    @Test
    public void showClassLoaderHierarchy1() throws Exception {
    }

}