package com.zpauly.githubapp.presenter.follow;

import android.content.Context;
import android.util.Log;

import com.zpauly.githubapp.Constants;
import com.zpauly.githubapp.base.NetPresenter;
import com.zpauly.githubapp.entity.response.OrganizationBean;
import com.zpauly.githubapp.entity.response.UserBean;
import com.zpauly.githubapp.network.organizations.OrganizationsMethod;
import com.zpauly.githubapp.network.user.UserMethod;
import com.zpauly.githubapp.utils.SPUtil;

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

    private Subscriber<List<UserBean>> mFollowersSubscriber;
    private Subscriber<List<UserBean>> mFollowingSubscriber;
    private Subscriber<List<OrganizationBean>> mOrgsSubscriber;

    private int loadPageId = 1;
    private boolean loading = false;

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
    }

    @Override
    public void stop() {
        unsubscribe(mFollowersSubscriber, mFollowingSubscriber, mOrgsSubscriber);
    }

    @Override
    public void getFollowers() {
        mFollowersSubscriber = new Subscriber<List<UserBean>>() {
            @Override
            public void onCompleted() {
                mFollowView.loadSuccess();
                loadPageId ++;
                loading = false;
            }

            @Override
            public void onError(Throwable e) {
                mFollowView.loadFail();
                e.printStackTrace();
                loading = false;
            }

            @Override
            public void onNext(List<UserBean> followersBean) {
                mFollowView.loading(followersBean);
            }
        };
        if (mFollowView.getUsername() != null && !loading) {
            loading = true;
            method.getUserFollowers(mFollowersSubscriber, auth, mFollowView.getUsername(), loadPageId);
        } else {
            loading = true;
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
                loading = false;
            }

            @Override
            public void onError(Throwable e) {
                mFollowView.loadFail();
                e.printStackTrace();
                loading = false;
            }

            @Override
            public void onNext(List<UserBean> followersBean) {
                mFollowView.loading(followersBean);
            }
        };
        if (mFollowView.getUsername() != null && !loading) {
            loading = true;
            method.getUserFollowing(mFollowingSubscriber, auth, mFollowView.getUsername(), loadPageId);
        } else {
            loading = true;
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
        if (!loading) {
            if (mFollowView.getUsername() == null) {
                Log.i(TAG, "user orgs");
                orgMethod.getUserOrgs(mOrgsSubscriber, auth);
            } else {
                Log.i(TAG, "others orgs");
                orgMethod.getUserOrgs(mOrgsSubscriber, auth, mFollowView.getUsername());
            }
        }
    }
}
