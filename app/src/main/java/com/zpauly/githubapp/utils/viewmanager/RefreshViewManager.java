package com.zpauly.githubapp.utils.viewmanager;

import android.view.View;

import com.zpauly.githubapp.listener.LoadListener;
import com.zpauly.githubapp.ui.RefreshView;

/**
 * Created by zpauly on 16/9/23.
 */

public abstract class RefreshViewManager implements ViewManager, LoadListener {
    private RefreshView refreshView;
    private View[] otherViews;

    public RefreshViewManager(RefreshView refreshView, View... otherView) {
        this.refreshView = refreshView;
        this.otherViews = otherView;
    }

    @Override
    public void manager() {
        setRefreshView(refreshView, this.otherViews);
        refreshView.startRefresh();
    }

    public void setRefreshView(final RefreshView refreshView, final View... otherViews) {
        refreshView.setOnRefreshStateListener(new RefreshView.OnRefreshStateListener() {
            @Override
            public void beforeRefreshing() {
                load();
            }

            @Override
            public void onRefreshSuccess() {
                refreshView.setVisibility(View.GONE);
                for (View view : otherViews)
                    view.setVisibility(View.VISIBLE);
            }

            @Override
            public void onRefreshFail() {
                refreshView.setVisibility(View.VISIBLE);
                for (View view : otherViews)
                    view.setVisibility(View.GONE);
            }
        });
    }
}
