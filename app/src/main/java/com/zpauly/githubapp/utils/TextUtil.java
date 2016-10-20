package com.zpauly.githubapp.utils;

import android.text.Html;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.widget.TextView;

/**
 * Created by zpauly on 16-8-1.
 */

public class TextUtil {
    private static final String TAG = TextUtil.class.getName();

    public static final String timeConverter(String time) {
        return time.substring(0, 4) + "/" + time.substring(5, 7) + "/" + time.substring(8, 10);
    }

    public static void showReadMe(TextView textView, String content, Html.ImageGetter imageGetter) {
        content = HtmlUtil.format(content);
        Spanned spanned = Html.fromHtml(content, imageGetter, HtmlUtil.getTagHandler());
        textView.setText(spanned);
        textView.setMovementMethod(new LinkMovementMethod());
    }
}
