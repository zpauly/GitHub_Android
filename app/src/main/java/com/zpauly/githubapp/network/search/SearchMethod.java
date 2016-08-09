package com.zpauly.githubapp.network.search;

import com.zpauly.githubapp.Api;
import com.zpauly.githubapp.entity.response.SearchReposBean;
import com.zpauly.githubapp.utils.RetrofitUtil;

import java.util.List;

import retrofit2.Retrofit;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by zpauly on 16-8-9.
 */

public class SearchMethod {
    private static SearchMethod instance;

    private Retrofit retrofit;

    private SearchService service;

    private SearchMethod() {
        retrofit = RetrofitUtil.initRetrofit(Api.GitHubApi);
        service = retrofit.create(SearchService.class);
    }

    private static SearchMethod getInstance() {
        if (instance == null) {
            synchronized (SearchMethod.class) {
                if (instance == null) {
                    instance = new SearchMethod();
                }
            }
        }
        return instance;
    }

    public void getSearchRepos(Observer<List<SearchReposBean>> observer, String auth,
                               String sort, String order) {
        service.getSearchRepos(auth, sort, order)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }
}
