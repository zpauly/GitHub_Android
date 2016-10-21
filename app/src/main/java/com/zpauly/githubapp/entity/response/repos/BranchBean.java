package com.zpauly.githubapp.entity.response.repos;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by zpauly on 2016/10/21.
 */

public class BranchBean implements Parcelable {
    /**
     * name : master
     * commit : {"sha":"7fd1a60b01f91b314f59955a4e4d4e80d8edf11d","commit":{"author":{"name":"The Octocat","date":"2012-03-06T15:06:50-08:00","email":"octocat@nowhere.com"},"url":"https://api.github.com/repos/octocat/Hello-World/git/commits/7fd1a60b01f91b314f59955a4e4d4e80d8edf11d","message":"Merge pull request #6 from Spaceghost/patch-1\n\nNew line at end of file.","tree":{"sha":"b4eecafa9be2f2006ce1b709d6857b07069b4608","url":"https://api.github.com/repos/octocat/Hello-World/git/trees/b4eecafa9be2f2006ce1b709d6857b07069b4608"},"committer":{"name":"The Octocat","date":"2012-03-06T15:06:50-08:00","email":"octocat@nowhere.com"}},"author":{"gravatar_id":"","avatar_url":"https://secure.gravatar.com/avatar/7ad39074b0584bc555d0417ae3e7d974?d=https://a248.e.akamai.net/assets.github.com%2Fimages%2Fgravatars%2Fgravatar-140.png","url":"https://api.github.com/users/octocat","id":583231,"login":"octocat"},"parents":[{"sha":"553c2077f0edc3d5dc5d17262f6aa498e69d6f8e","url":"https://api.github.com/repos/octocat/Hello-World/commits/553c2077f0edc3d5dc5d17262f6aa498e69d6f8e"},{"sha":"762941318ee16e59dabbacb1b4049eec22f0d303","url":"https://api.github.com/repos/octocat/Hello-World/commits/762941318ee16e59dabbacb1b4049eec22f0d303"}],"url":"https://api.github.com/repos/octocat/Hello-World/commits/7fd1a60b01f91b314f59955a4e4d4e80d8edf11d","committer":{"gravatar_id":"","avatar_url":"https://secure.gravatar.com/avatar/7ad39074b0584bc555d0417ae3e7d974?d=https://a248.e.akamai.net/assets.github.com%2Fimages%2Fgravatars%2Fgravatar-140.png","url":"https://api.github.com/users/octocat","id":583231,"login":"octocat"}}
     * _links : {"html":"https://github.com/octocat/Hello-World/tree/master","self":"https://api.github.com/repos/octocat/Hello-World/branches/master"}
     * protected : true
     * protection_url : https://api.github.com/repos/octocat/Hello-World/branches/master/protection
     */

    private String name;
    /**
     * sha : 7fd1a60b01f91b314f59955a4e4d4e80d8edf11d
     * commit : {"author":{"name":"The Octocat","date":"2012-03-06T15:06:50-08:00","email":"octocat@nowhere.com"},"url":"https://api.github.com/repos/octocat/Hello-World/git/commits/7fd1a60b01f91b314f59955a4e4d4e80d8edf11d","message":"Merge pull request #6 from Spaceghost/patch-1\n\nNew line at end of file.","tree":{"sha":"b4eecafa9be2f2006ce1b709d6857b07069b4608","url":"https://api.github.com/repos/octocat/Hello-World/git/trees/b4eecafa9be2f2006ce1b709d6857b07069b4608"},"committer":{"name":"The Octocat","date":"2012-03-06T15:06:50-08:00","email":"octocat@nowhere.com"}}
     * author : {"gravatar_id":"","avatar_url":"https://secure.gravatar.com/avatar/7ad39074b0584bc555d0417ae3e7d974?d=https://a248.e.akamai.net/assets.github.com%2Fimages%2Fgravatars%2Fgravatar-140.png","url":"https://api.github.com/users/octocat","id":583231,"login":"octocat"}
     * parents : [{"sha":"553c2077f0edc3d5dc5d17262f6aa498e69d6f8e","url":"https://api.github.com/repos/octocat/Hello-World/commits/553c2077f0edc3d5dc5d17262f6aa498e69d6f8e"},{"sha":"762941318ee16e59dabbacb1b4049eec22f0d303","url":"https://api.github.com/repos/octocat/Hello-World/commits/762941318ee16e59dabbacb1b4049eec22f0d303"}]
     * url : https://api.github.com/repos/octocat/Hello-World/commits/7fd1a60b01f91b314f59955a4e4d4e80d8edf11d
     * committer : {"gravatar_id":"","avatar_url":"https://secure.gravatar.com/avatar/7ad39074b0584bc555d0417ae3e7d974?d=https://a248.e.akamai.net/assets.github.com%2Fimages%2Fgravatars%2Fgravatar-140.png","url":"https://api.github.com/users/octocat","id":583231,"login":"octocat"}
     */

    private CommitBean commit;
    /**
     * html : https://github.com/octocat/Hello-World/tree/master
     * self : https://api.github.com/repos/octocat/Hello-World/branches/master
     */

    private LinksBean _links;
    @SerializedName("protected")
    private boolean protectedX;
    private String protection_url;

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

    public LinksBean get_links() {
        return _links;
    }

    public void set_links(LinksBean _links) {
        this._links = _links;
    }

    public boolean isProtectedX() {
        return protectedX;
    }

    public void setProtectedX(boolean protectedX) {
        this.protectedX = protectedX;
    }

    public String getProtection_url() {
        return protection_url;
    }

    public void setProtection_url(String protection_url) {
        this.protection_url = protection_url;
    }

    public static class LinksBean implements Parcelable {
        private String html;
        private String self;

        public String getHtml() {
            return html;
        }

        public void setHtml(String html) {
            this.html = html;
        }

        public String getSelf() {
            return self;
        }

        public void setSelf(String self) {
            this.self = self;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.html);
            dest.writeString(this.self);
        }

        public LinksBean() {
        }

        protected LinksBean(Parcel in) {
            this.html = in.readString();
            this.self = in.readString();
        }

        public static final Parcelable.Creator<LinksBean> CREATOR = new Parcelable.Creator<LinksBean>() {
            @Override
            public LinksBean createFromParcel(Parcel source) {
                return new LinksBean(source);
            }

            @Override
            public LinksBean[] newArray(int size) {
                return new LinksBean[size];
            }
        };
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeParcelable(this.commit, flags);
        dest.writeParcelable(this._links, flags);
        dest.writeByte(this.protectedX ? (byte) 1 : (byte) 0);
        dest.writeString(this.protection_url);
    }

    public BranchBean() {
    }

    protected BranchBean(Parcel in) {
        this.name = in.readString();
        this.commit = in.readParcelable(CommitBean.class.getClassLoader());
        this._links = in.readParcelable(LinksBean.class.getClassLoader());
        this.protectedX = in.readByte() != 0;
        this.protection_url = in.readString();
    }

    public static final Parcelable.Creator<BranchBean> CREATOR = new Parcelable.Creator<BranchBean>() {
        @Override
        public BranchBean createFromParcel(Parcel source) {
            return new BranchBean(source);
        }

        @Override
        public BranchBean[] newArray(int size) {
            return new BranchBean[size];
        }
    };
}
