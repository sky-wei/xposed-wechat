package com.sky.xposed.wechat.ui.interfaces;

import android.content.SharedPreferences;
import android.view.View;

public interface TrackViewStatus<T> {

    void bind(SharedPreferences preferences, String key, T defValue, StatusChangeListener<T> listener);

    interface StatusChangeListener<T> {

        boolean onStatusChange(View view, String key, T value);
    }
}
