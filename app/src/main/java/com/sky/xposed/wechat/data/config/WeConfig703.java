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

package com.sky.xposed.wechat.data.config;

import com.sky.xposed.wechat.data.M;

/**
 * Created by sky on 2019/1/14.
 */
public class WeConfig703 extends WeConfig {

    @Override
    public void loadConfig() {

        /** Class */
        add(M.classz.class_tinker_loader_app_TinkerApplication, "com.tencent.tinker.loader.app.TinkerApplication");
        add(M.classz.class_ui_LauncherUI, "com.tencent.mm.ui.LauncherUI");
        add(M.classz.class_plugin_webwx_ui_ExtDeviceWXLoginUI, "com.tencent.mm.plugin.webwx.ui.ExtDeviceWXLoginUI");

        /** Method */
        add(M.method.method_tinker_loader_app_TinkerApplication_onCreate, "onCreate");
        add(M.method.method_ui_LauncherUI_onCreateOptionsMenu, "onCreateOptionsMenu");
        add(M.method.method_ui_LauncherUI_onOptionsItemSelected, "onOptionsItemSelected");
        add(M.method.method_plugin_webwx_ui_ExtDeviceWXLoginUI_onResume, "onResume");

        /** Field */
    }
}
