package com.sky.xposed.wechat.ui.dialog;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sky.xposed.wechat.Constant;
import com.sky.xposed.wechat.ui.base.BaseDialogFragment;
import com.sky.xposed.wechat.ui.interfaces.TrackViewStatus;
import com.sky.xposed.wechat.ui.view.CommonFrameLayout;
import com.sky.xposed.wechat.ui.view.DialogTitle;
import com.sky.xposed.wechat.ui.view.SwitchItemView;
import com.sky.xposed.wechat.util.EventUtil;

/**
 * Created by sky on 18-3-12.
 */

public class OtherDialog extends BaseDialogFragment
        implements DialogTitle.OnTitleEventListener, TrackViewStatus.StatusChangeListener<Boolean> {

    private DialogTitle mDialogTitle;
    private CommonFrameLayout mCommonFrameLayout;

    private SwitchItemView sivPcAutoLogin;


    @Override
    protected View createView(LayoutInflater inflater, ViewGroup container) {

        mCommonFrameLayout = new CommonFrameLayout(getContext());
        mDialogTitle = mCommonFrameLayout.getDialogTitle();

        sivPcAutoLogin = new SwitchItemView(getContext());
        sivPcAutoLogin.setName("扫描自动登录");

        mCommonFrameLayout.addContent(sivPcAutoLogin);

        return mCommonFrameLayout;
    }

    @Override
    protected void initView(View view, Bundle args) {

        mDialogTitle.setTitle("其他设置");
        mDialogTitle.showClose();
        mDialogTitle.setOnTitleEventListener(this);

        trackBind(sivPcAutoLogin, Constant.Preference.AUTO_LOGIN, false, this);
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
    public boolean onStatusChange(View view, String key, Boolean value) {
        EventUtil.postBackgroundEvent(Constant.EventId.AUTO_LOGIN, value);
        return false;
    }
}
