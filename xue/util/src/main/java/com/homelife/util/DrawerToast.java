/*
 * Copyright (c) 2016  Corporation. All rights reserved.
 *
 */

package com.homelife.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @Description 自定义Toast
 * @Author Xue
 */
public final class DrawerToast {
    private static final String TAG = "DrawerToast";
    private static int currentapiVersion = android.os.Build.VERSION.SDK_INT; // 当前Android系统版本
    private static final int TIME_START_ANIM = 1000; // 入场动画持续时间
    private static final int TIME_END_ANIM = 1000; // 离场动画持续时间
    private static final int TIME_DURATION = 3500; // 每条Toast显示持续时间
    private static DrawerToast instance; // 单例
    private boolean mIsShow; // 记录当前Toast的内容是否已经在显示
    private Toast mToast; // 以下为反射相关内容
    private Field mTN;
    private Object mObj;
    private Method showMethod, hideMethod;
    private Handler mHandler; // UI线程句柄
    private Context mContext;
    private LinearLayout mTopView, mTopView2; // 顶层布局
    private LinearLayout mView; // 内容布局
    private LinearLayout.LayoutParams lp_WW, lp_MM; // 布局属性
    private int screenWidth; // 屏幕宽度
    private int screenHeight; // 屏幕高度
    private boolean hasReflectException = false; // 反射过程中是否出现异常的标志

    public static DrawerToast getInstance(Context context) {
        if (instance == null) {
            instance = new DrawerToast(context);
        }
        return instance;
    }

    private DrawerToast(Context context) {
        if (context == null || context.getApplicationContext() == null) {
            throw new NullPointerException("context can't be null");
        }
        mContext = context.getApplicationContext();
        initView();
        initTN();
        if (instance != null) { // 防反射获取实例
            throw new NullPointerException("error");
        }
    }

    /**
     * @description 初始化视图控件
     * @author Xue
     */
    private void initView() {
        mHandler = new Handler(mContext.getMainLooper());
        mIsShow = false; // 记录当前Toast的内容是否已经在显示

        lp_WW = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        lp_MM = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);

        DisplayMetrics mDisplayMetrics = mContext.getResources().getDisplayMetrics();
        screenWidth = mDisplayMetrics.widthPixels;
        screenHeight = mDisplayMetrics.heightPixels;

        mTopView = new LinearLayout(mContext);
        mTopView.setLayoutParams(lp_MM);
        mTopView.setOrientation(LinearLayout.VERTICAL);
        mTopView.setGravity(Gravity.CENTER);

        mTopView2 = new LinearLayout(mContext);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(screenWidth, screenHeight);
        mTopView2.setLayoutParams(params);
        mTopView2.setOrientation(LinearLayout.VERTICAL);
        mTopView2.setGravity(Gravity.NO_GRAVITY); // BOTTOM

        mView = new LinearLayout(mContext);
        mView.setLayoutParams(lp_MM);
        mView.setOrientation(LinearLayout.VERTICAL);
        mView.setGravity(Gravity.CENTER_HORIZONTAL); // BOTTOM
        // mView.setGravity(Gravity.NO_GRAVITY);//BOTTOM
        mView.setY(20);

        View gapView = new View(mContext);
        gapView.setLayoutParams(new LinearLayout.LayoutParams(screenWidth, screenHeight / 4));
        gapView.setY(20);

        mView.addView(gapView);
        mTopView.addView(mTopView2);
        mTopView2.addView(mView);
        // resetDefaultBackgroundAndTextColor();
    }

    /**
     * @param msg
     * @description 显示自定义Toast
     * @author Xue
     */
    public void show(String msg) {
        // show(getTextView(msg), null, null, null);
        if (!mIsShow) {
            mIsShow = true;
            show(getCommonView(msg), null, null, null);
        }

    }

    /**
     * @description
     * @author Xue
     */
    public void show() {
        // show(getTextView(msg), null, null, null);
        if (!mIsShow) {
            mIsShow = true;
            show(getView(), null, null, null);
        }

    }

    public void show(View v) {
        show(v, null, null, null);
    }

    public void show(String msg, long duration) {
        show(getView(), duration, null, null);
    }

    public void show(View v, long duration) {
        show(v, duration, null, null);
    }

    /**
     * @param msg       消息内容
     * @param duration  持续时间，单位为毫秒
     * @param startAnim 入场动画
     * @param endAnim   离场动画
     * @description 显示一条Toast
     * @author Xue
     */
    public void show(String msg, Long duration, Animation startAnim, Animation endAnim) {
        show(getView(), duration, startAnim, endAnim);
    }

    /**
     * @param v         显示的内容
     * @param duration  持续时间，单位为毫秒
     * @param startAnim 入场动画
     * @param endAnim   离场动画
     * @description
     * @author Xue
     */
    public void show(final View v, Long duration, Animation startAnim, final Animation endAnim) {
        // 反射过程异常时则使用源生Toast
        if (hasReflectException) {
            Toast t = new Toast(mContext);
            t.setView(v);
            t.setDuration(Toast.LENGTH_SHORT);
            t.show();
            initTN(); // 重新获取反射对象
            return;
        }
        if (mView.getChildCount() == 1) { // 显示顶层容器控件
            showToast();
        }
        if (startAnim == null) { // 获得入场动画
            startAnim = getStartAnimation();
        }
        v.clearAnimation();
        v.startAnimation(startAnim);
        mView.addView(v, 0); // 把传入的toast显示出来
        mHandler.postDelayed(new Runnable() { // 延迟后隐藏传入toast
            @Override
            public void run() {
                hide(v, endAnim);
            }
        }, duration == null ? TIME_DURATION : duration);

    }

    /**
     * @param v
     * @param endAnim
     * @description 隐藏指定控件
     * @author Xue
     */
    public void hide(final View v, Animation endAnim) {
        if (v == null || mView.indexOfChild(v) < 0) {
            return;
        }
        if (endAnim == null) { // 获得出场动画
            endAnim = getEndAnimation();
        }
        v.clearAnimation();
        v.startAnimation(endAnim); // 开始出场动画
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() { // 动画结束后移除控件
                if (v == null || mView.indexOfChild(v) < 0) {
                    return;
                }
                mView.removeView(v); // 移除指定控件
                if (mView.getChildCount() == 1) { // 隐藏顶层容器控件
                    hideToast();
                }
            }
        }, TIME_END_ANIM);
    }

    /**
     * @return
     * @description 针对离线提醒
     * @author Xue
     */
    @SuppressLint("InflateParams")
    public View getView() {
        LayoutInflater inflate = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflate.inflate(R.layout.core_toast_off_line, null);
        v.setLayoutParams(lp_WW);
        return v;
    }

    /**
     * @param msg
     * @return
     * @description 获取通用的toast
     * @author Xue
     */
    public View getCommonView(String msg) {
        LayoutInflater inflate = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflate.inflate(R.layout.core_toast_common, null);
        TextView tv = (TextView) v.findViewById(R.id.tv_toast);
        tv.setText(msg);
        v.setLayoutParams(lp_WW);
        return v;
    }

    /**
     * @return
     * @description 获得入场动画
     * @author Xue
     */
    protected Animation getStartAnimation() {
        AlphaAnimation animAlpha = new AlphaAnimation(0, 1);
        animAlpha.setDuration(TIME_START_ANIM);
        animAlpha.setFillAfter(true);
        // TranslateAnimation animTrans = new TranslateAnimation(Animation.RELATIVE_TO_PARENT, 1.5f,
        // Animation.RELATIVE_TO_PARENT, 0f,Animation.RELATIVE_TO_PARENT, 0f, Animation.RELATIVE_TO_PARENT, 0f);
        TranslateAnimation animTrans = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0f, Animation.RELATIVE_TO_SELF,
                0f, Animation.RELATIVE_TO_SELF, -1.5f, Animation.RELATIVE_TO_SELF, 0f);
        animTrans.setDuration(TIME_START_ANIM);
        animTrans.setFillAfter(true);
        animTrans.setInterpolator(new DecelerateInterpolator());
        AnimationSet sets = new AnimationSet(true);
        sets.addAnimation(animAlpha);
        sets.addAnimation(animTrans);
        return sets;
    }

    /**
     * @return
     * @description 获得离场动画
     * @author Xue
     */
    protected Animation getEndAnimation() {
        AlphaAnimation animAlpha = new AlphaAnimation(1, 0);
        animAlpha.setDuration(TIME_END_ANIM);
        animAlpha.setFillAfter(true);
        // TranslateAnimation animTrans = new TranslateAnimation(Animation.RELATIVE_TO_PARENT, 0f, Animation.RELATIVE_TO_PARENT, -1.5f,
        // Animation.RELATIVE_TO_PARENT, 0f, Animation.RELATIVE_TO_PARENT, 0f);
        TranslateAnimation animTrans = new TranslateAnimation(Animation.RELATIVE_TO_PARENT, 0f, Animation.RELATIVE_TO_PARENT,
                0f, Animation.RELATIVE_TO_PARENT, 0f, Animation.RELATIVE_TO_PARENT, -1.5f);
        animTrans.setDuration(TIME_END_ANIM);
        animTrans.setFillAfter(true);
        animTrans.setInterpolator(new AccelerateInterpolator());
        AnimationSet sets = new AnimationSet(true);
        sets.addAnimation(animAlpha);
        sets.addAnimation(animTrans);
        return sets;
    }

    /**
     * @description 通过反射获得mTN下的show和hide方法
     * @author Xue
     */
    private void initTN() {
        mToast = new Toast(mContext);
        mToast.setView(mTopView);
        Class<Toast> clazz = Toast.class;
        try {
            mTN = clazz.getDeclaredField("mTN");
            mTN.setAccessible(true);
            mObj = mTN.get(mToast);
            showMethod = mObj.getClass().getDeclaredMethod("show", new Class<?>[]{}); //Class<?>[] null
            hideMethod = mObj.getClass().getDeclaredMethod("hide", new Class<?>[]{}); //null
            hasReflectException = false;
        } catch (NoSuchFieldException e) {
            hasReflectException = true;
            Log.d(TAG,e.getMessage());
        } catch (IllegalAccessException e) {
            hasReflectException = true;
            Log.d(TAG,e.getMessage());
        } catch (IllegalArgumentException e) {
            hasReflectException = true;
            Log.d(TAG,e.getMessage());
        } catch (NoSuchMethodException e) {
            hasReflectException = true;
            Log.d(TAG,e.getMessage());
        }
    }

    /**
     * @description 通过反射获得的show方法显示指定View
     * @author Xue
     */
    private void showToast() {
        try {
            if (currentapiVersion > 10) { // 高版本需要再次手动设置mNextView属性，2系列版本不需要
                Field mNextView = mObj.getClass().getDeclaredField("mNextView");
                mNextView.setAccessible(true);
                mNextView.set(mObj, mTopView);
            }
            showMethod.invoke(mObj, new Object[]{}); //null
            hasReflectException = false;
        } catch (Exception e) {
            hasReflectException = true;
            Log.d(TAG,e.getMessage());
        }

    }

    /**
     * @description 通过反射获得的hide方法隐藏指定View
     * @author Xue
     */
    private void hideToast() {
        mIsShow = false;
        try {
            hideMethod.invoke(mObj, new Object[]{}); //null
            hasReflectException = false;
        } catch (Exception e) {
            hasReflectException = true;
            Log.d(TAG,e.getMessage());
        }
    }
}
