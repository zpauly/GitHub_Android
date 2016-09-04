package com.zpauly.githubapp.network.gists;

import com.zpauly.githubapp.Api;
import com.zpauly.githubapp.base.BaseNetMethod;
import com.zpauly.githubapp.entity.response.gists.GistsBean;
import com.zpauly.githubapp.utils.RetrofitUtil;
import com.zpauly.githubapp.utils.StringConverterFactory;

import java.util.List;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by zpauly on 16-8-5.
 */

public class GistsMethod extends BaseNetMethod {
    private Retrofit retrofit;

    private GistsService service;

    private static class Nested {
        static GistsMethod instance = new GistsMethod();
    }

    private GistsMethod() {
        retrofit = RetrofitUtil.initRetrofit(Api.GitHubApi);
        service = retrofit.create(GistsService.class);
    }

    public static GistsMethod getInstance() {
        return Nested.instance;
    }

    public void getUserGists(Observer<List<GistsBean>> observer, String auth, int pageId) {
        service.getUserGists(auth, pageId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    public void getUserGists(Observer<List<GistsBean>> observer, String auth, String username, int pageId) {
        service.getUserGists(auth, username, pageId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    public void getStarredGists(Observer<List<GistsBean>> observer, String auth,
                                int pageId) {
        service.getStarredGists(auth, pageId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    public void getPublicGists(Observer<List<GistsBean>> observer, String auth, int pageId) {
        service.getPublicGists(auth, pageId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    public void getASingleGIst(Observer<GistsBean> observer, String auth, String id) {
        service.getASingleGist(auth, id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }
}
