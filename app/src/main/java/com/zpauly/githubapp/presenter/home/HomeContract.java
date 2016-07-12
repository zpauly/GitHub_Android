package com.zpauly.githubapp.presenter.home;

import com.zpauly.githubapp.base.BasePresenter;
import com.zpauly.githubapp.base.BaseView;
import com.zpauly.githubapp.entity.response.AuthenticatedUser;

/**
 * Created by root on 16-7-11.
 */

public class HomeContract {
    public interface Presenter extends BasePresenter {
        void loadUserInfo();
    }

    public interface View extends BaseView<Presenter> {
        void loadInfoSuccess();

        void loadInfoFail();

        void loadInfo(AuthenticatedUser user);
    }
}
