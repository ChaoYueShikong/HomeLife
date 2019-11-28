/*
 * Copyright (c) 2016 Corporation. All rights reserved.
 *
 */

package com.homelife.base.base;

import android.app.Activity;
import android.os.Bundle;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;


/**
 * @Description 所有Activity父类，集成EventBus功能
 * @Author Xue
 * @CreateDate
 */
public class BaseNoStatusActivity extends AbsActivity {

    public Activity mActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        mActivity = this;
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

}
