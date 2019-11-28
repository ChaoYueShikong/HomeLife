/*
 * Copyright (c) 2016 Corporation. All rights reserved.
 *
 */
package com.homelife.base.base;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;


/**
 * @Description 待扩展集成奔溃日志拦截
 * @Author Xue
 * @CreateDate
 */
public class RpActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
//        final View viewRoot = getWindow().getDecorView().findViewById(android.R.id.content); //系统根View隐藏键盘
//        viewRoot.setOnClickListener(new View.OnClickListener() { //隐藏键盘
//            @Override
//            public void onClick(View v) { //关闭键盘
//                SoftInputUtil.hideShow(viewRoot);
//            }
//        });
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

}
