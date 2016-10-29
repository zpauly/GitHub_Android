package com.zpauly.githubapp.view.repositories;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.AppCompatTextView;
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
import com.zpauly.githubapp.entity.response.repos.BranchBean;
import com.zpauly.githubapp.entity.response.repos.RepositoriesBean;
import com.zpauly.githubapp.entity.response.repos.TagBean;
import com.zpauly.githubapp.listener.OnMenuItemSelectedListener;
import com.zpauly.githubapp.listener.OnNavItemClickListener;
import com.zpauly.githubapp.network.repositories.RepositoriesService;
import com.zpauly.githubapp.presenter.repos.ForksActivity;
import com.zpauly.githubapp.presenter.repos.RepoContentContract;
import com.zpauly.githubapp.presenter.repos.RepoContentPresenter;
import com.zpauly.githubapp.service.DownloadSevice;
import com.zpauly.githubapp.ui.RefreshView;
import com.zpauly.githubapp.utils.HtmlImageGetter;
import com.zpauly.githubapp.utils.ImageUtil;
import com.zpauly.githubapp.utils.TextUtil;
import com.zpauly.githubapp.view.RightDrawerActivity;
import com.zpauly.githubapp.view.files.FilesActivity;
import com.zpauly.githubapp.view.issues.IssuesOrPullRequestsActivity;
import com.zpauly.githubapp.view.profile.OthersActivity;
import com.zpauly.githubapp.view.users.UsersActivity;

import java.util.ArrayList;
import java.util.List;

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
    private AppCompatTextView mPullRequestsTV;
    private ProgressBar mReadMePB;

    private MenuItem mMenuItemStar;
    private MenuItem mMenuWatch;

    private RefreshView mRefreshView;

    private String content;
    private RepositoriesBean repoBean;
    private boolean isStarred;
    private boolean isWatched;

    private MaterialDialog mDownloadDialog;
    private MaterialDialog mLoadingDialog;
    private MaterialDialog mBranchesDialog;
    private MaterialDialog mTagsDialog;

    private List<String> branches = new ArrayList<>();
    private List<String> tags = new ArrayList<>();

    private String preBranch;
    private String branch;
    private String preRef;
    private String ref = preRef;

    private boolean isFirstLoad = true;

    private boolean isStarChecked;
    private boolean isWatchChecked;

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
        mPullRequestsTV = (AppCompatTextView) findViewById(R.id.repo_content_pull_requests_TV);
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
        mToolbarPB.setVisibility(View.VISIBLE);
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
        mDownloadDialog = new MaterialDialog.Builder(this)
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

        mLoadingDialog = new MaterialDialog.Builder(this)
                .cancelable(false)
                .title(R.string.loading)
                .content(R.string.please_wait)
                .progress(true, 0)
                .build();
    }

    private void setBranchesDialog() {
        mBranchesDialog = new MaterialDialog.Builder(this)
                .title(R.string.branches)
                .items(branches)
                .cancelable(false)
                .positiveText(R.string.ok)
                .negativeText(R.string.cancel)
                .itemsCallbackSingleChoice(-1, new MaterialDialog.ListCallbackSingleChoice() {
                    @Override
                    public boolean onSelection(MaterialDialog dialog, View itemView, int which, CharSequence text) {
                        if (which < branches.size() && which >= 0) {
                            preBranch = branch;
                            branch = branches.get(which);
                            if (!branches.get(which).equals(ref)) {
                                preRef = ref;
                                ref = branches.get(which);
                                mSRLayout.setRefreshing(true);
                                loadRepo();
                            }
                        }
                        return true;
                    }
                })
                .onNegative(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        branch = preBranch;
                        ref = preRef;
                    }
                })
                .build();
    }

    private void setTagsDialog() {
        mTagsDialog = new MaterialDialog.Builder(this)
                .title(R.string.tags)
                .items(tags)
                .cancelable(false)
                .positiveText(R.string.ok)
                .negativeText(R.string.cancel)
                .itemsCallbackSingleChoice(-1, new MaterialDialog.ListCallbackSingleChoice() {
                    @Override
                    public boolean onSelection(MaterialDialog dialog, View itemView, int which, CharSequence text) {
                        if (which < tags.size() && which >= 0) {
                            if (!tags.get(which).equals(ref)) {
                                preRef = ref;
                                ref = tags.get(which);
                                mSRLayout.setRefreshing(true);
                                loadRepo();
                            }
                        }
                        return true;
                    }
                })
                .onNegative(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        ref = preRef;
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
                IssuesOrPullRequestsActivity.launchRepoIssuesActivity(RepoContentActivity.this, repoBean.getOwner().getLogin(), repoBean.getName());
            }
        });
        mPullRequestsTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IssuesOrPullRequestsActivity.launchPullRequestsActivity(RepoContentActivity.this,
                        repoBean.getOwner().getLogin(), repoBean.getName());
            }
        });
        mWatchersLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UsersActivity.launchWatchersActivity(RepoContentActivity.this, repoBean.getOwner().getLogin(), repoBean.getName());
            }
        });
        mStargazersLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UsersActivity.launchStargazersActivity(RepoContentActivity.this, repoBean.getOwner().getLogin(), repoBean.getName());
            }
        });
        mForksLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ForksActivity.launchActivity(RepoContentActivity.this, repoBean.getOwner().getLogin(), repoBean.getName());
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

    private void loadBranches() {
        mPresenter.loadBranches();
    }

    private void loadTags() {
        mPresenter.loadTags();
    }

    private void loadRepo() {
        Log.i(TAG, "in load ref = " + ref);
        mPresenter.loadRepo();
    }

    private void checkStarred() {
        mPresenter.checkStarred();
    }

    private void checkWatched() {
        mPresenter.checkWatched();
    }

    private void starRepo() {
        mPresenter.starRepo();
        mLoadingDialog.show();
    }

    private void unstarRepo() {
        mPresenter.unstarRepo();
        mLoadingDialog.show();
    }

    private void watchRepo() {
        mPresenter.watchRepo();
        mLoadingDialog.show();
    }

    private void unwatchRepo() {
        mPresenter.unwatchRepo();
        mLoadingDialog.show();
    }

    @Override
    public void setPresenter(RepoContentContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void loadBranchesSuccess() {
        mLoadingDialog.dismiss();
        setBranchesDialog();
        mBranchesDialog.show();
    }

    @Override
    public void loadBranchesFail() {
        mLoadingDialog.dismiss();
        Snackbar.make(getCurrentFocus(), R.string.load_fail, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void loadTagsSuccess() {
        mLoadingDialog.dismiss();
        setTagsDialog();
        mTagsDialog.show();
    }

    @Override
    public void loadTagsFail() {
        mLoadingDialog.dismiss();
        Snackbar.make(getCurrentFocus(), R.string.load_fail, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void loadingBranches(List<BranchBean> branchBeen) {
        for (BranchBean branch : branchBeen) {
            branches.add(branch.getName());
        }
    }

    @Override
    public void loadingTags(List<TagBean> tagBeen) {
        for (TagBean tag : tagBeen) {
            tags.add(tag.getName());
        }
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
        TextUtil.showReadMe(mReadMeTV, content, imageGetter);
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
                        ref, branch, repoBean.getHtml_url());
            }
        });
        if (isFirstLoad) {
            preBranch = branch = repoBean.getDefault_branch();
            preRef = ref = repoBean.getDefault_branch();
            isFirstLoad = false;
        }
        initNav();
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
        Snackbar.make(getCurrentFocus(), R.string.error_occurred, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void isStarred() {
        mMenuItemStar.setIcon(R.drawable.ic_star_white_24dp);
        isStarred = true;
        isStarChecked = true;
        showStarAndWatch();
    }

    @Override
    public void isUnstarred() {
        mMenuItemStar.setIcon(R.drawable.ic_star_border_white_24dp);
        isStarred = false;
        isStarChecked = true;
        showStarAndWatch();
    }

    @Override
    public void starSuccess() {
        mMenuItemStar.setIcon(R.drawable.ic_star_white_24dp);
        mLoadingDialog.dismiss();
        Snackbar.make(getCurrentFocus(), R.string.starred, Snackbar.LENGTH_SHORT).show();
        isStarred = true;
        mMenuItemStar.setEnabled(true);
    }

    @Override
    public void starFail() {
        mLoadingDialog.dismiss();
        Snackbar.make(getCurrentFocus(), R.string.error_occurred, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void unstarSuccess() {
        mMenuItemStar.setIcon(R.drawable.ic_star_border_white_24dp);
        mLoadingDialog.dismiss();
        Snackbar.make(getCurrentFocus(), R.string.unstarred, Snackbar.LENGTH_SHORT).show();
        isStarred = false;
        mMenuItemStar.setEnabled(true);
    }

    @Override
    public void unstarFail() {
        Snackbar.make(getCurrentFocus(), R.string.error_occurred, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void isWatched() {
        Log.i(TAG, "is watched");
        mMenuWatch.setIcon(R.drawable.ic_visibility_white_24dp);
        isWatched = true;
        isWatchChecked = true;
        showStarAndWatch();
    }

    @Override
    public void isUnwatched() {
        Log.i(TAG, "is unwatched");
        mMenuWatch.setIcon(R.drawable.ic_visibility_off_white_24dp);
        isWatched = false;
        isWatchChecked = true;
        showStarAndWatch();
    }

    @Override
    public void checkWatchedFail() {
        Log.i(TAG, "check watch failed");
        Snackbar.make(getCurrentFocus(), R.string.error_occurred, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void watchSuccess() {
        mMenuWatch.setIcon(R.drawable.ic_visibility_white_24dp);
        mLoadingDialog.dismiss();
        Snackbar.make(getCurrentFocus(), R.string.watch_success, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void watchFail() {
        mLoadingDialog.dismiss();
        Snackbar.make(getCurrentFocus(), R.string.watch_fail, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void unwatchSuccess() {
        mMenuWatch.setIcon(R.drawable.ic_visibility_off_white_24dp);
        mLoadingDialog.dismiss();
        Snackbar.make(getCurrentFocus(), R.string.stop_watch_success, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void unwatchFail() {
        mLoadingDialog.dismiss();
        Snackbar.make(getCurrentFocus(), R.string.stop_watch_fail, Snackbar.LENGTH_SHORT).show();
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
    public String getRef() {
        return ref;
    }

    @Override
    public void inflateMenu(MenuInflater inflater, Menu menu) {
        inflater.inflate(R.menu.repo_toolbar_menu, menu);
        initNav();
    }

    @Override
    public void createMenu(Menu menu) {
        mMenuItemStar = menu.findItem(R.id.repo_menu_star);
        mMenuItemStar.setVisible(false);
        mMenuWatch = menu.findItem(R.id.repo_menu_watch);
        mMenuWatch.setVisible(false);
        checkStarred();
        checkWatched();
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
                    case R.id.repo_menu_watch:
                        if (isWatched) {
                            unwatchRepo();
                        } else {
                            watchRepo();
                        }
                        break;
                    case R.id.repo_menu_choose:
                        openDrawer();
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

    private void initNav() {
        setOnNavItemClickListener(new OnNavItemClickListener() {
            @Override
            public void onItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.repo_right_nav_commits:
                        Log.i(TAG, "load repo commit activity");
                        RepoCommitActivity.launchActiivty(RepoContentActivity.this, login, name);
                        break;
                    case R.id.repo_right_nav_branches:
                        BranchesActivity.launchActivity(RepoContentActivity.this, login, name, repoBean.getDefault_branch());
                        break;
                    case R.id.repo_right_nav_releases:
                        RepoReleasesActivity.launchActivity(RepoContentActivity.this, login, name);
                        break;
                    case R.id.repo_right_nav_contributors:
                        ContributorsActivity.launchActivity(RepoContentActivity.this, login, name);
                        break;
                    case R.id.repo_right_nav_download:
                        mDownloadDialog.show();
                        break;
                    case R.id.repo_right_nav_switch_branches:
                        if (branches.size() == 0) {
                            mLoadingDialog.show();
                            loadBranches();
                        } else {
                            mBranchesDialog.show();
                        }
                        break;
                    case R.id.repo_right_nav_switch_tags:
                        if (tags.size() == 0) {
                            mLoadingDialog.show();
                            loadTags();
                        } else {
                            mTagsDialog.show();
                        }
                        break;
                    default:
                        break;
                }
            }
        });
    }

    private void showStarAndWatch() {
        if (isStarChecked && isWatchChecked) {
            mToolbarPB.setVisibility(View.GONE);
            mMenuWatch.setVisible(true);
            mMenuItemStar.setVisible(true);
        }
    }
}
