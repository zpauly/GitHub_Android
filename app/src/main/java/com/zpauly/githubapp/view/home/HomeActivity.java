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
import com.zpauly.githubapp.presenter.home.HomePresenter;
import com.zpauly.githubapp.utils.SPUtil;
import com.zpauly.githubapp.view.DrawerActivity;
import com.zpauly.githubapp.view.login.LoginActivity;
import com.zpauly.githubapp.view.profile.ProfileFragment;
import com.zpauly.githubapp.view.stars.StarsFragment;

/**
 * Created by zpauly on 16-6-9.
 */
public class HomeActivity extends DrawerActivity implements HomeContract.View {
    private HomeContract.Presenter mPresenter;

    private static final int PROFILE = 0;
    private static final int STARS = 1;

    private int currentFragmentID = PROFILE;

    private BaseFragment mCurrentFragment;
    private BaseFragment[] fragments = {new ProfileFragment(), new StarsFragment()};

    private FragmentManager mFragmentManager;
    private FragmentTransaction mFragmentTransaction;

    @Override
    protected void onStop() {
        mPresenter.stop();
        super.onStop();
    }

    @Override
    public void initViews() {
        new HomePresenter(this, this);
        mPresenter.start();

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
                            currentFragmentID = PROFILE;
                            mCurrentFragment = fragments[currentFragmentID];
                            changeFragment();
                        }
                        break;
                    case R.id.navigation_stars:
                        if (currentFragmentID == STARS) {

                        } else {
                            currentFragmentID = STARS;
                            mCurrentFragment = fragments[currentFragmentID];
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
