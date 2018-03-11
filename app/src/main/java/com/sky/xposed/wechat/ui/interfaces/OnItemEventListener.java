package com.sky.xposed.wechat.ui.interfaces;

import android.view.View;

/**
 * Created by starrysky on 16-8-2.
 */
public interface OnItemEventListener {

    void onItemEvent(int event, View view, int position, Object... args);
}
