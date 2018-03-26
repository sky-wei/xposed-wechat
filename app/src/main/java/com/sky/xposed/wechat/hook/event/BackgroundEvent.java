package com.sky.xposed.wechat.hook.event;

import com.sky.xposed.wechat.hook.base.BaseEvent;

/**
 * Created by sky on 18-3-26.
 */

public class BackgroundEvent extends BaseEvent {

    public BackgroundEvent(int id) {
        super(id);
    }

    public BackgroundEvent(int id, boolean bValue) {
        super(id, bValue);
    }
}
