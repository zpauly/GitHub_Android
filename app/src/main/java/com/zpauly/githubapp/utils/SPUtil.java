package com.zpauly.githubapp.utils;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import java.util.Map;

/**
 * Created by zpauly on 16-6-8.
 */
public class SPUtil {
    public static SharedPreferences mSP;
    public static SharedPreferences.Editor mSPEditor;

    public static SharedPreferences getSPTable(Context context, String tableName) {
        mSP = context.getSharedPreferences(tableName, Context.MODE_PRIVATE);
        return mSP;
    }

    public static <T> T setField(Context context, String tableName, String key, T value) {
        mSP = getSPTable(context, tableName);
        mSPEditor = mSP.edit();
        if (value instanceof String) {
            mSPEditor.putString(key, (String) value);
        } else if (value instanceof Integer) {
            mSPEditor.putInt(key, (Integer) value);
        } else if (value instanceof Boolean) {
            mSPEditor.putBoolean(key, (Boolean) value);
        } else if (value instanceof Float) {
            mSPEditor.putFloat(key, (Float) value);
        } else if (value instanceof Long) {
            mSPEditor.putLong(key, (Long) value);
        }
        mSPEditor.commit();
        return value;
    }

    public static void putString(Context context, String tableName, String key, String value) {
        mSP = getSPTable(context, tableName);
        mSPEditor = mSP.edit();
        mSPEditor.putString(key, value);
        mSPEditor.commit();
    }

    public static String getString(Context context, String tableName, String key, String defValue) {
        mSP = getSPTable(context, tableName);
        return mSP.getString(key, defValue);
    }

    public static void putInt(Context context, String tableName, String key, int value) {
        mSP = getSPTable(context, tableName);
        mSPEditor = mSP.edit();
        mSPEditor.putInt(key, value);
        mSPEditor.commit();
    }

    public static int getInt(Context context, String tableName, String key, int defValue) {
        mSP = getSPTable(context, tableName);
        return mSP.getInt(key, defValue);
    }

    public static <T> T getField(Context context, String tableName, String key, T defValue) {
        mSP = getSPTable(context, tableName);
        if (defValue instanceof String) {
            return (T) mSP.getString(key, (String) defValue);
        } else if (defValue instanceof Integer) {
            Integer value = mSP.getInt(key, (Integer) defValue);
            return (T) value;
        } else if (defValue instanceof Float) {
            Float value = mSP.getFloat(key, (Float) defValue);
            return (T) value;
        } else if (defValue instanceof Long) {
            Long value = mSP.getLong(key, (Long) defValue);
            return (T) value;
        } else if (defValue instanceof Boolean) {
            Boolean value = mSP.getBoolean(key, (Boolean) defValue);
            return (T) value;
        } else {
            return defValue;
        }
    }

    public static void clearAllField(Context context, String tableName) {
        mSP = getSPTable(context, tableName);
        mSPEditor = mSP.edit();
        mSPEditor.clear();
        mSPEditor.commit();
    }
}
