package com.zpauly.githubapp.view.issues;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.NestedScrollView;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.ms.square.android.expandabletextview.ExpandableTextView;
import com.zpauly.githubapp.R;
import com.zpauly.githubapp.adapter.CommentsRecyclerViewAdapter;
import com.zpauly.githubapp.entity.response.CommentBean;
import com.zpauly.githubapp.entity.response.issues.IssueBean;
import com.zpauly.githubapp.entity.response.issues.LabelBean;
import com.zpauly.githubapp.presenter.issues.IssueContentContract;
import com.zpauly.githubapp.presenter.issues.IssueContentPresenter;
import com.zpauly.githubapp.ui.FloatingActionButton;
import com.zpauly.githubapp.ui.IssueLabelView;
import com.zpauly.githubapp.ui.RefreshView;
import com.zpauly.githubapp.utils.ImageUtil;
import com.zpauly.githubapp.utils.TextUtil;
import com.zpauly.githubapp.utils.viewmanager.LoadMoreInSwipeRefreshLayoutMoreManager;
import com.zpauly.githubapp.utils.viewmanager.RefreshViewManager;
import com.zpauly.githubapp.view.ToolbarActivity;
import com.zpauly.githubapp.view.comment.CommentCreateActivity;
import com.zpauly.githubapp.view.profile.OthersActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by zpauly on 16/9/5.
 */
public class IssueContentActivity extends ToolbarActivity implements IssueContentContract.View {
    private final String TAG = getClass().getName();

    public static final String ISSUE = "ISSUE";
    public static final String ISSUE_OWNER = "ISSUE_OWNER";

    private IssueContentContract.Presenter mPresenter;

    @BindView(R.id.issue_content_RefreshView) public RefreshView mRefreshView;
    @BindView(R.id.issue_content_SRLayout) public SwipeRefreshLayout mSRLayout;
    @BindView(R.id.issue_content_title) public AppCompatTextView mTitleTV;
    @BindView(R.id.issue_content_user_avatar) public ImageView mUserAvatarIV;
    @BindView(R.id.issue_content_username) public AppCompatTextView mUsernameTV;
    @BindView(R.id.issue_content_open_time) public AppCompatTextView mOpenTimeTV;
    @BindView(R.id.issue_content_body_ETV) public ExpandableTextView mBodyTV;
    @BindView(R.id.issue_content_comments_RV) public RecyclerView mCommentsRV;
    @BindView(R.id.issue_content_NSV) public NestedScrollView mContentNSV;
    @BindView(R.id.issue_content_FAB) public FloatingActionButton mIssueFAB;
    @BindView(R.id.issue_content_labels_layout) public LinearLayout mLabelsLayout;
    @BindView(R.id.issue_content_milestone_TV) public AppCompatTextView mMilestoneTV;
    @BindView(R.id.issue_content_milestone_layout) public LinearLayout mMilestoneLayout;

    private IssueBean issueBean;
    private String owner;

    private CommentsRecyclerViewAdapter mCommentsAdapter;

    private String repoName;

    private List<CommentBean> list = new ArrayList<>();

    private LoadMoreInSwipeRefreshLayoutMoreManager loadMoreInSwipeRefreshLayoutMoreManager;
    private RefreshViewManager refreshViewManager;

    @Override
    protected void onStop() {
        mPresenter.stop();
        super.onStop();
    }

    @Override
    public void initViews() {
        new IssueContentPresenter(this, this);

        setupSwipeRefreshLayout();
        setupRecyclerView();
        mIssueFAB.attachButtonToNestedScrollView(mContentNSV);

        setupViews();

        setViewManager(new LoadMoreInSwipeRefreshLayoutMoreManager(mCommentsRV, mSRLayout) {
            @Override
            public void load() {
                getIssueComments();
            }
        }, new RefreshViewManager(mRefreshView, mSRLayout) {
            @Override
            public void load() {
                getIssueComments();
            }
        });

        loadMoreInSwipeRefreshLayoutMoreManager = getViewManager(LoadMoreInSwipeRefreshLayoutMoreManager.class);
        refreshViewManager = getViewManager(RefreshViewManager.class);
    }

    @Override
    protected void setToolbar() {
        super.setToolbar();
        setToolbarTitle("ISSUE #" + issueBean.getNumber());
        setOnToolbarNavClickedListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    @Override
    protected void getParams() {
        issueBean = getIntent().getParcelableExtra(ISSUE);
        owner = getIntent().getStringExtra(ISSUE_OWNER);
        String strs[] = issueBean.getUrl().split("/");
        repoName = strs[strs.length - 3];
    }

    @Override
    public void initContent() {
        super.initContent();
        setContent(R.layout.content_issue_content);
    }

    public static void launchActivity(Context context, IssueBean issue, String owner) {
        Intent intent = new Intent();
        intent.putExtra(ISSUE, issue);
        intent.putExtra(ISSUE_OWNER, owner);
        intent.setClass(context, IssueContentActivity.class);
        context.startActivity(intent);
//        ((Activity) context).finish();
    }

    @OnClick(R.id.issue_content_FAB)
    public void jump2CommentCreateActivity() {
        CommentCreateActivity.launchIssueCommentActivity(IssueContentActivity.this, owner, repoName, issueBean.getNumber());
    }

    private void addLabel(LabelBean labelBean) {
        IssueLabelView issueLabelView = new IssueLabelView(this);
        issueLabelView.setBgColor("#" + labelBean.getColor());
        issueLabelView.setText(labelBean.getName());
        mLabelsLayout.addView(issueLabelView);
    }

    private void setupViews() {
        mTitleTV.setText(issueBean.getTitle());
        ImageUtil.loadAvatarImageFromUrl(IssueContentActivity.this, issueBean.getUser().getAvatar_url(), mUserAvatarIV);
        mUsernameTV.setText(issueBean.getUser().getLogin());
        if (issueBean.getClosed_at() == null) {
            mOpenTimeTV.setText("opened at " + TextUtil.timeConverter(issueBean.getCreated_at()));
        } else {
            mOpenTimeTV.setText("closed at " + TextUtil.timeConverter(issueBean.getClosed_at()));
        }
        mBodyTV.setText(issueBean.getBody());
        mUserAvatarIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OthersActivity.lanuchActivity(IssueContentActivity.this, issueBean.getUser().getLogin());
            }
        });
        for (LabelBean labelBean : issueBean.getLabels()) {
            addLabel(labelBean);
        }
        if (issueBean.getMilestone() != null) {
            mMilestoneTV.setText(issueBean.getMilestone().getTitle());
        } else {
            mMilestoneLayout.setVisibility(View.GONE);
        }
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
        mPresenter.setPageId(1);
        loadMoreInSwipeRefreshLayoutMoreManager.setSwipeRefreshLayoutRefreshing(mCommentsAdapter);
    }

    private void setupRecyclerView() {
        mCommentsAdapter = new CommentsRecyclerViewAdapter(this);
        mCommentsRV.setLayoutManager(new LinearLayoutManager(this));
        mCommentsRV.setAdapter(mCommentsAdapter);
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
        if (!mRefreshView.isRefreshSuccess()) {
            mRefreshView.refreshFail();
        } else {
            Snackbar.make(mRefreshView, R.string.error_occurred, Snackbar.LENGTH_SHORT);
        }
    }

    @Override
    public void gettingComments(List<CommentBean> commentBeen) {
        if (mSRLayout.isRefreshing()) {
            list.clear();
        }
        loadMoreInSwipeRefreshLayoutMoreManager.hasNoMoreData(commentBeen, mCommentsAdapter);
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
