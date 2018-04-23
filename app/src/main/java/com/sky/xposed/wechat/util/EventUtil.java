package com.sky.xposed.wechat.util;

import android.content.Context;
import android.content.Intent;

import com.sky.xposed.wechat.Constant;
import com.sky.xposed.wechat.data.model.Pair;
import com.sky.xposed.wechat.hook.base.BaseEvent;
import com.sky.xposed.wechat.hook.event.BackgroundEvent;
import com.sky.xposed.wechat.hook.event.MultiProEvent;
import com.sky.xposed.wechat.ui.helper.ReceiverHelper;

import org.greenrobot.eventbus.EventBus;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

/**
 * Created by sky on 18-3-27.
 */

public class EventUtil {

    public static void postEvent(BaseEvent event) {

        if (event == null) return ;

        EventBus.getDefault().post(event);
    }

    public static void postBackgroundEvent(int eventId, boolean booleanValue) {
        postEvent(new BackgroundEvent(eventId, booleanValue));
    }

    public static void postMultiProgressEvent(Context context, MultiProEvent event) {

        if (context == null || event == null) return ;

        try {
            // 添加发送的数据
            Intent intent = new Intent(Constant.Action.HOOK_EVENT);
            intent.putExtra(Constant.Key.DATA, event);

            // 发送广播
            context.sendBroadcast(intent);
        } catch (Throwable tr) {
            Alog.e("发送广播异常", tr);
        }
    }

    public static void postModifyValue(Context context, String key, Object value) {
        postModifyValue(context, new Pair<>(key, value));
    }

    public static void postModifyValue(Context context, Pair<String, Object>... pairs) {

        if (context == null || pairs == null) return;

        List<Pair<String, Object>> data = Arrays.asList(pairs);

        Intent intent = new Intent(Constant.Action.REFRESH_VALUE);
        intent.putExtra(Constant.Key.DATA, (Serializable) data);

        // 发送广播
        ReceiverHelper.sendBroadcastReceiver(context, intent);
    }
}
