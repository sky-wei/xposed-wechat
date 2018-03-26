package com.sky.xposed.wechat.hook.module;

import com.sky.xposed.wechat.hook.HookManager;
import com.sky.xposed.wechat.hook.event.BackgroundEvent;
import com.sky.xposed.wechat.hook.event.MainEvent;
import com.sky.xposed.wechat.hook.event.MultiProEvent;

/**
 * Created by sky on 18-3-12.
 */

public interface Module {

    int getId();

    String getName();

    void initialization(HookManager hookManager);

    void onHandleLoadPackage();

    void onBackgroundEvent(BackgroundEvent event);

    void onMainEvent(MainEvent event);

    void onMultiProgressEvent(MultiProEvent event);

    void release();

    void reloadConfig();
}
