package com.sky.xposed.wechat.config.v665;

import android.util.SparseArray;

import com.sky.xposed.wechat.Constant;
import com.sky.xposed.wechat.config.ConfigManager;

/**
 * Created by sky on 18-3-10.
 */

public class ConfigManagerV665 implements ConfigManager {

    private SparseArray<String> mClassName = new SparseArray<>();
    private SparseArray<String> mMethodName = new SparseArray<>();
    private SparseArray<String> mFieldName = new SparseArray<>();

    public ConfigManagerV665() {
        initConfig();
    }

    private void initConfig() {

        mClassName.put(Constant.ClassKey.APPLICATION, "com.tencent.tinker.loader.app.TinkerApplication");
    }

    @Override
    public String getClassName(int key) {
        return mClassName.get(key);
    }

    @Override
    public String getMethodName(int key) {
        return mMethodName.get(key);
    }

    @Override
    public String getFieldName(int key) {
        return mFieldName.get(key);
    }
}
