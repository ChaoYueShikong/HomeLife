package com.homelife.xhs.biz;

import android.view.View;

import com.homelife.base.anno.AfterViews;
import com.homelife.base.anno.RootLayout;
import com.homelife.base.base.BaseFragment;
import com.homelife.util.SharedUtil;
import com.homelife.xhs.R;

import butterknife.BindView;


/**
 * @Description
 * @Author Xue
 * @CreateDate 2018/8/22
 */
@RootLayout(R.layout.act_splash)
public class DemoFragment extends BaseFragment {


    @BindView(R.id.view)
    View mView; //所有视图的处理


    @AfterViews
    void onCreate() {
        SharedUtil.getPreferStr(SharedUtil.UMENG_TOKEN);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}