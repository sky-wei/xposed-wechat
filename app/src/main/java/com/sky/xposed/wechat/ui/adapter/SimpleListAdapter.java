package com.sky.xposed.wechat.ui.adapter;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.sky.xposed.wechat.data.model.ItemModel;
import com.sky.xposed.wechat.ui.base.BaseListAdapter;
import com.sky.xposed.wechat.ui.util.LayoutUtil;
import com.sky.xposed.wechat.ui.view.SimpleItemView;
import com.sky.xposed.wechat.util.Alog;

/**
 * Created by sky on 18-3-11.
 */

public class SimpleListAdapter extends BaseListAdapter<ItemModel> {

    public SimpleListAdapter(Context context) {
        super(context);
    }

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup parent, int viewType) {
        return new SimpleItemView(getContext());
    }

    @Override
    public ViewHolder<ItemModel> onCreateViewHolder(View view, int viewType) {
        return new SimpleViewHolder(view, this);
    }

    private final class SimpleViewHolder extends ViewHolder<ItemModel> implements View.OnClickListener {

        public SimpleViewHolder(View itemView, BaseListAdapter baseListAdapter) {
            super(itemView, baseListAdapter);
        }

        @Override
        public void onInitialize() {
            super.onInitialize();

            // 添加事件
            mItemView.setOnClickListener(this);
        }

        @Override
        public void onBind(int position, int viewType) {

            SimpleItemView view = (SimpleItemView) mItemView;

            // 设置内容
            view.setName(getItem(position).getName());
        }

        @Override
        public void onClick(View v) {
            Alog.d("YYYYYYYYYYYYYYYYYYYY");
        }
    }
}
