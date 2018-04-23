package com.sky.xposed.wechat.ui.view;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.sky.xposed.wechat.ui.interfaces.TrackViewStatus;
import com.sky.xposed.wechat.ui.util.LayoutUtil;
import com.sky.xposed.wechat.ui.util.ViewUtil;
import com.sky.xposed.wechat.util.DisplayUtil;

/**
 * Created by sky on 18-3-12.
 */

public class SwitchItemView extends FrameLayout implements View.OnClickListener, TrackViewStatus<Boolean> {

    private TextView tvName;
    private Switch mSwitch;
    private OnCheckedChangeListener mOnCheckedChangeListener;

    public SwitchItemView(@NonNull Context context) {
        this(context, null);
    }

    public SwitchItemView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SwitchItemView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    public OnCheckedChangeListener getOnCheckedChangeListener() {
        return mOnCheckedChangeListener;
    }

    public void setOnCheckedChangeListener(OnCheckedChangeListener onCheckedChangeListener) {
        mOnCheckedChangeListener = onCheckedChangeListener;
    }

    private void initView() {

        int left = DisplayUtil.dip2px(getContext(), 15);

        setPadding(left, 0, left, 0);
        setBackground(ViewUtil.newBackgroundDrawable());
        setLayoutParams(LayoutUtil.newViewGroupParams(
                ViewGroup.LayoutParams.MATCH_PARENT, DisplayUtil.dip2px(getContext(), 40)));

        tvName = new TextView(getContext());
        tvName.setTextColor(Color.BLACK);
        tvName.setTextSize(14);

        FrameLayout.LayoutParams params = LayoutUtil.newWrapFrameLayoutParams();
        params.gravity = Gravity.CENTER_VERTICAL;

        addView(tvName, params);

        mSwitch = new Switch(getContext());
        mSwitch.setClickable(false);
        mSwitch.setFocusable(false);
        mSwitch.setFocusableInTouchMode(false);

        params = LayoutUtil.newWrapFrameLayoutParams();
        params.gravity = Gravity.CENTER_VERTICAL | Gravity.RIGHT;

        addView(mSwitch, params);
        setOnClickListener(this);
    }

    public void setName(String title) {
        tvName.setText(title);
    }

    public String getName() {
        return tvName.getText().toString();
    }

    public void setChecked(boolean checked) {
        mSwitch.setChecked(checked);
    }

    public boolean isChecked() {
        return mSwitch.isChecked();
    }

    @Override
    public void onClick(View v) {

        setChecked(!isChecked());

        if (mOnCheckedChangeListener != null)
            mOnCheckedChangeListener.onCheckedChanged(this, isChecked());
    }

    @Override
    public void bind(final SharedPreferences preferences,
                     final String key, Boolean defValue, final StatusChangeListener<Boolean> listener) {
        // 设置状态
        setChecked(preferences.getBoolean(key, defValue));
        setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(View view, boolean isChecked) {
                // 保存状态信息
                preferences.edit().putBoolean(key, isChecked).apply();
                listener.onStatusChange(view, key, isChecked);
            }
        });
    }

    public interface OnCheckedChangeListener {

        void onCheckedChanged(View view, boolean isChecked);
    }
}
