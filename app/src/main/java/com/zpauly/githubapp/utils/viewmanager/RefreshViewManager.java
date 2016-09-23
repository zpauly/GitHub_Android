package com.zpauly.githubapp.utils.viewmanager;

import android.view.View;

import com.zpauly.githubapp.listener.LoadListener;
import com.zpauly.githubapp.ui.RefreshView;

/**
 * Created by zpauly on 16/9/23.
 */

public abstract class RefreshViewManager implements ViewManager, LoadListener {
    private RefreshView refreshView;
    private View otherView;

    public RefreshViewManager(RefreshView refreshView, View otherView) {
        this.refreshView = refreshView;
        this.otherView = otherView;
    }

    @Override
    public void manager() {
        setRefreshView(refreshView, otherView);
        refreshView.startRefresh();
    }

    public void setRefreshView(final RefreshView refreshView, final View otherView) {
        refreshView.setOnRefreshStateListener(new RefreshView.OnRefreshStateListener() {
            @Override
            public void beforeRefreshing() {
                load();
            }

            @Override
            public void onRefreshSuccess() {
                refreshView.setVisibility(View.GONE);
                otherView.setVisibility(View.VISIBLE);
            }

            @Override
            public void onRefreshFail() {
                refreshView.setVisibility(View.VISIBLE);
                otherView.setVisibility(View.GONE);
            }
        });
    }
}
