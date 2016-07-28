package com.zpauly.githubapp.presenter.profile;

import android.content.Context;

import com.zpauly.githubapp.Constants;
import com.zpauly.githubapp.entity.response.AuthenticatedUserBean;
import com.zpauly.githubapp.entity.response.UserBean;
import com.zpauly.githubapp.network.user.UserMethod;
import com.zpauly.githubapp.utils.SPUtil;

import rx.Subscriber;

/**
 * Created by zpauly on 16-6-10.
 */
public class ProfilePresenter implements ProfileContract.Presenter {
    private ProfileContract.View mHomeView;
    private Context mContext;

    private UserMethod method;
    private String auth;

    private Subscriber<AuthenticatedUserBean> authenticatedUserSubscriber;
    private Subscriber<UserBean> userSubscriber;

    public ProfilePresenter(ProfileContract.View view, Context context) {
        mHomeView = view;
        mContext = context;
        mHomeView.setPresenter(this);
        start();
    }

    @Override
    public void start() {
        method = UserMethod.getInstance();
        auth = SPUtil.getString(mContext, Constants.USER_INFO, Constants.USER_AUTH, null);
    }

    @Override
    public void stop() {
        if (authenticatedUserSubscriber != null) {
            if (authenticatedUserSubscriber.isUnsubscribed()) {
                authenticatedUserSubscriber.unsubscribe();
            }
        }
        if (userSubscriber != null) {
            if (userSubscriber.isUnsubscribed()) {
                userSubscriber.unsubscribe();
            }
        }
    }

    @Override
    public void loadUserInfo() {
        authenticatedUserSubscriber = new Subscriber<AuthenticatedUserBean>() {
            @Override
            public void onCompleted() {
                mHomeView.loadInfoSuccess();
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
                mHomeView.loadInfoFail();
            }

            @Override
            public void onNext(AuthenticatedUserBean authenticatedUser) {
                mHomeView.loadInfo(authenticatedUser);
            }
        };
        method.getAuthenticatedUser(authenticatedUserSubscriber, auth);
    }

    @Override
    public void loadOtherInfo() {
        userSubscriber = new Subscriber<UserBean>() {
            @Override
            public void onCompleted() {
                mHomeView.loadInfoSuccess();
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
                mHomeView.loadInfoFail();
            }

            @Override
            public void onNext(UserBean bean) {
                mHomeView.loadOtherInfo(bean);
            }
        };
        method.getUser(userSubscriber, mHomeView.getUsername());
    }
}
