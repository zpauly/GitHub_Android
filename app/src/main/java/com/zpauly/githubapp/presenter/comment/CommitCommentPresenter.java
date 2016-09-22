package com.zpauly.githubapp.presenter.comment;

import android.content.Context;

import com.zpauly.githubapp.base.NetPresenter;
import com.zpauly.githubapp.entity.response.CommentBean;
import com.zpauly.githubapp.network.repositories.RepositoriesMethod;

import java.util.List;

import rx.Subscriber;

/**
 * Created by zpauly on 16/9/22.
 */

public class CommitCommentPresenter extends NetPresenter implements CommitCommentContract.Presenter {
    private final String TAG = getClass().getName();

    private Context mContext;
    private CommitCommentContract.View mCommitCommentView;

    private String auth;
    private RepositoriesMethod repositoriesMethod;

    private Subscriber<List<CommentBean>> commitCommentSubscriber;

    private int pageId = 1;

    public CommitCommentPresenter(Context context, CommitCommentContract.View view) {
        mContext = context;
        mCommitCommentView = view;
        mCommitCommentView.setPresenter(this);
        start();
    }

    @Override
    public void getASingleCommitComments() {
        commitCommentSubscriber = new Subscriber<List<CommentBean>>() {
            @Override
            public void onCompleted() {
                mCommitCommentView.getCommentsSuccess();
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
                mCommitCommentView.getCommentsFail();
            }

            @Override
            public void onNext(List<CommentBean> commentBeen) {
                mCommitCommentView.gettingComments(commentBeen);
            }
        };
        repositoriesMethod.getACommitComments(commitCommentSubscriber, auth,
                mCommitCommentView.getOwner(), mCommitCommentView.getRepo(),
                mCommitCommentView.getRef(), pageId++);
    }

    @Override
    public void setPageId(int pageId) {
        this.pageId = pageId;
    }

    @Override
    public void start() {
        auth = getAuth(mContext);
        repositoriesMethod = getMethod(RepositoriesMethod.class);
    }

    @Override
    public void stop() {
        unsubscribe(commitCommentSubscriber);
    }
}
