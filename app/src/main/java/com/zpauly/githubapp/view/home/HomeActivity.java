package com.zpauly.githubapp.view.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.MenuItem;
import android.view.View;

import com.zpauly.githubapp.Constants;
import com.zpauly.githubapp.R;
import com.zpauly.githubapp.base.BaseFragment;
import com.zpauly.githubapp.listener.OnNavHeaderAvatarClickListener;
import com.zpauly.githubapp.listener.OnNavItemClickListener;
import com.zpauly.githubapp.utils.SPUtil;
import com.zpauly.githubapp.view.LeftDrawerActivity;
import com.zpauly.githubapp.view.events.EventsActivity;
import com.zpauly.githubapp.view.events.EventsFragment;
import com.zpauly.githubapp.view.explore.ExploreFragment;
import com.zpauly.githubapp.view.gists.GistsFragment;
import com.zpauly.githubapp.view.issues.IssuesOrPullRequestsActivity;
import com.zpauly.githubapp.view.issues.IssuesOrPullRequestsFragment;
import com.zpauly.githubapp.view.login.LoginActivity;
import com.zpauly.githubapp.view.profile.ProfileActivity;
import com.zpauly.githubapp.view.settings.SettingsActivity;
import com.zpauly.githubapp.view.stars.StarsFragment;

/**
 * Created by zpauly on 16-6-9.
 */
public class HomeActivity extends LeftDrawerActivity {
    private final String TAG = getClass().getName();

    private long lastPressTime = System.currentTimeMillis();

    private static final int EVENTS = 0;
    private static final int STARS = 1;
    private static final int EXPLORE = 2;
    private static final int GISTS = 3;
    private static final int All_GISTS = 4;
    private static final int STARRED_GISTS = 5;
    private static final int ISSUES = 6;

    private int currentFragmentID = EVENTS;

    private BaseFragment mCurrentFragment;
    private BaseFragment[] fragments;

    private FragmentManager mFragmentManager;
    private FragmentTransaction mFragmentTransaction;

    @Override
    public void initViews() {
        fragments = new BaseFragment[]{ new EventsFragment(), new StarsFragment()
                , new ExploreFragment(), new GistsFragment(), new GistsFragment(), new GistsFragment(), new IssuesOrPullRequestsFragment()};

        Bundle eventsBundle = new Bundle();
        eventsBundle.putInt(EventsActivity.EVENTS_ID, EventsActivity.RECEIVED_EVENTS);
        fragments[EVENTS].setArguments(eventsBundle);

        Bundle gistsBundle = new Bundle();
        gistsBundle.putInt(GistsFragment.GISTS_ID, GistsFragment.GISTS);
        fragments[GISTS].setArguments(gistsBundle);

        Bundle publicGistsBundle = new Bundle();
        publicGistsBundle.putInt(GistsFragment.GISTS_ID, GistsFragment.PUBLIC_GISTS);
        fragments[All_GISTS].setArguments(publicGistsBundle);

        Bundle starredGistsBundle = new Bundle();
        starredGistsBundle.putInt(GistsFragment.GISTS_ID, GistsFragment.STARRED_GISTS);
        fragments[STARRED_GISTS].setArguments(starredGistsBundle);

        Bundle issuesBundle = new Bundle();
        issuesBundle.putInt(IssuesOrPullRequestsActivity.DATA_TYPE, IssuesOrPullRequestsActivity.USER_ISSUES);
        fragments[ISSUES].setArguments(issuesBundle);

        setListener();
        mFragmentManager = getSupportFragmentManager();
        mFragmentTransaction = mFragmentManager.beginTransaction();
        mCurrentFragment = fragments[currentFragmentID];
        mFragmentTransaction.replace(R.id.nav_content, mCurrentFragment);
        mFragmentTransaction.commit();
    }

    private void setListener() {
        setOnNavHeaderAvatarClickListener(new OnNavHeaderAvatarClickListener() {
            @Override
            public void onClick(View v) {
                ProfileActivity.launchActivity(HomeActivity.this);
//                finish();
            }
        });
        setOnNavItemClickListener(new OnNavItemClickListener() {
            @Override
            public void onItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_stars:
                        if (currentFragmentID == STARS) {
                        } else {
                            changeFragment(STARS);
                            setToolbarTitle(R.string.starred);
                            item.setChecked(true);
                        }
                        break;
                    case R.id.navigation_events:
                        if (currentFragmentID == EVENTS) {
                        } else {
                            changeFragment(EVENTS);
                            setToolbarTitle(R.string.events);
                            item.setChecked(true);
                        }
                        break;
                    case R.id.navigation_explore:
                        if (currentFragmentID == EXPLORE) {

                        } else {
                            changeFragment(EXPLORE);
                            setToolbarTitle(R.string.explore);
                            item.setChecked(true);
                        }
                        break;
                    case R.id.navigation_gists:
                        if (currentFragmentID == GISTS) {

                        } else {
                            changeFragment(GISTS);
                            setToolbarTitle(R.string.gists);
                            item.setChecked(true);
                        }
                        break;
                    case R.id.navigation_all_gists:
                        if (currentFragmentID == All_GISTS) {

                        } else {
                            changeFragment(All_GISTS);
                            setToolbarTitle(R.string.all_gists);
                            item.setChecked(true);
                        }
                        break;
                    case R.id.navigation_starred_gists:
                        if (currentFragmentID == STARRED_GISTS) {

                        } else {
                            changeFragment(STARRED_GISTS);
                            setToolbarTitle(R.string.starred_gists);
                            item.setChecked(true);
                        }
                        break;
                    case R.id.navigation_issue:
                        if (currentFragmentID == ISSUES) {

                        } else {
                            changeFragment(ISSUES);
                            setToolbarTitle(R.string.issues);
                            item.setChecked(true);
                        }
                        break;
                    case R.id.navigation_settings:
                        SettingsActivity.launchActivity(HomeActivity.this);
                        break;
                    case R.id.navigation_exit:
                        exit();
                        break;
                    default:
                        break;
                }
            }
        });
    }

    private void changeFragment(int fragmentID) {
        currentFragmentID = fragmentID;
        mCurrentFragment = fragments[currentFragmentID];
        mFragmentTransaction = mFragmentManager.beginTransaction();
        mFragmentTransaction.replace(R.id.nav_content, mCurrentFragment);
        mFragmentTransaction.commit();
    }

    @Override
    protected void setToolbar() {
        super.setToolbar();
        setToolbarTitle(R.string.events);
    }

    private void exit() {
        SPUtil.clearAllField(this, Constants.USER_INFO);
        SPUtil.clearAllField(this, Constants.LOCAL_CONFIGURATION);
        Intent intent = new Intent();
        intent.setClass(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        if (canExit()) {
            super.onBackPressed();
        }
    }

    private boolean canExit(){
        if(System.currentTimeMillis() - lastPressTime > Constants.CLICK_EXIT_TIME){
            Snackbar.make(getCurrentFocus(), R.string.double_click_to_exit, Snackbar.LENGTH_SHORT).show();
            lastPressTime = System.currentTimeMillis();
            return false;
        } else {
            return true;
        }
    }
}
