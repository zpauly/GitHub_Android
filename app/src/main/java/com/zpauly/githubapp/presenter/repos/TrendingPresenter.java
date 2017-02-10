package com.zpauly.githubapp.presenter.repos;

import android.content.Context;

import com.zpauly.githubapp.base.NetPresenter;
import com.zpauly.githubapp.entity.response.repos.RepositoriesBean;
import com.zpauly.githubapp.network.repositories.RepositoriesMethod;

import java.util.List;

import rx.Subscriber;

/**
 * Created by zpauly on 2017/2/10.
 */

public class TrendingPresenter extends NetPresenter implements TrendingContract.Presenter {
    private Context mContext;
    private TrendingContract.View mTrendingView;

    private RepositoriesMethod mRepoMethod;

    private Subscriber<List<RepositoriesBean>> mTrendingSubscriber;

    public TrendingPresenter(Context context, TrendingContract.View view) {
        mContext = context;
        mTrendingView = view;
        mTrendingView.setPresenter(this);

        start();
    }

    @Override
    public void getTrendingRepos() {
        mTrendingSubscriber = new Subscriber<List<RepositoriesBean>>() {
            @Override
            public void onCompleted() {
                mTrendingView.getTrendingReposSuccess();
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
                mTrendingView.getTrendingReposFail();
            }

            @Override
            public void onNext(List<RepositoriesBean> been) {
                mTrendingView.gettingTrendingRepos(been);
            }
        };
        mRepoMethod.getTrendingRepos(mTrendingSubscriber, mTrendingView.getLang(), mTrendingView.getSince());
    }

    @Override
    public void start() {
        mRepoMethod = getMethod(RepositoriesMethod.class);
    }

    @Override
    public void stop() {
        unsubscribe(mTrendingSubscriber);
    }
}
