package com.sky.xposed.wechat.hook.handler;

import android.widget.Button;

import com.sky.xposed.wechat.hook.base.BaseHandler;
import com.sky.xposed.wechat.util.FindUtil;

import java.lang.reflect.Field;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedHelpers;

public class OtherHandler extends BaseHandler {

    public static class AutoLoginHandler extends BaseHandler {

        private XC_MethodHook.Unhook mAutoLoginUnhook;

        @Override
        public void onHandleLoadPackage() {
            super.onHandleLoadPackage();
        }

        @Override
        public void onStatusChange(String key, Object value) {
            super.onStatusChange(key, value);

            if (!(boolean) value) {
                // 释放
                unhook(mAutoLoginUnhook);
                return ;
            }

            mAutoLoginUnhook = findAndHookMethod(
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
    }
}
