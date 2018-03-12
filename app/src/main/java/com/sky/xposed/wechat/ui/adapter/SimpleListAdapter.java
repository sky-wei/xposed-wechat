package com.sky.xposed.wechat.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sky.xposed.wechat.Constant;
import com.sky.xposed.wechat.data.model.ItemModel;
import com.sky.xposed.wechat.ui.base.BaseListAdapter;
import com.sky.xposed.wechat.ui.interfaces.OnItemEventListener;
import com.sky.xposed.wechat.ui.view.SimpleItemView;

/**
 * Created by sky on 18-3-11.
 */

public class SimpleListAdapter extends BaseListAdapter<ItemModel> {

    private OnItemEventListener mOnItemEventListener;

    public SimpleListAdapter(Context context) {
        super(context);
    }

    public OnItemEventListener getOnItemEventListener() {
        return mOnItemEventListener;
    }

    public void setOnItemEventListener(OnItemEventListener onItemEventListener) {
        mOnItemEventListener = onItemEventListener;
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
            if (mOnItemEventListener != null) {
                mOnItemEventListener.onItemEvent(Constant.Event.CLICK, v, getAdapterPosition());
            }
        }
    }
}
