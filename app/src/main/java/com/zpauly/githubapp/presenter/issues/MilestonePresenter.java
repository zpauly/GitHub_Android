package com.zpauly.githubapp.presenter.issues;

import android.content.Context;

import com.zpauly.githubapp.base.NetPresenter;
import com.zpauly.githubapp.entity.response.issues.MilestoneBean;
import com.zpauly.githubapp.network.issues.IssuesMethod;

import java.util.List;

import rx.Subscriber;

/**
 * Created by zpauly on 2016/10/11.
 */

public class MilestonePresenter extends NetPresenter implements MilestoneContract.Presenter {
    private final String TAG = getClass().getName();

    private Context mContext;
    private MilestoneContract.View mMilestoneView;

    private String auth;
    private IssuesMethod issuesMethod;

    private Subscriber<List<MilestoneBean>> milestonesSubscriber;

    private int pageId = 1;

    public MilestonePresenter(Context context, MilestoneContract.View view) {
        mContext = context;
        mMilestoneView = view;
        mMilestoneView.setPresenter(this);
        start();
    }

    @Override
    public void setPageId(int pageId) {
        this.pageId = pageId;
    }

    @Override
    public void getMilestones() {
        milestonesSubscriber = new Subscriber<List<MilestoneBean>>() {
            @Override
            public void onCompleted() {
                mMilestoneView.getMilestonesSuccess();
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
            }

            @Override
            public void onNext(List<MilestoneBean> milestoneBeen) {
                mMilestoneView.gettingMilestones(milestoneBeen);
            }
        };
        issuesMethod.getMilestones(milestonesSubscriber, auth,
                mMilestoneView.getOwner(), mMilestoneView.getRepo(),
                mMilestoneView.getState(), mMilestoneView.getSort(), mMilestoneView.getDirection(),
                pageId++);
    }

    @Override
    public void start() {
        auth = getAuth(mContext);
        issuesMethod = getMethod(IssuesMethod.class);
    }

    @Override
    public void stop() {
        unsubscribe(milestonesSubscriber);
    }
}
