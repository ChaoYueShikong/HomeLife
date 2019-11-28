package com.homelife.xhs.recyclerview.common;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by xue on 2019/2/21.
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface HolderAnnotation {
    int layoutId();
}
