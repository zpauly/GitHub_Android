package com.zpauly.githubapp.entity.request;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by zpauly on 16/9/8.
 */
public class IssueCommentRequestBean implements Parcelable {
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

    public IssueCommentRequestBean() {
    }

    protected IssueCommentRequestBean(Parcel in) {
        this.body = in.readString();
    }

    public static final Parcelable.Creator<IssueCommentRequestBean> CREATOR = new Parcelable.Creator<IssueCommentRequestBean>() {
        @Override
        public IssueCommentRequestBean createFromParcel(Parcel source) {
            return new IssueCommentRequestBean(source);
        }

        @Override
        public IssueCommentRequestBean[] newArray(int size) {
            return new IssueCommentRequestBean[size];
        }
    };
}
