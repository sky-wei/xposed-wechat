package com.sky.xposed.wechat.ui.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sky.xposed.wechat.BuildConfig;
import com.sky.xposed.wechat.Constant;
import com.sky.xposed.wechat.ui.base.BaseDialogFragment;
import com.sky.xposed.wechat.ui.util.ActivityUtil;
import com.sky.xposed.wechat.util.DisplayUtil;

/**
 * Created by sky on 18-3-12.
 */

public class AboutDialog extends BaseDialogFragment implements View.OnClickListener {

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

        tvVersion.setText("版本：V" + BuildConfig.VERSION_NAME);
        tvSource.setText(Html.fromHtml("项目：<u>https://github.com/jingcai-wei/xposed-wechat</u>"));

        tvSource.setOnClickListener(this);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder =
                new AlertDialog.Builder(getActivity(), getTheme());

        builder.setTitle("关于");
        builder.setView(createDialogView());
        builder.setPositiveButton("确定", null);

        return builder.create();
    }

    @Override
    public void onClick(View v) {

        Uri uri = Uri.parse("https://github.com/jingcai-wei/xposed-wechat");
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);

        ActivityUtil.startActivity(getApplicationContext(), intent);
    }
}
