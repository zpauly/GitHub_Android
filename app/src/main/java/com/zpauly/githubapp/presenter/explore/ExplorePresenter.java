package com.zpauly.githubapp.presenter.explore;

import android.content.Context;

import com.zpauly.githubapp.Constants;
import com.zpauly.githubapp.entity.search.SearchCodeBean;
import com.zpauly.githubapp.entity.search.SearchReposBean;
import com.zpauly.githubapp.entity.search.SearchUsersBean;
import com.zpauly.githubapp.network.search.SearchMethod;
import com.zpauly.githubapp.utils.SPUtil;

import rx.Subscriber;

/**
 * Created by zpauly on 16-8-10.
 */

public class ExplorePresenter implements ExploreContract.Presenter {
    private final String TAG = getClass().getName();

    private Context mContext;
    private ExploreContract.View mExploreView;

    private String auth;
    private SearchMethod method;

    private Subscriber<SearchReposBean> searchReposSubscriber;
    private Subscriber<SearchCodeBean> searchCodeSubscriber;
    private Subscriber<SearchUsersBean> searchUsersSubscriber;

    public ExplorePresenter(Context context, ExploreContract.View view) {
        this.mContext = context;
        this.mExploreView = view;
        mExploreView.setPresenter(this);
        start();
    }

    @Override
    public void start() {
        auth = SPUtil.getString(mContext, Constants.USER_INFO, Constants.USER_AUTH, null);
        method = SearchMethod.getInstance();
    }

    @Override
    public void stop() {
        if (searchReposSubscriber != null) {
            if (searchReposSubscriber.isUnsubscribed()) {
                searchReposSubscriber.unsubscribe();
            }
        }
        if (searchCodeSubscriber != null) {
            if (searchCodeSubscriber.isUnsubscribed()) {
                searchCodeSubscriber.unsubscribe();
            }
        }
        if (searchUsersSubscriber != null) {
            if (searchUsersSubscriber.isUnsubscribed()) {
                searchUsersSubscriber.unsubscribe();
            }
        }
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
                mExploreView.getSort(), mExploreView.getOrder());
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
                mExploreView.getSort(), mExploreView.getOrder());
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
                mExploreView.getSort(), mExploreView.getOrder());
    }
}
