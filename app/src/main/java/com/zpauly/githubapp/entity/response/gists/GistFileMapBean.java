package com.zpauly.githubapp.entity.response.gists;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.TreeMap;

/**
 * Created by zpauly on 16-8-5.
 */

public class GistFileMapBean extends TreeMap<String, GistFileBean> implements Parcelable {

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }

    public GistFileMapBean() {
    }

    protected GistFileMapBean(Parcel in) {
    }

    public static final Creator<GistFileMapBean> CREATOR = new Creator<GistFileMapBean>() {
        @Override
        public GistFileMapBean createFromParcel(Parcel source) {
            return new GistFileMapBean(source);
        }

        @Override
        public GistFileMapBean[] newArray(int size) {
            return new GistFileMapBean[size];
        }
    };
}
