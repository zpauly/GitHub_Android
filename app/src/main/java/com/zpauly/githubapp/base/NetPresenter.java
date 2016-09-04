package com.zpauly.githubapp.base;

import android.content.Context;
import android.util.Log;

import com.zpauly.githubapp.Constants;
import com.zpauly.githubapp.network.MethodFactory;
import com.zpauly.githubapp.utils.SPUtil;

import rx.Subscriber;

/**
 * Created by zpauly on 16/9/4.
 */
public class NetPresenter {
    public <T extends BaseNetMethod> T getMethod(Class<T> clazz) {
        return (T) MethodFactory.getMethod(clazz);
    }

    public String getAuth(Context context) {
        return SPUtil.getString(context, Constants.USER_INFO, Constants.USER_AUTH, null);
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
