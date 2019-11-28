/*
 * Copyright (c) 2016 Corporation. All rights reserved.
 *
 */
package com.homelife.util;

import android.util.Log;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * @Description json通用工具
 * @Author Xue
 * @CreateDate
 */
public final class JsonUtil {

    public static final String TAG = "JsonUtil";

    /**
     * @Description map -->  json
     * @Author Xue
     * @CreateDate
     */
    public static String map2Json(Map<String, String> map) {
        StringBuilder jsonBuffer = new StringBuilder();
        if (map != null) {
            Set<String> keys = map.keySet();
            String key = "";
            String value = "";
            jsonBuffer.append("{");
            for (Iterator<String> it = keys.iterator(); it.hasNext();) {
                key = (String) it.next();
                value = map.get(key);
                if (value.contains("{")) { //如果value中的值有{说明有JSON对象，不需要加双引号"
                    jsonBuffer.append("\"" + key + "\"" + ":" + value); // 加双引号
                } else {
                    jsonBuffer.append("\"" + key + "\"" + ":" + "\"" + value + "\""); // 加双引号
                }
                if (it.hasNext()) {
                    jsonBuffer.append(",");
                }
            }
        }
        return jsonBuffer.append("}").toString();
    }

    /**
     * @description 测试
     * @author Xue
     * @createDate
     * @version 1.0
     */
    public static void main(String[] args) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("amount", "10000");
        map.put("customerId", "973");
        map.put("productId", "544");
        map.put("extras", "{\"couponId\":\"" + "FHB" + "\"}");
        Log.d(TAG,"String === " + map2Json(map)); //{"amount":"10000","productId":"544","customerId":"973","extras":{"couponId":null}}
    }

}
