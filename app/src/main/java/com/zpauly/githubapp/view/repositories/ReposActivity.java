package com.zpauly.githubapp.view.repositories;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;

import com.zpauly.githubapp.R;
import com.zpauly.githubapp.adapter.ViewPagerAdapter;
import com.zpauly.githubapp.entity.response.repos.RepositoriesBean;
import com.zpauly.githubapp.presenter.repos.ReposContract;
import com.zpauly.githubapp.presenter.repos.ReposPresenter;
import com.zpauly.githubapp.ui.RefreshView;
import com.zpauly.githubapp.view.ToolbarActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zpauly on 16-7-18.
 */

public class ReposActivity extends ToolbarActivity implements ReposContract.View {
    private final String TAG = getClass().getName();

    public static final String REPOS = "REPOS";

    private ReposContract.Presenter mPresenter;

    private AppBarLayout mReposABLayout;
    private SwipeRefreshLayout mReposSwLayout;
    private TabLayout mReposTBLayout;
    private ViewPager mReposVP;

    private RefreshView mRefreshView;

    private ViewPagerAdapter adapter;

    private List<ReposFragment> mFragmentList = new ArrayList<>();

    private ArrayList<RepositoriesBean> repoList = new ArrayList<>();

    @Override
    protected void onStop() {
        if (mPresenter != null) {
            mPresenter.stop();
        }
        super.onStop();
    }

    @Override
    public void initViews() {
        new ReposPresenter(this, this);

        mRefreshView = (RefreshView) findViewById(R.id.repos_RefreshView);
        mReposABLayout = (AppBarLayout) findViewById(R.id.repos_ABLayout);
        mReposSwLayout = (SwipeRefreshLayout) findViewById(R.id.repos_SWLayout);
        mReposTBLayout = (TabLayout) findViewById(R.id.repos_TBLayout);
        mReposVP = (ViewPager) findViewById(R.id.repos_VP);

        setupSwipeRefreshLayout();

        setupViewPager();
        setupTabLayout();

        mRefreshView.setOnRefreshStateListener(new RefreshView.OnRefreshStateListener() {
            @Override
            public void beforeRefreshing() {
                loadOwnerRepos();
            }

            @Override
            public void onRefreshSuccess() {
                mRefreshView.setVisibility(View.GONE);
                mReposSwLayout.setVisibility(View.VISIBLE);
            }

            @Override
            public void onRefreshFail() {
                mRefreshView.setVisibility(View.VISIBLE);
                mReposSwLayout.setVisibility(View.GONE);
            }
        });
        mRefreshView.startRefresh();
    }

    @Override
    protected void setToolbar() {
        super.setToolbar();
        setToolbarTitle(R.string.repos);
        setOnToolbarNavClickedListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void addPagers() {
        adapter.addFragment(createFragment(ReposFragment.ALL), getString(R.string.repos_all));
        adapter.addFragment(createFragment(ReposFragment.PUBLIC), getString(R.string.repos_public));
        if (username == null) {
            adapter.addFragment(createFragment(ReposFragment.PRIVATE), getString(R.string.repos_private));
        }
        adapter.addFragment(createFragment(ReposFragment.SOURCE), getString(R.string.repos_sources));
        adapter.addFragment(createFragment(ReposFragment.FORK), getString(R.string.repos_forks));
        adapter.notifyDataSetChanged();
    }

    private void setupViewPager() {
        adapter = new ViewPagerAdapter(getSupportFragmentManager());
        mReposVP.setAdapter(adapter);
    }

    private Fragment createFragment(int tag) {
        Bundle bundle = new Bundle();
        bundle.putInt(ReposFragment.FRAGMENT_TAG, tag);
        bundle.putParcelableArrayList(REPOS, repoList);
        ReposFragment fragment = new ReposFragment();
        fragment.setArguments(bundle);
        mFragmentList.add(fragment);
        return fragment;
    }

    public static void launchActivity(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, ReposActivity.class);
        context.startActivity(intent);
    }

    private void setupTabLayout() {
        mReposTBLayout.setupWithViewPager(mReposVP);
    }

    private void setupSwipeRefreshLayout() {
        mReposSwLayout.measure(View.MEASURED_SIZE_MASK, View.MEASURED_HEIGHT_STATE_SHIFT);
        mReposSwLayout.setColorSchemeResources(R.color.colorAccent);
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
        repoList.addAll(list);
    }

    @Override
    public void loadFail() {
        mReposSwLayout.setRefreshing(false);
        mRefreshView.refreshFail();
    }

    @Override
    public void loadSuccess() {
        mReposSwLayout.setRefreshing(false);
        mReposSwLayout.setEnabled(false);
        addPagers();
        if (!mRefreshView.isRefreshSuccess()) {
            mRefreshView.refreshSuccess();
        }
    }

    @Override
    public String getUsername() {
        return username;
    }
}
