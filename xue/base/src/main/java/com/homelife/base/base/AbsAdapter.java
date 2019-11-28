/*
 * Copyright (c) 2016 Corporation. All rights reserved.
 *
 */
package com.homelife.base.base;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * @Description listview&gridview的通用适配器,所有Adapter继承该adapter
 * @Author Xue
 * @CreateDate
 */
public abstract class AbsAdapter<T> extends BaseAdapter {
    protected Context mContext;

    protected abstract View getViews(int position, View convertView, ViewGroup parent);

    protected abstract void itemOnClick(View v, int position);

    protected List<T> dataList;

    public void setIndex(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }

    protected int index = 1; //点击第几个标签

    public AbsAdapter(Context mContext, List<T> dataList) {
        super();
        this.mContext = mContext;
        this.dataList = dataList;
    }

    public void setData(List<T> list) {
        this.dataList.clear();
        this.dataList = list;
    }

    @Override
    public int getCount() {
        return dataList == null ? 0 : dataList.size();
    }

    @Override
    public Object getItem(int position) {
        return dataList == null ? null : dataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = getViews(position, convertView, parent);
        convertView.setOnClickListener(new ItemOnclicListen(position)); //跟swipelistview点击控件有冲突，如果用SwipeMenuListView，需要注销这个用list.onitem事件
        return convertView;
    }

    /**
     * @author Xue
     * @version 1.0
     * @description Item点击事件
     */
    public class ItemOnclicListen implements OnClickListener {
        private int position = 0;

        public ItemOnclicListen(int position) {
            this.position = position;
        }

        @Override
        public void onClick(View v) {
            itemOnClick(v, position);
        }

    }

}
