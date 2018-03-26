package com.sky.xposed.wechat.hook.event;

import com.sky.xposed.wechat.hook.base.BaseEvent;

/**
 * Created by sky on 18-3-26.
 */

public class MainEvent extends BaseEvent {

    public MainEvent(int id) {
        super(id);
    }

    public MainEvent(int id, boolean bValue) {
        super(id, bValue);
    }
}
