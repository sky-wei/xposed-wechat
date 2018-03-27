package com.sky.xposed.wechat.util;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.StateListDrawable;

/**
 * Created by sky on 18-3-27.
 */

public class ViewUtil {

    public static StateListDrawable newBackgroundDrawable() {

        StateListDrawable drawable = new StateListDrawable();

        drawable.addState(new int[] { android.R.attr.state_pressed }, new ColorDrawable(0xffe5e5e5));
        drawable.addState(new int[] {}, new ColorDrawable(Color.WHITE));

        return drawable;
    }
}
