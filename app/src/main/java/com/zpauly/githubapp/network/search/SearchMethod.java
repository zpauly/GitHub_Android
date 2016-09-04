package com.zpauly.githubapp.network.search;

import com.zpauly.githubapp.Api;
import com.zpauly.githubapp.base.BaseNetMethod;
import com.zpauly.githubapp.entity.search.SearchCodeBean;
import com.zpauly.githubapp.entity.search.SearchReposBean;
import com.zpauly.githubapp.entity.search.SearchUsersBean;
import com.zpauly.githubapp.utils.RetrofitUtil;

import retrofit2.Retrofit;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by zpauly on 16-8-9.
 */

public class SearchMethod extends BaseNetMethod {
    private Retrofit retrofit;

    private SearchService service;

    private static class Nested {
        static SearchMethod instance = new SearchMethod();
    }

    private SearchMethod() {
        retrofit = RetrofitUtil.initRetrofit(Api.GitHubApi);
        service = retrofit.create(SearchService.class);
    }

    public static SearchMethod getInstance() {
        return Nested.instance;
    }

    public void getSearchRepos(Observer<SearchReposBean> observer, String auth, String query,
                               String sort, String order) {
        service.getSearchRepos(auth, query, sort, order)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    public void getSearchCode(Observer<SearchCodeBean> observer, String auth, String query,
                              String sort, String order) {
        service.getSearchCode(auth, query, sort, order)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    public void getSearchUsers(Observer<SearchUsersBean> observer, String auth, String query,
                               String sort, String order) {
        service.getSearchUsers(auth, query, sort, order)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }
}
