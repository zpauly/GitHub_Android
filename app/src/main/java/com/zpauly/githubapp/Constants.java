package com.zpauly.githubapp;

import com.protectsoft.webviewcode.Settings;

/**
 * Created by zpauly on 16-6-8.
 */
public interface Constants {
    long MAX_MEMORY = Runtime.getRuntime().maxMemory();

    /**
     * local configuration in sharedprefrences
     */
    //sp table name
    String LOCAL_CONFIGURATION = "LOCAL_CONFIGURATION";

    //first use
    String FIRST_USED = "FIRST_USED";

    //languages key
    String LOCAL_LANGUAGE_CONFIG = "LOCAL_LANGUAGE_CONFIG";

    //languages value:english
    int LANGUAGE_ENGLISH = 0;

    //languages value:simplified chinese
    int LANGUAGE_SIMPLIFIED_CHINESE = 1;

    //code style
    String CODE_STYLE = "CODE_STYLE";

    //day night mode key
    String DAY_NIGHT_MODE = "DAY_NIGHT_MODE";

    //day mode
    int DAY_MODE = 0;

    //night_mode
    int NIGHT_MODE = 1;

    String[] codeStyleName = {
            "GITHUB(DEFAULT)",
            "AGATE",
            "ANDROIDSTUDIO",
            "ARDUINO LIGHT",
            "ARTA",
            "ASCETIC",
            "ATELIER DARK",
            "ATELIER LIGHT",
            "ATELIER FOREST DARK",
            "DARKSTYLE",
            "DARKULA",
            "DOCCO",
            "FAR",
            "GITHUB GIST",
            "GOOGLECODE",
            "IDEA",
            "MAGULA",
            "OBSIDIAN",
            "XCODE"
    };

    String[] codeStyle = {
            Settings.WithStyle.GITHUB,
            Settings.WithStyle.AGATE,
            Settings.WithStyle.ANDROIDSTUDIO,
            Settings.WithStyle.ARDUINO_LIGHT,
            Settings.WithStyle.ARTA,
            Settings.WithStyle.ASCETIC,
            Settings.WithStyle.ATELIER_DARK,
            Settings.WithStyle.ARDUINO_LIGHT,
            Settings.WithStyle.ATELIER_FOREST_DARK,
            Settings.WithStyle.DARKSTYLE,
            Settings.WithStyle.DARKULA,
            Settings.WithStyle.DOCCO,
            Settings.WithStyle.FAR,
            Settings.WithStyle.GITHUBGIST,
            Settings.WithStyle.GOOGLECODE,
            Settings.WithStyle.IDEA,
            Settings.WithStyle.MAGULA,
            Settings.WithStyle.OBSIDIAN,
            Settings.WithStyle.XCODE
    };

    /**
     * local user information in sharedprefrences
     */
    //sp table name
    String USER_INFO = "USER_INFO";

    //user username
    String USER_USERNAME = "USER_USERNAME";

    //user password
    String USER_PASSWORD = "USER_PASSWORD";

    //user id
    String USER_ID = "USER_ID";

    //user email
    String USER_EMAIL = "USER_EMAIL";

    //user_avatar
    String USER_AVATAR = "USER_AVATAR";

    //user auth
    String USER_AUTH = "USER_AUTH";

    //user login
    String USER_LOGIN = "USER_LOGIN";

    /**
     * net
     */
    ///time out
    long DEFAULT_TIMEOUT = 10000;

    long CLICK_EXIT_TIME = 2000;

    int SCROLL_THRESHOLD = 4;
}
