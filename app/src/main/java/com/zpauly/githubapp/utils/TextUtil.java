package com.zpauly.githubapp.utils;

import android.text.Html;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by zpauly on 16-8-1.
 */

public class TextUtil {
    private static final String TAG = TextUtil.class.getName();

    public static final int CONTENT_READY = 0;
    public static final String MESSAGE_CONTENT = "MESSAGE_CONTENT";

    public static final String timeConverter(String time) {
        return time.substring(0, 4) + "/" + time.substring(5, 7) + "/" + time.substring(8, 10);
    }

    public static void showReadMe(final TextView textView, final String content, final Html.ImageGetter imageGetter) {
        Observable.just(content)
                .map(new Func1<String, Spanned>() {
                    @Override
                    public Spanned call(String s) {
                        s = HtmlUtil.format(s);
                        Spanned spanned = Html.fromHtml(s, imageGetter,
                                HtmlUtil.getTagHandler());
                        return spanned;
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Spanned>() {
                    @Override
                    public void call(Spanned spanned) {
                        textView.setText(spanned);
                        textView.setMovementMethod(new LinkMovementMethod());
                    }
                });
    }
}
