package com.zpauly.githubapp.entity.response;

/**
 * Created by zpauly on 16-7-31.
 */

public class RepositoryContentBean {
    /**
     * type : file
     * encoding : base64
     * size : 5362
     * name : README.md
     * path : README.md
     * content : encoded content ...
     * sha : 3d21ec53a331a6f037a91c368710b99387d012c1
     * url : https://api.github.com/repos/octokit/octokit.rb/contents/README.md
     * git_url : https://api.github.com/repos/octokit/octokit.rb/git/blobs/3d21ec53a331a6f037a91c368710b99387d012c1
     * html_url : https://github.com/octokit/octokit.rb/blob/master/README.md
     * download_url : https://raw.githubusercontent.com/octokit/octokit.rb/master/README.md
     * _links : {"git":"https://api.github.com/repos/octokit/octokit.rb/git/blobs/3d21ec53a331a6f037a91c368710b99387d012c1","self":"https://api.github.com/repos/octokit/octokit.rb/contents/README.md","html":"https://github.com/octokit/octokit.rb/blob/master/README.md"}
     */

    private String type;
    private String encoding;
    private int size;
    private String name;
    private String path;
    private String content;
    private String sha;
    private String url;
    private String git_url;
    private String html_url;
    private String download_url;
    /**
     * git : https://api.github.com/repos/octokit/octokit.rb/git/blobs/3d21ec53a331a6f037a91c368710b99387d012c1
     * self : https://api.github.com/repos/octokit/octokit.rb/contents/README.md
     * html : https://github.com/octokit/octokit.rb/blob/master/README.md
     */

    private LinksBean _links;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getEncoding() {
        return encoding;
    }

    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSha() {
        return sha;
    }

    public void setSha(String sha) {
        this.sha = sha;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getGit_url() {
        return git_url;
    }

    public void setGit_url(String git_url) {
        this.git_url = git_url;
    }

    public String getHtml_url() {
        return html_url;
    }

    public void setHtml_url(String html_url) {
        this.html_url = html_url;
    }

    public String getDownload_url() {
        return download_url;
    }

    public void setDownload_url(String download_url) {
        this.download_url = download_url;
    }

    public LinksBean get_links() {
        return _links;
    }

    public void set_links(LinksBean _links) {
        this._links = _links;
    }

    public static class LinksBean {
        private String git;
        private String self;
        private String html;

        public String getGit() {
            return git;
        }

        public void setGit(String git) {
            this.git = git;
        }

        public String getSelf() {
            return self;
        }

        public void setSelf(String self) {
            this.self = self;
        }

        public String getHtml() {
            return html;
        }

        public void setHtml(String html) {
            this.html = html;
        }
    }
}
