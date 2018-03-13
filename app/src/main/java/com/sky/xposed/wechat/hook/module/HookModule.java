package com.sky.xposed.wechat.hook.module;

import com.sky.xposed.wechat.hook.HookManager;

/**
 * Created by sky on 18-3-12.
 */

public interface HookModule {

    int getId();

    String getName();

    void initialization(HookManager hookManager);

    void onHook();

    void onUnhook();

    void release();

    void reloadConfig();

    void add(int moduleId);

    void remove(int moduleId);
}
