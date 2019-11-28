package com.homelife.xhs.recyclerview.items;


import com.homelife.xhs.bean.Satin;
import com.homelife.xhs.recyclerview.ViewType;
import com.homelife.xhs.recyclerview.common.AdapterItem;

/**
 * Created by xue on 2019/2/21.
 */
public class SatinItem implements AdapterItem<Satin> {

    private Satin model;

    public SatinItem(Satin model) {
        this.model = model;
    }

    @Override
    public Satin getDataModel() {
        return model;
    }

    @Override
    public int getViewType() {
        return ViewType.TYPE_Satin;
    }
}
