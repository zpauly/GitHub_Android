package com.zpauly.githubapp.entity.request;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by zpauly on 16/9/8.
 */
public class CommentRequestBean implements Parcelable {
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

    public CommentRequestBean() {
    }

    protected CommentRequestBean(Parcel in) {
        this.body = in.readString();
    }

    public static final Parcelable.Creator<CommentRequestBean> CREATOR = new Parcelable.Creator<CommentRequestBean>() {
        @Override
        public CommentRequestBean createFromParcel(Parcel source) {
            return new CommentRequestBean(source);
        }

        @Override
        public CommentRequestBean[] newArray(int size) {
            return new CommentRequestBean[size];
        }
    };
}
