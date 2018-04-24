package com.sky.xposed.wechat.hook.handler;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.os.UserHandle;

import com.sky.xposed.wechat.hook.base.BaseHandler;
import com.sky.xposed.wechat.util.Alog;
import com.sky.xposed.wechat.util.ToStringUtil;

import de.robv.android.xposed.XC_MethodHook;

public class LogHandler extends BaseHandler {

    public static class ActivityCycleHandler extends BaseHandler {

        private ActivityLifecycleCallbacks mActivityLifecycleCallbacks;

        @Override
        public void onHandleLoadPackage() {
            // 开始处理
            onStatusChange(getKey(), getBooleanValue(getKey()));
        }

        @Override
        public void onStatusChange(String key, Object value) {
            super.onStatusChange(key, value);

            final boolean enable = (boolean) value;
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
    }

    public static class ActivityStartHandler extends BaseHandler {

        private XC_MethodHook.Unhook mActivityStart1Unhook;
        private XC_MethodHook.Unhook mActivityStart2Unhook;

        @Override
        public void onHandleLoadPackage() {
            super.onHandleLoadPackage();
            // 开始处理
            onStatusChange(getKey(), getBooleanValue(getKey()));
        }

        @Override
        public void onStatusChange(String key, Object value) {
            super.onStatusChange(key, value);

            final boolean enable = (boolean) value;

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
    }

    public static class ActivityResultHandler extends BaseHandler {

        private XC_MethodHook.Unhook mActivityResultUnhook;

        @Override
        public void onHandleLoadPackage() {
            super.onHandleLoadPackage();
            // 开始处理
            onStatusChange(getKey(), getBooleanValue(getKey()));
        }

        @Override
        public void onStatusChange(String key, Object value) {
            super.onStatusChange(key, value);

            final boolean enable = (boolean) value;

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
    }

    public static class ActivityLifecycleCallbacks implements Application.ActivityLifecycleCallbacks {

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
