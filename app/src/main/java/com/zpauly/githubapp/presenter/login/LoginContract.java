package com.zpauly.githubapp.presenter.login;

import com.zpauly.githubapp.base.BasePresenter;
import com.zpauly.githubapp.base.BaseView;

/**
 * Created by zpauly on 16-6-9.
 */
public class LoginContract {
    public interface Presenter extends BasePresenter {
        void login();
    }

    public interface View extends BaseView<Presenter> {
        void loginSuccess();

        void loginFail();

        void logining();

        String getAuth();
    }
}
