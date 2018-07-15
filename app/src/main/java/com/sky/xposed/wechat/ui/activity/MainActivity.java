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

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.sky.android.common.util.AToast;
import com.sky.android.common.util.PackageUitl;
import com.sky.xposed.wechat.BuildConfig;
import com.sky.xposed.wechat.Constant;
import com.sky.xposed.wechat.R;
import com.sky.xposed.wechat.ui.dialog.SettingDialog;
import com.sky.xposed.wechat.ui.view.ItemMenu;
import com.sky.xposed.wechat.util.CommUtil;

public class MainActivity extends AppCompatActivity {

    private ItemMenu imVersion;
    private ItemMenu imWeiShiVersion;
    private TextView tvSupportVersion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 初始化
        AToast.getInstance().init(getApplicationContext());

        imVersion = findViewById(R.id.im_version);
        imWeiShiVersion = findViewById(R.id.im_wechat_version);
        tvSupportVersion = findViewById(R.id.tv_support_version);

        imVersion.setDesc("v" + BuildConfig.VERSION_NAME);
        imWeiShiVersion.setDesc(getWechatVersionName());
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
            SettingDialog dialog = new SettingDialog();
            dialog.show(getFragmentManager(), "setting");
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.im_download:
                // 下载
                openUrl("http://repo.xposed.info/module/com.sky.xposed.wechat");
                break;
            case R.id.im_source:
                // 源地址
                openUrl("https://github.com/sky-wei/xposed-wechat");
                break;
            case R.id.im_donate:
                // 捐赠
//                DonateDialog donateDialog = new DonateDialog();
//                donateDialog.show(getFragmentManager(), "donate");
                break;
            case R.id.im_about:
                CommUtil.showAboutDialog(this);
                break;
        }
    }

    private void openUrl(String url) {

        try {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(url));

            // 调用系统浏览器打开
            startActivity(intent);
        } catch (Throwable tr) {
            AToast.show("打开浏览器异常");
        }
    }

    private String getWechatVersionName() {

        // 获取微视版本名
        PackageUitl.SimplePackageInfo info = PackageUitl
                .getSimplePackageInfo(this, Constant.Wechat.PACKAGE_NAME);

        return info == null ? "Unknown" : "v" + info.getVersionName();
    }
}
