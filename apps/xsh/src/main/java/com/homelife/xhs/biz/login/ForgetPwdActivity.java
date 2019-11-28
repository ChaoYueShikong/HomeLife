package com.homelife.xhs.biz.login;

import android.widget.LinearLayout;

import com.homelife.base.anno.AfterViews;
import com.homelife.base.anno.RootLayout;
import com.homelife.xhs.BaseBizActivity;
import com.homelife.xhs.R;

import butterknife.BindView;

/**
 * 功能： 忘记密码
 * 参数：
 * 作者 : HDM
 * 创建日期: 2019/9/29 15:15
 */
@RootLayout(R.layout.activity_forget_pwd)
public class ForgetPwdActivity extends BaseBizActivity {

    @BindView(R.id.view)
    LinearLayout mLayout;

    @AfterViews
    void onCreate() {

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
