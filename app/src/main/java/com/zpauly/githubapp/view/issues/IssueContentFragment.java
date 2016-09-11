package com.zpauly.githubapp.view.issues;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ms.square.android.expandabletextview.ExpandableTextView;
import com.zpauly.githubapp.R;
import com.zpauly.githubapp.adapter.CommentsRecyclerViewAdapter;
import com.zpauly.githubapp.base.BaseFragment;
import com.zpauly.githubapp.entity.response.CommentBean;
import com.zpauly.githubapp.entity.response.issues.IssueBean;
import com.zpauly.githubapp.presenter.issues.IssueContentContract;
import com.zpauly.githubapp.presenter.issues.IssueContentPresenter;
import com.zpauly.githubapp.ui.FloatingActionButton;
import com.zpauly.githubapp.ui.RefreshView;
import com.zpauly.githubapp.utils.ImageUtil;
import com.zpauly.githubapp.utils.TextUtil;
import com.zpauly.githubapp.view.comment.CommentCreateActivity;
import com.zpauly.githubapp.view.profile.OthersActivity;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by zpauly on 16/9/5.
 */
public class IssueContentFragment extends BaseFragment implements IssueContentContract.View {
    private final String TAG = getClass().getName();

    private IssueContentContract.Presenter mPresenter;

    private RefreshView mRefreshView;
    private SwipeRefreshLayout mSRLayout;
    private AppCompatTextView mTitleTV;
    private CircleImageView mUserAvatarIV;
    private AppCompatTextView mUsernameTV;
    private AppCompatTextView mOpenTimeTV;
    private ExpandableTextView mBodyTV;
    private RecyclerView mCommentsRV;
    private FloatingActionButton mIssueFAB;

    private IssueBean issueBean;
    private String owner;

    private CommentsRecyclerViewAdapter mCommentsAdapter;

    private String repoName;

    private List<CommentBean> list = new ArrayList<>();

    @Override
    protected void initViews(View view) {
        getAttrs();

        new IssueContentPresenter(getContext(), this);

        mRefreshView = (RefreshView) view.findViewById(R.id.issue_content_RefreshView);
        mSRLayout = (SwipeRefreshLayout) view.findViewById(R.id.issue_content_SRLayout);
        mTitleTV = (AppCompatTextView) view.findViewById(R.id.issue_content_title);
        mUserAvatarIV = (CircleImageView) view.findViewById(R.id.issue_content_user_avatar);
        mUsernameTV = (AppCompatTextView) view.findViewById(R.id.issue_content_username);
        mOpenTimeTV = (AppCompatTextView) view.findViewById(R.id.issue_content_open_time);
        mBodyTV = (ExpandableTextView) view.findViewById(R.id.issue_content_body_ETV);
        mCommentsRV = (RecyclerView) view.findViewById(R.id.issue_content_comments_RV);
        mIssueFAB = (FloatingActionButton) view.findViewById(R.id.issue_content_FAB);

        setupSwipeRefreshLayout();
        setupRecyclerView();
        setupFloatingActionButton();

        setupViews();

        mRefreshView.setOnRefreshStateListener(new RefreshView.OnRefreshStateListener() {
            @Override
            public void beforeRefreshing() {
                getIssueComments();
            }

            @Override
            public void onRefreshSuccess() {
                mRefreshView.setVisibility(View.GONE);
                mSRLayout.setVisibility(View.VISIBLE);
            }

            @Override
            public void onRefreshFail() {
                mRefreshView.setVisibility(View.VISIBLE);
                mSRLayout.setVisibility(View.GONE);
            }
        });
        mRefreshView.startRefresh();
    }

    private void setupFloatingActionButton() {
        mIssueFAB.attachButtonToRecyclerView(mCommentsRV);
        mIssueFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CommentCreateActivity.launchActivity(getContext(), owner, repoName, issueBean.getNumber());
            }
        });
    }

    @Override
    protected View setContentView(LayoutInflater inflater, @Nullable ViewGroup container) {
        return inflater.inflate(R.layout.fragment_issue_content, container, false);
    }

    private void getAttrs() {
        Bundle bundle = getArguments();
        if (bundle != null) {
            issueBean = bundle.getParcelable(IssueContentActivity.ISSUE);
            owner = bundle.getString(IssueContentActivity.ISSUE_OWNER);
        }
        String strs[] = issueBean.getUrl().split("/");
        repoName = strs[strs.length - 3];
        Log.i(TAG, repoName);
    }

    private void setupViews() {
        mTitleTV.setText(issueBean.getTitle());
        ImageUtil.loadAvatarImageFromUrl(getContext(), issueBean.getUser().getAvatar_url(), mUserAvatarIV);
        mUsernameTV.setText(issueBean.getUser().getLogin());
        mOpenTimeTV.setText("at " + TextUtil.timeConverter(issueBean.getCreated_at()));
        mBodyTV.setText(issueBean.getBody());
        mUserAvatarIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OthersActivity.lanuchActivity(getContext(), issueBean.getUser().getLogin());
            }
        });
    }

    private void setupSwipeRefreshLayout() {
        mSRLayout.measure(View.MEASURED_SIZE_MASK, View.MEASURED_HEIGHT_STATE_SHIFT);
        mSRLayout.setColorSchemeResources(R.color.colorAccent);
        mSRLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                setSwipeRefreshViewRefershing();
            }
        });
    }

    private void setSwipeRefreshViewRefershing() {
        mCommentsAdapter.setHasLoading(true);
        mPresenter.setPageId(1);
        getIssueComments();
    }

    private void setupRecyclerView() {
        mCommentsAdapter = new CommentsRecyclerViewAdapter(getContext());
        mCommentsRV.setLayoutManager(new LinearLayoutManager(getContext()));
        mCommentsRV.setAdapter(mCommentsAdapter);
        final LinearLayoutManager manager = (LinearLayoutManager) mCommentsRV.getLayoutManager();
        mCommentsRV.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int lastItemPosition = manager.findLastCompletelyVisibleItemPosition();
                int firstItemPosition = manager.findFirstCompletelyVisibleItemPosition();
                if (lastItemPosition == mCommentsAdapter.getItemCount() - 1
                        && firstItemPosition != mCommentsAdapter.getItemCount() - 1
                        && mCommentsAdapter.isHasMoreData()) {
                    if (!mSRLayout.isRefreshing()) {
                        mCommentsAdapter.setHasLoading(true);
                        getIssueComments();
                    }
                }
            }
        });
    }

    private void getIssueComments() {
        mPresenter.getIssueComments();
    }

    @Override
    public void getCommentsSuccess() {
        mSRLayout.setRefreshing(false);
        if (!mRefreshView.isRefreshSuccess()) {
            mRefreshView.refreshSuccess();
        }
        mCommentsAdapter.swapAllData(list);
    }

    @Override
    public void getCommentsFail() {
        mSRLayout.setRefreshing(false);
        mRefreshView.refreshFail();
        if (!mRefreshView.isRefreshSuccess()) {
            mPresenter.setPageId(1);
        }
    }

    @Override
    public void gettingComments(List<CommentBean> commentBeen) {
        if (mSRLayout.isRefreshing()) {
            list.clear();
        }
        if (list == null || list.size() == 0)
            mCommentsAdapter.setHasLoading(false);
        list.addAll(commentBeen);
    }

    @Override
    public String getRepo() {
        return repoName;
    }

    @Override
    public String getOwner() {
        return owner;
    }

    @Override
    public int getNum() {
        return issueBean.getNumber();
    }

    @Override
    public void setPresenter(IssueContentContract.Presenter presenter) {
        this.mPresenter = presenter;
    }
}
