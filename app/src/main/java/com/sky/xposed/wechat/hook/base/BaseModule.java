package com.sky.xposed.wechat.hook.base;

import android.content.Context;
import android.util.SparseArray;

import com.sky.xposed.wechat.data.PreferencesManager;
import com.sky.xposed.wechat.hook.HookManager;
import com.sky.xposed.wechat.hook.module.HookModule;
import com.sky.xposed.wechat.util.Alog;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

/**
 * Created by sky on 18-3-12.
 */

public abstract class BaseModule implements HookModule {

    private HookManager mHookManager;
    private Context mContext;
    private PreferencesManager mPreferencesManager;
    private XC_LoadPackage.LoadPackageParam mLoadPackageParam;
    private SparseArray<HookModule> mHookModules = new SparseArray<>();

    @Override
    public void initialization(HookManager hookManager) {
        mHookManager = hookManager;
        mContext = hookManager.getContext();
        mPreferencesManager = hookManager.getPreferencesManager();
        mLoadPackageParam = hookManager.getLoadPackageParam();
    }

    @Override
    public void onHook() {
    }

    @Override
    public void onUnhook() {
    }

    @Override
    public void release() {
    }

    @Override
    public void reloadConfig() {

        for (int i = 0; i < mHookModules.size(); i++) {
            // 添加注册的模块
            HookModule module = mHookModules.valueAt(i);
            module.reloadConfig();
        }
    }

    public void add(HookModule module) {

        // 先移除模块
        remove(module.getId());

        try {
            // 添加
            mHookModules.put(module.getId(), module);

            // 初始化
            module.initialization(mHookManager);
            module.onHook();
        } catch (Throwable tr) {
            Alog.e("add", tr);
        }
    }

    @Override
    public void remove(int moduleId) {

        HookModule module = mHookModules.get(moduleId);

        if (module == null) return ;

        try {
            // 移除
            mHookModules.remove(module.getId());

            // 释放
            module.onUnhook();
            module.release();
        } catch (Throwable tr) {
            Alog.e("remove", tr);
        }
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
