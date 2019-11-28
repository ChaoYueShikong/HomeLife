package com.homelife.xhs.recyclerview.common;

/**
 * Created by xue on 2019/2/21.
 */
public interface AdapterItem<T> {

    T getDataModel();

    int getViewType();
}
