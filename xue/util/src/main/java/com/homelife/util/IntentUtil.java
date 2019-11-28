/*
 * Copyright (c) 2016 Corporation. All rights reserved.
 *
 */
package com.homelife.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;

/**
 * @Description Intent通用工具
 * @Author Xue
 * @CreateDate
 */
public final class IntentUtil {
    /**
     * @description 启动跳转的Act
     * @author Xue
     */
    public static void startActivity(Context context, Class<?> clazz) {
        Intent mIntent = new Intent(context, clazz);
        context.startActivity(mIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
    }

    /**
     * @description 启动跳转的Act FLAG_ACTIVITY_NEW_TASK
     * @author Xue
     */
    public static void startActivity(Class<?> clazz) {
        Intent mIntent = new Intent(Static.CONTEXT, clazz);
        Static.CONTEXT.startActivity(mIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
    }

    /**
     * @description 启动跳转的Act
     * @author Xue
     */
    public static void startActivityForResult(Activity context, Class<?> clazz, int request) {
        Intent mIntent = new Intent(context, clazz);
        context.startActivityForResult(mIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP), request);
    }

    /**
     * @description 启动跳转的Act
     * @author Xue
     */
    public static void startActivityForResult(Activity context, Intent intent, int request) {
        context.startActivityForResult(intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP), request);
    }

    /**
     * @description 启动跳转的Act
     * @author Xue
     */
    public static void startActivityForResult(Fragment context, Intent intent, int request) {
        context.startActivityForResult(intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP), request);
    }

    /**
     * @description 启动跳转的Act
     * @author Xue
     */
    public static void startActivity(Context context, Intent mIntent) {
        context.startActivity(mIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
    }

    /**
     * @description
     * @author Xue
     */
    public static void startActivity(Context context, Class<?> clazz, Bundle mBundle) {
        if (context != null) {
            Intent mIntent = new Intent(context, clazz);
            mIntent.putExtras(mBundle);
            context.startActivity(mIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
        }
    }

}
