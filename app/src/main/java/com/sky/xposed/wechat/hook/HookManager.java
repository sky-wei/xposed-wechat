package com.sky.xposed.wechat.hook;

import android.content.Context;

import com.sky.xposed.wechat.data.PreferencesManager;
import com.sky.xposed.wechat.hook.base.BaseModule;
import com.sky.xposed.wechat.hook.module.HookModule;
import com.sky.xposed.wechat.util.Alog;

import java.util.ArrayList;
import java.util.List;

import de.robv.android.xposed.callbacks.XC_LoadPackage;

/**
 * Created by sky on 18-3-12.
 */

public class HookManager {

    private static final HookManager HOOK_MANAGER = new HookManager();

    private Context mContext;
    private XC_LoadPackage.LoadPackageParam mLoadPackageParam;
    private List<HookModule> mHookModules = new ArrayList<>();

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
        return null;
    }

    public HookManager register(HookModule module) {

        try {
            // 添加
            mHookModules.add(module);

            // 初始化
            module.initialization(this);
            module.onHook();
        } catch (Throwable tr) {
            Alog.e("注册异常", tr);
        }
        return this;
    }

    public HookManager unregister(HookModule module) {

        try {
            // 移除
            mHookModules.remove(module);

            // 释放
            module.onHook();
            module.release();
        } catch (Throwable tr) {
            Alog.e("注册异常", tr);
        }
        return this;
    }

    public List<HookModule> getHookModules() {
        return mHookModules;
    }
}
