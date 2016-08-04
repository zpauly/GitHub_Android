package com.zpauly.githubapp.view.users;

import android.content.Context;
import android.content.Intent;
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
public class UsersActivity extends ToolbarActivity {
    private final String TAG = getClass().getName();

    public static final String USERS_ID = "USERS_ID";
    public static final int FOLLOWERS = 0;
    public static final int FOLLOWING = 1;
    public static final int ORGS = 2;

    private int userId;

    private AppBarLayout mABLayout;

    private FragmentManager mFragmentManager;
    private FragmentTransaction mFragmentTransaction;

    @Override
    public void initViews() {
        userId = getIntent().getIntExtra(USERS_ID, -1);

        mABLayout = (AppBarLayout) findViewById(R.id.followers_ABLayout);
        setFragment();
    }

    private void setFragment() {
        mFragmentManager = getSupportFragmentManager();
        mFragmentTransaction = mFragmentManager.beginTransaction();
        Bundle bundle = new Bundle();
        bundle.putInt(USERS_ID, userId);
        bundle.putString(OthersActivity.USERNAME, username);
        Fragment fragment = new UsersFragment();
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
        if (userId == FOLLOWERS) {
            setToolbarTitle(R.string.followers);
        } else if (userId == FOLLOWING) {
            setToolbarTitle(R.string.following);
        } else if (userId == ORGS) {
            setToolbarTitle(R.string.orgs);
        }
        setOnToolbarNavClickedListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    public static void launchActivity(Context context, String username, int userId) {
        Intent intent = new Intent();
        intent.putExtra(OthersActivity.USERNAME, username);
        intent.putExtra(USERS_ID, userId);
        intent.setClass(context, UsersActivity.class);
        context.startActivity(intent);
    }
}
