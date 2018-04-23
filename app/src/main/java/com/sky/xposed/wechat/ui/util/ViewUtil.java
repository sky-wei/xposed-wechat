package com.sky.xposed.wechat.ui.util;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.StateListDrawable;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.sky.xposed.wechat.ui.view.CategoryItemView;
import com.sky.xposed.wechat.ui.view.SwitchItemView;

/**
 * Created by starrysky on 16-8-2.
 */
public class ViewUtil {

    public static void setVisibility(View view, int visibility) {

        if (view == null || view.getVisibility() == visibility) return ;

        view.setVisibility(visibility);
    }

    public static void setVisibility(int visibility, View... views) {

        if (views == null) return ;

        for (View view : views) {
            setVisibility(view, visibility);
        }
    }

    public static void setTypeface(Typeface typeface, TextView... textViews) {

        if (typeface == null || textViews == null) return ;

        for (TextView textView : textViews) {
            textView.setTypeface(typeface);
        }
    }

    public static String getText(TextView textView) {
        return textView != null ? charSequenceToString(textView.getText()) : null;
    }

    public static String charSequenceToString(CharSequence charSequence) {
        return charSequence != null ? charSequence.toString() : null;
    }

    /**
     * EditText竖直方向是否可以滚动
     * @param editText  需要判断的EditText
     * @return  true：可以滚动   false：不可以滚动
     */
    public static boolean canVerticalScroll(EditText editText) {

        if (editText == null) return false;

        //滚动的距离
        int scrollY = editText.getScrollY();
        //控件内容的总高度
        int scrollRange = editText.getLayout().getHeight();
        //控件实际显示的高度
        int scrollExtent = editText.getHeight() - editText.getCompoundPaddingTop() -editText.getCompoundPaddingBottom();
        //控件内容总高度与实际显示高度的差值
        int scrollDifference = scrollRange - scrollExtent;

        if(scrollDifference == 0) {
            return false;
        }

        return (scrollY > 0) || (scrollY < scrollDifference - 1);
    }

    public static StateListDrawable newBackgroundDrawable() {

        StateListDrawable drawable = new StateListDrawable();

        drawable.addState(new int[] { android.R.attr.state_pressed }, new ColorDrawable(0xffe5e5e5));
        drawable.addState(new int[] {}, new ColorDrawable(Color.WHITE));

        return drawable;
    }

    public static View newLineView(Context context) {

        View lineView = new View(context);
        lineView.setBackgroundColor(0xFFDFDFDF);
        lineView.setLayoutParams(LayoutUtil.newViewGroupParams(
                FrameLayout.LayoutParams.MATCH_PARENT, 2));
        return lineView;
    }

    public static SwitchItemView newSwitchItemView(Context context, String name) {

        SwitchItemView itemView = new SwitchItemView(context);
        itemView.setName(name);

        return itemView;
    }

    public static CategoryItemView newCategoryItemView(Context context, String name) {

        CategoryItemView itemView = new CategoryItemView(context);
        itemView.setName(name);

        return itemView;
    }
}
