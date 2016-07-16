package com.zpauly.githubapp.presenter.home;

import android.content.Context;

import com.zpauly.githubapp.Constants;
import com.zpauly.githubapp.entity.response.AuthenticatedUser;
import com.zpauly.githubapp.network.user.UserMethod;
import com.zpauly.githubapp.utils.SPUtil;

import rx.Subscriber;

/**
 * Created by zpauly on 16-7-11.
 */

public class HomePresenter implements HomeContract.Presenter {
    private final String TAG = getClass().getName();

    private Context mContext;
    private HomeContract.View mHomeView;

    public HomePresenter(Context context, HomeContract.View view) {
        mContext = context;
        mHomeView = view;
        mHomeView.setPresenter(this);
    }

    private String auth;
    private UserMethod method;

    private Subscriber<AuthenticatedUser> authenticatedUserSubscriber;

    @Override
    public void loadUserInfo() {
        authenticatedUserSubscriber = new Subscriber<AuthenticatedUser>() {
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
            public void onNext(AuthenticatedUser authenticatedUser) {
                mHomeView.loadInfo(authenticatedUser);
            }
        };
        method.getAuthenticatedUser(authenticatedUserSubscriber, auth);
    }

    @Override
    public void start() {
        auth = SPUtil.getString(mContext, Constants.USER_INFO, Constants.USER_AUTH, null);
        method = UserMethod.getInstance();
    }

    @Override
    public void stop() {
        if (authenticatedUserSubscriber != null) {
            if (authenticatedUserSubscriber.isUnsubscribed()) {
                authenticatedUserSubscriber.unsubscribe();
            }
        }
    }
}
