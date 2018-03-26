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
    }

    interface ModuleId {

        int MAIN = 0x0000;

        int OTHER = 0x1001;

        int DEVELOP = 0x1002;

        int ABOUT = 0x1003;

        int AUTO_LOGIN = 0x0004;
    }

    interface Color {

        int BLUE = 0XFF66CCFF;
    }

    interface Strings {

        String TITLE = "WeBlue";
    }

    interface Preference {

        String AUTO_LOGIN = "otherModule.autoLogin";
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

        String HOOK_EVENT = "com.sky.xposed.wechat.ACTION_HOOK_EVENT";
    }

    interface Key {

        String DATA = "data";
    }
}
