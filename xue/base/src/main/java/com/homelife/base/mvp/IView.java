/*
 * Copyright (c) 2016 Corporation. All rights reserved.
 *
 */

package com.homelife.base.mvp;

import android.content.Context;
import android.view.View;

/**
 * @Description MVP改造之V层 是所有VIEW的基类，其他类可以继承该类
 * @Author Xue
 * @Version 1.0
 */
public interface IView {
    /**
     * @Description
     * @Author Xue
     * @CreateDate
     */
    Context getContext();

    View getView();

    /**
     * @description 显示加载框
     * @author Xue
     * @createDate
     * @version 1.0
     */
    void showLoading();

    /**
     * @description 隐藏加载框
     * @author Xue
     * @createDate
     * @version 1.0
     */
    void hideLoading();

    /**
     * @description 全局消息展示
     * @author Xue
     * @createDate
     * @version 1.0
     */
    void showMsg(String msg);

    /**
     * @description 全局错误消息展示
     * @author Xue
     * @createDate
     * @version 1.0
     */
    void showErrorMsg(String msg);

    /**
     * @description 关闭View
     * @author Xue
     * @createDate
     * @version 1.0
     */
    void close();

}