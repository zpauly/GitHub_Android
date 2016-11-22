package com.zpauly.githubapp.view.users;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.zpauly.githubapp.R;
import com.zpauly.githubapp.adapter.LoadMoreRecyclerViewAdapter;
import com.zpauly.githubapp.adapter.OrgsRecyclerViewAdapter;
import com.zpauly.githubapp.adapter.UsersRecyclerViewAdapter;
import com.zpauly.githubapp.entity.response.OrganizationBean;
import com.zpauly.githubapp.entity.response.UserBean;
import com.zpauly.githubapp.presenter.follow.FollowContract;
import com.zpauly.githubapp.presenter.follow.FollowPresenter;
import com.zpauly.githubapp.ui.RefreshView;
import com.zpauly.githubapp.utils.viewmanager.LoadMoreInSwipeRefreshLayoutMoreManager;
import com.zpauly.githubapp.utils.viewmanager.RefreshViewManager;
import com.zpauly.githubapp.view.ToolbarActivity;
import com.zpauly.githubapp.view.profile.OthersActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zpauly on 16-6-15.
 */
public class UsersActivity extends ToolbarActivity implements FollowContract.View {
    private final String TAG = getClass().getName();

    public static final String USERS_ID = "USERS_ID";
    public static final String OWNER = "OWNER";
    public static final String REPO = "REPO";
    public static final int FOLLOWERS = 0;
    public static final int FOLLOWING = 1;
    public static final int ORGS = 2;
    public static final int WATCHERS = 3;
    public static final int STARGAZERS = 4;

    private int userId;
    private String owner;
    private String repo;

    private FollowContract.Presenter mPresenter;
    private List<UserBean> list = new ArrayList<>();
    private List<OrganizationBean> orgsList = new ArrayList<>();
    private LoadMoreRecyclerViewAdapter mRVAdapter;

    private SwipeRefreshLayout mSRLayout;
    private RecyclerView mContentRV;

    private RefreshView mRefreshView;

    private LoadMoreInSwipeRefreshLayoutMoreManager loadMoreInSwipeRefreshLayoutMoreManager;
    private RefreshViewManager refreshViewManager;

    @Override
    protected void onStop() {
        if (mPresenter != null) {
            mPresenter.stop();
        }
        super.onStop();
    }

    @Override
    public void initViews() {
        userId = getIntent().getIntExtra(USERS_ID, -1);
        owner = getIntent().getStringExtra(OWNER);
        repo = getIntent().getStringExtra(REPO);

        setContent(R.layout.content_followers);

        new FollowPresenter(this, this);

        mRefreshView = (RefreshView) findViewById(R.id.followers_RefreshView);

        mSRLayout = (SwipeRefreshLayout) findViewById(R.id.followers_SWLayout);
        mContentRV = (RecyclerView) findViewById(R.id.followers_content_RV);

        setupSwipeRefreshLayout();
        setupRecyclerView();

        setViewManager(new LoadMoreInSwipeRefreshLayoutMoreManager(mContentRV, mSRLayout) {
            @Override
            public void load() {
                loadFollow();
            }
        }, new RefreshViewManager(mRefreshView, mSRLayout) {
            @Override
            public void load() {
                loadFollow();
            }
        });

        loadMoreInSwipeRefreshLayoutMoreManager = getViewManager(LoadMoreInSwipeRefreshLayoutMoreManager.class);
        refreshViewManager = getViewManager(RefreshViewManager.class);
    }

    @Override
    protected void setToolbar() {
        super.setToolbar();
        if (userId == FOLLOWERS) {
            setToolbarTitle(R.string.followers);
        } else if (userId == FOLLOWING) {
            setToolbarTitle(R.string.following);
        } else if (userId == ORGS) {
            setToolbarTitle(R.string.orgs);
        } else if (userId == WATCHERS) {
            setToolbarTitle(R.string.watchers);
        } else if (userId == STARGAZERS) {
            setToolbarTitle(R.string.stargazers);
        }
        setOnToolbarNavClickedListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    public static void launchActivity(Context context, String username, int userId) {
        Intent intent = new Intent();
        intent.putExtra(OthersActivity.USERNAME, username);
        intent.putExtra(USERS_ID, userId);
        intent.setClass(context, UsersActivity.class);
        context.startActivity(intent);
//        ((Activity) context).finish();
    }

    public static void launchWatchersActivity(Context context, String owner, String repo) {
        Intent intent = new Intent();
        intent.putExtra(USERS_ID, WATCHERS);
        intent.putExtra(OWNER, owner);
        intent.putExtra(REPO, repo);
        intent.setClass(context, UsersActivity.class);
        context.startActivity(intent);
    }

    public static void launchStargazersActivity(Context context, String owner, String repo) {
        Intent intent = new Intent();
        intent.putExtra(USERS_ID, STARGAZERS);
        intent.putExtra(OWNER, owner);
        intent.putExtra(REPO, repo);
        intent.setClass(context, UsersActivity.class);
        context.startActivity(intent);
    }

    private void setupSwipeRefreshLayout() {
        mSRLayout.measure(View.MEASURED_SIZE_MASK, View.MEASURED_HEIGHT_STATE_SHIFT);
        mSRLayout.setColorSchemeResources(R.color.colorAccent);
        mSRLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.setPageId(1);
                loadMoreInSwipeRefreshLayoutMoreManager.setSwipeRefreshLayoutRefreshing(mRVAdapter);
            }
        });
    }

    private void setupRecyclerView() {
        if (userId == ORGS) {
            mRVAdapter = new OrgsRecyclerViewAdapter(this);
        } else {
            mRVAdapter = new UsersRecyclerViewAdapter(this);
        }

        mContentRV.setLayoutManager(new LinearLayoutManager(this));
        mContentRV.setAdapter(mRVAdapter);
    }

    private void loadFollow() {
        switch (userId) {
            case FOLLOWERS:
                mPresenter.getFollowers();
                break;
            case FOLLOWING:
                mPresenter.getFollowing();
                break;
            case ORGS:
                mPresenter.getOrgs();
                break;
            case WATCHERS:
                mPresenter.getWatchers();
                break;
            case STARGAZERS:
                mPresenter.getStargazers();
                break;
            default:
                break;
        }
    }

    @Override
    public void loading(List<UserBean> list) {
        if (mSRLayout.isRefreshing()) {
            this.list.clear();
        }
        loadMoreInSwipeRefreshLayoutMoreManager.hasNoMoreData(list, mRVAdapter);
        this.list.addAll(list);
    }

    @Override
    public void loadingOrgs(List<OrganizationBean> list) {
        if (mSRLayout.isRefreshing()) {
            this.orgsList.clear();
        }
        loadMoreInSwipeRefreshLayoutMoreManager.hasNoMoreData(list, mRVAdapter);
        this.orgsList.addAll(list);
    }

    @Override
    public void loadOrgsSuccess() {
        mRVAdapter.swapAllData(orgsList);
        mSRLayout.setRefreshing(false);
        if (!mRefreshView.isRefreshSuccess()) {
            mRefreshView.refreshSuccess();
        }
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
        mRVAdapter.swapAllData(list);
        mSRLayout.setRefreshing(false);
        if (!mRefreshView.isRefreshSuccess()) {
            mRefreshView.refreshSuccess();
        }
    }

    @Override
    public String getUsername() {
        return username;
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
    public void setPresenter(FollowContract.Presenter presenter) {
        this.mPresenter = presenter;
    }
}
