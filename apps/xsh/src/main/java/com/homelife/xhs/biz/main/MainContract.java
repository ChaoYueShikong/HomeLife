package com.homelife.xhs.biz.main;


import com.homelife.base.mvp.BasePresenter;
import com.homelife.base.mvp.BaseView;
import com.homelife.base.mvp.IContract;
import com.homelife.net.RxException;
import com.homelife.xhs.bean.Satin;

import java.util.List;

/**
 * @Description 契约类
 * @Author Xue
 * @CreateDate
 */
public interface MainContract extends IContract {
    abstract class Presenter extends BasePresenter<View> {
        public abstract void onResume();
    }

    abstract class View extends BaseView {
        /**
         * @Description 初始化TAB
         * @Author Xue
         * @CreateDate
         */
        public abstract void initView();
        public abstract void start();
        public abstract void stop();
        public abstract void setSatin(List<Satin> list);
        public abstract void getSatinError(RxException exception);
    }
}
