package com.sky.xposed.wechat.hook.handler;

import com.sky.xposed.wechat.hook.HookManager;

public interface Handler {

    void onInitialize(String key, HookManager hookManager);

    void onHandleLoadPackage();

    void onStatusChange(String key, Object value);

    void onRelease();
}
