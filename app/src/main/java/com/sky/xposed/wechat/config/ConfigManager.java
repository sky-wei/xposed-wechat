package com.sky.xposed.wechat.config;

/**
 * Created by sky on 18-3-10.
 */

public interface ConfigManager {

    String getClassName(int key);

    String getMethodName(int key);

    String getFieldName(int key);
}
