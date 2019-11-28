/*
 * Copyright (c) 2016 Corporation. All rights reserved.
 *
 */

package com.homelife.util;

import android.content.Context;
import android.view.LayoutInflater;

/**
 * @Description 常用的静态常量
 * @Author Xue
 * @CreateDate
 */
public final class Static {
    private Static() {
        throw new AssertionError();
    }

    public static Context CONTEXT = null;
    public static LayoutInflater INFLATER = null;
}
