package com.sky.xposed.wechat.hook.module;

import android.widget.Button;

import com.sky.xposed.wechat.Constant;
import com.sky.xposed.wechat.hook.base.BaseModule;
import com.sky.xposed.wechat.hook.event.BackgroundEvent;
import com.sky.xposed.wechat.util.Alog;
import com.sky.xposed.wechat.util.FindUtil;

import java.lang.reflect.Field;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedHelpers;

/**
 * Created by sky on 18-3-12.
 */

public class OtherModule extends BaseModule {

    private XC_MethodHook.Unhook mAutoLoginUnhook;

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
            // 发送事件
            postEvent(new BackgroundEvent(Constant.EventId.AUTO_LOGIN,
                    getPreferencesManager().getBoolean(Constant.Preference.AUTO_LOGIN, false)));
        }
    }

    @Override
    public void onBackgroundEvent(BackgroundEvent event) {
        super.onBackgroundEvent(event);

        switch (event.getId()) {
            case Constant.EventId.AUTO_LOGIN :
                // 处理自动登录
                handlerAutoLogin(event.isBooleanValue());
                break;
        }
    }

    private void handlerAutoLogin(boolean autoLogin) {

        Alog.d(">>>>>>>>>>>>>>>>>>>>>>>  cccc " + autoLogin);

        if (!autoLogin) {
            // 释放
            if (mAutoLoginUnhook != null) mAutoLoginUnhook.unhook();
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
