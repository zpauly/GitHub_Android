package com.zpauly.githubapp.presenter.repos;

import com.zpauly.githubapp.base.BasePresenter;
import com.zpauly.githubapp.base.BaseView;
import com.zpauly.githubapp.entity.response.repos.RepositoriesBean;

import java.util.List;

/**
 * Created by zpauly on 16-7-18.
 */

public class ReposContract {
    public interface Presenter extends BasePresenter {
        void loadUserRepositories();
    }

    public interface View extends BaseView<Presenter> {
        void loadingRepos(List<RepositoriesBean> list);

        void loadFail();

        void loadSuccess();

        String getUsername();
    }
}
