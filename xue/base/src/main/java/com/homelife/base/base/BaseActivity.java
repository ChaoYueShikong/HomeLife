/*
 * Copyright (c) 2016  Corporation. All rights reserved.
 *
 */

package com.homelife.base.base;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;

import com.homelife.base.R;
import com.homelife.util.AppToast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

//import com.xue.util.SystemStatusManager;
//import com.umeng.analytics.MobclickAgent;


/**
 * @Description 所有Activity父类，集成EventBus功能
 * @Author Xue
 * @CreateDate
 */
public class BaseActivity extends AbsActivity {

    public Activity mActivity;
    private long mExitTime;
    private boolean isExit = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        mActivity = this;
//        setTranslucentStatus();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventOnUI(EventClass even) { //子类继承

    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void onEventOnBack(EventClass even) { //子类继承

    }

    @Subscribe(threadMode = ThreadMode.ASYNC)
    public void onEventOnAsync(EventClass even) { //子类继承

    }

    @Subscribe(threadMode = ThreadMode.POSTING)
    public void onEventOnPost(EventClass even) { //子类继承

    }

    /**
     * @Description 状态栏一体化
     * @Author Xue
     * @CreateDate
     */
    public void setTranslucentStatus() {
        setTranslucentStatus(R.color.black);
    }

    /**
     * @Description 状态栏一体化自定义颜色，默认黑色
     * @Author Xue
     * @CreateDate
     */
    public void setTranslucentStatus(int color) {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS); // 透明状态栏
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION); // 透明导航栏
//            SystemStatusManager tintManager = new SystemStatusManager(this);
//            tintManager.setStatusBarTintEnabled(true);
//            tintManager.setStatusBarTintResource(color); //设置状态栏的颜色
//            getWindow().getDecorView().setFitsSystemWindows(true);
//        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (isExit) {
                if ((System.currentTimeMillis() - mExitTime) > 2000) {//再按一次退出程序
                    AppToast.showToast("再按一次退出程序");
                    mExitTime = System.currentTimeMillis();
                } else {
                    finish();
                }
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    protected void setExit(boolean isExit) {
        this.isExit = isExit;
    }
}
