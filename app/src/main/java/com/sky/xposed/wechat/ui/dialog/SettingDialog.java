package com.sky.xposed.wechat.ui.dialog;


import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupMenu;

import com.sky.xposed.wechat.Constant;
import com.sky.xposed.wechat.data.model.ItemModel;
import com.sky.xposed.wechat.ui.adapter.SimpleListAdapter;
import com.sky.xposed.wechat.ui.base.BaseDialogFragment;
import com.sky.xposed.wechat.ui.interfaces.OnItemEventListener;
import com.sky.xposed.wechat.ui.util.LayoutUtil;
import com.sky.xposed.wechat.ui.view.DialogTitle;
import com.sky.xposed.wechat.util.DisplayUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sky on 18-3-10.
 */

public class SettingDialog extends BaseDialogFragment
        implements DialogTitle.OnMoreClickListener, OnItemEventListener {

    private DialogTitle mDialogTitle;
    private ListView mListView;
    private SimpleListAdapter mSettingAdapter;

    @Override
    protected View createView(LayoutInflater inflater, ViewGroup container) {

        LinearLayout content = new LinearLayout(getApplicationContext());
        content.setLayoutParams(LayoutUtil.newMatchLinearLayoutParams());
        content.setMinimumWidth(DisplayUtil.sp2px(getApplicationContext(), 360));
        content.setOrientation(LinearLayout.VERTICAL);
        content.setBackgroundColor(Color.WHITE);

        // 添加标题
        mDialogTitle = new DialogTitle(getApplicationContext());

        content.addView(mDialogTitle);

        // 添加ListView
        mListView = new ListView(getApplicationContext());

        LinearLayout.LayoutParams params = LayoutUtil.newMatchLinearLayoutParams();
        params.topMargin = DisplayUtil.dip2px(getApplicationContext(), 5);
        params.bottomMargin = params.topMargin;
        mListView.setLayoutParams(params);

        content.addView(mListView);

        return content;
    }

    @Override
    protected void initView(View view, Bundle args) {

        mDialogTitle.setTitle("WeBlue");
        mDialogTitle.showMore();
        mDialogTitle.setMoreOnClickListener(this);

        mSettingAdapter = new SimpleListAdapter(getApplicationContext());
        mSettingAdapter.setOnItemEventListener(this);
        mSettingAdapter.setItems(newItemModels());

        mListView.setAdapter(mSettingAdapter);
    }

    @Override
    public void onClick(View view) {

        PopupMenu popupMenu = new PopupMenu(getApplicationContext(), mDialogTitle, Gravity.RIGHT);
        Menu menu = popupMenu.getMenu();

        menu.add(1, 1, 1, "导入配置");
        menu.add(1, 2, 1, "导出配置");

        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                return false;
            }
        });

        popupMenu.show();
    }

    @Override
    public void onItemEvent(int event, View view, int position, Object... args) {

        ItemModel model = mSettingAdapter.getItem(position);

        switch (model.getId()) {
            case Constant.ItemId.OTHER:
                break;
            case Constant.ItemId.DEVELOP:
                break;
            case Constant.ItemId.ABOUT:
                // 显示关于
                AboutDialog dialog = new AboutDialog();
                dialog.show(getChildFragmentManager(), "about");
                break;
        }
    }

    private List<ItemModel> newItemModels() {

        List<ItemModel> itemModels = new ArrayList<>();

        itemModels.add(new ItemModel(Constant.ItemId.OTHER, "其他功能"));
        itemModels.add(new ItemModel(Constant.ItemId.DEVELOP, "开发调试"));
        itemModels.add(new ItemModel(Constant.ItemId.ABOUT, "关于"));

        return itemModels;
    }
}
