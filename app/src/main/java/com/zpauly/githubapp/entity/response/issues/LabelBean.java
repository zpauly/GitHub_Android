package com.zpauly.githubapp.entity.response.issues;

/**
 * Created by zpauly on 16/9/1.
 */
public class LabelBean {
    /**
     * url : https://api.github.com/repos/octocat/Hello-World/labels/bug
     * name : bug
     * color : f29513
     */

    private String url;
    private String name;
    private String color;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
