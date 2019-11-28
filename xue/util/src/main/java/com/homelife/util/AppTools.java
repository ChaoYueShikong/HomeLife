/*
 * Copyright (c) 2016 Corporation. All rights reserved.
 *
 */
package com.homelife.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.telephony.TelephonyManager;
import android.util.Log;

import java.io.UnsupportedEncodingException;

/**
 * @author Xue
 * @version 1.0
 * @description 工具类
 */
public final class AppTools {
    private AppTools() {
    }

    private static final int GB_SP_DIFF = 160;

    private static final int[] secPosValueList = {1601, 1637, 1833, 2078, 2274, 2302, 2433, 2594, 2787, 3106, 3212, 3472, 3635, 3722, 3730, 3858, 4027, 4086,
            4390, 4558, 4684, 4925, 5249, 5600}; // 存放国标一级汉字不同读音的起始区位码

    private static final char[] firstLetter = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'w', 'x', 'y',
            'z'}; // 存放国标一级汉字不同读音的起始区位码对应读音

    /**
     * @param context
     * @return android:versionName="1.0.0.12"
     * @description 获取版本名称
     * @author Xue
     */
    public static String getAPPVersion(Context context) {
        String appVersion = "";
        PackageManager manager = context.getPackageManager();
        try {
            PackageInfo info = manager.getPackageInfo(context.getPackageName(), 0);
            return info.versionName;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return appVersion;
    }

    /**
     * @param context
     * @description 获取版本号
     * @author Xue
     * @return比如：android:versionCode="11"
     */
    public static int getAPPVersionCode(Context context) {
        PackageManager manager = context.getPackageManager();
        try {
            PackageInfo info = manager.getPackageInfo(context.getPackageName(), 0);
            return info.versionCode;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * @param context
     * @description 获取APP包名的路径
     * @author Xue
     * @return比如：package="com.ms.ui.activity"
     */
    public static String getAPPPackageName(Context context) {
        PackageManager manager = context.getPackageManager();
        try {
            PackageInfo info = manager.getPackageInfo(context.getPackageName(), 0);
            return info.packageName;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * @param characters
     * @return
     * @description
     * @author Xue
     */
    public static String getInitials(String characters) {
        if (characters == null) {
            return "";
        }
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < characters.length(); i++) {
            char ch = characters.charAt(i);
            if ((ch >> 7) == 0) {
                // 判断是否为汉字，如果左移7为0就不是汉字，否则是汉字
            } else {
                char spell = getFirstLetter(ch);
                buffer.append(String.valueOf(spell));
            }
        }
        return buffer.toString();
    }

    /**
     * @param ch
     * @return
     * @description 获取一个汉字的首字母
     * @author Xue
     */
    public static Character getFirstLetter(char ch) {
        byte[] uniCode = null;
        try {
            uniCode = String.valueOf(ch).getBytes("GBK");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
        if (uniCode[0] < 128 && uniCode[0] > 0) { // 非汉字
            return null;
        } else {
            return convert(uniCode);
        }
    }

    /**
     * @Description 获取一个汉字的拼音首字母。 GB码两个字节分别减去160，转换成10进制码组合就可以得到区位码
     * 例如汉字“你”的GB码是0xC4/0xE3，分别减去0xA0（160）
     * 就是0x24/0x43 0x24转成10进制就是36，0x43是67，那么它的区位码就是3667，在对照表中读音为‘n’
     * @Author Xue
     * @CreateDate
     */
    public static char convert(byte[] bytes) {
        char result = '-';
        int secPosValue = 0;
        int i;
        for (i = 0; i < bytes.length; i++) {
            bytes[i] -= GB_SP_DIFF;
        }
        secPosValue = bytes[0] * 100 + bytes[1];
        for (i = 0; i < 23; i++) {
            if (secPosValue >= secPosValueList[i] && secPosValue < secPosValueList[i + 1]) {
                result = firstLetter[i];
                break;
            }
        }
        return result;
    }

    /**
     * @Description 权限申请后设置IMEI
     * @Author Xue
     * @CreateDate
     */
    public static void setIMEI() {
        TelephonyManager tm = (TelephonyManager) Static.CONTEXT.getSystemService(Context.TELEPHONY_SERVICE);
        if (StringUtil.isNotBlank(tm.getDeviceId())) {
            SharedUtil.setPreferStr(SharedUtil.IMEI, tm.getDeviceId()); // IMEI号
        } else {
            SharedUtil.setPreferStr(SharedUtil.IMEI, "1234567890"); // IMEI号如果没有就返回默认1234567890
        }
    }

    /**
     * @Description AppTools
     * @Author Xue
     * @CreateDate 2018/9/20 上午2:50
     */
    public static int compareVersion(String version1) {
        return compareVersion(version1, getAPPVersion(Static.CONTEXT));
    }

    /**
     * 版本号比较
     *
     * @param version1
     * @param version2
     * @return 0代表相等，1代表version1大于version2，-1代表version1小于version2
     * 1.  主版本号
     * 2.  次版本号
     * 3.  修正版本号
     * 4.  编译版本号
     * 例如：2.1.3 ，3.7.5，10.2.0
     */
    public static int compareVersion(String version1, String version2) {
        if (StringUtil.isBlank(version1) || StringUtil.isBlank(version2) || version1.equals(version2)) {
            return 0;
        }
        String[] version1Array = version1.split("\\.");
        String[] version2Array = version2.split("\\.");
        Log.d("HomePageActivity", "version1Array==" + version1Array.length);
        Log.d("HomePageActivity", "version2Array==" + version2Array.length);
        int index = 0;
        // 获取最小长度值
        int minLen = Math.min(version1Array.length, version2Array.length);
        int diff = 0;
        // 循环判断每位的大小
        Log.d("HomePageActivity", "verTag2=2222=" + version1Array[index]);
        while (index < minLen
                && (diff = Integer.parseInt(version1Array[index])
                - Integer.parseInt(version2Array[index])) == 0) {
            index++;
        }
        if (diff == 0) {
            // 如果位数不一致，比较多余位数
            for (int i = index; i < version1Array.length; i++) {
                if (Integer.parseInt(version1Array[i]) > 0) {
                    return 1;
                }
            }

            for (int i = index; i < version2Array.length; i++) {
                if (Integer.parseInt(version2Array[i]) > 0) {
                    return -1;
                }
            }
            return 0;
        } else {
            return diff > 0 ? 1 : -1;
        }
    }

    /**
     * @param args
     * @description
     * @author Xue
     */
    public static void main(String[] args) {

    }

}
