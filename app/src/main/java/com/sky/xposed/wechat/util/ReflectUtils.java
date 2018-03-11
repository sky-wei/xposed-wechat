package com.sky.xposed.wechat.util;

import android.text.TextUtils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Alter byt sky on 16-11-18.
 * Created by starrysky on 16-7-31.
 *
 * 反射的工具类
 */
public class ReflectUtils {

    public final static String TAG = "ReflectUtils";

    public static Field findField(Class tClass, String name) {

        if (tClass == null || name == null) return null;

        try {
            Field field = tClass.getDeclaredField(name);
            field.setAccessible(true);
            return field;
        } catch (NoSuchFieldException e) {
            Alog.e(TAG, "NoSuchFieldException", e);
        }
        return findField(tClass.getSuperclass(), name);
    }

    public static Method findMethod(Class tClass, String name, Class<?>... parameterTypes) {

        if (tClass == null || name == null) return null;

        try {
            Method method = tClass.getDeclaredMethod(name, parameterTypes);
            method.setAccessible(true);
            return method;
        } catch (NoSuchMethodException e) {
            Alog.e(TAG, "NoSuchMethodException", e);
        }
        return findMethod(tClass.getSuperclass(), name, parameterTypes);
    }

    public static Object getFieldValue(Class tClass, String name) {
        return getValueQuietly(findField(tClass, name), null);
    }

    public static Object getFieldValue(Object object, String name) {

        if (object == null) return null;

        return getValueQuietly(findField(object.getClass(), name), object);
    }

    public static void setFieldValue(Class tClass, String name, Object value) {
        setValueQuietly(findField(tClass, name), null, value);
    }

    public static void setFieldValue(Object object, String name, Object value) {

        if (object == null) return ;

        setValueQuietly(findField(object.getClass(), name), object, value);
    }

    public static Object getValueQuietly(Field field, Object object) {

        if (field == null) return null;

        try {
            field.setAccessible(true);
            return field.get(object);
        } catch (IllegalAccessException e) {
            Alog.e(TAG, "IllegalAccessException", e);
        }
        return null;
    }

    public static void setValueQuietly(Field field, Object object, Object value) {

        if (field == null) return ;

        try {
            field.setAccessible(true);
            field.set(object, value);
        } catch (IllegalAccessException e) {
            Alog.e(TAG, "IllegalAccessException", e);
        }
    }

    public static Object invoke(Object receiver, Class tClass, String name,
                                Class[] parameterTypes, Object[] args) throws InvocationTargetException, IllegalAccessException {

        if (tClass == null || name == null) return null;

        Method method = findMethod(tClass, name, parameterTypes);

        return method == null ? null : method.invoke(receiver, args);
    }

    public static Object invokeQuietly(Object receiver, Class tClass, String name, Class[] parameterTypes, Object[] args) {

        try {
            return invoke(receiver, tClass, name, parameterTypes, args);
        } catch (Exception e) {
            Alog.e(TAG, "Invoke Exception", e);
        }
        return null;
    }

    public static Object invokeQuietly(Object receiver, String name) {
        return invokeQuietly(receiver, name, null, null);
    }

    public static Object invokeQuietly(Object receiver, String name, Class[] parameterTypes, Object[] args) {

        if (receiver == null) return null;

        return invokeQuietly(receiver, receiver.getClass(), name, parameterTypes, args);
    }

    public static Object invokeQuietly(Class tClass, String name, Class[] parameterTypes, Object[] args) {
        return invokeQuietly(null, tClass, name, parameterTypes, args);
    }

    public static Object invokeQuietly(Class tClass, String name) {
        return invokeQuietly(null, tClass, name, null, null);
    }

    public static Object newInstance(Class tClass) {
        return newInstance(tClass, null, null);
    }

    public static Object newInstance(Class tClass, Class[] parameterTypes, Object[] args) {

        if (tClass == null) return null;

        try {
            // 获取类的构造方法
            Constructor constructor = tClass.getConstructor(parameterTypes);
            constructor.setAccessible(true);

            // 创建类的实例
            return constructor.newInstance(args);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Class loadClassQuietly(String className) {

        if (TextUtils.isEmpty(className)) return null;

        try {
            ClassLoader loader = getClassLoader();
            return loader.loadClass(className);
        } catch (Exception e) {
            Alog.e(TAG, "LoadClass Exception", e);
        }
        return null;
    }

    public static Class classForName(String className) {

        if (TextUtils.isEmpty(className)) return null;

        try {
            return Class.forName(className);
        } catch (ClassNotFoundException e) {
            Alog.e(TAG, "没有找到相应的类", e);
        }
        return null;
    }

    public static ClassLoader getClassLoader() {
        return Thread.currentThread().getContextClassLoader();
    }
}
