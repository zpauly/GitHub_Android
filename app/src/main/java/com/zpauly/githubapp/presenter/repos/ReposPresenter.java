package com.zpauly.githubapp.presenter.repos;

import android.content.Context;

import com.zpauly.githubapp.base.NetPresenter;
import com.zpauly.githubapp.entity.response.repos.RepositoriesBean;
import com.zpauly.githubapp.network.repositories.RepositoriesMethod;
import com.zpauly.githubapp.network.repositories.RepositoriesService;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;

/**
 * Created by zpauly on 16-7-18.
 */

public class ReposPresenter extends NetPresenter implements ReposContract.Presenter {
    private Context mContext;
    private ReposContract.View mReposView;

    private RepositoriesMethod mReposMethod;
    private String auth;

    private int pageId = 1;
    private boolean flag = false;
    private Subscriber<List<RepositoriesBean>> mReposSubscriber;

    public ReposPresenter(Context context, ReposContract.View view) {
        mContext = context;
        mReposView = view;
        mReposView.setPresenter(this);

        start();
    }

    @Override
    public void start() {
        auth = getAuth(mContext);
        mReposMethod = getMethod(RepositoriesMethod.class);
    }

    @Override
    public void stop() {
        unsubscribe(mReposSubscriber);
    }

    @Override
    public void loadUserRepositories() {
        mReposSubscriber = new Subscriber<List<RepositoriesBean>>() {
            @Override
            public void onCompleted() {
                pageId++;
                if (flag) {
                    mReposView.loadSuccess();
                } else {
                    loadUserRepositories();
                }
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
                mReposView.loadFail();
            }

            @Override
            public void onNext(List<RepositoriesBean> repositoriesBeen) {
                mReposView.loadingRepos(repositoriesBeen);
                if (repositoriesBeen == null || repositoriesBeen.size() == 0) {
                    flag = true;
                }
            }
        };
//        List<String> affiliation = new ArrayList<>();
//        affiliation.add(RepositoriesService.AFFILIATION_OWNER);
        if (mReposView.getUsername() != null) {
            mReposMethod.getRepositories(mReposSubscriber, auth, mReposView.getUsername(), mReposView.getType(), mReposView.getSort(), mReposView.getDirection(), pageId);
        } else {
            mReposMethod.getOwendRepositories(mReposSubscriber, auth, null, null, mReposView
                    .getType(), mReposView.getSort(), mReposView.getDirection(), pageId);
        }
    }

    @Override
    public void setPageId(int pageId) {
        this.pageId = pageId;
    }
}
