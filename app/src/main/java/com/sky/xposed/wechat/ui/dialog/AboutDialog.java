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

package com.sky.xposed.wechat.ui.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sky.xposed.common.util.DisplayUtil;
import com.sky.xposed.wechat.BuildConfig;
import com.sky.xposed.wechat.Constant;
import com.sky.xposed.wechat.ui.base.BaseDialog;
import com.sky.xposed.wechat.ui.util.ActivityUtil;

/**
 * Created by sky on 18-3-12.
 */

public class AboutDialog extends BaseDialog implements View.OnClickListener {

    private TextView tvVersion;
    private TextView tvSource;

    @Override
    protected View createView(LayoutInflater inflater, ViewGroup container) {

        int left = DisplayUtil.dip2px(getApplicationContext(), 25);
        int top = DisplayUtil.dip2px(getApplicationContext(), 10);

        LinearLayout linearLayout = new LinearLayout(getApplicationContext());
        linearLayout.setPadding(left, top, left, 0);
        linearLayout.setOrientation(LinearLayout.VERTICAL);

        tvVersion = new TextView(getApplicationContext());
        tvVersion.setSingleLine(true);
        tvVersion.setTextColor(Constant.Color.BLUE);
        tvVersion.setTextSize(14);

        tvSource = new TextView(getApplicationContext());
        tvSource.setTextColor(Constant.Color.BLUE);
        tvSource.setPadding(0, 5, 0, 0);
        tvSource.setSingleLine(true);
        tvSource.setEllipsize(TextUtils.TruncateAt.END);
        tvSource.setTextSize(14);

        linearLayout.addView(tvVersion);
        linearLayout.addView(tvSource);

        return linearLayout;
    }

    @Override
    protected void initView(View view, Bundle args) {

        tvVersion.setText("版本：v" + BuildConfig.VERSION_NAME);
        tvSource.setText(Html.fromHtml("项目：<u>https://github.com/sky-wei/xposed-wechat</u>"));

        tvSource.setOnClickListener(this);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder =
                new AlertDialog.Builder(getContext());

        builder.setTitle("关于");
        builder.setPositiveButton("确定", null);

        return builder.create();
    }

    @Override
    public void onClick(View v) {
        ActivityUtil.openUrl(getContext(), "https://github.com/sky-wei/xposed-wechat");
    }
}
