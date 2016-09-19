package com.zpauly.githubapp.entity.response.repos;

import android.os.Parcel;
import android.os.Parcelable;

import com.zpauly.githubapp.entity.response.UserBean;

/**
 * Created by zpauly on 16/9/19.
 */
public class CommitBean implements Parcelable {

    /**
     * url : https://api.github.com/repos/octocat/Hello-World/git/commits/6dcb09b5b57875f334f61aebed695e2e4193db5e
     * author : {"name":"Monalisa Octocat","email":"support@github.com","date":"2011-04-14T16:00:49Z"}
     * committer : {"name":"Monalisa Octocat","email":"support@github.com","date":"2011-04-14T16:00:49Z"}
     * message : Fix all the bugs
     * tree : {"url":"https://api.github.com/repos/octocat/Hello-World/tree/6dcb09b5b57875f334f61aebed695e2e4193db5e","sha":"6dcb09b5b57875f334f61aebed695e2e4193db5e"}
     * comment_count : 0
     * verification : {"verified":true,"reason":"valid","signature":"-----BEGIN PGP MESSAGE-----\n...\n-----END PGP MESSAGE-----","payload":"tree 6dcb09b5b57875f334f61aebed695e2e4193db5e\n..."}
     */

    private String url;
    /**
     * name : Monalisa Octocat
     * email : support@github.com
     * date : 2011-04-14T16:00:49Z
     */

    private UserBean author;
    /**
     * name : Monalisa Octocat
     * email : support@github.com
     * date : 2011-04-14T16:00:49Z
     */

    private CommitterBean committer;
    private String message;
    /**
     * url : https://api.github.com/repos/octocat/Hello-World/tree/6dcb09b5b57875f334f61aebed695e2e4193db5e
     * sha : 6dcb09b5b57875f334f61aebed695e2e4193db5e
     */

    private TreeBean tree;
    private int comment_count;
    /**
     * verified : true
     * reason : valid
     * signature : -----BEGIN PGP MESSAGE-----
     ...
     -----END PGP MESSAGE-----
     * payload : tree 6dcb09b5b57875f334f61aebed695e2e4193db5e
     ...
     */

    private VerificationBean verification;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public UserBean getAuthor() {
        return author;
    }

    public void setAuthor(UserBean author) {
        this.author = author;
    }

    public CommitterBean getCommitter() {
        return committer;
    }

    public void setCommitter(CommitterBean committer) {
        this.committer = committer;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public TreeBean getTree() {
        return tree;
    }

    public void setTree(TreeBean tree) {
        this.tree = tree;
    }

    public int getComment_count() {
        return comment_count;
    }

    public void setComment_count(int comment_count) {
        this.comment_count = comment_count;
    }

    public VerificationBean getVerification() {
        return verification;
    }

    public void setVerification(VerificationBean verification) {
        this.verification = verification;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.url);
        dest.writeParcelable(this.author, flags);
        dest.writeParcelable(this.committer, flags);
        dest.writeString(this.message);
        dest.writeParcelable(this.tree, flags);
        dest.writeInt(this.comment_count);
        dest.writeParcelable(this.verification, flags);
    }

    public CommitBean() {
    }

    protected CommitBean(Parcel in) {
        this.url = in.readString();
        this.author = in.readParcelable(UserBean.class.getClassLoader());
        this.committer = in.readParcelable(CommitterBean.class.getClassLoader());
        this.message = in.readString();
        this.tree = in.readParcelable(TreeBean.class.getClassLoader());
        this.comment_count = in.readInt();
        this.verification = in.readParcelable(VerificationBean.class.getClassLoader());
    }

    public static final Parcelable.Creator<CommitBean> CREATOR = new Parcelable.Creator<CommitBean>() {
        @Override
        public CommitBean createFromParcel(Parcel source) {
            return new CommitBean(source);
        }

        @Override
        public CommitBean[] newArray(int size) {
            return new CommitBean[size];
        }
    };
}
