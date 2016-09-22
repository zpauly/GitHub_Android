package com.zpauly.githubapp.presenter.commit;

import android.content.Context;

import com.zpauly.githubapp.base.NetPresenter;
import com.zpauly.githubapp.entity.response.repos.SingleCommitBean;
import com.zpauly.githubapp.network.repositories.RepositoriesMethod;

import rx.Subscriber;

/**
 * Created by zpauly on 16/9/22.
 */

public class CommitContentPresenter extends NetPresenter implements CommitContentContract.Presenter {
    private final String TAG = getClass().getName();

    private Context mContext;

    private CommitContentContract.View mCommitContentView;

    private String auth;

    private RepositoriesMethod repositoriesMethod;

    private Subscriber<SingleCommitBean> singleCommitBeanSubscriber;

    public CommitContentPresenter(Context context, CommitContentContract.View view) {
        mContext = context;
        mCommitContentView = view;
        mCommitContentView.setPresenter(this);
        start();
    }

    @Override
    public void getSingleCommit() {
        singleCommitBeanSubscriber = new Subscriber<SingleCommitBean>() {
            @Override
            public void onCompleted() {
                mCommitContentView.getCommitSuccess();
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
                mCommitContentView.getCommitFail();
            }

            @Override
            public void onNext(SingleCommitBean singleCommitBean) {
                mCommitContentView.gettingCommit(singleCommitBean);
            }
        };
        repositoriesMethod.getASingleCommit(singleCommitBeanSubscriber, auth,
                mCommitContentView.getOwner(), mCommitContentView.getRepo(),
                mCommitContentView.getSha());
    }

    @Override
    public void start() {
        auth = getAuth(mContext);
        repositoriesMethod = getMethod(RepositoriesMethod.class);
    }

    @Override
    public void stop() {
        unsubscribe(singleCommitBeanSubscriber);
    }
}
