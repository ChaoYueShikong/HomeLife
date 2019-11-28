/*
 * Copyright (c) 2016  Corporation. All rights reserved.
 *
 */

package com.homelife.xhs.biz.splash;

import android.content.Intent;
import android.view.View;

import com.homelife.base.anno.AfterViews;
import com.homelife.base.anno.RootLayout;
import com.homelife.base.base.BaseNoStatusActivity;
import com.homelife.xhs.R;
import com.homelife.util.StatusBarUtil;

import butterknife.BindView;

import static com.homelife.util.PermissionsUtil.SETTINGS_REQ_CODE;


/**
 * @Description 启动页
 * @Author Xue
 * @CreateDate
 */
@RootLayout(R.layout.act_splash)
public class SplashActivity extends BaseNoStatusActivity {

    @BindView(R.id.view)
    View mView;

    public SplashPresenter mPresenter;

    @AfterViews
    void onCreate() {
        if ((getIntent().getFlags() & Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT) != 0) {
            finish();
            return;
        }
        SplashView view = new SplashView(this, mView);
        mPresenter = new SplashPresenter(this);
        mPresenter.attachView(view);
        mPresenter.subscribe();

        StatusBarUtil.darkMode(this);

    }

    @Override
    public void startActivityForResult(Intent intent, int requestCode) {
        super.startActivityForResult(intent, requestCode);
        if (requestCode == SETTINGS_REQ_CODE) {
            mPresenter.permission();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}
