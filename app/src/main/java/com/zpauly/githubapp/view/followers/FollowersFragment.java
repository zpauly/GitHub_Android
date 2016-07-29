package com.zpauly.githubapp.view.followers;

import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zpauly.githubapp.R;
import com.zpauly.githubapp.adapter.FollowersRecyclerViewAdapter;
import com.zpauly.githubapp.base.BaseFragment;
import com.zpauly.githubapp.entity.response.FollowersBean;
import com.zpauly.githubapp.presenter.follow.FollowContract;
import com.zpauly.githubapp.presenter.follow.FollowPresenter;
import com.zpauly.githubapp.ui.DividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zpauly on 16-7-25.
 */

public class FollowersFragment extends BaseFragment implements FollowContract.View {
    private final String TAG = getClass().getName();

    private FollowContract.Presenter mPresenter;
    private List<FollowersBean> list = new ArrayList<>();
    private FollowersRecyclerViewAdapter mRVAdapter;

    private SwipeRefreshLayout mSWLayout;
    private RecyclerView mContentRV;

    private int followId = 0;

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

        followId = getArguments().getInt(FollowersActivity.FOLLOW_ID);

        mSWLayout = (SwipeRefreshLayout) view.findViewById(R.id.followers_SWLayout);
        mContentRV = (RecyclerView) view.findViewById(R.id.followers_content_RV);

        setupSwipeRefreshLayout();
        setupRecyclerView();

        mSWLayout.setRefreshing(true);
        loadFollow();
    }

    @Override
    protected View setContentView(LayoutInflater inflater, @Nullable ViewGroup container) {
        return LayoutInflater.from(getContext()).inflate(R.layout.fragment_followers, container, false);
    }

    private void setupSwipeRefreshLayout() {
        mSWLayout.measure(View.MEASURED_SIZE_MASK, View.MEASURED_HEIGHT_STATE_SHIFT);
        mSWLayout.setColorSchemeResources(R.color.colorAccent);
        mSWLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadFollow();
            }
        });
    }

    private void setupRecyclerView() {
        mRVAdapter = new FollowersRecyclerViewAdapter(getContext());

        mContentRV.setLayoutManager(new LinearLayoutManager(getContext()));
        mContentRV.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL_LIST));
        mContentRV.setAdapter(mRVAdapter);

        final LinearLayoutManager manager = (LinearLayoutManager) mContentRV.getLayoutManager();
        mContentRV.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int lastVisiableItemPosition = manager.findLastCompletelyVisibleItemPosition();
                if (lastVisiableItemPosition == mRVAdapter.getItemCount() - 1
                        && manager.findFirstCompletelyVisibleItemPosition() != mRVAdapter.getItemCount() - 1
                        && mRVAdapter.isHasMoreData()) {
                    mRVAdapter.setHasLoading(true);
                    if (!mSWLayout.isRefreshing()) {
                        loadFollow();
                    }
                }
            }
        });
    }

    private void loadFollow() {
        switch (followId) {
            case FollowersActivity.FOLLOWERS:
                mPresenter.getFollowers();
                break;
            case FollowersActivity.FOLLOWING:
                mPresenter.getFollowing();
                break;
            default:
                break;
        }
    }

    @Override
    public void loading(List<FollowersBean> list) {
        this.list.clear();
        this.list.addAll(list);
    }

    @Override
    public void loadFail() {
        mSWLayout.setRefreshing(false);
    }

    @Override
    public void loadSuccess() {
        mRVAdapter.addData(list);
        mSWLayout.setRefreshing(false);
        if (list == null || list.size() == 0) {
            mRVAdapter.setHasLoading(false);
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
