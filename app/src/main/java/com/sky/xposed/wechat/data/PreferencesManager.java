package com.sky.xposed.wechat.data;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by sky on 18-3-12.
 */

public class PreferencesManager {

    private Context mContext;
    private SharedPreferences mSharedPreferences;

    private PreferencesManager(Context context) {
        mContext = context;
        mSharedPreferences = mContext.getSharedPreferences("weblue", Context.MODE_PRIVATE);
    }



    public void reload() {

    }
}
