package com.homelife.base.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.homelife.util.AppToast;
import com.homelife.util.NetUtil;

import org.greenrobot.eventbus.EventBus;

/**
 * @Description 网络监控广播，所有页面能获取到网络状态
 * @Author Xue
 * @CreateDate 2017/9/27
 */
public class NetStateReceiver extends BroadcastReceiver {
    Context mContext = null;

    @Override
    public void onReceive(Context context, Intent intent) {
        mContext = context;
        if (NetUtil.isNetConnected(mContext)) {// 判断网络是否连接
            AppToast.showToast("已连接网络！");
            EventBus.getDefault().post(new NetEvent(NetType.Yes)); //如果需要细分，需扩展
        } else {// 当前无网络
            AppToast.showToast("当前无网络！");
            EventBus.getDefault().post(new NetEvent(NetType.None)); //没有网络
        }
    }
}
