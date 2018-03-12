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
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sky.xposed.wechat.ui.util.LayoutUtil;
import com.sky.xposed.wechat.ui.util.ViewUtil;
import com.sky.xposed.wechat.util.DisplayUtil;

/**
 * Created by sky on 18-3-11.
 */

public class DialogTitle extends FrameLayout implements View.OnClickListener {

    private ImageView ivClose;
    private TextView tvTitle;
    private ImageView ivMore;
    private OnTitleEventListener mOnTitleEventListener;

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

        LinearLayout tLayout = new LinearLayout(getContext());
        tLayout.setGravity(Gravity.CENTER_VERTICAL);
        tLayout.setOrientation(LinearLayout.HORIZONTAL);

        ivClose = new ImageView(getContext());
        ivClose.setTag("close");
        ivClose.setImageResource(android.R.drawable.ic_menu_close_clear_cancel);
        ivClose.setOnClickListener(this);

        tvTitle = new TextView(getContext());
        tvTitle.setPadding(DisplayUtil.dip2px(getContext(), 5), 0, 0, 0);
        tvTitle.setTextColor(Color.WHITE);
        tvTitle.setTextSize(18);

        tLayout.addView(ivClose);
        tLayout.addView(tvTitle);

        ivMore = new ImageView(getContext());
        ivMore.setTag("more");
        ivMore.setImageResource(android.R.drawable.ic_menu_more);
        ivMore.setOnClickListener(this);

        FrameLayout.LayoutParams params = LayoutUtil.newWrapFrameLayoutParams();
        params.gravity = Gravity.CENTER_VERTICAL;
        params.leftMargin = DisplayUtil.dip2px(getContext(), 10);

        FrameLayout.LayoutParams imageParams = LayoutUtil.newWrapFrameLayoutParams();
        imageParams.gravity = Gravity.CENTER_VERTICAL | Gravity.RIGHT;
        imageParams.rightMargin = DisplayUtil.dip2px(getContext(), 10);

        addView(tLayout, params);
        addView(ivMore, imageParams);

        hideClose();
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

    public void showClose() {
        ViewUtil.setVisibility(ivClose, View.VISIBLE);
    }

    public void hideClose() {
        ViewUtil.setVisibility(ivClose, View.GONE);
    }

    public OnTitleEventListener getOnTitleEventListener() {
        return mOnTitleEventListener;
    }

    public void setOnTitleEventListener(OnTitleEventListener onTitleEventListener) {
        mOnTitleEventListener = onTitleEventListener;
    }

    @Override
    public void onClick(View v) {

        if (mOnTitleEventListener == null) return ;

        String tag = (String) v.getTag();

        if ("close".equals(tag)) {
            mOnTitleEventListener.onCloseEvent(v);
        } else {
            mOnTitleEventListener.onMoreEvent(v);
        }
    }

    public interface OnTitleEventListener {

        void onCloseEvent(View view);

        void onMoreEvent(View view);
    }
}
