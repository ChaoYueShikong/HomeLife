/*
 * Copyright (c) 2016  Corporation. All rights reserved.
 *
 */
package com.homelife.base.base;

import android.Manifest;
import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.WindowManager;

import com.homelife.util.AppToast;
import com.homelife.util.AppTools;
import com.homelife.util.PathUtil;
import com.homelife.util.ProcessUtil;
import com.homelife.util.SdCardUtil;
import com.homelife.util.SharedUtil;
import com.homelife.util.Static;
import com.homelife.util.StringUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;


/**
 * @author Xue
 * @version 1.0
 * @description 初始化APK初始值
 */
public class AApplication extends Application { //MultiDexApplication
    public static final int SIZE = 1024;

    @Override
    public void onCreate() {
        super.onCreate();
        Static.CONTEXT = this;
        Static.INFLATER = LayoutInflater.from(this);
        //ExceptionHandler.getInstance(this).setIsNeedShowViewer(AppLog.DEBUG);//Xue 异常日志提示
        if (ProcessUtil.isCurMainProcess()) {
            initSd();
            init();
        }
        EventBus.getDefault().register(this);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        EventBus.getDefault().unregister(this);
    }

    /**
     * @Description 初始化通用信息
     * @Author Xue
     * @CreateDate
     */
    private void init() {
        Display display = ((WindowManager) getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
        DisplayMetrics dm = new DisplayMetrics();
        display.getMetrics(dm);
        SharedUtil.setPreferInt(this, SharedUtil.SWIDTH, dm.widthPixels);
        SharedUtil.setPreferInt(this, SharedUtil.SHEIGHT, dm.heightPixels);
        SharedUtil.setPreferStr(this, SharedUtil.DPI_FLOAT, String.valueOf(dm.density)); // 屏幕密度（像素比例：0.75/1.0/1.5/2.0）
        SharedUtil.setPreferInt(this, SharedUtil.DPI_INT, dm.densityDpi); // 屏幕密度DPI（120 / 160 / 240）
        SharedUtil.setPreferInt(this, SharedUtil.SDK_VERSION, Build.VERSION.SDK_INT); // sdk版本
        SharedUtil.setPreferStr(this, SharedUtil.VERSION_NAME, AppTools.getAPPVersion(this)); // 版本名称， android:versionName="1.0.0.12"
        SharedUtil.setPreferInt(this, SharedUtil.VERSION_NO, AppTools.getAPPVersionCode(this)); // 版本号，android:versionCode="11"
        SharedUtil.setPreferStr(this, SharedUtil.PACKAGE_NAME, AppTools.getAPPPackageName(this)); // 包名称，package="com.ms.ui.activity"
        final int memoryClass = ((ActivityManager) getSystemService(Context.ACTIVITY_SERVICE)).getMemoryClass();
        SharedUtil.setPreferInt(this, SharedUtil.MEMORY_SIZE, (memoryClass * SIZE * SIZE / 8)); // 返回内存限制
        final int maxMemory = (int) (Runtime.getRuntime().maxMemory()); // 应用程序最高可用内存是 8M,16M,32M (/ 1024)KB
        SharedUtil.setPreferInt(this, SharedUtil.MEMORY_MAX, maxMemory); // 应用程序最高可用内存是 8,16,32 KB
        TelephonyManager tm = (TelephonyManager) this.getSystemService(Context.TELEPHONY_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        if (StringUtil.isNotBlank(tm.getDeviceId())) {
            SharedUtil.setPreferStr(this, SharedUtil.IMEI, tm.getDeviceId()); // IMEI号
        } else {
            SharedUtil.setPreferStr(this, SharedUtil.IMEI, "1234567890"); // IMEI号如果没有就返回默认1234567890
        }
    }

    /**
     * @description 判断否有SD卡设备在启动时建立根文件目录
     * @author Xue
     */
    public void initSd() {
        if (SdCardUtil.isSDCardEnable()) { // SD卡已挂载并且可以访问
            File sdcard = Environment.getExternalStorageDirectory();
            if (sdcard.exists()) { // SD卡是否可用
                File rootFile = new File(PathUtil.PATH_ROOT); // /<package name>/
                if (!rootFile.exists()) {
                    rootFile.mkdirs();
                }
            } else {
                AppToast.showToast("SD卡不能使用");
            }
        } else {
            File rootFile = new File(PathUtil.PATH_ROOT_NOSD); // /data/data/<package name>/cache
            if (rootFile.exists()) {
                rootFile.mkdirs();
            } else {
                AppToast.showToast("内存卡不能用");
            }
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventOnUI(EventClass even) {

    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void onEventOnBack(EventClass even) {

    }

    @Subscribe(threadMode = ThreadMode.ASYNC)
    public void onEventOnAsync(EventClass even) {

    }

    @Subscribe(threadMode = ThreadMode.POSTING)
    public void onEventOnPost(EventClass even) {

    }

}
