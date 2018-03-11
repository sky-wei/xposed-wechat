package com.sky.xposed.wechat.util;

import android.content.Context;

/**
 * Created by starrysky on 14-12-16.
 */
public class DisplayUtil {

    public static float getDensity(Context context) {
        return context.getResources().getDisplayMetrics().density;
    }

    public static float getScaledDensity(Context context) {
        return context.getResources().getDisplayMetrics().scaledDensity;
    }

    public static int dip2px(Context context, float dipValue){
        return dip2px(dipValue, getDensity(context));
    }

    public static int px2dip(Context context, float pxValue){
        return px2dip(pxValue, getDensity(context));
    }

    /**
     * 将px值转换为sp值，保证文字大小不变
     * @param context
     * @param pxValue
     *            （DisplayMetrics类中属性scaledDensity）
     * @return
     */
    public static int px2sp(Context context, float pxValue) {
        return px2sp(pxValue, getScaledDensity(context));
    }

    /**
     * 将sp值转换为px值，保证文字大小不变
     * @param context
     * @param spValue
     *            （DisplayMetrics类中属性scaledDensity）
     * @return
     */
    public static int sp2px(Context context, float spValue) {
        return sp2px(spValue, getScaledDensity(context));
    }

    /**
     * 将px值转换为dip或dp值，保证尺寸大小不变
     *
     * @param pxValue
     * @param scale（DisplayMetrics类中属性density）
     * @return
     */
    public static int px2dip(float pxValue, float scale) {
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * 将dip或dp值转换为px值，保证尺寸大小不变
     *
     * @param dipValue
     * @param scale（DisplayMetrics类中属性density）
     * @return
     */
    public static int dip2px(float dipValue, float scale) {
        return (int) (dipValue * scale + 0.5f);
    }

    /**
     * 将px值转换为sp值，保证文字大小不变
     *
     * @param pxValue
     * @param fontScale（DisplayMetrics类中属性scaledDensity）
     * @return
     */
    public static int px2sp(float pxValue, float fontScale) {
        return (int) (pxValue / fontScale + 0.5f);
    }

    /**
     * 将sp值转换为px值，保证文字大小不变
     *
     * @param spValue
     * @param fontScale（DisplayMetrics类中属性scaledDensity）
     * @return
     */
    public static int sp2px(float spValue, float fontScale) {
        return (int) (spValue * fontScale + 0.5f);
    }
}