package com.zpauly.githubapp.entity.response.events;

import com.google.gson.annotations.SerializedName;

/**
 * Created by zpauly on 16-7-19.
 */

public class EventsBean {
    /**
     * type : Event
     * public : true
     * payload : {}
     * repo : {"id":3,"name":"octocat/Hello-World","url":"https://api.github.com/repos/octocat/Hello-World"}
     * actor : {"id":1,"login":"octocat","gravatar_id":"","avatar_url":"https://github.com/images/error/octocat_happy.gif","url":"https://api.github.com/users/octocat"}
     * org : {"id":1,"login":"github","gravatar_id":"","url":"https://api.github.com/orgs/github","avatar_url":"https://github.com/images/error/octocat_happy.gif"}
     * created_at : 2011-09-06T17:26:27Z
     * id : 12345
     */

    private String type;
    @SerializedName("public")
    private boolean publicX;
    private Payload payload;
    /**
     * id : 3
     * name : octocat/Hello-World
     * url : https://api.github.com/repos/octocat/Hello-World
     */

    private RepoBean repo;
    /**
     * id : 1
     * login : octocat
     * gravatar_id :
     * avatar_url : https://github.com/images/error/octocat_happy.gif
     * url : https://api.github.com/users/octocat
     */

    private ActorBean actor;
    /**
     * id : 1
     * login : github
     * gravatar_id :
     * url : https://api.github.com/orgs/github
     * avatar_url : https://github.com/images/error/octocat_happy.gif
     */

    private OrgBean org;
    private String created_at;
    private String id;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isPublicX() {
        return publicX;
    }

    public void setPublicX(boolean publicX) {
        this.publicX = publicX;
    }

    public Payload getPayload() {
        return payload;
    }

    public void setPayload(Payload payload) {
        this.payload = payload;
    }

    public RepoBean getRepo() {
        return repo;
    }

    public void setRepo(RepoBean repo) {
        this.repo = repo;
    }

    public ActorBean getActor() {
        return actor;
    }

    public void setActor(ActorBean actor) {
        this.actor = actor;
    }

    public OrgBean getOrg() {
        return org;
    }

    public void setOrg(OrgBean org) {
        this.org = org;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public static class RepoBean {
        private int id;
        private String name;
        private String url;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }

    public static class ActorBean {
        private int id;
        private String login;
        private String gravatar_id;
        private String avatar_url;
        private String url;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getLogin() {
            return login;
        }

        public void setLogin(String login) {
            this.login = login;
        }

        public String getGravatar_id() {
            return gravatar_id;
        }

        public void setGravatar_id(String gravatar_id) {
            this.gravatar_id = gravatar_id;
        }

        public String getAvatar_url() {
            return avatar_url;
        }

        public void setAvatar_url(String avatar_url) {
            this.avatar_url = avatar_url;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }

    public static class OrgBean {
        private int id;
        private String login;
        private String gravatar_id;
        private String url;
        private String avatar_url;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getLogin() {
            return login;
        }

        public void setLogin(String login) {
            this.login = login;
        }

        public String getGravatar_id() {
            return gravatar_id;
        }

        public void setGravatar_id(String gravatar_id) {
            this.gravatar_id = gravatar_id;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getAvatar_url() {
            return avatar_url;
        }

        public void setAvatar_url(String avatar_url) {
            this.avatar_url = avatar_url;
        }
    }
}
