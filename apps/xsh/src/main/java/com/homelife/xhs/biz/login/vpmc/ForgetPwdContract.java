package com.homelife.xhs.biz.login.vpmc;


import com.homelife.base.mvp.BasePresenter;
import com.homelife.base.mvp.BaseView;
import com.homelife.base.mvp.IContract;
import com.homelife.net.RxException;
import com.homelife.xhs.bean.LoginResponse;

/**
 * @Description 契约类
 * @Author Xue
 * @CreateDate
 */
public interface ForgetPwdContract extends IContract {
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
        public abstract void getYzCode(LoginResponse loginBean);
        public abstract void getLoginError(RxException exception);
    }
}
