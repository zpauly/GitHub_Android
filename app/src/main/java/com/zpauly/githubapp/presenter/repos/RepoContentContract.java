package com.zpauly.githubapp.presenter.repos;

import com.zpauly.githubapp.base.BasePresenter;
import com.zpauly.githubapp.base.BaseView;

/**
 * Created by zpauly on 16-7-31.
 */

public class RepoContentContract {
    public interface Presenter extends BasePresenter {
        void loadReadMe();
    }

    public interface View extends BaseView<Presenter> {
        void loadFail();

        void loadSuccess();
    }
}
