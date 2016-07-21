package com.zpauly.githubapp.db;

import com.zpauly.githubapp.entity.response.events.EventsBean;

import org.litepal.crud.DataSupport;

/**
 * Created by zpauly on 16-7-20.
 */

public class EventsModel extends DataSupport {
    private String type;

    private String actorName;
    private String avatarUrl;

    private String repoName;
    private String repoUrl;

    private String orgName;
    private String orgUrl;
    private String orgAvatarUrl;

    private String createAt;

    private EventsBean.PayloadBean payload;

    public void setPayload(EventsBean.PayloadBean payload) {
        this.payload = payload;
    }

    public EventsBean.PayloadBean getPayload() {
        return payload;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getActorName() {
        return actorName;
    }

    public void setActorName(String actorName) {
        this.actorName = actorName;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getRepoName() {
        return repoName;
    }

    public void setRepoName(String repoName) {
        this.repoName = repoName;
    }

    public String getRepoUrl() {
        return repoUrl;
    }

    public void setRepoUrl(String repoUrl) {
        this.repoUrl = repoUrl;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getOrgUrl() {
        return orgUrl;
    }

    public void setOrgUrl(String orgUrl) {
        this.orgUrl = orgUrl;
    }

    public String getOrgAvatarUrl() {
        return orgAvatarUrl;
    }

    public void setOrgAvatarUrl(String orgAvatarUrl) {
        this.orgAvatarUrl = orgAvatarUrl;
    }

    public String getCreateAt() {
        return createAt;
    }

    public void setCreateAt(String createAt) {
        this.createAt = createAt;
    }
}
