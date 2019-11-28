package com.homelife.xhs.biz.login.vpmc;


import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.homelife.base.mvp.ModelManager;
import com.homelife.net.RxException;
import com.homelife.net.utils.RxJavaUtil;
import com.homelife.util.SharedUtil;
import com.homelife.xhs.bean.LoginBean;
import com.homelife.xhs.bean.LoginParams;
import com.homelife.xhs.bean.LoginResponse;
import com.homelife.xhs.biz.common.net.SdSingleObserver;
import com.homelife.xhs.model.LoginModel;
import com.homelife.xhs.utils.MyLogUtils;

import java.util.HashMap;

/**
 * @Description 数据仓库（从Model中获取网络&缓存&数据库中数据）
 * @Author Xue
 * @CreateDate
 */
public class LoginDataRepository {

    private LoginModel mModel = ModelManager.getModelInstance(LoginModel.class);
    private LoginPresenter mPresenter;
    private Context mContext;

    public LoginDataRepository(LoginPresenter presenter) {
        this.mPresenter = presenter;
        this.mContext = mContext;
    }


    public void onLogin(String phone, String password) {
        String json = new Gson().toJson(new LoginParams(phone, password));
        mModel.onLogin(json)
                .compose(RxJavaUtil.getSingleResponseTransformer())
                .subscribe(new SdSingleObserver<LoginResponse>(mContext) {
                    @Override
                    public void onSucceed(LoginResponse object) {
                        LoginResponse.DataBean data = object.getData();
                        if (null != data) {
                            String token = data.getToken();
                            SharedUtil.setPreferStr(SharedUtil.TOKEN, token);
                        }
                    }

                    @Override
                    public void onFailed(RxException exception) {
                        MyLogUtils.i("onLogin", exception.getMsg() + exception.getCode());
                    }
                });
    }

}
