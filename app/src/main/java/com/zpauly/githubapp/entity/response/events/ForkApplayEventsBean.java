package com.zpauly.githubapp.entity.response.events;

/**
 * Created by root on 16-7-19.
 */

public class ForkApplayEventsBean extends EventsBean.PayloadBean {
    private String head;
    private String before;
    private String after;

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public String getBefore() {
        return before;
    }

    public void setBefore(String before) {
        this.before = before;
    }

    public String getAfter() {
        return after;
    }

    public void setAfter(String after) {
        this.after = after;
    }
}
