package com.sky.xposed.wechat.ui.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.sky.xposed.wechat.ui.util.LayoutUtil;
import com.sky.xposed.wechat.util.DisplayUtil;

/**
 * Created by sky on 18-3-27.
 */

public class CategoryItemView extends FrameLayout {

    private TextView tvName;

    public CategoryItemView(@NonNull Context context) {
        this(context, null);
    }

    public CategoryItemView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CategoryItemView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        setLayoutParams(LayoutUtil.newViewGroupParams(
                ViewGroup.LayoutParams.MATCH_PARENT, DisplayUtil.dip2px(getContext(), 40)));
        setPadding(0, 10, 0, 10);

        tvName = new TextView(getContext());
        tvName.setTextColor(getResources().getColor(android.R.color.holo_blue_dark));
        tvName.setTextSize(15);

        FrameLayout.LayoutParams params = LayoutUtil.newWrapFrameLayoutParams();
        params.leftMargin = DisplayUtil.dip2px(getContext(), 15);
        params.gravity = Gravity.CENTER_VERTICAL;

        addView(tvName, params);
    }

    public void setName(String title) {
        tvName.setText(title);
    }

    public String getName() {
        return tvName.getText().toString();
    }
}
