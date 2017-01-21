package com.zpauly.githubapp.view.profile;

import android.content.Context;
import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zpauly.githubapp.Constants;
import com.zpauly.githubapp.R;
import com.zpauly.githubapp.entity.response.AuthenticatedUserBean;
import com.zpauly.githubapp.entity.response.UserBean;
import com.zpauly.githubapp.listener.OnMenuItemSelectedListener;
import com.zpauly.githubapp.listener.OnNavItemClickListener;
import com.zpauly.githubapp.presenter.profile.ProfileContract;
import com.zpauly.githubapp.presenter.profile.ProfilePresenter;
import com.zpauly.githubapp.ui.RefreshView;
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
 * Created by zpauly on 16-7-30.
 */

public class ProfileActivity extends RightDrawerActivity implements ProfileContract.View {
    private final String TAG = getClass().getName();

    private ProfileContract.Presenter mPresenter;

    @BindView(R.id.profile_SRLayout) public SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.profile_avatar) public ImageView mAvatarIV;
    @BindView(R.id.profile_login_TV) public TextView mLoginTV;
    @BindView(R.id.profile_name_TV) public TextView mNameTV;
    @BindView(R.id.profile_bio_TV) public TextView mBioTV;
    @BindView(R.id.profile_location_TV) public TextView mLocationTV;
    @BindView(R.id.profile_email_TV) public TextView mEmailTV;
    @BindView(R.id.profile_join_time_TV) public TextView mJoinTimeTV;
    @BindView(R.id.profile_followers_TV) public TextView mFollowersTV;
    @BindView(R.id.profile_following_TV) public TextView mFollowingTV;
    @BindView(R.id.profile_company_TV) public TextView mCompanyTV;
    @BindView(R.id.profile_blog_TV) public TextView mBlogTV;
    @BindView(R.id.profile_location_divider_IV) public ImageView mLocationDividerIV;
    @BindView(R.id.profile_email_divider_IV) public ImageView mEmailDividerIV;
    @BindView(R.id.profile_time_divider_IV) public ImageView mTimeDividerIV;
    @BindView(R.id.profile_company_divider_IV) public ImageView mCompanyDividerIV;
    @BindView(R.id.profile_blog_divider_IV) public ImageView mBlogDividerTV;
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
    @BindView(R.id.profile_RefreshView) public RefreshView mRefreshView;

    private AuthenticatedUserBean userBean;

    @Override
    public void initViews() {
        new ProfilePresenter(this, this);

        mLoginTV.setText("");
        mNameTV.setText("");
        mBioTV.setText("");
        mLocationTV.setText("");
        mEmailTV.setText("");
        mJoinTimeTV.setText("");
        mCompanyTV.setText("");
        mBlogTV.setText("");

        setUserInfo();

        setupSwpieRefreshLayout();

        mRefreshView.setOnRefreshStateListener(new RefreshView.OnRefreshStateListener() {
            @Override
            public void beforeRefreshing() {
                loadUserInfo();
            }

            @Override
            public void onRefreshSuccess() {
                mRefreshView.setVisibility(View.GONE);
                mSwipeRefreshLayout.setVisibility(View.VISIBLE);
            }

            @Override
            public void onRefreshFail() {
                mRefreshView.setVisibility(View.VISIBLE);
                mSwipeRefreshLayout.setVisibility(View.GONE);
            }
        });
        mRefreshView.startRefresh();
    }

    @Override
    public void initContent() {
        super.initContent();
        setContent(R.layout.fragment_profile);
    }

    @Override
    protected void setToolbar() {
        super.setToolbar();
        setToolbarTitle(R.string.profile);
        setOnToolbarNavClickedListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    public static void launchActivity(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, ProfileActivity.class);
        context.startActivity(intent);
    }

    private void setUserInfo() {
        if (userBean == null)
            return;
        ImageUtil.loadAvatarImageFromUrl(this, userBean.getAvatar_url(), mAvatarIV);
        mFollowersTV.setText(String.valueOf(userBean.getFollowers()));
        mFollowingTV.setText(String.valueOf(userBean.getFollowing()));
        mLoginTV.setText(userBean.getLogin());
        mNameTV.setText(userBean.getName());
        mBioTV.setText(userBean.getBio());
        mLocationTV.setText(userBean.getLocation());
        mEmailTV.setText(userBean.getEmail());
        mCompanyTV.setText(userBean.getCompany());
        mBlogTV.setText(userBean.getBlog());
        mJoinTimeTV.setText(TextUtil.timeConverter(userBean.getCreated_at()));
        if (userBean.getEmail() == null || userBean.getEmail().equals("")) {
            mEmailLayout.setVisibility(View.GONE);
            mEmailDividerIV.setVisibility(View.GONE);
        }
        if (userBean.getLocation() == null || userBean.getLocation().equals("")) {
            mLocationLayout.setVisibility(View.GONE);
            mLocationDividerIV.setVisibility(View.GONE);
        }
        if (userBean.getCreated_at() == null || userBean.getCreated_at().equals("")) {
            mTimeLayout.setVisibility(View.GONE);
            mTimeDividerIV.setVisibility(View.GONE);
        }
        if (userBean.getCompany() == null || userBean.getCompany().equals("")) {
            mCompanyLayout.setVisibility(View.GONE);
            mCompanyDividerIV.setVisibility(View.GONE);
        }
        if (userBean.getBlog() == null || userBean.getBlog().equals("")) {
            mBlogLayout.setVisibility(View.GONE);
            mBlogDividerTV.setVisibility(View.GONE);
        }
    }

    @Override
    protected void onStop() {
        if (mPresenter != null) {
            mPresenter.stop();
        }
        super.onStop();
    }

    private void setupSwpieRefreshLayout() {
        mSwipeRefreshLayout.measure(View.MEASURED_SIZE_MASK, View.MEASURED_HEIGHT_STATE_SHIFT);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorAccent);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadUserInfo();
            }
        });
    }

    private void setClickListener() {
        mFollowersLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UsersActivity.launchActivity(ProfileActivity.this, null, UsersActivity.FOLLOWERS);
            }
        });

        mFollowingLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UsersActivity.launchActivity(ProfileActivity.this, null, UsersActivity.FOLLOWING);
            }
        });

        mEventsLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventsActivity.launchActivity(ProfileActivity.this, EventsActivity.USER_EVENTS);
            }
        });

        mReposLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ReposActivity.launchActivity(ProfileActivity.this, null);
            }
        });

        mOrgsLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UsersActivity.launchActivity(ProfileActivity.this, null, UsersActivity.ORGS);
            }
        });
    }

    private void loadUserInfo() {
        mPresenter.loadUserInfo();
    }

    @Override
    public void loadInfoSuccess() {
        mSwipeRefreshLayout.setRefreshing(false);
        setUserInfo();
        setClickListener();
        if (!mRefreshView.isRefreshSuccess()) {
            mRefreshView.refreshSuccess();
        }
    }

    @Override
    public void loadInfoFail() {
        mSwipeRefreshLayout.setRefreshing(false);
        mRefreshView.refreshFail();
    }

    @Override
    public void loadInfo(AuthenticatedUserBean user) {
        userBean = user;
    }

    @Override
    public void loadOtherInfo(UserBean user) {

    }

    @Override
    public void checkFollowFail() {

    }

    @Override
    public void isFollowed() {

    }

    @Override
    public void isUnfollowed() {

    }

    @Override
    public void followFail() {

    }

    @Override
    public void followSuccess() {

    }

    @Override
    public void unfollowSuccess() {

    }

    @Override
    public void unfollowFail() {

    }

    @Override
    public String getUsername() {
        return SPUtil.getString(this, Constants.USER_INFO, Constants.USER_LOGIN, null);
    }

    @Override
    public void setPresenter(ProfileContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void initNavMenu() {
        inflaterNavMenu(R.menu.profile_right_nav_menu);
        setOnNavItemClickListener(new OnNavItemClickListener() {
            @Override
            public void onItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.profile_right_nav_share:
                        DisplayUtil.share(ProfileActivity.this,
                                "User " + getUsername(),
                                "https://github.com/" + getUsername());
                        break;
                    default:
                        break;
                }
            }
        });
    }

    @Override
    public void createMenu(Menu menu) {
        setOnMenuItemSelectedListener(new OnMenuItemSelectedListener() {
            @Override
            public void onItemSelected(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.profile_toolbar_choose:
                        openDrawer();
                        break;
                    default:
                        break;
                }
            }
        });
    }

    @Override
    public void inflateMenu(MenuInflater inflater, Menu menu) {
        inflater.inflate(R.menu.profile_toolbar_menu, menu);
    }
}
