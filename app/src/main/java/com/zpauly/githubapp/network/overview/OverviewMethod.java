package com.zpauly.githubapp.network.overview;

import com.zpauly.githubapp.Constants;
import com.zpauly.githubapp.base.BaseNetMethod;
import com.zpauly.githubapp.entity.request.AuthorizationRequest;
import com.zpauly.githubapp.entity.response.AppAuthorizationBean;
import com.zpauly.githubapp.utils.RetrofitUtil;

import retrofit2.Retrofit;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by zpauly on 16-6-9.
 */
public class OverviewMethod extends BaseNetMethod {
    private Retrofit retrofit;

    private OverviewService service;

    private static class Nested {
        static OverviewMethod instance = new OverviewMethod();
    }

    private OverviewMethod() {
        retrofit = RetrofitUtil.initRetrofit(Constants.GITHUB_API_URL);

        service = retrofit.create(OverviewService.class);
    }

    public static OverviewMethod getInstance() {
        return Nested.instance;
    }

    public void getOrCreateAuthorization(Observer<AppAuthorizationBean> observer, String auth, AuthorizationRequest requestBean) {
        service.login(auth, requestBean)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }
}
