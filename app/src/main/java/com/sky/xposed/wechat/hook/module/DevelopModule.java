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
import com.sky.xposed.wechat.hook.handler.LogHandler;

/**
 * Created by sky on 18-3-13.
 */

public class DevelopModule extends BaseModule {

    @Override
    public int getId() {
        return Constant.ModuleId.DEVELOP;
    }

    @Override
    public String getName() {
        return "开发调试";
    }

    @Override
    public void onHandleLoadPackage() {

        register(Constant.Preference.ACTIVITY_CYCLE,
                new LogHandler.ActivityCycleHandler());

        register(Constant.Preference.ACTIVITY_START,
                new LogHandler.ActivityStartHandler());

        register(Constant.Preference.ACTIVITY_RESULT,
                new LogHandler.ActivityResultHandler());
    }
}
