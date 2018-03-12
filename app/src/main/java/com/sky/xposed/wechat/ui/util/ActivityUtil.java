package com.sky.xposed.wechat.ui.util;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;

import com.sky.xposed.wechat.util.Alog;

/**
 * Created by sky on 18-3-12.
 */

public class ActivityUtil {

    public static boolean startActivity(Context context, Intent intent) {

        try {
            // 获取目标包名
            String packageName = intent.getPackage();

            // 设置启动参数
            if (!TextUtils.isEmpty(packageName)
                    && !TextUtils.equals(packageName, context.getPackageName())) {
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            }

            // 启动Activity
            context.startActivity(intent);
            return true;
        } catch (Exception e) {
            Alog.e("启动Activity异常", e);
        }
        return false;
    }
}
