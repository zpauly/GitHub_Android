package com.zpauly.githubapp.view.users;

import android.content.Context;
import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
    public static final int FOLLOWERS = 0;
    public static final int FOLLOWING = 1;
    public static final int ORGS = 2;

    private int userId;

    private FollowContract.Presenter mPresenter;
    private List<UserBean> list = new ArrayList<>();
    private List<OrganizationBean> orgsList = new ArrayList<>();
    private LoadMoreRecyclerViewAdapter mRVAdapter;

    private SwipeRefreshLayout mSWLayout;
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

        setContent(R.layout.content_followers);

        new FollowPresenter(this, this);

        mRefreshView = (RefreshView) findViewById(R.id.followers_RefreshView);

        mSWLayout = (SwipeRefreshLayout) findViewById(R.id.followers_SWLayout);
        mContentRV = (RecyclerView) findViewById(R.id.followers_content_RV);

        setupSwipeRefreshLayout();
        setupRecyclerView();

        setViewManager(new LoadMoreInSwipeRefreshLayoutMoreManager(mContentRV, mSWLayout) {
            @Override
            public void load() {
                loadFollow();
            }
        }, new RefreshViewManager(mRefreshView, mSWLayout) {
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

    private void setupSwipeRefreshLayout() {
        mSWLayout.measure(View.MEASURED_SIZE_MASK, View.MEASURED_HEIGHT_STATE_SHIFT);
        mSWLayout.setColorSchemeResources(R.color.colorAccent);
        mSWLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mRVAdapter.addAllData(new ArrayList<UserBean>());
                loadMoreInSwipeRefreshLayoutMoreManager.setSwipeRefreshLayoutRefreshing(mRVAdapter);
            }
        });
    }

    private void setupRecyclerView() {
        if (userId == UsersActivity.ORGS) {
            mRVAdapter = new OrgsRecyclerViewAdapter(this);
        }
        mRVAdapter = new UsersRecyclerViewAdapter(this);

        mContentRV.setLayoutManager(new LinearLayoutManager(this));
        mContentRV.setAdapter(mRVAdapter);
    }

    private void loadFollow() {
        switch (userId) {
            case UsersActivity.FOLLOWERS:
                mPresenter.getFollowers();
                break;
            case UsersActivity.FOLLOWING:
                mPresenter.getFollowing();
                break;
            case UsersActivity.ORGS:
                mPresenter.getOrgs();
            default:
                break;
        }
    }

    @Override
    public void loading(List<UserBean> list) {
        this.list.clear();
        this.list.addAll(list);
    }

    @Override
    public void loadingOrgs(List<OrganizationBean> list) {
        this.orgsList.clear();
        this.orgsList.addAll(list);
    }

    @Override
    public void loadOrgsSuccess() {
        mSWLayout.setRefreshing(false);
        mRVAdapter.addAllData(orgsList);
        mRVAdapter.setHasLoading(false);
    }

    @Override
    public void loadFail() {
        mSWLayout.setRefreshing(false);
        mRefreshView.refreshFail();
    }

    @Override
    public void loadSuccess() {
        mRVAdapter.addAllData(list);
        mSWLayout.setRefreshing(false);
        loadMoreInSwipeRefreshLayoutMoreManager.hasNoMoreData(list, mRVAdapter);
        if (!mRefreshView.isRefreshSuccess()) {
            mRefreshView.refreshSuccess();
        }
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public void setPresenter(FollowContract.Presenter presenter) {
        this.mPresenter = presenter;
    }
}
