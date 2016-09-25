package com.zpauly.githubapp.entity.response.repos;

import android.os.Parcel;
import android.os.Parcelable;

import com.zpauly.githubapp.entity.response.UserBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zpauly on 16/9/24.
 */

public class ReleaseBean implements Parcelable {

    /**
     * url : https://api.github.com/repos/octocat/Hello-World/releases/1
     * html_url : https://github.com/octocat/Hello-World/releases/v1.0.0
     * assets_url : https://api.github.com/repos/octocat/Hello-World/releases/1/assets
     * upload_url : https://uploads.github.com/repos/octocat/Hello-World/releases/1/assets{?name,label}
     * tarball_url : https://api.github.com/repos/octocat/Hello-World/tarball/v1.0.0
     * zipball_url : https://api.github.com/repos/octocat/Hello-World/zipball/v1.0.0
     * id : 1
     * tag_name : v1.0.0
     * target_commitish : master
     * name : v1.0.0
     * body : Description of the release
     * draft : false
     * prerelease : false
     * created_at : 2013-02-27T19:35:32Z
     * published_at : 2013-02-27T19:35:32Z
     * author : {"login":"octocat","id":1,"avatar_url":"https://github.com/images/error/octocat_happy.gif","gravatar_id":"","url":"https://api.github.com/users/octocat","html_url":"https://github.com/octocat","followers_url":"https://api.github.com/users/octocat/followers","following_url":"https://api.github.com/users/octocat/following{/other_user}","gists_url":"https://api.github.com/users/octocat/gists{/gist_id}","starred_url":"https://api.github.com/users/octocat/starred{/owner}{/repo}","subscriptions_url":"https://api.github.com/users/octocat/subscriptions","organizations_url":"https://api.github.com/users/octocat/orgs","repos_url":"https://api.github.com/users/octocat/repos","events_url":"https://api.github.com/users/octocat/events{/privacy}","received_events_url":"https://api.github.com/users/octocat/received_events","type":"User","site_admin":false}
     * assets : [{"url":"https://api.github.com/repos/octocat/Hello-World/releases/assets/1","browser_download_url":"https://github.com/octocat/Hello-World/releases/download/v1.0.0/example.zip","id":1,"name":"example.zip","label":"short description","state":"uploaded","content_type":"application/zip","size":1024,"download_count":42,"created_at":"2013-02-27T19:35:32Z","updated_at":"2013-02-27T19:35:32Z","uploader":{"login":"octocat","id":1,"avatar_url":"https://github.com/images/error/octocat_happy.gif","gravatar_id":"","url":"https://api.github.com/users/octocat","html_url":"https://github.com/octocat","followers_url":"https://api.github.com/users/octocat/followers","following_url":"https://api.github.com/users/octocat/following{/other_user}","gists_url":"https://api.github.com/users/octocat/gists{/gist_id}","starred_url":"https://api.github.com/users/octocat/starred{/owner}{/repo}","subscriptions_url":"https://api.github.com/users/octocat/subscriptions","organizations_url":"https://api.github.com/users/octocat/orgs","repos_url":"https://api.github.com/users/octocat/repos","events_url":"https://api.github.com/users/octocat/events{/privacy}","received_events_url":"https://api.github.com/users/octocat/received_events","type":"User","site_admin":false}}]
     */

    private String url;
    private String html_url;
    private String assets_url;
    private String upload_url;
    private String tarball_url;
    private String zipball_url;
    private int id;
    private String tag_name;
    private String target_commitish;
    private String name;
    private String body;
    private boolean draft;
    private boolean prerelease;
    private String created_at;
    private String published_at;
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
     * url : https://api.github.com/repos/octocat/Hello-World/releases/assets/1
     * browser_download_url : https://github.com/octocat/Hello-World/releases/download/v1.0.0/example.zip
     * id : 1
     * name : example.zip
     * label : short description
     * state : uploaded
     * content_type : application/zip
     * size : 1024
     * download_count : 42
     * created_at : 2013-02-27T19:35:32Z
     * updated_at : 2013-02-27T19:35:32Z
     * uploader : {"login":"octocat","id":1,"avatar_url":"https://github.com/images/error/octocat_happy.gif","gravatar_id":"","url":"https://api.github.com/users/octocat","html_url":"https://github.com/octocat","followers_url":"https://api.github.com/users/octocat/followers","following_url":"https://api.github.com/users/octocat/following{/other_user}","gists_url":"https://api.github.com/users/octocat/gists{/gist_id}","starred_url":"https://api.github.com/users/octocat/starred{/owner}{/repo}","subscriptions_url":"https://api.github.com/users/octocat/subscriptions","organizations_url":"https://api.github.com/users/octocat/orgs","repos_url":"https://api.github.com/users/octocat/repos","events_url":"https://api.github.com/users/octocat/events{/privacy}","received_events_url":"https://api.github.com/users/octocat/received_events","type":"User","site_admin":false}
     */

    private List<AssetsBean> assets;

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

    public String getAssets_url() {
        return assets_url;
    }

    public void setAssets_url(String assets_url) {
        this.assets_url = assets_url;
    }

    public String getUpload_url() {
        return upload_url;
    }

    public void setUpload_url(String upload_url) {
        this.upload_url = upload_url;
    }

    public String getTarball_url() {
        return tarball_url;
    }

    public void setTarball_url(String tarball_url) {
        this.tarball_url = tarball_url;
    }

    public String getZipball_url() {
        return zipball_url;
    }

    public void setZipball_url(String zipball_url) {
        this.zipball_url = zipball_url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTag_name() {
        return tag_name;
    }

    public void setTag_name(String tag_name) {
        this.tag_name = tag_name;
    }

    public String getTarget_commitish() {
        return target_commitish;
    }

    public void setTarget_commitish(String target_commitish) {
        this.target_commitish = target_commitish;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public boolean isDraft() {
        return draft;
    }

    public void setDraft(boolean draft) {
        this.draft = draft;
    }

    public boolean isPrerelease() {
        return prerelease;
    }

    public void setPrerelease(boolean prerelease) {
        this.prerelease = prerelease;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getPublished_at() {
        return published_at;
    }

    public void setPublished_at(String published_at) {
        this.published_at = published_at;
    }

    public UserBean getAuthor() {
        return author;
    }

    public void setAuthor(UserBean author) {
        this.author = author;
    }

    public List<AssetsBean> getAssets() {
        return assets;
    }

    public void setAssets(List<AssetsBean> assets) {
        this.assets = assets;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.url);
        dest.writeString(this.html_url);
        dest.writeString(this.assets_url);
        dest.writeString(this.upload_url);
        dest.writeString(this.tarball_url);
        dest.writeString(this.zipball_url);
        dest.writeInt(this.id);
        dest.writeString(this.tag_name);
        dest.writeString(this.target_commitish);
        dest.writeString(this.name);
        dest.writeString(this.body);
        dest.writeByte(this.draft ? (byte) 1 : (byte) 0);
        dest.writeByte(this.prerelease ? (byte) 1 : (byte) 0);
        dest.writeString(this.created_at);
        dest.writeString(this.published_at);
        dest.writeParcelable(this.author, flags);
        dest.writeList(this.assets);
    }

    public ReleaseBean() {
    }

    protected ReleaseBean(Parcel in) {
        this.url = in.readString();
        this.html_url = in.readString();
        this.assets_url = in.readString();
        this.upload_url = in.readString();
        this.tarball_url = in.readString();
        this.zipball_url = in.readString();
        this.id = in.readInt();
        this.tag_name = in.readString();
        this.target_commitish = in.readString();
        this.name = in.readString();
        this.body = in.readString();
        this.draft = in.readByte() != 0;
        this.prerelease = in.readByte() != 0;
        this.created_at = in.readString();
        this.published_at = in.readString();
        this.author = in.readParcelable(UserBean.class.getClassLoader());
        this.assets = new ArrayList<AssetsBean>();
        in.readList(this.assets, AssetsBean.class.getClassLoader());
    }

    public static final Parcelable.Creator<ReleaseBean> CREATOR = new Parcelable.Creator<ReleaseBean>() {
        @Override
        public ReleaseBean createFromParcel(Parcel source) {
            return new ReleaseBean(source);
        }

        @Override
        public ReleaseBean[] newArray(int size) {
            return new ReleaseBean[size];
        }
    };
}
