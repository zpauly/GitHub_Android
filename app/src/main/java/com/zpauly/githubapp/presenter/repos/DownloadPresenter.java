package com.zpauly.githubapp.presenter.repos;

import android.content.Context;
import android.os.Environment;

import com.zpauly.githubapp.base.NetPresenter;
import com.zpauly.githubapp.network.repositories.RepositoriesMethod;

import java.io.File;
import java.io.IOException;

import okhttp3.ResponseBody;
import okio.BufferedSink;
import okio.Okio;
import retrofit2.Response;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;

/**
 * Created by zpauly on 16/9/27.
 */

public class DownloadPresenter extends NetPresenter implements DownloadContract.Presenter {
    private final String TAG = getClass().getName();

    private Context mContext;
    private DownloadContract.View mDownloadView;

    private String auth;
    private RepositoriesMethod repositoriesMethod;

    private Subscriber<File> downloadSusbcriber;
    private Func1<Response<ResponseBody>, Observable<File>> downFunc1;

    public DownloadPresenter(Context context, DownloadContract.View view) {
        this.mContext = context;
        this.mDownloadView = view;
        mDownloadView.setPresenter(this);
        start();
    }

    @Override
    public void downloadRepo() {
        downloadSusbcriber = new Subscriber<File>() {
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
            public void onNext(File file) {
                mDownloadView.downloading(file);
            }
        };
        downFunc1 = new Func1<Response<ResponseBody>, Observable<File>>() {
            @Override
            public Observable<File> call(final
                                         Response<ResponseBody> responseBodyResponse) {
                return Observable.create(new Observable.OnSubscribe<File>() {
                    @Override
                    public void call(Subscriber<? super File> subscriber) {
                        mDownloadView.flatMap(responseBodyResponse, subscriber);
                    }
                });
            }
        };
        repositoriesMethod.getArchiveLink(downloadSusbcriber, downFunc1,
                auth, mDownloadView.getOwner(),
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
