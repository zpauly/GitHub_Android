package com.zpauly.githubapp.entity.response.repos;

import android.os.Parcel;
import android.os.Parcelable;

import com.zpauly.githubapp.entity.response.UserBean;

import java.util.List;

/**
 * Created by zpauly on 16/9/19.
 */
public class SingleCommitBean implements Parcelable {

    /**
     * url : https://api.github.com/repos/octocat/Hello-World/commits/6dcb09b5b57875f334f61aebed695e2e4193db5e
     * sha : 6dcb09b5b57875f334f61aebed695e2e4193db5e
     * html_url : https://github.com/octocat/Hello-World/commit/6dcb09b5b57875f334f61aebed695e2e4193db5e
     * comments_url : https://api.github.com/repos/octocat/Hello-World/commits/6dcb09b5b57875f334f61aebed695e2e4193db5e/comments
     * commit : {"url":"https://api.github.com/repos/octocat/Hello-World/git/commits/6dcb09b5b57875f334f61aebed695e2e4193db5e","author":{"name":"Monalisa Octocat","email":"support@github.com","date":"2011-04-14T16:00:49Z"},"committer":{"name":"Monalisa Octocat","email":"support@github.com","date":"2011-04-14T16:00:49Z"},"message":"Fix all the bugs","tree":{"url":"https://api.github.com/repos/octocat/Hello-World/tree/6dcb09b5b57875f334f61aebed695e2e4193db5e","sha":"6dcb09b5b57875f334f61aebed695e2e4193db5e"},"comment_count":0,"verification":{"verified":true,"reason":"valid","signature":"-----BEGIN PGP MESSAGE-----\n...\n-----END PGP MESSAGE-----","payload":"tree 6dcb09b5b57875f334f61aebed695e2e4193db5e\n..."}}
     * author : {"login":"octocat","id":1,"avatar_url":"https://github.com/images/error/octocat_happy.gif","gravatar_id":"","url":"https://api.github.com/users/octocat","html_url":"https://github.com/octocat","followers_url":"https://api.github.com/users/octocat/followers","following_url":"https://api.github.com/users/octocat/following{/other_user}","gists_url":"https://api.github.com/users/octocat/gists{/gist_id}","starred_url":"https://api.github.com/users/octocat/starred{/owner}{/repo}","subscriptions_url":"https://api.github.com/users/octocat/subscriptions","organizations_url":"https://api.github.com/users/octocat/orgs","repos_url":"https://api.github.com/users/octocat/repos","events_url":"https://api.github.com/users/octocat/events{/privacy}","received_events_url":"https://api.github.com/users/octocat/received_events","type":"User","site_admin":false}
     * committer : {"login":"octocat","id":1,"avatar_url":"https://github.com/images/error/octocat_happy.gif","gravatar_id":"","url":"https://api.github.com/users/octocat","html_url":"https://github.com/octocat","followers_url":"https://api.github.com/users/octocat/followers","following_url":"https://api.github.com/users/octocat/following{/other_user}","gists_url":"https://api.github.com/users/octocat/gists{/gist_id}","starred_url":"https://api.github.com/users/octocat/starred{/owner}{/repo}","subscriptions_url":"https://api.github.com/users/octocat/subscriptions","organizations_url":"https://api.github.com/users/octocat/orgs","repos_url":"https://api.github.com/users/octocat/repos","events_url":"https://api.github.com/users/octocat/events{/privacy}","received_events_url":"https://api.github.com/users/octocat/received_events","type":"User","site_admin":false}
     * parents : [{"url":"https://api.github.com/repos/octocat/Hello-World/commits/6dcb09b5b57875f334f61aebed695e2e4193db5e","sha":"6dcb09b5b57875f334f61aebed695e2e4193db5e"}]
     * stats : {"additions":104,"deletions":4,"total":108}
     * files : [{"filename":"file1.txt","additions":10,"deletions":2,"changes":12,"status":"modified","raw_url":"https://github.com/octocat/Hello-World/raw/7ca483543807a51b6079e54ac4cc392bc29ae284/file1.txt","blob_url":"https://github.com/octocat/Hello-World/blob/7ca483543807a51b6079e54ac4cc392bc29ae284/file1.txt","patch":"@@ -29,7 +29,7 @@\n....."}]
     */

    private String url;
    private String sha;
    private String html_url;
    private String comments_url;
    /**
     * url : https://api.github.com/repos/octocat/Hello-World/git/commits/6dcb09b5b57875f334f61aebed695e2e4193db5e
     * author : {"name":"Monalisa Octocat","email":"support@github.com","date":"2011-04-14T16:00:49Z"}
     * committer : {"name":"Monalisa Octocat","email":"support@github.com","date":"2011-04-14T16:00:49Z"}
     * message : Fix all the bugs
     * tree : {"url":"https://api.github.com/repos/octocat/Hello-World/tree/6dcb09b5b57875f334f61aebed695e2e4193db5e","sha":"6dcb09b5b57875f334f61aebed695e2e4193db5e"}
     * comment_count : 0
     * verification : {"verified":true,"reason":"valid","signature":"-----BEGIN PGP MESSAGE-----\n...\n-----END PGP MESSAGE-----","payload":"tree 6dcb09b5b57875f334f61aebed695e2e4193db5e\n..."}
     */

    private CommitBean commit;
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

    private UserBean author;
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

    private UserBean committer;
    /**
     * additions : 104
     * deletions : 4
     * total : 108
     */

    private StatsBean stats;
    /**
     * url : https://api.github.com/repos/octocat/Hello-World/commits/6dcb09b5b57875f334f61aebed695e2e4193db5e
     * sha : 6dcb09b5b57875f334f61aebed695e2e4193db5e
     */

    private List<ParentsBean> parents;
    /**
     * filename : file1.txt
     * additions : 10
     * deletions : 2
     * changes : 12
     * status : modified
     * raw_url : https://github.com/octocat/Hello-World/raw/7ca483543807a51b6079e54ac4cc392bc29ae284/file1.txt
     * blob_url : https://github.com/octocat/Hello-World/blob/7ca483543807a51b6079e54ac4cc392bc29ae284/file1.txt
     * patch : @@ -29,7 +29,7 @@
     .....
     */

    private List<FileBean> files;

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

    public String getHtml_url() {
        return html_url;
    }

    public void setHtml_url(String html_url) {
        this.html_url = html_url;
    }

    public String getComments_url() {
        return comments_url;
    }

    public void setComments_url(String comments_url) {
        this.comments_url = comments_url;
    }

    public CommitBean getCommit() {
        return commit;
    }

    public void setCommit(CommitBean commit) {
        this.commit = commit;
    }

    public UserBean getAuthor() {
        return author;
    }

    public void setAuthor(UserBean author) {
        this.author = author;
    }

    public UserBean getCommitter() {
        return committer;
    }

    public void setCommitter(UserBean committer) {
        this.committer = committer;
    }

    public StatsBean getStats() {
        return stats;
    }

    public void setStats(StatsBean stats) {
        this.stats = stats;
    }

    public List<ParentsBean> getParents() {
        return parents;
    }

    public void setParents(List<ParentsBean> parents) {
        this.parents = parents;
    }

    public List<FileBean> getFiles() {
        return files;
    }

    public void setFiles(List<FileBean> files) {
        this.files = files;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.url);
        dest.writeString(this.sha);
        dest.writeString(this.html_url);
        dest.writeString(this.comments_url);
        dest.writeParcelable(this.commit, flags);
        dest.writeParcelable(this.author, flags);
        dest.writeParcelable(this.committer, flags);
        dest.writeParcelable(this.stats, flags);
        dest.writeTypedList(this.parents);
        dest.writeTypedList(this.files);
    }

    public SingleCommitBean() {
    }

    protected SingleCommitBean(Parcel in) {
        this.url = in.readString();
        this.sha = in.readString();
        this.html_url = in.readString();
        this.comments_url = in.readString();
        this.commit = in.readParcelable(CommitBean.class.getClassLoader());
        this.author = in.readParcelable(UserBean.class.getClassLoader());
        this.committer = in.readParcelable(UserBean.class.getClassLoader());
        this.stats = in.readParcelable(StatsBean.class.getClassLoader());
        this.parents = in.createTypedArrayList(ParentsBean.CREATOR);
        this.files = in.createTypedArrayList(FileBean.CREATOR);
    }

    public static final Parcelable.Creator<SingleCommitBean> CREATOR = new Parcelable.Creator<SingleCommitBean>() {
        @Override
        public SingleCommitBean createFromParcel(Parcel source) {
            return new SingleCommitBean(source);
        }

        @Override
        public SingleCommitBean[] newArray(int size) {
            return new SingleCommitBean[size];
        }
    };
}
