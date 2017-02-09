package com.zpauly.githubapp.view.repositories;

import android.content.Context;
import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.zpauly.githubapp.R;
import com.zpauly.githubapp.adapter.ReleasesRecyclerViewAdapter;
import com.zpauly.githubapp.entity.response.repos.ReleaseBean;
import com.zpauly.githubapp.presenter.repos.ReleasesContract;
import com.zpauly.githubapp.presenter.repos.ReleasesPresenter;
import com.zpauly.githubapp.widget.RefreshView;
import com.zpauly.githubapp.utils.viewmanager.LoadMoreInSwipeRefreshLayoutMoreManager;
import com.zpauly.githubapp.utils.viewmanager.RefreshViewManager;
import com.zpauly.githubapp.view.ToolbarActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by zpauly on 16/9/24.
 */

public class RepoReleasesActivity extends ToolbarActivity implements ReleasesContract.View {
    private final String TAG = getClass().getName();

    public static final String OWNER = "OWNER";
    public static final String REPO = "REPO";

    private ReleasesContract.Presenter mPresenter;

    private String owner;
    private String repo;

    @BindView(R.id.releases_RefreshView) public RefreshView mRefreshView;
    @BindView(R.id.releases_SRLayout) public SwipeRefreshLayout mReleasesSRLayout;
    @BindView(R.id.releases_RV) public RecyclerView mReleasesRV;

    private ReleasesRecyclerViewAdapter mReleasesAdapter;

    private List<ReleaseBean> list = new ArrayList<>();

    private LoadMoreInSwipeRefreshLayoutMoreManager loadMoreInSwipeRefreshLayoutMoreManager;
    private RefreshViewManager refreshViewManager;

    @Override
    protected void onStop() {
        mPresenter.stop();
        super.onStop();
    }

    @Override
    public void initViews() {
        new ReleasesPresenter(this, this);

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

    @Override
    protected void setToolbar() {
        super.setToolbar();
        setToolbarTitle(R.string.releases);
        setOnToolbarNavClickedListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    @Override
    public void initContent() {
        super.initContent();
        setContent(R.layout.content_releases);
    }

    @Override
    protected void getParams() {
        owner = getIntent().getStringExtra(OWNER);
        repo = getIntent().getStringExtra(REPO);
    }

    public static void launchActivity(Context context, String owner, String repo) {
        Intent intent = new Intent();
        intent.putExtra(OWNER, owner);
        intent.putExtra(REPO, repo);
        intent.setClass(context, RepoReleasesActivity.class);
        context.startActivity(intent);
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
        mReleasesAdapter = new ReleasesRecyclerViewAdapter(this);
        mReleasesRV.setLayoutManager(new LinearLayoutManager(this));
        mReleasesRV.setAdapter(mReleasesAdapter);
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
        if (mRefreshView.isRefreshSuccess()) {
            mRefreshView.refreshFail();
        }
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
