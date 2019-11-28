package com.homelife.xhs.biz.common.net;


import com.homelife.net.RxException;

/**
 * @Description
 * @Author Xue
 * @CreateDate
 */
interface SdObserver<T> {
    void onSucceed(T t);

    void onFailed(RxException exception);
}
