package com.zpauly.githubapp;

/**
 * Created by zpauly on 16-6-8.
 */
public interface Constants {
    /**
     * local configuration in sharedprefrences
     */
    //sp name
    public static final String LOCAL_CONFIGURATION = "LOCAL_CONFIGURATION";

    //languages key
    public static final String LOCAL_LANGUAGE_CONFIG = "LOCAL_LANGUAGE_CONFIG";

    //languages value:english
    public static final String LANGUAGE_ENGLISH = "0";

    //languages value:simplified chinese
    public static final String LANGUAGE_SIMPLIFIED_CHINESE = "1";


    /**
     * net
     */
    ///time out
    public static final long DEFAULT_TIMEOUT = 5000;

    //github api
    public static final String GITHUB_API_URL = "https://api.github.com/";
}
