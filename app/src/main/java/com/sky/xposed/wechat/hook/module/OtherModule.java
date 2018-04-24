package com.sky.xposed.wechat.hook.module;

import com.sky.xposed.wechat.Constant;
import com.sky.xposed.wechat.hook.base.BaseModule;
import com.sky.xposed.wechat.hook.handler.OtherHandler;

/**
 * Created by sky on 18-3-12.
 */

public class OtherModule extends BaseModule {

    @Override
    public int getId() {
        return Constant.ModuleId.OTHER;
    }

    @Override
    public String getName() {
        return "其他功能";
    }

    @Override
    public void onHandleLoadPackage() {

        if (isMainProcess()) {
            // 自动登录处理
            register(Constant.Preference.AUTO_LOGIN,
                    new OtherHandler.AutoLoginHandler());
        }
    }
}
