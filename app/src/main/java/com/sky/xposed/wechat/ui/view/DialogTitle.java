package com.sky.xposed.wechat.ui.view;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.sky.xposed.wechat.ui.util.LayoutUtil;
import com.sky.xposed.wechat.ui.util.ViewUtil;
import com.sky.xposed.wechat.util.DisplayUtil;

/**
 * Created by sky on 18-3-11.
 */

public class DialogTitle extends FrameLayout implements View.OnClickListener {

    private TextView tvTitle;
    private ImageView ivMore;
    private OnMoreClickListener mOnMoreClickListener;

    public DialogTitle(@NonNull Context context) {
        this(context, null);
    }

    public DialogTitle(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DialogTitle(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        setLayoutParams(LayoutUtil.newViewGroupParams(
                ViewGroup.LayoutParams.MATCH_PARENT, DisplayUtil.dip2px(getContext(), 45)));
        setBackgroundColor(0xFF393A3F);

        tvTitle = new TextView(getContext());
        tvTitle.setTextColor(Color.WHITE);
        tvTitle.setTextSize(18);

        ivMore = new ImageView(getContext());
        ivMore.setImageResource(android.R.drawable.ic_menu_more);
        ivMore.setOnClickListener(this);

        FrameLayout.LayoutParams params = LayoutUtil.newWrapFrameLayoutParams();
        params.gravity = Gravity.CENTER_VERTICAL;
        params.leftMargin = DisplayUtil.dip2px(getContext(), 15);

        FrameLayout.LayoutParams imageParams = LayoutUtil.newWrapFrameLayoutParams();
        imageParams.gravity = Gravity.CENTER_VERTICAL | Gravity.RIGHT;
        imageParams.rightMargin = DisplayUtil.dip2px(getContext(), 10);

        addView(tvTitle, params);
        addView(ivMore, imageParams);

        hideMore();
    }

    public void setTitle(String title) {
        tvTitle.setText(title);
    }

    public void showMore() {
        ViewUtil.setVisibility(ivMore, View.VISIBLE);
    }

    public void hideMore() {
        ViewUtil.setVisibility(ivMore, View.GONE);
    }

    public void setMoreOnClickListener(OnMoreClickListener l) {
        mOnMoreClickListener = l;
    }

    @Override
    public void onClick(View v) {
        if (mOnMoreClickListener != null) mOnMoreClickListener.onClick(v);
    }

    public interface OnMoreClickListener {

        void onClick(View view);
    }
}
