package com.zpauly.githubapp.presenter.repos;

import android.content.Context;

import com.zpauly.githubapp.Constants;
import com.zpauly.githubapp.entity.response.RepositoriesBean;
import com.zpauly.githubapp.network.repositories.RepositoriesMethod;
import com.zpauly.githubapp.network.repositories.RepositoriesService;
import com.zpauly.githubapp.utils.SPUtil;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;

/**
 * Created by zpauly on 16-7-18.
 */

public class ReposPresenter implements ReposContract.Presenter {
    private Context mContext;
    private ReposContract.View mReposView;

    private RepositoriesMethod mReposMethod;
    private String auth;

    private Subscriber<List<RepositoriesBean>> mReposSubscriber;

    public ReposPresenter(Context context, ReposContract.View view) {
        mContext = context;
        mReposView = view;
        mReposView.setPresenter(this);

        start();
    }

    @Override
    public void start() {
        auth = SPUtil.getString(mContext, Constants.USER_INFO, Constants.USER_AUTH, null);
        mReposMethod = RepositoriesMethod.getInstance();
    }

    @Override
    public void stop() {
        if (mReposSubscriber != null) {
            if (mReposSubscriber.isUnsubscribed()) {
                mReposSubscriber.unsubscribe();
            }
        }
    }

    @Override
    public void loadUserRepositories() {
        mReposSubscriber = new Subscriber<List<RepositoriesBean>>() {
            @Override
            public void onCompleted() {
                mReposView.loadSuccess();
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
                mReposView.loadFail();
            }

            @Override
            public void onNext(List<RepositoriesBean> repositoriesBeen) {
                mReposView.loadingRepos(repositoriesBeen);
            }
        };
        List<String> affiliation = new ArrayList<>();
        affiliation.add(RepositoriesService.AFFILIATION_OWNER);
        mReposMethod.getOwendRespositories(mReposSubscriber, auth, affiliation, RepositoriesService.SORT_ALL);
    }
}
