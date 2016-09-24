package com.zpauly.githubapp.presenter.repos;

import com.zpauly.githubapp.base.BasePresenter;
import com.zpauly.githubapp.base.BaseView;
import com.zpauly.githubapp.entity.response.repos.ReleaseBean;

import java.util.List;

/**
 * Created by zpauly on 16/9/24.
 */

public class ReleasesContract {
    public interface Presenter extends BasePresenter {
        void getReleases();

        void setPageId(int pageId);
    }

    public interface View extends BaseView<Presenter> {
        void getReleasesSuccess();

        void getReleasesFail();

        void gettingReleases(List<ReleaseBean> releaseBeen);

        String getOwner();

        String getRepo();
    }
}
