package com.zpauly.githubapp.presenter.repos;

import android.content.Context;

import com.zpauly.githubapp.base.NetPresenter;
import com.zpauly.githubapp.entity.response.repos.ReleaseBean;
import com.zpauly.githubapp.network.repositories.RepositoriesMethod;

import java.util.List;

import rx.Subscriber;

/**
 * Created by zpauly on 16/9/24.
 */

public class ReleasesPresenter extends NetPresenter implements ReleasesContract.Presenter {
    private final String TAG = getClass().getName();

    private Context mContext;

    private ReleasesContract.View mReleasesView;

    private String auth;

    private RepositoriesMethod repositoriesMethod;

    private Subscriber<List<ReleaseBean>> releasesSubscriber;

    private int pageId = 1;

    public ReleasesPresenter(Context context, ReleasesContract.View view) {
        this.mContext = context;
        this.mReleasesView = view;
        mReleasesView.setPresenter(this);
        start();
    }

    @Override
    public void getReleases() {
        releasesSubscriber = new Subscriber<List<ReleaseBean>>() {
            @Override
            public void onCompleted() {
                pageId++;
                mReleasesView.getReleasesSuccess();
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
                mReleasesView.getReleasesFail();
            }

            @Override
            public void onNext(List<ReleaseBean> releaseBeen) {
                mReleasesView.gettingReleases(releaseBeen);
            }
        };
        repositoriesMethod.getRepositoryReleases(releasesSubscriber, auth,
                mReleasesView.getOwner(), mReleasesView.getRepo(), pageId);
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
        unsubscribe(releasesSubscriber);
    }
}
