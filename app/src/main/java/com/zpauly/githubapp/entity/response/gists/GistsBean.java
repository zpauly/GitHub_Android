package com.zpauly.githubapp.entity.response.gists;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;
import com.zpauly.githubapp.entity.response.UserBean;

/**
 * Created by zpauly on 16-8-5.
 */

public class GistsBean implements Parcelable {

    /**
     * url : https://api.github.com/gists/aa5a315d61ae9438b18d
     * forks_url : https://api.github.com/gists/aa5a315d61ae9438b18d/forks
     * commits_url : https://api.github.com/gists/aa5a315d61ae9438b18d/commits
     * id : aa5a315d61ae9438b18d
     * description : description of gist
     * public : true
     * owner : {"login":"octocat","id":1,"avatar_url":"https://github.com/images/error/octocat_happy.gif","gravatar_id":"","url":"https://api.github.com/users/octocat","html_url":"https://github.com/octocat","followers_url":"https://api.github.com/users/octocat/followers","following_url":"https://api.github.com/users/octocat/following{/other_user}","gists_url":"https://api.github.com/users/octocat/gists{/gist_id}","starred_url":"https://api.github.com/users/octocat/starred{/owner}{/repo}","subscriptions_url":"https://api.github.com/users/octocat/subscriptions","organizations_url":"https://api.github.com/users/octocat/orgs","repos_url":"https://api.github.com/users/octocat/repos","events_url":"https://api.github.com/users/octocat/events{/privacy}","received_events_url":"https://api.github.com/users/octocat/received_events","type":"User","site_admin":false}
     * user : null
     * truncated : false
     * comments : 0
     * comments_url : https://api.github.com/gists/aa5a315d61ae9438b18d/comments/
     * html_url : https://gist.github.com/aa5a315d61ae9438b18d
     * git_pull_url : https://gist.github.com/aa5a315d61ae9438b18d.git
     * git_push_url : https://gist.github.com/aa5a315d61ae9438b18d.git
     * created_at : 2010-04-14T02:15:15Z
     * updated_at : 2011-06-20T11:34:15Z
     */

    private String url;
    private String forks_url;
    private String commits_url;
    private String id;
    private String description;
    @SerializedName("public")
    private boolean publicX;
    /**
     * login : octocat
     * id : 1
     * avatar_url : https://github.com/images/error/octocat_happy.gif
     * gravatar_id :
     * url : https://api.github.com/users/octocat
     * html_url : https://github.com/octocat
     * followers_url : https://api.github.com/users/octocat/followers
     * following_url : https://api.github.com/users/octocat/following{/other_user}
     * gists_url : https://api.github.com/users/octocat/gists{/gist_id}
     * starred_url : https://api.github.com/users/octocat/starred{/owner}{/repo}
     * subscriptions_url : https://api.github.com/users/octocat/subscriptions
     * organizations_url : https://api.github.com/users/octocat/orgs
     * repos_url : https://api.github.com/users/octocat/repos
     * events_url : https://api.github.com/users/octocat/events{/privacy}
     * received_events_url : https://api.github.com/users/octocat/received_events
     * type : User
     * site_admin : false
     */

    private UserBean owner;
    private UserBean user;
    private boolean truncated;
    private int comments;
    private String comments_url;
    private String html_url;
    private String git_pull_url;
    private String git_push_url;
    private String created_at;
    private String updated_at;
    private GistFileMapBean files;

    public void setFiles(GistFileMapBean files) {
        this.files = files;
    }

    public GistFileMapBean getFiles() {
        return files;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getForks_url() {
        return forks_url;
    }

    public void setForks_url(String forks_url) {
        this.forks_url = forks_url;
    }

    public String getCommits_url() {
        return commits_url;
    }

    public void setCommits_url(String commits_url) {
        this.commits_url = commits_url;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isPublicX() {
        return publicX;
    }

    public void setPublicX(boolean publicX) {
        this.publicX = publicX;
    }

    public UserBean getOwner() {
        return owner;
    }

    public void setOwner(UserBean owner) {
        this.owner = owner;
    }

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }

    public boolean isTruncated() {
        return truncated;
    }

    public void setTruncated(boolean truncated) {
        this.truncated = truncated;
    }

    public int getComments() {
        return comments;
    }

    public void setComments(int comments) {
        this.comments = comments;
    }

    public String getComments_url() {
        return comments_url;
    }

    public void setComments_url(String comments_url) {
        this.comments_url = comments_url;
    }

    public String getHtml_url() {
        return html_url;
    }

    public void setHtml_url(String html_url) {
        this.html_url = html_url;
    }

    public String getGit_pull_url() {
        return git_pull_url;
    }

    public void setGit_pull_url(String git_pull_url) {
        this.git_pull_url = git_pull_url;
    }

    public String getGit_push_url() {
        return git_push_url;
    }

    public void setGit_push_url(String git_push_url) {
        this.git_push_url = git_push_url;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.url);
        dest.writeString(this.forks_url);
        dest.writeString(this.commits_url);
        dest.writeString(this.id);
        dest.writeString(this.description);
        dest.writeByte(this.publicX ? (byte) 1 : (byte) 0);
        dest.writeParcelable(this.owner, flags);
        dest.writeParcelable(this.user, flags);
        dest.writeByte(this.truncated ? (byte) 1 : (byte) 0);
        dest.writeInt(this.comments);
        dest.writeString(this.comments_url);
        dest.writeString(this.html_url);
        dest.writeString(this.git_pull_url);
        dest.writeString(this.git_push_url);
        dest.writeString(this.created_at);
        dest.writeString(this.updated_at);
        dest.writeParcelable(this.files, flags);
    }

    public GistsBean() {
    }

    protected GistsBean(Parcel in) {
        this.url = in.readString();
        this.forks_url = in.readString();
        this.commits_url = in.readString();
        this.id = in.readString();
        this.description = in.readString();
        this.publicX = in.readByte() != 0;
        this.owner = in.readParcelable(UserBean.class.getClassLoader());
        this.user = in.readParcelable(Object.class.getClassLoader());
        this.truncated = in.readByte() != 0;
        this.comments = in.readInt();
        this.comments_url = in.readString();
        this.html_url = in.readString();
        this.git_pull_url = in.readString();
        this.git_push_url = in.readString();
        this.created_at = in.readString();
        this.updated_at = in.readString();
        this.files = in.readParcelable(GistFileMapBean.class.getClassLoader());
    }

    public static final Creator<GistsBean> CREATOR = new Creator<GistsBean>() {
        @Override
        public GistsBean createFromParcel(Parcel source) {
            return new GistsBean(source);
        }

        @Override
        public GistsBean[] newArray(int size) {
            return new GistsBean[size];
        }
    };
}
