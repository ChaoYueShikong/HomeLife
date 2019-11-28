/*
 * Copyright (c) 2016  Corporation. All rights reserved.
 *
 */

package com.homelife.base.base;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.homelife.util.Static;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @Description 改造使用butterknife框架
 * @Author Xue
 * @Version 1.0
 */
public class BKFragment extends Fragment {
    protected String TAG = "";

    protected LayoutInflater mInflater;

    protected View mRootView;

    protected Unbinder uNbinder;

//    protected View mErrorView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TAG = this.getClass().getSimpleName();
        mInflater = LayoutInflater.from(Static.CONTEXT);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        return new Annotation4View(this, mInflater) {
            @Override
            public View initContainer(int rootId) {
                mRootView = mLayoutInflater.inflate(rootId, container, false);
                uNbinder = ButterKnife.bind(BKFragment.this, mRootView);
                return mRootView;
            }
        }.prepareViews();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (uNbinder != null) {
            uNbinder.unbind();
            uNbinder = null;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

}
