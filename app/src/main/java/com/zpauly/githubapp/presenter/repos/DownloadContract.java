package com.zpauly.githubapp.presenter.repos;

import com.zpauly.githubapp.base.BasePresenter;
import com.zpauly.githubapp.base.BaseView;

import okhttp3.ResponseBody;

/**
 * Created by zpauly on 16/9/27.
 */

public class DownloadContract {
    public interface Presenter extends BasePresenter {
        void downloadRepo();
    }

    public interface View extends BaseView<Presenter> {
        void downloadRepoSuccess();

        void downloadRepoFail();

        void downloading(ResponseBody responseBody);

        String getRepo();

        String getOwner();

        String getArchiveFormat();

        String getRef();
    }
}
