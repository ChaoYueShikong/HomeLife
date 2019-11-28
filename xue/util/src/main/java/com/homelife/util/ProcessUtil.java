/*
 * Copyright (c) 2016 Corporation. All rights reserved.
 *
 */
package com.homelife.util;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Process;

/**
 * @Description 处理进程类工具
 * @Author Xue
 * @CreateDate
 */
public final class ProcessUtil {
    private static final Context context = Static.CONTEXT;
    private static ActivityManager mActivityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);

    private ProcessUtil() {
    }

    /**
     * @return true(是主进程) false(不是主进程)
     * @description 判断是否是主进程
     * @author Xue
     */
    public static boolean isCurMainProcess() {
        boolean isMainProcess = false;
        int pid = Process.myPid();
        for (ActivityManager.RunningAppProcessInfo appProcess : mActivityManager.getRunningAppProcesses()) {
            if (appProcess.pid == pid && !appProcess.processName.contains(":")) {
                isMainProcess = true;
            }
        }
        return isMainProcess;
    }

    /**
     * @return 所有进程名称的数组
     * @description 返回当前进程的名称（一个APP有多多个进程情况）
     * @author Xue
     */
    public static String[] getCurProcessName() {
        // String[] processName = new String[mActivityManager.getRunningAppProcesses().size()];
        String[] processName = new String[5];
        int pid = Process.myPid(), i = 0;
        for (ActivityManager.RunningAppProcessInfo appProcess : mActivityManager.getRunningAppProcesses()) {
            if (appProcess.pid == pid) {
                processName[i] = appProcess.processName;
                i++;
            }
        }
        return processName;
    }

    /**
     * @description 杀死进程
     * @author Xue
     */
    public static void killProcess() {
        int pid = Process.myPid();
        Process.killProcess(pid);
        System.exit(0);
    }
}
