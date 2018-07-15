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

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.sky.android.common.util.DisplayUtil;
import com.sky.xposed.wechat.ui.util.LayoutUtil;
import com.sky.xposed.wechat.ui.util.ViewUtil;

/**
 * Created by sky on 18-3-12.
 */

public class CommonFrameLayout extends LinearLayout {

    private LinearLayout mContent;
    private TitleView mTitleView;

    public CommonFrameLayout(@NonNull Context context) {
        this(context, null);
    }

    public CommonFrameLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CommonFrameLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        setOrientation(VERTICAL);
        setBackgroundColor(Color.WHITE);
        setLayoutParams(LayoutUtil.newMatchLinearLayoutParams());

        // 添加标题
        mTitleView = new TitleView(getContext());
        addView(mTitleView);

        ScrollView scrollView = new ScrollView(getContext());
        scrollView.setLayoutParams(LayoutUtil.newMatchLinearLayoutParams());

        int top = DisplayUtil.dip2px(getContext(), 5);

        mContent = LayoutUtil.newCommonLayout(getContext());
        mContent.setPadding(0, top, 0, top);
        scrollView.addView(mContent);

        addView(scrollView);
    }

    public TitleView getDialogTitle() {
        return mTitleView;
    }

    public void setTitle(String title) {
        mTitleView.setTitle(title);
    }

    public void addContent(View child) {
        addContent(child, false);
    }

    public void addContent(View child, boolean line) {
        mContent.addView(child);
        if (line) mContent.addView(ViewUtil.newLineView(getContext()));
    }

    public void addContent(View child, ViewGroup.LayoutParams params) {
        mContent.addView(child, params);
    }
}
