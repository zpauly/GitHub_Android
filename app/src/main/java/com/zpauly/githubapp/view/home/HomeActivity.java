package com.zpauly.githubapp.view.home;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.MenuItem;

import com.zpauly.githubapp.R;
import com.zpauly.githubapp.listener.OnNavItemClickListener;
import com.zpauly.githubapp.view.DrawerActivity;
import com.zpauly.githubapp.view.events.EventsFragment;
import com.zpauly.githubapp.view.profile.ProfileFragment;

/**
 * Created by zpauly on 16-6-9.
 */
public class HomeActivity extends DrawerActivity {
    private Fragment mCurrentFragment;

    private FragmentManager mFragmentManager;
    private FragmentTransaction mFragmentTransaction;

    @Override
    public void initViews() {
        setListener();
        mFragmentManager = getSupportFragmentManager();
        mFragmentTransaction = mFragmentManager.beginTransaction();
        mCurrentFragment = new ProfileFragment();
        mFragmentTransaction.replace(R.id.nav_content, mCurrentFragment);
        mFragmentTransaction.commit();
    }

    private void setListener() {
        setOnNavItemClickListener(new OnNavItemClickListener() {
            @Override
            public void onItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_profile:
                        if (mCurrentFragment instanceof ProfileFragment) {
                            break;
                        } else {
                            mCurrentFragment = new ProfileFragment();
                            changeFragment();
                        }
                        break;
                    case R.id.navigation_events:
                        if (mCurrentFragment instanceof EventsFragment) {
                            break;
                        } else {
                            mCurrentFragment = new EventsFragment();
                            changeFragment();
                        }
                        break;
                    case R.id.navigation_repos:
                        changeFragment();
                        break;
                    case R.id.navigation_orgs:
                        changeFragment();
                        break;
                    case R.id.navigation_gists:
                        changeFragment();
                        break;
                    default:
                        break;
                }
            }
        });
    }

    private void changeFragment() {
        mFragmentTransaction.replace(R.id.nav_content, mCurrentFragment);
        mFragmentTransaction.commit();
    }

    @Override
    protected void setToolbar() {
        super.setToolbar();
        setToolbarTitle(R.string.profile);
    }
}
