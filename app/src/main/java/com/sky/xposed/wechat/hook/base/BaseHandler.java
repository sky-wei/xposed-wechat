package com.sky.xposed.wechat.hook.base;

import android.content.Context;

import com.sky.xposed.wechat.data.CachePreferences;
import com.sky.xposed.wechat.hook.HookManager;
import com.sky.xposed.wechat.hook.handler.Handler;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public abstract class BaseHandler implements Handler {

    private String mKey;
    private HookManager mHookManager;
    private Context mContext;
    private CachePreferences mCachePreferences;
    private XC_LoadPackage.LoadPackageParam mLoadPackageParam;

    @Override
    public void onInitialize(String key, HookManager hookManager) {
        mKey = key;
        mHookManager = hookManager;
        mContext = hookManager.getContext();
        mCachePreferences = hookManager.getCachePreferences();
        mLoadPackageParam = hookManager.getLoadPackageParam();
    }

    @Override
    public void onHandleLoadPackage() {
    }

    @Override
    public void onStatusChange(String key, Object value) {

    }

    @Override
    public void onRelease() {
    }

    public String getKey() {
        return mKey;
    }

    public Context getContext() {
        return mContext;
    }

    public HookManager getHookManager() {
        return mHookManager;
    }

    public XC_LoadPackage.LoadPackageParam getLoadPackageParam() {
        return mLoadPackageParam;
    }

    public CachePreferences getCachePreferences() {
        return mCachePreferences;
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

    public void unhook(XC_MethodHook.Unhook unhook) {
        if (unhook != null) unhook.unhook();
    }

    public boolean getBooleanValue(String key) {
        return mCachePreferences.getBoolean(key, false);
    }
}
