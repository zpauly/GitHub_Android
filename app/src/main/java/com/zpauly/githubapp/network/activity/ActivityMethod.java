package com.zpauly.githubapp.network.activity;

import android.support.annotation.Nullable;

import com.zpauly.githubapp.Api;
import com.zpauly.githubapp.entity.response.StarredRepositories;
import com.zpauly.githubapp.entity.response.events.EventsBean;
import com.zpauly.githubapp.utils.RetrofitUtil;

import java.util.List;

import retrofit2.Retrofit;
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

    public void getStarredRepositories(Observer<List<StarredRepositories>> observer, String auth,
                                       @Nullable String sort, @Nullable String direction) {
        service.getStarredRepositories(auth, sort, direction)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    public void getReceivedEvents(Observer<List<EventsBean>> observer, String auth, String username) {
        service.getReceivedEvents(auth, username)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }
}
