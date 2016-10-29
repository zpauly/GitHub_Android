package com.zpauly.githubapp.presenter.follow;

import android.content.Context;
import android.util.Log;

import com.zpauly.githubapp.base.NetPresenter;
import com.zpauly.githubapp.entity.response.OrganizationBean;
import com.zpauly.githubapp.entity.response.UserBean;
import com.zpauly.githubapp.network.activity.ActivityMethod;
import com.zpauly.githubapp.network.organizations.OrganizationsMethod;
import com.zpauly.githubapp.network.user.UserMethod;

import java.util.List;

import rx.Subscriber;

/**
 * Created by zpauly on 16-7-25.
 */

public class FollowPresenter extends NetPresenter implements FollowContract.Presenter {
    private final String TAG = getClass().getName();

    private Context mContext;
    private FollowContract.View mFollowView;

    private String auth;
    private UserMethod method;
    private OrganizationsMethod orgMethod;
    private ActivityMethod activityMethod;

    private Subscriber<List<UserBean>> mFollowersSubscriber;
    private Subscriber<List<UserBean>> mFollowingSubscriber;
    private Subscriber<List<UserBean>> mWatchersSubscriber;
    private Subscriber<List<UserBean>> mStargazersSubscriber;
    private Subscriber<List<OrganizationBean>> mOrgsSubscriber;

    private int loadPageId = 1;

    public FollowPresenter(Context context, FollowContract.View view) {
        mContext = context;
        mFollowView = view;
        mFollowView.setPresenter(this);
        start();
    }

    @Override
    public void start() {
        auth = getAuth(mContext);
        method = getMethod(UserMethod.class);
        orgMethod = getMethod(OrganizationsMethod.class);
        activityMethod = getMethod(ActivityMethod.class);
    }

    @Override
    public void stop() {
        unsubscribe(mFollowersSubscriber, mFollowingSubscriber, mOrgsSubscriber, mWatchersSubscriber, mStargazersSubscriber);
    }

    @Override
    public void getFollowers() {
        mFollowersSubscriber = new Subscriber<List<UserBean>>() {
            @Override
            public void onCompleted() {
                mFollowView.loadSuccess();
                loadPageId ++;
            }

            @Override
            public void onError(Throwable e) {
                mFollowView.loadFail();
                e.printStackTrace();
            }

            @Override
            public void onNext(List<UserBean> followersBean) {
                mFollowView.loading(followersBean);
            }
        };
        if (mFollowView.getUsername() != null) {
            method.getUserFollowers(mFollowersSubscriber, auth, mFollowView.getUsername(), loadPageId);
        } else {
            method.getFollowers(mFollowersSubscriber, auth, loadPageId);
        }
    }

    @Override
    public void getFollowing() {
        mFollowingSubscriber = new Subscriber<List<UserBean>>() {
            @Override
            public void onCompleted() {
                mFollowView.loadSuccess();
                loadPageId ++;
            }

            @Override
            public void onError(Throwable e) {
                mFollowView.loadFail();
                e.printStackTrace();
            }

            @Override
            public void onNext(List<UserBean> followersBean) {
                mFollowView.loading(followersBean);
            }
        };
        if (mFollowView.getUsername() != null) {
            method.getUserFollowing(mFollowingSubscriber, auth, mFollowView.getUsername(), loadPageId);
        } else {
            method.getFollowing(mFollowingSubscriber, auth, loadPageId);
        }
    }

    @Override
    public void getOrgs() {
        mOrgsSubscriber = new Subscriber<List<OrganizationBean>>() {
            @Override
            public void onCompleted() {
                mFollowView.loadSuccess();
            }

            @Override
            public void onError(Throwable e) {
                mFollowView.loadFail();
                e.printStackTrace();
            }

            @Override
            public void onNext(List<OrganizationBean> organizationBeen) {
                mFollowView.loadingOrgs(organizationBeen);
            }
        };
            if (mFollowView.getUsername() == null) {
                Log.i(TAG, "user orgs");
                orgMethod.getUserOrgs(mOrgsSubscriber, auth);
            } else {
                Log.i(TAG, "others orgs");
                orgMethod.getUserOrgs(mOrgsSubscriber, auth, mFollowView.getUsername());
            }
    }

    @Override
    public void getWatchers() {
        mWatchersSubscriber = new Subscriber<List<UserBean>>() {
            @Override
            public void onCompleted() {
                loadPageId++;
                mFollowView.loadSuccess();
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
                mFollowView.loadFail();
            }

            @Override
            public void onNext(List<UserBean> userBeen) {
                mFollowView.loading(userBeen);
            }
        };
        activityMethod.getWatchers(mWatchersSubscriber, auth, mFollowView.getOwner(), mFollowView.getRepo(), loadPageId);
    }

    @Override
    public void getStargazers() {
        mStargazersSubscriber = new Subscriber<List<UserBean>>() {
            @Override
            public void onCompleted() {
                mFollowView.loadSuccess();
                loadPageId++;
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
                mFollowView.loadFail();
            }

            @Override
            public void onNext(List<UserBean> userBeen) {
                mFollowView.loading(userBeen);
            }
        };
        activityMethod.getStargazers(mStargazersSubscriber, auth, mFollowView.getOwner(), mFollowView.getRepo(), loadPageId);
    }
}
