/*
 * Copyright (c) 2016 Corporation. All rights reserved.
 *
 */
package com.homelife.util;

import android.content.Context;
import android.telephony.TelephonyManager;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

/**
 * @author Xue
 * @version 1.0
 * @description 处理集中的公共头信息
 */
public final class HeaderUtil {
    /**
     * @param map
     * @description 所有的头信息都从这里出来，注意，Authorization不能添加2次，
     * 即应用层不要在添加Authorization不能添加2次认证，否则Nginx会返回400错误（头信息最好不要添加相同的key）
     * map.put("host",android.os.Build.HOST);//引起Nginx无法转发找到Tomcat服务器 引起的Nginx 服务器返回500错误 (不能用host关键字)
     * @author Xue
     */
    @SuppressWarnings("deprecation")
    public static void setHeader(Map<String, String> map) {
        map.put("Authorization", "Basic " + BasicUtil.replaceBlank(BasicUtil.getAuth())); // xue auth认证
        map.put("userId", SharedUtil.getUserId()); //用户 id SharedUtil.getPreferStr("USERID")
        map.put("token", SharedUtil.getToken()); //用户 token
        map.put("deviceTokenCode", SharedUtil.getUmengToken()); //umeng token
        map.put("padEquipmentNo", SharedUtil.getIMEI()); // pad设备号getIMEI()  getAndroidUUID()  "A0000002FF7DCD"
        map.put("appversioncode", SharedUtil.getVersionCode() + "");
        map.put("appversionname", SharedUtil.getVersionName());
        map.put("longitude", SharedUtil.getLongitude()); //经度
        map.put("latitude", SharedUtil.getLatitude()); //纬度
        String address = SharedUtil.getAdress();
        if (StringUtil.isNotBlank(address)) {
            try {
                map.put("address", URLEncoder.encode(address, "UTF-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } //经纬度对应的地址位置文字
        }
        String location = SharedUtil.getLocation();
        if (StringUtil.isNotBlank(location)) { //经纬度+地址的定位JSON包
            map.put("location", location);
        }
        TelephonyManager telephonyManager = (TelephonyManager) Static.CONTEXT.getSystemService(Context.TELEPHONY_SERVICE);
        String meid = telephonyManager.getDeviceId();
        // meid="A0000002FF6F54";
        map.put("versionsdkname", android.os.Build.VERSION.SDK);
        // map.put("versionsdkcode", android.os.Build.VERSION.SDK_INT);
        map.put("display", android.os.Build.DISPLAY);
        map.put("product", android.os.Build.PRODUCT);
        map.put("board", android.os.Build.BOARD);
        map.put("bootloader", android.os.Build.BOOTLOADER);
        map.put("brand", android.os.Build.BRAND);
        map.put("cpuabi", android.os.Build.CPU_ABI);
        map.put("cpuabi2", android.os.Build.CPU_ABI2);
        map.put("device", android.os.Build.DEVICE);
        map.put("fingerprint", android.os.Build.FINGERPRINT);
        map.put("hardware", android.os.Build.HARDWARE);
        //map.put("host",android.os.Build.HOST); //引起Nginx无法转发找到Tomcat服务器 引起的Nginx 服务器返回500错误 (不能用host关键字)
        map.put("id", android.os.Build.ID);
        map.put("manufacturer", android.os.Build.MANUFACTURER);
        map.put("model", android.os.Build.MODEL);
        map.put("product", android.os.Build.PRODUCT);
        map.put("radio", android.os.Build.RADIO);
        map.put("serial", android.os.Build.SERIAL);
        map.put("tags", android.os.Build.TAGS);
        map.put("type", android.os.Build.TYPE);
        map.put("meid", meid); //meid  "A0000002FF7DCD"
        map.put("os", "ANDROID"); //IOS 操作系统
        map.put("ipAddress", SharedUtil.getIP());
    }

}
