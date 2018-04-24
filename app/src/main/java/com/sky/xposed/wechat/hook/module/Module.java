package com.sky.xposed.wechat.hook.module;

import com.sky.xposed.wechat.hook.HookManager;

/**
 * Created by sky on 18-3-12.
 */

public interface Module {

    int getId();

    String getName();

    void initialization(HookManager hookManager);

    void onHandleLoadPackage();

    void release();
}
