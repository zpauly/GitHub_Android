package com.zpauly.githubapp.entity.request;

import java.util.List;

/**
 * Created by zpauly on 16-6-9.
 */
public class AuthorizationRequest {
    /**
     * client_secret : abcdabcdabcdabcdabcdabcdabcdabcdabcdabcd
     * scopes : ["public_repo"]
     * note : admin script
     */

    private String client_secret;
    private String note;
    private List<String> scopes;

    public String getClient_secret() {
        return client_secret;
    }

    public void setClient_secret(String client_secret) {
        this.client_secret = client_secret;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public List<String> getScopes() {
        return scopes;
    }

    public void setScopes(List<String> scopes) {
        this.scopes = scopes;
    }

    @Override
    public String toString() {
        return "AuthorizationRequest{" +
                "client_secret='" + client_secret + '\'' +
                ", note='" + note + '\'' +
                ", scopes=" + scopes +
                '}';
    }
}
