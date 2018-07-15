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

package com.sky.xposed.wechat;

import android.app.Application;
import android.content.Context;

import com.sky.android.common.util.Alog;
import com.sky.android.common.util.PackageUitl;
import com.sky.xposed.wechat.Constant;
import com.sky.xposed.wechat.config.ConfigManager;
import com.sky.xposed.wechat.config.v665.ConfigManagerV665;
import com.sky.xposed.wechat.hook.HookManager;
import com.sky.xposed.wechat.hook.base.BaseHook;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

/**
 * Created by sky on 18-3-10.
 */

public class Main extends BaseHook {

    @Override
    public void onHandleLoadPackage(XC_LoadPackage.LoadPackageParam param) {

        String packageName = param.packageName;

        if (Constant.Wechat.PACKAGE_NAME.equals(packageName)) {

            // 处理微信
            onHookWechat(PackageUitl.getSimplePackageInfo(
                    getSystemContext(), packageName), param);
        }
    }

    private void onHookWechat(final PackageUitl.SimplePackageInfo packageInfo, XC_LoadPackage.LoadPackageParam param) {

        final ConfigManager configManager = loadWechatConfig(packageInfo);

        String applicationClassName = configManager.getClassName(Constant.ClassKey.APPLICATION);
        findClass(applicationClassName);

        findAndHookMethod(applicationClassName, "onCreate", new XC_MethodHook() {

            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                super.afterHookedMethod(param);

                long startTime = System.currentTimeMillis();

                final Application application = (Application) param.thisObject;
                final Context context = application.getApplicationContext();

                // 初始化
                HookManager
                        .getInstance()
                        .initialization(context, getLoadPackageParam(), configManager)
                        .onHandleLoadPackage();

                Alog.d("初始化Application完成,耗时：" + (System.currentTimeMillis() - startTime));
            }
        });
    }

    private ConfigManager loadWechatConfig(PackageUitl.SimplePackageInfo packageInfo) {
        return new ConfigManagerV665();
    }
}
