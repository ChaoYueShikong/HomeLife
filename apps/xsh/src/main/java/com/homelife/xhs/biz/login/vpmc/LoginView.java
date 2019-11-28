package com.homelife.xhs.biz.login.vpmc;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.homelife.net.RxException;
import com.homelife.util.AppToast;
import com.homelife.util.DesUtil;
import com.homelife.util.MD5Util;
import com.homelife.util.SharedUtil;
import com.homelife.xhs.R;
import com.homelife.xhs.bean.LoginBean;
import com.homelife.xhs.bean.LoginResponse;
import com.homelife.xhs.biz.login.ForgetPwdActivity;
import com.homelife.xhs.biz.login.RegisterActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @Description 主页View的UI处理
 * @Author Xue
 * @CreateDate
 */
public class LoginView extends LoginContract.View {

    @BindView(R.id.phone_et)
    EditText mPhoneEt;
    @BindView(R.id.pwd_et)
    EditText mPwdEt;
    @BindView(R.id.btn_login)
    Button mBtnLogin;
    @BindView(R.id.yz_code_ll)
    LinearLayout mYzCodeLl;
    @BindView(R.id.forget_pwd_tv)
    TextView mForgetPwdTv;
    @BindView(R.id.fast_register_tv)
    TextView mFastRegistTv;
    @BindView(R.id.show_hide_iv)
    ImageView mShowHIv;
    private Context mContext;
    private LoginPresenter mPresenter;
    private boolean isChoose = false;//默认未选中 即隐藏密码


    public LoginView(Context mContext, View view, LoginPresenter presenter) {
        this.mContext = mContext;
        this.mPresenter = presenter;
        this.setView(view); //使用ButterKnife必须调用该方法
    }


    @Override
    public void initView() {

    }

    @OnClick({R.id.btn_login, R.id.yz_code_ll, R.id.fast_register_tv, R.id.show_hide_iv,R.id.forget_pwd_tv})
    public void onClick(View view) {
        Intent mIntent;
        switch (view.getId()) {

            case R.id.btn_login://登录
                if (TextUtils.isEmpty(mPhoneEt.getText().toString()) || mPhoneEt.getText().toString().length() != 11) {
                    AppToast.showToast("请输入正确手机号！");
                    return;
                }
                if (TextUtils.isEmpty(mPwdEt.getText().toString())) {
                    AppToast.showToast("请输入密码！");
                    return;
                }

                mPresenter.onLogin(mPhoneEt.getText().toString(), MD5Util.MD5(mPwdEt.getText().toString()));//TODO 这里要去js代码里查找加密的方式 并不是简单的md5加密
                break;
            case R.id.forget_pwd_tv://忘记密码
                mIntent = new Intent(mContext, ForgetPwdActivity.class);
                mContext.startActivity(mIntent);
                break;
            case R.id.fast_register_tv://快速注册
                mIntent = new Intent(mContext, RegisterActivity.class);
                mContext.startActivity(mIntent);
                break;
            case R.id.show_hide_iv://显示隐藏密码
                if (isChoose) {//显示
                    mPwdEt.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    mShowHIv.setImageResource(R.mipmap.ic_show_pwd);
                } else {//隐藏
                    mPwdEt.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    mShowHIv.setImageResource(R.mipmap.ic_hide_pwd);
                }
                isChoose = !isChoose;
                break;
        }
    }

    @Override
    public void start() {
    }

    @Override
    public void stop() {
    }

    @Override
    public void onLogin(LoginResponse loginBean) {

    }

    @Override
    public void getLoginError(RxException exception) {

    }


}
