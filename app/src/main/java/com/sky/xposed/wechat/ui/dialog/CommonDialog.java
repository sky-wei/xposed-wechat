package com.sky.xposed.wechat.ui.dialog;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sky.xposed.wechat.ui.base.BaseDialog;
import com.sky.xposed.wechat.ui.view.CommonFrameLayout;
import com.sky.xposed.wechat.ui.view.TitleView;

public class CommonDialog extends BaseDialog implements TitleView.OnTitleEventListener {

    private TitleView mTitleView;
    private CommonFrameLayout mCommonFrameLayout;

    @Override
    protected View createView(LayoutInflater inflater, ViewGroup container) {

        // 初始化View
        mCommonFrameLayout = new CommonFrameLayout(getContext());
        mTitleView = mCommonFrameLayout.getDialogTitle();

        // 创建相关的View
        createView(mCommonFrameLayout);

        return mCommonFrameLayout;
    }

    public TitleView getTitleView() {
        return mTitleView;
    }

    public CommonFrameLayout getCommonFrameLayout() {
        return mCommonFrameLayout;
    }

    protected void createView(CommonFrameLayout view) {

    }

    @Override
    protected void initView(View view, Bundle args) {
        // 设置监听
        mTitleView.setOnTitleEventListener(this);
    }

    @Override
    public void onCloseEvent(View view) {
        // 关闭界面
        dismiss();
    }

    @Override
    public void onMoreEvent(View view) {
    }

    public void setTitle(String title) {
        mTitleView.setTitle(title);
    }
}
