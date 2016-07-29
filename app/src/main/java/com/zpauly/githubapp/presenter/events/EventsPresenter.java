package com.zpauly.githubapp.presenter.events;

import android.content.Context;

import com.zpauly.githubapp.Constants;
import com.zpauly.githubapp.entity.response.events.EventsBean;
import com.zpauly.githubapp.network.activity.ActivityMethod;
import com.zpauly.githubapp.utils.SPUtil;

import java.util.List;

import rx.Subscriber;

/**
 * Created by zpauly on 16-7-20.
 */

public class EventsPresenter implements EventsContract.Presenter {
    private final String TAG = getClass().getName();

    private Context mContext;
    private EventsContract.View mEventsView;

    private String auth;
    private Subscriber<List<EventsBean>> mEventsSubscriber;
    private ActivityMethod activityMethod;

    private int pageId = 1;

    public EventsPresenter(Context context, EventsContract.View view) {
        mContext = context;
        mEventsView = view;
        view.setPresenter(this);
        start();
    }

    public void setPageId(int pageId) {
        this.pageId = pageId;
    }

    @Override
    public void start() {
        auth = SPUtil.getString(mContext, Constants.USER_INFO, Constants.USER_AUTH, null);
        activityMethod = ActivityMethod.getInstance();
    }

    @Override
    public void stop() {
        if (mEventsSubscriber != null) {
            if (mEventsSubscriber.isUnsubscribed()) {
                mEventsSubscriber.unsubscribe();
            }
        }
    }

    @Override
    public void getUserEvents() {
        String username = SPUtil.getString(mContext, Constants.USER_INFO, Constants.USER_USERNAME, null);
        mEventsSubscriber = new Subscriber<List<EventsBean>>() {
            @Override
            public void onCompleted() {
                pageId ++;
                mEventsView.loadSuccess();
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
                mEventsView.loadFail();
            }

            @Override
            public void onNext(List<EventsBean> eventsBeen) {
                mEventsView.loadEvents(eventsBeen);
            }
        };
        if (mEventsView.getUsername() != null) {
            activityMethod.getUserEvents(mEventsSubscriber, null, mEventsView.getUsername(), pageId);
        } else {
            activityMethod.getUserEvents(mEventsSubscriber, auth, username, pageId);
        }
    }

    @Override
    public void getReceivedEvents() {
        String username = SPUtil.getString(mContext, Constants.USER_INFO, Constants.USER_USERNAME, null);
        mEventsSubscriber = new Subscriber<List<EventsBean>>() {
            @Override
            public void onCompleted() {
                pageId ++;
                mEventsView.loadSuccess();
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
                mEventsView.loadFail();
            }

            @Override
            public void onNext(List<EventsBean> eventsBeen) {
                mEventsView.loadEvents(eventsBeen);
            }
        };
        activityMethod.getReceivedEvents(mEventsSubscriber, auth, username, pageId);
    }
}
