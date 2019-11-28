package com.homelife.net.internal;

import com.homelife.net.Options;
import com.homelife.net.config.Config;
import com.homelife.net.utils.RetrofitUtil;
import com.homelife.util.ObjectUtil;

import io.reactivex.disposables.Disposable;
import retrofit2.Retrofit;

/**
 * @Description 
 * @Author Xue
 * @CreateDate 
 */
public class RealHttpRequest {
    public RealHttpRequest init(Options options) {
        ObjectUtil.checkNonNull(options, "options == null");
        Config.setContext(options.getContext());
        Config.setsRetryOnConnectionFailure(options.isRetryOnConnectionFailure());
        Config.setConnectTimeout(options.getConnectTimeout());
        Config.setReadTimeout(options.getReadTimeout());
        Config.setWriteTimeout(options.getWriteTimeout());
        Config.setHosts(options.getHosts());
        Config.setCertificates(options.getCertificates());
        Config.setBaseUrl(options.getBaseUrl());
        Config.debug(options.isDebug());
        Config.setHttpHeaders(options.getHttpHeaders());
        Config.setParams(options.getParams());
        Config.setInterceptors(options.getInterceptors());
        Config.setNetworkInterceptors(options.getNetworkInterceptors());
        Retrofit retrofit = options.getRetrofit();
        if (ObjectUtil.isNull(retrofit)) {
            Config.setRetrofit(RetrofitUtil.buildRetrofit());
        } else {
            Config.setRetrofit(retrofit);
        }
        return this;
    }

    public <T> T create(Class<T> service) {
        ObjectUtil.checkNonNull(service, "service == null");
        return Config.getRetrofit().create(service);
    }

    public void addCall(Disposable call) {
        RetrofitUtil.addCall(call);
    }

    public void cancelCall(Disposable call) {
        RetrofitUtil.cancelCall(call);
    }

    public void cancelAll() {
        RetrofitUtil.cancelAll();
    }
}