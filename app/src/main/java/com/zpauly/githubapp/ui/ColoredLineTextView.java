package com.zpauly.githubapp.ui;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by zpauly on 16/9/21.
 */

public class ColoredLineTextView extends LinearLayout implements Iterable<TextView> {
    private final String TAG = getClass().getName();

    private List<TextView> mTextViewList;
    private String[] contents;

    public ColoredLineTextView(Context context) {
        super(context);
        init();
    }

    public ColoredLineTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ColoredLineTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public ColoredLineTextView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    public void init() {
        setOrientation(VERTICAL);
        mTextViewList = new ArrayList<>();
    }

    public void setText(String[] strings) {
        contents = strings;
        for (int i = 0; i < strings.length; i++) {
            TextView textView = new TextView(getContext());
            mTextViewList.add(textView);
            setupTextView(textView, strings[i]);
        }
        invalidate();
    }

    public String[] getText() {
        return contents;
    }

    private void setupTextView(TextView textView, String text) {
        LinearLayout.LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        addView(textView, layoutParams);
        textView.setText(text);
    }

    public void setLineBackgroundResource(int index, int resId) {
        if (index >= mTextViewList.size()) {
            return;
        }
        mTextViewList.get(index).setBackgroundResource(resId);
    }

    public void setLineBackgroundColor(int index, int color) {
        if (index >= mTextViewList.size()) {
            return;
        }
        mTextViewList.get(index).setBackgroundColor(color);
    }

    public void setLineBackground(int index, Drawable drawable) {
        if (index >= mTextViewList.size()) {
            return;
        }
        mTextViewList.get(index).setBackgroundDrawable(drawable);
    }

    private String getLineText(int lineNum) {
        return contents[lineNum];
    }

    @Override
    public Iterator<TextView> iterator() {
        return mTextViewList.iterator();
    }
}
