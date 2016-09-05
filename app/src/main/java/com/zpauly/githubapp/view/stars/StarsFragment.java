package com.zpauly.githubapp.view.stars;

import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.zpauly.githubapp.R;
import com.zpauly.githubapp.adapter.ReposRecyclerViewAdapter;
import com.zpauly.githubapp.base.BaseFragment;
import com.zpauly.githubapp.db.ReposDao;
import com.zpauly.githubapp.db.ReposModel;
import com.zpauly.githubapp.entity.response.RepositoriesBean;
import com.zpauly.githubapp.listener.OnMenuItemSelectedListener;
import com.zpauly.githubapp.network.activity.ActivityService;
import com.zpauly.githubapp.presenter.star.StarContract;
import com.zpauly.githubapp.presenter.star.StarPresenter;
import com.zpauly.githubapp.ui.RefreshView;
import com.zpauly.githubapp.view.ToolbarMenuFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zpauly on 16-7-16.
 */

public class StarsFragment extends ToolbarMenuFragment implements StarContract.View {
    private final String TAG = getClass().getName();

    private StarContract.Presenter mPresenter;

    private SwipeRefreshLayout mStarredReposSRLayout;
    private RecyclerView mStarredReposRV;

    private RefreshView mRefreshView;

    private ReposRecyclerViewAdapter mAdapter;

    private List<ReposModel> list = new ArrayList<>();

    private String sort = ActivityService.SORT_CREATED;
    private String direction = ActivityService.DIRECTION_DESC;

    @Override
    public void onStop() {
        if (mPresenter != null) {
            mPresenter.stop();
        }
        super.onStop();
    }

    @Override
    protected void initViews(View view) {
        new StarPresenter(getContext(), this);
        mPresenter.start();

        mRefreshView = (RefreshView) view.findViewById(R.id.starred_repos_RefreshView);
        mStarredReposSRLayout = (SwipeRefreshLayout) view.findViewById(R.id.starred_repos_SRLayout);
        mStarredReposRV = (RecyclerView) view.findViewById(R.id.starred_repos_RV);

        setupRecyclerView();
        setupSwipeRefreshLayout();

        mRefreshView.setOnRefreshStateListener(new RefreshView.OnRefreshStateListener() {
            @Override
            public void beforeRefreshing() {
                ReposDao.deleteRepos();
                loadStarredRepositories();
            }

            @Override
            public void onRefreshSuccess() {
                mRefreshView.setVisibility(View.GONE);
                mStarredReposSRLayout.setVisibility(View.VISIBLE);
            }

            @Override
            public void onRefreshFail() {
                mRefreshView.setVisibility(View.VISIBLE);
                mStarredReposSRLayout.setVisibility(View.GONE);
            }
        });
        mRefreshView.startRefresh();
    }

    private void setupSwipeRefreshLayout() {
        mStarredReposSRLayout.measure(View.MEASURED_SIZE_MASK, View.MEASURED_HEIGHT_STATE_SHIFT);
        mStarredReposSRLayout.setColorSchemeResources(R.color.colorAccent);
        mStarredReposSRLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                setSwipeRefreshLayoutRefreshing();
            }
        });
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public void inflateMenu() {
        inflateMenu(R.menu.star_toolbar_menu);
    }

    @Override
    public void createMenu(Menu menu) {
        setOnMenuItemSelectedListener(new OnMenuItemSelectedListener() {
            @Override
            public void onItemSelected(MenuItem item) {
                if (!mRefreshView.isRefreshSuccess())
                    return;
                item.setChecked(true);
                switch (item.getItemId()) {
                    case R.id.star_sort_recently_starred:
                        sort = ActivityService.SORT_CREATED;
                        direction = ActivityService.DIRECTION_DESC;
                        mStarredReposSRLayout.setRefreshing(true);
                        setSwipeRefreshLayoutRefreshing();
                        break;
                    case R.id.star_sort_recently_active:
                        sort = ActivityService.SORT_UPDATED;
                        direction = ActivityService.DIRECTION_DESC;
                        mStarredReposSRLayout.setRefreshing(true);
                        setSwipeRefreshLayoutRefreshing();
                        break;
                }
            }
        });
    }

    private void setSwipeRefreshLayoutRefreshing() {
        ReposDao.deleteRepos();
        mAdapter.setHasLoading(true);
        mAdapter.hideLoadingView();
        StarPresenter presenter = (StarPresenter) mPresenter;
        presenter.setPageId(1);
        loadStarredRepositories();
    }

    private void setupRecyclerView() {
        mAdapter = new ReposRecyclerViewAdapter(getContext());
        mStarredReposRV.setLayoutManager(new LinearLayoutManager(getContext()));
        mStarredReposRV.setAdapter(mAdapter);

        final LinearLayoutManager manager = (LinearLayoutManager) mStarredReposRV.getLayoutManager();
        mStarredReposRV.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int lastItemPosition = manager.findLastCompletelyVisibleItemPosition();
                int firstItemPosition = manager.findFirstCompletelyVisibleItemPosition();
                if (lastItemPosition == mAdapter.getItemCount() - 1
                        && firstItemPosition != mAdapter.getItemCount() - 1
                        && mAdapter.isHasMoreData()) {
                    if (!mStarredReposSRLayout.isRefreshing()) {
                        mAdapter.setHasLoading(true);
                        loadStarredRepositories();
                    }
                }
            }
        });
    }

    private void loadStarredRepositories() {
        mPresenter.getUserStarredRepos();
    }

    @Override
    protected View setContentView(LayoutInflater inflater, @Nullable ViewGroup container) {
        return inflater.inflate(R.layout.fragment_stars, container, false);
    }

    @Override
    public void setPresenter(StarContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void loading(List<RepositoriesBean> starredRepositories) {
        for (RepositoriesBean repo : starredRepositories) {
            ReposDao.insertRepo(repo);
        }
        Log.i(TAG, String.valueOf(starredRepositories.size()));
        if (starredRepositories == null || starredRepositories.size() == 0) {
            mAdapter.setHasLoading(false);
        }
        list.clear();
        list.addAll(ReposDao.queryRepos());
    }

    @Override
    public void loadFail() {
        mStarredReposSRLayout.setRefreshing(false);
        mRefreshView.refreshFail();
    }

    @Override
    public void loadSuccess() {
        if (mStarredReposSRLayout.isRefreshing()) {
            mAdapter.swapAllData(new ArrayList<ReposModel>());
        }
        mAdapter.swapAllData(list);
        mStarredReposSRLayout.setRefreshing(false);
        if (!mRefreshView.isRefreshSuccess()) {
            mRefreshView.refreshSuccess();
        }
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getSort() {
        return sort;
    }

    @Override
    public String getDirection() {
        return direction;
    }
}
