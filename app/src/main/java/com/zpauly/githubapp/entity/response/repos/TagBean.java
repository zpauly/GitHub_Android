package com.zpauly.githubapp.entity.response.repos;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by zpauly on 2016/10/21.
 */

public class TagBean implements Parcelable {
    /**
     * name : v0.1
     * commit : {"sha":"c5b97d5ae6c19d5c5df71a34c7fbeeda2479ccbc","url":"https://api.github.com/repos/octocat/Hello-World/commits/c5b97d5ae6c19d5c5df71a34c7fbeeda2479ccbc"}
     * zipball_url : https://github.com/octocat/Hello-World/zipball/v0.1
     * tarball_url : https://github.com/octocat/Hello-World/tarball/v0.1
     */

    private String name;
    /**
     * sha : c5b97d5ae6c19d5c5df71a34c7fbeeda2479ccbc
     * url : https://api.github.com/repos/octocat/Hello-World/commits/c5b97d5ae6c19d5c5df71a34c7fbeeda2479ccbc
     */

    private CommitBean commit;
    private String zipball_url;
    private String tarball_url;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CommitBean getCommit() {
        return commit;
    }

    public void setCommit(CommitBean commit) {
        this.commit = commit;
    }

    public String getZipball_url() {
        return zipball_url;
    }

    public void setZipball_url(String zipball_url) {
        this.zipball_url = zipball_url;
    }

    public String getTarball_url() {
        return tarball_url;
    }

    public void setTarball_url(String tarball_url) {
        this.tarball_url = tarball_url;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeParcelable(this.commit, flags);
        dest.writeString(this.zipball_url);
        dest.writeString(this.tarball_url);
    }

    public TagBean() {
    }

    protected TagBean(Parcel in) {
        this.name = in.readString();
        this.commit = in.readParcelable(CommitBean.class.getClassLoader());
        this.zipball_url = in.readString();
        this.tarball_url = in.readString();
    }

    public static final Parcelable.Creator<TagBean> CREATOR = new Parcelable.Creator<TagBean>() {
        @Override
        public TagBean createFromParcel(Parcel source) {
            return new TagBean(source);
        }

        @Override
        public TagBean[] newArray(int size) {
            return new TagBean[size];
        }
    };
}
