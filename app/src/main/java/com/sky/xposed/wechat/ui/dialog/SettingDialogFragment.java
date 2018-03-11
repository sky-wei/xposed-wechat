package com.sky.xposed.wechat.ui.dialog;


import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupMenu;

import com.sky.xposed.wechat.data.model.ItemModel;
import com.sky.xposed.wechat.ui.adapter.SimpleListAdapter;
import com.sky.xposed.wechat.ui.base.BaseDialogFragment;
import com.sky.xposed.wechat.ui.util.LayoutUtil;
import com.sky.xposed.wechat.ui.view.DialogTitle;
import com.sky.xposed.wechat.util.DisplayUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sky on 18-3-10.
 */

public class SettingDialogFragment extends BaseDialogFragment
        implements DialogTitle.OnMoreClickListener, AdapterView.OnItemClickListener {

    private DialogTitle mDialogTitle;
    private ListView mListView;
    private SimpleListAdapter mSettingAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        LinearLayout content = new LinearLayout(getApplicationContext());
        content.setLayoutParams(LayoutUtil.newMatchLinearLayoutParams());
        content.setMinimumWidth(DisplayUtil.sp2px(getApplicationContext(), 360));
        content.setOrientation(LinearLayout.VERTICAL);
        content.setBackgroundColor(Color.WHITE);

        mDialogTitle = new DialogTitle(getApplicationContext());
        mDialogTitle.setTitle("Wechat-sky");
        mDialogTitle.showMore();
        mDialogTitle.setMoreOnClickListener(this);

        content.addView(mDialogTitle);

        mListView = new ListView(getApplicationContext());

        LinearLayout.LayoutParams params = LayoutUtil.newMatchLinearLayoutParams();
        params.topMargin = DisplayUtil.dip2px(getApplicationContext(), 5);
        params.bottomMargin = params.topMargin;
        mListView.setLayoutParams(params);

        mSettingAdapter = new SimpleListAdapter(getApplicationContext());
        mSettingAdapter.setItems(newItemModels());

        mListView.setAdapter(mSettingAdapter);

        content.addView(mListView);

        return content;
    }

    @Override
    public void onClick(View view) {

        PopupMenu popupMenu = new PopupMenu(getApplicationContext(), view);
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
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    private List<ItemModel> newItemModels() {

        List<ItemModel> itemModels = new ArrayList<>();

        itemModels.add(new ItemModel("其他功能"));
        itemModels.add(new ItemModel("开发调试"));
        itemModels.add(new ItemModel("关于"));

        return itemModels;
    }
}
