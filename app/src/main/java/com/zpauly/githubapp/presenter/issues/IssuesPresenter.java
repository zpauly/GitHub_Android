package com.zpauly.githubapp.presenter.issues;

import android.content.Context;

import com.zpauly.githubapp.base.NetPresenter;
import com.zpauly.githubapp.entity.response.issues.IssueBean;
import com.zpauly.githubapp.network.issues.IssuesMethod;

import java.util.List;

import rx.Subscriber;

/**
 * Created by zpauly on 16/9/4.
 */
public class IssuesPresenter extends NetPresenter implements IssuesContract.Presenter {
    private final String TAG = getClass().getName();

    private Context mContext;
    private IssuesContract.View mView;

    private String auth;
    private IssuesMethod issuesMethod;

    private Subscriber<List<IssueBean>> issuesSubscriber;

    public IssuesPresenter(Context context, IssuesContract.View view) {
        mContext = context;
        mView = view;
        view.setPresenter(this);
        start();
    }


    @Override
    public void getIssues() {
        auth = getAuth(mContext);
        issuesMethod = getMethod(IssuesMethod.class);
    }

    @Override
    public void start() {

    }

    @Override
    public void stop() {
        unsubscribe(issuesSubscriber);
    }
}
