package com.homelife.xhs;

import android.content.Context;

import com.homelife.base.mvp.ModelManager;
import com.homelife.net.Feed;
import com.homelife.net.RxException;
import com.homelife.net.utils.RxJavaUtil;
import com.homelife.util.SharedUtil;
import com.homelife.util.Static;
import com.homelife.util.StringUtil;
import com.homelife.xhs.biz.common.net.SdSingleObserver;
import com.homelife.xhs.model.ComCenterModel;

import java.util.HashMap;

/**
 * 饿汉单例
 * @Description 公用数据中心
 * @Author Xue
 */
public enum ComDataCenter {

    INSTANCE;
    private ComCenterModel mModel = ModelManager.getModelInstance(ComCenterModel.class);
    private Context mContext = Static.CONTEXT;


    /**
     * @Author Xue
     */
    public void getInfo() {
        String token = SharedUtil.getPreferStr(SharedUtil.UMENG_TOKEN);
        if (StringUtil.isBlank(token)) {
            return;
        }
        HashMap<String, String> map = new HashMap<>();
        map.put("msgId", String.valueOf(System.currentTimeMillis()));
        mModel.getInfo(map)
                .compose(RxJavaUtil.getSingleFeedTransformer())//预处理server返回的结果，将Feed中的data返回;
                .subscribe(new SdSingleObserver<Feed>(mContext) {

                    @Override
                    public void onSucceed(Feed feed) {
                    }

                    @Override
                    public void onFailed(RxException exception) {
                    }
                });
    }
}
