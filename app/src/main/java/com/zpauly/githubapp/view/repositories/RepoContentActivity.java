package com.zpauly.githubapp.view.repositories;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.AppCompatTextView;
import android.text.Html;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.zpauly.githubapp.R;
import com.zpauly.githubapp.entity.response.repos.RepositoriesBean;
import com.zpauly.githubapp.listener.OnMenuItemSelectedListener;
import com.zpauly.githubapp.listener.OnNavItemClickListener;
import com.zpauly.githubapp.network.repositories.RepositoriesService;
import com.zpauly.githubapp.presenter.repos.RepoContentContract;
import com.zpauly.githubapp.presenter.repos.RepoContentPresenter;
import com.zpauly.githubapp.service.DownloadSevice;
import com.zpauly.githubapp.ui.RefreshView;
import com.zpauly.githubapp.utils.HtmlImageGetter;
import com.zpauly.githubapp.utils.ImageUtil;
import com.zpauly.githubapp.view.RightDrawerActivity;
import com.zpauly.githubapp.view.ToolbarActivity;
import com.zpauly.githubapp.view.files.FilesActivity;
import com.zpauly.githubapp.view.issues.IssuesActivity;
import com.zpauly.githubapp.view.profile.OthersActivity;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by zpauly on 16-7-31.
 */

public class RepoContentActivity extends RightDrawerActivity implements RepoContentContract.View {
    private final String TAG = getClass().getName();

    public static final String FULL_NAME = "FULL_NAME";
    public static final String NAME = "NAME";
    public static final String REPO_URL = "REPO_URL";
    public static final String LOGIN = "LOGIN";

    private String full_name;
    private String name;
    private String login;
    private String repo_url;

    private RepoContentContract.Presenter mPresenter;

    private AppBarLayout mABLayout;
    private SwipeRefreshLayout mSRLayout;
    private LinearLayout mTitleLayout;
    private CircleImageView mAvatarIV;
    private AppCompatTextView mTitleTV;
    private LinearLayout mDescLayout;
    private AppCompatTextView mDescTV;
    private LinearLayout mWatchersLayout;
    private LinearLayout mStargazersLayout;
    private LinearLayout mForksLayout;
    private AppCompatTextView mWatchersTV;
    private AppCompatTextView mStargazersTV;
    private AppCompatTextView mForksTV;
    private AppCompatTextView mIssuesTV;
    private AppCompatTextView mReadMeTV;
    private AppCompatTextView mLoadAgainTV;
    private AppCompatTextView mViewFilesTV;
    private ProgressBar mReadMePB;

    private MenuItem mMenuItemStar;
    private MenuItem mMenuItemChoose;
    private MenuItem mMenuDownload;

    private RefreshView mRefreshView;

    private String content;
    private RepositoriesBean repoBean;
    private boolean isStarred;

    private MaterialDialog downloadDialog;

    @Override
    protected void onStop() {
        mPresenter.stop();
        super.onStop();
    }

    @Override
    public void initViews() {
        getAttrs();

        setContent(R.layout.content_repo_content);

        new RepoContentPresenter(this, this);

        mSRLayout = (SwipeRefreshLayout) findViewById(R.id.repo_content_SRLayout);
        mTitleLayout = (LinearLayout) findViewById(R.id.repo_content_title_layout);
        mAvatarIV = (CircleImageView) findViewById(R.id.repo_content_avatar_IV);
        mTitleTV = (AppCompatTextView) findViewById(R.id.repo_content_title_TV);
        mDescLayout = (LinearLayout) findViewById(R.id.repo_content_desc_layout);
        mDescTV = (AppCompatTextView) findViewById(R.id.repo_content_desc_TV);
        mWatchersLayout = (LinearLayout) findViewById(R.id.repo_content_watchers_layout);
        mStargazersLayout = (LinearLayout) findViewById(R.id.repo_content_stargazers_layout);
        mForksLayout = (LinearLayout) findViewById(R.id.repo_content_forks_layout);
        mWatchersTV = (AppCompatTextView) findViewById(R.id.repo_content_watchers_TV);
        mStargazersTV = (AppCompatTextView) findViewById(R.id.repo_content_stargazers_TV);
        mForksTV = (AppCompatTextView) findViewById(R.id.repo_content_forks_TV);
        mIssuesTV = (AppCompatTextView) findViewById(R.id.repo_content_issues_TV);
        mReadMeTV = (AppCompatTextView) findViewById(R.id.repo_content_readme_TV);
        mViewFilesTV = (AppCompatTextView) findViewById(R.id.repo_content_view_files_TV);
        mLoadAgainTV = (AppCompatTextView) findViewById(R.id.repo_content_readme_load_again_TV);
        mReadMePB = (ProgressBar) findViewById(R.id.repo_content_readme_PB);

        mRefreshView = (RefreshView) findViewById(R.id.repo_content_RefreshView);

        setupSwipeRefreshLayout();
        setupViews();

        setupDialogs();

        mRefreshView.setOnRefreshStateListener(new RefreshView.OnRefreshStateListener() {
            @Override
            public void beforeRefreshing() {
                loadRepo();
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

        mReadMeTV.setMovementMethod(LinkMovementMethod.getInstance());
    }

    private void getAttrs() {
        Intent intent = getIntent();
        full_name = intent.getStringExtra(FULL_NAME);
        name = intent.getStringExtra(NAME);
        repo_url = intent.getStringExtra(REPO_URL);
        login = intent.getStringExtra(LOGIN);
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

    private void setupDialogs() {
        downloadDialog = new MaterialDialog.Builder(this)
                .title(full_name)
                .content(R.string.ensure_to_download)
                .cancelable(false)
                .positiveText(R.string.ok)
                .negativeText(R.string.cancel)
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        Intent intent = new Intent();
                        Log.i(TAG, "ref = " + repoBean.getDefault_branch());
                        intent.putExtra(DownloadSevice.ARCHIVE_FORMAT, RepositoriesService.ZIPBALL);
                        intent.putExtra(DownloadSevice.REF, repoBean.getDefault_branch());
                        intent.putExtra(DownloadSevice.FULL_NAME, full_name);
                        intent.putExtra(DownloadSevice.OWNER, login);
                        intent.setClass(RepoContentActivity.this, DownloadSevice.class);
                        RepoContentActivity.this.startService(intent);
                    }
                })
                .onNegative(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        dialog.dismiss();
                    }
                })
                .build();
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
        mIssuesTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IssuesActivity.launchRepoIssuesActivity(RepoContentActivity.this, repoBean.getOwner().getLogin(), repoBean.getName());
            }
        });
        mWatchersLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        mStargazersLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        mForksLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

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

    private void checkStarred() {
        mPresenter.checkStarred();
    }

    private void starRepo() {
        mPresenter.starRepo();
    }

    private void unstarRepo() {
        mPresenter.unstarRepo();
    }

    @Override
    public void setPresenter(RepoContentContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void loadReadMeFail() {
        mLoadAgainTV.setVisibility(View.VISIBLE);
        mReadMePB.setVisibility(View.GONE);
        mRefreshView.refreshFail();
    }

    @Override
    public void noReadMe() {
        mLoadAgainTV.setVisibility(View.GONE);
        mReadMePB.setVisibility(View.GONE);
        if (!mRefreshView.isRefreshSuccess()) {
            mRefreshView.refreshSuccess();
        }
    }

    @Override
    public void loadReadMeSuccess() {
        mReadMePB.setVisibility(View.GONE);
        mReadMeTV.setVisibility(View.VISIBLE);
        HtmlImageGetter imageGetter = new HtmlImageGetter(mReadMeTV, this,
                repoBean.getHtml_url() + "/raw/" + repoBean.getDefault_branch());
        Spanned htmlSpann = Html.fromHtml(content, imageGetter, null);
        mReadMeTV.setText(htmlSpann);
        if (!mRefreshView.isRefreshSuccess()) {
            mRefreshView.refreshSuccess();
        }
    }

    @Override
    public void loadingReadMe(String string) {
        content = string;
    }

    @Override
    public void loadRepoSuccess() {
        loadReadMe();
        mSRLayout.setRefreshing(false);
        ImageUtil.loadAvatarImageFromUrl(this, repoBean.getOwner().getAvatar_url(), mAvatarIV);
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
        mRefreshView.refreshFail();
    }

    @Override
    public void loadingRepo(RepositoriesBean bean) {
        repoBean = bean;
    }

    @Override
    public void checkStarredFail() {

    }

    @Override
    public void isStarred() {
        mMenuItemStar.setVisible(true);
        mMenuItemStar.setIcon(R.drawable.ic_star_white_24dp);
        isStarred = true;
    }

    @Override
    public void isUnstarred() {
        mMenuItemStar.setVisible(true);
        mMenuItemStar.setIcon(R.drawable.ic_star_border_white_24dp);
        isStarred = false;
    }

    @Override
    public void starSuccess() {
        mMenuItemStar.setIcon(R.drawable.ic_star_white_24dp);
        Snackbar.make(getCurrentFocus(), R.string.starred, Snackbar.LENGTH_SHORT).show();
        isStarred = true;
        mMenuItemStar.setEnabled(true);
    }

    @Override
    public void starFail() {

    }

    @Override
    public void unstarSuccess() {
        mMenuItemStar.setIcon(R.drawable.ic_star_border_white_24dp);
        Snackbar.make(getCurrentFocus(), R.string.unstarred, Snackbar.LENGTH_SHORT).show();
        isStarred = false;
        mMenuItemStar.setEnabled(true);
    }

    @Override
    public void unstarFail() {

    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public String getRepoName() {
        return name;
    }

    @Override
    public void inflateMenu(MenuInflater inflater, Menu menu) {
        inflater.inflate(R.menu.repo_toolbar_menu, menu);
    }

    @Override
    public void createMenu(Menu menu) {
        mMenuItemStar = menu.findItem(R.id.repo_menu_star);
        mMenuItemChoose = menu.findItem(R.id.repo_menu_choose);
        mMenuDownload = menu.findItem(R.id.repo_menu_download);
        mMenuItemStar.setVisible(false);
        checkStarred();
        setOnMenuItemSelectedListener(new OnMenuItemSelectedListener() {
            @Override
            public void onItemSelected(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.repo_menu_star:
                        item.setEnabled(false);
                        if (isStarred) {
                            unstarRepo();
                        } else {
                            starRepo();
                        }
                        break;
                    case R.id.repo_menu_download:
                        downloadDialog.show();
                        break;
                    case R.id.repo_menu_choose:
                        openDrawer();
                        break;
                    case R.id.repo_right_nav_commits:
                        RepoCommitActivity.launchActiivty(RepoContentActivity.this, login, name);
                        closeDrawer();
                        break;
                    case R.id.repo_right_nav_branches:
                        closeDrawer();
                        break;
                    case R.id.repo_right_nav_releases:
                        RepoReleasesActivity.launchActivity(RepoContentActivity.this, login, name);
                        closeDrawer();
                        break;
                    case R.id.repo_right_nav_contributors:
                        ContributorsActivity.launchActivity(RepoContentActivity.this, login, name);
                        closeDrawer();
                        break;
                    default:
                        break;
                }
            }
        });
    }

    @Override
    public void initNavMenu() {
        inflaterNavMenu(R.menu.repo_right_nav_menu);
    }
}
