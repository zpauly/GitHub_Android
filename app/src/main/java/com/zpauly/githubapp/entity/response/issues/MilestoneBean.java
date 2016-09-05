package com.zpauly.githubapp.entity.response.issues;

import android.os.Parcel;
import android.os.Parcelable;

import com.zpauly.githubapp.entity.response.UserBean;

/**
 * Created by zpauly on 16/9/1.
 */
public class MilestoneBean implements Parcelable {
    /**
     * url : https://api.github.com/repos/octocat/Hello-World/milestones/1
     * html_url : https://github.com/octocat/Hello-World/milestones/v1.0
     * labels_url : https://api.github.com/repos/octocat/Hello-World/milestones/1/labels
     * id : 1002604
     * number : 1
     * state : open
     * title : v1.0
     * description : Tracking milestone for version 1.0
     * creator : {"login":"octocat","id":1,"avatar_url":"https://github.com/images/error/octocat_happy.gif","gravatar_id":"","url":"https://api.github.com/users/octocat","html_url":"https://github.com/octocat","followers_url":"https://api.github.com/users/octocat/followers","following_url":"https://api.github.com/users/octocat/following{/other_user}","gists_url":"https://api.github.com/users/octocat/gists{/gist_id}","starred_url":"https://api.github.com/users/octocat/starred{/owner}{/repo}","subscriptions_url":"https://api.github.com/users/octocat/subscriptions","organizations_url":"https://api.github.com/users/octocat/orgs","repos_url":"https://api.github.com/users/octocat/repos","events_url":"https://api.github.com/users/octocat/events{/privacy}","received_events_url":"https://api.github.com/users/octocat/received_events","type":"User","site_admin":false}
     * open_issues : 4
     * closed_issues : 8
     * created_at : 2011-04-10T20:09:31Z
     * updated_at : 2014-03-03T18:58:10Z
     * closed_at : 2013-02-12T13:22:01Z
     * due_on : 2012-10-09T23:39:01Z
     */

    private String url;
    private String html_url;
    private String labels_url;
    private int id;
    private int number;
    private String state;
    private String title;
    private String description;
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

    private UserBean creator;
    private int open_issues;
    private int closed_issues;
    private String created_at;
    private String updated_at;
    private String closed_at;
    private String due_on;

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

    public String getLabels_url() {
        return labels_url;
    }

    public void setLabels_url(String labels_url) {
        this.labels_url = labels_url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public UserBean getCreator() {
        return creator;
    }

    public void setCreator(UserBean creator) {
        this.creator = creator;
    }

    public int getOpen_issues() {
        return open_issues;
    }

    public void setOpen_issues(int open_issues) {
        this.open_issues = open_issues;
    }

    public int getClosed_issues() {
        return closed_issues;
    }

    public void setClosed_issues(int closed_issues) {
        this.closed_issues = closed_issues;
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

    public String getClosed_at() {
        return closed_at;
    }

    public void setClosed_at(String closed_at) {
        this.closed_at = closed_at;
    }

    public String getDue_on() {
        return due_on;
    }

    public void setDue_on(String due_on) {
        this.due_on = due_on;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.url);
        dest.writeString(this.html_url);
        dest.writeString(this.labels_url);
        dest.writeInt(this.id);
        dest.writeInt(this.number);
        dest.writeString(this.state);
        dest.writeString(this.title);
        dest.writeString(this.description);
        dest.writeParcelable(this.creator, flags);
        dest.writeInt(this.open_issues);
        dest.writeInt(this.closed_issues);
        dest.writeString(this.created_at);
        dest.writeString(this.updated_at);
        dest.writeString(this.closed_at);
        dest.writeString(this.due_on);
    }

    public MilestoneBean() {
    }

    protected MilestoneBean(Parcel in) {
        this.url = in.readString();
        this.html_url = in.readString();
        this.labels_url = in.readString();
        this.id = in.readInt();
        this.number = in.readInt();
        this.state = in.readString();
        this.title = in.readString();
        this.description = in.readString();
        this.creator = in.readParcelable(UserBean.class.getClassLoader());
        this.open_issues = in.readInt();
        this.closed_issues = in.readInt();
        this.created_at = in.readString();
        this.updated_at = in.readString();
        this.closed_at = in.readString();
        this.due_on = in.readString();
    }

    public static final Parcelable.Creator<MilestoneBean> CREATOR = new Parcelable.Creator<MilestoneBean>() {
        @Override
        public MilestoneBean createFromParcel(Parcel source) {
            return new MilestoneBean(source);
        }

        @Override
        public MilestoneBean[] newArray(int size) {
            return new MilestoneBean[size];
        }
    };
}
