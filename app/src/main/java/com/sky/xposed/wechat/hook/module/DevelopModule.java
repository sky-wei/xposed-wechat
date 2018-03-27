package com.sky.xposed.wechat.hook.module;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.os.UserHandle;

import com.sky.xposed.wechat.Constant;
import com.sky.xposed.wechat.hook.base.BaseModule;
import com.sky.xposed.wechat.hook.event.BackgroundEvent;
import com.sky.xposed.wechat.util.Alog;
import com.sky.xposed.wechat.util.EventUtil;
import com.sky.xposed.wechat.util.ToStringUtil;

import de.robv.android.xposed.XC_MethodHook;

/**
 * Created by sky on 18-3-13.
 */

public class DevelopModule extends BaseModule {

    private XC_MethodHook.Unhook mActivityResultUnhook;
    private XC_MethodHook.Unhook mActivityStart1Unhook;
    private XC_MethodHook.Unhook mActivityStart2Unhook;
    private ActivityLifecycleCallbacks mActivityLifecycleCallbacks;

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

        if (isMainProcess()) {
            // 发送事件
            EventUtil.postBackgroundEvent(Constant.EventId.ACTIVITY_CYCLE,
                    getBooleanValue(Constant.Preference.ACTIVITY_CYCLE));
            EventUtil.postBackgroundEvent(Constant.EventId.ACTIVITY_START,
                    getBooleanValue(Constant.Preference.ACTIVITY_START));
            EventUtil.postBackgroundEvent(Constant.EventId.ACTIVITY_RESULT,
                    getBooleanValue(Constant.Preference.ACTIVITY_RESULT));
        }
    }

    @Override
    public void onBackgroundEvent(BackgroundEvent event) {
        super.onBackgroundEvent(event);

        switch (event.getId()) {
            case Constant.EventId.ACTIVITY_CYCLE :
                // 处理Activity生命周期
                handlerActivityCycle(event.isBooleanValue());
                break;
            case Constant.EventId.ACTIVITY_START :
                // 处理Activity启动参数
                handlerActivityStart(event.isBooleanValue());
                break;
            case Constant.EventId.ACTIVITY_RESULT :
                // 处理Activity结果
                handlerActivityResult(event.isBooleanValue());
                break;
        }
    }

    private void handlerActivityCycle(boolean enable) {

        Application application = (Application) getContext();

        if (!enable) {
            // 注销事件
            if (mActivityLifecycleCallbacks != null) {
                application.unregisterActivityLifecycleCallbacks(mActivityLifecycleCallbacks);
                mActivityLifecycleCallbacks = null;
            }
            return ;
        }

        // 注册事件
        mActivityLifecycleCallbacks = new ActivityLifecycleCallbacks();
        application.registerActivityLifecycleCallbacks(mActivityLifecycleCallbacks);
    }

    private void handlerActivityStart(boolean enable) {

        if (!enable) {
            // 释放
            unhook(mActivityStart1Unhook);
            unhook(mActivityStart2Unhook);
            return ;
        }

        mActivityStart1Unhook = findAndHookMethod(
                "android.app.Instrumentation", "execStartActivity",
                Context.class, IBinder.class, IBinder.class,
                Activity.class, Intent.class, int.class, Bundle.class,
                new XC_MethodHook() {

                    @Override
                    protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                        super.beforeHookedMethod(param);

                        Intent intent = (Intent) param.args[4];
                        ToStringUtil.toString("Instrumentation#execStartActivity: " + intent.getComponent(), intent);
                    }
                });

        mActivityStart2Unhook = findAndHookMethod(
                "android.app.Instrumentation", "execStartActivity",
                Context.class, IBinder.class, IBinder.class,
                Activity.class, Intent.class, int.class,
                Bundle.class, UserHandle.class,
                new XC_MethodHook() {

                    @Override
                    protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                        super.beforeHookedMethod(param);

                        Intent intent = (Intent) param.args[4];
                        ToStringUtil.toString("Instrumentation#execStartActivity: " + intent.getComponent(), intent);
                    }
                });
    }

    private void handlerActivityResult(boolean enable) {

        if (!enable) {
            // 释放
            unhook(mActivityResultUnhook);
            return ;
        }

        mActivityResultUnhook = findAndHookMethod(
                "android.app.Activity",
                "setResult",
                int.class, Intent.class,
                new XC_MethodHook() {

                    @Override
                    protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                        super.beforeHookedMethod(param);

                        // 输出信息
                        String name = param.thisObject.getClass().getName();
                        ToStringUtil.toString("Activity#setResult: " + name + " " + param.args[0], param.args[1]);
                    }
                });
    }

    private final class ActivityLifecycleCallbacks implements Application.ActivityLifecycleCallbacks {

        @Override
        public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
            Alog.d("Activity#onActivityCreated: " + activity);
        }

        @Override
        public void onActivityStarted(Activity activity) {

        }

        @Override
        public void onActivityResumed(Activity activity) {

        }

        @Override
        public void onActivityPaused(Activity activity) {

        }

        @Override
        public void onActivityStopped(Activity activity) {

        }

        @Override
        public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

        }

        @Override
        public void onActivityDestroyed(Activity activity) {
            Alog.d("Activity#onActivityDestroyed: " + activity);
        }
    }
}
