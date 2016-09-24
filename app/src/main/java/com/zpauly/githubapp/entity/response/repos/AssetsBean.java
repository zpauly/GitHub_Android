package com.zpauly.githubapp.entity.response.repos;

import com.zpauly.githubapp.entity.response.UserBean;

/**
 * Created by zpauly on 16/9/24.
 */


public class AssetsBean {
    private String url;
    private String browser_download_url;
    private int id;
    private String name;
    private String label;
    private String state;
    private String content_type;
    private int size;
    private int download_count;
    private String created_at;
    private String updated_at;
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

    private UserBean uploader;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getBrowser_download_url() {
        return browser_download_url;
    }

    public void setBrowser_download_url(String browser_download_url) {
        this.browser_download_url = browser_download_url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getContent_type() {
        return content_type;
    }

    public void setContent_type(String content_type) {
        this.content_type = content_type;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getDownload_count() {
        return download_count;
    }

    public void setDownload_count(int download_count) {
        this.download_count = download_count;
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

    public UserBean getUploader() {
        return uploader;
    }

    public void setUploader(UserBean uploader) {
        this.uploader = uploader;
    }
}


