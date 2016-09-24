package com.zpauly.githubapp.view.repositories;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zpauly.githubapp.R;
import com.zpauly.githubapp.adapter.ReleasesRecyclerViewAdapter;
import com.zpauly.githubapp.base.BaseFragment;
import com.zpauly.githubapp.entity.response.repos.ReleaseBean;
import com.zpauly.githubapp.presenter.repos.ReleasesContract;
import com.zpauly.githubapp.presenter.repos.ReleasesPresenter;
import com.zpauly.githubapp.ui.RefreshView;
import com.zpauly.githubapp.utils.viewmanager.LoadMoreInSwipeRefreshLayoutMoreManager;
import com.zpauly.githubapp.utils.viewmanager.RefreshViewManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zpauly on 16/9/24.
 */

public class RepoReleasesFragment extends BaseFragment implements ReleasesContract.View {
    private final String TAG = getClass().getName();

    private ReleasesContract.Presenter mPresenter;

    private String owner;
    private String repo;

    private RefreshView mRefreshView;
    private SwipeRefreshLayout mReleasesSRLayout;
    private RecyclerView mReleasesRV;

    private ReleasesRecyclerViewAdapter mReleasesAdapter;

    private List<ReleaseBean> list = new ArrayList<>();

    private LoadMoreInSwipeRefreshLayoutMoreManager loadMoreInSwipeRefreshLayoutMoreManager;
    private RefreshViewManager refreshViewManager;

    @Override
    protected void initViews(View view) {
        getAttrs();

        new ReleasesPresenter(getContext(), this);

        mRefreshView = (RefreshView) view.findViewById(R.id.releases_RefreshView);
        mReleasesSRLayout = (SwipeRefreshLayout) view.findViewById(R.id.releases_SRLayout);
        mReleasesRV = (RecyclerView) view.findViewById(R.id.releases_RV);

        setupSwipeRefreshLayout();
        setupRecyclerView();

        setViewManager(new LoadMoreInSwipeRefreshLayoutMoreManager(mReleasesRV, mReleasesSRLayout) {
            @Override
            public void load() {
                getReleases();
            }
        }, new RefreshViewManager(mRefreshView, mReleasesSRLayout) {
            @Override
            public void load() {
                list.clear();
                getReleases();
            }
        });

        loadMoreInSwipeRefreshLayoutMoreManager = getViewManager(LoadMoreInSwipeRefreshLayoutMoreManager.class);
        refreshViewManager = getViewManager(RefreshViewManager.class);
    }

    private void setupSwipeRefreshLayout() {
        mReleasesSRLayout.setColorSchemeResources(R.color.colorAccent);
        mReleasesSRLayout.measure(View.MEASURED_SIZE_MASK, View.MEASURED_HEIGHT_STATE_SHIFT);
        mReleasesSRLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.setPageId(1);
                loadMoreInSwipeRefreshLayoutMoreManager.setSwipeRefreshLayoutRefreshing(mReleasesAdapter);
            }
        });
    }

    private void setupRecyclerView() {
        mReleasesAdapter = new ReleasesRecyclerViewAdapter(getContext());
        mReleasesRV.setLayoutManager(new LinearLayoutManager(getContext()));
        mReleasesRV.setAdapter(mReleasesAdapter);
    }

    private void getAttrs() {
        Bundle bundle = getArguments();
        if (bundle != null) {
            owner = bundle.getString(RepoReleasesActivity.OWNER);
            repo = bundle.getString(RepoReleasesActivity.REPO);
        }
    }

    @Override
    protected View setContentView(LayoutInflater inflater, @Nullable ViewGroup container) {
        return inflater.inflate(R.layout.fragment_releases, container, false);
    }

    private void getReleases() {
        mPresenter.getReleases();
    }

    @Override
    public void getReleasesSuccess() {
        mReleasesAdapter.swapAllData(list);
        mReleasesSRLayout.setRefreshing(false);
        if (!mRefreshView.isRefreshSuccess()) {
            mRefreshView.refreshSuccess();
        }
    }

    @Override
    public void getReleasesFail() {
        mReleasesSRLayout.setRefreshing(false);
        mRefreshView.refreshFail();
    }

    @Override
    public void gettingReleases(List<ReleaseBean> releaseBeen) {
        if (mReleasesSRLayout.isRefreshing()) {
            list.clear();
        }
        loadMoreInSwipeRefreshLayoutMoreManager.hasNoMoreData(releaseBeen, mReleasesAdapter);
        list.addAll(releaseBeen);
    }

    @Override
    public String getOwner() {
        return owner;
    }

    @Override
    public String getRepo() {
        return repo;
    }

    @Override
    public void setPresenter(ReleasesContract.Presenter presenter) {
        this.mPresenter = presenter;
    }
}
