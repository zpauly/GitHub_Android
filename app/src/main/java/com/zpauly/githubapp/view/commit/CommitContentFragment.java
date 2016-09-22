package com.zpauly.githubapp.view.commit;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zpauly.githubapp.R;
import com.zpauly.githubapp.adapter.PatchRecyclerViewAdapter;
import com.zpauly.githubapp.base.BaseFragment;
import com.zpauly.githubapp.entity.response.repos.SingleCommitBean;
import com.zpauly.githubapp.presenter.commit.CommitContentContract;
import com.zpauly.githubapp.presenter.commit.CommitContentPresenter;
import com.zpauly.githubapp.ui.RefreshView;
import com.zpauly.githubapp.utils.ImageUtil;
import com.zpauly.githubapp.utils.TextUtil;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by zpauly on 16/9/22.
 */

public class CommitContentFragment extends BaseFragment implements CommitContentContract.View {
    private final String TAG = getClass().getName();

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

    private void getAttrs() {
        Bundle bundle = getArguments();
        if (bundle != null) {
            owner = bundle.getString(CommitContentActivity.OWNER);
            repo = bundle.getString(CommitContentActivity.REPO);
            sha = bundle.getString(CommitContentActivity.SHA);
        }
    }

    @Override
    protected void initViews(View view) {
        getAttrs();

        new CommitContentPresenter(getContext(), this);

        mRefreshView = (RefreshView) view.findViewById(R.id.commit_content_RefreshView);
        mContentNSV = (NestedScrollView) view.findViewById(R.id.commit_content_layout);
        mMessageTV = (AppCompatTextView) view.findViewById(R.id.commit_content_message_TV);
        mAvatarIV = (CircleImageView) view.findViewById(R.id.commit_content_committer_avatar_IV);
        mUsernameTV = (AppCompatTextView) view.findViewById(R.id.commit_content_committer_name_TV);
        mShaTV = (AppCompatTextView) view.findViewById(R.id.commit_content_sha_TV);
        mTimeTV = (AppCompatTextView) view.findViewById(R.id.commit_content_time_TV);
        mPatchRV = (RecyclerView) view.findViewById(R.id.commit_content_patch_RV);

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
    protected View setContentView(LayoutInflater inflater, @Nullable ViewGroup container) {
        return inflater.inflate(R.layout.fragment_commit_content, container, false);
    }

    private void getSingleCommit() {
        mPresenter.getSingleCommit();
    }

    private void setupRecyclerView() {
        mPatchAdapter = new PatchRecyclerViewAdapter(getContext());
        mPatchRV.setLayoutManager(new LinearLayoutManager(getContext()));
        mPatchRV.setAdapter(mPatchAdapter);
    }

    @Override
    public void getCommitSuccess() {
        Log.i(TAG, "success");
        mMessageTV.setText(singleCommitBean.getCommit().getMessage());
        mUsernameTV.setText(singleCommitBean.getCommit().getCommitter().getName());
        mTimeTV.setText(TextUtil.timeConverter(singleCommitBean.getCommit().getCommitter().getDate()));
        mShaTV.setText(singleCommitBean.getSha().substring(0, 8));
        if (singleCommitBean.getAuthor() != null) {
            ImageUtil.loadAvatarImageFromUrl(getContext(),
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
}
