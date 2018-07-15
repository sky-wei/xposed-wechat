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

package com.sky.xposed.wechat.hook.handler;

import android.app.FragmentManager;
import android.view.Menu;
import android.view.MenuItem;

import com.sky.xposed.wechat.Constant;
import com.sky.xposed.wechat.hook.base.BaseHandler;
import com.sky.xposed.wechat.ui.dialog.SettingDialog;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedHelpers;

public class MainHandler extends BaseHandler {

    @Override
    public void onHandleLoadPackage() {
        super.onHandleLoadPackage();

    }

    public static class UIMenuHandler extends BaseHandler {

        @Override
        public void onHandleLoadPackage() {
            super.onHandleLoadPackage();

            findAndHookMethod(
                    "com.tencent.mm.ui.LauncherUI",
                    "onCreateOptionsMenu",
                    Menu.class,
                    new XC_MethodHook() {
                        @Override
                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                            super.afterHookedMethod(param);

                            // 添加菜单入口
                            Menu menu = (Menu) param.args[0];
                            menu.add(99, 200, 1, Constant.Strings.TITLE);
                        }
                    });

            findAndHookMethod(
                    "com.tencent.mm.ui.LauncherUI",
                    "onOptionsItemSelected",
                    MenuItem.class,
                    new XC_MethodHook() {
                        @Override
                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                            super.afterHookedMethod(param);

                            // 添加菜单入口
                            MenuItem menuItem = (MenuItem) param.args[0];
                            FragmentManager fragmentManager = (FragmentManager) XposedHelpers
                                    .callMethod(param.thisObject, "getFragmentManager");

                            if (menuItem.getItemId() == 200) {

                                SettingDialog dialog = new SettingDialog();
                                dialog.show(fragmentManager, "setting");
                            }
                        }
                    });
        }
    }
}
