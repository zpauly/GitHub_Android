package com.zpauly.githubapp.view.comment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zpauly.githubapp.R;
import com.zpauly.githubapp.adapter.CommentsRecyclerViewAdapter;
import com.zpauly.githubapp.base.BaseFragment;
import com.zpauly.githubapp.entity.response.CommentBean;
import com.zpauly.githubapp.presenter.comment.CommitCommentContract;
import com.zpauly.githubapp.presenter.comment.CommitCommentPresenter;
import com.zpauly.githubapp.ui.FloatingActionButton;
import com.zpauly.githubapp.ui.RefreshView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zpauly on 16/9/22.
 */

public class CommentFragment extends BaseFragment implements CommitCommentContract.View {
    private final String TAG = getClass().getName();

    private CommitCommentContract.Presenter mCommitCommentPresenter;

    private int commentType;

    private String owner;
    private String repo;
    private String ref;

    private RefreshView mRefreshView;

    private FloatingActionButton mCommentFAB;
    private CoordinatorLayout mContentLayout;
    private RecyclerView mCommentRV;
    private SwipeRefreshLayout mCommentSRLayout;
    private CommentsRecyclerViewAdapter mCommentAdapter;

    private List<CommentBean> commentList = new ArrayList<>();

    @Override
    protected void initViews(View view) {
        mRefreshView = (RefreshView) view.findViewById(R.id.commit_comment_RefreshView);
        mContentLayout = (CoordinatorLayout) view.findViewById(R.id.comment_content_layout);
        mCommentRV = (RecyclerView) view.findViewById(R.id.commit_comment_RV);
        mCommentSRLayout = (SwipeRefreshLayout) view.findViewById(R.id.comment_SRLayout);
        mCommentFAB = (FloatingActionButton) view.findViewById(R.id.commit_comment_create_FAB);

        switch (commentType) {
            case CommentActivity.TYPE_COMMIT:
                new CommitCommentPresenter(getContext(), this);
                setupCommitComment();
                break;
            default:
                break;
        }
    }

    private void setupCommitComment() {
        setupSwipeRefreshLayout();
        setupCommitCommmentRecyclerView();
        setupFloatingActionButton();

        mRefreshView.setOnRefreshStateListener(new RefreshView.OnRefreshStateListener() {
            @Override
            public void beforeRefreshing() {
                mCommitCommentPresenter.setPageId(1);
                getCommitComments();
            }

            @Override
            public void onRefreshSuccess() {
                mRefreshView.setVisibility(View.GONE);
                mContentLayout.setVisibility(View.VISIBLE);
            }

            @Override
            public void onRefreshFail() {
                mRefreshView.setVisibility(View.VISIBLE);
                mContentLayout.setVisibility(View.GONE);
            }
        });
        mRefreshView.startRefresh();
    }

    private void setupSwipeRefreshLayout() {
        mCommentSRLayout.measure(View.MEASURED_SIZE_MASK, View.MEASURED_HEIGHT_STATE_SHIFT);
        mCommentSRLayout.setColorSchemeResources(R.color.colorAccent);
        mCommentSRLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mCommentAdapter.setHasLoading(true);
                mCommitCommentPresenter.setPageId(1);
                getCommitComments();
            }
        });
    }

    private void setupFloatingActionButton() {
        mCommentFAB.attachButtonToRecyclerView(mCommentRV);
        mCommentFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private void setupCommitCommmentRecyclerView() {
        mCommentAdapter = new CommentsRecyclerViewAdapter(getContext());
        mCommentRV.setLayoutManager(new LinearLayoutManager(getContext()));
        mCommentRV.setAdapter(mCommentAdapter);

        final LinearLayoutManager manager = (LinearLayoutManager) mCommentRV.getLayoutManager();
        mCommentRV.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int lastItemPosition = manager.findLastCompletelyVisibleItemPosition();
                int firstItemPosition = manager.findFirstCompletelyVisibleItemPosition();
                if (lastItemPosition == mCommentAdapter.getItemCount() - 1
                        && firstItemPosition != mCommentAdapter.getItemCount() - 1
                        && mCommentAdapter.isHasMoreData()) {
                    if (!mCommentSRLayout.isRefreshing()) {
                        mCommentAdapter.setHasLoading(true);
                        getCommitComments();
                    }
                }
            }
        });
    }

    private void getAttrs() {
        Bundle bundle = getArguments();
        if (bundle != null) {
            commentType = bundle.getInt(CommentActivity.COMMENT_TYPE);
            owner = bundle.getString(CommentActivity.OWNER);
            repo = bundle.getString(CommentActivity.REPO);
            ref = bundle.getString(CommentActivity.REF);
        }
    }

    @Override
    protected View setContentView(LayoutInflater inflater, @Nullable ViewGroup container) {
        getAttrs();
        View view = null;

        switch (commentType) {
            case CommentActivity.TYPE_COMMIT:
                view = inflater.inflate(R.layout.fragment_commit_comment, container, false);
                break;
            default:
                break;
        }
        return view;
    }

    private void getCommitComments() {
        mCommitCommentPresenter.getASingleCommitComments();
    }

    @Override
    public void getCommentsSuccess() {
        mCommentSRLayout.setRefreshing(false);
        if (!mRefreshView.isRefreshSuccess()) {
            mRefreshView.refreshSuccess();
        }
    }

    @Override
    public void getCommentsFail() {
        mCommentSRLayout.setRefreshing(false);
        mRefreshView.refreshFail();
    }

    @Override
    public void gettingComments(List<CommentBean> commentBeen) {
        if (!mCommentSRLayout.isRefreshing()) {
            commentList.clear();
        }
        if (commentBeen == null || commentBeen.size() == 0) {
            mCommentAdapter.setHasLoading(false);
        }
        commentList.addAll(commentBeen);
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
    public String getRef() {
        return ref;
    }

    @Override
    public void setPresenter(CommitCommentContract.Presenter presenter) {
        switch (commentType) {
            case CommentActivity.TYPE_COMMIT:
                mCommitCommentPresenter = presenter;
                break;
            default:
                break;
        }
    }
}
