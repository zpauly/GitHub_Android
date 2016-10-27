package com.zpauly.githubapp.db;

import android.content.ContentValues;

import com.zpauly.githubapp.entity.response.AuthenticatedUserBean;
import com.zpauly.githubapp.entity.response.UserBean;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by zpauly on 16-6-12.
 */
public class UserDao {
    public static boolean insertUser(AuthenticatedUserBean user) {
        final boolean[] result = {false};
        Observable.just(user)
                .map(new Func1<AuthenticatedUserBean, Boolean>() {
                    @Override
                    public Boolean call(AuthenticatedUserBean authenticatedUserBean) {
                        UserModel userModel = new UserModel();
                        userModel.setLogin(authenticatedUserBean.getLogin());
                        userModel.setName(authenticatedUserBean.getName());
                        userModel.setLocation(authenticatedUserBean.getLocation());
                        userModel.setAvatar_url(authenticatedUserBean.getAvatar_url());
                        userModel.setBio(authenticatedUserBean.getBio());
                        userModel.setBlog(authenticatedUserBean.getBlog());
                        userModel.setEmail(authenticatedUserBean.getEmail());
                        userModel.setCompany(authenticatedUserBean.getCompany());
                        userModel.setHirable(authenticatedUserBean.isHireable());
                        userModel.setFollowing(authenticatedUserBean.getFollowing());
                        userModel.setFollowers(authenticatedUserBean.getFollowers());
                        userModel.setCreated_at(authenticatedUserBean.getCreated_at());
                        userModel.setUpdated_at(authenticatedUserBean.getUpdated_at());
                        userModel.setPublic_gists(authenticatedUserBean.getPublic_gists());
                        userModel.setPublic_repos(authenticatedUserBean.getPublic_repos());
                        userModel.setType(authenticatedUserBean.getType());
                        userModel.setUser(true);
                        return userModel.save();
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Boolean>() {
                    @Override
                    public void call(Boolean aBoolean) {
                        result[0] = aBoolean;
                    }
                });
        return result[0];
    }

    public static List<UserModel> query(final String tip, final String data) {
        final List<UserModel> list = new ArrayList<>();
        Observable.create(new Observable.OnSubscribe<List<UserModel>>() {
            @Override
            public void call(Subscriber<? super List<UserModel>> subscriber) {
                subscriber.onNext(DataSupport.where(tip + " = ?", data).find(UserModel.class));
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<List<UserModel>>() {
                    @Override
                    public void call(List<UserModel> userModels) {
                        list.addAll(userModels);
                    }
                });
        return list;
    }

    public static UserModel queryUser() {
        final List<UserModel> list = new ArrayList<>();
        Observable.create(new Observable.OnSubscribe<List<UserModel>>() {
            @Override
            public void call(Subscriber<? super List<UserModel>> subscriber) {
                List<UserModel> list = DataSupport.findAll(UserModel.class);
                if (list.size() == 0) {
                    subscriber.onNext(new ArrayList<UserModel>());
                } else {
                    subscriber.onNext(list);
                }
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<List<UserModel>>() {
                    @Override
                    public void call(List<UserModel> userModels) {
                        list.addAll(userModels);
                    }
                });
        return list.get(list.size() - 1);
    }

    public static void deleteUser() {
        Observable.create(new Observable.OnSubscribe<Object>() {
            @Override
            public void call(Subscriber<? super Object> subscriber) {
                DataSupport.deleteAll(UserModel.class);
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Object>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Object o) {

                    }
                });
    }
}
