package com.sky.xposed.wechat.hook;

import android.content.Context;
import android.content.Intent;
import android.util.SparseArray;

import com.sky.xposed.wechat.Constant;
import com.sky.xposed.wechat.config.ConfigManager;
import com.sky.xposed.wechat.data.CachePreferences;
import com.sky.xposed.wechat.data.model.Pair;
import com.sky.xposed.wechat.hook.handler.Handler;
import com.sky.xposed.wechat.hook.module.DevelopModule;
import com.sky.xposed.wechat.hook.module.MainModule;
import com.sky.xposed.wechat.hook.module.Module;
import com.sky.xposed.wechat.hook.module.OtherModule;
import com.sky.xposed.wechat.ui.helper.ReceiverHelper;
import com.sky.xposed.wechat.util.Alog;

import java.util.HashMap;
import java.util.List;
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
    private Map<String, Handler> mHandlerMap = new HashMap<>();

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
        mReceiverHelper = new ReceiverHelper(
                context, this, Constant.Action.STATUS_CHANGE);

        // 注册事件
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

    @Override
    public void onReceive(String action, Intent intent) {

        if (Constant.Action.STATUS_CHANGE.equals(action)) {
            // 处理修改的值通知
            onStatusChange((List<Pair<String, Object>>)
                    intent.getSerializableExtra(Constant.Key.DATA));
        }
    }

    public void register(String key, Handler handler) {

        if (handler == null
                || mHandlerMap.containsKey(key)) {
            return;
        }

        try {
            // 初始化
            handler.onInitialize(key, this);
            mHandlerMap.put(key, handler);
            // 开始处理
            handler.onHandleLoadPackage();
        } catch (Throwable tr) {
            Alog.e("Register异常");
        }
    }

    public void unregister(String key) {

        if (!mHandlerMap.containsKey(key)) {
            return;
        }

        try {
            // 释放
            Handler handler = mHandlerMap.remove(key);
            handler.onRelease();
        } catch (Throwable tr) {
            Alog.e("Register异常");
        }
    }

    public void release() {

        // 释放事件
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

    private void onStatusChange(List<Pair<String, Object>> pairs) {

        if (pairs == null) return;

        for (Pair<String, Object> pair : pairs) {

            // 修改缓存的信息
            mCachePreferences.putObject(pair.first, pair.second);

            // 直接调用
            onHandlerStatusChange(pair);
        }
    }

    private void onHandlerStatusChange(Pair<String, Object> pair) {

        // 异常不需要处理
        if (pair == null) return;

        Alog.d("onHandlerStatusChange " + pair);

        try {
            // 获取需要处理的对象
            Handler handler = mHandlerMap.get(pair.first);

            if (handler != null) {
                // 传递到需要处理的类
                handler.onStatusChange(pair.first, pair.second);
            }
        } catch (Throwable tr) {
            Alog.e("onStatusChange", tr);
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
