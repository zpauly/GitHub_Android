package com.zpauly.githubapp.base;

import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.support.v7.app.AppCompatDelegate;
import android.util.DisplayMetrics;

import com.umeng.analytics.MobclickAgent;
import com.zpauly.githubapp.Constants;
import com.zpauly.githubapp.utils.LanguageUtil;
import com.zpauly.githubapp.utils.RetrofitUtil;
import com.zpauly.githubapp.utils.SPUtil;

/**
 * Created by zpauly on 16-6-10.
 */
public class BaseApplication extends Application {
    private static BaseApplication instance;

    public static final int DAY_MODE = 0;
    public static final int NIGHT_MODE = 1;

    private static int dayNightMode;

    static {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_AUTO);
    }

    public static void setDayNightMode(int mode, Context context) {
        dayNightMode = mode;
        SPUtil.putInt(context, Constants.LOCAL_CONFIGURATION, Constants.DAY_NIGHT_MODE, mode);
    }

    public static int getDayNightMode() {
        return dayNightMode;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        MobclickAgent.setScenarioType(this, MobclickAgent.EScenarioType.E_UM_NORMAL);
        MobclickAgent.openActivityDurationTrack(false);
        MobclickAgent.setDebugMode( true );
        dayNightMode = SPUtil.getInt(this, Constants.LOCAL_CONFIGURATION, Constants.DAY_NIGHT_MODE, DAY_MODE);

        RetrofitUtil.setupContext(getApplicationContext());

        getLocalLanguageSetting();
    }

    public static BaseApplication getInstance() {
        if (instance == null) {
            synchronized (BaseApplication.class) {
                instance = new BaseApplication();
            }
        }
        return instance;
    }

    private void getLocalLanguageSetting() {
        int language = SPUtil.getField(getApplicationContext(), Constants.LOCAL_CONFIGURATION, Constants.LOCAL_LANGUAGE_CONFIG, -1);

        if (language == -1) {
            return;
        }
        Resources resources = getApplicationContext().getResources();
        DisplayMetrics dm = resources.getDisplayMetrics();
        Configuration config = resources.getConfiguration();
        config.locale = LanguageUtil.getLanguageSetting(language);
        resources.updateConfiguration(config, dm);
    }
}
