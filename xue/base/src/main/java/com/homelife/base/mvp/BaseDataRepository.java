package com.homelife.base.mvp;

import io.reactivex.Single;
import io.reactivex.disposables.Disposable;

/**
 * @Description
 * @Author Xue
 * @CreateDate 2017/7/9
 */
public class BaseDataRepository {

    public <T> Disposable subscribe(Single<T> mSingle) {
        return mSingle.subscribe();
    }

}
