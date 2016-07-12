package com.zpauly.githubapp.view.profile;

import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zpauly.githubapp.R;
import com.zpauly.githubapp.base.BaseFragment;
import com.zpauly.githubapp.db.UserDao;
import com.zpauly.githubapp.entity.response.AuthenticatedUser;
import com.zpauly.githubapp.presenter.profile.ProfileContract;
import com.zpauly.githubapp.presenter.profile.ProfilePresenter;
import com.zpauly.githubapp.view.home.HomeFragment;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by zpauly on 16-6-10.
 */
public class ProfileFragment extends BaseFragment implements ProfileContract.View{
    private final String TAG = getClass().getName();
    private ProfileContract.Presenter mPresenter;

    private View mView;

    private SwipeRefreshLayout mSwipeRefreshLayout;

    private CircleImageView mAvatarIV;
    private TextView mLoginTV;
    private TextView mNameTV;
    private TextView mBioTV;
    private TextView mLocationTV;
    private TextView mEmailTV;
    private TextView mJoinTimeTV;
    private TextView mFollowersTV;
    private TextView mFollowingTV;
    private RelativeLayout mEventsLayout;
    private RelativeLayout mReposLayout;
    private RelativeLayout mOrgsLayout;
    private RelativeLayout mGistsLayout;

    @Override
    protected void initViews() {
        new ProfilePresenter(this, getContext());
        mPresenter.start();

        mSwipeRefreshLayout = (SwipeRefreshLayout) mView.findViewById(R.id.profile_SRLayout);

        mAvatarIV = (CircleImageView) mView.findViewById(R.id.profile_avatar);
        mLoginTV = (TextView) mView.findViewById(R.id.profile_login_TV);
        mNameTV = (TextView) mView.findViewById(R.id.profile_name_TV);
        mBioTV = (TextView) mView.findViewById(R.id.profile_bio_TV);
        mLocationTV = (TextView) mView.findViewById(R.id.profile_location_TV);
        mEmailTV = (TextView) mView.findViewById(R.id.profile_email_TV);
        mJoinTimeTV = (TextView) mView.findViewById(R.id.profile_join_time_TV);
        mFollowersTV = (TextView) mView.findViewById(R.id.profile_followers_TV);
        mFollowingTV = (TextView) mView.findViewById(R.id.profile_following_TV);
        mEventsLayout = (RelativeLayout) mView.findViewById(R.id.profile_events_layout);
        mReposLayout = (RelativeLayout) mView.findViewById(R.id.profile_repos_layout);
        mOrgsLayout = (RelativeLayout) mView.findViewById(R.id.profile_orgs_layout);
        mGistsLayout = (RelativeLayout) mView.findViewById(R.id.profile_gists_layout);

        setUserInfo();

        setupSwpieRefreshLayout();

        setClickListener();
    }

    private void setUserInfo() {
        if (userInfo == null)
            return;
        Glide.with(this)
                .load(Uri.parse(userInfo.getAvatar_url()))
                .centerCrop()
                .crossFade()
                .into(mAvatarIV);
        mFollowersTV.setText(String.valueOf(userInfo.getFollowers()));
        mFollowingTV.setText(String.valueOf(userInfo.getFollowing()));
        mLoginTV.setText(userInfo.getLogin());
        mNameTV.setText(userInfo.getName());
        mBioTV.setText(userInfo.getBio());
        mLocationTV.setText(userInfo.getLocation());
        mEmailTV.setText(userInfo.getEmail());
        mJoinTimeTV.setText(userInfo.getCreated_at());
    }

    @Override
    public void onStop() {
        mPresenter.stop();
        super.onStop();
    }

    @Override
    protected void setContentView(LayoutInflater inflater, @Nullable ViewGroup container) {
        mView = inflater.inflate(R.layout.fragment_profile, container);
    }

    private void setupSwpieRefreshLayout() {
        mSwipeRefreshLayout.measure(View.MEASURED_SIZE_MASK, View.MEASURED_HEIGHT_STATE_SHIFT);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadUserInfo();
            }
        });
    }

    private void setClickListener() {
        mEventsLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        mReposLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        mOrgsLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        mGistsLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private void loadUserInfo() {
        mPresenter.loadUserInfo();
    }

    @Override
    public void loadInfoSuccess() {
        mSwipeRefreshLayout.setRefreshing(false);
        Log.i(TAG, "load success");
        if (UserDao.queryUser() == null) {
            Log.i(TAG, "data save fail");
        } else {
            userInfo = UserDao.queryUser();
            setUserInfo();
        }
    }

    @Override
    public void loadInfoFail() {
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void loadInfo(AuthenticatedUser user) {
        UserDao.deleteUser();
        UserDao.insertUser(user);
    }

    @Override
    public void setPresenter(ProfileContract.Presenter presenter) {
        mPresenter = presenter;
    }
}
