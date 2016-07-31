package com.zpauly.githubapp.presenter.repos;

import com.zpauly.githubapp.base.BasePresenter;
import com.zpauly.githubapp.base.BaseView;
import com.zpauly.githubapp.entity.response.RepositoriesBean;
import com.zpauly.githubapp.entity.response.RepositoryContentBean;

import java.util.List;

/**
 * Created by zpauly on 16-7-31.
 */

public class RepoContentContract {
    public interface Presenter extends BasePresenter {
        void loadReadMe();

        void loadRepo();
    }

    public interface View extends BaseView<Presenter> {
        void loadReadMeFail();

        void loadReadMeSuccess();

        void loadingReadMe(String string);

        void loadRepoSuccess();

        void loadRepoFail();

        void loadingRepo(RepositoriesBean bean);

        String getUsername();

        String getRepoName();
    }
}
