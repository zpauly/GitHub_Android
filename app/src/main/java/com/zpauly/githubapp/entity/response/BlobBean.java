package com.zpauly.githubapp.entity.response;

/**
 * Created by zpauly on 16-8-3.
 */

public class BlobBean {
    /**
     * content : Q29udGVudCBvZiB0aGUgYmxvYg==

     * encoding : base64
     * url : https://api.github.com/repos/octocat/example/git/blobs/3a0f86fb8db8eea7ccbb9a95f325ddbedfb25e15
     * sha : 3a0f86fb8db8eea7ccbb9a95f325ddbedfb25e15
     * size : 19
     */

    private String content;
    private String encoding;
    private String url;
    private String sha;
    private int size;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getEncoding() {
        return encoding;
    }

    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSha() {
        return sha;
    }

    public void setSha(String sha) {
        this.sha = sha;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
