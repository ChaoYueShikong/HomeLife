/*
 * Copyright (c) 2016  Corporation. All rights reserved.
 *
 */

package com.homelife.xhs.biz.splash;


import com.homelife.base.mvp.BasePresenter;
import com.homelife.base.mvp.BaseView;
import com.homelife.base.mvp.IContract;

/**
 * @Description 启动页面协议类（控制Mode,View,Presenter）
 * @Author Xue
 * @CreateDate
 */
public interface SplashContract extends IContract {
    /**
     * @author Xue
     * @version 1.0
     * @description Presenter处理的业务逻辑都在这里定义
     * @createDate
     */
    public abstract class Presenter extends BasePresenter<View> {
        public abstract void toMain();
    }

    /**
     * @author Xue
     * @version 1.0
     * @description 如何需要可以写更新UI的接口
     * @createDate
     */
    public abstract class View extends BaseView {
    }
}
