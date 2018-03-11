package com.sky.xposed.wechat.ui.util;

import android.graphics.Typeface;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

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
}
