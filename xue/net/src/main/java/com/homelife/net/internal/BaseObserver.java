package com.homelife.net.internal;

import com.homelife.net.RxException;

/**
 * @Description 
 * @Author Xue
 * @CreateDate
 */
public interface BaseObserver {
    void onError(RxException e);
}
