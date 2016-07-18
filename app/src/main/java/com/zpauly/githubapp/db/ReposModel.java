package com.zpauly.githubapp.db;

import com.google.gson.annotations.SerializedName;

import org.litepal.crud.DataSupport;

/**
 * Created by zpauly on 16-7-18.
 */

public class ReposModel extends DataSupport {
    private String name;
    private String full_name;
    private String description;
    @SerializedName("private")
    private boolean privateX;
    private boolean fork;
    private String url;
    private String html_url;
    private String contributors_url;
    private String deployments_url;
    private String downloads_url;
    private String events_url;
    private String forks_url;
    private String hooks_url;
    private String lanauages_url;
    private String merges_url;
    private String stargazers_url;
    private String subscribers_url;
    private String subscription_url;
    private String tags_url;
    private String teams_url;
    private String language;
    private int forks_count;
    private int stargazers_count;
    private int watchers_count;
    private int size;
    private String default_branch;
    private int open_issue_count;
    private boolean has_issues;
    private boolean has_wiki;
    private boolean has_pages;
    private boolean has_downloads;
    private String pushed_at;
    private String created_at;
    private String updated_at;

    public void setName(String name) {
        this.name = name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrivateX(boolean privateX) {
        this.privateX = privateX;
    }

    public void setFork(boolean fork) {
        this.fork = fork;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setHtml_url(String html_url) {
        this.html_url = html_url;
    }

    public void setContributors_url(String contributors_url) {
        this.contributors_url = contributors_url;
    }

    public void setDeployments_url(String deployments_url) {
        this.deployments_url = deployments_url;
    }

    public void setDownloads_url(String downloads_url) {
        this.downloads_url = downloads_url;
    }

    public void setEvents_url(String events_url) {
        this.events_url = events_url;
    }

    public void setForks_url(String forks_url) {
        this.forks_url = forks_url;
    }

    public void setHooks_url(String hooks_url) {
        this.hooks_url = hooks_url;
    }

    public void setLanauages_url(String lanauages_url) {
        this.lanauages_url = lanauages_url;
    }

    public void setMerges_url(String merges_url) {
        this.merges_url = merges_url;
    }

    public void setStargazers_url(String stargazers_url) {
        this.stargazers_url = stargazers_url;
    }

    public void setSubscribers_url(String subscribers_url) {
        this.subscribers_url = subscribers_url;
    }

    public void setSubscription_url(String subscription_url) {
        this.subscription_url = subscription_url;
    }

    public void setTags_url(String tags_url) {
        this.tags_url = tags_url;
    }

    public void setTeams_url(String teams_url) {
        this.teams_url = teams_url;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public void setForks_count(int forks_count) {
        this.forks_count = forks_count;
    }

    public void setStargazers_count(int stargazers_count) {
        this.stargazers_count = stargazers_count;
    }

    public void setWatchers_count(int watchers_count) {
        this.watchers_count = watchers_count;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setDefault_branch(String default_branch) {
        this.default_branch = default_branch;
    }

    public void setOpen_issue_count(int open_issue_count) {
        this.open_issue_count = open_issue_count;
    }

    public void setHas_issues(boolean has_issues) {
        this.has_issues = has_issues;
    }

    public void setHas_wiki(boolean has_wiki) {
        this.has_wiki = has_wiki;
    }

    public void setHas_pages(boolean has_pages) {
        this.has_pages = has_pages;
    }

    public void setHas_downloads(boolean has_downloads) {
        this.has_downloads = has_downloads;
    }

    public void setPushed_at(String pushed_at) {
        this.pushed_at = pushed_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public String getName() {
        return name;
    }

    public String getFull_name() {
        return full_name;
    }

    public String getDescription() {
        return description;
    }

    public String getUrl() {
        return url;
    }

    public String getHtml_url() {
        return html_url;
    }

    public String getContributors_url() {
        return contributors_url;
    }

    public String getDownloads_url() {
        return downloads_url;
    }

    public String getDeployments_url() {
        return deployments_url;
    }

    public String getForks_url() {
        return forks_url;
    }

    public String getEvents_url() {
        return events_url;
    }

    public String getHooks_url() {
        return hooks_url;
    }

    public String getLanauages_url() {
        return lanauages_url;
    }

    public String getMerges_url() {
        return merges_url;
    }

    public String getSubscribers_url() {
        return subscribers_url;
    }

    public String getStargazers_url() {
        return stargazers_url;
    }

    public String getSubscription_url() {
        return subscription_url;
    }

    public String getTags_url() {
        return tags_url;
    }

    public String getTeams_url() {
        return teams_url;
    }

    public String getLanguage() {
        return language;
    }

    public int getForks_count() {
        return forks_count;
    }

    public int getWatchers_count() {
        return watchers_count;
    }

    public int getStargazers_count() {
        return stargazers_count;
    }

    public int getSize() {
        return size;
    }

    public String getDefault_branch() {
        return default_branch;
    }

    public int getOpen_issue_count() {
        return open_issue_count;
    }

    public String getPushed_at() {
        return pushed_at;
    }

    public String getCreated_at() {
        return created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public boolean isPrivateX() {
        return privateX;
    }

    public boolean isFork() {
        return fork;
    }

    public boolean isHas_downloads() {
        return has_downloads;
    }

    public boolean isHas_issues() {
        return has_issues;
    }

    public boolean isHas_pages() {
        return has_pages;
    }

    public boolean isHas_wiki() {
        return has_wiki;
    }
}
