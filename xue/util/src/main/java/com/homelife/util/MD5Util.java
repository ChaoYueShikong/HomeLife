/*
 * Copyright (c) 2016 Corporation. All rights reserved.
 *
 */
package com.homelife.util;

import java.security.MessageDigest;

/**
 * @author Xue
 * @version 1.0
 * @description MD5加密工具
 */
public final class MD5Util {
    private static final char[] hexDigits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    /**
     * @return 把密文转换成十六进制的字符串形式
     * @description 字节数组转十六进制字符串
     * @author Xue
     */
    public static String byteArrayToHexString(byte[] md) {
        int j = md.length;
        char[] str = new char[j * 2];
        int k = 0;
        for (int i = 0; i < j; i++) {
            byte byte0 = md[i];
            str[k++] = hexDigits[byte0 >>> 4 & 0xf];
            str[k++] = hexDigits[byte0 & 0xf];
        }
        return new String(str);
    }

    /**
     * @return 加密后的字符串
     * @description MD5加密签名
     * @author Xue
     * @createDate 2016年9月18日
     * s需要加密的字符串
     */
    public static String MD5(String s) {
        try {
            byte[] btInput = s.getBytes();
            MessageDigest mdInst = MessageDigest.getInstance("MD5"); // 获得MD5摘要算法的 MessageDigest 对象
            mdInst.update(btInput); // 使用指定的字节更新摘要
            byte[] md = mdInst.digest(); // 获得密文
            String str = byteArrayToHexString(md); // 把密文转换成十六进制的字符串形式
            return str;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
}
