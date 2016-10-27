package com.zpauly.githubapp.view.commit;

import android.content.Context;
import android.content.Intent;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.zpauly.githubapp.R;
import com.zpauly.githubapp.adapter.PatchRecyclerViewAdapter;
import com.zpauly.githubapp.entity.response.repos.SingleCommitBean;
import com.zpauly.githubapp.listener.OnMenuItemSelectedListener;
import com.zpauly.githubapp.presenter.commit.CommitContentContract;
import com.zpauly.githubapp.presenter.commit.CommitContentPresenter;
import com.zpauly.githubapp.ui.RefreshView;
import com.zpauly.githubapp.utils.ImageUtil;
import com.zpauly.githubapp.utils.TextUtil;
import com.zpauly.githubapp.view.ToolbarActivity;
import com.zpauly.githubapp.view.comment.CommentActivity;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by zpauly on 16/9/22.
 */

public class CommitContentActivity extends ToolbarActivity implements CommitContentContract.View {
    private final String TAG = getClass().getName();

    public static final String OWNER = "OWNER";
    public static final String REPO = "REPO";
    public static final String SHA = "SHA";

    private CommitContentContract.Presenter mPresenter;

    private RefreshView mRefreshView;
    private NestedScrollView mContentNSV;
    private AppCompatTextView mMessageTV;
    private CircleImageView mAvatarIV;
    private AppCompatTextView mUsernameTV;
    private AppCompatTextView mShaTV;
    private AppCompatTextView mTimeTV;
    private RecyclerView mPatchRV;

    private PatchRecyclerViewAdapter mPatchAdapter;

    private String owner;
    private String repo;
    private String sha;

    private SingleCommitBean singleCommitBean;

    @Override
    protected void onStop() {
        mPresenter.stop();
        super.onStop();
    }

    @Override
    public void initViews() {
        getParams();

        setContent(R.layout.content_commit_content);

        new CommitContentPresenter(this, this);

        mRefreshView = (RefreshView) findViewById(R.id.commit_content_RefreshView);
        mContentNSV = (NestedScrollView) findViewById(R.id.commit_content_layout);
        mMessageTV = (AppCompatTextView) findViewById(R.id.commit_content_message_TV);
        mAvatarIV = (CircleImageView) findViewById(R.id.commit_content_committer_avatar_IV);
        mUsernameTV = (AppCompatTextView) findViewById(R.id.commit_content_committer_name_TV);
        mShaTV = (AppCompatTextView) findViewById(R.id.commit_content_sha_TV);
        mTimeTV = (AppCompatTextView) findViewById(R.id.commit_content_time_TV);
        mPatchRV = (RecyclerView) findViewById(R.id.commit_content_patch_RV);

        setupRecyclerView();

        mRefreshView.setOnRefreshStateListener(new RefreshView.OnRefreshStateListener() {
            @Override
            public void beforeRefreshing() {
                getSingleCommit();
            }

            @Override
            public void onRefreshSuccess() {
                mContentNSV.setVisibility(View.VISIBLE);
                mRefreshView.setVisibility(View.GONE);
            }

            @Override
            public void onRefreshFail() {
                mContentNSV.setVisibility(View.GONE);
                mRefreshView.setVisibility(View.VISIBLE);
            }
        });
        mRefreshView.startRefresh();
    }

    @Override
    protected void setToolbar() {
        super.setToolbar();
        setToolbarTitle(sha.substring(0, 8));
        setOnToolbarNavClickedListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void getParams() {
        owner = getIntent().getStringExtra(OWNER);
        repo = getIntent().getStringExtra(REPO);
        sha = getIntent().getStringExtra(SHA);
    }

    public static void launchActivity(Context context, String owner, String repo, String sha) {
        Intent intent = new Intent();
        intent.putExtra(OWNER, owner);
        intent.putExtra(REPO, repo);
        intent.putExtra(SHA, sha);
        intent.setClass(context, CommitContentActivity.class);
        context.startActivity(intent);
    }

    private void getSingleCommit() {
        mPresenter.getSingleCommit();
    }

    private void setupRecyclerView() {
        mPatchAdapter = new PatchRecyclerViewAdapter(this);
        mPatchRV.setLayoutManager(new LinearLayoutManager(this));
        mPatchRV.setAdapter(mPatchAdapter);
        mPatchAdapter.hideLoadingView();
    }

    @Override
    public void getCommitSuccess() {
        mMessageTV.setText(singleCommitBean.getCommit().getMessage());
        mUsernameTV.setText(singleCommitBean.getCommit().getCommitter().getName());
        mTimeTV.setText(TextUtil.timeConverter(singleCommitBean.getCommit().getCommitter().getDate()));
        mShaTV.setText(singleCommitBean.getSha().substring(0, 8));
        if (singleCommitBean.getAuthor() != null) {
            ImageUtil.loadAvatarImageFromUrl(this,
                    singleCommitBean.getAuthor().getAvatar_url(),
                    mAvatarIV);
        }
        mPatchAdapter.swapAllData(singleCommitBean.getFiles());
        if (!mRefreshView.isRefreshSuccess()) {
            mRefreshView.refreshSuccess();
        }
    }

    @Override
    public void getCommitFail() {
        mRefreshView.refreshFail();
    }

    @Override
    public void gettingCommit(SingleCommitBean singleCommitBean) {
        this.singleCommitBean = singleCommitBean;
    }

    @Override
    public String getRepo() {
        return repo;
    }

    @Override
    public String getOwner() {
        return owner;
    }

    @Override
    public String getSha() {
        return sha;
    }

    @Override
    public void setPresenter(CommitContentContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void inflateMenu(MenuInflater inflater, Menu menu) {
        inflater.inflate(R.menu.commit_toolbar_menu, menu);
    }

    @Override
    public void createMenu(Menu menu) {
        setOnMenuItemSelectedListener(new OnMenuItemSelectedListener() {
            @Override
            public void onItemSelected(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.commit_toolbar_comment:
                        CommentActivity.launchCommitCommentActivity(CommitContentActivity.this,
                                owner, repo, sha);
                        break;
                    default:
                        break;
                }
            }
        });
    }
}
