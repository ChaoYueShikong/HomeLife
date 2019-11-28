package com.homelife.net.internal;


import com.homelife.util.ObjectUtil;
import okhttp3.Interceptor;

/**
 * @Description 基类拦截器
 * @Author Xue
 * @CreateDate
 */
public abstract class BaseInterceptor<T> implements Interceptor {
    protected T t;

    protected BaseInterceptor(T t) {
        ObjectUtil.checkNonNull(t, "t == null");
        this.t = t;
    }

    protected T getParams() {
        return t;
    }
}
