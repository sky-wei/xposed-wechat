package com.sky.xposed.wechat.hook.base;

import android.content.Context;

import com.sky.xposed.wechat.Constant;
import com.sky.xposed.wechat.data.CachePreferences;
import com.sky.xposed.wechat.hook.HookManager;
import com.sky.xposed.wechat.hook.event.BackgroundEvent;
import com.sky.xposed.wechat.hook.event.MainEvent;
import com.sky.xposed.wechat.hook.event.MultiProEvent;
import com.sky.xposed.wechat.hook.module.Module;
import com.sky.xposed.wechat.util.EventUtil;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

/**
 * Created by sky on 18-3-12.
 */

public abstract class BaseModule implements Module {

    private HookManager mHookManager;
    private Context mContext;
    private CachePreferences mCachePreferences;
    private XC_LoadPackage.LoadPackageParam mLoadPackageParam;
//    private SparseArray<Module> mHookModules = new SparseArray<>();

    @Override
    public void initialization(HookManager hookManager) {
        mHookManager = hookManager;
        mContext = hookManager.getContext();
        mCachePreferences = hookManager.getCachePreferences();
        mLoadPackageParam = hookManager.getLoadPackageParam();
    }

    @Override
    public void release() {
    }

    @Override
    public void reloadConfig() {

//        for (int i = 0; i < mHookModules.size(); i++) {
//            // 添加注册的模块
//            Module module = mHookModules.valueAt(i);
//            module.reloadConfig();
//        }
    }

//    public void add(Module module) {
//
//        // 先移除模块
//        remove(module.getId());
//
//        try {
//            // 添加
//            mHookModules.put(module.getId(), module);
//
//            // 初始化
//            module.initialization(mHookManager);
//            module.onHook();
//        } catch (Throwable tr) {
//            Alog.e("add", tr);
//        }
//    }
//
//    @Override
//    public void remove(int moduleId) {
//
//        Module module = mHookModules.get(moduleId);
//
//        if (module == null) return ;
//
//        try {
//            // 移除
//            mHookModules.remove(module.getId());
//
//            // 释放
//            module.onUnhook();
//            module.release();
//        } catch (Throwable tr) {
//            Alog.e("remove", tr);
//        }
//    }

    public void onBackgroundEvent(BackgroundEvent event) {

    }

    public void onMainEvent(MainEvent event) {

    }

    public void onMultiProgressEvent(MultiProEvent event) {

    }

    public void postEvent(BaseEvent event) {
        EventUtil.postEvent(event);
    }

    public void postMultiProgressEvent(MultiProEvent event) {
        EventUtil.postMultiProgressEvent(getContext(), event);
    }

    public boolean getBooleanValue(String key) {
        return getCachePreferences().getBoolean(key, false);
    }

    public void putBooleanValue(String key, boolean value) {
        getCachePreferences().putBoolean(key, value);
    }

    public String getProcessName() {
        return mLoadPackageParam.processName;
    }

    public int getProcessType() {

        String processName = getProcessName();
        int index = processName.lastIndexOf(":");

        if (index == -1) return Constant.Process.MAIN;

        switch (processName.substring(index + 1)) {
            case "exdevice" :
                return Constant.Process.EX_DEVICE;
            case "push" :
                return Constant.Process.PUSH;
            case "support" :
                return Constant.Process.SUPPORT;
            case "tools" :
                return Constant.Process.TOOLS;
            case "sandbox" :
                return Constant.Process.SANDBOX;
        }
        return Constant.Process.OTHER;
    }

    public boolean isMainProcess() {
        return Constant.Process.MAIN == getProcessType();
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
}
