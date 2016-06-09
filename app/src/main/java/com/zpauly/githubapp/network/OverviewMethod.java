package com.zpauly.githubapp.network;

import com.zpauly.githubapp.Constants;
import com.zpauly.githubapp.entity.request.AuthorizationRequest;
import com.zpauly.githubapp.entity.response.AppAuthorization;
import com.zpauly.githubapp.utils.RetrofitUtil;

import retrofit2.Retrofit;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by zpauly on 16-6-9.
 */
public class OverviewMethod {
    private static OverviewMethod instance;

    private Retrofit retrofit;

    private OverviewService service;

    private OverviewMethod() {
        retrofit = RetrofitUtil.initRetrofit(Constants.GITHUB_API_URL);

        service = retrofit.create(OverviewService.class);
    }

    public static OverviewMethod getInstance() {
        if (instance == null) {
            synchronized (OverviewMethod.class) {
                if (instance == null) {
                    instance = new OverviewMethod();
                }
            }
        }
        return instance;
    }

    public void getOrCreateAuthorization(Observer<AppAuthorization> observer, String auth, AuthorizationRequest requestBean) {
        service.login(auth, requestBean)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }
}
