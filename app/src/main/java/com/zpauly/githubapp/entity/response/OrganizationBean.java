package com.zpauly.githubapp.entity.response;

/**
 * Created by zpauly on 16-8-4.
 */

public class OrganizationBean {

    /**
     * login : github
     * id : 1
     * url : https://api.github.com/orgs/github
     * repos_url : https://api.github.com/orgs/github/repos
     * events_url : https://api.github.com/orgs/github/events
     * hooks_url : https://api.github.com/orgs/github/hooks
     * issues_url : https://api.github.com/orgs/github/issues
     * members_url : https://api.github.com/orgs/github/members{/member}
     * public_members_url : https://api.github.com/orgs/github/public_members{/member}
     * avatar_url : https://github.com/images/error/octocat_happy.gif
     * description : A great organization
     */

    private String login;
    private int id;
    private String url;
    private String repos_url;
    private String events_url;
    private String hooks_url;
    private String issues_url;
    private String members_url;
    private String public_members_url;
    private String avatar_url;
    private String description;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getRepos_url() {
        return repos_url;
    }

    public void setRepos_url(String repos_url) {
        this.repos_url = repos_url;
    }

    public String getEvents_url() {
        return events_url;
    }

    public void setEvents_url(String events_url) {
        this.events_url = events_url;
    }

    public String getHooks_url() {
        return hooks_url;
    }

    public void setHooks_url(String hooks_url) {
        this.hooks_url = hooks_url;
    }

    public String getIssues_url() {
        return issues_url;
    }

    public void setIssues_url(String issues_url) {
        this.issues_url = issues_url;
    }

    public String getMembers_url() {
        return members_url;
    }

    public void setMembers_url(String members_url) {
        this.members_url = members_url;
    }

    public String getPublic_members_url() {
        return public_members_url;
    }

    public void setPublic_members_url(String public_members_url) {
        this.public_members_url = public_members_url;
    }

    public String getAvatar_url() {
        return avatar_url;
    }

    public void setAvatar_url(String avatar_url) {
        this.avatar_url = avatar_url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
