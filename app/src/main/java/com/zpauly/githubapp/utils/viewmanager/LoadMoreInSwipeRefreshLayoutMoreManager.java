package com.zpauly.githubapp.utils.viewmanager;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;

import com.zpauly.githubapp.adapter.LoadMoreRecyclerViewAdapter;

/**
 * Created by zpauly on 16/9/23.
 */

public abstract class LoadMoreInSwipeRefreshLayoutMoreManager extends LoadMoreManager {

    public LoadMoreInSwipeRefreshLayoutMoreManager(RecyclerView recyclerView, SwipeRefreshLayout swipeRefreshLayout) {
        super(recyclerView, swipeRefreshLayout);
    }

    public void setSwipeRefreshLayoutRefreshing(LoadMoreRecyclerViewAdapter adapter) {
        adapter.setHasLoading(true);
        load();
    }
}
