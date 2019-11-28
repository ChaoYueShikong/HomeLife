package com.homelife.base.receiver;

import com.homelife.base.base.EventClass;

/**
 * @Description 网络通知
 * @Author Xue
 * @CreateDate 2019/1/23 01:59
 */
public class NetEvent implements EventClass {

    public NetType getNetType() {
        return netType;
    }

    public void setNetType(NetType netType) {
        this.netType = netType;
    }

    NetType netType;

    NetEvent(NetType type) {
        this.netType = type;
    }
}
