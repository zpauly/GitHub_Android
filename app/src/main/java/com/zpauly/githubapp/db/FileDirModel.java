package com.zpauly.githubapp.db;

import org.litepal.crud.DataSupport;

/**
 * Created by zpauly on 16-8-1.
 */

public class FileDirModel extends DataSupport {
    private String parentPath;
    private String type;
    private String path;
    private String sha;

    public void setSha(String sha) {
        this.sha = sha;
    }

    public String getSha() {
        return sha;
    }

    public void setParentPath(String parentPath) {
        this.parentPath = parentPath;
    }

    public String getParentPath() {
        return parentPath;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
