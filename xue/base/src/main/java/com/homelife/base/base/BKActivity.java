/*
 * Copyright (c) 2016 Corporation. All rights reserved.
 *
 */

package com.homelife.base.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @Description 改造使用butterknife框架
 * @Author Xue
 * @Version 1.0
 */
public class BKActivity extends RpActivity {
    protected String TAG = "";

    protected LayoutInflater mInflater;

    protected Unbinder mUnbinder;
    protected View mRootView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TAG = this.getClass().getSimpleName();
        mInflater = LayoutInflater.from(this);
        new Annotation4View(this, mInflater) {
            @Override
            public View initContainer(int rootId) {
                mRootView = mLayoutInflater.inflate(rootId, null);
                setContentView(mRootView);
                mUnbinder = ButterKnife.bind(BKActivity.this);
                return mRootView;
            }
        }.processViews();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mUnbinder != null) {
            mUnbinder.unbind();
            mUnbinder = null;
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }
}
