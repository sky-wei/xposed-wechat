package com.sky.xposed.wechat.hook.event;

import com.sky.xposed.wechat.hook.base.BaseEvent;

/**
 * Created by sky on 18-3-26.
 */

public class MultiProEvent extends BaseEvent {

    public MultiProEvent(int id) {
        super(id);
    }

    public MultiProEvent(int id, boolean booleanValue) {
        super(id, booleanValue);
    }
}
