/*
 * Copyright (c) 2016 Corporation. All rights reserved.
 *
 */

package com.homelife.base.mvp;


import com.homelife.util.ObjectUtil;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @Description 抽象的公用Presenter
 * @Author Xue
 * @Version 1.0
 */
public abstract class BasePresenter<V extends IView> implements IPresenter<V> {
    protected V mMvpView;//所有View
    protected Unbinder mUnbinder;

    /**
     * @description 获取V
     * @author Xue
     * @createDate
     * @version 1.0
     */
    public V getMvpView() {
        return mMvpView;
    }

    /**
     * @description view绑定P的时候初始化
     * @author Xue
     * @createDate
     * @version 1.0
     */
    @Override
    public void attachView(V view) {
        this.mMvpView = view;
        mUnbinder = ButterKnife.bind(this, view.getView());
    }

    /**
     * @description view失去绑定清除
     * @author Xue
     * @createDate
     * @version 1.0
     */
    @Override
    public void detachView() {
        unsubscribe();
        if (ObjectUtil.nonNull(mUnbinder)) {
            mUnbinder.unbind();
            mUnbinder = null;
        }
        this.mMvpView = null;
    }

    @Override
    public void unsubscribe() {
    }

    /**
     * @description 当前的view（fragemnt&activity是否存在）
     * @author Xue
     * @createDate
     * @version 1.0
     */
    public boolean isViewAttached() {
        return mMvpView != null;
    }

    /**
     * @description 是否viewb绑定过P
     * @author Xue
     * @createDate
     * @version 1.0
     */
    public void checkViewAttached() {
        if (!isViewAttached()) {
            throw new MvpViewNotAttachedException();
        }
    }

    /**
     * @author Xue
     * @version 1.0
     * @description p&v没有绑定的异常
     * @createDate
     */
    public static class MvpViewNotAttachedException extends RuntimeException {
        public MvpViewNotAttachedException() {
            super("Please call Presenter.attachView(MvpView) before requesting data to the Presenter");
        }
    }

}
