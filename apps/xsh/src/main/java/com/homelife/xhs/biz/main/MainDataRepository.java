package com.homelife.xhs.biz.main;


import android.content.Context;
import android.util.Log;

import com.homelife.base.mvp.ModelManager;
import com.homelife.net.RxException;
import com.homelife.net.utils.RxJavaUtil;
import com.homelife.util.AppToast;
import com.homelife.xhs.bean.ImgInfo;
import com.homelife.xhs.bean.Satin;
import com.homelife.xhs.biz.common.net.SdSingleObserver;
import com.homelife.xhs.model.MainModel;

import java.util.HashMap;
import java.util.List;

/**
 * @Description 数据仓库（从Model中获取网络&缓存&数据库中数据）
 * @Author Xue
 * @CreateDate
 */
public class MainDataRepository {

    private MainModel mModel = ModelManager.getModelInstance(MainModel.class);
    private MainPresenter mPresenter;
    private Context mContext;

    public MainDataRepository(MainPresenter presenter) {
        this.mPresenter = presenter;
        this.mContext = mContext;
    }


    /**
     * satinApi?type=1&page=2
     * @Author Xue
     */
    public void getSatin(int page) {
        HashMap<String, String> map = new HashMap<>();
        map.put("type", 1+"");
        map.put("page", page+"");
        mModel.getSatin(map)
                .compose(RxJavaUtil.getSingleFeedTransformer())//预处理server返回的结果，将Feed中的data返回;
                .subscribe(new SdSingleObserver<List<Satin>>(mContext) {
                    @Override
                    public void onSucceed(List<Satin> mlist) {
                        mPresenter.onSuccessed(mlist);
                    }

                    @Override
                    public void onFailed(RxException exception) {
                        mPresenter.onFailed(exception);
                        AppToast.showToast(exception.getMsg());
                    }
                });
    }


    /**
     * 测试网络框架集成
     */
    public void getBgImg() {
        HashMap<String, String> map = new HashMap<>();
        map.put("service","Master.GetLoginBg");
        mModel.getBgImg(map)
                .compose(RxJavaUtil.getSingleFeedTransformer())//预处理server返回的结果，将Feed中的data返回;
                .subscribe(new SdSingleObserver<ImgInfo>(mContext) {
                    @Override
                    public void onSucceed(ImgInfo imgInfo) {

                        String s = imgInfo.getLists().get(0);
                        Log.i("fdfffdeeee", s);

//                        EventBus.getDefault().post(new EventDownImg("houseInfo",list));
                    }

                    @Override
                    public void onFailed(RxException exception) {
                        AppToast.showToast(exception.getMsg());
                    }
                });
    }


}
