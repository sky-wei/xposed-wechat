package com.sky.xposed.wechat.ui.base;

import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sky.xposed.wechat.data.PreferencesManager;
import com.sky.xposed.wechat.hook.HookManager;

/**
 * Created by sky on 18-3-11.
 */

public abstract class BaseDialogFragment extends DialogFragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return createView(inflater, container);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // 初始化View
        initView(view, getArguments());
    }

    /**
     * 针对New Dialog处理的
     * @return
     */
    protected View createDialogView() {

        // 创建View
        View view = createView(getSkyLayoutInflater(), null);

        // 初始化
        initView(view, getArguments());

        return view;
    }

    protected abstract View createView(LayoutInflater inflater, ViewGroup container);

    protected abstract void initView(View view, Bundle args);

    public Context getContext() {
        return getActivity();
    }

    public LayoutInflater getSkyLayoutInflater() {
        return LayoutInflater.from(getContext());
    }

    public Context getApplicationContext() {
        return getActivity().getApplicationContext();
    }

    public PreferencesManager getPreferencesManager() {
        return HookManager.getInstance().getPreferencesManager();
    }
}
