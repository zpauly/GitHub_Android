package com.zpauly.githubapp.presenter.issues;

import android.content.Context;

import com.zpauly.githubapp.base.NetPresenter;
import com.zpauly.githubapp.entity.response.CommentBean;
import com.zpauly.githubapp.entity.response.repos.FileBean;
import com.zpauly.githubapp.entity.response.repos.SingleCommitBean;
import com.zpauly.githubapp.network.pullRequests.PullRequestsMethod;

import java.util.List;

import rx.Subscriber;

/**
 * Created by zpauly on 2016/10/22.
 */

public class PullRequestContentPresenter extends NetPresenter implements PullRequestContentContract.Presenter {
    private final String TAG = getClass().getName();

    private Context mContext;

    private PullRequestContentContract.View mPullRequestContentView;

    private String auth;

    private PullRequestsMethod pullRequestsMethod;

    private Subscriber<List<CommentBean>> commentsSubscriber;
    private Subscriber<List<SingleCommitBean>> commitsSubscriber;
    private Subscriber<List<FileBean>> filesSubscriber;

    private int pageId = 1;

    public PullRequestContentPresenter(Context context, PullRequestContentContract.View view) {
        this.mContext = context;
        mPullRequestContentView = view;
        mPullRequestContentView.setPresenter(this);
        start();
    }

    @Override
    public void getComments() {
        commentsSubscriber = new Subscriber<List<CommentBean>>() {
            @Override
            public void onCompleted() {
                pageId++;
                mPullRequestContentView.getCommentsSuccess();
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
                mPullRequestContentView.getCommentsFail();
            }

            @Override
            public void onNext(List<CommentBean> commentBeen) {
                mPullRequestContentView.gettingComments(commentBeen);
            }
        };
        pullRequestsMethod.getAPullComments(commentsSubscriber, auth,
                mPullRequestContentView.getOwner(),
                mPullRequestContentView.getRepo(),
                mPullRequestContentView.getNumber(),
                pageId);
    }

    @Override
    public void getCommits() {
        commitsSubscriber = new Subscriber<List<SingleCommitBean>>() {
            @Override
            public void onCompleted() {
                pageId++;
                mPullRequestContentView.getCommitsSuccess();
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
                mPullRequestContentView.getCommitsFail();
            }

            @Override
            public void onNext(List<SingleCommitBean> commitBeen) {
                mPullRequestContentView.gettingCommits(commitBeen);
            }
        };
        pullRequestsMethod.getAPullCommits(commitsSubscriber, auth,
                mPullRequestContentView.getOwner(),
                mPullRequestContentView.getRepo(),
                mPullRequestContentView.getNumber(),
                pageId);
    }

    @Override
    public void getFiles() {
        filesSubscriber = new Subscriber<List<FileBean>>() {
            @Override
            public void onCompleted() {
                pageId++;
                mPullRequestContentView.getFilesSuccess();
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
                mPullRequestContentView.getFilsFail();
            }

            @Override
            public void onNext(List<FileBean> fileBeen) {
                mPullRequestContentView.gettingFiles(fileBeen);
            }
        };
        pullRequestsMethod.getAPullFiles(filesSubscriber, auth,
                mPullRequestContentView.getOwner(), mPullRequestContentView.getRepo(),
                mPullRequestContentView.getNumber(), pageId);
    }

    @Override
    public void setPageId(int pageId) {
        this.pageId = pageId;
    }

    @Override
    public void start() {
        auth = getAuth(mContext);
        pullRequestsMethod = getMethod(PullRequestsMethod.class);
    }

    @Override
    public void stop() {
        unsubscribe(commentsSubscriber, commitsSubscriber, filesSubscriber);
    }
}
