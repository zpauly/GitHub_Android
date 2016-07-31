package com.zpauly.githubapp.view.repositories;

import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.AppCompatTextView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.zpauly.githubapp.R;
import com.zpauly.githubapp.db.ReposModel;
import com.zpauly.githubapp.presenter.repos.RepoContentContract;
import com.zpauly.githubapp.presenter.repos.RepoContentPresenter;
import com.zpauly.githubapp.view.ToolbarActivity;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by zpauly on 16-7-31.
 */

public class RepoContentActivity extends ToolbarActivity implements RepoContentContract.View {
    private final String TAG = getClass().getName();

    private RepoContentContract.Presenter mPresenter;

    public static final String REPO = "REPO";

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
    private AppCompatTextView mReadMeContentTV;
    private AppCompatTextView mViewFilesTV;

    private ReposModel reposModel;

    @Override
    public void initViews() {
        Bundle bundle = getIntent().getBundleExtra(REPO);
        reposModel = bundle.getParcelable(REPO);
        if (reposModel == null) {
            Log.i(TAG, "model = null");
        }

        new RepoContentPresenter(this, this);

        mABLayout = (AppBarLayout) findViewById(R.id.repo_content_ABLayout);
        mSRLayout = (SwipeRefreshLayout) findViewById(R.id.repo_content_SRLayout);
        mTitleLayout = (LinearLayout) findViewById(R.id.repo_content_title_layout);
        mAvatarIV = (CircleImageView) findViewById(R.id.repo_content_avatar_IV);
        mTitleTV = (AppCompatTextView) findViewById(R.id.repo_content_title_TV);
        mDescLayout = (LinearLayout) findViewById(R.id.repo_content_desc_layout);
        mDescTV = (AppCompatTextView) findViewById(R.id.repo_content_desc_TV);
        mWatchersTV = (AppCompatTextView) findViewById(R.id.repo_content_watchers_TV);
        mStargazersTV = (AppCompatTextView) findViewById(R.id.repo_content_stargazers_TV);
        mForksTV = (AppCompatTextView) findViewById(R.id.repo_content_forks_TV);
        mReadMeContentTV = (AppCompatTextView) findViewById(R.id.repo_content_readme_content_TV);
        mViewFilesTV = (AppCompatTextView) findViewById(R.id.repo_content_view_files_TV);

        setupSwipeRefreshLayout();
//        setupViews();
    }

    private void setupSwipeRefreshLayout() {
        mSRLayout.measure(View.MEASURED_SIZE_MASK, View.MEASURED_HEIGHT_STATE_SHIFT);
        mSRLayout.setColorSchemeResources(R.color.colorAccent);
        mSRLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadReadMe();
            }
        });
    }

    private void setupViews() {
        mTitleTV.setText(reposModel.getFull_name());
        Glide.with(this)
                .load(Uri.parse(reposModel.getAvatar_url()))
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .crossFade()
                .centerCrop()
                .into(mAvatarIV);
        mDescTV.setText(reposModel.getDescription());
        mWatchersTV.setText(String.valueOf(reposModel.getWatchers_count()));
        mStargazersTV.setText(String.valueOf(reposModel.getStargazers_count()));
        mForksTV.setText(String.valueOf(reposModel.getForks_count()));
    }

    private void loadReadMe() {
        mPresenter.loadReadMe();
    }

    @Override
    public void initContent() {
        setContentView(R.layout.activity_repo_content);
    }

    @Override
    public void setPresenter(RepoContentContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void loadFail() {

    }

    @Override
    public void loadSuccess() {

    }
}
