package com.sky.xposed.wechat.util;

import de.robv.android.xposed.XposedBridge;

/**
 * Created by sky on 18-3-10.
 */

public class Alog {

    private static final String TAG = "Xposed";
    public static boolean sDEBUG = true;

    public static void d(String msg) {
        d(TAG, msg);
    }

    public static void d(String tag, String msg) {
        if (sDEBUG) XposedBridge.log(msg);
    }

    public static void d(String msg, Throwable tr) {
        d(TAG, msg, tr);
    }

    public static void d(String tag, String msg, Throwable tr) {
        if (sDEBUG) {
            XposedBridge.log(msg);
            XposedBridge.log(tr);
        }
    }

    public static void e(String msg) {
        e(TAG, msg);
    }

    public static void e(String tag, String msg) {
        if (sDEBUG) XposedBridge.log(msg);
    }

    public static void e(String msg, Throwable tr) {
        e(TAG, msg, tr);
    }

    public static void e(String tag, String msg, Throwable tr) {
        if (sDEBUG) {
            XposedBridge.log(msg);
            XposedBridge.log(tr);
        }
    }
}
