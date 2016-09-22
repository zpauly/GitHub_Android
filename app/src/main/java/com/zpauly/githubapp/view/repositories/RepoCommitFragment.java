package com.zpauly.githubapp.view.repositories;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zpauly.githubapp.R;
import com.zpauly.githubapp.adapter.CommitsRecyclerViewAdapter;
import com.zpauly.githubapp.base.BaseAdapter;
import com.zpauly.githubapp.base.BaseFragment;
import com.zpauly.githubapp.entity.response.repos.SingleCommitBean;
import com.zpauly.githubapp.presenter.repos.RepoCommitContract;
import com.zpauly.githubapp.presenter.repos.RepoCommitPresenter;
import com.zpauly.githubapp.ui.RefreshView;
import com.zpauly.githubapp.view.commit.CommitContentActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zpauly on 16/9/20.
 */

public class RepoCommitFragment extends BaseFragment implements RepoCommitContract.View {
    private final String TAG = getClass().getName();

    private RepoCommitContract.Presenter mPresenter;

    private String repoName;
    private String owner;

    private RefreshView mRefreshView;
    private SwipeRefreshLayout mCommitSRLayout;
    private RecyclerView mCommitRV;

    private CommitsRecyclerViewAdapter mCommitAdapter;

    private List<SingleCommitBean> commitList = new ArrayList<>();

    @Override
    public void onStop() {
        mPresenter.stop();
        super.onStop();
    }

    private void getAttrs() {
        Bundle bundle = getArguments();
        if (bundle != null) {
            repoName = bundle.getString(RepoCommitActivity.REPONAME);
            owner = bundle.getString(RepoCommitActivity.OWNER);
        }
    }

    @Override
    protected void initViews(View view) {
        getAttrs();

        new RepoCommitPresenter(getContext(), this);

        mRefreshView = (RefreshView) view.findViewById(R.id.commit_RefreshView);
        mCommitSRLayout = (SwipeRefreshLayout) view.findViewById(R.id.commit_SRLayout);
        mCommitRV = (RecyclerView) view.findViewById(R.id.commit_RV);

        setupSwipeRefreshLayout();
        setupRecyclerView();

        mRefreshView.setOnRefreshStateListener(new RefreshView.OnRefreshStateListener() {
            @Override
            public void beforeRefreshing() {
                mPresenter.setPageId(1);
                getCommits();
            }

            @Override
            public void onRefreshSuccess() {
                mRefreshView.setVisibility(View.GONE);
                mCommitSRLayout.setVisibility(View.VISIBLE);
            }

            @Override
            public void onRefreshFail() {
                mRefreshView.setVisibility(View.VISIBLE);
                mCommitSRLayout.setVisibility(View.GONE);
            }
        });
        mRefreshView.startRefresh();
    }

    private void getCommits() {
        mPresenter.getCommits();
    }

    private void setupSwipeRefreshLayout() {
        mCommitSRLayout.measure(View.MEASURED_SIZE_MASK, View.MEASURED_HEIGHT_STATE_SHIFT);
        mCommitSRLayout.setColorSchemeResources(R.color.colorAccent);
        mCommitSRLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.setPageId(1);
                getCommits();
            }
        });
    }

    private void setupRecyclerView() {
        mCommitAdapter = new CommitsRecyclerViewAdapter(getContext());
        mCommitRV.setLayoutManager(new LinearLayoutManager(getContext()));
        mCommitRV.setAdapter(mCommitAdapter);

        final LinearLayoutManager manager = (LinearLayoutManager) mCommitRV.getLayoutManager();
        mCommitRV.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int lastItemPosition = manager.findLastCompletelyVisibleItemPosition();
                int firstItemPosition = manager.findFirstCompletelyVisibleItemPosition();
                if (lastItemPosition == mCommitAdapter.getItemCount() - 1
                        && firstItemPosition != mCommitAdapter.getItemCount() - 1
                        && mCommitAdapter.isHasMoreData()) {
                    if (!mCommitSRLayout.isRefreshing()) {
                        mCommitAdapter.setHasLoading(true);
                        getCommits();
                    }
                }
            }
        });
    }

    @Override
    protected View setContentView(LayoutInflater inflater, @Nullable ViewGroup container) {
        return inflater.inflate(R.layout.fragment_repo_commit, container, false);
    }

    @Override
    public void getCommitsSuccess() {
        mCommitAdapter.swapAllData(commitList);
        mCommitAdapter.setOnItemClickedListener(new BaseAdapter.OnItemClickedListener() {
            @Override
            public void onItemClicked(View view, int position) {
                CommitContentActivity.launchActivity(getContext(), owner, repoName,
                        commitList.get(position).getSha());
            }
        });
        mCommitSRLayout.setRefreshing(false);
        if (!mRefreshView.isRefreshSuccess()) {
            mRefreshView.refreshSuccess();
        }
    }

    @Override
    public void getCommitsFail() {
        mCommitSRLayout.setRefreshing(false);
        mRefreshView.refreshFail();
    }

    @Override
    public void gettingCommits(List<SingleCommitBean> singleCommitBeen) {
        if (mCommitSRLayout.isRefreshing()) {
            commitList.clear();
        }
        if (singleCommitBeen == null || singleCommitBeen.size() == 0) {
            mCommitAdapter.setHasLoading(false);
            return;
        }
        commitList.addAll(singleCommitBeen);
    }

    @Override
    public String getOwner() {
        return owner;
    }

    @Override
    public String getRepo() {
        return repoName;
    }

    @Override
    public void setPresenter(RepoCommitContract.Presenter presenter) {
        mPresenter = presenter;
    }
}
