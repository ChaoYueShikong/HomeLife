package com.homelife.xhs.biz.login.vpmc;

import android.content.Context;

import com.homelife.net.RxException;
import com.homelife.xhs.bean.LoginBean;
import com.homelife.xhs.bean.LoginResponse;

/**
 * @Description 主页P
 * @Author Xue
 * @CreateDate
 */
public class LoginPresenter<V extends LoginContract.View> extends LoginContract.Presenter {

    private Context mContext;
    private LoginDataRepository mDataCenter; //数据中心

    public LoginPresenter(Context mContext) {
        this.mContext = mContext;
        mDataCenter = new LoginDataRepository(this);
    }

    @Override
    public void subscribe() { //入口
        mMvpView.initView();
    }

    @Override
    public void onResume() {
    }

    public void onSuccessed(LoginResponse loginBean) {
        mMvpView.onLogin(loginBean);
    }


    public void onFailed(RxException exception) {
        mMvpView.getLoginError(exception);
    }

    /**请求
     * @Description
     * @Author Xue
     */
    public void onLogin(String phone,String password) {
        mDataCenter.onLogin(phone,password);
    }

}
