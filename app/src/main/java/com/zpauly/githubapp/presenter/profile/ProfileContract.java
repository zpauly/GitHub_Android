package com.zpauly.githubapp.presenter.profile;

import com.zpauly.githubapp.base.BasePresenter;
import com.zpauly.githubapp.base.BaseView;
import com.zpauly.githubapp.entity.response.AuthenticatedUserBean;
import com.zpauly.githubapp.entity.response.UserBean;

/**
 * Created by zpauly on 16-6-10.
 */
public class ProfileContract {
    public interface Presenter extends BasePresenter {
        void loadUserInfo();

        void loadOtherInfo();

        void checkUserFollowed();

        void follow();

        void unfollow();
    }

    public interface View extends BaseView<Presenter> {
        void loadInfoSuccess();

        void loadInfoFail();

        void loadInfo(AuthenticatedUserBean user);

        void loadOtherInfo(UserBean user);

        void checkFollowFail();

        void isFollowed();

        void isUnfollowed();

        void followFail();

        void followSuccess();

        void unfollowSuccess();

        void unfollowFail();

        String getUsername();
    }
}
