package com.zpauly.githubapp.presenter.repos;

import android.content.Context;

import com.zpauly.githubapp.entity.response.RepositoriesBean;
import com.zpauly.githubapp.entity.response.RepositoryContentBean;
import com.zpauly.githubapp.network.repositories.RepositoriesMethod;
import com.zpauly.githubapp.presenter.repos.RepoContentContract.Presenter;

import java.util.List;

import rx.Subscriber;

/**
 * Created by zpauly on 16-7-31.
 */

public class RepoContentPresenter implements Presenter {
    private final String TAG = getClass().getName();

    private Context mContext;
    private RepoContentContract.View mRepoContentView;

    private RepositoriesMethod method;

    private Subscriber<String> repoContentSubscriber;
    private Subscriber<RepositoriesBean> repoSubscriber;

    public RepoContentPresenter(Context context, RepoContentContract.View view) {
        mContext = context;
        mRepoContentView = view;
        mRepoContentView.setPresenter(this);
        start();
    }

    @Override
    public void start() {
        method = RepositoriesMethod.getInstance();
    }

    @Override
    public void stop() {
        if (repoContentSubscriber != null) {
            if (repoContentSubscriber.isUnsubscribed()) {
                repoContentSubscriber.unsubscribe();
            }
        }
        if (repoSubscriber != null) {
            if (repoSubscriber.isUnsubscribed()) {
                repoSubscriber.unsubscribe();
            }
        }
    }

    @Override
    public void loadReadMe() {
        repoContentSubscriber = new Subscriber<String>() {
            @Override
            public void onCompleted() {
                mRepoContentView.loadReadMeSuccess();
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
                mRepoContentView.loadReadMeFail();
            }

            @Override
            public void onNext(String string) {
                mRepoContentView.loadingReadMe(string);
            }
        };
        method.getReadMe(repoContentSubscriber, mRepoContentView.getUsername(),
                mRepoContentView.getRepoName());
    }

    @Override
    public void loadRepo() {
        repoSubscriber = new Subscriber<RepositoriesBean>() {
            @Override
            public void onCompleted() {
                mRepoContentView.loadRepoSuccess();
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
                mRepoContentView.loadRepoFail();
            }

            @Override
            public void onNext(RepositoriesBean repositoriesBean) {
                mRepoContentView.loadingRepo(repositoriesBean);
            }
        };
        method.getRepository(repoSubscriber, mRepoContentView.getUsername(), mRepoContentView.getRepoName());
    }
}
