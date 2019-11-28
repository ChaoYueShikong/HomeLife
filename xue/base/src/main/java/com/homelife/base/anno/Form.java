/*
 * Copyright (c) 2016 Corporation. All rights reserved.
 *
 */
package com.homelife.base.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Xue
 * @version 1.0
 * @description 表单数据，map/json
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Form {
    String value() default "";
}
