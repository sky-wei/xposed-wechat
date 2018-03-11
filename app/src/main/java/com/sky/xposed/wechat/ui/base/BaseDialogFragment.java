package com.sky.xposed.wechat.ui.base;

import android.app.DialogFragment;
import android.content.Context;

/**
 * Created by sky on 18-3-11.
 */

public class BaseDialogFragment extends DialogFragment {

    public Context getApplicationContext() {
        return getActivity().getApplicationContext();
    }
}
