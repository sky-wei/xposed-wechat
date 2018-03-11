package com.sky.xposed.wechat.ui.view;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.StateListDrawable;
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
 * Created by sky on 18-3-11.
 */

public class SimpleItemView extends FrameLayout {

    private TextView tvName;

    public SimpleItemView(@NonNull Context context) {
        this(context, null);
    }

    public SimpleItemView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SimpleItemView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        setBackground(newBackgroundDrawable());
        setLayoutParams(LayoutUtil.newViewGroupParams(
                ViewGroup.LayoutParams.MATCH_PARENT, DisplayUtil.dip2px(getContext(), 40)));

        tvName = new TextView(getContext());
        tvName.setTextColor(Color.BLACK);
        tvName.setTextSize(16);

        FrameLayout.LayoutParams params = LayoutUtil.newWrapFrameLayoutParams();
        params.gravity = Gravity.CENTER_VERTICAL;
        params.leftMargin = DisplayUtil.dip2px(getContext(), 15);

        addView(tvName, params);
    }

    public void setName(String title) {
        tvName.setText(title);
    }

    public String getName() {
        return tvName.getText().toString();
    }

    private StateListDrawable newBackgroundDrawable() {

        StateListDrawable drawable = new StateListDrawable();

        drawable.addState(new int[] { android.R.attr.state_pressed }, new ColorDrawable(0xffe5e5e5));
        drawable.addState(new int[] {}, new ColorDrawable(Color.WHITE));

        return drawable;
    }
}
