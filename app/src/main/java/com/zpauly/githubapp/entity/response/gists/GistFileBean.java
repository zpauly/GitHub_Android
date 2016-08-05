package com.zpauly.githubapp.entity.response.gists;

/**
 * Created by zpauly on 16-8-5.
 */

public class GistFileBean {
    /**
     * size : 932
     * raw_url : https://gist.githubusercontent.com/raw/365370/8c4d2d43d178df44f4c03a7f2ac0ff512853564e/ring.erl
     * type : text/plain
     * truncated : false
     * language : Erlang
     */

    private int size;
    private String raw_url;
    private String type;
    private boolean truncated;
    private String filename;
    private String language;

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getFilename() {
        return filename;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getRaw_url() {
        return raw_url;
    }

    public void setRaw_url(String raw_url) {
        this.raw_url = raw_url;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isTruncated() {
        return truncated;
    }

    public void setTruncated(boolean truncated) {
        this.truncated = truncated;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}
