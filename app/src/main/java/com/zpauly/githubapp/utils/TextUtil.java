package com.zpauly.githubapp.utils;

/**
 * Created by zpauly on 16-8-1.
 */

public class TextUtil {
    public static final String timeConverter(String time) {
        return time.substring(0, 4) + "/" + time.substring(5, 7) + "/" + time.substring(8, 10);
    }
}
