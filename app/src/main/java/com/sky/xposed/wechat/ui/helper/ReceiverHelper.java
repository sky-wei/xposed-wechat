package com.sky.xposed.wechat.ui.helper;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import com.sky.xposed.wechat.util.Alog;

/**
 * Created by sky on 16-12-2.
 */
public class ReceiverHelper {

    private Context mContext;
    private ReceiverCallback mCallback;
    private IntentFilter mIntentFilter;

    private HelperBroadcastReceiver mHelperBroadcastReceiver;

    public ReceiverHelper(Context context, ReceiverCallback callback, String... actions) {
        this(context, callback, buildIntentFilter(actions));
    }

    public ReceiverHelper(Context context, ReceiverCallback callback, IntentFilter intentFilter) {
        mContext = context.getApplicationContext();
        mCallback = callback;
        mIntentFilter = intentFilter;
    }

    public void registerReceiver() {

        if (mHelperBroadcastReceiver != null) return ;

        try {
            mHelperBroadcastReceiver = new HelperBroadcastReceiver();
            mContext.registerReceiver(mHelperBroadcastReceiver, mIntentFilter);
        } catch (Exception e) {
            Alog.e("Exception", e);
        }
    }

    public void unregisterReceiver() {

        if (mHelperBroadcastReceiver == null) return ;

        try {
            mContext.unregisterReceiver(mHelperBroadcastReceiver);
            mHelperBroadcastReceiver = null;
        } catch (Exception e) {
            Alog.e("Exception", e);
        }
    }

    public static IntentFilter buildIntentFilter(String... actions) {

        IntentFilter filter = new IntentFilter();

        if (actions == null || actions.length <= 0) {
            // 暂无
            return filter;
        }

        for (String action : actions) {
            // 添加Action
            filter.addAction(action);
        }

        return filter;
    }

    public static void sendBroadcastReceiver(Context context, String action) {
        sendBroadcastReceiver(context, new Intent(action));
    }

    public static void sendBroadcastReceiver(Context context, Intent intent) {

        if (context == null || intent == null) return ;

        // 发送广播
        context.sendBroadcast(intent);
    }

    private final class HelperBroadcastReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            // 直接回调出去就可以了
            if (mCallback != null) mCallback.onReceive(intent.getAction(), intent);
        }
    }

    public interface ReceiverCallback {

        void onReceive(String action, Intent intent);
    }
}
