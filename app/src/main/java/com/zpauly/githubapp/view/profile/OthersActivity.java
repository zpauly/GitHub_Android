package com.zpauly.githubapp.view.profile;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.zpauly.githubapp.Constants;
import com.zpauly.githubapp.R;
import com.zpauly.githubapp.entity.response.AuthenticatedUserBean;
import com.zpauly.githubapp.entity.response.UserBean;
import com.zpauly.githubapp.listener.OnMenuItemSelectedListener;
import com.zpauly.githubapp.listener.OnNavItemClickListener;
import com.zpauly.githubapp.presenter.profile.ProfileContract;
import com.zpauly.githubapp.presenter.profile.ProfilePresenter;
import com.zpauly.githubapp.widget.RefreshView;
import com.zpauly.githubapp.utils.DisplayUtil;
import com.zpauly.githubapp.utils.ImageUtil;
import com.zpauly.githubapp.utils.SPUtil;
import com.zpauly.githubapp.utils.TextUtil;
import com.zpauly.githubapp.view.RightDrawerActivity;
import com.zpauly.githubapp.view.events.EventsActivity;
import com.zpauly.githubapp.view.repositories.ReposActivity;
import com.zpauly.githubapp.view.users.UsersActivity;

import butterknife.BindView;

/**
 * Created by zpauly on 16-7-27.
 */

public class OthersActivity extends RightDrawerActivity implements ProfileContract.View {
    private final String TAG = getClass().getName();

    private ProfileContract.Presenter mPresenter;

    public static final String USERNAME = "USERNAME";
    public static final String IS_ORG = "IS_ORG";

    private boolean isOrg = false;
    private String username;

    @BindView(R.id.others_SWLayout) public SwipeRefreshLayout mSWLayout;
    @BindView(R.id.profile_avatar) public ImageView mAvatarIV;
    @BindView(R.id.profile_login_TV) public TextView mLoginTV;
    @BindView(R.id.profile_name_TV) public TextView mNameTV;
    @BindView(R.id.profile_bio_TV) public TextView mBioTV;
    @BindView(R.id.profile_location_TV) public TextView mLocationTV;
    @BindView(R.id.profile_email_TV) public TextView mEmailTV;
    @BindView(R.id.profile_join_time_TV) public TextView mTimeTV;
    @BindView(R.id.profile_followers_TV) public TextView mFollowersTV;
    @BindView(R.id.profile_following_TV) public TextView mFollowingTV;
    @BindView(R.id.profile_company_TV) public TextView mCompanyTV;
    @BindView(R.id.profile_blog_TV) public TextView mBlogTV;
    @BindView(R.id.profile_location_divider_IV) public ImageView mLocationDividerIV;
    @BindView(R.id.profile_email_divider_IV) public ImageView mEmailDividerIV;
    @BindView(R.id.profile_time_divider_IV) public ImageView mTimeDividerIV;
    @BindView(R.id.profile_company_divider_IV) public ImageView mCompanyDividerIV;
    @BindView(R.id.profile_blog_divider_IV) public ImageView mBlogDividerIV;
    @BindView(R.id.profile_location_layout) public LinearLayout mLocationLayout;
    @BindView(R.id.profile_email_layout) public LinearLayout mEmailLayout;
    @BindView(R.id.profile_time_layout) public LinearLayout mTimeLayout;
    @BindView(R.id.profile_company_layout) public LinearLayout mCompanyLayout;
    @BindView(R.id.profile_blog_layout) public LinearLayout mBlogLayout;
    @BindView(R.id.profile_followers_layout) public LinearLayout mFollowersLayout;
    @BindView(R.id.profile_following_layout) public LinearLayout mFollowingLayout;
    @BindView(R.id.profile_events_layout) public RelativeLayout mEventsLayout;
    @BindView(R.id.profile_repos_layout) public RelativeLayout mReposLayout;
    @BindView(R.id.profile_orgs_layout) public RelativeLayout mOrgsLayout;
    @BindView(R.id.profile_organizations_TV) public TextView mOrgTV;
    @BindView(R.id.others_RefreshView) public RefreshView mRefreshView;

    private UserBean user;

    private MenuItem mMenuItemFollow;

    private MaterialDialog mLoadingDialog;

    private boolean isFollowed;

    @Override
    protected void onStop() {
        mPresenter.stop();
        super.onStop();
    }

    @Override
    public void initViews() {
        new ProfilePresenter(this, this);

        mLoginTV.setText("");
        mNameTV.setText("");
        mBioTV.setText("");
        mLocationTV.setText("");
        mEmailTV.setText("");
        mTimeTV.setText("");
        mCompanyTV.setText("");
        mBlogTV.setText("");
        if (isOrg) {
            mOrgTV.setText(getString(R.string.members));
            mFollowersLayout.setVisibility(View.GONE);
            mFollowingLayout.setVisibility(View.GONE);
        }

        mLoadingDialog = new MaterialDialog.Builder(this)
                .cancelable(false)
                .title(R.string.loading)
                .content(R.string.please_wait)
                .progress(true, 0)
                .build();

        setupSwipeRefreshLayout();

        mRefreshView.setOnRefreshStateListener(new RefreshView.OnRefreshStateListener() {
            @Override
            public void beforeRefreshing() {
                loadData();
            }

            @Override
            public void onRefreshSuccess() {
                mRefreshView.setVisibility(View.GONE);
                mSWLayout.setVisibility(View.VISIBLE);
            }

            @Override
            public void onRefreshFail() {
                mRefreshView.setVisibility(View.VISIBLE);
                mRefreshView.setVisibility(View.GONE);
            }
        });
        mRefreshView.startRefresh();
    }

    @Override
    protected void getParams() {
        username = getIntent().getStringExtra(USERNAME);
        isOrg = getIntent().getBooleanExtra(IS_ORG, false);
    }

    @Override
    public void initContent() {
        super.initContent();
        setContent(R.layout.content_others);
    }

    @Override
    protected void setToolbar() {
        super.setToolbar();
        mToolbarPB.setVisibility(View.VISIBLE);
        if (username != null) {
            setToolbarTitle(username);
        } else {
            setToolbarTitle(SPUtil.getString(this,
                    Constants.USER_INFO, Constants.USER_LOGIN, null));
        }
        setOnToolbarNavClickedListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void setupSwipeRefreshLayout() {
        mSWLayout.setColorSchemeResources(R.color.colorAccent);
        mSWLayout.measure(View.MEASURED_SIZE_MASK, View.MEASURED_HEIGHT_STATE_SHIFT);
        mSWLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadData();
            }
        });
    }

    private void setupListener() {
        mFollowersLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UsersActivity.launchActivity(OthersActivity.this, username, UsersActivity.FOLLOWERS);
            }
        });

        mFollowingLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UsersActivity.launchActivity(OthersActivity.this, username, UsersActivity.FOLLOWING);
            }
        });

        mEventsLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra(EventsActivity.EVENTS_ID, EventsActivity.USER_EVENTS);
                intent.putExtra(USERNAME, username);
                intent.setClass(OthersActivity.this, EventsActivity.class);
                startActivity(intent);
            }
        });

        mReposLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ReposActivity.launchActivity(OthersActivity.this, username);
            }
        });

        mOrgsLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isOrg) {
                    UsersActivity.launchMemberActivity(OthersActivity.this, username);
                } else {
                    UsersActivity.launchActivity(OthersActivity.this, username, UsersActivity.ORGS);
                }
            }
        });
    }

    private void loadData() {
        mPresenter.loadOtherInfo();
    }

    private void checkUserFollow() {
        mPresenter.checkUserFollowed();
    }

    private void follow() {
        mLoadingDialog.show();
        mPresenter.follow();
    }

    private void unfollow() {
        mLoadingDialog.show();
        mPresenter.unfollow();
    }

    @Override
    public void loadInfoSuccess() {
        if (user != null) {
            ImageUtil.loadAvatarImageFromUrl(this, user.getAvatar_url(), mAvatarIV);
            mNameTV.setText(user.getName());
            mLoginTV.setText(user.getLogin());
            mBioTV.setText(user.getBio());
            mLocationTV.setText(user.getLocation());
            mEmailTV.setText(user.getEmail());
            mTimeTV.setText(TextUtil.timeConverter(user.getCreated_at()));
            mCompanyTV.setText(user.getCompany());
            mBlogTV.setText(user.getBlog());
            mFollowersTV.setText(String.valueOf(user.getFollowers()));
            mFollowingTV.setText(String.valueOf(user.getFollowing()));
            if (user.getEmail() == null || user.getEmail().equals("")) {
                mEmailLayout.setVisibility(View.GONE);
                mEmailDividerIV.setVisibility(View.GONE);
            }
            if (user.getLocation() == null || user.getLocation().equals("")) {
                mLocationLayout.setVisibility(View.GONE);
                mLocationDividerIV.setVisibility(View.GONE);
            }
            if (user.getCreated_at() == null || user.getCreated_at().equals("")) {
                mTimeLayout.setVisibility(View.GONE);
                mTimeDividerIV.setVisibility(View.GONE);
            }
            if (user.getCompany() == null || user.getCompany().equals("")) {
                mCompanyLayout.setVisibility(View.GONE);
                mCompanyDividerIV.setVisibility(View.GONE);
            }
            if (user.getBlog() == null || user.getBlog().equals("")) {
                mBlogLayout.setVisibility(View.GONE);
                mBlogDividerIV.setVisibility(View.GONE);
            }
        }
        mSWLayout.setRefreshing(false);
        setupListener();
        if (!mRefreshView.isRefreshSuccess()) {
            mRefreshView.refreshSuccess();
        }
    }

    public static void lanuchActivity(Context context, String username) {
        Intent intent = new Intent();
        intent.putExtra(USERNAME, username);
        intent.putExtra(IS_ORG, false);
        intent.setClass(context, OthersActivity.class);
        context.startActivity(intent);
//        ((Activity) context).finish();
    }

    public static void launchOrganizationActivity(Context context, String organizationName) {
        Intent intent = new Intent();
        intent.putExtra(USERNAME, organizationName);
        intent.putExtra(IS_ORG, true);
        intent.setClass(context, OthersActivity.class);
        context.startActivity(intent);
    }

    @Override
    public void loadInfoFail() {
        mSWLayout.setRefreshing(false);
        if (!mRefreshView.isRefreshSuccess()) {
            mRefreshView.refreshFail();
        } else {
            Snackbar.make(mRefreshView, R.string.error_occurred, Snackbar.LENGTH_SHORT);
        }
    }

    @Override
    public void loadInfo(AuthenticatedUserBean user) {

    }

    @Override
    public void loadOtherInfo(UserBean user) {
        this.user = user;
    }

    @Override
    public void checkFollowFail() {
        Snackbar.make(getCurrentFocus(), R.string.error_occurred, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void isFollowed() {
        mMenuItemFollow.setVisible(true);
        mMenuItemFollow.setTitle(R.string.unfollow);
        mToolbarPB.setVisibility(View.GONE);
        isFollowed = true;
    }

    @Override
    public void isUnfollowed() {
        mMenuItemFollow.setVisible(true);
        mMenuItemFollow.setTitle(R.string.follow);
        mToolbarPB.setVisibility(View.GONE);
        isFollowed = false;
    }

    @Override
    public void followFail() {
        mLoadingDialog.dismiss();
        Snackbar.make(getCurrentFocus(), R.string.follow_fail, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void followSuccess() {
        isFollowed = true;
        mMenuItemFollow.setTitle(R.string.unfollow);
        mLoadingDialog.dismiss();
        Snackbar.make(mToolbar, R.string.follow_success, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void unfollowSuccess() {
        isFollowed = false;
        mMenuItemFollow.setTitle(R.string.follow);
        mLoadingDialog.dismiss();
        Snackbar.make(mToolbar, R.string.unfollow_success, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void unfollowFail() {
        mLoadingDialog.dismiss();
        Snackbar.make(mToolbar, R.string.unfollow_fail, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public void setPresenter(ProfileContract.Presenter presenter) {
        this.mPresenter = presenter;
    }

    @Override
    public void inflateMenu(MenuInflater inflater, Menu menu) {
        inflater.inflate(R.menu.others_toolbar_menu, menu);
    }

    @Override
    public void createMenu(Menu menu) {
        super.createMenu(menu);
        mMenuItemFollow = menu.findItem(R.id.others_toolbar_follow);
        mMenuItemFollow.setVisible(false);
        if (!isOrg) {
            checkUserFollow();
        } else {
            mToolbarPB.setVisibility(View.GONE);
        }
        setOnMenuItemSelectedListener(new OnMenuItemSelectedListener() {
            @Override
            public void onItemSelected(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.others_toolbar_follow:
                        if (isFollowed) {
                            unfollow();
                        } else {
                            follow();
                        }
                        break;
                    case R.id.others_toolbar_choose:
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
        inflaterNavMenu(R.menu.profile_right_nav_menu);
        setOnNavItemClickListener(new OnNavItemClickListener() {
            @Override
            public void onItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.profile_right_nav_share:
                        DisplayUtil.share(OthersActivity.this,
                                "User " + getUsername(),
                                "https://github.com/" + getUsername());
                        break;
                    default:
                        break;
                }
            }
        });
    }
}
