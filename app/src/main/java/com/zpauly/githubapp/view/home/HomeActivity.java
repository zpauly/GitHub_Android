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
import com.zpauly.githubapp.db.UserDao;
import com.zpauly.githubapp.listener.OnNavHeaderAvatarClickListener;
import com.zpauly.githubapp.listener.OnNavItemClickListener;
import com.zpauly.githubapp.utils.SPUtil;
import com.zpauly.githubapp.view.DrawerActivity;
import com.zpauly.githubapp.view.events.EventsActivity;
import com.zpauly.githubapp.view.events.EventsFragment;
import com.zpauly.githubapp.view.login.LoginActivity;
import com.zpauly.githubapp.view.profile.ProfileActivity;
import com.zpauly.githubapp.view.stars.StarsFragment;

/**
 * Created by zpauly on 16-6-9.
 */
public class HomeActivity extends DrawerActivity {
    private final String TAG = getClass().getName();

    private long lastPressTime = System.currentTimeMillis();

    private static final int EVENTS = 0;
    private static final int STARS = 1;

    private int currentFragmentID = EVENTS;

    private BaseFragment mCurrentFragment;
    private BaseFragment[] fragments;

    private FragmentManager mFragmentManager;
    private FragmentTransaction mFragmentTransaction;

    @Override
    public void initViews() {
        fragments = new BaseFragment[]{ new EventsFragment(), new StarsFragment()};
        Bundle eventsBundle = new Bundle();
        eventsBundle.putInt(EventsActivity.EVENTS_ID, EventsActivity.RECEIVED_EVENTS);
        fragments[0].setArguments(eventsBundle);

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
                Intent intent = new Intent();
                intent.setClass(HomeActivity.this, ProfileActivity.class);
                startActivity(intent);
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
                            item.setChecked(true);
                            changeFragment(STARS);
                            setToolbarTitle(R.string.starred);
                        }
                        break;
                    case R.id.navigation_events:
                        if (currentFragmentID == EVENTS) {
                        } else {
                            item.setChecked(true);
                            changeFragment(EVENTS);
                            setToolbarTitle(R.string.events);
                        }
                        break;
                    case R.id.navigation_settings:
                        break;
                    case R.id.navigation_exit:
                        item.setChecked(true);
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
        UserDao.deleteUser();
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
