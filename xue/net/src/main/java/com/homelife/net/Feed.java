/*
 * Copyright (c) 2016 Corporation. All rights reserved.
 *
 */
package com.homelife.net;

import java.io.Serializable;

/**
 * @author Xue
 * @version 1.0
 * @description 所有数据类基类(feed-供给，流入)
 */
public class Feed<T> implements Serializable {
    protected int code; // 200 成功
    protected String msg; // 消息
    protected T data; //各种数据类型 真正需要解释的数据结构


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

}
