package com.zpauly.githubapp.network.pullRequests;

import com.zpauly.githubapp.Api;
import com.zpauly.githubapp.base.BaseNetMethod;
import com.zpauly.githubapp.entity.response.events.Payload;
import com.zpauly.githubapp.utils.RetrofitUtil;

import java.util.List;

import retrofit2.Retrofit;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by zpauly on 2016/10/22.
 */

public class PullRequestsMethod extends BaseNetMethod {
    private Retrofit retrofit;

    private PullRequestsService service;

    private static class Nested {
        static PullRequestsMethod instance = new PullRequestsMethod();
    }

    public static PullRequestsMethod getInstance() {
        return Nested.instance;
    }

    private PullRequestsMethod() {
        retrofit = RetrofitUtil.initRetrofit(Api.GitHubApi);
        service = retrofit.create(PullRequestsService.class);
    }

    public void getPullRequests(Observer<List<Payload.PullRequestBean>> observer,
                                 String auth, String owner, String repo,
                                 String state, String head, String base,
                                 String sort, String direction, int pageId) {
        service.getPullRequests(auth, owner, repo, state, head, base, sort, direction, pageId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }
}
