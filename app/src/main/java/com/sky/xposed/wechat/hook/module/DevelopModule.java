package com.sky.xposed.wechat.hook.module;

import com.sky.xposed.wechat.Constant;
import com.sky.xposed.wechat.hook.base.BaseModule;
import com.sky.xposed.wechat.hook.handler.LogHandler;

/**
 * Created by sky on 18-3-13.
 */

public class DevelopModule extends BaseModule {

    @Override
    public int getId() {
        return Constant.ModuleId.DEVELOP;
    }

    @Override
    public String getName() {
        return "开发调试";
    }

    @Override
    public void onHandleLoadPackage() {

        register(Constant.Preference.ACTIVITY_CYCLE,
                new LogHandler.ActivityCycleHandler());

        register(Constant.Preference.ACTIVITY_START,
                new LogHandler.ActivityStartHandler());

        register(Constant.Preference.ACTIVITY_RESULT,
                new LogHandler.ActivityResultHandler());
    }
}
