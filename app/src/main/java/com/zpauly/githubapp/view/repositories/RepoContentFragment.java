package com.zpauly.githubapp.view.repositories;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.AppCompatTextView;
import android.text.Html;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.zpauly.githubapp.R;
import com.zpauly.githubapp.base.BaseFragment;
import com.zpauly.githubapp.entity.response.RepositoriesBean;
import com.zpauly.githubapp.listener.OnMenuItemSelectedListener;
import com.zpauly.githubapp.presenter.repos.RepoContentContract;
import com.zpauly.githubapp.presenter.repos.RepoContentPresenter;
import com.zpauly.githubapp.ui.RefreshView;
import com.zpauly.githubapp.utils.HtmlImageGetter;
import com.zpauly.githubapp.utils.ImageUtil;
import com.zpauly.githubapp.view.ToolbarMenuFragment;
import com.zpauly.githubapp.view.files.FilesActivity;
import com.zpauly.githubapp.view.profile.OthersActivity;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by zpauly on 16-8-6.
 */

public class RepoContentFragment extends ToolbarMenuFragment implements RepoContentContract.View {
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

    private MenuItem mMenuItemStar;

    private RefreshView mRefreshView;

    private String content;
    private RepositoriesBean repoBean;
    private boolean isStarred;

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

        mRefreshView = (RefreshView) view.findViewById(R.id.repo_content_RefreshView);

        setupSwipeRefreshLayout();
        setupViews();

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
    public void loadReadMeSuccess() {
        mReadMePB.setVisibility(View.GONE);
        mReadMeTV.setVisibility(View.VISIBLE);
        HtmlImageGetter imageGetter = new HtmlImageGetter(mReadMeTV, getContext(),
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
        Snackbar.make(getView(), R.string.starred, Snackbar.LENGTH_SHORT).show();
        isStarred = true;
        mMenuItemStar.setEnabled(true);
    }

    @Override
    public void starFail() {

    }

    @Override
    public void unstarSuccess() {
        mMenuItemStar.setIcon(R.drawable.ic_star_border_white_24dp);
        Snackbar.make(getView(), R.string.unstarred, Snackbar.LENGTH_SHORT).show();
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
    public void inflateMenu() {
        inflateMenu(R.menu.repo_toolbar_menu);
    }

    @Override
    public void createMenu(Menu menu) {
        mMenuItemStar = menu.findItem(R.id.repo_menu_star);
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
                }
            }
        });
    }
}
