/*
 * Copyright (c) 2016 Corporation. All rights reserved.
 *
 */
package com.homelife.util;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;

/**
 * @Description 软键盘隐藏显示工具集
 * @Author Xue
 * @CreateDate
 */
public final class SoftInputUtil {
    private SoftInputUtil() {
        throw new AssertionError();
    }

    /**
     * @description 如果输入法在窗口上已经显示，则隐藏，反之则显示
     * @author Xue
     */
    public static void showOrHide() {
        InputMethodManager imm = (InputMethodManager) Static.CONTEXT.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
    }

    /**
     * @description SHOW_FORCED表示强制显示
     * @author Xue
     * @view接受软键盘输入的视图
     */
    public static void showSoftInput(View view) {
        InputMethodManager imm = (InputMethodManager) Static.CONTEXT.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(view, InputMethodManager.SHOW_FORCED);
    }

    /**
     * @description Activity隐藏系统默认的输入法
     * @author Xue
     */
    public static void hideSysSoftInput(Activity activity) {
        InputMethodManager imm = (InputMethodManager) Static.CONTEXT.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }

    /**
     * @return 若返回true，则表示输入法打开
     * @description 获取输入法打开的状态
     * @author Xue
     */
    public static boolean isOpen() {
        InputMethodManager imm = (InputMethodManager) Static.CONTEXT.getSystemService(Context.INPUT_METHOD_SERVICE);
        return imm.isActive();
    }

    /**
     * @param view
     * @description 强制隐藏键盘
     * @author Xue
     */
    public static void hideShow(View view) {
        InputMethodManager imm = (InputMethodManager) Static.CONTEXT.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0); // 强制隐藏键盘
    }

    /**
     * @param view 一般是EditText 一般控件xml需要添加属性
     *             android:focusable="false"
     *             android:focusableInTouchMode="false"
     * @description 点击该控件重新获取焦点并打开键盘（配合hideShow使用）
     * @author Xue
     */
    public static void requstShow(final View view) {
        view.setOnClickListener(new OnClickListener() { // 打开键盘
            @Override
            public void onClick(View v) {
                InputMethodManager imm = (InputMethodManager) Static.CONTEXT.getSystemService(Context.INPUT_METHOD_SERVICE);
                // view.clearFocus(); //失去焦点
                view.setFocusable(true);
                view.setFocusableInTouchMode(true);
                view.requestFocus();
                imm.showSoftInput(view, InputMethodManager.SHOW_FORCED);
            }
        });
    }

    /**
     * @Description 打开软键盘
     * @Author Xue
     * @CreateDate
     */
    public static void openKeybord(View view) {
        InputMethodManager imm = (InputMethodManager) Static.CONTEXT.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(view, InputMethodManager.RESULT_SHOWN);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
    }

    /**
     * @Description 关闭软键盘
     * @Author Xue
     * @CreateDate
     */
    public static void closeKeybord(View view) {
        InputMethodManager imm = (InputMethodManager) Static.CONTEXT.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

}
