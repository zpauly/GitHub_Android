package com.zpauly.githubapp.presenter.repos;

import android.content.Context;
import android.util.Log;

import com.zpauly.githubapp.Constants;
import com.zpauly.githubapp.base.NetPresenter;
import com.zpauly.githubapp.entity.response.RepositoriesBean;
import com.zpauly.githubapp.entity.response.RepositoryContentBean;
import com.zpauly.githubapp.network.activity.ActivityMethod;
import com.zpauly.githubapp.network.repositories.RepositoriesMethod;
import com.zpauly.githubapp.presenter.repos.RepoContentContract.Presenter;
import com.zpauly.githubapp.utils.SPUtil;

import java.util.List;

import rx.Subscriber;

/**
 * Created by zpauly on 16-7-31.
 */

public class RepoContentPresenter extends NetPresenter implements Presenter {
    private final String TAG = getClass().getName();

    private Context mContext;
    private RepoContentContract.View mRepoContentView;

    private String auth;
    private RepositoriesMethod method;
    private ActivityMethod activityMethod;

    private Subscriber<String> repoContentSubscriber;
    private Subscriber<RepositoriesBean> repoSubscriber;
    private Subscriber<String> starSubscriber;

    public RepoContentPresenter(Context context, RepoContentContract.View view) {
        mContext = context;
        mRepoContentView = view;
        mRepoContentView.setPresenter(this);
        start();
    }

    @Override
    public void start() {
        auth = getAuth(mContext);
        method = getMethod(RepositoriesMethod.class);
        activityMethod = getMethod(ActivityMethod.class);
    }

    @Override
    public void stop() {
        unsubscribe(repoSubscriber);
        unsubscribe(repoContentSubscriber);
        unsubscribe(starSubscriber);
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
        Log.i(TAG, auth);
        method.getReadMe(repoContentSubscriber, auth, mRepoContentView.getUsername(),
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
        Log.i(TAG, auth);
        method.getRepository(repoSubscriber, auth, mRepoContentView.getUsername(), mRepoContentView.getRepoName());
    }

    @Override
    public void checkStarred() {
        starSubscriber = new Subscriber<String>() {
            @Override
            public void onCompleted() {
                Log.i(TAG, "checkSuccess");
                mRepoContentView.isStarred();
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
                if (e.toString().contains("404"))
                    mRepoContentView.isUnstarred();
                mRepoContentView.checkStarredFail();
            }

            @Override
            public void onNext(String s) {
                /*if (s.contains("204")) {
                    mRepoContentView.isStarred();
                } else if (s.contains("404")) {
                    mRepoContentView.isUnstarred();
                }*/
            }
        };
        activityMethod.isRepoStarred(starSubscriber, auth, mRepoContentView.getUsername(),
                mRepoContentView.getRepoName());
    }

    @Override
    public void starRepo() {
        starSubscriber = new Subscriber<String>() {
            @Override
            public void onCompleted() {
                mRepoContentView.starSuccess();
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
                mRepoContentView.starFail();
            }

            @Override
            public void onNext(String s) {
            }
        };
        activityMethod.starRepo(starSubscriber, auth, mRepoContentView.getUsername(),
                mRepoContentView.getRepoName());
    }

    @Override
    public void unstarRepo() {
        starSubscriber = new Subscriber<String>() {
            @Override
            public void onCompleted() {
                Log.i(TAG, "checkSuccess");
                mRepoContentView.unstarSuccess();
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
                mRepoContentView.unstarFail();
            }

            @Override
            public void onNext(String s) {

            }
        };
        activityMethod.unstarRepo(starSubscriber, auth, mRepoContentView.getUsername(),
                mRepoContentView.getRepoName());
    }
}
