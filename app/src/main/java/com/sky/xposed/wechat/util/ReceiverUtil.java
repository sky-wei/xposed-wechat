package com.sky.xposed.wechat.util;

import android.content.Context;
import android.content.Intent;

import com.sky.xposed.wechat.Constant;
import com.sky.xposed.wechat.data.model.Pair;
import com.sky.xposed.wechat.ui.helper.ReceiverHelper;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

/**
 * Created by sky on 18-3-27.
 */

public class ReceiverUtil {

    public static void postStatusChange(Context context, String key, Object value) {
        postStatusChange(context, new Pair<>(key, value));
    }

    public static void postStatusChange(Context context, Pair<String, Object>... pairs) {

        if (context == null || pairs == null) return;

        List<Pair<String, Object>> data = Arrays.asList(pairs);

        Intent intent = new Intent(Constant.Action.STATUS_CHANGE);
        intent.putExtra(Constant.Key.DATA, (Serializable) data);

        // 发送广播
        ReceiverHelper.sendBroadcastReceiver(context, intent);
    }
}
