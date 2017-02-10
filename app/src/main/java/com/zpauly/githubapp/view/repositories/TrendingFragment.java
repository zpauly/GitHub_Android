package com.zpauly.githubapp.view.repositories;

import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.zpauly.githubapp.R;
import com.zpauly.githubapp.adapter.TrendingRecyclerViewAdapter;
import com.zpauly.githubapp.entity.response.repos.RepositoriesBean;
import com.zpauly.githubapp.listener.OnMenuItemSelectedListener;
import com.zpauly.githubapp.network.repositories.RepositoriesService;
import com.zpauly.githubapp.presenter.repos.TrendingContract;
import com.zpauly.githubapp.presenter.repos.TrendingPresenter;
import com.zpauly.githubapp.utils.viewmanager.RefreshViewManager;
import com.zpauly.githubapp.view.ToolbarMenuFragment;
import com.zpauly.githubapp.widget.RefreshView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by zpauly on 2017/2/9.
 */

public class TrendingFragment extends ToolbarMenuFragment implements TrendingContract.View {
    private TrendingContract.Presenter mPresenter;

    @BindView(R.id.RefreshView) public RefreshView mRefreshView;
    @BindView(R.id.trending_SRLayout) public SwipeRefreshLayout mSRLayout;
    @BindView(R.id.trending_RV) public RecyclerView mTrendingRV;

    private TrendingRecyclerViewAdapter mAdapter;

    private RefreshViewManager refreshViewManager;

    private String since = RepositoriesService.TODAY_URL;
    private List<RepositoriesBean> list = new ArrayList<>();

    @Override
    public void onStop() {
        if (mPresenter != null) {
            mPresenter.stop();
        }
        super.onStop();
    }

    @Override
    public void inflateMenu() {
        inflateMenu(R.menu.trending_toolbar_menu);
    }

    @Override
    public void createMenu(Menu menu) {
        setOnMenuItemSelectedListener(new OnMenuItemSelectedListener() {
            @Override
            public void onItemSelected(MenuItem item) {
                if (!mRefreshView.isRefreshSuccess()) {
                    return;
                }
                item.setChecked(true);
                switch (item.getItemId()) {
                    case R.id.trending_toolbar_time_today:
                        since = RepositoriesService.TODAY_URL;
                        mSRLayout.setRefreshing(true);
                        getTrendingRepos();
                        break;
                    case R.id.trending_toolbar_item_week:
                        since = RepositoriesService.WEEK_URL;
                        mSRLayout.setRefreshing(true);
                        getTrendingRepos();
                        break;
                    case R.id.trending_toolbar_item_month:
                        since = RepositoriesService.MONTH_URL;
                        mSRLayout.setRefreshing(true);
                        getTrendingRepos();
                        break;
                    default:
                        break;
                }
            }
        });
    }

    @Override
    protected void initViews(View view) {
        new TrendingPresenter(getContext(), this);

        setupRecyclerView();
        setupSwipeRefreshView();

        refreshViewManager = new RefreshViewManager(mRefreshView, mSRLayout) {
            @Override
            public void load() {
                getTrendingRepos();
            }
        };
        setViewManager(refreshViewManager);
    }

    private void setupRecyclerView() {
        mAdapter = new TrendingRecyclerViewAdapter(getContext());
        mTrendingRV.setLayoutManager(new LinearLayoutManager(getContext()));
        mTrendingRV.setAdapter(mAdapter);
    }

    private void setupSwipeRefreshView() {
        mSRLayout.measure(View.MEASURED_SIZE_MASK, View.MEASURED_HEIGHT_STATE_SHIFT);
        mSRLayout.setColorSchemeResources(R.color.colorAccent);
        mSRLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getTrendingRepos();
            }
        });
    }

    private void getTrendingRepos() {
        mPresenter.getTrendingRepos();
    }

    @Override
    protected View setContentView(LayoutInflater inflater, @Nullable ViewGroup container) {
        return inflater.inflate(R.layout.fragment_trending, container, false);
    }

    @Override
    public String getLang() {
        return null;
    }

    @Override
    public String getSince() {
        return since;
    }

    @Override
    public void gettingTrendingRepos(List<RepositoriesBean> been) {
        list.clear();
        list.addAll(been);
    }

    @Override
    public void getTrendingReposSuccess() {
        mSRLayout.setRefreshing(false);
        if (!mRefreshView.isRefreshSuccess()) {
            mRefreshView.refreshSuccess();
        }
        mAdapter.swapAllData(list);
    }

    @Override
    public void getTrendingReposFail() {
        mSRLayout.setRefreshing(false);
        if (!mRefreshView.isRefreshSuccess()) {
            mRefreshView.refreshFail();
        }
        Snackbar.make(mRefreshView, R.string.error_occurred, Snackbar.LENGTH_SHORT);
    }

    @Override
    public void setPresenter(TrendingContract.Presenter presenter) {
        this.mPresenter = presenter;
    }
}
