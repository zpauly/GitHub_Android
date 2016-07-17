package com.zpauly.githubapp.entity.response;

import java.util.List;

/**
 * Created by zpauly on 16-6-9.
 */
public class AppAuthorizationBean {
    /**
     * id : 1
     * url : https://api.github.com/authorizations/1
     * scopes : ["public_repo"]
     * token : abcdefgh12345678
     * token_last_eight : 12345678
     * hashed_token : 25f94a2a5c7fbaf499c665bc73d67c1c87e496da8985131633ee0a95819db2e8
     * app : {"url":"http://my-github-app.com","name":"my github app","client_id":"abcde12345fghij67890"}
     * note : optional note
     * note_url : http://optional/note/url
     * updated_at : 2011-09-06T20:39:23Z
     * created_at : 2011-09-06T17:26:27Z
     * fingerprint :
     */

    private int id;
    private String url;
    private String token;
    private String token_last_eight;
    private String hashed_token;
    /**
     * url : http://my-github-app.com
     * name : my github app
     * client_id : abcde12345fghij67890
     */

    private App app;
    private String note;
    private String note_url;
    private String updated_at;
    private String created_at;
    private String fingerprint;
    private List<String> scopes;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getToken_last_eight() {
        return token_last_eight;
    }

    public void setToken_last_eight(String token_last_eight) {
        this.token_last_eight = token_last_eight;
    }

    public String getHashed_token() {
        return hashed_token;
    }

    public void setHashed_token(String hashed_token) {
        this.hashed_token = hashed_token;
    }

    public App getApp() {
        return app;
    }

    public void setApp(App app) {
        this.app = app;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getNote_url() {
        return note_url;
    }

    public void setNote_url(String note_url) {
        this.note_url = note_url;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getFingerprint() {
        return fingerprint;
    }

    public void setFingerprint(String fingerprint) {
        this.fingerprint = fingerprint;
    }

    public List<String> getScopes() {
        return scopes;
    }

    public void setScopes(List<String> scopes) {
        this.scopes = scopes;
    }

    public static class App {
        private String url;
        private String name;
        private String client_id;

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

        public String getClient_id() {
            return client_id;
        }

        public void setClient_id(String client_id) {
            this.client_id = client_id;
        }

        @Override
        public String toString() {
            return "App{" +
                    "url='" + url + '\'' +
                    ", name='" + name + '\'' +
                    ", client_id='" + client_id + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "AppAuthorizationBean{" +
                "id=" + id +
                ", url='" + url + '\'' +
                ", token='" + token + '\'' +
                ", token_last_eight='" + token_last_eight + '\'' +
                ", hashed_token='" + hashed_token + '\'' +
                ", app=" + app +
                ", note='" + note + '\'' +
                ", note_url='" + note_url + '\'' +
                ", updated_at='" + updated_at + '\'' +
                ", created_at='" + created_at + '\'' +
                ", fingerprint='" + fingerprint + '\'' +
                ", scopes=" + scopes +
                '}';
    }
}
