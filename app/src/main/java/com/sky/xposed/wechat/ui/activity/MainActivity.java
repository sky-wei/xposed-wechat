package com.sky.xposed.wechat.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.sky.xposed.wechat.R;
import com.sky.xposed.wechat.hook.HookManager;
import com.sky.xposed.wechat.ui.dialog.SettingDialog;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.menu_settings) {
            // 显示
            SettingDialog dialog = new SettingDialog();
            dialog.show(getFragmentManager(), "setting");
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
