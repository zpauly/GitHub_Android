package com.zpauly.githubapp.presenter.issues;

import android.content.Context;

import com.zpauly.githubapp.base.NetPresenter;
import com.zpauly.githubapp.entity.response.issues.LabelBean;
import com.zpauly.githubapp.network.issues.IssuesMethod;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;

/**
 * Created by zpauly on 2016/10/13.
 */

public class LabelsPresenter extends NetPresenter implements LabelsContract.Presenter {
    private final String TAG = getClass().getName();

    private Context mContext;
    private LabelsContract.View mLabelsView;

    private String auth;
    private IssuesMethod issuesMethod;

    private Subscriber<List<LabelBean>> labelsSubscriber;

    private boolean isLoadComplete = false;

    private int pageId = 1;

    private List<LabelBean> labelList = new ArrayList<>();

    public LabelsPresenter(Context context, LabelsContract.View view) {
        mContext = context;
        mLabelsView = view;
        mLabelsView.setPresenter(this);
        start();
    }

    @Override
    public void getLabels() {
        labelsSubscriber = new Subscriber<List<LabelBean>>() {
            @Override
            public void onCompleted() {
                if (isLoadComplete) {
                    mLabelsView.gettingLabels(labelList);
                    mLabelsView.getLabelsSuccess();
                } else {
                    get();
                }
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
                labelList.clear();
                mLabelsView.getLabelsFail();
            }

            @Override
            public void onNext(List<LabelBean> labelBeen) {
                labelList.addAll(labelBeen);
                if (labelBeen.size() == 0) {
                    isLoadComplete = true;
                } else {
                    isLoadComplete = false;
                }
            }
        };
        get();
    }

    private void get() {
        issuesMethod.getLabels(labelsSubscriber, auth,
                mLabelsView.getOwner(), mLabelsView.getRepo(), pageId++);
    }

    @Override
    public void start() {
        auth = getAuth(mContext);
        issuesMethod = getMethod(IssuesMethod.class);
    }

    @Override
    public void stop() {
        unsubscribe(labelsSubscriber);
    }
}
