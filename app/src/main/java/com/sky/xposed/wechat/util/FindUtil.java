package com.sky.xposed.wechat.util;

import java.lang.reflect.Field;

/**
 * Created by sky on 18-3-13.
 */

public class FindUtil {

    public static Field firstOrNull(Field[] fields, Filter<Field> filter) {

        if (fields == null || filter == null) return null;

        for (Field field : fields) {

            if (filter.accept(field)) return field;
        }

        return null;
    }

    public interface Filter<T> {

        boolean accept(T t);
    }
}
