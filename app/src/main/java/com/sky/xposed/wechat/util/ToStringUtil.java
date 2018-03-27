package com.sky.xposed.wechat.util;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Created by sky on 18-3-27.
 */

public class ToStringUtil {

    public static void toString(Object object) {

        if (object == null) {
            Alog.d("打印的对象为空");
            return;
        }

        // 直接输出
        Alog.d(ToStringBuilder.reflectionToString(object));
    }

    public static void toString(String tag, Object object) {

        if (object == null) {
            Alog.d("$tag 打印的对象为空");
            return;
        }

        // 直接输出
        Alog.d(tag + " " + ToStringBuilder.reflectionToString(object));
    }
}
