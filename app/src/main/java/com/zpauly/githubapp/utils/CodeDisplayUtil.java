package com.zpauly.githubapp.utils;

import android.graphics.Color;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.widget.TextView;

/**
 * Created by zpauly on 16-8-2.
 */

public class CodeDisplayUtil {
    public static void displayCode(String code, TextView textView) {
        SpannableString spannedPermissions = new SpannableString(
                "<uses-permission android:name=\"android.permission.GET_ACCOUNTS\"/>");
        spannedPermissions.setSpan(new ForegroundColorSpan(Color.CYAN), 0, 16,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannedPermissions.setSpan(new ForegroundColorSpan(Color.GREEN), 17,
                24, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannedPermissions.setSpan(new ForegroundColorSpan(Color.MAGENTA), 24,
                25, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannedPermissions.setSpan(new ForegroundColorSpan(Color.GREEN), 25,
                29, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannedPermissions.setSpan(new ForegroundColorSpan(Color.RED), 30, 63,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannedPermissions.setSpan(new ForegroundColorSpan(Color.CYAN), 63, 65,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        textView.setText(spannedPermissions);

        Spanned spannedCode = Html.fromHtml(code);

    }
}
