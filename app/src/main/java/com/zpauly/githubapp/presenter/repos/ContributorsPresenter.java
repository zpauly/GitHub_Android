package com.zpauly.githubapp.presenter.repos;

import android.content.Context;

import com.zpauly.githubapp.base.NetPresenter;
import com.zpauly.githubapp.entity.response.repos.ContributorBean;
import com.zpauly.githubapp.network.repositories.RepositoriesMethod;

import java.util.List;

import rx.Subscriber;

/**
 * Created by zpauly on 16/9/25.
 */

public class ContributorsPresenter extends NetPresenter implements ContributorsContract.Presenter {
    private final String TAG = getClass().getName();

    private Context mContext;

    private ContributorsContract.View mContributorsView;

    private String auth;
    private RepositoriesMethod repositoriesMethod;

    private Subscriber<List<ContributorBean>> contributorsSubscriber;

    private int pageId = 1;

    public ContributorsPresenter(Context context, ContributorsContract.View view) {
        this.mContext = context;
        this.mContributorsView = view;
        mContributorsView.setPresenter(this);
        start();
    }

    @Override
    public void getContributors() {
        contributorsSubscriber = new Subscriber<List<ContributorBean>>() {
            @Override
            public void onCompleted() {
                mContributorsView.getContributorsSuccess();
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
                mContributorsView.getContributorsFail();
            }

            @Override
            public void onNext(List<ContributorBean> contributorBeen) {
                mContributorsView.gettingContributors(contributorBeen);
            }
        };
        repositoriesMethod.getContributors(contributorsSubscriber, auth,
                mContributorsView.getOwner(), mContributorsView.getRepo(), pageId++);
    }

    @Override
    public void setPageId(int pageId) {
        this.pageId = pageId;
    }

    @Override
    public void start() {
        auth = getAuth(mContext);
        repositoriesMethod = getMethod(RepositoriesMethod.class);
    }

    @Override
    public void stop() {
        unsubscribe(contributorsSubscriber);
    }
}
