package com.zpauly.githubapp.presenter.issues;

import android.content.Context;

import com.zpauly.githubapp.base.NetPresenter;
import com.zpauly.githubapp.entity.response.issues.AssigneeBean;
import com.zpauly.githubapp.network.issues.IssuesMethod;

import java.util.List;

import rx.Subscriber;

/**
 * Created by zpauly on 16/9/12.
 */
public class IssueCreatePresenter extends NetPresenter implements IssueCreateContract.Presenter {
    private final String TAG = getClass().getName();

    private Context mContext;
    private IssueCreateContract.View mIssueCreateView;

    private String auth;
    private IssuesMethod issuesMethod;

    private Subscriber<List<AssigneeBean>> assigneesSubscribe;
    private Subscriber checkSubscribe;

    public IssueCreatePresenter(Context context, IssueCreateContract.View view) {
        mContext = context;
        mIssueCreateView = view;
        view.setPresenter(this);
        start();
    }

    @Override
    public void checkAssignee() {
        checkSubscribe = new Subscriber() {
            @Override
            public void onCompleted() {
                mIssueCreateView.checkSuccess();
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
                mIssueCreateView.checkFail();
            }

            @Override
            public void onNext(Object o) {

            }
        };
        issuesMethod.checkAssignee(checkSubscribe, auth, mIssueCreateView.getUsername(),
                mIssueCreateView.getOwner(), mIssueCreateView.getUsername());
    }

    @Override
    public void getAssignees() {
        assigneesSubscribe = new Subscriber<List<AssigneeBean>>() {
            @Override
            public void onCompleted() {
                mIssueCreateView.getAssigneesSuccess();
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
                mIssueCreateView.getAssigneeFail();
            }

            @Override
            public void onNext(List<AssigneeBean> assigneeBeen) {
                mIssueCreateView.gettingAssignees(assigneeBeen);
            }
        };
        issuesMethod.getAssignees(assigneesSubscribe, auth, mIssueCreateView.getOwner(),
                mIssueCreateView.getRepoName());
    }

    @Override
    public void start() {
        auth = getAuth(mContext);
        issuesMethod = getMethod(IssuesMethod.class);
    }

    @Override
    public void stop() {
        unsubscribe(assigneesSubscribe, checkSubscribe);
    }
}
