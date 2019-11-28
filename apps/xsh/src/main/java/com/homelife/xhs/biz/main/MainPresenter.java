package com.homelife.xhs.biz.main;

import android.content.Context;

import com.homelife.net.RxException;
import com.homelife.xhs.bean.Satin;

import java.util.List;

/**
 * @Description 主页P
 * @Author Xue
 * @CreateDate
 */
public class MainPresenter<V extends MainContract.View> extends MainContract.Presenter {

    private Context mContext;
    private MainDataRepository mDataCenter; //数据中心

    public MainPresenter(Context mContext) {
        this.mContext = mContext;
        mDataCenter = new MainDataRepository(this);
    }

    @Override
    public void subscribe() { //入口
        mMvpView.initView();
    }

    @Override
    public void onResume() {
    }

    public void onSuccessed(List<Satin> list) {
        mMvpView.setSatin(list);
    }


    public void onFailed(RxException exception) {
        mMvpView.getSatinError(exception);
    }

    /**请求
     * @Description
     * @Author Xue
     */
    public void getSatin(int page) {
        mDataCenter.getSatin(page);
    }




}
