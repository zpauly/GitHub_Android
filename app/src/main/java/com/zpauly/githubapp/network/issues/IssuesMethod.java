package com.zpauly.githubapp.network.issues;

import com.zpauly.githubapp.Api;
import com.zpauly.githubapp.base.BaseNetMethod;
import com.zpauly.githubapp.entity.response.issues.AssigneeBean;
import com.zpauly.githubapp.entity.response.issues.IssueCommentBean;
import com.zpauly.githubapp.entity.response.issues.IssueBean;
import com.zpauly.githubapp.utils.RetrofitUtil;

import java.util.List;

import retrofit2.Retrofit;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by zpauly on 16/9/1.
 */
public class IssuesMethod extends BaseNetMethod {
    private Retrofit retrofit;

    private IssuesService service;

    private static class Nested {
        static IssuesMethod instance = new IssuesMethod();
    }

    private IssuesMethod() {
        retrofit = RetrofitUtil.initRetrofit(Api.GitHubApi);
        service = retrofit.create(IssuesService.class);
    }

    public static IssuesMethod getInstance() {
        return Nested.instance;
    }

    public void getIssues(Observer<List<IssueBean>> observer, String auth,
                          String filter, String state, String labels, String sort,
                          String direction, String since, int pageId) {
        service.getIssues(auth, filter, state, labels, sort, direction, since, pageId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    public void getDefaultIssues(Observer<List<IssueBean>> observer, String auth, int pageId) {
        service.getIssues(auth, null, null, null, null, null, null, pageId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    public void getUserIssues(Observer<List<IssueBean>> observer, String auth,
                              String filter, String state, String labels, String sort,
                              String direction, String since, int pageId) {
        service.getUserIssues(auth, filter, state, labels, sort, direction, since, pageId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    public void getDefaultUserIssues(Observer<List<IssueBean>> observer, String auth, int pageId) {
        service.getUserIssues(auth, null, null, null, null, null, null, pageId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    public void getOrgIssues(Observer<List<IssueBean>> observer, String auth,
                             String org,
                             String filter, String state, String labels, String sort,
                             String direction, String since, int pageId) {
        service.getOrgIssues(auth, org, filter, state, labels, sort, direction, since, pageId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    public void getDefaultOrgIssues(Observer<List<IssueBean>> observer, String auth,
                                    String org, int pageId) {
        service.getOrgIssues(auth, org, null, null, null, null, null, null, pageId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    public void getARepoIssues(Observer<List<IssueBean>> observer,
                               String auth, String owner, String repo,
                               String milestone, String state, String assignee,
                               String creator, String mentioned, String sort,
                               String direction, String since, String[] labels, int pageId) {
        service.getARepoIssues(auth, owner, repo, milestone, state, assignee, creator,
                mentioned, sort, direction, since, labels, pageId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    public void getDefaultARepoIssues(Observer<List<IssueBean>> observer,
                                      String auth, String owner, String repo, int pageId) {
        service.getARepoIssues(auth, owner, repo,
                null, null, null, null, null, null, null, null, null, pageId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    public void getASingleIssue(Observer<IssueBean> observer,
                                String auth, String owner, String repo, int number, int pageId) {
        service.getASingleIssue(auth, owner, repo, number, pageId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    public void getAnIssueComments(Observer<List<IssueCommentBean>> observer,
                                   String auth, String owner, String repo, int number,
                                   String since, int pageId) {
        service.getAnIssueComments(auth, owner, repo, number, since, pageId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    public void getDefaultAnIssueComments(Observer<List<IssueCommentBean>> observer,
                                   String auth, String owner, String repo, int number,
                                   int pageId) {
        service.getAnIssueComments(auth, owner, repo, number, null, pageId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    @SuppressWarnings("unchecked")
    public void checkAssignee(Observer observer, String auth, String owner, String repo,
                              String assignee) {
        service.checkAssignee(auth, owner, repo, assignee)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    public void getAssignees(Observer<List<AssigneeBean>> observer, String auth,
                             String owner, String repo) {
        service.getAssignees(auth, owner, repo)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    public void createAnIssue(Observer<com.zpauly.githubapp.entity.request.IssueBean> observer,
                              com.zpauly.githubapp.entity.request.IssueBean issueBean,
                              String auth, String owner, String repo) {
        service.createAnIssue(auth, issueBean, owner, repo)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    public void createAComment(Observer<IssueCommentBean> observer,
                               com.zpauly.githubapp.entity.request.CommentBean commentBean,
                               String auth, String owner, String repo, int number) {
        service.createAComment(auth, commentBean, owner, repo, number)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }
}
