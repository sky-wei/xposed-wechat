/*
 * Copyright (c) 2018 The sky Authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.sky.xposed.wechat.ui.view;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.sky.android.common.util.DisplayUtil;
import com.sky.xposed.wechat.ui.interfaces.TrackViewStatus;
import com.sky.xposed.wechat.ui.util.LayoutUtil;
import com.sky.xposed.wechat.ui.util.ViewUtil;

public class EditTextItemView extends FrameLayout
        implements View.OnClickListener, TrackViewStatus<String> {

    private TextView tvName;
    private OnTextChangeListener mOnTextChangeListener;

    public EditTextItemView(Context context) {
        this(context, null);
    }

    public EditTextItemView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public EditTextItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {

        int left = DisplayUtil.dip2px(getContext(), 15);

        setPadding(left, 0, left, 0);
        setBackground(ViewUtil.newBackgroundDrawable());
        setLayoutParams(LayoutUtil.newViewGroupParams(
                ViewGroup.LayoutParams.MATCH_PARENT, DisplayUtil.dip2px(getContext(), 40)));

        tvName = new TextView(getContext());
        tvName.setTextColor(Color.BLACK);
        tvName.setTextSize(15);

        FrameLayout.LayoutParams params = LayoutUtil.newWrapFrameLayoutParams();
        params.gravity = Gravity.CENTER_VERTICAL;

        addView(tvName, params);

        params = LayoutUtil.newWrapFrameLayoutParams();
        params.gravity = Gravity.CENTER_VERTICAL | Gravity.RIGHT;

        setOnClickListener(this);
    }

    public OnTextChangeListener getOnTextChangeListener() {
        return mOnTextChangeListener;
    }

    public void setOnTextChangeListener(OnTextChangeListener onTextChangeListener) {
        mOnTextChangeListener = onTextChangeListener;
    }

    public void setName(String title) {
        tvName.setText(title);
    }

    public String getName() {
        return tvName.getText().toString();
    }

    @Override
    public void onClick(View v) {

        int top = DisplayUtil.dip2px(getContext(), 10);
        int left = DisplayUtil.dip2px(getContext(), 24);

        FrameLayout frameLayout = new FrameLayout(getContext());
        frameLayout.setLayoutParams(LayoutUtil.newFrameLayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        frameLayout.setPadding(left, top, left, 0);

        final EditText editText = new EditText(getContext());
        editText.setText(mOnTextChangeListener.getDefaultText());
        editText.setLayoutParams(LayoutUtil.newViewGroupParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        frameLayout.addView(editText);

        AlertDialog.Builder builder =
                new AlertDialog.Builder(getContext());
        builder.setTitle(getName());
        builder.setView(frameLayout);
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // 返回文本的内容
                mOnTextChangeListener.onTextChanged(editText, editText.getText().toString());
            }
        });
        builder.setNegativeButton("取消", null);
        builder.show();
    }

    @Override
    public void bind(final SharedPreferences preferences,
                     final String key, final String defValue, final StatusChangeListener<String> listener) {

        setOnTextChangeListener(new OnTextChangeListener() {
            @Override
            public String getDefaultText() {
                // 获取文本信息
                return preferences.getString(key, defValue);
            }

            @Override
            public void onTextChanged(View view, String text) {
                // 保存信息
                preferences.edit().putString(key, text).apply();
                listener.onStatusChange(view, key, text);
            }
        });
    }

    interface OnTextChangeListener {

        String getDefaultText();

        void onTextChanged(View view, String text);
    }
}
