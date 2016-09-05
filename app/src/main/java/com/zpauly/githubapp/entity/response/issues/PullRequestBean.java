package com.zpauly.githubapp.entity.response.issues;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by zpauly on 16/9/1.
 */
public class PullRequestBean implements Parcelable {
    /**
     * url : https://api.github.com/repos/octocat/Hello-World/pulls/1347
     * html_url : https://github.com/octocat/Hello-World/pull/1347
     * diff_url : https://github.com/octocat/Hello-World/pull/1347.diff
     * patch_url : https://github.com/octocat/Hello-World/pull/1347.patch
     */

    private String url;
    private String html_url;
    private String diff_url;
    private String patch_url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getHtml_url() {
        return html_url;
    }

    public void setHtml_url(String html_url) {
        this.html_url = html_url;
    }

    public String getDiff_url() {
        return diff_url;
    }

    public void setDiff_url(String diff_url) {
        this.diff_url = diff_url;
    }

    public String getPatch_url() {
        return patch_url;
    }

    public void setPatch_url(String patch_url) {
        this.patch_url = patch_url;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.url);
        dest.writeString(this.html_url);
        dest.writeString(this.diff_url);
        dest.writeString(this.patch_url);
    }

    public PullRequestBean() {
    }

    protected PullRequestBean(Parcel in) {
        this.url = in.readString();
        this.html_url = in.readString();
        this.diff_url = in.readString();
        this.patch_url = in.readString();
    }

    public static final Parcelable.Creator<PullRequestBean> CREATOR = new Parcelable.Creator<PullRequestBean>() {
        @Override
        public PullRequestBean createFromParcel(Parcel source) {
            return new PullRequestBean(source);
        }

        @Override
        public PullRequestBean[] newArray(int size) {
            return new PullRequestBean[size];
        }
    };
}
