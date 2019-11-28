package com.homelife.util;

import android.app.Activity;
import android.content.Context;

/**
 * @Description 线程安全的网络进度条
 * @Author Xue
 * @CreateDate 2017/9/19
 */
public final class HttpUtil {

    /**
     * @Description 线程安全的显示进度条
     * @Author Xue
     * @CreateDate
     */
    public static void showLoading(Context mContext) {
        showLoading(mContext, "");
    }

    /**
     * @Description 线程安全的显示进度条
     * @Author Xue
     * @CreateDate
     */
    public static void showLoading(final Context mContext, final String msg) {
        if (mContext == null || !(mContext instanceof Activity) || ((Activity) mContext).isFinishing()) {
            return;
        }
        ((Activity) mContext).runOnUiThread(() -> {
            NetDialogUtil.getInstance().showDialog(mContext, msg);
        });
    }

    /**
     * @Description 取消对话框
     * @Author Xue
     * @CreateDate
     */
    public static void hideLoading(final Context mContext) {
        if (mContext == null || !(mContext instanceof Activity) || ((Activity) mContext).isFinishing()) {
            return; //扩展需抛出异常
        }
        ((Activity) mContext).runOnUiThread(() -> {
            NetDialogUtil.getInstance().cancleDialog(mContext);
        });
    }


}
