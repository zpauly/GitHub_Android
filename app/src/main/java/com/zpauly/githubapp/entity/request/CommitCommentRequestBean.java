package com.zpauly.githubapp.entity.request;

/**
 * Created by zpauly on 16/9/22.
 */

public class CommitCommentRequestBean {
    /**
     * body : Great stuff
     * path : file1.txt
     * position : 4
     * line : null
     */

    private String body;
    private String path;
    private int position;
    private Object line;

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public Object getLine() {
        return line;
    }

    public void setLine(Object line) {
        this.line = line;
    }
}
