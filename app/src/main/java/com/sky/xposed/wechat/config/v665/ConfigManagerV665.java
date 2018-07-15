/*
 * Copyright (c) 2018 The sky Authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
