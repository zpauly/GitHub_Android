package com.zpauly.githubapp;

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

    //github api
    String GITHUB_API_URL = "https://api.github.com/";


    long CLICK_EXIT_TIME = 2000;

    int SCROLL_THRESHOLD = 4;
}
