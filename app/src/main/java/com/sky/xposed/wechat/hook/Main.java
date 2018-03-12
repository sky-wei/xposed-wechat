package com.sky.xposed.wechat.hook;

import android.app.Application;
import android.app.FragmentManager;
import android.content.Context;
import android.view.Menu;
import android.view.MenuItem;

import com.sky.xposed.wechat.Constant;
import com.sky.xposed.wechat.config.ConfigManager;
import com.sky.xposed.wechat.config.v665.ConfigManagerV665;
import com.sky.xposed.wechat.hook.base.BaseHook;
import com.sky.xposed.wechat.hook.module.OtherModule;
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

                final Application application = (Application) param.thisObject;
                final Context context = application.getApplicationContext();

                // 初始化
                HookManager
                        .getInstance()
                        .initialization(context, getLoadPackageParam())
                        .register(new OtherModule());
            }
        });
    }

    private ConfigManager loadWechatConfig(PackageUitl.SimplePackageInfo packageInfo) {
        return new ConfigManagerV665();
    }
}
