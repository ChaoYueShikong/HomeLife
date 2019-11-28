/*
 * Copyright (c) 2016 QXWZ Corporation. All rights reserved.
 *
 */

package com.homelife.xhs.biz.splash;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;

import com.homelife.base.base.BaseNoStatusActivity;
import com.homelife.base.base.PermissionListener;
import com.homelife.util.AppToast;
import com.homelife.util.DialogListener2;
import com.homelife.util.DialogUtil;
import com.homelife.util.IntentUtil;
import com.homelife.util.ProcessUtil;
import com.homelife.xhs.biz.login.LoginActivity;
import com.homelife.xhs.biz.main.MainActivity;

import java.util.List;

import static com.homelife.util.PermissionsUtil.SETTINGS_REQ_CODE;
import static com.homelife.util.SharedUtil.getPackageName;


/**
 * @Description P层
 * @Author Xue
 * @CreateDate
 */
public class SplashPresenter<V extends SplashContract.View> extends SplashContract.Presenter {

    private Context mContext;
    private static final String[] REQUIRED_PERMISSIONS = {
            Manifest.permission.ACCESS_WIFI_STATE,
            Manifest.permission.ACCESS_NETWORK_STATE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_PHONE_STATE};

    public SplashPresenter(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public void subscribe() { //入口
        permission();
//        toMain();
    }

    @Override
    public void toMain() {
        int time = 0;
        Intent intent = new Intent();
        intent.setClassName("com.sys.hou.biz.main", "LoginActivity");
        if (mContext.getPackageManager().resolveActivity(intent, 0) == null) {
//            Toast.makeText(mContext, "mainActivity不存在", Toast.LENGTH_SHORT).show();
            time = 1000;
        }
        new Handler().postDelayed(() -> {
            IntentUtil.startActivity(mContext, LoginActivity.class);
            ((Activity) mContext).finish();
        }, time);
    }

    /**
     * @Description 权限申请
     * @Author Xue
     * @CreateDate
     */
    public void permission() {
        ((BaseNoStatusActivity) mContext).requestRunPermisssion(REQUIRED_PERMISSIONS, new PermissionListener() {
            @Override
            public void onGranted() {
                //AppToast.showToast("动态获取了全部权限！");
                toMain();
            }

            @Override
            public void onDenied(List<String> deniedPermission) {
                boolean flag = true;
                for (String permission : deniedPermission) {
                    AppToast.showToast("请开通权限：" + permission);
                    flag = false;
                }
                if (flag) {
                    toMain();
                } else {
                    DialogUtil.dialog("该权限必须设置!", "权限提示", "设置权限", "退出应用", mContext, new DialogListener2() {
                        @Override
                        public void confirm() {//Settings.ACTION_APPLICATION_DETAIL_SETTING
                            Intent localIntent = new Intent();
                            localIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            if (Build.VERSION.SDK_INT >= 9) {
                                localIntent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
                                localIntent.setData(Uri.fromParts("package", getPackageName(), null));
                            } else if (Build.VERSION.SDK_INT <= 8) {
                                localIntent.setAction(Intent.ACTION_VIEW);
                                localIntent.setClassName("com.android.settings","com.android.settings.InstalledAppDetails");
                                localIntent.putExtra("com.android.settings.ApplicationPkgName", getPackageName());
                            }
                            ((SplashActivity) mContext).startActivityForResult(localIntent, SETTINGS_REQ_CODE);
                        }
                        @Override
                        public void cancel() {
                            ((SplashActivity) mContext).finish();
                            ProcessUtil.killProcess();
                        }
                    });
                }
            }
        });
    }

}
