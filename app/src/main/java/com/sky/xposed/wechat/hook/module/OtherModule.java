package com.sky.xposed.wechat.hook.module;

import android.widget.Button;

import com.sky.xposed.wechat.Constant;
import com.sky.xposed.wechat.hook.base.BaseModule;
import com.sky.xposed.wechat.util.FindUtil;

import java.lang.reflect.Field;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedHelpers;

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
    public void onHook() {
        super.onHook();

    }

    @Override
    public void add(int moduleId) {

        if (Constant.ModuleId.AUTO_LOGIN == moduleId) {
            // 添加模块
            add(new AutoLoginModule());
        }
    }

    private class AutoLoginModule extends BaseModule {

        private XC_MethodHook.Unhook mUnhook;

        @Override
        public int getId() {
            return Constant.ModuleId.AUTO_LOGIN;
        }

        @Override
        public String getName() {
            return "自动登录";
        }

        @Override
        public void onHook() {

            mUnhook = findAndHookMethod(
                    "com.tencent.mm.plugin.webwx.ui.ExtDeviceWXLoginUI",
                    "onResume",
                    new XC_MethodHook() {
                        @Override
                        protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                            super.beforeHookedMethod(param);

                            final Object thisObject = param.thisObject;

                            Field fieldButton = FindUtil.firstOrNull(
                                    thisObject.getClass().getDeclaredFields(), new FindUtil.Filter<Field>() {
                                        @Override
                                        public boolean accept(Field field) {
                                            return field.getType() == Button.class;
                                        }
                                    });

                            if (fieldButton != null) {

                                // 获取登录按钮
                                Button loginButton = (Button) XposedHelpers
                                        .getObjectField(thisObject, fieldButton.getName());

                                if (loginButton.isEnabled()) {

                                    // 点击登录
                                    loginButton.performClick();
                                }
                            }
                        }
                    });
        }

        @Override
        public void onUnhook() {
            super.onUnhook();

            if (mUnhook != null) mUnhook.unhook();
        }

        @Override
        public void add(int moduleId) {
        }
    }
}
