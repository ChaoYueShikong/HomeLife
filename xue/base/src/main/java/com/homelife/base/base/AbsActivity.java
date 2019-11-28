/*
 * Copyright (c) 2016 Corporation. All rights reserved.
 *
 */

package com.homelife.base.base;

import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;

import com.homelife.base.mvp.IView;
import com.homelife.util.AppToast;
import com.homelife.util.HttpUtil;

import java.util.ArrayList;
import java.util.List;


/**
 * @Description Activity实现View的工作
 * @Author Xue
 * @Version 1.0
 */
public abstract class AbsActivity extends BKActivity implements IView {

    protected Context mContext = this;
    protected PermissionListener mListener;
    protected static final int PERMISSION_REQUESTCODE = 100;

    public void requestRunPermisssion(String[] permissions, PermissionListener listener) {
        mListener = listener;
        List<String> permissionLists = new ArrayList<>();
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
                permissionLists.add(permission);
            }
        }
        if (!permissionLists.isEmpty()) {
            ActivityCompat.requestPermissions(this, permissionLists.toArray(new String[permissionLists.size()]), PERMISSION_REQUESTCODE);
        } else {
            mListener.onGranted(); //全都授权了
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case PERMISSION_REQUESTCODE:
                if (grantResults.length > 0) { //存放没授权的权限
                    List<String> deniedPermissions = new ArrayList<>();
                    for (int i = 0; i < grantResults.length; i++) {
                        int grantResult = grantResults[i];
                        String permission = permissions[i];
                        if (grantResult != PackageManager.PERMISSION_GRANTED) {
                            deniedPermissions.add(permission);
                        }
                    }
                    if (deniedPermissions.isEmpty()) { //全部授权了
                        mListener.onGranted();
                    } else {
                        mListener.onDenied(deniedPermissions);
                    }
                }
                break;
            default:
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        close();
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public View getView() {
        return mRootView;
    }

    @Override
    public void showLoading() {
        HttpUtil.showLoading(mContext);
    }

    @Override
    public void hideLoading() {
        HttpUtil.hideLoading(mContext);
    }

    @Override
    public void showMsg(String msg) {
        AppToast.showToast(msg);
    }

    @Override
    public void showErrorMsg(String msg) {
        AppToast.showToast(msg);
    }

    @Override
    public void close() {
        this.mContext = null;
    }
}
