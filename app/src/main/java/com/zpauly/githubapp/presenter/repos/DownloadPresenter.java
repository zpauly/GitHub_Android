package com.zpauly.githubapp.presenter.repos;

import android.content.Context;

import com.zpauly.githubapp.base.NetPresenter;
import com.zpauly.githubapp.network.repositories.RepositoriesMethod;

import okhttp3.ResponseBody;
import rx.Subscriber;

/**
 * Created by zpauly on 16/9/27.
 */

public class DownloadPresenter extends NetPresenter implements DownloadContract.Presenter {
    private final String TAG = getClass().getName();

    private Context mContext;
    private DownloadContract.View mDownloadView;

    private String auth;
    private RepositoriesMethod repositoriesMethod;

    private Subscriber<ResponseBody> downloadSusbcriber;

    public DownloadPresenter(Context context, DownloadContract.View view) {
        this.mContext = context;
        this.mDownloadView = view;
        mDownloadView.setPresenter(this);
        start();
    }

    @Override
    public void downloadRepo() {
        downloadSusbcriber = new Subscriber<ResponseBody>() {
            @Override
            public void onCompleted() {
                mDownloadView.downloadRepoSuccess();
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
                mDownloadView.downloadRepoFail();
            }

            @Override
            public void onNext(ResponseBody responseBody) {
                mDownloadView.downloading(responseBody);
            }
        };
        repositoriesMethod.getArchiveLink(downloadSusbcriber, auth, mDownloadView.getOwner(),
                mDownloadView.getRepo(), mDownloadView.getArchiveFormat(), mDownloadView.getRef());
    }

    @Override
    public void start() {
        auth = getAuth(mContext);
        repositoriesMethod = getMethod(RepositoriesMethod.class);
    }

    @Override
    public void stop() {
        unsubscribe(downloadSusbcriber);
    }
}
