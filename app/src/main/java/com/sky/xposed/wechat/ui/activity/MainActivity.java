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

package com.sky.xposed.wechat.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.sky.xposed.common.ui.view.ItemMenu;
import com.sky.xposed.common.util.PackageUtil;
import com.sky.xposed.common.util.ToastUtil;
import com.sky.xposed.wechat.BuildConfig;
import com.sky.xposed.wechat.Constant;
import com.sky.xposed.wechat.R;
import com.sky.xposed.wechat.data.VersionManager;
import com.sky.xposed.wechat.plugin.interfaces.XVersionManager;
import com.sky.xposed.wechat.ui.dialog.DonateDialog;
import com.sky.xposed.wechat.ui.dialog.PluginSettingsDialog;
import com.sky.xposed.wechat.ui.util.ActivityUtil;
import com.sky.xposed.wechat.ui.util.DialogUtil;

public class MainActivity extends AppCompatActivity {

    private ItemMenu imVersion;
    private ItemMenu imWeiShiVersion;
    private TextView tvSupportVersion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 初始化
        ToastUtil.getInstance().init(getApplicationContext());

        XVersionManager versionManager = new VersionManager
                .Build(getApplicationContext())
                .build();

        imVersion = findViewById(R.id.im_version);
        imWeiShiVersion = findViewById(R.id.im_wechat_version);
        tvSupportVersion = findViewById(R.id.tv_support_version);

        imVersion.setDesc("v" + BuildConfig.VERSION_NAME);
        imWeiShiVersion.setDesc(getWechatVersionName());
        tvSupportVersion.setText("支持的版本: " + versionManager.getSupportVersion());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (BuildConfig.DEBUG) {
            getMenuInflater().inflate(R.menu.activity_main_menu, menu);
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.menu_settings) {
            // 显示
            PluginSettingsDialog dialog = new PluginSettingsDialog();
            dialog.show(getFragmentManager(), "setting");
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.im_download:
                // 下载
                ActivityUtil.openUrl(this, "http://repo.xposed.info/module/com.sky.xposed.wechat");
                break;
            case R.id.im_source:
                // 源地址
                ActivityUtil.openUrl(this, "https://github.com/sky-wei/xposed-wechat");
                break;
            case R.id.im_donate:
                // 捐赠
                DonateDialog donateDialog = new DonateDialog();
                donateDialog.show(getFragmentManager(), "donate");
                break;
            case R.id.im_about:
                // 关于
                DialogUtil.showAboutDialog(this);
                break;
        }
    }

    private String getWechatVersionName() {

        // 获取微视版本名
        PackageUtil.SimplePackageInfo info = PackageUtil
                .getSimplePackageInfo(this, Constant.WeChat.PACKAGE_NAME);

        return info == null ? "Unknown" : "v" + info.getVersionName();
    }
}
