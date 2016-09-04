package com.zpauly.githubapp.base;

import android.content.Context;

import com.zpauly.githubapp.Constants;
import com.zpauly.githubapp.utils.SPUtil;

import rx.Subscriber;

/**
 * Created by zpauly on 16/9/4.
 */
public class NetPresenter {
    private String auth;

    public void getMethod() {

    }

    private void prepare(Context context) {
        auth = SPUtil.getString(context, Constants.USER_INFO, Constants.USER_AUTH, null);
        getMethod();
    }

    public String getAuth() {
        return auth;
    }

    public void unsubscribe(Subscriber... subscribers) {
        for (Subscriber subscriber : subscribers) {
            if (subscriber != null) {
                if (subscriber.isUnsubscribed()) {
                    subscriber.unsubscribe();
                }
            }
        }
    }
}
