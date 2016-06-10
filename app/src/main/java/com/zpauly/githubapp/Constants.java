package com.zpauly.githubapp;

/**
 * Created by zpauly on 16-6-8.
 */
public interface Constants {
    /**
     * local configuration in sharedprefrences
     */
    //sp name
    String LOCAL_CONFIGURATION = "LOCAL_CONFIGURATION";

    //languages key
    String LOCAL_LANGUAGE_CONFIG = "LOCAL_LANGUAGE_CONFIG";

    //languages value:english
    int LANGUAGE_ENGLISH = 0;

    //languages value:simplified chinese
    int LANGUAGE_SIMPLIFIED_CHINESE = 1;


    /**
     * net
     */
    ///time out
    long DEFAULT_TIMEOUT = 5000;

    //github api
    String GITHUB_API_URL = "https://api.github.com/";
}
