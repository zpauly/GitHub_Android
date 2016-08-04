package com.zpauly.githubapp.network.organizations;

import com.zpauly.githubapp.Api;
import com.zpauly.githubapp.entity.response.OrganizationBean;
import com.zpauly.githubapp.utils.RetrofitUtil;

import java.util.List;

import retrofit2.Retrofit;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by zpauly on 16-8-4.
 */

public class OrganizationsMethod {
    private static OrganizationsMethod instance;

    private Retrofit retrofit;

    private OrganizationsService service;

    private OrganizationsMethod() {
        retrofit = RetrofitUtil.initRetrofit(Api.GitHubApi);
        service = retrofit.create(OrganizationsService.class);
    }

    public static OrganizationsMethod getInstance() {
        if (instance == null) {
            synchronized (OrganizationsMethod.class) {
                if (instance == null) {
                    instance = new OrganizationsMethod();
                }
            }
        }
        return instance;
    }

    public void getUserOrgs(Observer<List<OrganizationBean>> observer, String auth) {
        service.getUserOrgs(auth)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    public void getUserOrgs(Observer<List<OrganizationBean>> observer, String auth, String username) {
        service.getUserOrgs(auth, username)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }
}
