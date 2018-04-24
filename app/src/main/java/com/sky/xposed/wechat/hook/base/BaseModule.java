package com.sky.xposed.wechat.hook.base;

import android.content.Context;

import com.sky.xposed.wechat.Constant;
import com.sky.xposed.wechat.hook.HookManager;
import com.sky.xposed.wechat.hook.handler.Handler;
import com.sky.xposed.wechat.hook.module.Module;

import de.robv.android.xposed.callbacks.XC_LoadPackage;

/**
 * Created by sky on 18-3-12.
 */

public abstract class BaseModule implements Module {

    private HookManager mHookManager;
    private Context mContext;
    private XC_LoadPackage.LoadPackageParam mLoadPackageParam;

    @Override
    public void initialization(HookManager hookManager) {
        mHookManager = hookManager;
        mContext = hookManager.getContext();
        mLoadPackageParam = hookManager.getLoadPackageParam();
    }

    @Override
    public void release() {
    }

    public void register(String key, Handler handler) {
        mHookManager.register(key, handler);
    }

    public void unregister(String key) {
        mHookManager.unregister(key);
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
}
