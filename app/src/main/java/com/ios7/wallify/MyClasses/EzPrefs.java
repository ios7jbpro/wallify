package com.ios7.wallify.MyClasses;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

public class EzPrefs {

    private static final String PREF_NAME = "ezprefs";

    private static SharedPreferences getPrefs(Context context) {
        return context.getApplicationContext().getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    public static void putString(Context context, String key, String value) {
        boolean success = getPrefs(context).edit().putString(key, value).commit(); // <--- COMMIT now
        Log.d("EzPrefs", "putString key=" + key + " value=" + value + " success=" + success);
    }

    public static String getString(Context context, String key, String defValue) {
        String result = getPrefs(context).getString(key, defValue);
        Log.d("EzPrefs", "getString key=" + key + " result=" + result);
        return result;
    }

    // Keep others async unless you need reliability there too:
    public static void putInt(Context context, String key, int value) {
        getPrefs(context).edit().putInt(key, value).apply();
    }

    public static int getInt(Context context, String key, int defValue) {
        return getPrefs(context).getInt(key, defValue);
    }

    public static void putBoolean(Context context, String key, boolean value) {
        getPrefs(context).edit().putBoolean(key, value).apply();
    }

    public static boolean getBoolean(Context context, String key, boolean defValue) {
        return getPrefs(context).getBoolean(key, defValue);
    }

    public static void remove(Context context, String key) {
        getPrefs(context).edit().remove(key).apply();
    }

    public static void clear(Context context) {
        getPrefs(context).edit().clear().apply();
    }
}

