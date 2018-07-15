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

package com.sky.xposed.wechat.hook.module;

import com.sky.xposed.wechat.Constant;
import com.sky.xposed.wechat.hook.base.BaseModule;
import com.sky.xposed.wechat.hook.handler.OtherHandler;

/**
 * Created by sky on 18-3-12.
 */

public class OtherModule extends BaseModule {

    @Override
    public int getId() {
        return Constant.ModuleId.OTHER;
    }

    @Override
    public String getName() {
        return "其他功能";
    }

    @Override
    public void onHandleLoadPackage() {

        if (isMainProcess()) {
            // 自动登录处理
            register(Constant.Preference.AUTO_LOGIN,
                    new OtherHandler.AutoLoginHandler());
        }
    }
}
