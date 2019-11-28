/*
 * Copyright (c) 2012, Xue Corporation, All Rights Reserved
 */
package com.homelife.util;

import java.util.HashMap;

/**
 * @author Xue
 * @version 1.0
 * @description 模拟实现sesion功能(注意key相同会覆盖)
 * @createDate
 */
public final class SessionUtil {
    private static final HashMap<String, Object> map = new HashMap<String, Object>();
    private static String defaultVal = "-1";

    private SessionUtil() {
        throw new AssertionError();
    }

    /**
     * @param key
     * @param val
     * @description
     * @author Xue
     * @createDate
     */
    public static void setStr(String key, String val) {
        map.put(key, val);
    }

    /**
     * @param key
     * @return
     * @description
     * @author Xue
     * @createDate
     */
    public static String getStr(String key) {
        return getStr(key, defaultVal);
    }

    /**
     * @param key
     * @param default_v
     * @return
     * @description
     * @author Xue
     * @createDate
     */
    public static String getStr(String key, String default_v) {
        String str = "";
        if (map.containsKey(key)) {
            str = (String) map.get(key);
        } else {
            str = default_v;
        }
        return str;
    }

    /**
     * @description
     * @author Xue
     * @createDate
     */
    public static void setObj(String key, Object val) {
        map.put(key, val);
    }

    /**
     * @description
     * @author Xue
     * @createDate
     */
    public static Object getObj(String key) {
        return getObj(key, null);
    }

    /**
     * @description
     * @author Xue
     * @createDate
     */
    public static Object getObj(String key, Object mObj) {
        Object obj = null;
        if (map.containsKey(key)) {
            obj = map.get(key);
        } else {
            obj = mObj;
        }
        return obj;
    }

    /**
     * @description
     * @author Xue
     * @createDate
     */
    public static void setInt(String key, int val) {
        setStr(key, String.valueOf(val));
    }

    /**
     * @param key
     * @return
     * @description
     * @author Xue
     * @createDate
     */
    public static int getInt(String key) {
        return getInt(key, 0);
    }

    /**
     * @description
     * @author Xue
     * @createDate
     */
    public static int getInt(String key, int def) {
        int a = 0;
        if (map.containsKey(key)) {
            a = Integer.valueOf((String) map.get(key));
        } else {
            a = def;
        }
        return a;
    }

    /**
     * @description
     * @author Xue
     * @createDate
     */
    public static void setLong(String key, long val) {
        setStr(key, String.valueOf(val));
    }

    /**
     * @description
     * @author Xue
     * @createDate
     */
    public static long getLong(String key) {
        return getLong(key, 0l);
    }

    /**
     * @description
     * @author Xue
     * @createDate
     */
    public static long getLong(String key, long def) {
        long a = 0l;
        if (map.containsKey(key)) {
            a = Long.valueOf((String) map.get(key));
        } else {
            a = def;
        }
        return a;
    }

    /**
     * @description
     * @author Xue
     * @createDate
     */
    public static void setBool(String key, boolean val) {
        setStr(key, String.valueOf(val));
    }

    /**
     * @description
     * @author Xue
     * @createDate
     */
    public static boolean getBool(String key) {
        return getBool(key, false);
    }

    /**
     * @description
     * @author Xue
     * @createDate
     */
    public static boolean getBool(String key, boolean def) {
        boolean a = false;
        if (map.containsKey(key)) {
            a = Boolean.valueOf((String) map.get(key));
        } else {
            a = def;
        }
        return a;
    }
}
