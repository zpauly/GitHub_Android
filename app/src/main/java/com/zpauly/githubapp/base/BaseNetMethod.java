package com.zpauly.githubapp.base;

import com.zpauly.githubapp.Api;
import com.zpauly.githubapp.utils.RetrofitUtil;

import retrofit2.Retrofit;

/**
 * Created by zpauly on 16/9/4.
 */
public class BaseNetMethod<T extends BaseNetService> {
    private T service;
    private Retrofit retrofit;

    protected BaseNetMethod() {
        retrofit = RetrofitUtil.initRetrofit(Api.GitHubApi);
        service = retrofit.create();
    }
}
