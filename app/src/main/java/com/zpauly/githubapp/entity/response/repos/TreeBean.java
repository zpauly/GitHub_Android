package com.zpauly.githubapp.entity.response.repos;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by zpauly on 16/9/19.
 */
public class TreeBean implements Parcelable {
    private String url;
    private String sha;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSha() {
        return sha;
    }

    public void setSha(String sha) {
        this.sha = sha;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.url);
        dest.writeString(this.sha);
    }

    public TreeBean() {
    }

    protected TreeBean(Parcel in) {
        this.url = in.readString();
        this.sha = in.readString();
    }

    public static final Parcelable.Creator<TreeBean> CREATOR = new Parcelable.Creator<TreeBean>() {
        @Override
        public TreeBean createFromParcel(Parcel source) {
            return new TreeBean(source);
        }

        @Override
        public TreeBean[] newArray(int size) {
            return new TreeBean[size];
        }
    };
}
