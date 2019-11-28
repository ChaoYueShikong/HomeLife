/*
 * Copyright (c) 2016  Corporation. All rights reserved.
 *
 */

package com.homelife.xhs;

import com.homelife.base.base.AApplication;
import com.homelife.base.base.EventClass;
import com.homelife.net.HttpRequest;
import com.homelife.net.Options;
import com.homelife.net.internal.HttpHeaders;
import com.homelife.net.internal.Params;
import com.homelife.util.Static;
import com.homelife.util.StringUtil;
import com.homelife.xhs.biz.common.net.SdInterceptor;
import com.homelife.xhs.appconfig.Constant;
import com.homelife.xhs.crashAndReboot.CatchExcep;


/**
 * @Description
 * @Author Xue
 * @Version 1.0
 */
public class MainApplication extends AApplication {
    private static MainApplication instance;

    public static MainApplication getInstance(){
        return instance;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        Static.CONTEXT = this;
        instance = this;
        initHttpRequest();
        new CatchExcep().init();
    }


    private void initHttpRequest() {
        Params params = new Params.Builder()
                .bodyParam(Constant.Param.Key.APP_NAME, getParam(Constant.Param.Value.APP_NAME, "0"))
                .bodyParam(Constant.Param.Key.APP_VERSION, getParam(Constant.Param.Value.APP_VERSION, "0"))
                .build();


        Options options = new Options.Builder()
                .context(this)
                .debug(BuildConfig.DEBUG)
                .baseUrl(Constant.BASE_URL)//http://122.112.196.223/app-api  https://www.apiopen.top/
                .interceptor(new SdInterceptor())
                .params(params)
                .build();
        HttpRequest.init(options);
    }

    public String getParam(String value, String def) {
        if (StringUtil.isNotBlank(value)) {
            return value;
        } else {
            return def;
        }
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        HttpRequest.cancelAll();
    }


    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
    }

    @Override
    public void onEventOnBack(EventClass even) {
        super.onEventOnBack(even);
    }

}
