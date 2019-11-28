/*
 * Copyright (c) 2016 Corporation. All rights reserved.
 *
 */

package com.homelife.base.anno;

/**
 * @Description
 * @Author Xue
 * @CreateDate
 */
public enum ParamType {
    STRING(0), FILE(1);

    ParamType(int intMethod) {
        this.intMethod = intMethod;
    }

    private int intMethod = 0;

    public int getIntMethod() {
        return intMethod;
    }
}
