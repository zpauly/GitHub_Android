package com.zpauly.githubapp.utils;

import android.content.Context;
import android.support.v7.app.AlertDialog;

/**
 * Created by zpauly on 16-6-8.
 */
public class DisplayUtil {
    public static AlertDialog showMutiChoiceDialog(Context context) {
        AlertDialog alertDialog = new AlertDialog.Builder(context)
                .show();
        return alertDialog;
    }
}
