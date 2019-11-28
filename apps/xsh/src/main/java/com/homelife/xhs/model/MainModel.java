package com.homelife.xhs.model;

import com.homelife.net.Feed;
import com.homelife.net.HttpRequest;
import com.homelife.net.utils.RxJavaUtil;
import com.homelife.util.HttpUtil;
import com.homelife.xhs.api.SafedoorService;
import com.homelife.xhs.bean.ImgInfo;
import com.homelife.xhs.bean.Satin;
import com.homelife.xhs.bean.SysUserInfo;
import com.homelife.xhs.bean.SysUserResponse;

import java.util.List;
import java.util.Map;

import io.reactivex.Single;

/**
 * @Description
 * @Author Xue
 * @CreateDate
 */
public class MainModel {

    /**
     * @Description 测试
     * @Author Xue
     */
    public Single<Feed<ImgInfo>> getBgImg(Map<String, String> map){
        return HttpRequest.create(SafedoorService.class)
                .getBgImg(map)
                .compose(RxJavaUtil.getSingleMainThread()); //http请求在IO线程，observeOn  主线程
    }


    /**
     * @Description 获取新闻信息
     * @Author Xue
     * @CreateDate
     */
    public Single<Feed<List<Satin>>> getSatin(Map<String, String> map) {
        return HttpRequest.create(SafedoorService.class)
                .getSatin(map)
                .compose(RxJavaUtil.getSingleMainThread()); //http请求在IO线程，observeOn  主线程
    }

    public Single<SysUserResponse> obtainSysUser(SysUserInfo sysUserInfo){
        return HttpRequest.create(SafedoorService.class)
                .obtainSysnInfo(sysUserInfo.getAccountid(),sysUserInfo.getAuthtoken()
                ,sysUserInfo.getVer())
                .compose(RxJavaUtil.getSingleResponseTransformer());

    }
}
