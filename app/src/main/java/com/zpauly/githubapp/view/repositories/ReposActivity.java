package com.zpauly.githubapp.view.repositories;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.zpauly.githubapp.R;
import com.zpauly.githubapp.adapter.ReposRecyclerViewAdapter;
import com.zpauly.githubapp.entity.response.repos.RepositoriesBean;
import com.zpauly.githubapp.listener.OnMenuItemSelectedListener;
import com.zpauly.githubapp.listener.OnNavItemClickListener;
import com.zpauly.githubapp.network.repositories.RepositoriesService;
import com.zpauly.githubapp.presenter.repos.ReposContract;
import com.zpauly.githubapp.presenter.repos.ReposPresenter;
import com.zpauly.githubapp.ui.RefreshView;
import com.zpauly.githubapp.utils.viewmanager.LoadMoreInSwipeRefreshLayoutMoreManager;
import com.zpauly.githubapp.utils.viewmanager.RefreshViewManager;
import com.zpauly.githubapp.view.RightDrawerActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zpauly on 16-7-18.
 */

public class ReposActivity extends RightDrawerActivity implements ReposContract.View {
    private final String TAG = getClass().getName();

    public static final String USERNAME = "username";
    private static final int FILTER_ALL = 0;
    private static final int FILTER_SOURCES = 1;
    private static final int FILTER_FORKS = 2;

    private ReposContract.Presenter mPresenter;

    private String username;
    private String sort;
    private String direction;
    private String type;
    private int filter = 0;

    private RefreshView mRefreshView;
    private SwipeRefreshLayout mSRLayout;
    private RecyclerView mReposRV;

    private ReposRecyclerViewAdapter mReposAdapter;
    private List<RepositoriesBean> list = new ArrayList<>();

    private LoadMoreInSwipeRefreshLayoutMoreManager loadMoreInSwipeRefreshLayoutMoreManager;
    private RefreshViewManager refreshViewManager;

    @Override
    protected void onStop() {
        mPresenter.stop();
        super.onStop();
    }

    @Override
    public void initViews() {
        getParams();

        new ReposPresenter(this, this);

        setContent(R.layout.content_repos);

        mRefreshView = (RefreshView) findViewById(R.id.RefreshView);
        mSRLayout = (SwipeRefreshLayout) findViewById(R.id.SRLayout);
        mReposRV = (RecyclerView) findViewById(R.id.repos_RV);

        setupRecyclerView();
        setupSwipeRefeshLayout();

        loadMoreInSwipeRefreshLayoutMoreManager = new LoadMoreInSwipeRefreshLayoutMoreManager(mReposRV, mSRLayout) {
            @Override
            public void load() {
                getRepos();
            }
        };

        refreshViewManager = new RefreshViewManager(mRefreshView, mSRLayout) {
            @Override
            public void load() {
                getRepos();
            }
        };

        setViewManager(loadMoreInSwipeRefreshLayoutMoreManager, refreshViewManager);
    }

    private void getParams() {
        username = getIntent().getStringExtra(USERNAME);
    }

    private void setupRecyclerView() {
        mReposAdapter = new ReposRecyclerViewAdapter(this);
        mReposRV.setLayoutManager(new LinearLayoutManager(this));
        mReposRV.setAdapter(mReposAdapter);
    }

    private void setupSwipeRefeshLayout() {
        mSRLayout.setColorSchemeResources(R.color.colorAccent);
        mSRLayout.measure(View.MEASURED_SIZE_MASK, View.MEASURED_HEIGHT_STATE_SHIFT);
        mSRLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                setSwipeRefreshing();
            }
        });
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

    private void setSwipeRefreshing() {
        mPresenter.setPageId(1);
        loadMoreInSwipeRefreshLayoutMoreManager.setSwipeRefreshLayoutRefreshing(mReposAdapter);
    }

    private void getRepos() {
        mPresenter.loadUserRepositories();
    }

    private List<RepositoriesBean> getReposByFilter(List<RepositoriesBean> preList) {
        if (filter == FILTER_ALL) {
            return preList;
        }
        List<RepositoriesBean> newList = new ArrayList<>();
        if (filter == FILTER_SOURCES) {
            for (RepositoriesBean bean : preList) {
                if (!bean.isFork()) {
                    newList.add(bean);
                }
            }
            return newList;
        }
        if (filter == FILTER_FORKS) {
            for (RepositoriesBean bean : preList) {
                if (bean.isFork()) {
                    newList.add(bean);
                }
            }
            return newList;
        }
        return newList;
    }

    @Override
    public void loadingRepos(List<RepositoriesBean> list) {
        if (mSRLayout.isRefreshing()) {
            this.list.clear();
        }
        loadMoreInSwipeRefreshLayoutMoreManager.hasNoMoreData(list, mReposAdapter);
        this.list.addAll(getReposByFilter(list));
    }

    @Override
    public void loadFail() {
        mSRLayout.setRefreshing(false);
        if (!mRefreshView.isRefreshSuccess()) {
            mRefreshView.refreshFail();
        } else {
            Snackbar.make(mRefreshView, R.string.error_occurred, Snackbar.LENGTH_SHORT);
        }
    }

    @Override
    public void loadSuccess() {
        mSRLayout.setRefreshing(false);
        if (!mRefreshView.isRefreshSuccess()) {
            mRefreshView.refreshSuccess();
        }
        mReposAdapter.swapAllData(list);
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public String getSort() {
        return sort;
    }

    @Override
    public String getDirection() {
        return direction;
    }

    @Override
    public void setPresenter(ReposContract.Presenter presenter) {
        this.mPresenter = presenter;
    }

    @Override
    public void inflateMenu(MenuInflater inflater, Menu menu) {
        inflater.inflate(R.menu.repos_toolbar_menu, menu);
    }

    @Override
    public void createMenu(Menu menu) {
        setOnMenuItemSelectedListener(new OnMenuItemSelectedListener() {
            @Override
            public void onItemSelected(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.repos_toolbar_right_filter:
                        openDrawer();
                        break;
                    default:
                        break;
                }
            }
        });
    }

    @Override
    public void initNavMenu() {
        inflaterNavMenu(R.menu.repos_right_nav_menu);
        Menu menu = getRightNav().getMenu();
        MenuItem mMenuItemPrivate = menu.findItem(R.id.repos_right_nav_type_private);
        if (username != null) {
            mMenuItemPrivate.setVisible(false);
        }
        setOnNavItemClickListener(new OnNavItemClickListener() {
            @Override
            public void onItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.repos_right_nav_type_all:
                        type = RepositoriesService.TYPE_ALL;
                        filter = FILTER_ALL;
                        break;
                    case R.id.repos_right_nav_type_owner:
                        type = RepositoriesService.TYPE_OWNER;
                        filter = FILTER_ALL;
                        break;
                    case R.id.repos_right_nav_type_public:
                        type = RepositoriesService.TYPE_PUBLIC;
                        filter = FILTER_ALL;
                        break;
                    case R.id.repos_right_nav_type_private:
                        type = RepositoriesService.TYPE_PRIVATE;
                        filter = FILTER_ALL;
                        break;
                    case R.id.repos_right_nav_type_sources:
                        type = RepositoriesService.TYPE_OWNER;
                        filter = FILTER_SOURCES;
                        break;
                    case R.id.repos_right_nav_type_forks:
                        type = RepositoriesService.TYPE_OWNER;
                        filter = FILTER_FORKS;
                        break;
                    case R.id.repos_right_nav_type_member:
                        type = RepositoriesService.TYPE_MEMBER;
                        filter = FILTER_ALL;
                        break;
                    case R.id.repos_right_nav_sort_nameasc:
                        sort = RepositoriesService.SORT_FULL_NAME;
                        direction = RepositoriesService.DIRECTION_ASC;
                        break;
                    case R.id.repos_right_nav_sort_namedesc:
                        sort = RepositoriesService.SORT_FULL_NAME;
                        direction = RepositoriesService.DIRECTION_DESC;
                        break;
                    case R.id.repos_right_nav_sort_newest:
                        sort = RepositoriesService.SORT_CREATED;
                        direction = RepositoriesService.DIRECTION_DESC;
                        break;
                    case R.id.repos_right_nav_sort_oldest:
                        sort = RepositoriesService.SORT_CREATED;
                        direction = RepositoriesService.DIRECTION_ASC;
                        break;
                    case R.id.repos_right_nav_sort_recently_updated:
                        sort = RepositoriesService.SORT_UPDATED;
                        direction = RepositoriesService.DIRECTION_DESC;
                        break;
                    case R.id.repos_right_nav_sort_least_recently_updated:
                        sort = RepositoriesService.SORT_UPDATED;
                        direction = RepositoriesService.DIRECTION_ASC;
                        break;
                    default:
                        break;
                }
                mSRLayout.setRefreshing(true);
                setSwipeRefreshing();
            }
        });
    }

    public static void launchActivity(Context context, @Nullable String username) {
        Intent intent = new Intent();
        if (username != null) {
            intent.putExtra(USERNAME, username);
        }
        intent.setClass(context, ReposActivity.class);
        context.startActivity(intent);
//        ((Activity) context).finish();
    }
}
