package com.sky.xposed.wechat.ui.dialog;


import android.os.Bundle;
import android.util.SparseArray;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupMenu;

import com.sky.xposed.wechat.Constant;
import com.sky.xposed.wechat.data.model.ItemModel;
import com.sky.xposed.wechat.hook.HookManager;
import com.sky.xposed.wechat.hook.module.Module;
import com.sky.xposed.wechat.ui.adapter.SimpleListAdapter;
import com.sky.xposed.wechat.ui.interfaces.OnItemEventListener;
import com.sky.xposed.wechat.ui.util.LayoutUtil;
import com.sky.xposed.wechat.ui.view.CommonFrameLayout;
import com.sky.xposed.wechat.util.DisplayUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sky on 18-3-10.
 */

public class SettingDialog extends CommonDialog implements OnItemEventListener {

    private ListView mListView;
    private SimpleListAdapter mSettingAdapter;

    @Override
    protected void createView(CommonFrameLayout view) {
        super.createView(view);

        // 添加ListView
        mListView = new ListView(getApplicationContext());

        LinearLayout.LayoutParams params = LayoutUtil.newMatchLinearLayoutParams();
        params.topMargin = DisplayUtil.dip2px(getApplicationContext(), 5);
        params.bottomMargin = params.topMargin;
        mListView.setLayoutParams(params);

        view.addView(mListView);
    }

    @Override
    protected void initView(View view, Bundle args) {
        super.initView(view, args);

        setTitle(Constant.Strings.TITLE);
        getTitleView().showMore();

        mSettingAdapter = new SimpleListAdapter(getApplicationContext());
        mSettingAdapter.setOnItemEventListener(this);
        mSettingAdapter.setItems(newItemModels());

        mListView.setAdapter(mSettingAdapter);
    }

    @Override
    public void onMoreEvent(View view) {

        PopupMenu popupMenu = new PopupMenu(getApplicationContext(), getTitleView(), Gravity.RIGHT);
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
            case Constant.ModuleId.OTHER:
                OtherDialog otherDialog = new OtherDialog();
                otherDialog.show(getChildFragmentManager(), "other");
                break;
            case Constant.ModuleId.DEVELOP:
                // 开启选项
                DevelopDialog developDialog = new DevelopDialog();
                developDialog.show(getChildFragmentManager(), "develop");
                break;
            case Constant.ModuleId.ABOUT:
                // 显示关于
                AboutDialog aboutDialog = new AboutDialog();
                aboutDialog.show(getChildFragmentManager(), "about");
                break;
        }
    }

    private List<ItemModel> newItemModels() {

        SparseArray<Module> hookModules =
                HookManager.getInstance().getHookModules();

        List<ItemModel> itemModels = new ArrayList<>();

        for (int i = 0; i < hookModules.size(); i++) {
            // 添加注册的模块
            Module module = hookModules.valueAt(i);

            if ((0x1000 & module.getId()) > 0) {
                // 添加显示的模块
                itemModels.add(new ItemModel(module.getId(), module.getName()));
            }
        }
        // 关于
        itemModels.add(new ItemModel(Constant.ModuleId.ABOUT, "关于"));

        return itemModels;
    }
}
