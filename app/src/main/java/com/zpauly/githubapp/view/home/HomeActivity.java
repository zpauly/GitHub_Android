package com.zpauly.githubapp.view.home;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.MenuItem;

import com.zpauly.githubapp.Constants;
import com.zpauly.githubapp.R;
import com.zpauly.githubapp.base.BaseFragment;
import com.zpauly.githubapp.db.UserDao;
import com.zpauly.githubapp.entity.response.AuthenticatedUser;
import com.zpauly.githubapp.listener.OnNavItemClickListener;
import com.zpauly.githubapp.presenter.home.HomeContract;
import com.zpauly.githubapp.utils.SPUtil;
import com.zpauly.githubapp.view.DrawerActivity;
import com.zpauly.githubapp.view.events.EventsFragment;
import com.zpauly.githubapp.view.gists.GistsFragment;
import com.zpauly.githubapp.view.login.LoginActivity;
import com.zpauly.githubapp.view.orgs.OrgsFragment;
import com.zpauly.githubapp.view.profile.ProfileFragment;
import com.zpauly.githubapp.view.repos.ReposFragment;

/**
 * Created by zpauly on 16-6-9.
 */
public class HomeActivity extends DrawerActivity implements HomeContract.View {
    private HomeContract.Presenter mPresenter;

    private static final int PROFILE = 0;
    private static final int EVENTS = 1;
    private static final int REPOS = 2;
    private static final int ORGS = 3;
    private static final int GISTS = 4;

    private BaseFragment mCurrentFragment;
    private BaseFragment[] fragments = {new ProfileFragment(), new EventsFragment()
            , new ReposFragment(), new OrgsFragment(), new GistsFragment()};

    private FragmentManager mFragmentManager;
    private FragmentTransaction mFragmentTransaction;

    @Override
    protected void onStop() {
        mPresenter.stop();
        super.onStop();
    }

    @Override
    public void initViews() {
        setListener();
        mFragmentManager = getSupportFragmentManager();
        mFragmentTransaction = mFragmentManager.beginTransaction();
        mCurrentFragment = fragments[0];
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
                        } else {
                            mCurrentFragment = fragments[PROFILE];
                            changeFragment();
                        }
                        break;
                    case R.id.navigation_events:
                        if (mCurrentFragment instanceof EventsFragment) {
                        } else {
                            mCurrentFragment = fragments[EVENTS];
                            changeFragment();
                        }
                        break;
                    case R.id.navigation_repos:
                        if (mCurrentFragment instanceof ReposFragment) {
                        } else {
                            mCurrentFragment = fragments[REPOS];
                            changeFragment();
                        }
                        break;
                    case R.id.navigation_orgs:
                        if (mCurrentFragment instanceof OrgsFragment) {
                        } else {
                            mCurrentFragment = fragments[ORGS];
                            changeFragment();
                        }
                        break;
                    case R.id.navigation_gists:
                        if (mCurrentFragment instanceof GistsFragment) {
                        } else {
                            mCurrentFragment = fragments[GISTS];
                            changeFragment();
                        }
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

    private void changeFragment() {
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

    @Override
    public void setPresenter(HomeContract.Presenter presenter) {
        this.mPresenter = presenter;
    }

    @Override
    public void loadInfoSuccess() {

    }

    @Override
    public void loadInfoFail() {

    }

    @Override
    public void loadInfo(AuthenticatedUser user) {

    }
}
