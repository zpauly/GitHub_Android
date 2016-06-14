package com.zpauly.githubapp.presenter.profile;

import android.content.Context;

import com.zpauly.githubapp.Constants;
import com.zpauly.githubapp.entity.response.AuthenticatedUser;
import com.zpauly.githubapp.network.user.UserMethod;
import com.zpauly.githubapp.utils.AuthUtil;
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

    private Subscriber<AuthenticatedUser> authenticatedUserSubscriber;

    public ProfilePresenter(ProfileContract.View view, Context context) {
        mHomeView = view;
        mContext = context;
        mHomeView.setPresenter(this);
    }

    @Override
    public void start() {
        method = UserMethod.getInstance();
        String username = SPUtil.getString(mContext, Constants.USER_INFO, Constants.USER_USERNAME, null);
        String password = SPUtil.getString(mContext, Constants.USER_INFO, Constants.USER_PASSWORD, null);
        auth = AuthUtil.generateAuth(username, password);
    }

    @Override
    public void stop() {
        if (authenticatedUserSubscriber != null) {
            if (authenticatedUserSubscriber.isUnsubscribed()) {
                authenticatedUserSubscriber.unsubscribe();
            }
        }
    }

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
}
