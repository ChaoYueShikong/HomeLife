package com.homelife.xhs.biz.login;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.homelife.base.anno.AfterViews;
import com.homelife.base.anno.RootLayout;
import com.homelife.xhs.BaseBizActivity;
import com.homelife.xhs.R;
import com.homelife.xhs.utils.CommonUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 功能： 注册界面
 * 参数：
 * 作者 : HDM
 * 创建日期: 2019/9/27 15:28
 */

@RootLayout(R.layout.activity_register)
public class RegisterActivity extends BaseBizActivity {

    @BindView(R.id.iv_arrow)
    ImageView ivArrow;
    @BindView(R.id.rl_title_back)
    RelativeLayout rlTitleBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.rl_title)
    RelativeLayout rlTitle;
    @BindView(R.id.phone_tv)
    TextView phoneTv;
    @BindView(R.id.new_phone_et)
    EditText newPhoneEt;
    @BindView(R.id.new_phone_ll)
    RelativeLayout newPhoneLl;
    @BindView(R.id.yanzhengma)
    TextView yanzhengma;
    @BindView(R.id.verification_code_et)
    EditText verificationCodeEt;
    @BindView(R.id.get_verification_code_tv)
    TextView getVerificationCodeTv;
    @BindView(R.id.old_password_ll)
    RelativeLayout oldPasswordLl;
    @BindView(R.id.set_pwd)
    TextView setPwd;
    @BindView(R.id.new_password_et)
    EditText newPasswordEt;
    @BindView(R.id.close_eye_iv)
    ImageView closeEyeIv;
    @BindView(R.id.new_password_ll)
    RelativeLayout newPasswordLl;
    @BindView(R.id.save_btn)
    Button saveBtn;

    private CountDownTimer mCountDownTimer;

    @AfterViews
    void onCreate() {
        tvTitle.setText(R.string.register);
        initData();
    }


    @OnClick({R.id.rl_title_back})
    public void onViewClicked(){//返回
        finish();
    }

    //发送验证码倒计时
    private void initData() {
        mCountDownTimer = new CountDownTimer(1000 * 60, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                if (getVerificationCodeTv == null) return;
                getVerificationCodeTv.setEnabled(false);
                getVerificationCodeTv.setText((millisUntilFinished / 1000) + "s");
                getVerificationCodeTv.setTextColor(CommonUtils.getColor(R.color.themeColor));
            }

            @Override
            public void onFinish() {
                if (getVerificationCodeTv == null) return;
                getVerificationCodeTv.setEnabled(true);
                getVerificationCodeTv.setText("再次获取");
                getVerificationCodeTv.setTextColor(CommonUtils.getColor(R.color.themeColor));
            }
        };
    }


}
