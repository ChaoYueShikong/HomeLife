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
 * @description
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Inflater {

    int value();

}

