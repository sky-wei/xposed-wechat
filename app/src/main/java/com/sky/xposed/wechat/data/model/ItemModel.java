package com.sky.xposed.wechat.data.model;

/**
 * Created by sky on 18-3-11.
 */

public class ItemModel {

    private int id;
    private String name;

    public ItemModel(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
