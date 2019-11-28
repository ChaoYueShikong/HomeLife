package com.homelife.xhs.biz.common.net;

import android.content.Context;

import com.homelife.net.BaseSingleObserver;
import com.homelife.net.RxException;
import com.homelife.util.AppToast;
import com.homelife.util.HttpUtil;

import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;


/**
 * @Description
 * @Author Xue
 * @CreateDate
 */
public abstract class SdSingleObserver<T> extends BaseSingleObserver<T> implements SdObserver<T> {
    private Context mContext;

    public SdSingleObserver() {

    }

    public SdSingleObserver(Context context) { //需要使用加载dialog 的时候，使用这个
        mContext = context;
    }

    @Override
    public void onSubscribe(@NonNull Disposable d) {
        super.onSubscribe(d);
        HttpUtil.showLoading(mContext);
    }

    @Override
    public void onSuccess(@NonNull T t) {
        HttpUtil.hideLoading(mContext);
        onSucceed(t);
    }

    @Override
    public void onError(RxException e) {
        int code = e.getCode();
        HttpUtil.hideLoading(mContext);
        AppToast.showToast(e.getMsg());
        onFailed(e);
    }
}
