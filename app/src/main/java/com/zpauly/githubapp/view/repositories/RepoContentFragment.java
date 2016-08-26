package com.zpauly.githubapp.view.repositories;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.AppCompatTextView;
import android.text.Html;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.zpauly.githubapp.R;
import com.zpauly.githubapp.base.BaseFragment;
import com.zpauly.githubapp.entity.response.RepositoriesBean;
import com.zpauly.githubapp.presenter.repos.RepoContentContract;
import com.zpauly.githubapp.presenter.repos.RepoContentPresenter;
import com.zpauly.githubapp.utils.HtmlImageGetter;
import com.zpauly.githubapp.utils.ImageUtil;
import com.zpauly.githubapp.view.files.FilesActivity;
import com.zpauly.githubapp.view.profile.OthersActivity;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by zpauly on 16-8-6.
 */

public class RepoContentFragment extends BaseFragment implements RepoContentContract.View {
    private final String TAG = getClass().getName();

    private RepoContentContract.Presenter mPresenter;

    private String full_name;
    private String name;
    private String login;
    private String avatar_url;
    private String repo_url;

    private AppBarLayout mABLayout;
    private SwipeRefreshLayout mSRLayout;
    private LinearLayout mTitleLayout;
    private CircleImageView mAvatarIV;
    private AppCompatTextView mTitleTV;
    private LinearLayout mDescLayout;
    private AppCompatTextView mDescTV;
    private AppCompatTextView mWatchersTV;
    private AppCompatTextView mStargazersTV;
    private AppCompatTextView mForksTV;
    private AppCompatTextView mReadMeTV;
    private AppCompatTextView mLoadAgainTV;
    private AppCompatTextView mViewFilesTV;
    private ProgressBar mReadMePB;

    private String content;
    private RepositoriesBean repoBean;

    @Override
    protected void initViews(View view) {
        getAttrs();

        new RepoContentPresenter(getContext(), this);

        mSRLayout = (SwipeRefreshLayout) view.findViewById(R.id.repo_content_SRLayout);
        mTitleLayout = (LinearLayout) view.findViewById(R.id.repo_content_title_layout);
        mAvatarIV = (CircleImageView) view.findViewById(R.id.repo_content_avatar_IV);
        mTitleTV = (AppCompatTextView) view.findViewById(R.id.repo_content_title_TV);
        mDescLayout = (LinearLayout) view.findViewById(R.id.repo_content_desc_layout);
        mDescTV = (AppCompatTextView) view.findViewById(R.id.repo_content_desc_TV);
        mWatchersTV = (AppCompatTextView) view.findViewById(R.id.repo_content_watchers_TV);
        mStargazersTV = (AppCompatTextView) view.findViewById(R.id.repo_content_stargazers_TV);
        mForksTV = (AppCompatTextView) view.findViewById(R.id.repo_content_forks_TV);
        mReadMeTV = (AppCompatTextView) view.findViewById(R.id.repo_content_readme_TV);
        mViewFilesTV = (AppCompatTextView) view.findViewById(R.id.repo_content_view_files_TV);
        mLoadAgainTV = (AppCompatTextView) view.findViewById(R.id.repo_content_readme_load_again_TV);
        mReadMePB = (ProgressBar) view.findViewById(R.id.repo_content_readme_PB);

        setupSwipeRefreshLayout();
        setupViews();
        mSRLayout.setRefreshing(true);
        loadRepo();
    }

    private void getAttrs() {
        Bundle bundle = getArguments();
        full_name = bundle.getString(RepoContentActivity.FULL_NAME);
        name = bundle.getString(RepoContentActivity.NAME);
        repo_url = bundle.getString(RepoContentActivity.REPO_URL);
        login = bundle.getString(RepoContentActivity.LOGIN);
    }

    private void setupSwipeRefreshLayout() {
        mSRLayout.measure(View.MEASURED_SIZE_MASK, View.MEASURED_HEIGHT_STATE_SHIFT);
        mSRLayout.setColorSchemeResources(R.color.colorAccent);
        mSRLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadRepo();
            }
        });
    }

    private void setupViews() {
        mTitleTV.setText(full_name);
        mLoadAgainTV.setVisibility(View.GONE);
        mLoadAgainTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadReadMe();
            }
        });
        mViewFilesTV.setEnabled(false);
        mAvatarIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OthersActivity.lanuchActivity(getContext(), login);
            }
        });
    }

    @Override
    protected View setContentView(LayoutInflater inflater, @Nullable ViewGroup container) {
        return inflater.inflate(R.layout.fragment_repo_content, container, false);
    }

    private void loadReadMe() {
        mLoadAgainTV.setVisibility(View.GONE);
        mReadMePB.setVisibility(View.VISIBLE);
        mReadMeTV.setText(null);
        mReadMeTV.setVisibility(View.GONE);
        mPresenter.loadReadMe();
    }

    private void loadRepo() {
        mPresenter.loadRepo();
    }

    @Override
    public void setPresenter(RepoContentContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void loadReadMeFail() {
        mLoadAgainTV.setVisibility(View.VISIBLE);
        mReadMePB.setVisibility(View.GONE);
    }

    @Override
    public void loadReadMeSuccess() {
        mReadMePB.setVisibility(View.GONE);
        mReadMeTV.setVisibility(View.VISIBLE);
        HtmlImageGetter imageGetter = new HtmlImageGetter(mReadMeTV, getContext(),
                repoBean.getHtml_url() + "/raw/" + repoBean.getDefault_branch());
        Spanned htmlSpann = Html.fromHtml(content, imageGetter, null);
        mReadMeTV.setText(htmlSpann);
    }

    @Override
    public void loadingReadMe(String string) {
        content = string;
    }

    @Override
    public void loadRepoSuccess() {
        loadReadMe();
        mSRLayout.setRefreshing(false);
        ImageUtil.loadAvatarImageFromUrl(getContext(), repoBean.getOwner().getAvatar_url(), mAvatarIV);
        mDescTV.setText(repoBean.getDescription());
        mWatchersTV.setText(String.valueOf(repoBean.getWatchers_count()));
        mStargazersTV.setText(String.valueOf(repoBean.getStargazers_count()));
        mForksTV.setText(String.valueOf(repoBean.getForks_count()));
        mViewFilesTV.setEnabled(true);
        mViewFilesTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FilesActivity.launchActivity(getContext(), login, name,
                        repoBean.getDefault_branch(), repoBean.getHtml_url());
            }
        });
    }

    @Override
    public void loadRepoFail() {
        mSRLayout.setRefreshing(false);
    }

    @Override
    public void loadingRepo(RepositoriesBean bean) {
        repoBean = bean;
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public String getRepoName() {
        return name;
    }
}
