package com.zpauly.githubapp.presenter.follow;

import android.content.Context;

import com.zpauly.githubapp.Constants;
import com.zpauly.githubapp.entity.response.FollowersBean;
import com.zpauly.githubapp.network.user.UserMethod;
import com.zpauly.githubapp.utils.SPUtil;

import java.util.List;

import rx.Subscriber;

/**
 * Created by zpauly on 16-7-25.
 */

public class FollowPresenter implements FollowContract.Presenter {
    private Context mContext;
    private FollowContract.View mFollowView;

    private String auth;
    private UserMethod method;

    private Subscriber<List<FollowersBean>> mFollowersSubscriber;
    private Subscriber<List<FollowersBean>> mFollowingSubscriber;

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
        auth = SPUtil.getString(mContext, Constants.USER_INFO, Constants.USER_AUTH, null);
        method = UserMethod.getInstance();
    }

    @Override
    public void stop() {
        if (mFollowersSubscriber != null) {
            if (mFollowersSubscriber.isUnsubscribed()) {
                mFollowersSubscriber.unsubscribe();
            }
        }
        if (mFollowingSubscriber != null) {
            if (mFollowingSubscriber.isUnsubscribed()) {
                mFollowingSubscriber.unsubscribe();
            }
        }
    }

    @Override
    public void getFollowers() {
        mFollowersSubscriber = new Subscriber<List<FollowersBean>>() {
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
            public void onNext(List<FollowersBean> followersBean) {
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
        mFollowingSubscriber = new Subscriber<List<FollowersBean>>() {
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
            public void onNext(List<FollowersBean> followersBean) {
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
}
