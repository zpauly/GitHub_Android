package com.zpauly.githubapp.entity.request;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by zpauly on 16/9/8.
 */
public class CommentBean implements Parcelable {
    private String body;

    public void setBody(String body) {
        this.body = body;
    }

    public String getBody() {
        return body;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.body);
    }

    public CommentBean() {
    }

    protected CommentBean(Parcel in) {
        this.body = in.readString();
    }

    public static final Parcelable.Creator<CommentBean> CREATOR = new Parcelable.Creator<CommentBean>() {
        @Override
        public CommentBean createFromParcel(Parcel source) {
            return new CommentBean(source);
        }

        @Override
        public CommentBean[] newArray(int size) {
            return new CommentBean[size];
        }
    };
}
