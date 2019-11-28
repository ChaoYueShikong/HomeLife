package com.homelife.xhs.event;

import com.homelife.base.base.EventClass;

/**
 * @Description 回到首页
 * @Author Xue
 * @CreateDate 2018/10/9 23:55
 */
public class EventGoMain implements EventClass {

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    String name;

    public EventGoMain(String name) {
        this.name = name;
    }


}
