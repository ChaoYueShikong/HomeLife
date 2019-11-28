/*
 * Copyright (c) 2016  Corporation. All rights reserved.
 *
 */
package com.homelife.util;

import android.util.Base64;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Xue
 * @version 1.0
 * @description Basic认证工具
 */
public final class BasicUtil {
    /**
     * @return
     * @description Basic认证
     * @author Xue
     */
    public static String getAuth() {
        String secretAuthString = "";
        String username = SharedUtil.getPreferStr("USERNAME");
        String password = SharedUtil.getPreferStr("PASSWORD");
        if (StringUtil.isNotBlank(username) && StringUtil.isNotBlank(password)) {
            //String auth = DesUtil.decode(username) + ":"
            // + new String(Base64.decode(DesUtil.decode(password), Base64.DEFAULT)); // DEC解密后认证 小于8位会报错 后期修复
            //String auth = username + ":" + password;// "u0000001:123456";
            String auth = DesUtil.decode(username) + ":" + DesUtil.decode(password); // DEC解密后认证 小于8位会报错 后期修复
            secretAuthString = Base64.encodeToString(auth.getBytes(), Base64.DEFAULT);
        }
        return secretAuthString;
    }

    /**
     * @return
     * @description
     * @author Xue
     */
    public static String replaceBlank(String str) {
        String dest = "";
        if (str != null) {
            Pattern p = Pattern.compile("\\s*|\t|\r|\n");
            Matcher m = p.matcher(str);
            dest = m.replaceAll("");
        }
        return dest;
    }
}
