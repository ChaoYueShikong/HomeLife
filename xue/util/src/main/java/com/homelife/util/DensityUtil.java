package com.homelife.util;

import android.content.Context;
import android.util.TypedValue;

/**
 * @Description px&dp转换的工具类
 * @Author Xue
 * @CreateDate
 */
public final class DensityUtil {

    private DensityUtil() {
        throw new AssertionError();
    }

    /**
     * @Description dp转px
     * @Author Xue
     * @CreateDate
     */
    public static int dp2px(Context context, float dpVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpVal, context.getResources().getDisplayMetrics());
    }

    /**
     * @Description dp转px
     * @Author Xue
     * @CreateDate
     */
    public static int dp2px(float dpVal) {
        return dp2px(Static.CONTEXT, dpVal);
    }

    /**
     * @Description sp转px
     * @Author Xue
     * @CreateDate
     */
    public static int sp2px(Context context, float spVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, spVal, context.getResources().getDisplayMetrics());
    }

    /**
     * @Description sp转px
     * @Author Xue
     * @CreateDate
     */
    public static int sp2px(float spVal) {
        return sp2px(Static.CONTEXT, spVal);
    }

    /**
     * @Description px转dp
     * @Author Xue
     * @CreateDate
     */
    public static float px2dp(Context context, float pxVal) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (pxVal / scale);
    }

    /**
     * @Description px转dp
     * @Author Xue
     * @CreateDate
     */
    public static float px2dp(float pxVal) {
        return px2dp(Static.CONTEXT, pxVal);
    }

    /**
     * @Description px转sp
     * @Author Xue
     * @CreateDate
     */
    public static float px2sp(Context context, float pxVal) {
        return (pxVal / context.getResources().getDisplayMetrics().scaledDensity);
    }

    /**
     * @Description px转sp
     * @Author Xue
     * @CreateDate
     */
    public static float px2sp(float pxVal) {
        return px2sp(Static.CONTEXT, pxVal);
    }

}
