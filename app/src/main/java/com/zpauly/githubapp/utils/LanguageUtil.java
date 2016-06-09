package com.zpauly.githubapp.utils;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.util.DisplayMetrics;

import java.util.Locale;

/**
 * Created by zpauly on 16-6-8.
 */
public class LanguageUtil {
    public static void setLanguageToEnglish(Context context) {
        Resources resources = context.getResources();
        DisplayMetrics displayMetrics = resources.getDisplayMetrics();
        Configuration configuration = resources.getConfiguration();
        configuration.locale = Locale.ENGLISH;
        resources.updateConfiguration(configuration, displayMetrics);
    }

    public static void setLanguageToChinese(Context context) {
        Resources resources = context.getResources();
        DisplayMetrics displayMetrics = resources.getDisplayMetrics();
        Configuration configuration = resources.getConfiguration();
        configuration.locale = Locale.SIMPLIFIED_CHINESE;
        resources.updateConfiguration(configuration, displayMetrics);
    }


}
