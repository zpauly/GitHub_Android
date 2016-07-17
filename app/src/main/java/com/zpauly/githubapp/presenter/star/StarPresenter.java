package com.zpauly.githubapp.presenter.star;

import android.content.Context;

import com.zpauly.githubapp.Constants;
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

    private Subscriber<List<StarredRepositories>> starredRepositoriesSubscriber;

    public StarPresenter(Context context, StarContract.View view) {
        mContext = context;
        mStarView = view;
        mStarView.setPresenter(this);
    }

    @Override
    public void start() {
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
        starredRepositoriesSubscriber = new Subscriber<List<StarredRepositories>>() {
            @Override
            public void onCompleted() {
                mStarView.loadSuccess();
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
                mStarView.loadFail();
            }

            @Override
            public void onNext(List<StarredRepositories> starredRepositories) {
                 mStarView.loading(starredRepositories);
            }
        };
        activityMethod.getStarredRepositories(starredRepositoriesSubscriber, auth, null, null);
    }
}
