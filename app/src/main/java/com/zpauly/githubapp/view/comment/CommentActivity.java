package com.zpauly.githubapp.view.comment;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.zpauly.githubapp.R;
import com.zpauly.githubapp.adapter.CommentsRecyclerViewAdapter;
import com.zpauly.githubapp.entity.response.CommentBean;
import com.zpauly.githubapp.presenter.comment.CommitCommentContract;
import com.zpauly.githubapp.presenter.comment.CommitCommentPresenter;
import com.zpauly.githubapp.widget.FloatingActionButton;
import com.zpauly.githubapp.widget.RefreshView;
import com.zpauly.githubapp.utils.viewmanager.LoadMoreInSwipeRefreshLayoutMoreManager;
import com.zpauly.githubapp.utils.viewmanager.RefreshViewManager;
import com.zpauly.githubapp.view.ToolbarActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by zpauly on 16/9/22.
 */

public class CommentActivity extends ToolbarActivity implements CommitCommentContract.View {
    private final String TAG = getClass().getName();

    public static final String COMMENT_TYPE = "COMMENT_TYPE";
    public static final int TYPE_COMMIT = 0;

    public static final String OWNER = "OWNER";
    public static final String REPO = "REPO";
    public static final String REF = "REF";

    private CommitCommentContract.Presenter mCommitCommentPresenter;

    private int commentType;

    private String owner;
    private String repo;
    private String ref;

    @BindView(R.id.commit_comment_RefreshView) public RefreshView mRefreshView;
    @BindView(R.id.commit_comment_create_FAB) public FloatingActionButton mCommentFAB;
    @BindView(R.id.comment_content_layout) public CoordinatorLayout mContentLayout;
    @BindView(R.id.commit_comment_RV) public RecyclerView mCommentRV;
    @BindView(R.id.comment_SRLayout) public SwipeRefreshLayout mCommentSRLayout;

    private CommentsRecyclerViewAdapter mCommentAdapter;

    private List<CommentBean> commentList = new ArrayList<>();

    private LoadMoreInSwipeRefreshLayoutMoreManager loadMoreInSwipeRefreshLayoutMoreManager;
    private RefreshViewManager refreshViewManager;

    @Override
    protected void getParams(){
        commentType = getIntent().getIntExtra(COMMENT_TYPE, -1);
        switch (commentType) {
            case TYPE_COMMIT:
                owner = getIntent().getStringExtra(OWNER);
                repo = getIntent().getStringExtra(REPO);
                ref = getIntent().getStringExtra(REF);
                break;
            default:
                break;
        }

    }

    @Override
    protected void onStop() {
        mCommitCommentPresenter.stop();
        super.onStop();
    }

    @Override
    public void initViews() {
        switch (commentType) {
            case CommentActivity.TYPE_COMMIT:
                new CommitCommentPresenter(this, this);
                setupCommitComment();
                break;
            default:
                break;
        }
    }

    @Override
    public void initContent() {
        super.initContent();
        switch (commentType) {
            case CommentActivity.TYPE_COMMIT:
                setContent(R.layout.content_commit_comment);
                break;
            default:
                break;
        }
    }

    @Override
    protected void setToolbar() {
        super.setToolbar();
        setToolbarTitle(R.string.comments);
        setOnToolbarNavClickedListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    public static void launchCommitCommentActivity(Context context, String owner,
                                                   String repo, String ref) {
        Intent intent = new Intent();
        intent.putExtra(COMMENT_TYPE, TYPE_COMMIT);
        intent.putExtra(OWNER, owner);
        intent.putExtra(REPO, repo);
        intent.putExtra(REF, ref);
        intent.setClass(context, CommentActivity.class);
        context.startActivity(intent);
    }

    private void setupCommitComment() {
        setupSwipeRefreshLayout();
        setupCommitCommmentRecyclerView();
        mCommentFAB.attachButtonToRecyclerView(mCommentRV);

        setViewManager(new LoadMoreInSwipeRefreshLayoutMoreManager(mCommentRV, mCommentSRLayout) {
            @Override
            public void load() {
                getCommitComments();
            }
        }, new RefreshViewManager(mRefreshView, mContentLayout) {
            @Override
            public void load() {
                mCommitCommentPresenter.setPageId(1);
                getCommitComments();
            }
        });
        loadMoreInSwipeRefreshLayoutMoreManager = getViewManager(LoadMoreInSwipeRefreshLayoutMoreManager.class);
        refreshViewManager = getViewManager(RefreshViewManager.class);
    }

    private void setupSwipeRefreshLayout() {
        mCommentSRLayout.measure(View.MEASURED_SIZE_MASK, View.MEASURED_HEIGHT_STATE_SHIFT);
        mCommentSRLayout.setColorSchemeResources(R.color.colorAccent);
        mCommentSRLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mCommentAdapter.setHasLoading(true);
                mCommentAdapter.hideLoadingView();
                mCommitCommentPresenter.setPageId(1);
                getCommitComments();
            }
        });
    }

    @OnClick(R.id.create_comment_FAB)
    public void jumpToCommentCreateActivity() {
        CommentCreateActivity.launchCommitCommentActivity(CommentActivity.this,
                owner, repo, ref);
    }

    private void setupCommitCommmentRecyclerView() {
        mCommentAdapter = new CommentsRecyclerViewAdapter(this);
        mCommentRV.setLayoutManager(new LinearLayoutManager(this));
        mCommentRV.setAdapter(mCommentAdapter);
    }

    private void getCommitComments() {
        mCommitCommentPresenter.getASingleCommitComments();
    }

    @Override
    public void getCommentsSuccess() {
        mCommentAdapter.swapAllData(commentList);
        mCommentSRLayout.setRefreshing(false);
        if (!mRefreshView.isRefreshSuccess()) {
            mRefreshView.refreshSuccess();
        }
    }

    @Override
    public void getCommentsFail() {
        mCommentSRLayout.setRefreshing(false);
        if (!mRefreshView.isRefreshSuccess()) {
            mRefreshView.refreshFail();
        } else {
            Snackbar.make(mRefreshView, R.string.error_occurred, Snackbar.LENGTH_SHORT);
        }
    }

    @Override
    public void gettingComments(List<CommentBean> commentBeen) {
        if (!mCommentSRLayout.isRefreshing()) {
            commentList.clear();
        }
        loadMoreInSwipeRefreshLayoutMoreManager.hasNoMoreData(commentBeen, mCommentAdapter);
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
