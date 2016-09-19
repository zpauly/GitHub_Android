package com.zpauly.githubapp.presenter.star;

import com.zpauly.githubapp.base.BasePresenter;
import com.zpauly.githubapp.base.BaseView;
import com.zpauly.githubapp.entity.response.repos.RepositoriesBean;

import java.util.List;

/**
 * Created by zpauly on 16-7-17.
 */

public class StarContract {
    public interface Presenter extends BasePresenter {
        void getUserStarredRepos();
    }

    public interface View extends BaseView<Presenter> {
        void loading(List<RepositoriesBean> starredRepositories);

        void loadFail();

        void loadSuccess();

        String getUsername();

        String getSort();

        String getDirection();
    }
}
