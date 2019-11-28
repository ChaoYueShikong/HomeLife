package com.homelife.xhs.receiver;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.homelife.xhs.event.EventGoMain;

import org.greenrobot.eventbus.EventBus;

/**
 * @Description 监听home广播
 * @Author Xue
 */
public class HomeWatcherReceiver extends BroadcastReceiver {
    private static final String SYSTEM_DIALOG_REASON_KEY = "reason";
    private static final String SYSTEM_DIALOG_REASON_RECENT_APPS = "recentapps";
    private static final String SYSTEM_DIALOG_REASON_HOME_KEY = "homekey";
    private static final String SYSTEM_DIALOG_REASON_LOCK = "lock";
    private static final String SYSTEM_DIALOG_REASON_ASSIST = "assist";

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (action.equals(Intent.ACTION_CLOSE_SYSTEM_DIALOGS)) {
            String reason = intent.getStringExtra(SYSTEM_DIALOG_REASON_KEY);

            if (SYSTEM_DIALOG_REASON_HOME_KEY.equals(reason)) {
                // 短按Home键
                EventBus.getDefault().post(new EventGoMain("GoMain"));
            } else if (SYSTEM_DIALOG_REASON_RECENT_APPS.equals(reason)) {
                // 长按Home键 或者 activity切换键
                EventBus.getDefault().post(new EventGoMain("GoMain"));
            } else if (SYSTEM_DIALOG_REASON_LOCK.equals(reason)) {
                // 锁屏
                EventBus.getDefault().post(new EventGoMain("GoMain"));
            } else if (SYSTEM_DIALOG_REASON_ASSIST.equals(reason)) {
                // samsung 长按Home键
                EventBus.getDefault().post(new EventGoMain("GoMain"));
            }
        }
    }
}
