package com.zpauly.githubapp.view.issues;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ms.square.android.expandabletextview.ExpandableTextView;
import com.zpauly.githubapp.R;
import com.zpauly.githubapp.adapter.CommentsRecyclerViewAdapter;
import com.zpauly.githubapp.base.BaseFragment;
import com.zpauly.githubapp.entity.response.CommentBean;
import com.zpauly.githubapp.entity.response.PullRequestBean;
import com.zpauly.githubapp.entity.response.repos.FileBean;
import com.zpauly.githubapp.entity.response.repos.SingleCommitBean;
import com.zpauly.githubapp.presenter.issues.PullRequestContentContract;
import com.zpauly.githubapp.presenter.issues.PullRequestContentPresenter;
import com.zpauly.githubapp.ui.RefreshView;
import com.zpauly.githubapp.utils.ImageUtil;
import com.zpauly.githubapp.utils.TextUtil;
import com.zpauly.githubapp.utils.viewmanager.LoadMoreInSwipeRefreshLayoutMoreManager;
import com.zpauly.githubapp.utils.viewmanager.RefreshViewManager;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by zpauly on 2016/10/23.
 */

public class PullRequestsConversationsFragment extends BaseFragment implements PullRequestContentContract.View {
    private final String TAG = getClass().getName();

    private PullRequestContentContract.Presenter mPresenter;

    private RefreshView mRefreshView;
    private SwipeRefreshLayout mSRLayout;
    private AppCompatTextView mTitleTV;
    private AppCompatButton mStateBTN;
    private AppCompatTextView mDescTV;
    private CircleImageView mAvatarIV;
    private AppCompatTextView mUsernameTV;
    private AppCompatTextView mTimeTV;
    private ExpandableTextView mBodyTV;
    private RecyclerView mCommentsRV;

    private CommentsRecyclerViewAdapter mCommentsAdapter;

    private PullRequestBean pullRequestBean;

    private List<CommentBean> commentList = new ArrayList<>();

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
        mTitleTV = (AppCompatTextView) view.findViewById(R.id.pull_request_title_TV);
        mStateBTN = (AppCompatButton) view.findViewById(R.id.pull_request_state_BTN);
        mDescTV = (AppCompatTextView) view.findViewById(R.id.pull_request_description_TV);
        mAvatarIV = (CircleImageView) view.findViewById(R.id.pull_request_avatar_TV);
        mUsernameTV = (AppCompatTextView) view.findViewById(R.id.pull_request_username_TV);
        mTimeTV = (AppCompatTextView) view.findViewById(R.id.pull_request_time_TV);
        mBodyTV = (ExpandableTextView) view.findViewById(R.id.pull_request_body_ETV);
        mCommentsRV = (RecyclerView) view.findViewById(R.id.pull_request_comments_RV);

        setupViews();
        setupRecyclerView();
        setupSwipeRefreshLayout();

        loadMoreInSwipeRefreshLayoutMoreManager = new LoadMoreInSwipeRefreshLayoutMoreManager(mCommentsRV, mSRLayout) {
            @Override
            public void load() {
                getComments();
            }
        };
        refreshViewManager = new RefreshViewManager(mRefreshView, mSRLayout) {
            @Override
            public void load() {
                getComments();
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

    private void setupViews() {
        mTitleTV.setText(pullRequestBean.getTitle());
        mStateBTN.setText(pullRequestBean.getState());
        ImageUtil.loadAvatarImageFromUrl(getContext(), pullRequestBean.getUser().getAvatar_url(), mAvatarIV);
        mUsernameTV.setText(pullRequestBean.getUser().getLogin());
        if (pullRequestBean.getState().equals("open")) {
            mTimeTV.setText("opened at " + TextUtil.timeConverter(pullRequestBean.getCreated_at()));
            mStateBTN.setBackgroundColor(getResources().getColor(R.color.openStateColor));
        } else {
            mTimeTV.setText("closed at " + TextUtil.timeConverter(pullRequestBean.getClosed_at()));
            mStateBTN.setBackgroundColor(getResources().getColor(R.color.mergeStateColor));
        }
        mBodyTV.setText(pullRequestBean.getBody());
    }

    private void setupRecyclerView() {
        mCommentsAdapter = new CommentsRecyclerViewAdapter(getContext());
        mCommentsRV.setLayoutManager(new LinearLayoutManager(getContext()));
        mCommentsRV.setAdapter(mCommentsAdapter);
    }

    private void setupSwipeRefreshLayout() {
        mSRLayout.measure(View.MEASURED_SIZE_MASK, View.MEASURED_HEIGHT_STATE_SHIFT);
        mSRLayout.setColorSchemeResources(R.color.colorAccent);
        mSRLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.setPageId(1);
                loadMoreInSwipeRefreshLayoutMoreManager.setSwipeRefreshLayoutRefreshing(mCommentsAdapter);
            }
        });
    }

    private void getComments() {
        mPresenter.getComments();
    }

    @Override
    protected View setContentView(LayoutInflater inflater, @Nullable ViewGroup container) {
        return inflater.inflate(R.layout.fragment_pull_request_conversations, container, false);
    }

    @Override
    public void getCommentsSuccess() {
        mSRLayout.setRefreshing(false);
        mCommentsAdapter.swapAllData(commentList);
        if (!mRefreshView.isRefreshSuccess()) {
            mRefreshView.refreshSuccess();
        }
    }

    @Override
    public void getCommentsFail() {
        mSRLayout.setRefreshing(false);
        if (!mRefreshView.isRefreshSuccess()) {
            mRefreshView.refreshFail();
        }
    }

    @Override
    public void gettingComments(List<CommentBean> commentBeen) {
        loadMoreInSwipeRefreshLayoutMoreManager.hasNoMoreData(commentBeen, mCommentsAdapter);
        if (mSRLayout.isRefreshing()) {
            commentList.clear();
        }
        commentList.addAll(commentBeen);
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
