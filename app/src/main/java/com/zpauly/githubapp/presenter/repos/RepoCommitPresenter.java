package com.zpauly.githubapp.presenter.repos;

import android.content.Context;

import com.zpauly.githubapp.base.NetPresenter;
import com.zpauly.githubapp.entity.response.repos.SingleCommitBean;
import com.zpauly.githubapp.network.repositories.RepositoriesMethod;

import java.util.List;

import rx.Subscriber;

/**
 * Created by zpauly on 16/9/20.
 */

public class RepoCommitPresenter extends NetPresenter implements RepoCommitContract.Presenter {
    private final String TAG = getClass().getName();

    private Context mContext;

    private RepoCommitContract.View mCommitView;

    private String auth;
    private RepositoriesMethod repositoriesMethod;

    private Subscriber<List<SingleCommitBean>> commitSubscriber;

    private int pageId = 1;

    public RepoCommitPresenter(Context context, RepoCommitContract.View view) {
        mContext = context;
        mCommitView = view;
        mCommitView.setPresenter(this);
        start();
    }

    @Override
    public void start() {
        auth = getAuth(mContext);
        repositoriesMethod = getMethod(RepositoriesMethod.class);
    }

    @Override
    public void stop() {
        unsubscribe(commitSubscriber);
    }

    @Override
    public void getCommits() {
        commitSubscriber = new Subscriber<List<SingleCommitBean>>() {
            @Override
            public void onCompleted() {
                mCommitView.getCommitsSuccess();
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
                mCommitView.getCommitsFail();
            }

            @Override
            public void onNext(List<SingleCommitBean> singleCommitBeen) {
                mCommitView.gettingCommits(singleCommitBeen);
            }
        };
        repositoriesMethod.getRepositoryCommit(commitSubscriber, auth,
                mCommitView.getOwner(), mCommitView.getRepo(), pageId++);
    }

    @Override
    public void setPageId(int pageId) {
        this.pageId = pageId;
    }
}
