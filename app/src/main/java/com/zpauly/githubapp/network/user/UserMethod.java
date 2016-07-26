package com.zpauly.githubapp.network.user;

import com.zpauly.githubapp.Api;
import com.zpauly.githubapp.entity.response.AuthenticatedUserBean;
import com.zpauly.githubapp.entity.response.FollowersBean;
import com.zpauly.githubapp.entity.response.UserBean;
import com.zpauly.githubapp.utils.RetrofitUtil;

import java.util.List;

import retrofit2.Retrofit;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by zpauly on 16-6-10.
 */
public class UserMethod {
    private static UserMethod instance;

    private Retrofit retrofit;
    private UserService service;

    private UserMethod() {
        retrofit = RetrofitUtil.initRetrofit(Api.GitHubApi);

        service = retrofit.create(UserService.class);
    }

    public static UserMethod getInstance() {
        if (instance == null) {
            synchronized (UserMethod.class) {
                if (instance == null) {
                    instance = new UserMethod();
                }
            }
        }
        return instance;
    }

    public void getAuthenticatedUser(Observer<AuthenticatedUserBean> observer, String auth) {
        service.getAuthenticatedUser(auth)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    public void getFollowers(Observer<List<FollowersBean>> observer, String auth) {
        service.getFollowers(auth)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    public void getFollowing(Observer<List<FollowersBean>> observer, String auth) {
        service.getFollowing(auth)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    public void getUser(Observer<UserBean> observer, String username) {
        service.getUser(username)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }
}
