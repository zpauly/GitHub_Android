package com.zpauly.githubapp.presenter.star;

import android.content.Context;

import com.zpauly.githubapp.Constants;
import com.zpauly.githubapp.entity.response.RepositoriesBean;
import com.zpauly.githubapp.entity.response.StarredRepositories;
import com.zpauly.githubapp.network.activity.ActivityMethod;
import com.zpauly.githubapp.utils.SPUtil;

import java.util.List;

import rx.Subscriber;

/**
 * Created by zpauly on 16-7-17.
 */

public class StarPresenter implements StarContract.Presenter {
    private Context mContext;
    private StarContract.View mStarView;

    private String auth;
    private ActivityMethod activityMethod;

    private Subscriber<List<RepositoriesBean>> starredRepositoriesSubscriber;

    private int pageId = 1;
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
        auth = SPUtil.getString(mContext, Constants.USER_INFO, Constants.USER_AUTH, null);
        activityMethod = ActivityMethod.getInstance();
    }

    @Override
    public void stop() {
        if (starredRepositoriesSubscriber != null) {
            if (starredRepositoriesSubscriber.isUnsubscribed()) {
                starredRepositoriesSubscriber.unsubscribe();
            }
        }
    }

    @Override
    public void getUserStarredRepos() {
        starredRepositoriesSubscriber = new Subscriber<List<RepositoriesBean>>() {
            @Override
            public void onCompleted() {
                mStarView.loadSuccess();
                pageId ++;
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
                mStarView.loadFail();
            }

            @Override
            public void onNext(List<RepositoriesBean> starredRepositories) {
                 mStarView.loading(starredRepositories);
            }
        };
        if (username != null) {
            activityMethod.getOthersStarredRepositories(starredRepositoriesSubscriber, username, pageId);
        } else {
            activityMethod.getStarredRepositories(starredRepositoriesSubscriber, auth, null, null, pageId);
        }
    }
}
