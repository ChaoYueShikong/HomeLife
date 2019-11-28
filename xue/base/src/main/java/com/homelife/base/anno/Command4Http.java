/*
 * Copyright (c) 2016 Corporation. All rights reserved.
 *
 */

package com.homelife.base.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static com.homelife.base.anno.HttpMethod.GET;

/**
 * @Description
 * @Author Xue
 * @CreateDate
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Command4Http {
    String url();

    HttpMethod method() default GET;

    boolean isProcessUrl() default true;
}
