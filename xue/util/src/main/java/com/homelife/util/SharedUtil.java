/*
 * Copyright (c) 2016 Corporation. All rights reserved.
 *
 */

package com.homelife.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.Log;

//import com.alibaba.fastjson.JSON;

import java.util.List;
import java.util.Map;

/**
 * @Description 封装 sharepreferences
 * @Author Xue
 * @CreateDate
 */
public final class SharedUtil {
    private static final String TAG = "SharedUtil";
    public static final String SWIDTH = "SWIDTH"; // 屏幕宽度
    public static final String SHEIGHT = "SHEIGHT"; // 屏幕高度
    public static final String DPI_FLOAT = "DPI_FLOAT"; // 屏幕密度（像素比例：0.75/1.0/1.5/2.0）
    public static final String DPI_INT = "DPI_INT"; // 屏幕密度DPI（120 / 160 / 240）
    public static final String SDK_VERSION = "SDK_VERSION"; // sdk版本
    public static final String VERSION_NAME = "VERSION_NAME"; // 版本名称， android:versionName="1.0.0.12"
    public static final String VERSION_NO = "VERSION_NO"; // //版本号，android:versionCode="11"
    public static final String PACKAGE_NAME = "PACKAGE_NAME"; // 包名称，package="com.ms.ui.activity"
    public static final String MEMORY_SIZE = "MEMORY_SIZE"; // 内存缓存值
    public static final String MEMORY_MAX = "MEMORY_MAX"; // 应用程序最高可用内存是 8,16,24,32 KB
    public static final String CITYNAME = "CITYNAME"; // 城市名：上海
    public static final String CITYCODE = "CITYCODE"; // 城市代码：310000
    public static final String ADDRESS = "ADDRESS"; // 上海市长宁区金钟路999号(定位到的详细地址)
    public static final String LOCATION = "LOCATION"; // 定位信息JSON包（经度，纬度，地址）
    public static final String LONGITUDE = "LONGITUDE"; // 定位到的精度
    public static final String LATITUDE = "LATITUDE"; // 定位到的纬度
    public static final String IMEI = "IMEI"; // 手机IMEI号
    public static final String OBJ_TO_JSON = "OBJ_TO_JSON"; //对象转到JSON的String  对象类存储对象的名称
    public static final String LIST_TO_JSON = "LIST_TO_JSON"; //对象转到JSON的String  对象类存储对象的名称
    public static final String MAP_TO_JSON = "MAP_TO_JSON"; //对象转到JSON的String  对象类存储对象的名称
    public static final String ANDROID_ID = "ANDROID_ID"; //设备唯一号
    public static final String UMENG_TOKEN = "UMENG_TOKEN"; //umeng设备号  服务器---UMeng服务器---android设备

    public static final String TOKEN = "TOKEN"; //android设备编号，校验用户唯一性
    public static final String USERID = "USERID"; //用户ID
    public static final String IPADDRESS = "IPADDRESS"; //ip地址
    public static final String DB_VERSION = "DB_VERSION"; //当前数据库的版本方便扩展升级
    public static final String USER_NAME = "USER_NAME";
    public static final String CHANNEL = "CHANNEL";



    private SharedUtil() {
        throw new AssertionError();
    }

    public static void setUserName(String userName) {
        Log.e(TAG, "setUserName: " + userName);
        setPreferStr(USER_NAME, userName);
    }

    public static String getUserName() {
        return getPreferStr(USER_NAME);
    }

    /**
     * @return
     * @description 获取用户ID
     * @author Xue
     */
    public static String getUserId() {
        return getPreferStr(USERID);
    }

    /**
     * @return
     * @description 获取TOKEN
     * @author Xue
     */
    public static String getToken() {
        return getPreferStr(TOKEN);
    }

    /**
     * @return
     * @description 获取Umeng的token
     * @author Xue
     */
    public static String getUmengToken() {
        return getPreferStr(UMENG_TOKEN);
    }

    /**
     * @return
     * @description ANDROID-UUID
     * @author Xue
     */
    public static String getAndroidUUID() {
        return getPreferStr(ANDROID_ID);
    }

    /**
     * @return
     * @description IMEI
     * @author Xue
     */
    public static String getIMEI() {
        return getPreferStr(IMEI);
    }

    /**
     * @return
     * @description 获取精度
     * @author Xue
     */
    public static String getLongitude() {
        return getPreferStr(LONGITUDE);
    }

    /**
     * @return
     * @description 获取伟度
     * @author Xue
     */
    public static String getLatitude() {
        return getPreferStr(LATITUDE);
    }

    /**
     * @return
     * @description 获取图片地址
     * @author Xue
     */
    public static String getAdress() {
        return getPreferStr(ADDRESS);
    }

    /**
     * @return
     * @description 获取经度纬度和地址的，定位JSON包，可以是百度&高德&千寻
     * {"address":"%E4%B8%8A%E6%B5%B7%E5%B8%82%E6%9D%BE%E6%B1%9F%E5%8C%BA%E6%B6%9E%E5%9D%8A%E8%B7%AF705%E5%8F%B7",
     * "latitude":"31.156152","longitude":"121.314335"}
     * address考虑有中文必须做 URLEncoder.encode(address, "UTF-8")
     * @author Xue
     */
    public static String getLocation() {
        return getPreferStr(LOCATION);
    }

    /**
     * @return
     * @description 获取当前城市代号：310000
     * @author Xue
     */
    public static String getCityCode() {
        return getPreferStr(CITYCODE);
    }

    /**
     * @return
     * @description 获取当前城市名:上海
     * @author Xue
     */
    public static String getCityName() {
        return getPreferStr(CITYNAME);
    }

    /**
     * @return 480
     * @description 获取屏幕宽度
     * @author Xue
     */
    public static int getScreenWidth() {
        return getPreferInt(SWIDTH, 480);
    }

    /**
     * @return 800
     * @description 获取屏幕高度
     * @author Xue
     */
    public static int getScreenHeight() {
        return getPreferInt(SHEIGHT, 800);
    }

    /**
     * @return
     * @description 屏幕密度（像素比例：0.75/1.0/1.5/2.0）
     * @author Xue
     */
    public static String getDpiFloat() {
        return getPreferStr(DPI_FLOAT);
    }

    /**
     * @return
     * @description 获取屏幕密度DPI（120 / 160 / 240）
     * @author Xue
     */
    public static int getDpiInt() {
        return getPreferInt(DPI_INT, 240);
    }

    /**
     * @return
     * @description sdk版本
     * @author Xue
     */
    public static int getSdkVersion() {
        return getPreferInt(SDK_VERSION, 1);
    }

    /**
     * @return 5.1、1.0
     * @description 版本名称，比如：5.1、1.0
     * @author Xue
     */
    public static String getVersionName() {
        return getPreferStr(VERSION_NAME);
    }

    /**
     * @return VersionCode=1，2，3
     * @description 升级的版本号
     * @author Xue
     */
    public static int getVersionCode() {
        try {
            return Static.CONTEXT.getPackageManager().getPackageInfo(Static.CONTEXT.getPackageName(), 0).versionCode;
//            return getPreferInt(VERSION_NO, 1);
        } catch (Exception e) {
            return -1;
        }
    }

    /**
     * @return com.ms.ui.activity
     * @description package="com.ms.ui.activity"
     * @author Xue
     */
    public static String getPackageName() {
        return getPreferStr(PACKAGE_NAME);
    }

    /**
     * @return
     * @description 获取内存缓存大小
     * @author Xue
     */
    public static int getMemorySize() {
        return getPreferInt(MEMORY_SIZE, 1024 * 1024 * 8);
    }

    /**
     * @return
     * @description 应用程序最高可用内存，默认16MB
     * @author Xue
     */
    public static int getMemoryMax() {
        return getPreferInt(MEMORY_MAX, 1024 * 1024 * 16);
    }

    /**
     * @param mContext
     * @return
     * @description 获取包名
     * @author Xue
     */
    public static String getPackageName(Context mContext) {
        String str = "";
        PackageManager manager = mContext.getPackageManager();
        try {
            PackageInfo info = manager.getPackageInfo(mContext.getPackageName(), 0);
            str = info.packageName; // 包名
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str;
    }

    /**
     * @param pkg 跨进程的相同包名com.qx.wz 是A的包名
     * @Description 获取跨进程的Context
     * @Author Xue
     * @paramContext.CONTEXT_IGNORE_SECURITY 忽略安全检查标志
     */
    public static Context getContext(Context context, String pkg) {
        if (context == null) {
            context = Static.CONTEXT;
        }
        Context mContext = null;
        try {
            mContext = context.createPackageContext(pkg, Context.CONTEXT_IGNORE_SECURITY);
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mContext;
    }

    /**
     * @Description 通过包名获取跨进程的Context
     * @Author Xue
     * @CreateDate
     */
    public static Context getContext(String pkg) {
        return getContext(Static.CONTEXT, pkg);
    }

    /**
     * @param sp
     * @param key
     * @param strContent
     * @description 通过SharedPreferences设置key－velau
     * @author Xue
     */
    public static void setPrefer(SharedPreferences sp, String key, String strContent) {
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(key, strContent);
        //editor.apply();//提交到内存中，再异步写到文件，并且不需要返回写入成功与否的状态 Xue 会出现值丢失和存到值不一致
        editor.commit(); //同步写入内存和文件 Xue 性能会略差但是不大
    }

    /**
     * @param sp
     * @param key
     * @return
     * @description 通过SharedPreferences获取key
     * @author Xue
     */
    public static String getPrefer(SharedPreferences sp, String key) {
        String str = sp.getString(key, null);
        return str;
    }

    /**
     * @param mthis
     * @param key
     * @description 删除mthis的SharedPreferences的key值
     * @author Xue
     */
    public static void delPrefer(Context mthis, String key) {
        SharedPreferences sp = mthis.getSharedPreferences(getPackageName(mthis), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.remove(key);
        //editor.apply();//提交到内存中，再异步写到文件，并且不需要返回写入成功与否的状态 Xue 会出现值丢失和存到值不一致
        editor.commit(); //同步写入内存和文件 Xue 性能会略差但是不大
    }

    /**
     * @param mthis
     * @description 清除以包名为名字的所有preferences
     * @author Xue
     */
    public static void delAll(Context mthis) {
        SharedPreferences sp = mthis.getSharedPreferences(getPackageName(mthis), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        //editor.apply();//提交到内存中，再异步写到文件，并且不需要返回写入成功与否的状态 Xue 会出现值丢失和存到值不一致
        editor.commit(); //同步写入内存和文件 Xue 性能会略差但是不大
    }

    /**
     * @param sp
     * @param key
     * @description 删除指定SharedPreferences的key值
     * @author Xue
     */
    public static void delPrefer(SharedPreferences sp, String key) {
        SharedPreferences.Editor editor = sp.edit();
        editor.remove(key);
        //editor.apply();//提交到内存中，再异步写到文件，并且不需要返回写入成功与否的状态 Xue 会出现值丢失和存到值不一致
        editor.commit(); //同步写入内存和文件 Xue 性能会略差但是不大
    }

    /**
     * @param sp
     * @description 删除全部SharedPreferences的key值
     * @author Xue
     */
    public static void delAllPrefer(SharedPreferences sp) {
        SharedPreferences.Editor editor = sp.edit();
        editor.clear();
        //editor.apply();//提交到内存中，再异步写到文件，并且不需要返回写入成功与否的状态 Xue 会出现值丢失和存到值不一致
        editor.commit(); //同步写入内存和文件 Xue 性能会略差但是不大
    }

    /**
     * @param key
     * @description 设置要存储key值，指定文件名
     * @author Xue
     */
    public static void setPreferStr(String fileName, String key, String content) {
        setPreferStr(Static.CONTEXT, fileName, key, content);
    }

    /**
     * @param key
     * @description 设置要存储key值，指定文件名
     * @author Xue
     */
    public static void setPreferStr(Context mthis, String fileName, String key, String content) {
        if (null == content) {
            return;
        }
        SharedPreferences sp = mthis.getSharedPreferences(fileName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(key, content.trim());
        //editor.apply();//提交到内存中，再异步写到文件，并且不需要返回写入成功与否的状态 Xue 会出现值丢失和存到值不一致
        editor.commit(); //同步写入内存和文件 Xue 性能会略差但是不大
    }

    /**
     * @param fileName
     * @param key
     * @return
     * @description 从指定文件名读取key值
     * @author Xue
     */
    public static String getPreferStr(String fileName, String key) {
        return getPreferStr(Static.CONTEXT, fileName, key);
    }

    /**
     * @param mthis
     * @param fileName
     * @param key
     * @return
     * @description 从指定文件名读取key值
     * @author Xue
     */
    public static String getPreferStr(Context mthis, String fileName, String key) {
        SharedPreferences sp = mthis.getSharedPreferences(fileName, Context.MODE_PRIVATE);
        return (sp.getString(key, null));
    }

    /**
     * @param key
     * @return
     * @description 从指定文件名读取key值
     * @author Xue
     */
    public static void setPreferStr(String key, String content) {
        setPreferStr(Static.CONTEXT, key, content);
    }

    /**
     * @param mthis
     * @param key
     * @return
     * @description 从指定文件名读取key值
     * @author Xue
     */
    public static void setPreferStr(Context mthis, String key, String content) {
        SharedPreferences sp = mthis.getSharedPreferences(getPackageName(mthis), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(key, content);
        //editor.apply();//提交到内存中，再异步写到文件，并且不需要返回写入成功与否的状态 Xue 会出现值丢失和存到值不一致
        editor.commit(); //同步写入内存和文件 Xue 性能会略差但是不大
    }

    /**
     * @param key
     * @return
     * @description
     * @author Xue
     */
    public static String getPreferStr(String key) {
        return getPreferStr(Static.CONTEXT, key);
    }

    /**
     * @param mthis context
     * @param key
     * @return
     * @description 获取String值
     * @author Xue
     */
    public static String getPreferStr(Context mthis, String key) {
        if (mthis == null) {
            return "";
        }
        SharedPreferences sp = mthis.getSharedPreferences(getPackageName(mthis), Context.MODE_PRIVATE);
        return (sp.getString(key, ""));
    }

    /**
     * @param key
     * @param value
     * @description 设置bool值
     * @author Xue
     */
    public static void setPreferBool(String key, boolean value) {
        setPreferBool(Static.CONTEXT, key, value);
    }

    /**
     * @param mthis
     * @param key
     * @param content
     * @description 设置bool值
     * @author Xue
     */
    public static void setPreferBool(Context mthis, String key, boolean content) {
        SharedPreferences sp = mthis.getSharedPreferences(getPackageName(mthis), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean(key, content);
        //editor.apply();//提交到内存中，再异步写到文件，并且不需要返回写入成功与否的状态 Xue 会出现值丢失和存到值不一致
        editor.commit(); //同步写入内存和文件 Xue 性能会略差但是不大
    }

    /**
     * @param key
     * @param defValue
     * @return
     * @description bool
     * @author Xue
     */
    public static boolean getPreferBool(String key, boolean defValue) {
        return getPreferBool(Static.CONTEXT, key, defValue);
    }

    /**
     * @param mthis
     * @param key
     * @param defValue
     * @return
     * @description bool
     * @author Xue
     */
    public static boolean getPreferBool(Context mthis, String key, boolean defValue) {
        if (mthis == null) {
            return false;
        }
        SharedPreferences sp = mthis.getSharedPreferences(getPackageName(mthis), Context.MODE_PRIVATE);
        return sp.getBoolean(key, defValue);
    }

    /**
     * @param key
     * @param defValue
     * @return
     * @description int
     * @author Xue
     */
    public static int getPreferInt(String key, int defValue) {
        return getPreferInt(Static.CONTEXT, key, defValue);
    }

    /**
     * @param mthis
     * @param key
     * @param defValue
     * @return
     * @description int
     * @author Xue
     * @createDate 2014-7-31
     */
    public static int getPreferInt(Context mthis, String key, int defValue) {
        if (mthis == null) {
            return 0;
        }
        SharedPreferences sp = mthis.getSharedPreferences(getPackageName(mthis), Context.MODE_PRIVATE);
        return sp.getInt(key, defValue);
    }

    /**
     * @param key
     * @param content
     * @description int
     * @author Xue
     */
    public static void setPreferInt(String key, int content) {
        setPreferInt(Static.CONTEXT, key, content);
    }

    /**
     * @param mthis
     * @param key
     * @param content
     * @description int
     * @author Xue
     */
    public static void setPreferInt(Context mthis, String key, int content) {
        SharedPreferences sp = mthis.getSharedPreferences(getPackageName(mthis), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt(key, content);
        //editor.apply(); //提交到内存中，再异步写到文件，并且不需要返回写入成功与否的状态 Xue 会出现值丢失和存到值不一致
        editor.commit(); //同步写入内存和文件 Xue 性能会略差但是不大
    }


    /**
     * @param key
     * @return
     * @description
     * @author Xue
     */
    public static long getPreferLong(String key) {
        return getPreferLong(Static.CONTEXT, key, 0L);
    }

    /**
     * @param key
     * @param defValue
     * @return
     * @description long
     * @author Xue
     */
    public static long getPreferLong(String key, long defValue) {
        return getPreferLong(Static.CONTEXT, key, defValue);
    }

    /**
     * @param mthis
     * @param key
     * @param defValue
     * @return
     * @description long
     * @author Xue
     */
    public static long getPreferLong(Context mthis, String key, long defValue) {
        if (mthis == null) {
            return 0L;
        }
        SharedPreferences sp = mthis.getSharedPreferences(getPackageName(mthis), Context.MODE_PRIVATE);
        return sp.getLong(key, defValue);
    }

    /**
     * @param key
     * @param content
     * @description long
     * @author Xue
     */
    public static void setPreferLong(String key, long content) {
        setPreferLong(Static.CONTEXT, key, content);
    }

    /**
     * @param mthis
     * @param key
     * @param content
     * @description long 存储
     * @author Xue
     */
    public static void setPreferLong(Context mthis, String key, long content) {
        SharedPreferences sp = mthis.getSharedPreferences(getPackageName(mthis), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putLong(key, content);
        //editor.apply();//提交到内存中，再异步写到文件，并且不需要返回写入成功与否的状态 Xue 会出现值丢失和存到值不一致
        editor.commit(); //同步写入内存和文件 Xue 性能会略差但是不大
    }

    /**
     * @param key
     * @return
     * @description 获取Float值
     * @author Xue
     */
    public static float getPreferFloat(String key) {
        return getPreferFloat(Static.CONTEXT, key, 0.0f);
    }

    /**
     * @param key
     * @param defValue
     * @return
     * @description float
     * @author Xue
     * @createDate 2015-4-21
     */
    public static float getPreferFloat(String key, float defValue) {
        return getPreferFloat(Static.CONTEXT, key, defValue);
    }

    /**
     * @param mthis
     * @param key
     * @param defValue
     * @return 0.0f
     * @description float
     * @author Xue
     */
    public static float getPreferFloat(Context mthis, String key, float defValue) {
        if (mthis == null) {
            return 0.0f;
        }
        SharedPreferences sp = mthis.getSharedPreferences(getPackageName(mthis), Context.MODE_PRIVATE);
        return sp.getFloat(key, defValue);
    }

    /**
     * @param key
     * @param content
     * @description float
     * @author Xue
     */
    public static void setPreferFloat(String key, float content) {
        setPreferFloat(Static.CONTEXT, key, content);
    }

    /**
     * @param mthis
     * @param key
     * @param content
     * @description long
     * @author Xue
     */
    public static void setPreferFloat(Context mthis, String key, float content) {
        if (mthis == null) {
            return;
        }
        //Context.MODE_WORLD_READABLE  可以跨进程访问，需要把第2个进程的包传进来；
        SharedPreferences sp = mthis.getSharedPreferences(getPackageName(mthis), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putFloat(key, content);
        //editor.apply();//提交到内存中，再异步写到文件，并且不需要返回写入成功与否的状态 Xue 会出现值丢失和存到值不一致
        editor.commit(); //同步写入内存和文件 Xue 性能会略差但是不大
    }

    /**
     * @param key   存储对象的KEY
     * @param clazz 存储的对象Class
     * @return
     * @description 获取存储的对象
     * @author Xue
     */
    public static <T> T getObj(String key, Class<T> clazz) {
        T t = null;
//        try {
//            String str = SharedUtil.getPreferStr(OBJ_TO_JSON, key);
//            if (StringUtil.isNotBlank(str)) {
//                t = JSON.parseObject(str, clazz);
//            }
//        } catch (Exception e) {
//            e.getStackTrace();
//        }
        return t;
    }

    /**
     * @param t   要存储的对象
     * @param key 存储对象的KEY
     * @description 存储对象
     * @author Xue
     */
    public static <T> void setObj(String key, T t) {
//        String str = "";
//        if (t != null) {
//            str = JSON.toJSONString(t);
//        }
//        SharedUtil.setPreferStr(OBJ_TO_JSON, key, str);
    }


    /**
     * @param key
     * @param clazz
     * @return
     * @description 获取list对象
     * @author Xue
     */
    public static <T> List<T> getListObj(String key, Class<T> clazz) {
        List<T> t = null;
//        try {
//            String str = SharedUtil.getPreferStr(LIST_TO_JSON, key);
//            if (StringUtil.isNotBlank(str)) {
//                t = JSON.parseArray(str, clazz);
//            }
//        } catch (Exception e) {
//            e.getStackTrace();
//        }
        return t;
    }

    /**
     * @param key
     * @param t   List
     * @description 存储List对象
     * @author Xue
     */
    public static <T> void setListObj(String key, T t) {
//        String str = "";
//        if (t != null) {
//            str = JSON.toJSONString(t);
//        }
//        SharedUtil.setPreferStr(LIST_TO_JSON, key, str);
    }

    /**
     * @param key
     * @return
     * @description 获取Map对象
     * @author Xue
     * @createDate 2015-3-31
     */
    @SuppressWarnings("unchecked")
    public static <T> Map<String, T> getMap(String key) {
        Map<String, T> map = null;
//        try {
//            String str = SharedUtil.getPreferStr(MAP_TO_JSON, key);
//            if (StringUtil.isNotBlank(str)) {
//                map = ((Map<String, T>) JSON.parse(str));
//            }
//        } catch (Exception e) {
//            e.getStackTrace();
//        }
        return map;
    }

    /**
     * @param key
     * @param t
     * @description 设置Map对象
     * @author Xue
     */
    public static <T> void setMap(String key, T t) {
//        String str = "";
//        if (t != null) {
//            str = JSON.toJSONString(t);
//        }
//        SharedUtil.setPreferStr(MAP_TO_JSON, key, str);
    }

    /**
     * @description 获取IP地址
     * @author Xue
     * @createDate
     * @version 1.0
     */
    public static String getIP() {
        return getPreferStr(IPADDRESS); //en_US,zh_CN
    }

    /**
     * @description 设置IP地址
     * @author Xue
     * @version 1.0
     */
    public static void setIP(String language) {
        setPreferStr(IPADDRESS, language); //
    }

    /**
     * @description 获取数据库版本
     * @author Xue
     * @version 1.0
     */
    public static int getDbVersion() {
        return getPreferInt(DB_VERSION, 1);
    }

    /**
     * @description 设置数据库版本
     * @author Xue
     * @version 1.0
     */
    public static void setDbVersion(int newVersion) {
        setPreferInt(DB_VERSION, newVersion);
    }

    /**
     * @Description 移除某个key值已经对应的值
     * @Author Xue
     * @CreateDate
     */
    public static void remove(String key) {
        SharedPreferences sp = Static.CONTEXT.getSharedPreferences(getPackageName(Static.CONTEXT), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.remove(key);
        //editor.apply();
        editor.commit();
    }

    /**
     * @Description 清除所有数据
     * @Author Xue
     * @CreateDate
     */
    public static void clear() {
        SharedPreferences sp = Static.CONTEXT.getSharedPreferences(getPackageName(Static.CONTEXT), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.clear();
        //SharedPreferencesCompat.apply(editor);
        editor.commit();
    }

    /**
     * @Description 查询某个key是否已经存在
     * @Author Xue
     * @CreateDate
     */
    public static boolean contains(Context context, String key) {
        SharedPreferences sp = Static.CONTEXT.getSharedPreferences(getPackageName(Static.CONTEXT), Context.MODE_PRIVATE);
        return sp.contains(key);
    }

    /**
     * @Description 返回所有的键值对
     * @Author Xue
     * @CreateDate
     */
    public static Map<String, ?> getAll(Context context) {
        SharedPreferences sp = Static.CONTEXT.getSharedPreferences(getPackageName(Static.CONTEXT), Context.MODE_PRIVATE);
        return sp.getAll();
    }

}
