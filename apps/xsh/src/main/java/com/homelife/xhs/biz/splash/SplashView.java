package com.homelife.xhs.biz.splash;

import android.content.Context;
import android.view.View;

/**
 * @Description 所有实现IView的类都是我们的MVP中的View
 * @Author Xue
 * @CreateDate
 */
public class SplashView extends SplashContract.View {

    private Context mContext;
    private View mView;

    public SplashView(Context mContext, View view) {
        this.mContext = mContext;
        this.setView(view); //使用ButterKnife必须调用该方法
    }

}
