package com.zpauly.githubapp.view.issues;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zpauly.githubapp.R;
import com.zpauly.githubapp.adapter.PatchRecyclerViewAdapter;
import com.zpauly.githubapp.base.BaseFragment;
import com.zpauly.githubapp.entity.response.CommentBean;
import com.zpauly.githubapp.entity.response.PullRequestBean;
import com.zpauly.githubapp.entity.response.repos.FileBean;
import com.zpauly.githubapp.entity.response.repos.SingleCommitBean;
import com.zpauly.githubapp.presenter.issues.PullRequestContentContract;
import com.zpauly.githubapp.presenter.issues.PullRequestContentPresenter;
import com.zpauly.githubapp.ui.RefreshView;
import com.zpauly.githubapp.utils.viewmanager.LoadMoreInSwipeRefreshLayoutMoreManager;
import com.zpauly.githubapp.utils.viewmanager.RefreshViewManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by zpauly on 2016/10/23.
 */

public class PullRequestsFilesFragment extends BaseFragment implements PullRequestContentContract.View {
    private final String TAG = getClass().getName();

    private PullRequestContentContract.Presenter mPresenter;

    @BindView(R.id.RefreshView) public RefreshView mRefreshView;
    @BindView(R.id.SRLayout) SwipeRefreshLayout mSRLayout;
    @BindView(R.id.pull_request_files_RV) public RecyclerView mFilesRV;

    private PatchRecyclerViewAdapter mPatchAdapter;

    private PullRequestBean pullRequestBean;

    private List<FileBean> fileList = new ArrayList<>();

    private LoadMoreInSwipeRefreshLayoutMoreManager loadMoreInSwipeRefreshLayoutMoreManager;
    private RefreshViewManager refreshViewManager;

    @Override
    public void onStop() {
        mPresenter.stop();
        super.onStop();
    }

    @Override
    protected void initViews(View view) {
        new PullRequestContentPresenter(getContext(), this);

        setupRecyclerView();
        setupSwipeRefreshLayout();

        loadMoreInSwipeRefreshLayoutMoreManager = new LoadMoreInSwipeRefreshLayoutMoreManager(mFilesRV, mSRLayout) {
            @Override
            public void load() {
                getFiles();
            }
        };
        refreshViewManager = new RefreshViewManager(mRefreshView, mSRLayout) {
            @Override
            public void load() {
                getFiles();
            }
        };
        setViewManager(loadMoreInSwipeRefreshLayoutMoreManager, refreshViewManager);
    }

    @Override
    protected void getParams() {
        Bundle bundle = getArguments();
        if (bundle != null) {
            pullRequestBean = bundle.getParcelable(PullRequestContentActivity.PULL_REQUEST);
        }
    }

    private void setupRecyclerView() {
        mPatchAdapter = new PatchRecyclerViewAdapter(getContext());
        mFilesRV.setLayoutManager(new LinearLayoutManager(getContext()));
        mFilesRV.setAdapter(mPatchAdapter);
    }

    private void setupSwipeRefreshLayout() {
        mSRLayout.measure(View.MEASURED_SIZE_MASK, View.MEASURED_HEIGHT_STATE_SHIFT);
        mSRLayout.setColorSchemeResources(R.color.colorAccent);
        mSRLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.setPageId(1);
                loadMoreInSwipeRefreshLayoutMoreManager.setSwipeRefreshLayoutRefreshing(mPatchAdapter);
            }
        });
    }

    private void getFiles() {
        mPresenter.getFiles();
    }

    @Override
    protected View setContentView(LayoutInflater inflater, @Nullable ViewGroup container) {
        return inflater.inflate(R.layout.fragment_pull_request_files, container, false);
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

    //does't need
    @Override
    public void getCommitsSuccess() {

    }

    //does't need
    @Override
    public void getCommitsFail() {

    }

    //does't need
    @Override
    public void gettingCommits(List<SingleCommitBean> commitBeen) {

    }

    @Override
    public void getFilesSuccess() {
        mPatchAdapter.swapAllData(fileList);
        mSRLayout.setRefreshing(false);
        if (!mRefreshView.isRefreshSuccess()) {
            mRefreshView.refreshSuccess();
        }
    }

    @Override
    public void getFilsFail() {
        mSRLayout.setRefreshing(false);
        if (!mRefreshView.isRefreshSuccess()) {
            mRefreshView.refreshFail();
        } else {
            Snackbar.make(mRefreshView, R.string.error_occurred, Snackbar.LENGTH_SHORT);
        }
    }

    @Override
    public void gettingFiles(List<FileBean> fileBeen) {
        loadMoreInSwipeRefreshLayoutMoreManager.hasNoMoreData(fileBeen, mPatchAdapter);
        if (mSRLayout.isRefreshing()) {
            fileList.clear();
        }
        fileList.addAll(fileBeen);
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
