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
public enum HttpMethod {
    GET(0), POST(1), POST_FILE(2);

    HttpMethod(int intMethod) {
        this.intMethod = intMethod;
    }

    private int intMethod;

    public int getIntMethod() {
        return intMethod;
    }
}

