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

        int OTHER = 0x0001;

        int DEVELOP = 0x0002;

        int ABOUT = 0x0003;

        int AUTO_LOGIN = 0x0004;
    }

    interface Color {

        int BLUE = 0XFF66CCFF;
    }

    interface Strings {

        String TITLE = "WeBlue";
    }
}
