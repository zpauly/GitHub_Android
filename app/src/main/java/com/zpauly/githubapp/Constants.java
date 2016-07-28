package com.zpauly.githubapp;

/**
 * Created by zpauly on 16-6-8.
 */
public interface Constants {
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

    //user auth
    String USER_AUTH = "USER_AUTH";

    /**
     * net
     */
    ///time out
    long DEFAULT_TIMEOUT = 10000;

    //github api
    String GITHUB_API_URL = "https://api.github.com/";


    long CLICK_EXIT_TIME = 2000;
}
