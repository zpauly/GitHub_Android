package com.zpauly.githubapp.entity.response.events;

/**
 * Created by root on 16-7-19.
 */

public class MembershipEventsBean extends EventsBean.PayloadBean {
    /**
     * action : added
     * scope : team
     * member : {"login":"kdaigle","id":2501,"avatar_url":"https://avatars.githubusercontent.com/u/2501?v=3","gravatar_id":"","url":"https://api.github.com/users/kdaigle","html_url":"https://github.com/kdaigle","followers_url":"https://api.github.com/users/kdaigle/followers","following_url":"https://api.github.com/users/kdaigle/following{/other_user}","gists_url":"https://api.github.com/users/kdaigle/gists{/gist_id}","starred_url":"https://api.github.com/users/kdaigle/starred{/owner}{/repo}","subscriptions_url":"https://api.github.com/users/kdaigle/subscriptions","organizations_url":"https://api.github.com/users/kdaigle/orgs","repos_url":"https://api.github.com/users/kdaigle/repos","events_url":"https://api.github.com/users/kdaigle/events{/privacy}","received_events_url":"https://api.github.com/users/kdaigle/received_events","type":"User","site_admin":true}
     * sender : {"login":"baxterthehacker","id":6752317,"avatar_url":"https://avatars.githubusercontent.com/u/6752317?v=2","gravatar_id":"","url":"https://api.github.com/users/baxterthehacker","html_url":"https://github.com/baxterthehacker","followers_url":"https://api.github.com/users/baxterthehacker/followers","following_url":"https://api.github.com/users/baxterthehacker/following{/other_user}","gists_url":"https://api.github.com/users/baxterthehacker/gists{/gist_id}","starred_url":"https://api.github.com/users/baxterthehacker/starred{/owner}{/repo}","subscriptions_url":"https://api.github.com/users/baxterthehacker/subscriptions","organizations_url":"https://api.github.com/users/baxterthehacker/orgs","repos_url":"https://api.github.com/users/baxterthehacker/repos","events_url":"https://api.github.com/users/baxterthehacker/events{/privacy}","received_events_url":"https://api.github.com/users/baxterthehacker/received_events","type":"User","site_admin":false}
     * team : {"name":"Contractors","id":123456,"slug":"contractors","permission":"admin","url":"https://api.github.com/teams/123456","members_url":"https://api.github.com/teams/123456/members{/member}","repositories_url":"https://api.github.com/teams/123456/repos"}
     * organization : {"login":"baxterandthehackers","id":7649605,"url":"https://api.github.com/orgs/baxterandthehackers","repos_url":"https://api.github.com/orgs/baxterandthehackers/repos","events_url":"https://api.github.com/orgs/baxterandthehackers/events","members_url":"https://api.github.com/orgs/baxterandthehackers/members{/member}","public_members_url":"https://api.github.com/orgs/baxterandthehackers/public_members{/member}","avatar_url":"https://avatars.githubusercontent.com/u/7649605?v=2"}
     */

    private String action;
    private String scope;
    /**
     * login : kdaigle
     * id : 2501
     * avatar_url : https://avatars.githubusercontent.com/u/2501?v=3
     * gravatar_id :
     * url : https://api.github.com/users/kdaigle
     * html_url : https://github.com/kdaigle
     * followers_url : https://api.github.com/users/kdaigle/followers
     * following_url : https://api.github.com/users/kdaigle/following{/other_user}
     * gists_url : https://api.github.com/users/kdaigle/gists{/gist_id}
     * starred_url : https://api.github.com/users/kdaigle/starred{/owner}{/repo}
     * subscriptions_url : https://api.github.com/users/kdaigle/subscriptions
     * organizations_url : https://api.github.com/users/kdaigle/orgs
     * repos_url : https://api.github.com/users/kdaigle/repos
     * events_url : https://api.github.com/users/kdaigle/events{/privacy}
     * received_events_url : https://api.github.com/users/kdaigle/received_events
     * type : User
     * site_admin : true
     */

    private MemberBean member;
    /**
     * login : baxterthehacker
     * id : 6752317
     * avatar_url : https://avatars.githubusercontent.com/u/6752317?v=2
     * gravatar_id :
     * url : https://api.github.com/users/baxterthehacker
     * html_url : https://github.com/baxterthehacker
     * followers_url : https://api.github.com/users/baxterthehacker/followers
     * following_url : https://api.github.com/users/baxterthehacker/following{/other_user}
     * gists_url : https://api.github.com/users/baxterthehacker/gists{/gist_id}
     * starred_url : https://api.github.com/users/baxterthehacker/starred{/owner}{/repo}
     * subscriptions_url : https://api.github.com/users/baxterthehacker/subscriptions
     * organizations_url : https://api.github.com/users/baxterthehacker/orgs
     * repos_url : https://api.github.com/users/baxterthehacker/repos
     * events_url : https://api.github.com/users/baxterthehacker/events{/privacy}
     * received_events_url : https://api.github.com/users/baxterthehacker/received_events
     * type : User
     * site_admin : false
     */

    private SenderBean sender;
    /**
     * name : Contractors
     * id : 123456
     * slug : contractors
     * permission : admin
     * url : https://api.github.com/teams/123456
     * members_url : https://api.github.com/teams/123456/members{/member}
     * repositories_url : https://api.github.com/teams/123456/repos
     */

    private TeamBean team;
    /**
     * login : baxterandthehackers
     * id : 7649605
     * url : https://api.github.com/orgs/baxterandthehackers
     * repos_url : https://api.github.com/orgs/baxterandthehackers/repos
     * events_url : https://api.github.com/orgs/baxterandthehackers/events
     * members_url : https://api.github.com/orgs/baxterandthehackers/members{/member}
     * public_members_url : https://api.github.com/orgs/baxterandthehackers/public_members{/member}
     * avatar_url : https://avatars.githubusercontent.com/u/7649605?v=2
     */

    private OrganizationBean organization;

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public MemberBean getMember() {
        return member;
    }

    public void setMember(MemberBean member) {
        this.member = member;
    }

    public SenderBean getSender() {
        return sender;
    }

    public void setSender(SenderBean sender) {
        this.sender = sender;
    }

    public TeamBean getTeam() {
        return team;
    }

    public void setTeam(TeamBean team) {
        this.team = team;
    }

    public OrganizationBean getOrganization() {
        return organization;
    }

    public void setOrganization(OrganizationBean organization) {
        this.organization = organization;
    }

    public static class MemberBean {
        private String login;
        private int id;
        private String avatar_url;
        private String gravatar_id;
        private String url;
        private String html_url;
        private String followers_url;
        private String following_url;
        private String gists_url;
        private String starred_url;
        private String subscriptions_url;
        private String organizations_url;
        private String repos_url;
        private String events_url;
        private String received_events_url;
        private String type;
        private boolean site_admin;

        public String getLogin() {
            return login;
        }

        public void setLogin(String login) {
            this.login = login;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getAvatar_url() {
            return avatar_url;
        }

        public void setAvatar_url(String avatar_url) {
            this.avatar_url = avatar_url;
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

        public String getHtml_url() {
            return html_url;
        }

        public void setHtml_url(String html_url) {
            this.html_url = html_url;
        }

        public String getFollowers_url() {
            return followers_url;
        }

        public void setFollowers_url(String followers_url) {
            this.followers_url = followers_url;
        }

        public String getFollowing_url() {
            return following_url;
        }

        public void setFollowing_url(String following_url) {
            this.following_url = following_url;
        }

        public String getGists_url() {
            return gists_url;
        }

        public void setGists_url(String gists_url) {
            this.gists_url = gists_url;
        }

        public String getStarred_url() {
            return starred_url;
        }

        public void setStarred_url(String starred_url) {
            this.starred_url = starred_url;
        }

        public String getSubscriptions_url() {
            return subscriptions_url;
        }

        public void setSubscriptions_url(String subscriptions_url) {
            this.subscriptions_url = subscriptions_url;
        }

        public String getOrganizations_url() {
            return organizations_url;
        }

        public void setOrganizations_url(String organizations_url) {
            this.organizations_url = organizations_url;
        }

        public String getRepos_url() {
            return repos_url;
        }

        public void setRepos_url(String repos_url) {
            this.repos_url = repos_url;
        }

        public String getEvents_url() {
            return events_url;
        }

        public void setEvents_url(String events_url) {
            this.events_url = events_url;
        }

        public String getReceived_events_url() {
            return received_events_url;
        }

        public void setReceived_events_url(String received_events_url) {
            this.received_events_url = received_events_url;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public boolean isSite_admin() {
            return site_admin;
        }

        public void setSite_admin(boolean site_admin) {
            this.site_admin = site_admin;
        }
    }

    public static class SenderBean {
        private String login;
        private int id;
        private String avatar_url;
        private String gravatar_id;
        private String url;
        private String html_url;
        private String followers_url;
        private String following_url;
        private String gists_url;
        private String starred_url;
        private String subscriptions_url;
        private String organizations_url;
        private String repos_url;
        private String events_url;
        private String received_events_url;
        private String type;
        private boolean site_admin;

        public String getLogin() {
            return login;
        }

        public void setLogin(String login) {
            this.login = login;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getAvatar_url() {
            return avatar_url;
        }

        public void setAvatar_url(String avatar_url) {
            this.avatar_url = avatar_url;
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

        public String getHtml_url() {
            return html_url;
        }

        public void setHtml_url(String html_url) {
            this.html_url = html_url;
        }

        public String getFollowers_url() {
            return followers_url;
        }

        public void setFollowers_url(String followers_url) {
            this.followers_url = followers_url;
        }

        public String getFollowing_url() {
            return following_url;
        }

        public void setFollowing_url(String following_url) {
            this.following_url = following_url;
        }

        public String getGists_url() {
            return gists_url;
        }

        public void setGists_url(String gists_url) {
            this.gists_url = gists_url;
        }

        public String getStarred_url() {
            return starred_url;
        }

        public void setStarred_url(String starred_url) {
            this.starred_url = starred_url;
        }

        public String getSubscriptions_url() {
            return subscriptions_url;
        }

        public void setSubscriptions_url(String subscriptions_url) {
            this.subscriptions_url = subscriptions_url;
        }

        public String getOrganizations_url() {
            return organizations_url;
        }

        public void setOrganizations_url(String organizations_url) {
            this.organizations_url = organizations_url;
        }

        public String getRepos_url() {
            return repos_url;
        }

        public void setRepos_url(String repos_url) {
            this.repos_url = repos_url;
        }

        public String getEvents_url() {
            return events_url;
        }

        public void setEvents_url(String events_url) {
            this.events_url = events_url;
        }

        public String getReceived_events_url() {
            return received_events_url;
        }

        public void setReceived_events_url(String received_events_url) {
            this.received_events_url = received_events_url;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public boolean isSite_admin() {
            return site_admin;
        }

        public void setSite_admin(boolean site_admin) {
            this.site_admin = site_admin;
        }
    }

    public static class TeamBean {
        private String name;
        private int id;
        private String slug;
        private String permission;
        private String url;
        private String members_url;
        private String repositories_url;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getSlug() {
            return slug;
        }

        public void setSlug(String slug) {
            this.slug = slug;
        }

        public String getPermission() {
            return permission;
        }

        public void setPermission(String permission) {
            this.permission = permission;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getMembers_url() {
            return members_url;
        }

        public void setMembers_url(String members_url) {
            this.members_url = members_url;
        }

        public String getRepositories_url() {
            return repositories_url;
        }

        public void setRepositories_url(String repositories_url) {
            this.repositories_url = repositories_url;
        }
    }

    public static class OrganizationBean {
        private String login;
        private int id;
        private String url;
        private String repos_url;
        private String events_url;
        private String members_url;
        private String public_members_url;
        private String avatar_url;

        public String getLogin() {
            return login;
        }

        public void setLogin(String login) {
            this.login = login;
        }

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

        public String getRepos_url() {
            return repos_url;
        }

        public void setRepos_url(String repos_url) {
            this.repos_url = repos_url;
        }

        public String getEvents_url() {
            return events_url;
        }

        public void setEvents_url(String events_url) {
            this.events_url = events_url;
        }

        public String getMembers_url() {
            return members_url;
        }

        public void setMembers_url(String members_url) {
            this.members_url = members_url;
        }

        public String getPublic_members_url() {
            return public_members_url;
        }

        public void setPublic_members_url(String public_members_url) {
            this.public_members_url = public_members_url;
        }

        public String getAvatar_url() {
            return avatar_url;
        }

        public void setAvatar_url(String avatar_url) {
            this.avatar_url = avatar_url;
        }
    }
}
