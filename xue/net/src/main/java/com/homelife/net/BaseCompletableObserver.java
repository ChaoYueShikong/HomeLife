package com.homelife.net;

import com.homelife.net.internal.BaseObserver;
import com.homelife.net.internal.RxErrorHandler;
import io.reactivex.CompletableObserver;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

/**
 * @Description 
 * @Author Xue
 * @CreateDate
 */
public abstract class BaseCompletableObserver implements CompletableObserver, BaseObserver {
    @Override
    public void onSubscribe(@NonNull Disposable d) {
        HttpRequest.addCall(d);
    }

    @Override
    public void onError(@NonNull Throwable e) {
        onError(RxErrorHandler.createRxException(e));
    }
}
