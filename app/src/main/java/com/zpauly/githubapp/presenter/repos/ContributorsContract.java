package com.zpauly.githubapp.presenter.repos;

import com.zpauly.githubapp.base.BasePresenter;
import com.zpauly.githubapp.base.BaseView;
import com.zpauly.githubapp.entity.response.repos.ContributorBean;

import java.util.List;

/**
 * Created by zpauly on 16/9/25.
 */

public class ContributorsContract {
    public interface Presenter extends BasePresenter {
        void getContributors();

        void setPageId(int pageId);
    }

    public interface View extends BaseView<Presenter> {
        void getContributorsSuccess();

        void getContributorsFail();

        void gettingContributors(List<ContributorBean> contributorBeen);

        String getOwner();

        String getRepo();
    }
}
