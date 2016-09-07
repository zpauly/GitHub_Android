package com.zpauly.githubapp.listener;

import android.support.v7.widget.RecyclerView;

import com.zpauly.githubapp.Constants;

/**
 * Created by zpauly on 16-4-7.
 */
public abstract class RecyclerViewScrollListener extends RecyclerView.OnScrollListener {
    public abstract void onScrollUp();

    public abstract void onScrollDown();

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        if (Math.abs(dy) > Constants.SCROLL_THRESHOLD) {
            if (dy > 0) {
                onScrollUp();
            } else {
                onScrollDown();
            }
        }
    }

    @Override
    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);
    }
}
