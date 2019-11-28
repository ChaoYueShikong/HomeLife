/*
 * Copyright (c) 2016 Corporation. All rights reserved.
 *
 */

package com.homelife.base.mvp;

/**
 * @Description MVP的P层
 * @Author Xue
 * @Version 1.0
 */
public interface IPresenter<V extends IView> {

    /**
     * @description 关联P与V（绑定，VIEW销毁适合解绑）
     * @author Xue
     * @createDate
     * @version 1.0
     */
    void attachView(V view);

    /**
     * @description 取消关联P与V（防止内存泄漏）
     * @author Xue
     * @createDate
     * @version 1.0
     */
    void detachView();

    /**
     * @description RX订阅, 程序入口
     * @author Xue
     * @createDate
     * @version 1.0
     */
    void subscribe();

    /**
     * @description RX取消订阅
     * @author Xue
     * @createDate
     * @version 1.0
     */
    void unsubscribe();

}
