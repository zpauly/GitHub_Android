package com.zpauly.githubapp.network.gists;

import com.zpauly.githubapp.Api;
import com.zpauly.githubapp.entity.response.gists.GistsBean;
import com.zpauly.githubapp.utils.RetrofitUtil;

import java.util.List;

import retrofit2.Retrofit;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by zpauly on 16-8-5.
 */

public class GistsMethod {
    private static GistsMethod instance;

    private Retrofit retrofit;

    private GistsService service;

    private GistsMethod() {
        retrofit = RetrofitUtil.initRetrofit(Api.GitHubApi);
        service = retrofit.create(GistsService.class);
    }

    public static GistsMethod getInstance() {
        if (instance == null) {
            synchronized (GistsMethod.class) {
                if (instance == null) {
                    instance = new GistsMethod();
                }
            }
        }
        return instance;
    }

    public void getUserGists(Observer<List<GistsBean>> observer, String auth) {
        service.getUserGists(auth)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    public void getUserGists(Observer<List<GistsBean>> observer, String auth, String username) {
        service.getUserGists(auth, username)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }
}
