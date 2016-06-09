package com.zpauly.githubapp.utils;

import okhttp3.Credentials;

/**
 * Created by zpauly on 16-6-9.
 */
public class AuthUtil {
    public static String generateAuth(String username, String password) {
        String basicAuth = Credentials.basic(username, password);
        return basicAuth;
    }
}
