package com.example.systemadminproject.util;

import android.content.SharedPreferences;

public class SharedPreferenceUtil {
    public static void putKey(SharedPreferences sharedPreferences, String key, String value) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.commit();
    }
}