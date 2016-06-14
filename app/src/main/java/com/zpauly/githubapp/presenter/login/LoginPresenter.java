package com.zpauly.githubapp.presenter.login;

import com.zpauly.githubapp.Api;
import com.zpauly.githubapp.entity.request.AuthorizationRequest;
import com.zpauly.githubapp.entity.response.AppAuthorization;
import com.zpauly.githubapp.network.overview.OverviewMethod;
import com.zpauly.githubapp.presenter.login.LoginContract.Presenter;

import java.util.ArrayList;

import rx.Subscriber;

/**
 * Created by zpauly on 16-6-9.
 */
public class LoginPresenter implements Presenter {
    private final String TAG = getClass().getName();

    private LoginContract.View mLoginView;

    private Subscriber<AppAuthorization> mLoginSubscriber;

    private OverviewMethod method;

    public LoginPresenter(LoginContract.View view) {
        mLoginView = view;
        mLoginView.setPresenter(this);
    }

    @Override
    public void start() {
        method = OverviewMethod.getInstance();
    }

    @Override
    public void stop() {
        if (mLoginSubscriber != null) {
            if (mLoginSubscriber.isUnsubscribed()) {
                mLoginSubscriber.unsubscribe();
            }
        }
    }

    @Override
    public void login() {
        mLoginSubscriber = new Subscriber<AppAuthorization>() {
            @Override
            public void onCompleted() {
                mLoginView.loginSuccess();
            }

            @Override
            public void onError(Throwable e) {
                mLoginView.loginFail();
                e.printStackTrace();
            }

            @Override
            public void onNext(AppAuthorization appAuthorization) {
                mLoginView.logining();
            }
        };
        String auth = mLoginView.getAuth();
        AuthorizationRequest request = new AuthorizationRequest();
        request.setClient_secret(Api.CLIENT_SERECT);
        ArrayList<String> scopes = new ArrayList<>();
        for (String str : Api.SCPOES) {
            scopes.add(str);
        }
        request.setScopes(scopes);
        request.setNote(Api.NOTE);
        method.getOrCreateAuthorization(mLoginSubscriber, auth, request);
    }
}
