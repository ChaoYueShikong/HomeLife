package com.homelife.util;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

/**
 * @Description App应用工具
 * @Author Xue
 * @CreateDate
 */
public final class AppUtil {
    private AppUtil() {
        throw new AssertionError();
    }

    /**
     * @Description 获取应用程序名称
     * @Author Xue
     * @CreateDate
     */
    public static String getAppName() {
        try {
            PackageManager packageManager = Static.CONTEXT.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(Static.CONTEXT.getPackageName(), 0);
            int labelRes = packageInfo.applicationInfo.labelRes;
            return Static.CONTEXT.getResources().getString(labelRes);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @Description 当前应用的版本名称
     * @Author Xue
     * @CreateDate
     */
    public static String getVersionName() {
        try {
            PackageManager packageManager = Static.CONTEXT.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(Static.CONTEXT.getPackageName(), 0);
            return packageInfo.versionName;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
