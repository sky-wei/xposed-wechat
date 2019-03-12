/*
 * Copyright (c) 2019 The sky Authors.
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

package com.sky.xposed.wechat.data;

import android.content.Context;

import com.sky.xposed.wechat.plugin.interfaces.XConfigManager;
import com.sky.xposed.wechat.plugin.interfaces.XPluginManager;

/**
 * Created by sky on 2018/12/24.
 */
public class ConfigManager implements XConfigManager {

    private Context mContext;

    private ConfigManager(Build build) {
        mContext = build.mXPluginManager.getContext();
    }

    public static class Build {

        private XPluginManager mXPluginManager;

        public Build(XPluginManager xPluginManager) {
            mXPluginManager = xPluginManager;
        }

        public XConfigManager build() {
            return new ConfigManager(this);
        }
    }
}
