package com.zpauly.githubapp.presenter.follow;

import com.zpauly.githubapp.base.BasePresenter;
import com.zpauly.githubapp.base.BaseView;
import com.zpauly.githubapp.entity.response.FollowersBean;
import com.zpauly.githubapp.entity.response.OrganizationBean;

import java.util.List;

/**
 * Created by zpauly on 16-7-25.
 */

public class FollowContract {
    public interface Presenter extends BasePresenter {
        void getFollowers();

        void getFollowing();

        void getOrgs();
    }

    public interface View extends BaseView<Presenter> {
        void loading(List<FollowersBean> list);

        void loadingOrgs(List<OrganizationBean> list);

        void loadOrgsSuccess();

        void loadFail();

        void loadSuccess();

        String getUsername();
    }
}
