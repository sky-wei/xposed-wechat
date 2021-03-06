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

package com.sky.xposed.wechat;

/**
 * Created by sky on 18-3-10.
 */

public interface Constant {

    interface Wechat {

        String PACKAGE_NAME = "com.tencent.mm";
    }

    interface ClassKey {

        int APPLICATION = 0x000001;
    }

    interface Event {

        int CLICK = 0x01;
    }

    interface ModuleId {

        int MAIN = 0x0000;

        int OTHER = 0x1001;

        int DEVELOP = 0x1002;

        int ABOUT = 0x1003;
    }

    interface Color {

        int BLUE = 0xFF393A3F;
    }

    interface Strings {

        String TITLE = "WeCat";
    }

    interface Preference {

        String MAIN_MENU = "main.menu";

        String AUTO_LOGIN = "other.autoLogin";

        String ACTIVITY_CYCLE = "develop.activityCycle";

        String ACTIVITY_START = "develop.activityStart";

        String ACTIVITY_RESULT = "develop.activityResult";

        String WECHAT_LOG = "develop.wechatLog";
    }

    interface Process {

        int MAIN = 0x01;

        int EX_DEVICE = 0x02;

        int PUSH = 0x03;

        int SUPPORT = 0x04;

        int TOOLS = 0x05;

        int SANDBOX = 0x06;

        int OTHER = 0x07;
    }

    interface Action {

        String STATUS_CHANGE = BuildConfig.APPLICATION_ID + ".ACTION_STATUS_CHANGE";
    }

    interface Key {

        String DATA = "data";
    }

    interface Name {

        String WE_CAT = "weCat";
    }
}
