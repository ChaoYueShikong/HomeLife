package com.homelife.xhs.biz.login;

import android.widget.LinearLayout;

import com.homelife.base.anno.AfterViews;
import com.homelife.base.anno.RootLayout;
import com.homelife.base.base.EventClass;
import com.homelife.xhs.BaseBizActivity;
import com.homelife.xhs.R;
import com.homelife.xhs.biz.login.vpmc.LoginPresenter;
import com.homelife.xhs.biz.login.vpmc.LoginView;

import butterknife.BindView;

/**
 * 功能： 登录界面
 * 参数：
 * 作者 : HDM
 * 创建日期: 2019/9/27 15:48
 */
@RootLayout(R.layout.activity_login)
public class LoginActivity extends BaseBizActivity {


    @BindView(R.id.view)
    LinearLayout mLayout;
    private LoginPresenter mLoginPresenter;
    private LoginView mLoginView;

    @AfterViews
    void onCreate() {
        mLoginPresenter = new LoginPresenter(this);
        mLoginView = new LoginView(this, mLayout, mLoginPresenter);
        mLoginPresenter.attachView(mLoginView);
        mLoginPresenter.subscribe();
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (null != mLoginView) {
            mLoginView.start();
        }
    }

    @Override
    public void onEventOnUI(EventClass even) {
        super.onEventOnUI(even);
        //请求成功 可以在这个接收event发来的信息
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (null != mLoginView) {
            mLoginView.stop();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
