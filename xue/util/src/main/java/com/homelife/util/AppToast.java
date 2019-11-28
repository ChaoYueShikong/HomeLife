/*
 * Copyright (c) 2016  Corporation. All rights reserved.
 *
 */

package com.homelife.util;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


/**
 * @Description 封装Toast工具类
 * @Author Xue
 * @CreateDate
 */
public final class AppToast {
    private static Toast mToast;

    private AppToast() {
        throw new AssertionError();
    }

    /**
     * @description 头顶显示TOAST
     * @author Xue
     * @version 1.0
     */
    public static Toast getInstance(String text) {
        return getInstance(text, Gravity.CENTER, Toast.LENGTH_SHORT);
    }

    /**
     * @description 可以设置TOAST的位置信息
     * @author Xue
     * @version 1.0
     */
    public static Toast getInstance(String text, int position) {
        return getInstance(text, position, Toast.LENGTH_SHORT);
    }

    /**
     * @param duration = Toast.LENGTH_SHORT，Toast.LENGTH_LONG
     * @param position 位置默认是   Gravity.TOP  （Gravity.CENTER）
     * @description 获取统一的自定义的单例Toast 防止重复弹出TOAST
     * @author Xue
     */
    public static Toast getInstance(String text, int position, int duration) {
        try {
            View view = Static.INFLATER.inflate(R.layout.core_toast_dialog_common, null);
            TextView tv = (TextView) view.findViewById(R.id.tv_core_toast);
            if (StringUtil.isNotBlank(text)) {
                tv.setText(text);
            }
            if (mToast == null) {
                mToast = Toast.makeText(Static.CONTEXT, text, duration);
            }
            mToast.setView(view);
            mToast.setGravity(position, 0, 100);
            mToast.setDuration(duration);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mToast;
    }


    /**
     * @description 离线显示自定义动画Toast
     * @author Xue
     */
    public static void showToastTop() {
        DrawerToast.getInstance(Static.CONTEXT).show();
    }

    /**
     * @param msg 必须输入要显示的字
     * @description 显示从上往下弹出的自定义Toast
     * @author Xue
     */
    public static void showToastTop(String msg) {
        DrawerToast.getInstance(Static.CONTEXT).show(msg);
    }

    /**
     * @description
     * @author Xue
     * @createDate 2016-4-3
     */
    public static void showToastCent() {
        showToastCent("", Toast.LENGTH_LONG);
    }

    /**
     * @param duration
     * @description
     * @author Xue
     * @createDate 2016-4-7
     */
    public static void showToastCent(int duration) {
        showToastCent("", duration);
    }

    /**
     * @param str
     * @description
     * @author Xue
     */
    public static void showToastCent(String str) {
        showToastCent(str, Toast.LENGTH_LONG);
    }

    /**
     * @description 自己定义中间显示Dialog
     * @author Xue
     */
    @SuppressLint("InflateParams")
    public static void showToastCent(String text, int duration) {
        try {
            if (StringUtil.isNotBlank(text)) {
                getInstance(text, Gravity.CENTER).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @description
     * @author Xue
     */
    public static void showToast(String text) {
        showToast(text, Toast.LENGTH_SHORT);
    }

    /**
     * @param text
     * @param duration
     * @description
     * @author Xue
     */
    public static void showToast(String text, int duration) {
        showToast(Static.CONTEXT, text, duration);
    }

    /**
     * @param context
     * @param text
     * @param duration
     * @description 显示自定义TOAST
     * @author Xue
     */
    public static void showToast(Context context, String text, int duration) {
        try {
            if (StringUtil.isNotBlank(text)) {
                getInstance(text).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @param resId String资源中ID
     * @description
     * @author Xue
     */
    public static void showToast(int resId) {
        showToast(resId, Toast.LENGTH_SHORT);
    }

    /**
     * @param resId    资源String中ID
     * @param duration
     * @description
     * @author Xue
     */
    public static void showToast(int resId, int duration) {
        showToast(Static.CONTEXT, resId, duration);
    }

    /**
     * @param context
     * @param resId    资源String中ID
     * @param duration
     * @description 资源转Str
     * @author Xue
     * @createDate 2016-5-31
     */
    public static void showToast(Context context, int resId, int duration) {
        String strMsg = "";
        if (resId != 0) {
            strMsg = context.getResources().getText(resId) + "";
        }
        showToast(context, strMsg, duration);
    }

    /**
     * @param text
     * @description 线程中显示TOAST
     * @author Xue
     */
    public static void showToastOnThread(final String text) {
        //final long threadId = Thread.currentThread().getId();
        if (Looper.myLooper() != Looper.getMainLooper()) {
            Handler mainThread = new Handler(Looper.getMainLooper());
            mainThread.post(new Runnable() {
                @Override
                public void run() {
                    showToast(text);
                }
            });
            return;
        }
    }

    /**
     * @Description 一定会UI上显示
     * @Author Xue
     * @CreateDate
     */
    public static void showToastOnUI(Activity activity, final String text) {
        if (activity == null) {
            return;
        }
        activity.runOnUiThread(new Runnable() {
            public void run() {
                showToast(text);
            }
        });
    }
}
