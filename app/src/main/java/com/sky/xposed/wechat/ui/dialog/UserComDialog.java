/*
 * Copyright (c) 2019 The sky Authors.
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

import com.sky.xposed.common.ui.util.ViewUtil;
import com.sky.xposed.common.ui.view.CommonFrameLayout;
import com.sky.xposed.common.ui.view.SwitchItemView;
import com.sky.xposed.wechat.Constant;

/**
 * Created by sky on 2019/3/13.
 */
public class UserComDialog extends CommonDialog {

    private SwitchItemView sivPcAutoLogin;

    @Override
    public void createView(CommonFrameLayout frameView) {

        sivPcAutoLogin = ViewUtil.newSwitchItemView(getContext(), "扫描自动登录");

        frameView.addContent(sivPcAutoLogin);
    }

    @Override
    protected void initView(View view, Bundle args) {
        super.initView(view, args);

        setTitle("常用设置");

        // 绑定事件
        trackBind(sivPcAutoLogin, Integer.toString(Constant.XFlag.AUTO_LOGIN), false, (view1, key, value) -> true);
    }
}
