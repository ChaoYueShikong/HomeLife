package com.homelife.base.receiver;

/**
 * @Description 网络类型
 * @Author Xue
 * @CreateDate 2017/9/27
 */
public enum NetType {
    None(1),    //无网络
    Yes(2),     //有网络
    Mobile(3),  //移动网络
    Wifi(4),    //wifi网络
    Other(5);   //其他网络

    NetType(int value) {
        this.value = value;
    }

    public int value;
}
