package com.sky.xposed.wechat.hook.module;

import com.sky.xposed.wechat.Constant;
import com.sky.xposed.wechat.hook.base.BaseModule;
import com.sky.xposed.wechat.hook.handler.MainHandler;

/**
 * Created by sky on 18-3-20.
 */

public class MainModule extends BaseModule {

    @Override
    public int getId() {
        return 0;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public void onHandleLoadPackage() {

        if (isMainProcess()) {
            // 处理菜单
            register(Constant.Preference.MAIN_MENU,
                    new MainHandler.UIMenuHandler());
        }
    }
}
