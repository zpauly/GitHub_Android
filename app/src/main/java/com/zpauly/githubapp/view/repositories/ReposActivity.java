package com.zpauly.githubapp.view.repositories;

import android.support.design.widget.AppBarLayout;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.zpauly.githubapp.R;
import com.zpauly.githubapp.adapter.ReposRecyclerViewAdapter;
import com.zpauly.githubapp.db.ReposDao;
import com.zpauly.githubapp.entity.response.RepositoriesBean;
import com.zpauly.githubapp.presenter.repos.ReposContract;
import com.zpauly.githubapp.presenter.repos.ReposPresenter;
import com.zpauly.githubapp.view.ToolbarActivity;

import java.util.List;

/**
 * Created by zpauly on 16-7-18.
 */

public class ReposActivity extends ToolbarActivity implements ReposContract.View {
    private final String TAG = getClass().getName();

    private ReposContract.Presenter mPresenter;

    private AppBarLayout mReposABLayout;
    private SwipeRefreshLayout mReposSwLayout;
    private TabLayout mReposTBLayout;
    private ViewPager mReposVP;

    @Override
    public void initViews() {
        new ReposPresenter(this, this);

        mReposABLayout = (AppBarLayout) findViewById(R.id.repos_ABLayout);
        mReposSwLayout = (SwipeRefreshLayout) findViewById(R.id.repos_SWLayout);
        mReposTBLayout = (TabLayout) findViewById(R.id.repos_TBLayout);
        mReposVP = (ViewPager) findViewById(R.id.repos_VP);

        setupSwipeRefreshLayout();

        mReposSwLayout.setRefreshing(true);
        loadOwnerRepos();
    }

    @Override
    protected void setToolbar() {
        super.setToolbar();
        setToolbarTitle(R.string.repos);
    }

    private void setupSwipeRefreshLayout() {
        mReposSwLayout.measure(View.MEASURED_SIZE_MASK, View.MEASURED_HEIGHT_STATE_SHIFT);
        mReposSwLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadOwnerRepos();
            }
        });
    }

    private void loadOwnerRepos() {
        mPresenter.loadUserRepositories();
    }

    @Override
    public void initContent() {
        setContentView(R.layout.activity_repos);
    }

    @Override
    public void setPresenter(ReposContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void loadingRepos(List<RepositoriesBean> list) {
        ReposDao.deleteRepos();
        for (RepositoriesBean repo : list) {
            ReposDao.insertRepo(repo);
        }
    }

    @Override
    public void loadFail() {
        mReposSwLayout.setRefreshing(false);
    }

    @Override
    public void loadSuccess() {
        mReposSwLayout.setRefreshing(false);
        mReposSwLayout.setEnabled(false);
    }
}
