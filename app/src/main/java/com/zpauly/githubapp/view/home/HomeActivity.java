package com.zpauly.githubapp.view.home;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.MenuItem;

import com.zpauly.githubapp.Constants;
import com.zpauly.githubapp.R;
import com.zpauly.githubapp.base.BaseFragment;
import com.zpauly.githubapp.db.UserDao;
import com.zpauly.githubapp.listener.OnNavItemClickListener;
import com.zpauly.githubapp.utils.SPUtil;
import com.zpauly.githubapp.view.DrawerActivity;
import com.zpauly.githubapp.view.login.LoginActivity;
import com.zpauly.githubapp.view.profile.ProfileFragment;
import com.zpauly.githubapp.view.stars.StarsFragment;

/**
 * Created by zpauly on 16-6-9.
 */
public class HomeActivity extends DrawerActivity {
    private static final int PROFILE = 0;
    private static final int STARS = 1;

    private int currentFragmentID = PROFILE;

    private BaseFragment mCurrentFragment;
    private BaseFragment[] fragments = {new ProfileFragment(), new StarsFragment()};

    private FragmentManager mFragmentManager;
    private FragmentTransaction mFragmentTransaction;

    @Override
    public void initViews() {
        setListener();
        mFragmentManager = getSupportFragmentManager();
        mFragmentTransaction = mFragmentManager.beginTransaction();
        mCurrentFragment = fragments[currentFragmentID];
        mFragmentTransaction.replace(R.id.nav_content, mCurrentFragment);
        mFragmentTransaction.commit();
    }

    private void setListener() {
        setOnNavItemClickListener(new OnNavItemClickListener() {
            @Override
            public void onItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_profile:
                        if (currentFragmentID == PROFILE) {
                        } else {
                            item.setChecked(true);
                            currentFragmentID = PROFILE;
                            mCurrentFragment = fragments[currentFragmentID];
                            changeFragment();
                            setToolbarTitle(R.string.profile);
                        }
                        break;
                    case R.id.navigation_stars:
                        if (currentFragmentID == STARS) {

                        } else {
                            item.setChecked(true);
                            currentFragmentID = STARS;
                            mCurrentFragment = fragments[currentFragmentID];
                            changeFragment();
                            setToolbarTitle(R.string.starred);
                        }
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

    private void changeFragment() {
        mFragmentTransaction = mFragmentManager.beginTransaction();
        mFragmentTransaction.replace(R.id.nav_content, mCurrentFragment);
        mFragmentTransaction.commit();
    }

    @Override
    protected void setToolbar() {
        super.setToolbar();
        setToolbarTitle(R.string.profile);
    }

    private void exit() {
        SPUtil.clearAllField(this, Constants.USER_INFO);
        UserDao.deleteUser();
        Intent intent = new Intent();
        intent.setClass(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}
