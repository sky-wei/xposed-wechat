package com.sky.xposed.wechat.hook;

import android.content.Context;
import android.content.Intent;
import android.util.SparseArray;

import com.sky.xposed.wechat.Constant;
import com.sky.xposed.wechat.config.ConfigManager;
import com.sky.xposed.wechat.data.CachePreferences;
import com.sky.xposed.wechat.hook.event.BackgroundEvent;
import com.sky.xposed.wechat.hook.event.MainEvent;
import com.sky.xposed.wechat.hook.event.MultiProEvent;
import com.sky.xposed.wechat.hook.module.DevelopModule;
import com.sky.xposed.wechat.hook.module.MainModule;
import com.sky.xposed.wechat.hook.module.Module;
import com.sky.xposed.wechat.hook.module.OtherModule;
import com.sky.xposed.wechat.ui.helper.ReceiverHelper;
import com.sky.xposed.wechat.util.Alog;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.HashMap;
import java.util.Map;

import de.robv.android.xposed.callbacks.XC_LoadPackage;

/**
 * Created by sky on 18-3-12.
 */

public class HookManager implements ReceiverHelper.ReceiverCallback {

    private static final HookManager HOOK_MANAGER = new HookManager();

    private Context mContext;
    private XC_LoadPackage.LoadPackageParam mLoadPackageParam;
    private ConfigManager mConfigManager;
    private CachePreferences mCachePreferences;
    private ReceiverHelper mReceiverHelper;
    private SparseArray<Module> mHookModules = new SparseArray<>();

    private HookManager() {
    }

    public static HookManager getInstance() {
        return HOOK_MANAGER;
    }

    public HookManager initialization(Context context, XC_LoadPackage.LoadPackageParam param, ConfigManager configManager) {

        if (mContext != null) {
            throw new IllegalArgumentException("多次初始化异常");
        }

        mContext = context;
        mLoadPackageParam = param;
        mConfigManager = configManager;
        mCachePreferences = new CachePreferences(context, Constant.Name.WE_CAT);
        mReceiverHelper = new ReceiverHelper(context, this,
                Constant.Action.HOOK_EVENT, Constant.Action.REFRESH_VALUE);

        // 注册事件
        EventBus.getDefault().register(this);
        mReceiverHelper.registerReceiver();

        initModule();

        return this;
    }

    public void onHandleLoadPackage() {

        for (int i = 0; i < mHookModules.size(); i++) {
            // 处理加载的包
            onHandleLoadPackage(mHookModules.valueAt(i));
        }
    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void onBackgroundEvent(BackgroundEvent event) {

        for (int i = 0; i < mHookModules.size(); i++) {
            // 处理事件
            onBackgroundEvent(mHookModules.valueAt(i), event);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMainEvent(MainEvent event) {

        for (int i = 0; i < mHookModules.size(); i++) {
            // 处理事件
            onMainEvent(mHookModules.valueAt(i), event);
        }
    }

    public void onMultiProgressEvent(MultiProEvent event) {

        for (int i = 0; i < mHookModules.size(); i++) {
            // 处理事件
            onMultiProgressEvent(mHookModules.valueAt(i), event);
        }
    }

    @Override
    public void onReceive(String action, Intent intent) {

        if (Constant.Action.HOOK_EVENT.equals(action)) {
            // 处理数据
            onMultiProgressEvent((MultiProEvent)
                    intent.getSerializableExtra(Constant.Key.DATA));
        } else if (Constant.Action.REFRESH_VALUE.equals(action)) {

        }
    }

    public void release() {

        // 释放事件
        EventBus.getDefault().unregister(this);
        mReceiverHelper.unregisterReceiver();
    }

    public Context getContext() {
        return mContext;
    }

    public XC_LoadPackage.LoadPackageParam getLoadPackageParam() {
        return mLoadPackageParam;
    }

    public ConfigManager getConfigManager() {
        return mConfigManager;
    }

    public CachePreferences getCachePreferences() {
        return mCachePreferences;
    }

    private void initModule() {

        // 添加模块
        add(new MainModule());
        add(new OtherModule());
        if (Alog.sDEBUG) add(new DevelopModule());
    }

    private void onHandleLoadPackage(Module module) {

        try {
            // 进行Hook处理
            module.onHandleLoadPackage();
        } catch (Throwable tr) {
            Alog.e("onHandleLoadPackage", tr);
        }
    }

    public void onBackgroundEvent(Module module, BackgroundEvent event) {

        try {
            // 处理事件
            module.onBackgroundEvent(event);
        } catch (Throwable tr) {
            Alog.e("onBackgroundEvent", tr);
        }
    }

    public void onMainEvent(Module module, MainEvent event) {

        try {
            // 处理事件
            module.onMainEvent(event);
        } catch (Throwable tr) {
            Alog.e("onMainEvent", tr);
        }
    }

    public void onMultiProgressEvent(Module module, MultiProEvent event) {

        try {
            // 处理事件
            module.onMultiProgressEvent(event);
        } catch (Throwable tr) {
            Alog.e("onMultiProgressEvent", tr);
        }
    }

    private HookManager add(Module module) {

        // 先移除模块
        remove(module.getId());

        try {
            // 添加
            mHookModules.put(module.getId(), module);

            // 初始化
            module.initialization(this);
        } catch (Throwable tr) {
            Alog.e("add", tr);
        }
        return this;
    }

    public void remove(int moduleId) {

        Module module = mHookModules.get(moduleId);

        if (module == null) return ;

        try {
            // 移除
            mHookModules.remove(module.getId());

            // 释放
            module.release();
        } catch (Throwable tr) {
            Alog.e("remove", tr);
        }
        return ;
    }

    public Module get(int moduleId) {
        return mHookModules.get(moduleId);
    }

    public SparseArray<Module> getHookModules() {
        return mHookModules;
    }
}
