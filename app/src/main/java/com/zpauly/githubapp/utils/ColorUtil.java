package com.zpauly.githubapp.utils;

import android.graphics.Color;

/**
 * Created by zpauly on 2016/10/13.
 */

public class ColorUtil {

    public static int computeTextColorFromBackgroundColor(String colorString){
        long color = Long.parseLong(colorString.substring(1), 16);
        if (colorString.length() == 7) {
            final int r = (int)((color & 0x0000000000ff0000) >> 16);
            final int g = (int)((color & 0x000000000000ff00) >> 8);
            final int b = (int)(color & 0x00000000000000ff);
            if ((r*0.299 + g*0.587 + b*0.114) > 186){
                return Color.BLACK;
            }else{
                return Color.WHITE;
            }
        } else if (colorString.length() != 9) {
            throw new IllegalArgumentException("Unknown color");
        }
        return Color.WHITE;
    }
}
