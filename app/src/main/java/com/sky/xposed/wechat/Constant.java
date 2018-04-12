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

    interface EventId {

        int AUTO_LOGIN = 0x10001;

        int ACTIVITY_CYCLE = 0x10002;

        int ACTIVITY_START = 0x10003;

        int ACTIVITY_RESULT = 0x10004;

        int WECHAT_LOG = 0x10005;
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

        String AUTO_LOGIN = "otherModule.autoLogin";

        String ACTIVITY_CYCLE = "developModule.activityCycle";

        String ACTIVITY_START = "developModule.activityStart";

        String ACTIVITY_RESULT = "developModule.activityResult";

        String WECHAT_LOG = "developModule.wechatLog";
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

        String REFRESH_VALUE = BuildConfig.APPLICATION_ID + ".ACTION_REFRESH_VALUE";

        String HOOK_EVENT = BuildConfig.APPLICATION_ID + ".ACTION_HOOK_EVENT";
    }

    interface Key {

        String DATA = "data";
    }

    interface Name {

        String WE_CAT = "weCat";
    }
}
