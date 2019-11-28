package com.homelife.xhs.model;

import com.homelife.net.HttpRequest;
import com.homelife.net.utils.RxJavaUtil;
import com.homelife.xhs.api.SafedoorService;
import com.homelife.xhs.bean.LoginResponse;

import io.reactivex.Single;
import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * 功能： 登录的
 * 作者：HDM
 * 创建时间：2019/9/27 17:48
 */
public class LoginModel {

    public Single<LoginResponse> onLogin(String json){
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), json);
        return HttpRequest.create(SafedoorService.class)
                .onLogin(body)
                .compose(RxJavaUtil.getSingleMainThread());
    }


}
