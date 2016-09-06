package com.zpauly.githubapp.presenter.issues;

import android.content.Context;
import android.util.Log;

import com.zpauly.githubapp.base.NetPresenter;
import com.zpauly.githubapp.entity.response.CommentBean;
import com.zpauly.githubapp.network.issues.IssuesMethod;
import com.zpauly.githubapp.utils.TextUtil;

import java.util.List;

import rx.Subscriber;

/**
 * Created by zpauly on 16/9/6.
 */
public class IssueContentPresenter extends NetPresenter implements IssueContentContract.Presenter {
    private final String TAG = getClass().getName();

    private Context mContext;
    private IssueContentContract.View mIssueContentView;

    private String auth;
    private IssuesMethod issuesMethod;

    private Subscriber<List<CommentBean>> commentsSubscriber;

    private int pageId = 1;

    public IssueContentPresenter(Context context, IssueContentContract.View view) {
        this.mContext = context;
        this.mIssueContentView = view;
        mIssueContentView.setPresenter(this);
        start();
    }

    @Override
    public void getIssueComments() {
        commentsSubscriber = new Subscriber<List<CommentBean>>() {
            @Override
            public void onCompleted() {
                mIssueContentView.getCommentsSuccess();
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
                mIssueContentView.getCommentsFail();
            }

            @Override
            public void onNext(List<CommentBean> commentBeen) {
                mIssueContentView.gettingComments(commentBeen);
            }
        };
        Log.i(TAG, "/repos/" + mIssueContentView.getOwner() + "/" + mIssueContentView.getRepo() + "/issues/" + mIssueContentView.getNum() + "/comments");
        issuesMethod.getDefaultAnIssueComments(commentsSubscriber, auth, mIssueContentView.getOwner(), mIssueContentView.getRepo(), mIssueContentView.getNum(), pageId++);
    }

    @Override
    public void setPageId(int pageId) {
        this.pageId = pageId;
    }

    @Override
    public void start() {
        auth = getAuth(mContext);
        issuesMethod = getMethod(IssuesMethod.class);
    }

    @Override
    public void stop() {
        unsubscribe(commentsSubscriber);
    }
}
