package com.zpauly.githubapp.presenter.comment;

import android.content.Context;

import com.zpauly.githubapp.base.NetPresenter;
import com.zpauly.githubapp.entity.request.CommentRequestBean;
import com.zpauly.githubapp.entity.response.issues.IssueCommentBean;
import com.zpauly.githubapp.network.issues.IssuesMethod;

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

    private Subscriber<IssueCommentBean> commentSubscriber;

    public CommentCreatePresenter(Context context, CommentCreateContract.View view) {
        this.mContext = context;
        this.mCommentCreateView = view;
        mCommentCreateView.setPresenter(this);
        start();
    }

    @Override
    public void createAComment() {
        commentSubscriber = new Subscriber<IssueCommentBean>() {
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
            public void onNext(IssueCommentBean issueCommentBean) {
                mCommentCreateView.creatingComment(issueCommentBean);
            }
        };
        issuesMethod.createAComment(commentSubscriber, getComment(), auth,
                mCommentCreateView.getOwner(), mCommentCreateView.getRepo(), mCommentCreateView.getNum());
    }

    @Override
    public void start() {
        auth = getAuth(mContext);
        issuesMethod = getMethod(IssuesMethod.class);
    }

    @Override
    public void stop() {
        unsubscribe(commentSubscriber);
    }

    private CommentRequestBean getComment() {
        CommentRequestBean commentRequestBean = new CommentRequestBean();
        commentRequestBean.setBody(mCommentCreateView.getCommentBody());
        return commentRequestBean;
    }
}
