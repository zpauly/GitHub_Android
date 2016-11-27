package com.zpauly.githubapp.presenter.issues;

import android.content.Context;
import android.util.Log;

import com.zpauly.githubapp.base.NetPresenter;
import com.zpauly.githubapp.entity.response.AssigneeBean;
import com.zpauly.githubapp.entity.response.issues.IssueBean;
import com.zpauly.githubapp.entity.response.issues.LabelBean;
import com.zpauly.githubapp.entity.response.MilestoneBean;
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

    private Subscriber<List<AssigneeBean>> assigneesSubscriber;
    private Subscriber<List<MilestoneBean>> milestonesSubscriber;
    private Subscriber<List<LabelBean>> labelsSubscriber;
    private Subscriber<IssueBean> createSubscriber;
    private Subscriber<String> checkSubscribe;

    public IssueCreatePresenter(Context context, IssueCreateContract.View view) {
        mContext = context;
        mIssueCreateView = view;
        view.setPresenter(this);
        start();
    }

    @Override
    public void checkAssignee() {
        checkSubscribe = new Subscriber<String>() {
            @Override
            public void onCompleted() {
                mIssueCreateView.checkSuccess();
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
                if (e.getMessage().contains("404")) {
                    mIssueCreateView.checkNotFound();
                } else {
                    mIssueCreateView.checkFail();
                }
            }

            @Override
            public void onNext(String o) {

            }
        };
        issuesMethod.checkAssignee(checkSubscribe, auth, mIssueCreateView.getOwner(),
                mIssueCreateView.getRepoName(), mIssueCreateView.getUsername());
    }

    @Override
    public void getAssignees() {
        assigneesSubscriber = new Subscriber<List<AssigneeBean>>() {
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
        issuesMethod.getAssignees(assigneesSubscriber, auth, mIssueCreateView.getOwner(),
                mIssueCreateView.getRepoName());
    }

    @Override
    public void getMilestones() {
        milestonesSubscriber = new Subscriber<List<MilestoneBean>>() {
            @Override
            public void onCompleted() {
                mIssueCreateView.getMilestonesSuccess();
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
                mIssueCreateView.getMilestonesFail();
            }

            @Override
            public void onNext(List<MilestoneBean> milestoneBeen) {
                mIssueCreateView.gettingMilestones(milestoneBeen);
            }
        };
        Log.i(TAG, auth);
        issuesMethod.getMilestones(milestonesSubscriber, auth, mIssueCreateView.getOwner(), mIssueCreateView.getRepoName(), null, null, null, 1);
    }

    @Override
    public void getLabels() {
        labelsSubscriber = new Subscriber<List<LabelBean>>() {
            @Override
            public void onCompleted() {
                mIssueCreateView.getLabelsSuccess();
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
                mIssueCreateView.getLabelsFail();
            }

            @Override
            public void onNext(List<LabelBean> labelBeen) {
                mIssueCreateView.gettingLabels(labelBeen);
            }
        };
        issuesMethod.getLabels(labelsSubscriber, auth, mIssueCreateView.getOwner(),
                mIssueCreateView.getRepoName(), 1);
    }

    @Override
    public void createAnIssue() {
        createSubscriber = new Subscriber<IssueBean>() {
            @Override
            public void onCompleted() {
                mIssueCreateView.createIssueSuccess();
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
                mIssueCreateView.createIssueFail();
            }

            @Override
            public void onNext(IssueBean issueBean) {
                mIssueCreateView.creatingIssue(issueBean);
            }
        };
        issuesMethod.createAnIssue(createSubscriber, auth,
                mIssueCreateView.getIssueBean(),
                mIssueCreateView.getOwner(), mIssueCreateView.getRepoName());
    }

    @Override
    public void start() {
        auth = getAuth(mContext);
        issuesMethod = getMethod(IssuesMethod.class);
    }

    @Override
    public void stop() {
        unsubscribe(assigneesSubscriber, milestonesSubscriber,
                labelsSubscriber, createSubscriber, checkSubscribe);
    }
}
