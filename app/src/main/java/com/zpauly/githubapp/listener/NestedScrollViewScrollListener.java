package com.zpauly.githubapp.listener;

import android.support.v4.widget.NestedScrollView;

import com.zpauly.githubapp.Constants;

/**
 * Created by root on 16-4-10.
 */
public abstract class NestedScrollViewScrollListener implements NestedScrollView.OnScrollChangeListener {


    @Override
    public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
        if (Math.abs(scrollY - oldScrollY) > Constants.SCROLL_THRESHOLD) {
            if (scrollY > oldScrollY) {
                onScrollUp();
            } else {
                onScrollDown();
            }
        }
    }

    public abstract void onScrollUp();

    public abstract void onScrollDown();
}
