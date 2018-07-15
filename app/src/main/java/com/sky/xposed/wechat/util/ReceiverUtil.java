/*
 * Copyright (c) 2018 The sky Authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.sky.xposed.wechat.util;

import android.content.Context;
import android.content.Intent;

import com.sky.xposed.wechat.Constant;
import com.sky.xposed.wechat.data.model.Pair;
import com.sky.xposed.wechat.ui.helper.ReceiverHelper;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

/**
 * Created by sky on 18-3-27.
 */

public class ReceiverUtil {

    public static void postStatusChange(Context context, String key, Object value) {
        postStatusChange(context, new Pair<>(key, value));
    }

    public static void postStatusChange(Context context, Pair<String, Object>... pairs) {

        if (context == null || pairs == null) return;

        List<Pair<String, Object>> data = Arrays.asList(pairs);

        Intent intent = new Intent(Constant.Action.STATUS_CHANGE);
        intent.putExtra(Constant.Key.DATA, (Serializable) data);

        // 发送广播
        ReceiverHelper.sendBroadcastReceiver(context, intent);
    }
}
