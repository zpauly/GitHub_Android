package com.zpauly.githubapp.view.repositories;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zpauly.githubapp.R;
import com.zpauly.githubapp.adapter.ContributorsRecyclerViewAdapter;
import com.zpauly.githubapp.base.BaseFragment;
import com.zpauly.githubapp.entity.response.repos.ContributorBean;
import com.zpauly.githubapp.presenter.repos.ContributorsContract;
import com.zpauly.githubapp.presenter.repos.ContributorsPresenter;
import com.zpauly.githubapp.ui.RefreshView;
import com.zpauly.githubapp.utils.viewmanager.LoadMoreInSwipeRefreshLayoutMoreManager;
import com.zpauly.githubapp.utils.viewmanager.RefreshViewManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zpauly on 16/9/25.
 */

public class ContributorsFragment extends BaseFragment implements ContributorsContract.View {
    private final String TAG = getClass().getName();

    private ContributorsContract.Presenter mPresenter;

    private String owner;
    private String repo;

    private RefreshView mRefreshView;
    private SwipeRefreshLayout mContributorsSRLayout;
    private RecyclerView mContributorsRV;

    private ContributorsRecyclerViewAdapter mContributorsAdapter;

    private LoadMoreInSwipeRefreshLayoutMoreManager loadMoreInSwipeRefreshLayoutMoreManager;
    private RefreshViewManager refreshViewManager;

    private List<ContributorBean> list = new ArrayList<>();

    @Override
    public void onStop() {
        mPresenter.stop();
        super.onStop();
    }

    @Override
    protected void initViews(View view) {
        getAttrs();

        new ContributorsPresenter(getContext(), this);

        mRefreshView = (RefreshView) view.findViewById(R.id.contributors_RefreshView);
        mContributorsSRLayout = (SwipeRefreshLayout) view.findViewById(R.id.contributors_SRLayout);
        mContributorsRV = (RecyclerView) view.findViewById(R.id.contributors_RV);

        setupSwipeRefreshLayout();
        setupRecyclerView();

        setViewManager(new LoadMoreInSwipeRefreshLayoutMoreManager(mContributorsRV, mContributorsSRLayout) {
            @Override
            public void load() {
                getContributors();
            }
        }, new RefreshViewManager(mRefreshView, mContributorsSRLayout) {
            @Override
            public void load() {
                getContributors();
            }
        });

        loadMoreInSwipeRefreshLayoutMoreManager = getViewManager(LoadMoreInSwipeRefreshLayoutMoreManager.class);
        refreshViewManager = getViewManager(RefreshViewManager.class);
    }

    private void getContributors() {
        mPresenter.getContributors();
    }

    private void setupSwipeRefreshLayout() {
        mContributorsSRLayout.setColorSchemeResources(R.color.colorAccent);
        mContributorsSRLayout.measure(View.MEASURED_SIZE_MASK, View.MEASURED_HEIGHT_STATE_SHIFT);
        mContributorsSRLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.setPageId(1);
                loadMoreInSwipeRefreshLayoutMoreManager.setSwipeRefreshLayoutRefreshing(mContributorsAdapter);
            }
        });
    }

    private void setupRecyclerView() {
        mContributorsAdapter = new ContributorsRecyclerViewAdapter(getContext());
        mContributorsRV.setLayoutManager(new LinearLayoutManager(getContext()));
        mContributorsRV.setAdapter(mContributorsAdapter);
    }

    private void getAttrs() {
        Bundle bundle = getArguments();
        if (bundle != null) {
            owner = bundle.getString(ContributorsActivity.OWNER);
            repo = bundle.getString(ContributorsActivity.REPO);
        }
    }

    @Override
    protected View setContentView(LayoutInflater inflater, @Nullable ViewGroup container) {
        return inflater.inflate(R.layout.fragment_contributors, container, false);
    }

    @Override
    public void getContributorsSuccess() {
        mContributorsAdapter.swapAllData(list);
        mContributorsSRLayout.setRefreshing(false);
        if (!mRefreshView.isRefreshSuccess()) {
            mRefreshView.refreshSuccess();
        }
    }

    @Override
    public void getContributorsFail() {
        mContributorsSRLayout.setRefreshing(false);
        mRefreshView.refreshFail();
    }

    @Override
    public void gettingContributors(List<ContributorBean> contributorBeen) {
        loadMoreInSwipeRefreshLayoutMoreManager.hasNoMoreData(contributorBeen, mContributorsAdapter);
        if (mContributorsSRLayout.isRefreshing()) {
            list.clear();
        }
        list.addAll(contributorBeen);
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
    public void setPresenter(ContributorsContract.Presenter presenter) {
        this.mPresenter = presenter;
    }
}
