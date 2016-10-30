package com.zpauly.githubapp.utils;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.webkit.WebView;

import com.protectsoft.webviewcode.Codeview;
import com.protectsoft.webviewcode.Settings;
import com.zpauly.githubapp.Constants;
import com.zpauly.githubapp.view.settings.SettingsActivity;

/**
 * Created by zpauly on 16-6-8.
 */
public class DisplayUtil {
    private static final String TAG = DisplayUtil.class.getName();

    public static AlertDialog showMutiChoiceDialog(Context context) {
        AlertDialog alertDialog = new AlertDialog.Builder(context)
                .show();
        return alertDialog;
    }

    public static void showCode(WebView mWB, Context context, String code, String lang) {
        int codeStyle = SPUtil.getInt(context, Constants.LOCAL_CONFIGURATION, Constants.CODE_STYLE, 0);
        Log.i(TAG, "code style = " + Constants.codeStyleName[codeStyle]);
        Codeview.with(context)
                .withCode(code)
                .setStyle(Constants.codeStyle[codeStyle])
                .setLang(lang)
                .into(mWB);
    }
}
