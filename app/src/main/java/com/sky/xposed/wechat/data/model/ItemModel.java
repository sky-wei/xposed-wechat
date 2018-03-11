package com.sky.xposed.wechat.data.model;

/**
 * Created by sky on 18-3-11.
 */

public class ItemModel {

    private String name;

    public ItemModel(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
