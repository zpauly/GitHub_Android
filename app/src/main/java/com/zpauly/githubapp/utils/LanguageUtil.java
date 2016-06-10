package com.zpauly.githubapp.utils;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.util.DisplayMetrics;

import com.zpauly.githubapp.Constants;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * Created by zpauly on 16-6-8.
 */
public class LanguageUtil {
    private static Map<Integer, Locale> languageMap = new HashMap<>();

    static {
        languageMap.put(Constants.LANGUAGE_SIMPLIFIED_CHINESE, Locale.SIMPLIFIED_CHINESE);
        languageMap.put(Constants.LANGUAGE_ENGLISH, Locale.ENGLISH);
    }

    public static void setLanguageToEnglish(Context context) {
        Resources resources = context.getResources();
        DisplayMetrics displayMetrics = resources.getDisplayMetrics();
        Configuration configuration = resources.getConfiguration();
        configuration.locale = Locale.ENGLISH;
        resources.updateConfiguration(configuration, displayMetrics);
        saveLanguageSettings(context, Constants.LANGUAGE_ENGLISH);
    }

    public static void setLanguageToChinese(Context context) {
        Resources resources = context.getResources();
        DisplayMetrics displayMetrics = resources.getDisplayMetrics();
        Configuration configuration = resources.getConfiguration();
        configuration.locale = Locale.SIMPLIFIED_CHINESE;
        resources.updateConfiguration(configuration, displayMetrics);
        saveLanguageSettings(context, Constants.LANGUAGE_SIMPLIFIED_CHINESE);
    }

    public static void saveLanguageSettings(Context context, int language) {
        SPUtil.setField(context, Constants.LOCAL_CONFIGURATION, Constants.LOCAL_LANGUAGE_CONFIG, language);
    }

    public static Locale getLanguageSetting(int language) {
        return languageMap.get(language);
    }
}
