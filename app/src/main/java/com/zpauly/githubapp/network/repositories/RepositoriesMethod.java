package com.zpauly.githubapp.network.repositories;

import android.support.annotation.Nullable;

import com.zpauly.githubapp.Api;
import com.zpauly.githubapp.entity.response.RepositoriesBean;
import com.zpauly.githubapp.utils.RetrofitUtil;

import java.util.List;

import retrofit2.Retrofit;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by zpauly on 16-7-14.
 */

public class RepositoriesMethod {
    private static RepositoriesMethod instance;

    private Retrofit retrofit;

    private RepositoriesService service;

    private RepositoriesMethod() {
        retrofit = RetrofitUtil.initRetrofit(Api.GitHubApi);
        service = retrofit.create(RepositoriesService.class);
    }

    public static RepositoriesMethod getInstance() {
        if (instance == null) {
            synchronized (RepositoriesBean.class) {
                if (instance == null) {
                    instance = new RepositoriesMethod();
                }
            }
        }
        return instance;
    }

    public void getOwendRespositories(Observer<List<RepositoriesBean>> observer, String auth
            , @Nullable List<String> affiliation, @Nullable String sort) {
        service.getOwendRespositories(auth, affiliation, sort)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }
}
