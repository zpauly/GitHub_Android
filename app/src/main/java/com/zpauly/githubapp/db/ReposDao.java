package com.zpauly.githubapp.db;

import com.zpauly.githubapp.entity.response.repos.RepositoriesBean;

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
 * Created by zpauly on 16-7-18.
 */

public class ReposDao {
    public static boolean insertRepo(RepositoriesBean repos) {
        final boolean[] result = {false};
        Observable.just(repos)
                .map(new Func1<RepositoriesBean, Boolean>() {

                    @Override
                    public Boolean call(RepositoriesBean repositoriesBean) {
                        ReposModel model = new ReposModel();
                        model.setContributors_url(repositoriesBean.getContributors_url());
                        model.setCreated_at(repositoriesBean.getCreated_at());
                        model.setDefault_branch(repositoriesBean.getDefault_branch());
                        model.setDeployments_url(repositoriesBean.getDeployments_url());
                        model.setEvents_url(repositoriesBean.getEvents_url());
                        model.setForks_count(repositoriesBean.getForks_count());
                        model.setDownloads_url(repositoriesBean.getDownloads_url());
                        model.setForks_url(repositoriesBean.getForks_url());
                        model.setWatchers_count(repositoriesBean.getWatchers_count());
                        model.setUrl(repositoriesBean.getUrl());
                        model.setTeams_url(repositoriesBean.getTeams_url());
                        model.setTags_url(repositoriesBean.getTags_url());
                        model.setSubscription_url(repositoriesBean.getSubscription_url());
                        model.setSubscribers_url(repositoriesBean.getSubscribers_url());
                        model.setStargazers_url(repositoriesBean.getStargazers_url());
                        model.setStargazers_count(repositoriesBean.getStargazers_count());
                        model.setSize(repositoriesBean.getSize());
                        model.setPushed_at(repositoriesBean.getPushed_at());
                        model.setUpdated_at(repositoriesBean.getUpdated_at());
                        model.setOpen_issue_count(repositoriesBean.getOpen_issues_count());
                        model.setName(repositoriesBean.getName());
                        model.setMerges_url(repositoriesBean.getMerges_url());
                        model.setFull_name(repositoriesBean.getFull_name());
                        model.setLanauages_url(repositoriesBean.getLanguages_url());
                        model.setLanguage(repositoriesBean.getLanguage());
                        model.setHtml_url(repositoriesBean.getHtml_url());
                        model.setHooks_url(repositoriesBean.getHooks_url());
                        model.setDescription(repositoriesBean.getDescription());
                        model.setPrivateX(repositoriesBean.isPrivateX());
                        model.setFork(repositoriesBean.isFork());
                        model.setHas_downloads(repositoriesBean.isHas_downloads());
                        model.setHas_pages(repositoriesBean.isHas_pages());
                        model.setHas_issues(repositoriesBean.isHas_issues());
                        model.setHas_wiki(repositoriesBean.isHas_wiki());
                        model.setLogin(repositoriesBean.getOwner().getLogin());
                        model.setAvatar_url(repositoriesBean.getOwner().getAvatar_url());
                        return model.save();
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

    public static List<ReposModel> queryRepos(final String tip, final String data) {
        final List<ReposModel> list = new ArrayList<>();
        Observable.create(new Observable.OnSubscribe<List<ReposModel>>() {
            @Override
            public void call(Subscriber<? super List<ReposModel>> subscriber) {
                subscriber.onNext(DataSupport.where(tip + " = ?", data).find(ReposModel.class));
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<List<ReposModel>>() {
                    @Override
                    public void call(List<ReposModel> reposModels) {
                        list.addAll(reposModels);
                    }
                });
        return list;
    }

    public static List<ReposModel> queryRepos() {
        final List<ReposModel> list = new ArrayList<>();
        Observable.create(new Observable.OnSubscribe<List<ReposModel>>() {
            @Override
            public void call(Subscriber<? super List<ReposModel>> subscriber) {
                subscriber.onNext(DataSupport.findAll(ReposModel.class));
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<List<ReposModel>>() {
                    @Override
                    public void call(List<ReposModel> reposModels) {
                        list.addAll(reposModels);
                    }
                });
        return list;
    }

    public static void deleteRepos() {
        Observable.create(new Observable.OnSubscribe<Object>() {

            @Override
            public void call(Subscriber<? super Object> subscriber) {
                DataSupport.deleteAll(ReposModel.class);
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
