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

package com.sky.xposed.wechat.plugin.common;

import android.app.Activity;
import android.widget.Button;

import com.sky.xposed.wechat.Constant;
import com.sky.xposed.wechat.data.M;
import com.sky.xposed.wechat.data.model.PluginInfo;
import com.sky.xposed.wechat.plugin.base.BasePlugin;
import com.sky.xposed.wechat.plugin.interfaces.XPluginManager;
import com.sky.xposed.wechat.ui.dialog.UserComDialog;
import com.sky.xposed.wechat.util.FindUtil;

import java.lang.reflect.Field;

import de.robv.android.xposed.XposedHelpers;

/**
 * Created by sky on 2019/3/12.
 */
public class UserComPlugin extends BasePlugin {

    public UserComPlugin(XPluginManager pluginManager) {
        super(pluginManager);
    }

    @Override
    public Info getInfo() {
        return new PluginInfo(Constant.Plugin.COMMON, "常用功能");
    }

    @Override
    public void onHandleLoadPackage() {

        findMethod(
                M.classz.class_plugin_webwx_ui_ExtDeviceWXLoginUI,
                M.method.method_plugin_webwx_ui_ExtDeviceWXLoginUI_onResume)
                .after(param -> {

                    if (!isEnable(Constant.XFlag.AUTO_LOGIN, false)) return;

                    final Object thisObject = param.thisObject;

                    Field fieldButton = FindUtil.firstOrNull(
                            thisObject.getClass().getDeclaredFields(), field -> field.getType() == Button.class);

                    if (fieldButton != null) {

                        // 获取登录按钮
                        Button loginButton = (Button) XposedHelpers
                                .getObjectField(thisObject, fieldButton.getName());

                        if (loginButton.isEnabled()) {
                            // 点击登录
                            loginButton.performClick();
                        }
                    }
                });
    }

    @Override
    public void openSettings(Activity activity) {

        UserComDialog dialog = new UserComDialog();
        dialog.show(activity.getFragmentManager(), "userCommon");
    }
}
