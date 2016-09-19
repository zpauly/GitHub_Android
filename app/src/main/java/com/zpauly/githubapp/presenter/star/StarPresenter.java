package com.zpauly.githubapp.presenter.star;

import android.content.Context;

import com.zpauly.githubapp.base.NetPresenter;
import com.zpauly.githubapp.entity.response.repos.RepositoriesBean;
import com.zpauly.githubapp.network.activity.ActivityMethod;

import java.util.List;

import rx.Subscriber;

/**
 * Created by zpauly on 16-7-17.
 */

public class StarPresenter extends NetPresenter implements StarContract.Presenter {
    private Context mContext;
    private StarContract.View mStarView;

    private String auth;
    private ActivityMethod activityMethod;

    private Subscriber<List<RepositoriesBean>> starredRepositoriesSubscriber;

    private int pageId = 1;
    private boolean loading = false;
    private String username;

    public void setPageId(int pageId) {
        this.pageId = pageId;
    }

    public StarPresenter(Context context, StarContract.View view) {
        mContext = context;
        mStarView = view;
        mStarView.setPresenter(this);
        start();
    }

    @Override
    public void start() {
        username = mStarView.getUsername();
        auth = getAuth(mContext);
        activityMethod = getMethod(ActivityMethod.class);
    }

    @Override
    public void stop() {
        unsubscribe(starredRepositoriesSubscriber);
    }

    @Override
    public void getUserStarredRepos() {
        starredRepositoriesSubscriber = new Subscriber<List<RepositoriesBean>>() {
            @Override
            public void onCompleted() {
                mStarView.loadSuccess();
                pageId ++;
                loading = false;
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
                mStarView.loadFail();
                loading = false;
            }

            @Override
            public void onNext(List<RepositoriesBean> starredRepositories) {
                 mStarView.loading(starredRepositories);
            }
        };
        if (username != null && !loading) {
            loading = true;
            activityMethod.getOthersStarredRepositories(starredRepositoriesSubscriber, username, pageId);
        } else {
            loading = true;
            activityMethod.getStarredRepositories(starredRepositoriesSubscriber, auth, mStarView.getSort(), mStarView.getDirection(), pageId);
        }
    }
}
