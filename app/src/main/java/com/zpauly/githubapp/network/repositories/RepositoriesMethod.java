package com.zpauly.githubapp.network.repositories;

import android.support.annotation.Nullable;

import com.zpauly.githubapp.Api;
import com.zpauly.githubapp.base.BaseNetMethod;
import com.zpauly.githubapp.entity.request.CommitCommentRequestBean;
import com.zpauly.githubapp.entity.response.CommentBean;
import com.zpauly.githubapp.entity.response.repos.BranchBean;
import com.zpauly.githubapp.entity.response.repos.ContributorBean;
import com.zpauly.githubapp.entity.response.repos.ReleaseBean;
import com.zpauly.githubapp.entity.response.repos.RepositoriesBean;
import com.zpauly.githubapp.entity.response.repos.RepositoryContentBean;
import com.zpauly.githubapp.entity.response.repos.SingleCommitBean;
import com.zpauly.githubapp.entity.response.repos.TagBean;
import com.zpauly.githubapp.utils.RetrofitUtil;
import com.zpauly.githubapp.utils.StringConverterFactory;

import java.io.File;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by zpauly on 16-7-14.
 */

public class RepositoriesMethod extends BaseNetMethod {
    private Retrofit retrofit;

    private RepositoriesService service;

    private static class Nested {
        static RepositoriesMethod instance = new RepositoriesMethod();
    }

    private RepositoriesMethod() {
        retrofit = RetrofitUtil.initRetrofit(Api.GitHubApi);
        service = retrofit.create(RepositoriesService.class);
    }

    public static RepositoriesMethod getInstance() {
        return Nested.instance;
    }

    public void getOwendRepositories(Observer<List<RepositoriesBean>> observer,
                                     String auth,
                                     String visibility,
                                     @Nullable List<String> affiliation,
                                     @Nullable String type,
                                     @Nullable String sort,
                                     @Nullable String direction,
                                     int pageId) {
        service.getOwendRepositories(auth, visibility, affiliation, type, sort, direction, pageId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    public void getRepositories(Observer<List<RepositoriesBean>> observer,
                                String auth,
                                String username,
                                @Nullable String type,
                                @Nullable String sort,
                                @Nullable String direction,
                                int pageId) {
        service.getRepositories(auth, username, type, sort, direction, pageId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    public void getRepositoryContent(Observer<List<RepositoryContentBean>> observer,
                                     String auth, String acc,
                                     String owner, String repo,
                                     String path, String ref) {
        service.getRepositoryContent(acc, auth, owner, repo, path, ref)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    public void getFileContent(Observer<String> observer, String auth, String acc,
                               String owner, String repo, String path, String branch) {
        Retrofit retrofit = RetrofitUtil.initCustomRetrofit(Api.GitHubApi, StringConverterFactory.create(),
                RxJavaCallAdapterFactory.create());
        RepositoriesService service = retrofit.create(RepositoriesService.class);
        service.getFileContent(auth, acc, owner, repo, path, branch)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    public void getRepository(Observer<RepositoriesBean> observer, String auth, String username, String repo, String ref) {
        service.getRepository(auth, username, repo, ref)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    public void getReadMe(Observer<String> observer, String auth, String username, String repo, String ref) {
        Retrofit retrofit = RetrofitUtil.initCustomRetrofit(Api.GitHubApi, StringConverterFactory.create(),
                RxJavaCallAdapterFactory.create());
        RepositoriesService service = retrofit.create(RepositoriesService.class);
        service.getReadMe(auth, "application/vnd.github.VERSION.html", username, repo, ref)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    public void getRepositoryCommit(Observer<List<SingleCommitBean>> observer, String auth,
                                    String owner, String repo, int pageId) {
        service.getRepositoryCommit(auth, owner, repo, pageId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    public void getASingleCommit(Observer<SingleCommitBean> observer, String auth,
                                 String owner, String repo, String sha) {
        service.getASingleCommit(auth, owner, repo, sha)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    public void getACommitComments(Observer<List<CommentBean>> observer, String auth,
                                   String owner, String repo, String ref, int pageId) {
        service.getACommitComments(auth, "application/vnd.github.VERSION.html", owner, repo, ref, pageId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    public void createACommitComment(Observer<CommentBean> observer, String auth,
                                     String owner, String repo, String sha,
                                     CommitCommentRequestBean bean) {
        service.createACommitComment(auth, owner, repo, sha, bean)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    public void getRepositoryReleases(Observer<List<ReleaseBean>> observer, String auth,
                                      String owner, String repo, int pageId) {
        service.getRepositoryReleases(auth, owner, repo, pageId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    public void getASingleRelease(Observer<ReleaseBean> observer, String auth,
                                  String owner, String repo, int id, int pageId) {
        service.getASingleRelease(auth, owner, repo, id, pageId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    public void getContributors(Observer<List<ContributorBean>> observer, String auth,
                                String owner, String repo, int pageId) {
        service.getContributors(auth, owner, repo, pageId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    public void getArchiveLink(Observer<File> observer,
                               Func1<Response<ResponseBody>, Observable<File>> func1,
                               String auth, String owner, String repo,
                               String archive_format, String ref) {
        Retrofit retrofit = RetrofitUtil.initSimpleRetrofit(Api.GitHubApi);
        RepositoriesService service = retrofit.create(RepositoriesService.class);
        service.getArchiveLink(auth, owner, repo, archive_format, ref)
                .flatMap(func1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    public void getTags(Observer<List<TagBean>> observer,
                        String auth, String owner, String repo) {
        service.getTags(auth, owner, repo)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    public void getBranches(Observer<List<BranchBean>> observer,
                            String auth, String owner, String repo) {
        service.getBranches(auth, owner, repo)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    public void getForks(Observer<List<RepositoriesBean>> observer,
                         String auth, String owner, String repo, String sort, int pageId) {
        service.getForks(auth, owner, repo, sort, pageId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    public void createAFork(Observer<RepositoriesBean> observer,
                            String auth, String owner, String repo) {
        service.createAFork(auth, owner, repo)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }
}
