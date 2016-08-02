package com.zpauly.githubapp.view.repositories;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.AppBarLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.AppCompatTextView;
import android.text.Html;
import android.text.Spanned;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.zpauly.githubapp.R;
import com.zpauly.githubapp.entity.response.RepositoriesBean;
import com.zpauly.githubapp.presenter.repos.RepoContentContract;
import com.zpauly.githubapp.presenter.repos.RepoContentPresenter;
import com.zpauly.githubapp.utils.HtmlImageGetter;
import com.zpauly.githubapp.view.ToolbarActivity;
import com.zpauly.githubapp.view.files.FilesActivity;
import com.zpauly.githubapp.view.profile.OthersActivity;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by zpauly on 16-7-31.
 */

public class RepoContentActivity extends ToolbarActivity implements RepoContentContract.View {
    private final String TAG = getClass().getName();

    private RepoContentContract.Presenter mPresenter;

    public static final String REPO = "REPO";

    public static final String FULL_NAME = "FULL_NAME";
    public static final String NAME = "NAME";
    public static final String REPO_URL = "REPO_URL";
    public static final String LOGIN = "LOGIN";

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
    protected void onStop() {
        mPresenter.stop();
        Glide.clear(mAvatarIV);
        super.onStop();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mSRLayout.setRefreshing(true);
        loadRepo();
    }

    @Override
    public void initViews() {
        getAttrs();

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
        mReadMeTV = (AppCompatTextView) findViewById(R.id.repo_content_readme_TV);
        mViewFilesTV = (AppCompatTextView) findViewById(R.id.repo_content_view_files_TV);
        mLoadAgainTV = (AppCompatTextView) findViewById(R.id.repo_content_readme_load_again_TV);
        mReadMePB = (ProgressBar) findViewById(R.id.repo_content_readme_PB);

        setupSwipeRefreshLayout();
        setupViews();
        mSRLayout.setRefreshing(true);
        loadRepo();
    }

    private void getAttrs() {
        Intent intent = getIntent();
        full_name = intent.getStringExtra(FULL_NAME);
        name = intent.getStringExtra(NAME);
        repo_url = intent.getStringExtra(REPO_URL);
        login = intent.getStringExtra(LOGIN);
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
                OthersActivity.lanuchActivity(RepoContentActivity.this, login);
            }
        });
    }

    @Override
    protected void setToolbar() {
        super.setToolbar();
        setToolbarTitle(name);
        setOnToolbarNavClickedListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
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
    public void initContent() {
        setContentView(R.layout.activity_repo_content);
    }

    @Override
    public void setPresenter(RepoContentContract.Presenter presenter) {
        mPresenter = presenter;
    }

    public static void launchActivity(Context context, String full_name, String name,
                                      String repo_url, String login) {
        Intent intent = new Intent();
        intent.putExtra(FULL_NAME, full_name);
        intent.putExtra(NAME, name);
        intent.putExtra(REPO_URL, repo_url);
        intent.putExtra(LOGIN, login);
        intent.setClass(context, RepoContentActivity.class);
        context.startActivity(intent);
//        ((Activity) context).finish();
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
        HtmlImageGetter imageGetter = new HtmlImageGetter(mReadMeTV, this,
                repoBean.getHtml_url() + "/raw/" + repoBean.getDefault_branch());
        Spanned htmlSpann = Html.fromHtml(content, imageGetter, null);
        mReadMeTV.setText(htmlSpann);
    }

    @Override
    public void loadingReadMe(String string) {
//        content = HtmlUtil.format(string).toString();
        content = string;
    }

    @Override
    public void loadRepoSuccess() {
        loadReadMe();
        mSRLayout.setRefreshing(false);
        Glide.with(getApplicationContext())
                .load(Uri.parse(repoBean.getOwner().getAvatar_url()))
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .crossFade()
                .centerCrop()
                .into(mAvatarIV);
        mDescTV.setText(repoBean.getDescription());
        mWatchersTV.setText(String.valueOf(repoBean.getWatchers_count()));
        mStargazersTV.setText(String.valueOf(repoBean.getStargazers_count()));
        mForksTV.setText(String.valueOf(repoBean.getForks_count()));
        mViewFilesTV.setEnabled(true);
        mViewFilesTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FilesActivity.launchActivity(RepoContentActivity.this, login, name,
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
