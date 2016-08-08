package com.zpauly.githubapp.presenter.gists;

import com.zpauly.githubapp.base.BasePresenter;
import com.zpauly.githubapp.base.BaseView;
import com.zpauly.githubapp.entity.response.gists.GistsBean;

import java.util.List;

/**
 * Created by zpauly on 16-8-6.
 */

public class GistsContract {
    public interface Presenter extends BasePresenter {
        void loadGists();

        void loadPublicGists();

        void loadStarredGists();
    }

    public interface View extends BaseView<Presenter> {
        void loadFail();

        void loadSuccess();

        void loadingGists(List<GistsBean> list);
    }
}
