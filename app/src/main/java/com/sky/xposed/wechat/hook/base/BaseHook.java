package com.sky.xposed.wechat.hook.base;

import android.app.ActivityThread;
import android.content.Context;

import com.sky.xposed.wechat.util.Alog;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

/**
 * Created by sky on 18-3-10.
 */

public abstract class BaseHook implements IXposedHookLoadPackage {

    private XC_LoadPackage.LoadPackageParam mParam;

    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam lpparam) throws Throwable {
        this.mParam = lpparam;

        try {
            // 处理
            onHandleLoadPackage(lpparam);
        } catch (Throwable tr) {
            Alog.e("handleLoadPackage异常", tr);
        }
    }

    public abstract void onHandleLoadPackage(XC_LoadPackage.LoadPackageParam param);

    public Context getSystemContext() {
        return ActivityThread.currentActivityThread().getSystemContext();
    }

    public XC_LoadPackage.LoadPackageParam getLoadPackageParam() {
        return mParam;
    }

    public Class findClass(String className) {
        return findClass(className, mParam.classLoader);
    }

    public Class findClass(String className, ClassLoader classLoader) {
        return XposedHelpers.findClass(className, classLoader);
    }

    public XC_MethodHook.Unhook findAndHookMethod(String className, String methodName, Object... parameterTypesAndCallback) {
        return XposedHelpers.findAndHookMethod(className, mParam.classLoader, methodName, parameterTypesAndCallback);
    }
}
