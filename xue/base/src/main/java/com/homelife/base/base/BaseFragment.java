/*
 * Copyright (c) 2016 Corporation. All rights reserved.
 *
 */

package com.homelife.base.base;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * @Description 所有Fragment父类, 集成EventBus功能
 * @Author Xue
 * @CreateDate 2017/6/15
 */
public class BaseFragment extends AbsFragment {
    private FragmentManager FRG_MANAGER; // Xue  去掉了static  否则到其他的Activity就会把当前Ac的fragmentmanager替换掉

    public BaseFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
        FRG_MANAGER = getActivity().getSupportFragmentManager();
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventOnUI(EventClass even) {

    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void onEventOnBack(EventClass even) {

    }

    @Subscribe(threadMode = ThreadMode.ASYNC)
    public void onEventOnAsync(EventClass even) {

    }

    @Subscribe(threadMode = ThreadMode.POSTING)
    public void onEventOnPost(EventClass even) {

    }
}
