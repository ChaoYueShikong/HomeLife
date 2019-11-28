/*
 * Copyright (c) 2016 Corporation. All rights reserved.
 *
 */
package com.homelife.util;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

/**
 * @Description 注解解析工具
 * @Author Xue
 * @CreateDate
 */
public class ReflectionUtil {
    public static List<Field> getFields(Class<?> clazz, List<Field> fieldList) {

        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            fieldList.add(field);
        }
        if (clazz.getSuperclass() == null) {
            return fieldList;
        }
        return getFields(clazz.getSuperclass(), fieldList);
    }

    public static List<Method> getMethods(Class<?> clazz, List<Method> methodList) {

        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            methodList.add(method);
        }
        if (clazz.getSuperclass() == null) {
            return methodList;
        }
        return getMethods(clazz.getSuperclass(), methodList);
    }

    public static <A extends Annotation> A getAnnotation(Class<?> clazz, Class<A> annotationType) {

        A anno = clazz.getAnnotation(annotationType);
        if (anno != null || clazz.getSuperclass() == null) {
            return anno;
        }
        return getAnnotation(clazz.getSuperclass(), annotationType);
    }
}
