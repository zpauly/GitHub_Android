package com.zpauly.githubapp.view.users;

import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zpauly.githubapp.R;
import com.zpauly.githubapp.adapter.LoadMoreRecyclerViewAdapter;
import com.zpauly.githubapp.adapter.OrgsRecyclerViewAdapter;
import com.zpauly.githubapp.adapter.UsersRecyclerViewAdapter;
import com.zpauly.githubapp.base.BaseFragment;
import com.zpauly.githubapp.entity.response.OrganizationBean;
import com.zpauly.githubapp.entity.response.UserBean;
import com.zpauly.githubapp.presenter.follow.FollowContract;
import com.zpauly.githubapp.presenter.follow.FollowPresenter;
import com.zpauly.githubapp.ui.RefreshView;
import com.zpauly.githubapp.utils.viewmanager.LoadMoreInSwipeRefreshLayoutMoreManager;
import com.zpauly.githubapp.utils.viewmanager.RefreshViewManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zpauly on 16-7-25.
 */

public class UsersFragment extends BaseFragment implements FollowContract.View {
    private final String TAG = getClass().getName();

    private FollowContract.Presenter mPresenter;
    private List<UserBean> list = new ArrayList<>();
    private List<OrganizationBean> orgsList = new ArrayList<>();
    private LoadMoreRecyclerViewAdapter mRVAdapter;

    private SwipeRefreshLayout mSWLayout;
    private RecyclerView mContentRV;

    private RefreshView mRefreshView;

    private int userId = 0;

    private LoadMoreInSwipeRefreshLayoutMoreManager loadMoreInSwipeRefreshLayoutMoreManager;
    private RefreshViewManager refreshViewManager;

    @Override
    public void onStop() {
        if (mPresenter != null) {
            mPresenter.stop();
        }
        super.onStop();
    }

    @Override
    protected void initViews(View view) {
        new FollowPresenter(getContext(), this);

        userId = getArguments().getInt(UsersActivity.USERS_ID);

        mRefreshView = (RefreshView) view.findViewById(R.id.followers_RefreshView);

        mSWLayout = (SwipeRefreshLayout) view.findViewById(R.id.followers_SWLayout);
        mContentRV = (RecyclerView) view.findViewById(R.id.followers_content_RV);

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

//        mRefreshView.setOnRefreshStateListener(new RefreshView.OnRefreshStateListener() {
//            @Override
//            public void beforeRefreshing() {
//                loadFollow();
//            }
//
//            @Override
//            public void onRefreshSuccess() {
//                mRefreshView.setVisibility(View.GONE);
//                mSWLayout.setVisibility(View.VISIBLE);
//            }
//
//            @Override
//            public void onRefreshFail() {
//                mRefreshView.setVisibility(View.VISIBLE);
//                mSWLayout.setVisibility(View.GONE);
//            }
//        });
//        mRefreshView.startRefresh();
    }

    @Override
    protected View setContentView(LayoutInflater inflater, @Nullable ViewGroup container) {
        return inflater.inflate(R.layout.fragment_followers, container, false);
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
            mRVAdapter = new OrgsRecyclerViewAdapter(getContext());
        }
        mRVAdapter = new UsersRecyclerViewAdapter(getContext());

        mContentRV.setLayoutManager(new LinearLayoutManager(getContext()));
        mContentRV.setAdapter(mRVAdapter);

//        final LinearLayoutManager manager = (LinearLayoutManager) mContentRV.getLayoutManager();
//        mContentRV.addOnScrollListener(new RecyclerView.OnScrollListener() {
//            @Override
//            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
//                super.onScrollStateChanged(recyclerView, newState);
//            }
//
//            @Override
//            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
//                super.onScrolled(recyclerView, dx, dy);
//                int lastVisiableItemPosition = manager.findLastCompletelyVisibleItemPosition();
//                if (lastVisiableItemPosition == mRVAdapter.getItemCount() - 1
//                        && manager.findFirstCompletelyVisibleItemPosition() != mRVAdapter.getItemCount() - 1
//                        && mRVAdapter.isHasMoreData()) {
//                    mRVAdapter.setHasLoading(true);
//                    if (!mSWLayout.isRefreshing()) {
//                        loadFollow();
//                    }
//                }
//            }
//        });
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
