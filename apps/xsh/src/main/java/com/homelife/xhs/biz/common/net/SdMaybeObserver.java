package com.homelife.xhs.biz.common.net;

import android.content.Context;

import com.homelife.net.BaseMaybeObserver;
import com.homelife.net.RxException;
import com.homelife.util.HttpUtil;

import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

/**
 * @Description
 * @Author Xue
 * @CreateDate
 */
public abstract class SdMaybeObserver<T> extends BaseMaybeObserver<T> implements SdObserver<T> {
    private Context mContext;

    public SdMaybeObserver() {

    }

    public SdMaybeObserver(Context context) { //需要使用加载dialog 的时候，使用这个
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
        HttpUtil.hideLoading(mContext);
        onFailed(e);
    }
}
