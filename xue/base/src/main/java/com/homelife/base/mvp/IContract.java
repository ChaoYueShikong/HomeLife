/*
 * Copyright (c) 2016 Corporation. All rights reserved.
 *
 */

package com.homelife.base.mvp;

/**
 * @Description 引入协议类Contract管理控制Iview和Ipresent
 * @Author Xue
 * @Version 1.0
 */
public interface IContract {
    /**
     * @Description
     * @Author Xue
     * @CreateDate
     */
    interface View extends IView {
    }

    /**
     * @Description
     * @Author Xue
     * @CreateDate
     */
    interface Presenter<V extends IContract.View> extends IPresenter<V> {
    }
}
