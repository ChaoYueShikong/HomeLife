/*
 * Copyright (c) 2016 Corporation. All rights reserved.
 *
 */

package com.homelife.base.base;

import android.content.Context;

import com.homelife.base.mvp.IView;
import com.homelife.util.AppToast;
import com.homelife.util.HttpUtil;


/**
 * @Description 把通用的加载和弹出消息方式在这定义好（可扩展）
 * @Author Xue
 * @Version 1.0
 */
public class AbsFragment extends BKFragment implements IView {
    protected Context mContext = getActivity();

    @Override
    public void onDestroy() {
        super.onDestroy();
        close();
    }

    @Override
    public Context getContext() {
        return getActivity();
    }

    @Override
    public void showLoading() {
        HttpUtil.showLoading(mContext);
    }

    @Override
    public void hideLoading() {
        HttpUtil.hideLoading(mContext);
    }

    @Override
    public void showMsg(String msg) {
        AppToast.showToast(msg);
    }

    @Override
    public void showErrorMsg(String msg) {
        AppToast.showToast(msg);
    }

    @Override
    public void close() {
        this.mContext = null;
    }

}
