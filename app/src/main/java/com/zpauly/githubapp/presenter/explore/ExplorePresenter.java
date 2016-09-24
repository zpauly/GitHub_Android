package com.zpauly.githubapp.presenter.explore;

import android.content.Context;

import com.zpauly.githubapp.base.NetPresenter;
import com.zpauly.githubapp.entity.response.search.SearchCodeBean;
import com.zpauly.githubapp.entity.response.search.SearchReposBean;
import com.zpauly.githubapp.entity.response.search.SearchUsersBean;
import com.zpauly.githubapp.network.search.SearchMethod;

import rx.Subscriber;

/**
 * Created by zpauly on 16-8-10.
 */

public class ExplorePresenter extends NetPresenter implements ExploreContract.Presenter {
    private final String TAG = getClass().getName();

    private Context mContext;
    private ExploreContract.View mExploreView;

    private String auth;
    private SearchMethod method;

    private Subscriber<SearchReposBean> searchReposSubscriber;
    private Subscriber<SearchCodeBean> searchCodeSubscriber;
    private Subscriber<SearchUsersBean> searchUsersSubscriber;

    private int pageId = 1;

    public ExplorePresenter(Context context, ExploreContract.View view) {
        this.mContext = context;
        this.mExploreView = view;
        mExploreView.setPresenter(this);
        start();
    }

    @Override
    public void start() {
        auth = getAuth(mContext);
        method = getMethod(SearchMethod.class);
    }

    @Override
    public void stop() {
        unsubscribe(searchCodeSubscriber, searchReposSubscriber, searchUsersSubscriber);
    }

    @Override
    public void getSearchRepos() {
        searchReposSubscriber = new Subscriber<SearchReposBean>() {
            @Override
            public void onCompleted() {
                mExploreView.searchSuccess();
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
                mExploreView.searchFail();
            }

            @Override
            public void onNext(SearchReposBean searchReposBean) {
                mExploreView.searchingRepos(searchReposBean);
            }
        };
        method.getSearchRepos(searchReposSubscriber, auth, mExploreView.getQuery(),
                mExploreView.getSort(), mExploreView.getOrder(), pageId++);
    }

    @Override
    public void getSearchCode() {
        searchCodeSubscriber = new Subscriber<SearchCodeBean>() {
            @Override
            public void onCompleted() {
                mExploreView.searchSuccess();
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
                mExploreView.searchFail();
            }

            @Override
            public void onNext(SearchCodeBean searchCodeBean) {
                mExploreView.searchingCode(searchCodeBean);
            }
        };
        method.getSearchCode(searchCodeSubscriber, auth, mExploreView.getQuery(),
                mExploreView.getSort(), mExploreView.getOrder(), pageId++);
    }

    @Override
    public void getSearchUsers() {
        searchUsersSubscriber = new Subscriber<SearchUsersBean>() {
            @Override
            public void onCompleted() {
                mExploreView.searchSuccess();
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
                mExploreView.searchFail();
            }

            @Override
            public void onNext(SearchUsersBean searchUsersBean) {
                mExploreView.searchingUsers(searchUsersBean);
            }
        };
        method.getSearchUsers(searchUsersSubscriber, auth, mExploreView.getQuery(),
                mExploreView.getSort(), mExploreView.getOrder(), pageId++);
    }

    @Override
    public void setPageId(int pageId) {
        this.pageId = pageId;
    }
}
