package com.sky.xposed.wechat.hook.base;

import java.io.Serializable;

/**
 * Created by sky on 18-3-26.
 */

public abstract class BaseEvent implements Serializable {

    private int id;
    private boolean booleanValue;
    private Object data;

    public BaseEvent(int id) {
        this.id = id;
    }

    public BaseEvent(int id, boolean booleanValue) {
        this.id = id;
        this.booleanValue = booleanValue;
    }

    public BaseEvent(int id, boolean booleanValue, Object data) {
        this.id = id;
        this.booleanValue = booleanValue;
        this.data = data;
    }

    public int getId() {
        return id;
    }

    public boolean isBooleanValue() {
        return booleanValue;
    }

    public Object getData() {
        return data;
    }
}
