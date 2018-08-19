/*
 * Copyright (c) 2011-2025 PiChen
 */

package org.summerframework.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

/**
 * 解析类中的常量定义(public static final)
 * <p>
 *
 * @author Rod Johnson
 * @version $Id$
 * @since 16-Mar-2003
 */
public class Constants {
    private static final Log LOGGER = LogFactory.getLog(Constants.class);

    /**
     * 存放常量的Map
     */
    private Map<String, Object> map = new HashMap<>();

    /**
     * 需要被解析的类
     */
    private final Class clazz;

    /**
     * 构造方法.
     *
     * @param clazz 需要被解析的类.
     */
    public Constants(Class clazz) {
        this.clazz = clazz;
        Field[] fields = clazz.getFields();
        for (Field f : fields) {
            int modifiers = f.getModifiers();
            //public final static
            if (Modifier.isFinal(modifiers) && Modifier.isStatic(modifiers) && Modifier.isPublic(modifiers)) {
                String name = f.getName();
                try {
                    //static , so param set to null
                    Object value = f.get(null);
                    map.put(name, value);
                } catch (IllegalAccessException e) {
                    // Just leave this field and continue
                    LOGGER.error(e.toString(), e);
                }
            }
        }
    } // constructor

    /**
     * 获取常量map大小
     *
     * @return int map大小
     */
    public int getSize() {
        return this.map.size();
    }

    /**
     * 获取int常量
     *
     * @param code 常量名字
     * @return int int值
     * @throws ConstantException 找不到改常量或者类型不匹配
     * @see #asObject
     */
    public int asInt(String code) throws ConstantException {
        Object o = asObject(code);
        if (!(o instanceof Integer))
            throw new ConstantException(code, this.clazz, "not an int");
        return (Integer) o;
    }

    /**
     * 获取字符串常量
     *
     * @param code 常量名字
     * @return String 常量值.
     * @throws ConstantException 找不到改常量
     * @see #asObject
     */
    public String asString(String code) throws ConstantException {
        Object o = asObject(code);
        return o.toString();
    }

    /**
     * 根据常量名获取常量对象.
     *
     * @throws ConstantException 找不到改常量
     */
    public Object asObject(String code) throws ConstantException {
        code = code.toUpperCase();
        Object val = this.map.get(code);
        if (val == null)
            throw new ConstantException(code, this.clazz, "not found");
        return val;
    }

}
