package com.zpauly.githubapp.network.activity;

import android.support.annotation.Nullable;

import com.zpauly.githubapp.Api;
import com.zpauly.githubapp.entity.response.RepositoriesBean;
import com.zpauly.githubapp.entity.response.events.EventsBean;
import com.zpauly.githubapp.utils.RetrofitUtil;
import com.zpauly.githubapp.utils.StringConverterFactory;

import java.util.List;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by zpauly on 16-7-17.
 */

public class ActivityMethod {
    private static ActivityMethod instance;

    private Retrofit retrofit;

    private ActivityService service;

    private ActivityMethod() {
        retrofit = RetrofitUtil.initRetrofit(Api.GitHubApi);

        service = retrofit.create(ActivityService.class);
    }

    public static ActivityMethod getInstance() {
        if (instance == null) {
            synchronized (ActivityMethod.class) {
                if (instance == null) {
                    instance = new ActivityMethod();
                }
            }
        }
        return instance;
    }

    public void getStarredRepositories(Observer<List<RepositoriesBean>> observer, String auth,
                                       @Nullable String sort, @Nullable String direction, int pageId) {
        service.getStarredRepositories(auth, sort, direction, pageId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    public void getOthersStarredRepositories(Observer<List<RepositoriesBean>> observer, String username,
                                             int pageId) {
        service.getOthersRepositories(username, pageId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    public void getUserEvents(Observer<List<EventsBean>> observer, String auth, String username, int pageId) {
        service.getUserEvents(auth, username, pageId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    public void getReceivedEvents(Observer<List<EventsBean>> observer, String auth, String username, int pageId) {
        service.getReceivedEvents(auth, username, pageId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    public void isRepoStarred(Observer<String> observer, String auth, String owner, String repo) {
        Retrofit retrofit = RetrofitUtil.initCustomRetrofit(Api.GitHubApi, StringConverterFactory.create(),
                RxJavaCallAdapterFactory.create());
        ActivityService service = retrofit.create(ActivityService.class);
        service.isRepoStarred(auth, owner, repo)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    public void starRepo(Observer<String> observer, String auth, String owner, String repo) {
        Retrofit retrofit = RetrofitUtil.initCustomRetrofit(Api.GitHubApi, StringConverterFactory.create(),
                RxJavaCallAdapterFactory.create());
        ActivityService service = retrofit.create(ActivityService.class);
        service.starARepo(auth, owner, repo)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    public void unstarRepo(Observer<String> observer, String auth, String owner, String repo) {
        Retrofit retrofit = RetrofitUtil.initCustomRetrofit(Api.GitHubApi, StringConverterFactory.create(),
                RxJavaCallAdapterFactory.create());
        ActivityService service = retrofit.create(ActivityService.class);
        service.unstarARepo(auth, owner, repo)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }
}
