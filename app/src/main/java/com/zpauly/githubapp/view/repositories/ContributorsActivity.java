package com.zpauly.githubapp.view.repositories;

import android.content.Context;
import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.zpauly.githubapp.R;
import com.zpauly.githubapp.adapter.ContributorsRecyclerViewAdapter;
import com.zpauly.githubapp.entity.response.repos.ContributorBean;
import com.zpauly.githubapp.presenter.repos.ContributorsContract;
import com.zpauly.githubapp.presenter.repos.ContributorsPresenter;
import com.zpauly.githubapp.ui.RefreshView;
import com.zpauly.githubapp.utils.viewmanager.LoadMoreInSwipeRefreshLayoutMoreManager;
import com.zpauly.githubapp.utils.viewmanager.RefreshViewManager;
import com.zpauly.githubapp.view.ToolbarActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zpauly on 16/9/25.
 */

public class ContributorsActivity extends ToolbarActivity implements ContributorsContract.View {
    private final String TAG = getClass().getName();

    public static final String OWNER = "OWNER";
    public static final String REPO = "REPO";

    private String owner;
    private String repo;

    private ContributorsContract.Presenter mPresenter;

    private RefreshView mRefreshView;
    private SwipeRefreshLayout mContributorsSRLayout;
    private RecyclerView mContributorsRV;

    private ContributorsRecyclerViewAdapter mContributorsAdapter;

    private LoadMoreInSwipeRefreshLayoutMoreManager loadMoreInSwipeRefreshLayoutMoreManager;
    private RefreshViewManager refreshViewManager;

    private List<ContributorBean> list = new ArrayList<>();

    @Override
    protected void onStop() {
        mPresenter.stop();
        super.onStop();
    }

    @Override
    public void initViews() {
        getAttrs();

        setContent(R.layout.content_contributors);

        new ContributorsPresenter(this, this);

        mRefreshView = (RefreshView) findViewById(R.id.contributors_RefreshView);
        mContributorsSRLayout = (SwipeRefreshLayout) findViewById(R.id.contributors_SRLayout);
        mContributorsRV = (RecyclerView) findViewById(R.id.contributors_RV);

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

    private void getAttrs() {
        owner = getIntent().getStringExtra(OWNER);
        repo = getIntent().getStringExtra(REPO);
    }

    @Override
    protected void setToolbar() {
        super.setToolbar();
        setToolbarTitle(R.string.contributors);
        setOnToolbarNavClickedListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    public static void launchActivity(Context context, String owner, String repo) {
        Intent intent = new Intent();
        intent.putExtra(OWNER, owner);
        intent.putExtra(REPO, repo);
        intent.setClass(context, ContributorsActivity.class);
        context.startActivity(intent);
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
        mContributorsAdapter = new ContributorsRecyclerViewAdapter(this);
        mContributorsRV.setLayoutManager(new LinearLayoutManager(this));
        mContributorsRV.setAdapter(mContributorsAdapter);
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
