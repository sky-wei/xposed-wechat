package com.sky.xposed.wechat.ui.dialog;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sky.xposed.wechat.Constant;
import com.sky.xposed.wechat.hook.event.MultiProEvent;
import com.sky.xposed.wechat.ui.base.BaseDialogFragment;
import com.sky.xposed.wechat.ui.view.CategoryItemView;
import com.sky.xposed.wechat.ui.view.CommonFrameLayout;
import com.sky.xposed.wechat.ui.view.DialogTitle;
import com.sky.xposed.wechat.ui.view.SwitchItemView;
import com.sky.xposed.wechat.util.EventUtil;

/**
 * Created by sky on 18-3-12.
 */

public class DevelopDialog extends BaseDialogFragment implements
        DialogTitle.OnTitleEventListener, SwitchItemView.OnCheckedChangeListener {

    private DialogTitle mDialogTitle;
    private CommonFrameLayout mCommonFrameLayout;

    private SwitchItemView sivWechatLog;
    private SwitchItemView sivActivity;
    private SwitchItemView sivStartActivity;
    private SwitchItemView sivActivityResult;

    @Override
    protected View createView(LayoutInflater inflater, ViewGroup container) {

        mCommonFrameLayout = new CommonFrameLayout(getContext());
        mDialogTitle = mCommonFrameLayout.getDialogTitle();

        sivWechatLog = new SwitchItemView(getContext());
        sivWechatLog.setName("打印微信日志");

        mCommonFrameLayout.addContent(sivWechatLog);

        CategoryItemView categoryItemView = new CategoryItemView(getContext());
        categoryItemView.setName("Activity");
        mCommonFrameLayout.addContent(categoryItemView);

        sivActivity = new SwitchItemView(getContext());
        sivActivity.setName("打印Activity生命周期");

        sivStartActivity = new SwitchItemView(getContext());
        sivStartActivity.setName("打印Activity启动参数");

        sivActivityResult = new SwitchItemView(getContext());
        sivActivityResult.setName("打印Activity返回结果");

        mCommonFrameLayout.addContent(sivActivity);
        mCommonFrameLayout.addContent(sivStartActivity);
        mCommonFrameLayout.addContent(sivActivityResult);

        return mCommonFrameLayout;
    }

    @Override
    protected void initView(View view, Bundle args) {

        mDialogTitle.setTitle("开发设置");
        mDialogTitle.showClose();
        mDialogTitle.setOnTitleEventListener(this);

        sivWechatLog.setOnCheckedChangeListener(this);
        sivActivity.setOnCheckedChangeListener(this);
        sivStartActivity.setOnCheckedChangeListener(this);
        sivActivityResult.setOnCheckedChangeListener(this);

        // 恢复状态
        sivWechatLog.setChecked(
                getBooleanValue(Constant.Preference.WECHAT_LOG));
        sivActivity.setChecked(
                getBooleanValue(Constant.Preference.ACTIVITY_CYCLE));
        sivStartActivity.setChecked(
                getBooleanValue(Constant.Preference.ACTIVITY_START));
        sivActivityResult.setChecked(
                getBooleanValue(Constant.Preference.ACTIVITY_RESULT));
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

        if (view == sivWechatLog) {
            // 保存状态值
            putBooleanValue(Constant.Preference.WECHAT_LOG, isChecked);
            EventUtil.postMultiProgressEvent(getContext(), new MultiProEvent(Constant.EventId.WECHAT_LOG, isChecked));
        } else if (view == sivActivity) {
            // 保存状态值
            putBooleanValue(Constant.Preference.ACTIVITY_CYCLE, isChecked);
            EventUtil.postBackgroundEvent(Constant.EventId.ACTIVITY_CYCLE, isChecked);
        } else if (view == sivStartActivity) {
            // 保存状态值
            putBooleanValue(Constant.Preference.ACTIVITY_START, isChecked);
            EventUtil.postBackgroundEvent(Constant.EventId.ACTIVITY_START, isChecked);
        } else if (view == sivActivityResult) {
            // 保存状态值
            putBooleanValue(Constant.Preference.ACTIVITY_RESULT, isChecked);
            EventUtil.postBackgroundEvent(Constant.EventId.ACTIVITY_RESULT, isChecked);
        }
    }
}
