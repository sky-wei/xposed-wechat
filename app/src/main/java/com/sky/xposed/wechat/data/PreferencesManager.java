package com.sky.xposed.wechat.data;

import android.content.Context;
import android.content.SharedPreferences;

import de.robv.android.xposed.XSharedPreferences;

/**
 * Created by sky on 18-3-12.
 */

public class PreferencesManager {

    public static final String WE_BLUE = "weblue";

    private Context mContext;
    private SharedPreferences mPreferences;
//    private XSharedPreferences mXPreferences;

    public PreferencesManager(Context context) {
        mContext = context;
        mPreferences = mContext.getSharedPreferences(WE_BLUE, Context.MODE_PRIVATE);
//        mXPreferences = new XSharedPreferences(context.getPackageName(), WE_BLUE);
    }

    public void reload() {
//        mXPreferences.reload();
    }

    public boolean getBoolean(String key, boolean defValue) {
        return mPreferences.getBoolean(key, defValue);
    }

    public long getLong(String key, long defValue) {
        return mPreferences.getLong(key, defValue);
    }

    public String getString(String key, String defValue) {
        return mPreferences.getString(key, defValue);
    }

    public void putBoolean(String key, boolean value) {
        mPreferences.edit().putBoolean(key, value).apply();
    }

    public void putString(String key, String value) {
        mPreferences.edit().putString(key, value).apply();
    }

    public void putLong(String key, long value) {
        mPreferences.edit().putLong(key, value).apply();
    }

    public void remove(String key) {
        mPreferences.edit().remove(key).apply();
    }

    public void clear() {
        mPreferences.edit().clear().apply();
    }
}
