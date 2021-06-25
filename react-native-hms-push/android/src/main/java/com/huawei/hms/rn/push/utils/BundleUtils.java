/*
    Copyright 2020-2021. Huawei Technologies Co., Ltd. All rights reserved.

    Licensed under the Apache License, Version 2.0 (the "License")
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        https://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
*/

package com.huawei.hms.rn.push.utils;

import android.os.Build;
import android.os.Bundle;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Set;

public class BundleUtils {

    private BundleUtils() {
        throw new IllegalStateException("Utility class");
    }

    public static String get(Bundle bundle, String key) {

        return bundle != null ? bundle.getString(key) : null;
    }

    public static void set(Bundle bundle, String key, String value) {

        if (bundle != null)
            bundle.putString(key, value);
    }

    public static String get(Bundle bundle, String key, String defaultValue) {

        return bundle != null ? bundle.getString(key, defaultValue) : defaultValue;
    }

    public static boolean getB(Bundle bundle, String key) {

        return bundle != null && bundle.getBoolean(key);
    }

    public static boolean getB(Bundle bundle, String key, boolean defaultValue) {

        return bundle != null ? bundle.getBoolean(key, defaultValue) : defaultValue;
    }

    public static void setB(Bundle bundle, String key, boolean value) {

        if (bundle != null)
            bundle.putBoolean(key, value);
    }

    public static double getD(Bundle bundle, String key) {

        return bundle != null ? bundle.getDouble(key) : 0.0;
    }

    public static double getD(Bundle bundle, String key, double defaultValue) {

        return bundle != null ? bundle.getDouble(key, defaultValue) : defaultValue;
    }

    public static void setD(Bundle bundle, String key, double value) {

        if (bundle != null)
            bundle.putDouble(key, value);
    }

    public static long getL(Bundle bundle, String key) {

        return (long) getD(bundle, key);
    }

    public static long getL(Bundle bundle, String key, double defaultValue) {

        return (long) getD(bundle, key, defaultValue);
    }

    public static boolean contains(Bundle bundle, String key) {

        return bundle != null && bundle.containsKey(key);
    }

    public static String convertJSON(Bundle bundle) {

        JSONObject json = convertJSONObject(bundle);
        return json.toString();

    }

    static public JSONObject convertJSONObject(Bundle bundle) {
        try {
            JSONObject json = new JSONObject();
            if (bundle != null) {
                Set<String> keys = bundle.keySet();
                for (String key : keys) {
                    Object value = bundle.get(key);
                    if (value instanceof Bundle) {
                        json.put(key, convertJSONObject((Bundle) value));
                    } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                        json.put(key, JSONObject.wrap(value));
                    } else {
                        json.put(key, value);
                    }
                }
            }
            return json;
        } catch (JSONException e) {
            return null;
        }
    }


}
