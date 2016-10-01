package com.zpauly.githubapp.presenter.repos;

import com.zpauly.githubapp.base.BasePresenter;
import com.zpauly.githubapp.base.BaseView;

import java.io.File;

import okhttp3.ResponseBody;
import retrofit2.Response;
import rx.Subscriber;

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

        void downloading(File file);

        void flatMap(Response<ResponseBody> responseBodyResponse,
                     Subscriber<? super File> subscriber);

        String getRepo();

        String getOwner();

        String getArchiveFormat();

        String getRef();
    }
}
