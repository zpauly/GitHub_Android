package com.zpauly.githubapp.view.profile;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.AppBarLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.zpauly.githubapp.R;
import com.zpauly.githubapp.entity.response.AuthenticatedUserBean;
import com.zpauly.githubapp.entity.response.UserBean;
import com.zpauly.githubapp.presenter.profile.ProfileContract;
import com.zpauly.githubapp.presenter.profile.ProfilePresenter;
import com.zpauly.githubapp.utils.TextUtil;
import com.zpauly.githubapp.view.ToolbarActivity;
import com.zpauly.githubapp.view.events.EventsActivity;
import com.zpauly.githubapp.view.users.UsersActivity;
import com.zpauly.githubapp.view.repositories.ReposActivity;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by zpauly on 16-7-27.
 */

public class OthersActivity extends ToolbarActivity implements ProfileContract.View {
    private final String TAG = getClass().getName();

    private ProfileContract.Presenter mPresenter;

    public static final String USERNAME = "USERNAME";
    private String username;

    private AppBarLayout mABLayout;
    private SwipeRefreshLayout mSWLayout;
    private CircleImageView mAvatarIV;
    private TextView mLoginTV;
    private TextView mNameTV;
    private TextView mBioTV;
    private TextView mLocationTV;
    private TextView mEmailTV;
    private TextView mTimeTV;
    private TextView mFollowersTV;
    private TextView mFollowingTV;
    private LinearLayout mFollowersLayout;
    private LinearLayout mFollowingLayout;
    private RelativeLayout mEventsLayout;
    private RelativeLayout mReposLayout;
    private RelativeLayout mOrgsLayout;
    private RelativeLayout mGistsLayout;

    private UserBean user;

    @Override
    protected void onStop() {
        mPresenter.stop();
        super.onStop();
    }

    @Override
    public void initViews() {
        username = getIntent().getStringExtra(USERNAME);

        new ProfilePresenter(this, this);

        mABLayout = (AppBarLayout) findViewById(R.id.others_ABLayout);
        mSWLayout = (SwipeRefreshLayout) findViewById(R.id.others_SWLayout);
        mAvatarIV = (CircleImageView) findViewById(R.id.profile_avatar);
        mLoginTV = (TextView) findViewById(R.id.profile_login_TV);
        mLoginTV.setText("");
        mNameTV = (TextView) findViewById(R.id.profile_name_TV);
        mNameTV.setText("");
        mBioTV = (TextView) findViewById(R.id.profile_name_TV);
        mBioTV.setText("");
        mLocationTV = (TextView) findViewById(R.id.profile_location_TV);
        mLocationTV.setText("");
        mEmailTV = (TextView) findViewById(R.id.profile_email_TV);
        mEmailTV.setText("");
        mTimeTV = (TextView) findViewById(R.id.profile_join_time_TV);
        mTimeTV.setText("");
        mFollowersTV = (TextView) findViewById(R.id.profile_followers_TV);
        mFollowingTV = (TextView) findViewById(R.id.profile_following_TV);
        mFollowersLayout = (LinearLayout) findViewById(R.id.profile_followers_layout);
        mFollowingLayout = (LinearLayout) findViewById(R.id.profile_following_layout);
        mEventsLayout = (RelativeLayout) findViewById(R.id.profile_events_layout);
        mReposLayout = (RelativeLayout) findViewById(R.id.profile_repos_layout);
        mOrgsLayout = (RelativeLayout) findViewById(R.id.profile_orgs_layout);
        mGistsLayout = (RelativeLayout) findViewById(R.id.profile_gists_layout);

        setupSwipeRefreshLayout();

        mSWLayout.setRefreshing(true);
        loadData();
    }

    @Override
    protected void setToolbar() {
        super.setToolbar();
        if (username != null) {
            setToolbarTitle(username);
        } else {
            setToolbarTitle(userInfo.getLogin());
        }
        setOnToolbarNavClickedListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    @Override
    public void initContent() {
        setContentView(R.layout.activity_others);
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
                Intent intent = new Intent();
                intent.setClass(OthersActivity.this, ReposActivity.class);
                intent.putExtra(USERNAME, username);
                startActivity(intent);
            }
        });

        mOrgsLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UsersActivity.launchActivity(OthersActivity.this, username, UsersActivity.ORGS);
            }
        });

        mGistsLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private void loadData() {
        mPresenter.loadOtherInfo();
    }

    @Override
    public void loadInfoSuccess() {
        if (user != null) {
            Glide.with(this)
                    .load(Uri.parse(user.getAvatar_url()))
                    .centerCrop()
                    .crossFade()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(mAvatarIV);
            mLoginTV.setText(user.getLogin());
            mNameTV.setText(user.getName());
            mBioTV.setText(user.getBio());
            mLocationTV.setText(user.getLocation());
            mEmailTV.setText(user.getEmail());
            mTimeTV.setText(TextUtil.timeConverter(user.getCreated_at()));
            mFollowersTV.setText(String.valueOf(user.getFollowers()));
            mFollowingTV.setText(String.valueOf(user.getFollowing()));
        }
        mSWLayout.setRefreshing(false);
        setupListener();
    }

    public static void lanuchActivity(Context context, String username) {
        Intent intent = new Intent();
        intent.putExtra(USERNAME, username);
        intent.setClass(context, OthersActivity.class);
        context.startActivity(intent);
    }

    @Override
    public void loadInfoFail() {
        mSWLayout.setRefreshing(false);
    }

    @Override
    public void loadInfo(AuthenticatedUserBean user) {

    }

    @Override
    public void loadOtherInfo(UserBean user) {
        this.user = user;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public void setPresenter(ProfileContract.Presenter presenter) {
        this.mPresenter = presenter;
    }
}
