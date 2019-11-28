package com.homelife.base.mvp;

import android.content.Context;
import android.view.View;

import com.homelife.util.AppToast;
import com.homelife.util.HttpUtil;

import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * @Description view的基类, 集成Binder功能
 * @Author Xue
 * @CreateDate 2017/9/18
 */
public abstract class BaseView implements IView {

    protected Context mContext = null;

    protected View mView;//布局中的View

    protected Unbinder mUnbinder;

    @Override
    public View getView() {
        return mView;
    }

    /**
     * @Description 如果需要使用ButterKnife必须调用该方法
     * @Author Xue
     * @CreateDate
     */
    public void setView(View mView) {
        if (mView != null) {
            this.mView = mView;
            mUnbinder = ButterKnife.bind(this, mView);
        }
    }

    public void setContext(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public Context getContext() {
        return mContext;
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
        if (mUnbinder != null) {
            mUnbinder.unbind();
            mUnbinder = null;
        }
    }
}
