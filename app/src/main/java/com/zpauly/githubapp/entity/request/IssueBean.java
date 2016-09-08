package com.zpauly.githubapp.entity.request;

import android.os.Parcel;
import android.os.Parcelable;

import com.zpauly.githubapp.entity.response.issues.AssigneeBean;

import java.util.List;

/**
 * Created by zpauly on 16/9/8.
 */
public class IssueBean implements Parcelable {
    /**
     * title : Found a bug
     * body : I'm having a problem with this.
     * assignee : octocat
     * assignees : [{"login":"octocat","id":1,"avatar_url":"https://github.com/images/error/octocat_happy.gif","gravatar_id":"","url":"https://api.github.com/users/octocat","html_url":"https://github.com/octocat","followers_url":"https://api.github.com/users/octocat/followers","following_url":"https://api.github.com/users/octocat/following{/other_user}","gists_url":"https://api.github.com/users/octocat/gists{/gist_id}","starred_url":"https://api.github.com/users/octocat/starred{/owner}{/repo}","subscriptions_url":"https://api.github.com/users/octocat/subscriptions","organizations_url":"https://api.github.com/users/octocat/orgs","repos_url":"https://api.github.com/users/octocat/repos","events_url":"https://api.github.com/users/octocat/events{/privacy}","received_events_url":"https://api.github.com/users/octocat/received_events","type":"User","site_admin":false}]
     * milestone : 1
     * labels : ["bug"]
     */

    private String title;
    private String body;
    private String assignee;
    private int milestone;
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

    private List<AssigneeBean> assignees;
    private List<String> labels;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getAssignee() {
        return assignee;
    }

    public void setAssignee(String assignee) {
        this.assignee = assignee;
    }

    public int getMilestone() {
        return milestone;
    }

    public void setMilestone(int milestone) {
        this.milestone = milestone;
    }

    public List<AssigneeBean> getAssignees() {
        return assignees;
    }

    public void setAssignees(List<AssigneeBean> assignees) {
        this.assignees = assignees;
    }

    public List<String> getLabels() {
        return labels;
    }

    public void setLabels(List<String> labels) {
        this.labels = labels;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeString(this.body);
        dest.writeString(this.assignee);
        dest.writeInt(this.milestone);
        dest.writeTypedList(this.assignees);
        dest.writeStringList(this.labels);
    }

    public IssueBean() {
    }

    protected IssueBean(Parcel in) {
        this.title = in.readString();
        this.body = in.readString();
        this.assignee = in.readString();
        this.milestone = in.readInt();
        this.assignees = in.createTypedArrayList(AssigneeBean.CREATOR);
        this.labels = in.createStringArrayList();
    }

    public static final Parcelable.Creator<IssueBean> CREATOR = new Parcelable.Creator<IssueBean>() {
        @Override
        public IssueBean createFromParcel(Parcel source) {
            return new IssueBean(source);
        }

        @Override
        public IssueBean[] newArray(int size) {
            return new IssueBean[size];
        }
    };
}
