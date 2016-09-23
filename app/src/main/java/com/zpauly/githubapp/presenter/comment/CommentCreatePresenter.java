package com.zpauly.githubapp.presenter.comment;

import android.content.Context;

import com.zpauly.githubapp.base.NetPresenter;
import com.zpauly.githubapp.entity.request.CommitCommentRequestBean;
import com.zpauly.githubapp.entity.request.IssueCommentRequestBean;
import com.zpauly.githubapp.entity.response.CommentBean;
import com.zpauly.githubapp.network.issues.IssuesMethod;
import com.zpauly.githubapp.network.repositories.RepositoriesMethod;
import com.zpauly.githubapp.view.comment.CommentCreateActivity;

import rx.Subscriber;

/**
 * Created by zpauly on 16/9/8.
 */
public class CommentCreatePresenter extends NetPresenter implements CommentCreateContract.Presenter {
    private final String TAG = getClass().getName();

    private Context mContext;
    private CommentCreateContract.View mCommentCreateView;

    private String auth;
    private IssuesMethod issuesMethod;
    private RepositoriesMethod repositoriesMethod;

    private Subscriber<CommentBean> commentSubscriber;

    public CommentCreatePresenter(Context context, CommentCreateContract.View view) {
        this.mContext = context;
        this.mCommentCreateView = view;
        mCommentCreateView.setPresenter(this);
        start();
    }

    @Override
    public void createAComment() {
        commentSubscriber = new Subscriber<CommentBean>() {
            @Override
            public void onCompleted() {
                mCommentCreateView.createCommentSuccess();
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
                mCommentCreateView.createCommentFail();
            }

            @Override
            public void onNext(CommentBean commentBean) {
                mCommentCreateView.creatingComment(commentBean);
            }
        };
        switch (mCommentCreateView.getCommentType()) {
            case CommentCreateActivity.TYPE_COMMIT:
                repositoriesMethod.createACommitComment(commentSubscriber, auth,
                        mCommentCreateView.getOwner(), mCommentCreateView.getRepo(),
                        mCommentCreateView.getSha(), getCommitComment());
                break;
            case CommentCreateActivity.TYPE_ISSUE:
                issuesMethod.createAComment(commentSubscriber, getIssueComment(), auth,
                        mCommentCreateView.getOwner(), mCommentCreateView.getRepo(), mCommentCreateView.getNum());
                break;
            default:
                break;
        }
    }

    @Override
    public void start() {
        auth = getAuth(mContext);
        issuesMethod = getMethod(IssuesMethod.class);
        repositoriesMethod = getMethod(RepositoriesMethod.class);
    }

    @Override
    public void stop() {
        unsubscribe(commentSubscriber);
    }

    private CommitCommentRequestBean getCommitComment() {
        CommitCommentRequestBean commitCommentRequestBean = new CommitCommentRequestBean();
        commitCommentRequestBean.setBody(mCommentCreateView.getCommentBody());
        return commitCommentRequestBean;
    }

    private IssueCommentRequestBean getIssueComment() {
        IssueCommentRequestBean issueCommentRequestBean = new IssueCommentRequestBean();
        issueCommentRequestBean.setBody(mCommentCreateView.getCommentBody());
        return issueCommentRequestBean;
    }
}
