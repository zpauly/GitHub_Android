package com.zpauly.githubapp.presenter.explore;

import com.zpauly.githubapp.base.BasePresenter;
import com.zpauly.githubapp.base.BaseView;
import com.zpauly.githubapp.entity.response.search.SearchCodeBean;
import com.zpauly.githubapp.entity.response.search.SearchReposBean;
import com.zpauly.githubapp.entity.response.search.SearchUsersBean;

/**
 * Created by zpauly on 16-8-10.
 */

public class ExploreContract {
    public interface Presenter extends BasePresenter {
        void getSearchRepos();

        void getSearchCode();

        void getSearchUsers();

        void setPageId(int pageId);
    }

    public interface View extends BaseView<Presenter> {
        String getSort();

        String getOrder();

        String getQuery();

        void searchingUsers(SearchUsersBean bean);

        void searchingCode(SearchCodeBean bean);

        void searchingRepos(SearchReposBean bean);

        void searchSuccess();

        void searchFail();
    }
}
