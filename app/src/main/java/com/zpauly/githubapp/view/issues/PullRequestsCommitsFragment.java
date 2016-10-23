package com.zpauly.githubapp.view.issues;

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
import com.zpauly.githubapp.base.BaseFragment;
import com.zpauly.githubapp.entity.response.CommentBean;
import com.zpauly.githubapp.entity.response.PullRequestBean;
import com.zpauly.githubapp.entity.response.repos.FileBean;
import com.zpauly.githubapp.entity.response.repos.SingleCommitBean;
import com.zpauly.githubapp.presenter.issues.PullRequestContentContract;
import com.zpauly.githubapp.presenter.issues.PullRequestContentPresenter;
import com.zpauly.githubapp.ui.RefreshView;
import com.zpauly.githubapp.utils.TextUtil;
import com.zpauly.githubapp.utils.viewmanager.LoadMoreInSwipeRefreshLayoutMoreManager;
import com.zpauly.githubapp.utils.viewmanager.RefreshViewManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zpauly on 2016/10/23.
 */

public class PullRequestsCommitsFragment extends BaseFragment implements PullRequestContentContract.View {
    private final String TAG = getClass().getName();

    private PullRequestContentContract.Presenter mPresenter;

    private RefreshView mRefreshView;
    private SwipeRefreshLayout mSRLayout;
    private RecyclerView mCommitRV;

    private CommitsRecyclerViewAdapter mCommitAdapter;

    private PullRequestBean pullRequestBean;

    private List<SingleCommitBean> commitList = new ArrayList<>();

    private LoadMoreInSwipeRefreshLayoutMoreManager loadMoreInSwipeRefreshLayoutMoreManager;
    private RefreshViewManager refreshViewManager;

    @Override
    public void onStop() {
        mPresenter.stop();
        super.onStop();
    }

    @Override
    protected void initViews(View view) {
        getParams();

        new PullRequestContentPresenter(getContext(), this);

        mRefreshView = (RefreshView) view.findViewById(R.id.RefreshView);
        mSRLayout = (SwipeRefreshLayout) view.findViewById(R.id.SRLayout);
        mCommitRV = (RecyclerView) view.findViewById(R.id.pull_request_commits_RV);

        setupRecyclerView();
        setupSwipeRefreshLayout();

        loadMoreInSwipeRefreshLayoutMoreManager = new LoadMoreInSwipeRefreshLayoutMoreManager(mCommitRV, mSRLayout) {
            @Override
            public void load() {
                getCommits();
            }
        };
        refreshViewManager = new RefreshViewManager(mRefreshView, mSRLayout) {
            @Override
            public void load() {
                getCommits();
            }
        };
        setViewManager(loadMoreInSwipeRefreshLayoutMoreManager, refreshViewManager);
    }

    private void getParams() {
        Bundle bundle = getArguments();
        if (bundle != null) {
            pullRequestBean = bundle.getParcelable(PullRequestContentActivity.PULL_REQUEST);
        }
    }

    private void setupRecyclerView() {
        mCommitAdapter = new CommitsRecyclerViewAdapter(getContext());
        mCommitRV.setLayoutManager(new LinearLayoutManager(getContext()));
        mCommitRV.setAdapter(mCommitAdapter);
    }

    private void setupSwipeRefreshLayout() {
        mSRLayout.measure(View.MEASURED_SIZE_MASK, View.MEASURED_HEIGHT_STATE_SHIFT);
        mSRLayout.setColorSchemeResources(R.color.colorAccent);
        mSRLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.setPageId(1);
                loadMoreInSwipeRefreshLayoutMoreManager.setSwipeRefreshLayoutRefreshing(mCommitAdapter);
            }
        });
    }

    private void getCommits() {
        mPresenter.getCommits();
    }

    @Override
    protected View setContentView(LayoutInflater inflater, @Nullable ViewGroup container) {
        return inflater.inflate(R.layout.fragment_pull_request_commits, container, false);
    }

    //does't need
    @Override
    public void getCommentsSuccess() {

    }

    //does't need
    @Override
    public void getCommentsFail() {

    }

    //does't need
    @Override
    public void gettingComments(List<CommentBean> commentBeen) {

    }

    @Override
    public void getCommitsSuccess() {
        mCommitAdapter.swapAllData(commitList);
        mSRLayout.setRefreshing(false);
        if (!mRefreshView.isRefreshSuccess()) {
            mRefreshView.refreshSuccess();
        }
    }

    @Override
    public void getCommitsFail() {
        mSRLayout.setRefreshing(false);
        if (!mRefreshView.isRefreshSuccess()) {
            mRefreshView.refreshFail();
        }
    }

    @Override
    public void gettingCommits(List<SingleCommitBean> commitBeen) {
        loadMoreInSwipeRefreshLayoutMoreManager.hasNoMoreData(commitBeen, mCommitAdapter);
        if (mSRLayout.isRefreshing()) {
            commitList.clear();
        }
        commitList.addAll(commitBeen);
    }

    //does't need
    @Override
    public void getFilesSuccess() {

    }

    //does't need
    @Override
    public void getFilsFail() {

    }

    //does't need
    @Override
    public void gettingFiles(List<FileBean> fileBeen) {

    }

    @Override
    public String getOwner() {
        return pullRequestBean.getBase().getRepo().getOwner().getLogin();
    }

    @Override
    public String getRepo() {
        return pullRequestBean.getBase().getRepo().getName();
    }

    @Override
    public int getNumber() {
        return pullRequestBean.getNumber();
    }

    @Override
    public void setPresenter(PullRequestContentContract.Presenter presenter) {
        this.mPresenter = presenter;
    }
}
