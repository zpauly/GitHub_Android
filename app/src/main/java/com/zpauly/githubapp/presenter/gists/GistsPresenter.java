package com.zpauly.githubapp.presenter.gists;

import android.content.Context;

import com.zpauly.githubapp.Constants;
import com.zpauly.githubapp.entity.response.gists.GistsBean;
import com.zpauly.githubapp.network.gists.GistsMethod;
import com.zpauly.githubapp.utils.SPUtil;

import java.util.List;

import rx.Subscriber;

/**
 * Created by zpauly on 16-8-6.
 */

public class GistsPresenter implements GistsContract.Presenter {
    private final String TAG = getClass().getName();

    private Context mContext;
    private GistsContract.View mGistsView;

    private String auth;
    private GistsMethod method;

    private Subscriber<List<GistsBean>> gistsSubscriber;
    private int pageId = 1;

    public void setPageId(int pageId) {
        this.pageId = pageId;
    }

    public GistsPresenter(Context context, GistsContract.View view) {
        mContext = context;
        mGistsView = view;
        mGistsView.setPresenter(this);
        start();
    }

    @Override
    public void loadGists() {
        gistsSubscriber = new Subscriber<List<GistsBean>>() {
            @Override
            public void onCompleted() {
                mGistsView.loadSuccess();
                pageId ++;
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
                mGistsView.loadFail();
            }

            @Override
            public void onNext(List<GistsBean> gistsBeen) {
                mGistsView.loadingGists(gistsBeen);
            }
        };
        method.getUserGists(gistsSubscriber, auth, pageId);
    }

    @Override
    public void loadPublicGists() {
        gistsSubscriber = new Subscriber<List<GistsBean>>() {
            @Override
            public void onCompleted() {
                mGistsView.loadSuccess();
                pageId ++;
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
                mGistsView.loadFail();
            }

            @Override
            public void onNext(List<GistsBean> gistsBeen) {
                mGistsView.loadingGists(gistsBeen);
            }
        };
        method.getPublicGists(gistsSubscriber, auth, pageId);
    }

    @Override
    public void loadStarredGists() {
        gistsSubscriber = new Subscriber<List<GistsBean>>() {
            @Override
            public void onCompleted() {
                mGistsView.loadSuccess();
                pageId ++;
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
                mGistsView.loadFail();
            }

            @Override
            public void onNext(List<GistsBean> gistsBeen) {
                mGistsView.loadingGists(gistsBeen);
            }
        };
        method.getStarredGists(gistsSubscriber, auth, pageId);
    }

    @Override
    public void start() {
        auth = SPUtil.getString(mContext, Constants.USER_INFO, Constants.USER_AUTH, null);
        method = GistsMethod.getInstance();
    }

    @Override
    public void stop() {
        if (gistsSubscriber != null) {
            if (gistsSubscriber.isUnsubscribed()) {
                gistsSubscriber.unsubscribe();
            }
        }
    }
}
