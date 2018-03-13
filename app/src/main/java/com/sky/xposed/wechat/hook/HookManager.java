package com.sky.xposed.wechat.hook;

import android.content.Context;
import android.util.SparseArray;

import com.sky.xposed.wechat.data.PreferencesManager;
import com.sky.xposed.wechat.hook.module.HookModule;
import com.sky.xposed.wechat.util.Alog;

import de.robv.android.xposed.callbacks.XC_LoadPackage;

/**
 * Created by sky on 18-3-12.
 */

public class HookManager {

    private static final HookManager HOOK_MANAGER = new HookManager();

    private Context mContext;
    private XC_LoadPackage.LoadPackageParam mLoadPackageParam;
    private PreferencesManager mPreferencesManager;
    private SparseArray<HookModule> mHookModules = new SparseArray<>();

    private HookManager() {

    }

    public static HookManager getInstance() {
        return HOOK_MANAGER;
    }

    public HookManager initialization(Context context, XC_LoadPackage.LoadPackageParam param) {

        if (mContext != null) {
            throw new IllegalArgumentException("多次初始化异常");
        }

        mContext = context;
        mLoadPackageParam = param;
        mPreferencesManager = new PreferencesManager(context);

        return this;
    }

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

    public HookManager add(HookModule module) {

        // 先移除模块
        remove(module.getId());

        try {
            // 添加
            mHookModules.put(module.getId(), module);

            // 初始化
            module.initialization(this);
            module.onHook();
        } catch (Throwable tr) {
            Alog.e("add", tr);
        }
        return this;
    }

    public HookManager remove(int moduleId) {

        HookModule module = mHookModules.get(moduleId);

        if (module == null) return this;

        try {
            // 移除
            mHookModules.remove(module.getId());

            // 释放
            module.onUnhook();
            module.release();
        } catch (Throwable tr) {
            Alog.e("remove", tr);
        }
        return this;
    }

    public HookModule get(int moduleId) {
        return mHookModules.get(moduleId);
    }

    public SparseArray<HookModule> getHookModules() {
        return mHookModules;
    }
}
