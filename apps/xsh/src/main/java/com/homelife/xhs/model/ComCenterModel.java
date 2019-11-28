package com.homelife.xhs.model;

import com.homelife.net.Feed;
import com.homelife.net.HttpRequest;
import com.homelife.net.utils.RxJavaUtil;
import com.homelife.xhs.api.SafedoorService;

import java.util.Map;

import io.reactivex.Single;

/**
 * @Description 公用数据中心
 * @Author Xue
 */
public class ComCenterModel {

    /**
     * @Description 获取所有已经发布公告信息
     * @Author Xue
     */
    public Single<Feed> getInfo(Map<String, String> map){
        return HttpRequest.create(SafedoorService.class)
                .getInfo(map)
                .compose(RxJavaUtil.getSingleMainThread()); //http请求在IO线程，observeOn  主线程
    }


}
