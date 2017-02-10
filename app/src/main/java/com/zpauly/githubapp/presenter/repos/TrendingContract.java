package com.zpauly.githubapp.presenter.repos;

import com.zpauly.githubapp.base.BasePresenter;
import com.zpauly.githubapp.base.BaseView;
import com.zpauly.githubapp.entity.response.repos.RepositoriesBean;

import java.util.List;

/**
 * Created by zpauly on 2017/2/10.
 */

public class TrendingContract {
    public interface Presenter extends BasePresenter {
        void getTrendingRepos();
    }

    public interface View extends BaseView<Presenter> {
        String getLang();

        String getSince();

        void gettingTrendingRepos(List<RepositoriesBean> been);

        void getTrendingReposSuccess();

        void getTrendingReposFail();
    }
}
