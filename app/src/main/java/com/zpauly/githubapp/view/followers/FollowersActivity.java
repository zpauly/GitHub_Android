package com.zpauly.githubapp.view.followers;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

import com.zpauly.githubapp.R;
import com.zpauly.githubapp.view.ToolbarActivity;
import com.zpauly.githubapp.view.profile.OthersActivity;

/**
 * Created by zpauly on 16-6-15.
 */
public class FollowersActivity extends ToolbarActivity {
    private final String TAG = getClass().getName();

    public static final String FOLLOW_ID = "FOLLOW_ID";
    public static final int FOLLOWERS = 0;
    public static final int FOLLOWING = 1;

    private int followId;

    private AppBarLayout mABLayout;

    private FragmentManager mFragmentManager;
    private FragmentTransaction mFragmentTransaction;

    @Override
    public void initViews() {
        followId = getIntent().getIntExtra(FOLLOW_ID, -1);

        mABLayout = (AppBarLayout) findViewById(R.id.followers_ABLayout);
        setFragment();
    }

    private void setFragment() {
        mFragmentManager = getSupportFragmentManager();
        mFragmentTransaction = mFragmentManager.beginTransaction();
        Bundle bundle = new Bundle();
        bundle.putInt(FOLLOW_ID, followId);
        bundle.putString(OthersActivity.USERNAME, username);
        Fragment fragment = new FollowersFragment();
        fragment.setArguments(bundle);
        mFragmentTransaction.replace(R.id.followers_content, fragment);
        mFragmentTransaction.commit();
    }

    @Override
    public void initContent() {
        setContentView(R.layout.activity_followers);
    }

    @Override
    protected void setToolbar() {
        super.setToolbar();
        if (followId == FOLLOWERS) {
            setToolbarTitle(R.string.followers);
        } else if (followId == FOLLOWING) {
            setToolbarTitle(R.string.following);
        }
        setOnToolbarNavClickedListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}
