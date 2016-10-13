package com.zpauly.githubapp.ui;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;
import android.graphics.drawable.shapes.Shape;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.zpauly.githubapp.R;
import com.zpauly.githubapp.utils.ColorUtil;

/**
 * Created by zpauly on 2016/10/10.
 */

public class IssueLabelView extends LinearLayout {
    private final String TAG = getClass().getName();

    private AppCompatTextView mLabelTV;
    private String label;
    private ImageView mLabelIV;

    private int labelPadding;
    private int labelContentMargin;
    private int imageSize;

    private GradientDrawable background;

    public IssueLabelView(Context context) {
        super(context);
        init();
    }

    public IssueLabelView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public IssueLabelView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public IssueLabelView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void initBackground() {
        setBackgroundResource(R.drawable.issue_label_bg);
        background = (GradientDrawable) getBackground();
    }

    private void init() {
        getParams();

        initBackground();

        setOrientation(LinearLayout.HORIZONTAL);
        setPadding(labelPadding, labelPadding, labelPadding, labelPadding);
        mLabelIV = new ImageView(getContext());
        mLabelTV = new AppCompatTextView(getContext());
        mLabelIV.setImageResource(R.drawable.ic_label);
        ViewGroup.LayoutParams imageLP = new ViewGroup.LayoutParams(imageSize, imageSize);
        addView(mLabelIV, imageLP);
        ViewGroup.LayoutParams textViewLP = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        mLabelTV.setPadding(labelContentMargin, 0, 0, 0);
        addView(mLabelTV, textViewLP);
    }

    private void getParams() {
        imageSize = getResources().getDimensionPixelSize(R.dimen.small_image_size);
        labelPadding = getResources().getDimensionPixelOffset(R.dimen.text_view_padding);
        labelContentMargin = labelPadding;
    }

    public void setText(int resId) {
        mLabelTV.setText(resId);
    }

    public void setText(CharSequence text) {
        mLabelTV.setText(text);
        invalidate();
    }

    public void setBgColor(int color) {
        String c = "#" + color;
        background.setColor(color);
        mLabelTV.setTextColor(ColorUtil.computeTextColorFromBackgroundColor(c));
    }

    public void setBgColor(String color) {
        int c = Color.parseColor(color);
        background.setColor(c);
        mLabelTV.setTextColor(ColorUtil.computeTextColorFromBackgroundColor(color));
    }

    public String getText() {
        return mLabelTV.getText().toString();
    }
}
