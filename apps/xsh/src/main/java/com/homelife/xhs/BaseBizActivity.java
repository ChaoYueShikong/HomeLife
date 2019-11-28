package com.homelife.xhs;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import com.homelife.base.base.BaseActivity;
import com.homelife.xhs.receiver.HomeWatcherReceiver;

/**
 * @Description 监听home按键
 * @Author Xue
 * @CreateDate 2018/10/4 00:54
 */
public class BaseBizActivity extends BaseActivity {
    private static final String LOG_TAG = "HomeReceiver";
    private HomeWatcherReceiver mHomeKeyReceiver;

    @Override
    protected void onResume() {
        super.onResume();
        registerHomeKeyReceiver(this);
    }

    @Override
    protected void onPause() {
        unregisterHomeKeyReceiver(this);
        super.onPause();
    }

    private  void registerHomeKeyReceiver(Context context) {
        mHomeKeyReceiver = new HomeWatcherReceiver();
        final IntentFilter homeFilter = new IntentFilter(Intent.ACTION_CLOSE_SYSTEM_DIALOGS);
        context.registerReceiver(mHomeKeyReceiver, homeFilter);
    }

    private  void unregisterHomeKeyReceiver(Context context) {
        if (null != mHomeKeyReceiver) {
            context.unregisterReceiver(mHomeKeyReceiver);
        }
    }


}
