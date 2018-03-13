package com.sky.xposed.wechat.hook.module;

import com.sky.xposed.wechat.Constant;
import com.sky.xposed.wechat.hook.base.BaseModule;

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
    public void add(int moduleId) {
    }
}
