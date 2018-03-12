package com.sky.xposed.wechat.hook;

import android.app.FragmentManager;
import android.view.Menu;
import android.view.MenuItem;

import com.sky.xposed.wechat.Constant;
import com.sky.xposed.wechat.config.ConfigManager;
import com.sky.xposed.wechat.config.v665.ConfigManagerV665;
import com.sky.xposed.wechat.hook.base.BaseHook;
import com.sky.xposed.wechat.ui.dialog.SettingDialog;
import com.sky.xposed.wechat.util.Alog;
import com.sky.xposed.wechat.util.PackageUitl;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedHelpers;
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

        ConfigManager configManager = loadWechatConfig(packageInfo);

        String applicationClassName = configManager.getClassName(Constant.ClassKey.APPLICATION);
        findClass(applicationClassName);

        findAndHookMethod(applicationClassName, "onCreate", new XC_MethodHook() {

            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                super.afterHookedMethod(param);

                // 初始化
            }
        });

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

    private ConfigManager loadWechatConfig(PackageUitl.SimplePackageInfo packageInfo) {
        return new ConfigManagerV665();
    }
}
