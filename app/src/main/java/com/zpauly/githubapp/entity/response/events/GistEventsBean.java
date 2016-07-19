package com.zpauly.githubapp.entity.response.events;

/**
 * Created by root on 16-7-19.
 */

public class GistEventsBean extends EventsBean.PayloadBean {
    private String action;
    private Object gist;

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public Object getGist() {
        return gist;
    }

    public void setGist(Object gist) {
        this.gist = gist;
    }
}
