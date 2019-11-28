/*
 * Copyright (c) 2016 Corporation. All rights reserved.
 */
package com.homelife.base.anno;

/**
 * @Description 缓存类型, GET请求，POST请求，POST_SYNC需要模拟服务器数据的进行同步的请求
 * @author Xue
 * @version 1.0
 */
public enum CacheType {
    GET(0), POST(1), POST_SYNC(2);

    CacheType(int type) {
        this.cacheType = type;
    }

    private int cacheType; //缓存类型

    private String name;

    private long cacheTime; // 缓存时间  控制缓存存储多久

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getCacheType() {
        return cacheType;
    }

    public void setCacheType(int cacheType) {
        this.cacheType = cacheType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getCacheTime() {
        return cacheTime;
    }

    public void setCacheTime(long cacheTime) {
        this.cacheTime = cacheTime;
    }

    private String desc; //描述
}
