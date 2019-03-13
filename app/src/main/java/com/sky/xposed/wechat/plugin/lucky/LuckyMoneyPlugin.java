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

package com.sky.xposed.wechat.plugin.lucky;

import android.app.Activity;

import com.sky.xposed.wechat.Constant;
import com.sky.xposed.wechat.data.model.PluginInfo;
import com.sky.xposed.wechat.plugin.base.BasePlugin;
import com.sky.xposed.wechat.plugin.interfaces.XPluginManager;
import com.sky.xposed.wechat.ui.dialog.LuckyMoneyDialog;

/**
 * Created by sky on 2019/3/13.
 */
public class LuckyMoneyPlugin extends BasePlugin {

    public LuckyMoneyPlugin(XPluginManager pluginManager) {
        super(pluginManager);
    }

    @Override
    public Info getInfo() {
        return new PluginInfo(Constant.Plugin.LUCKY_MONEY, "红包功能");
    }

    @Override
    public void onHandleLoadPackage() {

    }

    @Override
    public void openSettings(Activity activity) {

        LuckyMoneyDialog luckyMoneyDialog = new LuckyMoneyDialog();
        luckyMoneyDialog.show(activity.getFragmentManager(), "luckyMoney");
    }
}
