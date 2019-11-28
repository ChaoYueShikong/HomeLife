/*
 * Copyright (c) 2016 Corporation. All rights reserved.
 *
 */

package com.homelife.base.mvp;


import com.homelife.util.AppToast;

/**
 * @Description 数据模型基础类(需要切换到RX2)
 * @Author Xue
 * @Version 1.0
 */
public abstract class BaseModel implements IModel {


    /**
     * @description 校验接口合法性
     * @author Xue
     * @createDate
     * @version 1.0
     */
    public <T> void validateServiceInterface(Class<T> service) {
        if (service == null) {
            AppToast.showToast("服务接口不能为空！");
        }
        if (!service.isInterface()) {
            throw new IllegalArgumentException("API declarations must be interfaces.");
        }
        if (service.getInterfaces().length > 0) {
            throw new IllegalArgumentException("API interfaces must not extend other interfaces.");
        }
    }

}
