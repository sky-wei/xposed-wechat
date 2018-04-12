package com.sky.xposed.wechat.ui.interfaces;

import android.content.SharedPreferences;

public interface BindPreferences<T> {

    void bind(SharedPreferences preferences, String key, T defValue, ValueChangeListener<T> listener);

    interface ValueChangeListener<T> {

        void onValueChange(String key, T value);
    }
}
