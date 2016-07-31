package com.zpauly.githubapp.presenter.repos;

import android.content.Context;

import com.zpauly.githubapp.entity.response.RepositoriesBean;
import com.zpauly.githubapp.entity.response.RepositoryContentBean;
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

    private Subscriber<List<RepositoryContentBean>> repoContentSubscriber;

    public RepoContentPresenter(Context context, RepoContentContract.View view) {
        mContext = context;
        mRepoContentView = view;
        mRepoContentView.setPresenter(this);
        start();
    }

    @Override
    public void start() {

    }

    @Override
    public void stop() {

    }

    @Override
    public void loadReadMe() {

    }
}
