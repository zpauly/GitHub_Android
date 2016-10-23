package com.zpauly.githubapp.presenter.issues;

import android.content.Context;

import com.zpauly.githubapp.base.NetPresenter;
import com.zpauly.githubapp.entity.response.PullRequestBean;
import com.zpauly.githubapp.entity.response.issues.IssueBean;
import com.zpauly.githubapp.network.issues.IssuesMethod;
import com.zpauly.githubapp.network.pullRequests.PullRequestsMethod;
import com.zpauly.githubapp.view.issues.IssuesOrPullRequestsActivity;

import java.util.List;

import rx.Subscriber;

/**
 * Created by zpauly on 16/9/4.
 */
public class IssuesOrPullRequestsPresenter extends NetPresenter implements IssuesOrPullRequestsContract.Presenter {
    private final String TAG = getClass().getName();

    private Context mContext;
    private IssuesOrPullRequestsContract.View mIssuesView;

    private String auth;
    private IssuesMethod issuesMethod;
    private PullRequestsMethod pullRequestsMethod;

    private Subscriber<List<IssueBean>> issuesSubscriber;
    private Subscriber<List<PullRequestBean>> pullRequestsSubscrber;

    private int pageId = 1;

    public IssuesOrPullRequestsPresenter(Context context, IssuesOrPullRequestsContract.View view) {
        mContext = context;
        mIssuesView = view;
        view.setPresenter(this);
        start();
    }


    @Override
    public int getPageId() {
        return pageId;
    }

    @Override
    public void setPageId(int pageId) {
        this.pageId = pageId;
    }

    @Override
    public void getIssues() {
        issuesSubscriber = new Subscriber<List<IssueBean>>() {
            @Override
            public void onCompleted() {
                mIssuesView.getIssueSuccess();
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
                mIssuesView.getIssueFail();
            }

            @Override
            public void onNext(List<IssueBean> issueBeen) {
                mIssuesView.gettingIssues(issueBeen);
            }
        };
        switch (mIssuesView.getIssueType()) {
            case IssuesOrPullRequestsActivity.USER_ISSUES:
                issuesMethod.getIssues(issuesSubscriber, auth, mIssuesView.getFilter(), mIssuesView.getState(), null, mIssuesView.getSort(), mIssuesView.getDirection(), null, pageId++);
                break;
            case IssuesOrPullRequestsActivity.REPO_ISSUES:
                issuesMethod.getARepoIssues(issuesSubscriber, auth, mIssuesView.getUsername(), mIssuesView.getRepoName(), null, mIssuesView.getState(), null, null, null, mIssuesView.getSort(), mIssuesView.getDirection(), null, null, pageId++);
                break;
            case IssuesOrPullRequestsActivity.ORG_ISSUES:
                issuesMethod.getOrgIssues(issuesSubscriber, auth, mIssuesView.getOrgName(), mIssuesView.getFilter(), mIssuesView.getState(), null, mIssuesView.getSort(), mIssuesView.getDirection(), null, pageId++);
                break;
            default:
                break;
        }

    }

    @Override
    public void getPullRequests() {
        pullRequestsSubscrber = new Subscriber<List<PullRequestBean>>() {
            @Override
            public void onCompleted() {
                mIssuesView.getPullRequestsSuccess();
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
                mIssuesView.getPullRequestsFail();
            }

            @Override
            public void onNext(List<PullRequestBean> pullRequestBeen) {
                mIssuesView.gettingPullRequests(pullRequestBeen);
            }
        };
        pullRequestsMethod.getPullRequests(pullRequestsSubscrber, auth,
                mIssuesView.getUsername(), mIssuesView.getRepoName(), mIssuesView.getState(),
                null, null,
                mIssuesView.getSort(), mIssuesView.getDirection(), pageId++);
    }

    @Override
    public void start() {
        auth = getAuth(mContext);
        issuesMethod = getMethod(IssuesMethod.class);
        pullRequestsMethod = getMethod(PullRequestsMethod.class);
    }

    @Override
    public void stop() {
        unsubscribe(issuesSubscriber, pullRequestsSubscrber);
    }
}
