package com.zpauly.githubapp.presenter.explore;

import com.zpauly.githubapp.base.BasePresenter;
import com.zpauly.githubapp.base.BaseView;
import com.zpauly.githubapp.entity.response.SearchReposBean;

/**
 * Created by zpauly on 16-8-10.
 */

public class ExploreContract {
    public interface Presenter extends BasePresenter {
        void getSearchRepos();
    }

    public interface View extends BaseView<Presenter> {
        String getSort();

        String getOrder();

        void searchingRepos(SearchReposBean bean);

        void searchSuccess();

        void searchFail();
    }
}
