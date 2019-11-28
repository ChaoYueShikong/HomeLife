/*
 * Copyright (c) 2016 Corporation. All rights reserved.
 *
 */

package com.homelife.base.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.PARAMETER})
public @interface ProtcolParam {
    String value();

    ParamType paramType() default ParamType.STRING;
}
