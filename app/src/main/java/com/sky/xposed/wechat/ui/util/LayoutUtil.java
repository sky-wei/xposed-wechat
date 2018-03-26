package com.sky.xposed.wechat.ui.util;

import android.content.Context;
import android.graphics.Color;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.sky.xposed.wechat.util.DisplayUtil;

/**
 * Created by sky on 18-3-11.
 */

public class LayoutUtil {

    public static LinearLayout.LayoutParams newLinearLayoutParams(int width, int height) {
        return new LinearLayout.LayoutParams(width, height);
    }

    public static LinearLayout.LayoutParams newMatchLinearLayoutParams() {
        return newLinearLayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
    }

    public static LinearLayout.LayoutParams newWrapLinearLayoutParams() {
        return newLinearLayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
    }

    public static ViewGroup.LayoutParams newViewGroupParams(int width, int height) {
        return new LinearLayout.LayoutParams(width, height);
    }

    public static ViewGroup.LayoutParams newMatchViewGroupParams() {
        return newViewGroupParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
    }

    public static ViewGroup.LayoutParams newWrapViewGroupParams() {
        return newViewGroupParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    public static FrameLayout.LayoutParams newFrameLayoutParams(int width, int height) {
        return new FrameLayout.LayoutParams(width, height);
    }

    public static FrameLayout.LayoutParams newMatchFrameLayoutParams() {
        return newFrameLayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT);
    }

    public static FrameLayout.LayoutParams newWrapFrameLayoutParams() {
        return newFrameLayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT);
    }

    public static LinearLayout newCommonLayout(Context context) {

        LinearLayout content = new LinearLayout(context);
        content.setLayoutParams(LayoutUtil.newMatchLinearLayoutParams());
        content.setMinimumWidth(DisplayUtil.sp2px(context, 300));
        content.setOrientation(LinearLayout.VERTICAL);
        content.setBackgroundColor(Color.WHITE);

        return content;
    }
}
