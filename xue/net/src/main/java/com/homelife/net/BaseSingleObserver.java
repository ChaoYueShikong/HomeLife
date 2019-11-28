package com.homelife.net;

import com.homelife.net.internal.BaseObserver;
import com.homelife.net.internal.RxErrorHandler;
import io.reactivex.SingleObserver;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

/**
 * @Description 
 * @Author Xue
 * @CreateDate
 */
public abstract class BaseSingleObserver<T> implements SingleObserver<T>, BaseObserver {

    public BaseSingleObserver() {
    }

    @Override
    public void onSubscribe(@NonNull Disposable d) {
        HttpRequest.addCall(d);
    }

    @Override
    public void onError(@NonNull Throwable e) {
        onError(RxErrorHandler.createRxException(e));
    }
}
