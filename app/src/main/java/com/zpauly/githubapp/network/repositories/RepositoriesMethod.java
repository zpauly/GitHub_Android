package com.zpauly.githubapp.network.repositories;

import android.support.annotation.Nullable;

import com.zpauly.githubapp.Api;
import com.zpauly.githubapp.base.BaseNetMethod;
import com.zpauly.githubapp.entity.response.RepositoriesBean;
import com.zpauly.githubapp.entity.response.RepositoryContentBean;
import com.zpauly.githubapp.utils.RetrofitUtil;
import com.zpauly.githubapp.utils.StringConverterFactory;

import java.util.List;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by zpauly on 16-7-14.
 */

public class RepositoriesMethod extends BaseNetMethod {
    private Retrofit retrofit;

    private RepositoriesService service;

    private static class Nested {
        static RepositoriesMethod instance = new RepositoriesMethod();
    }

    private RepositoriesMethod() {
        retrofit = RetrofitUtil.initRetrofit(Api.GitHubApi);
        service = retrofit.create(RepositoriesService.class);
    }

    public static RepositoriesMethod getInstance() {
        return Nested.instance;
    }

    public void getOwendRepositories(Observer<List<RepositoriesBean>> observer, String auth
            , @Nullable List<String> affiliation, @Nullable String sort, int pageId) {
        service.getOwendRepositories(auth, affiliation, sort, pageId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    public void getRepositories(Observer<List<RepositoriesBean>> observer, String auth, String username,
                                @Nullable List<String> affiliation, @Nullable String sort, int pageId) {
        service.getRepositories(auth, username, affiliation, sort, pageId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    public void getRepositoryContent(Observer<List<RepositoryContentBean>> observer, String auth, String acc,
                                     String owner, String repo, String path) {
        service.getRepositoryContent(acc, auth, owner, repo, path)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    public void getFileContent(Observer<String> observer, String auth, String acc,
                               String owner, String repo, String path) {
        Retrofit retrofit = RetrofitUtil.initCustomRetrofit(Api.GitHubApi, StringConverterFactory.create(),
                RxJavaCallAdapterFactory.create());
        RepositoriesService service = retrofit.create(RepositoriesService.class);
        service.getFileContent(auth, acc, owner, repo, path)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    public void getRepository(Observer<RepositoriesBean> observer, String auth, String username, String repo) {
        service.getRepository(auth, username, repo)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    public void getReadMe(Observer<String> observer, String auth, String username, String repo) {
        Retrofit retrofit = RetrofitUtil.initCustomRetrofit(Api.GitHubApi, StringConverterFactory.create(),
                RxJavaCallAdapterFactory.create());
        RepositoriesService service = retrofit.create(RepositoriesService.class);
        service.getReadMe(auth, "application/vnd.github.VERSION.html", username, repo)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }
}
