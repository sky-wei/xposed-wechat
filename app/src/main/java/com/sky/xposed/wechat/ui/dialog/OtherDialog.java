package com.sky.xposed.wechat.ui.dialog;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sky.xposed.wechat.Constant;
import com.sky.xposed.wechat.hook.HookManager;
import com.sky.xposed.wechat.hook.module.HookModule;
import com.sky.xposed.wechat.hook.module.OtherModule;
import com.sky.xposed.wechat.ui.base.BaseDialogFragment;
import com.sky.xposed.wechat.ui.view.CommonFrameLayout;
import com.sky.xposed.wechat.ui.view.DialogTitle;
import com.sky.xposed.wechat.ui.view.SwitchItemView;

/**
 * Created by sky on 18-3-12.
 */

public class OtherDialog extends BaseDialogFragment
        implements DialogTitle.OnTitleEventListener, SwitchItemView.OnCheckedChangeListener {

    private DialogTitle mDialogTitle;
    private CommonFrameLayout mCommonFrameLayout;

    private SwitchItemView sivPcAutoLogin;

    @Override
    protected View createView(LayoutInflater inflater, ViewGroup container) {

        mCommonFrameLayout = new CommonFrameLayout(getContext());
        mDialogTitle = mCommonFrameLayout.getDialogTitle();

        sivPcAutoLogin = new SwitchItemView(getContext());
        sivPcAutoLogin.setName("扫描自动登录");
        sivPcAutoLogin.setOnCheckedChangeListener(this);

        mCommonFrameLayout.addContent(sivPcAutoLogin);

        return mCommonFrameLayout;
    }

    @Override
    protected void initView(View view, Bundle args) {

        mDialogTitle.setTitle("其他设置");
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

    @Override
    public void onCheckedChanged(View view, boolean isChecked) {

        HookModule module = HookManager
                .getInstance().get(Constant.ModuleId.OTHER);

        if (isChecked) {
            // 添加模块
            module.add(Constant.ModuleId.AUTO_LOGIN);
        } else {
            // 移除模块
            module.remove(Constant.ModuleId.AUTO_LOGIN);
        }
    }
}
