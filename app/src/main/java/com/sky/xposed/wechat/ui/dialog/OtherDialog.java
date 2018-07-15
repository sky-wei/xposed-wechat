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

public class OtherDialog extends CommonDialog
        implements TrackViewStatus.StatusChangeListener<Boolean> {

    private SwitchItemView sivPcAutoLogin;

    @Override
    protected void createView(CommonFrameLayout view) {
        super.createView(view);

        sivPcAutoLogin = ViewUtil.newSwitchItemView(getContext(), "扫描自动登录");

        view.addContent(sivPcAutoLogin);
    }

    @Override
    protected void initView(View view, Bundle args) {
        super.initView(view, args);

        setTitle("其他设置");
        getTitleView().showClose();

        // 绑定事件
        trackBind(sivPcAutoLogin, Constant.Preference.AUTO_LOGIN, false, this);
    }

    @Override
    public boolean onStatusChange(View view, String key, Boolean value) {
        // 发送修改值的广播
        ReceiverUtil.postStatusChange(getContext(), key, value);
        return true;
    }
}
