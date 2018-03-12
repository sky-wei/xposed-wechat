package com.sky.xposed.wechat.ui.dialog;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sky.xposed.wechat.Constant;
import com.sky.xposed.wechat.ui.base.BaseDialogFragment;
import com.sky.xposed.wechat.ui.view.CommonFrameLayout;
import com.sky.xposed.wechat.ui.view.DialogTitle;
import com.sky.xposed.wechat.ui.view.SwitchItemView;

/**
 * Created by sky on 18-3-12.
 */

public class DevelopDialog extends BaseDialogFragment implements DialogTitle.OnTitleEventListener {

    private DialogTitle mDialogTitle;
    private CommonFrameLayout mCommonFrameLayout;

    private SwitchItemView sivLog;

    @Override
    protected View createView(LayoutInflater inflater, ViewGroup container) {

        mCommonFrameLayout = new CommonFrameLayout(getContext());
        mDialogTitle = mCommonFrameLayout.getDialogTitle();

        sivLog = new SwitchItemView(getContext());
        sivLog.setName("调试日志开关");

        mCommonFrameLayout.addContent(sivLog);

        return mCommonFrameLayout;
    }

    @Override
    protected void initView(View view, Bundle args) {

        mDialogTitle.setTitle("开发设置");
        mDialogTitle.showClose();
        mDialogTitle.setOnTitleEventListener(this);
    }

    @Override
    public void onCloseEvent(View view) {
        // 关闭界面
        dismiss();
    }

    @Override
    public void onMoreEvent(View view) {
    }
}
