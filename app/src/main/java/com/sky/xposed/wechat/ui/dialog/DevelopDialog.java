package com.sky.xposed.wechat.ui.dialog;

import android.os.Bundle;
import android.view.View;

import com.sky.xposed.wechat.Constant;
import com.sky.xposed.wechat.ui.interfaces.TrackViewStatus;
import com.sky.xposed.wechat.ui.util.ViewUtil;
import com.sky.xposed.wechat.ui.view.CommonFrameLayout;
import com.sky.xposed.wechat.ui.view.SwitchItemView;
import com.sky.xposed.wechat.util.ReceiverUtil;

/**
 * Created by sky on 18-3-12.
 */

public class DevelopDialog extends CommonDialog
        implements TrackViewStatus.StatusChangeListener<Boolean> {

    private SwitchItemView sivWechatLog;
    private SwitchItemView sivActivity;
    private SwitchItemView sivStartActivity;
    private SwitchItemView sivActivityResult;

    @Override
    protected void createView(CommonFrameLayout view) {
        super.createView(view);

        sivWechatLog = ViewUtil.newSwitchItemView(getContext(), "打印微信日志");

        view.addContent(sivWechatLog);

        view.addContent(
                ViewUtil.newCategoryItemView(getContext(), "Activity"));
        sivActivity = ViewUtil.newSwitchItemView(getContext(), "打印Activity生命周期");
        sivStartActivity = ViewUtil.newSwitchItemView(getContext(), "打印Activity启动参数");
        sivActivityResult = ViewUtil.newSwitchItemView(getContext(), "打印Activity返回结果");

        view.addContent(sivActivity, true);
        view.addContent(sivStartActivity, true);
        view.addContent(sivActivityResult);
    }

    @Override
    protected void initView(View view, Bundle args) {
        super.initView(view, args);

        setTitle("开发设置");
        getTitleView().showClose();

        // 绑定事件
        trackBind(sivWechatLog, Constant.Preference.WECHAT_LOG, false, this);
        trackBind(sivActivity, Constant.Preference.ACTIVITY_CYCLE, false, this);
        trackBind(sivStartActivity, Constant.Preference.ACTIVITY_START, false, this);
        trackBind(sivActivityResult, Constant.Preference.ACTIVITY_RESULT, false, this);
    }

    @Override
    public boolean onStatusChange(View view, String key, Boolean value) {
        // 发送修改值的广播
        ReceiverUtil.postStatusChange(getContext(), key, value);
        return true;
    }
}
