package com.homelife.xhs.utils;

import android.util.Log;

/**
 * author："houdongmin"
 * time：2019/5/21 0024 11:56
 * version:3.3
 * function:管理log打印
 * params:
 * result:
 */

public class MyLogUtils {

    private static final String TAG = "tag";
    private static boolean isDebug = true;// 是否需要打印

    public MyLogUtils() {
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    // 四个默认tag的函数
    public static void i(String msg) {
        if (isDebug){
            Log.i(TAG, msg);
        }
    }

    public static void d(String msg) {
        if (isDebug){
            Log.d(TAG, msg);
        }

    }

    public static void e(String msg) {
        if (isDebug){
            Log.e(TAG, msg);
        }

    }

    public static void v(String msg) {
        if (isDebug){
            Log.v(TAG, msg);
        }

    }

    // 下面是传入自定义tag的函数
    public static void i(String tag, String msg) {
        if (isDebug){
            Log.i(tag, msg);
        }

    }

    public static void d(String tag, String msg) {
        if (isDebug){
            Log.i(tag, msg);
        }

    }

    public static void e(String tag, String msg) {
        if (isDebug){
            Log.i(tag, msg);
        }

    }

    public static void v(String tag, String msg) {
        if (isDebug){
            Log.i(tag, msg);
        }

    }
}
