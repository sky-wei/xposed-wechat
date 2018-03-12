package com.sky.xposed.wechat.hook.base;

import android.content.Context;

import com.sky.xposed.wechat.data.PreferencesManager;
import com.sky.xposed.wechat.hook.HookManager;
import com.sky.xposed.wechat.hook.module.HookModule;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

/**
 * Created by sky on 18-3-12.
 */

public abstract class BaseModule implements HookModule {

    private Context mContext;
    private PreferencesManager mPreferencesManager;
    private XC_LoadPackage.LoadPackageParam mLoadPackageParam;

    @Override
    public void initialization(HookManager hookManager) {

        mContext = hookManager.getContext();
        mPreferencesManager = hookManager.getPreferencesManager();
        mLoadPackageParam = hookManager.getLoadPackageParam();
    }

    @Override
    public void onUnhook() {
    }

    @Override
    public void release() {
    }

    public Context getContext() {
        return mContext;
    }

    public XC_LoadPackage.LoadPackageParam getLoadPackageParam() {
        return mLoadPackageParam;
    }

    public PreferencesManager getPreferencesManager() {
        return mPreferencesManager;
    }

    public Class findClass(String className) {
        return findClass(className, mLoadPackageParam.classLoader);
    }

    public Class findClass(String className, ClassLoader classLoader) {
        return XposedHelpers.findClass(className, classLoader);
    }

    public XC_MethodHook.Unhook findAndHookMethod(String className, String methodName, Object... parameterTypesAndCallback) {
        return XposedHelpers.findAndHookMethod(className, mLoadPackageParam.classLoader, methodName, parameterTypesAndCallback);
    }
}
