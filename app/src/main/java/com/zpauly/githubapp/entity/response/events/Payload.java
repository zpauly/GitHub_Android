package com.zpauly.githubapp.entity.response.events;

import com.google.gson.annotations.SerializedName;
import com.zpauly.githubapp.entity.response.OrganizationBean;
import com.zpauly.githubapp.entity.response.RepositoriesBean;

import java.util.List;

/**
 * Created by zpauly on 16-7-23.
 */

public class Payload {
    private String action;
    private RepositoriesBean repository;
    private SenderBean sender;
    private int number;
    private PullRequestBean pull_request;
    @SerializedName("public")
    private boolean publicX;
    private OrganizationBean org;
    private String created_at;
    private String master_branch;
    private IssueBean issue;
    private CommentBean comment;
    private ReleaseBean release;
    private TeamBean team;
    private long push_id;
    private int size;
    private int distinct_size;
    private String ref_type;

    public String getMaster_branch() {
        return master_branch;
    }

    public void setMaster_branch(String master_branch) {
        this.master_branch = master_branch;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public RepositoriesBean getRepository() {
        return repository;
    }

    public void setRepository(RepositoriesBean repository) {
        this.repository = repository;
    }

    public SenderBean getSender() {
        return sender;
    }

    public void setSender(SenderBean sender) {
        this.sender = sender;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public PullRequestBean getPull_request() {
        return pull_request;
    }

    public void setPull_request(PullRequestBean pull_request) {
        this.pull_request = pull_request;
    }

    public boolean isPublicX() {
        return publicX;
    }

    public void setPublicX(boolean publicX) {
        this.publicX = publicX;
    }

    public OrganizationBean getOrg() {
        return org;
    }

    public void setOrg(OrganizationBean org) {
        this.org = org;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public IssueBean getIssue() {
        return issue;
    }

    public void setIssue(IssueBean issue) {
        this.issue = issue;
    }

    public CommentBean getComment() {
        return comment;
    }

    public void setComment(CommentBean comment) {
        this.comment = comment;
    }

    public ReleaseBean getRelease() {
        return release;
    }

    public void setRelease(ReleaseBean release) {
        this.release = release;
    }

    public TeamBean getTeam() {
        return team;
    }

    public void setTeam(TeamBean team) {
        this.team = team;
    }

    public long getPush_id() {
        return push_id;
    }

    public void setPush_id(long push_id) {
        this.push_id = push_id;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getDistinct_size() {
        return distinct_size;
    }

    public void setDistinct_size(int distinct_size) {
        this.distinct_size = distinct_size;
    }

    public String getRef_type() {
        return ref_type;
    }

    public void setRef_type(String ref_type) {
        this.ref_type = ref_type;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public String getBefore() {
        return before;
    }

    public void setBefore(String before) {
        this.before = before;
    }

    public List<CommitsBean> getCommits() {
        return commits;
    }

    public void setCommits(List<CommitsBean> commits) {
        this.commits = commits;
    }

    public RepositoriesBean getForkee() {
        return forkee;
    }

    public void setForkee(RepositoriesBean forkee) {
        this.forkee = forkee;
    }

    public SenderBean getMember() {
        return member;
    }

    public void setMember(SenderBean member) {
        this.member = member;
    }

    private String ref;
    private String head;
    private String before;
    private List<CommitsBean> commits;
    private RepositoriesBean forkee;
    private SenderBean member;

    public static class CommitsBean {

        /**
         * sha : ca69bed865bf007cce3349dd104c5af916a6b007
         * author : {"email":"zpauly1996@gmail.com","name":"zpauly"}
         * message : delete events in db
         * distinct : true
         * url : https://api.github.com/repos/zpauly/GitHub_Android/commits/ca69bed865bf007cce3349dd104c5af916a6b007
         */

        private String sha;
        /**
         * email : zpauly1996@gmail.com
         * name : zpauly
         */

        private AuthorBean author;
        private String message;
        private boolean distinct;
        private String url;

        public String getSha() {
            return sha;
        }

        public void setSha(String sha) {
            this.sha = sha;
        }

        public AuthorBean getAuthor() {
            return author;
        }

        public void setAuthor(AuthorBean author) {
            this.author = author;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public boolean isDistinct() {
            return distinct;
        }

        public void setDistinct(boolean distinct) {
            this.distinct = distinct;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public static class AuthorBean {
            private String email;
            private String name;

            public String getEmail() {
                return email;
            }

            public void setEmail(String email) {
                this.email = email;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }
    }

    public static class TeamBean {
        private String name;
        private int id;
        private String slug;
        private String description;
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

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
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

    public static class ReleaseBean {
        private String url;
        private String assets_url;
        private String upload_url;
        private String html_url;
        private int id;
        private String tag_name;
        private String target_commitish;
        private Object name;
        private boolean draft;
        /**
         * login : baxterthehacker
         * id : 6752317
         * avatar_url : https://avatars.githubusercontent.com/u/6752317?v=3
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

        private ReleaseBean.AuthorBean author;
        private boolean prerelease;
        private String created_at;
        private String published_at;
        private String tarball_url;
        private String zipball_url;
        private Object body;
        private List<?> assets;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getAssets_url() {
            return assets_url;
        }

        public void setAssets_url(String assets_url) {
            this.assets_url = assets_url;
        }

        public String getUpload_url() {
            return upload_url;
        }

        public void setUpload_url(String upload_url) {
            this.upload_url = upload_url;
        }

        public String getHtml_url() {
            return html_url;
        }

        public void setHtml_url(String html_url) {
            this.html_url = html_url;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTag_name() {
            return tag_name;
        }

        public void setTag_name(String tag_name) {
            this.tag_name = tag_name;
        }

        public String getTarget_commitish() {
            return target_commitish;
        }

        public void setTarget_commitish(String target_commitish) {
            this.target_commitish = target_commitish;
        }

        public Object getName() {
            return name;
        }

        public void setName(Object name) {
            this.name = name;
        }

        public boolean isDraft() {
            return draft;
        }

        public void setDraft(boolean draft) {
            this.draft = draft;
        }

        public ReleaseBean.AuthorBean getAuthor() {
            return author;
        }

        public void setAuthor(ReleaseBean.AuthorBean author) {
            this.author = author;
        }

        public boolean isPrerelease() {
            return prerelease;
        }

        public void setPrerelease(boolean prerelease) {
            this.prerelease = prerelease;
        }

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }

        public String getPublished_at() {
            return published_at;
        }

        public void setPublished_at(String published_at) {
            this.published_at = published_at;
        }

        public String getTarball_url() {
            return tarball_url;
        }

        public void setTarball_url(String tarball_url) {
            this.tarball_url = tarball_url;
        }

        public String getZipball_url() {
            return zipball_url;
        }

        public void setZipball_url(String zipball_url) {
            this.zipball_url = zipball_url;
        }

        public Object getBody() {
            return body;
        }

        public void setBody(Object body) {
            this.body = body;
        }

        public List<?> getAssets() {
            return assets;
        }

        public void setAssets(List<?> assets) {
            this.assets = assets;
        }

        public static class AuthorBean {
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
    }

    public static class CommentBean {
        private String url;
        private String html_url;
        private int id;
        /**
         * login : baxterthehacker
         * id : 6752317
         * avatar_url : https://avatars.githubusercontent.com/u/6752317?v=3
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

        private CommentBean.UserBean user;
        private Object position;
        private Object line;
        private Object path;
        private String commit_id;
        private String created_at;
        private String updated_at;
        private String body;

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

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public CommentBean.UserBean getUser() {
            return user;
        }

        public void setUser(CommentBean.UserBean user) {
            this.user = user;
        }

        public Object getPosition() {
            return position;
        }

        public void setPosition(Object position) {
            this.position = position;
        }

        public Object getLine() {
            return line;
        }

        public void setLine(Object line) {
            this.line = line;
        }

        public Object getPath() {
            return path;
        }

        public void setPath(Object path) {
            this.path = path;
        }

        public String getCommit_id() {
            return commit_id;
        }

        public void setCommit_id(String commit_id) {
            this.commit_id = commit_id;
        }

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }

        public String getUpdated_at() {
            return updated_at;
        }

        public void setUpdated_at(String updated_at) {
            this.updated_at = updated_at;
        }

        public String getBody() {
            return body;
        }

        public void setBody(String body) {
            this.body = body;
        }

        public static class UserBean {
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
    }

    public static class IssueBean {
        private String url;
        private String labels_url;
        private String comments_url;
        private String events_url;
        private String html_url;
        private int id;
        private int number;
        private String title;
        /**
         * login : baxterthehacker
         * id : 6752317
         * avatar_url : https://avatars.githubusercontent.com/u/6752317?v=3
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

        private IssueBean.UserBean user;
        private String state;
        private boolean locked;
        private Object assignee;
        private Object milestone;
        private int comments;
        private String created_at;
        private String updated_at;
        private Object closed_at;
        private String body;
        /**
         * url : https://api.github.com/repos/baxterthehacker/public-repo/labels/bug
         * name : bug
         * color : fc2929
         */

        private List<IssueBean.LabelsBean> labels;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getLabels_url() {
            return labels_url;
        }

        public void setLabels_url(String labels_url) {
            this.labels_url = labels_url;
        }

        public String getComments_url() {
            return comments_url;
        }

        public void setComments_url(String comments_url) {
            this.comments_url = comments_url;
        }

        public String getEvents_url() {
            return events_url;
        }

        public void setEvents_url(String events_url) {
            this.events_url = events_url;
        }

        public String getHtml_url() {
            return html_url;
        }

        public void setHtml_url(String html_url) {
            this.html_url = html_url;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getNumber() {
            return number;
        }

        public void setNumber(int number) {
            this.number = number;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public IssueBean.UserBean getUser() {
            return user;
        }

        public void setUser(IssueBean.UserBean user) {
            this.user = user;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public boolean isLocked() {
            return locked;
        }

        public void setLocked(boolean locked) {
            this.locked = locked;
        }

        public Object getAssignee() {
            return assignee;
        }

        public void setAssignee(Object assignee) {
            this.assignee = assignee;
        }

        public Object getMilestone() {
            return milestone;
        }

        public void setMilestone(Object milestone) {
            this.milestone = milestone;
        }

        public int getComments() {
            return comments;
        }

        public void setComments(int comments) {
            this.comments = comments;
        }

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }

        public String getUpdated_at() {
            return updated_at;
        }

        public void setUpdated_at(String updated_at) {
            this.updated_at = updated_at;
        }

        public Object getClosed_at() {
            return closed_at;
        }

        public void setClosed_at(Object closed_at) {
            this.closed_at = closed_at;
        }

        public String getBody() {
            return body;
        }

        public void setBody(String body) {
            this.body = body;
        }

        public List<IssueBean.LabelsBean> getLabels() {
            return labels;
        }

        public void setLabels(List<IssueBean.LabelsBean> labels) {
            this.labels = labels;
        }

        public static class UserBean {
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

        public static class LabelsBean {
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
    }

    public static class PullRequestBean {
        private String url;
        private int id;
        private String html_url;
        private String diff_url;
        private String patch_url;
        private String issue_url;
        private int number;
        private String state;
        private boolean locked;
        private String title;
        /**
         * login : baxterthehacker
         * id : 6752317
         * avatar_url : https://avatars.githubusercontent.com/u/6752317?v=3
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

        private PullRequestBean.UserBean user;
        private String body;
        private String created_at;
        private String updated_at;
        private Object closed_at;
        private Object merged_at;
        private Object merge_commit_sha;
        private Object assignee;
        private Object milestone;
        private String commits_url;
        private String review_comments_url;
        private String review_comment_url;
        private String comments_url;
        private String statuses_url;
        /**
         * label : baxterthehacker:changes
         * ref : changes
         * sha : 0d1a26e67d8f5eaf1f6ba5c57fc3c7d91ac0fd1c
         * user : {"login":"baxterthehacker","id":6752317,"avatar_url":"https://avatars.githubusercontent.com/u/6752317?v=3","gravatar_id":"","url":"https://api.github.com/users/baxterthehacker","html_url":"https://github.com/baxterthehacker","followers_url":"https://api.github.com/users/baxterthehacker/followers","following_url":"https://api.github.com/users/baxterthehacker/following{/other_user}","gists_url":"https://api.github.com/users/baxterthehacker/gists{/gist_id}","starred_url":"https://api.github.com/users/baxterthehacker/starred{/owner}{/repo}","subscriptions_url":"https://api.github.com/users/baxterthehacker/subscriptions","organizations_url":"https://api.github.com/users/baxterthehacker/orgs","repos_url":"https://api.github.com/users/baxterthehacker/repos","events_url":"https://api.github.com/users/baxterthehacker/events{/privacy}","received_events_url":"https://api.github.com/users/baxterthehacker/received_events","type":"User","site_admin":false}
         * repo : {"id":35129377,"name":"public-repo","full_name":"baxterthehacker/public-repo","owner":{"login":"baxterthehacker","id":6752317,"avatar_url":"https://avatars.githubusercontent.com/u/6752317?v=3","gravatar_id":"","url":"https://api.github.com/users/baxterthehacker","html_url":"https://github.com/baxterthehacker","followers_url":"https://api.github.com/users/baxterthehacker/followers","following_url":"https://api.github.com/users/baxterthehacker/following{/other_user}","gists_url":"https://api.github.com/users/baxterthehacker/gists{/gist_id}","starred_url":"https://api.github.com/users/baxterthehacker/starred{/owner}{/repo}","subscriptions_url":"https://api.github.com/users/baxterthehacker/subscriptions","organizations_url":"https://api.github.com/users/baxterthehacker/orgs","repos_url":"https://api.github.com/users/baxterthehacker/repos","events_url":"https://api.github.com/users/baxterthehacker/events{/privacy}","received_events_url":"https://api.github.com/users/baxterthehacker/received_events","type":"User","site_admin":false},"private":false,"html_url":"https://github.com/baxterthehacker/public-repo","description":"","fork":false,"url":"https://api.github.com/repos/baxterthehacker/public-repo","forks_url":"https://api.github.com/repos/baxterthehacker/public-repo/forks","keys_url":"https://api.github.com/repos/baxterthehacker/public-repo/keys{/key_id}","collaborators_url":"https://api.github.com/repos/baxterthehacker/public-repo/collaborators{/collaborator}","teams_url":"https://api.github.com/repos/baxterthehacker/public-repo/teams","hooks_url":"https://api.github.com/repos/baxterthehacker/public-repo/hooks","issue_events_url":"https://api.github.com/repos/baxterthehacker/public-repo/issues/events{/number}","events_url":"https://api.github.com/repos/baxterthehacker/public-repo/events","assignees_url":"https://api.github.com/repos/baxterthehacker/public-repo/assignees{/user}","branches_url":"https://api.github.com/repos/baxterthehacker/public-repo/branches{/branch}","tags_url":"https://api.github.com/repos/baxterthehacker/public-repo/tags","blobs_url":"https://api.github.com/repos/baxterthehacker/public-repo/git/blobs{/sha}","git_tags_url":"https://api.github.com/repos/baxterthehacker/public-repo/git/tags{/sha}","git_refs_url":"https://api.github.com/repos/baxterthehacker/public-repo/git/refs{/sha}","trees_url":"https://api.github.com/repos/baxterthehacker/public-repo/git/trees{/sha}","statuses_url":"https://api.github.com/repos/baxterthehacker/public-repo/statuses/{sha}","languages_url":"https://api.github.com/repos/baxterthehacker/public-repo/languages","stargazers_url":"https://api.github.com/repos/baxterthehacker/public-repo/stargazers","contributors_url":"https://api.github.com/repos/baxterthehacker/public-repo/contributors","subscribers_url":"https://api.github.com/repos/baxterthehacker/public-repo/subscribers","subscription_url":"https://api.github.com/repos/baxterthehacker/public-repo/subscription","commits_url":"https://api.github.com/repos/baxterthehacker/public-repo/commits{/sha}","git_commits_url":"https://api.github.com/repos/baxterthehacker/public-repo/git/commits{/sha}","comments_url":"https://api.github.com/repos/baxterthehacker/public-repo/comments{/number}","issue_comment_url":"https://api.github.com/repos/baxterthehacker/public-repo/issues/comments{/number}","contents_url":"https://api.github.com/repos/baxterthehacker/public-repo/contents/{+path}","compare_url":"https://api.github.com/repos/baxterthehacker/public-repo/compare/{base}...{head}","merges_url":"https://api.github.com/repos/baxterthehacker/public-repo/merges","archive_url":"https://api.github.com/repos/baxterthehacker/public-repo/{archive_format}{/ref}","downloads_url":"https://api.github.com/repos/baxterthehacker/public-repo/downloads","issues_url":"https://api.github.com/repos/baxterthehacker/public-repo/issues{/number}","pulls_url":"https://api.github.com/repos/baxterthehacker/public-repo/pulls{/number}","milestones_url":"https://api.github.com/repos/baxterthehacker/public-repo/milestones{/number}","notifications_url":"https://api.github.com/repos/baxterthehacker/public-repo/notifications{?since,all,participating}","labels_url":"https://api.github.com/repos/baxterthehacker/public-repo/labels{/name}","releases_url":"https://api.github.com/repos/baxterthehacker/public-repo/releases{/id}","created_at":"2015-05-05T23:40:12Z","updated_at":"2015-05-05T23:40:12Z","pushed_at":"2015-05-05T23:40:26Z","git_url":"git://github.com/baxterthehacker/public-repo.git","ssh_url":"git@github.com:baxterthehacker/public-repo.git","clone_url":"https://github.com/baxterthehacker/public-repo.git","svn_url":"https://github.com/baxterthehacker/public-repo","homepage":null,"size":0,"stargazers_count":0,"watchers_count":0,"language":null,"has_issues":true,"has_downloads":true,"has_wiki":true,"has_pages":true,"forks_count":0,"mirror_url":null,"open_issues_count":1,"forks":0,"open_issues":1,"watchers":0,"default_branch":"master"}
         */

        private PullRequestBean.HeadBean head;
        /**
         * label : baxterthehacker:master
         * ref : master
         * sha : 9049f1265b7d61be4a8904a9a27120d2064dab3b
         * user : {"login":"baxterthehacker","id":6752317,"avatar_url":"https://avatars.githubusercontent.com/u/6752317?v=3","gravatar_id":"","url":"https://api.github.com/users/baxterthehacker","html_url":"https://github.com/baxterthehacker","followers_url":"https://api.github.com/users/baxterthehacker/followers","following_url":"https://api.github.com/users/baxterthehacker/following{/other_user}","gists_url":"https://api.github.com/users/baxterthehacker/gists{/gist_id}","starred_url":"https://api.github.com/users/baxterthehacker/starred{/owner}{/repo}","subscriptions_url":"https://api.github.com/users/baxterthehacker/subscriptions","organizations_url":"https://api.github.com/users/baxterthehacker/orgs","repos_url":"https://api.github.com/users/baxterthehacker/repos","events_url":"https://api.github.com/users/baxterthehacker/events{/privacy}","received_events_url":"https://api.github.com/users/baxterthehacker/received_events","type":"User","site_admin":false}
         * repo : {"id":35129377,"name":"public-repo","full_name":"baxterthehacker/public-repo","owner":{"login":"baxterthehacker","id":6752317,"avatar_url":"https://avatars.githubusercontent.com/u/6752317?v=3","gravatar_id":"","url":"https://api.github.com/users/baxterthehacker","html_url":"https://github.com/baxterthehacker","followers_url":"https://api.github.com/users/baxterthehacker/followers","following_url":"https://api.github.com/users/baxterthehacker/following{/other_user}","gists_url":"https://api.github.com/users/baxterthehacker/gists{/gist_id}","starred_url":"https://api.github.com/users/baxterthehacker/starred{/owner}{/repo}","subscriptions_url":"https://api.github.com/users/baxterthehacker/subscriptions","organizations_url":"https://api.github.com/users/baxterthehacker/orgs","repos_url":"https://api.github.com/users/baxterthehacker/repos","events_url":"https://api.github.com/users/baxterthehacker/events{/privacy}","received_events_url":"https://api.github.com/users/baxterthehacker/received_events","type":"User","site_admin":false},"private":false,"html_url":"https://github.com/baxterthehacker/public-repo","description":"","fork":false,"url":"https://api.github.com/repos/baxterthehacker/public-repo","forks_url":"https://api.github.com/repos/baxterthehacker/public-repo/forks","keys_url":"https://api.github.com/repos/baxterthehacker/public-repo/keys{/key_id}","collaborators_url":"https://api.github.com/repos/baxterthehacker/public-repo/collaborators{/collaborator}","teams_url":"https://api.github.com/repos/baxterthehacker/public-repo/teams","hooks_url":"https://api.github.com/repos/baxterthehacker/public-repo/hooks","issue_events_url":"https://api.github.com/repos/baxterthehacker/public-repo/issues/events{/number}","events_url":"https://api.github.com/repos/baxterthehacker/public-repo/events","assignees_url":"https://api.github.com/repos/baxterthehacker/public-repo/assignees{/user}","branches_url":"https://api.github.com/repos/baxterthehacker/public-repo/branches{/branch}","tags_url":"https://api.github.com/repos/baxterthehacker/public-repo/tags","blobs_url":"https://api.github.com/repos/baxterthehacker/public-repo/git/blobs{/sha}","git_tags_url":"https://api.github.com/repos/baxterthehacker/public-repo/git/tags{/sha}","git_refs_url":"https://api.github.com/repos/baxterthehacker/public-repo/git/refs{/sha}","trees_url":"https://api.github.com/repos/baxterthehacker/public-repo/git/trees{/sha}","statuses_url":"https://api.github.com/repos/baxterthehacker/public-repo/statuses/{sha}","languages_url":"https://api.github.com/repos/baxterthehacker/public-repo/languages","stargazers_url":"https://api.github.com/repos/baxterthehacker/public-repo/stargazers","contributors_url":"https://api.github.com/repos/baxterthehacker/public-repo/contributors","subscribers_url":"https://api.github.com/repos/baxterthehacker/public-repo/subscribers","subscription_url":"https://api.github.com/repos/baxterthehacker/public-repo/subscription","commits_url":"https://api.github.com/repos/baxterthehacker/public-repo/commits{/sha}","git_commits_url":"https://api.github.com/repos/baxterthehacker/public-repo/git/commits{/sha}","comments_url":"https://api.github.com/repos/baxterthehacker/public-repo/comments{/number}","issue_comment_url":"https://api.github.com/repos/baxterthehacker/public-repo/issues/comments{/number}","contents_url":"https://api.github.com/repos/baxterthehacker/public-repo/contents/{+path}","compare_url":"https://api.github.com/repos/baxterthehacker/public-repo/compare/{base}...{head}","merges_url":"https://api.github.com/repos/baxterthehacker/public-repo/merges","archive_url":"https://api.github.com/repos/baxterthehacker/public-repo/{archive_format}{/ref}","downloads_url":"https://api.github.com/repos/baxterthehacker/public-repo/downloads","issues_url":"https://api.github.com/repos/baxterthehacker/public-repo/issues{/number}","pulls_url":"https://api.github.com/repos/baxterthehacker/public-repo/pulls{/number}","milestones_url":"https://api.github.com/repos/baxterthehacker/public-repo/milestones{/number}","notifications_url":"https://api.github.com/repos/baxterthehacker/public-repo/notifications{?since,all,participating}","labels_url":"https://api.github.com/repos/baxterthehacker/public-repo/labels{/name}","releases_url":"https://api.github.com/repos/baxterthehacker/public-repo/releases{/id}","created_at":"2015-05-05T23:40:12Z","updated_at":"2015-05-05T23:40:12Z","pushed_at":"2015-05-05T23:40:26Z","git_url":"git://github.com/baxterthehacker/public-repo.git","ssh_url":"git@github.com:baxterthehacker/public-repo.git","clone_url":"https://github.com/baxterthehacker/public-repo.git","svn_url":"https://github.com/baxterthehacker/public-repo","homepage":null,"size":0,"stargazers_count":0,"watchers_count":0,"language":null,"has_issues":true,"has_downloads":true,"has_wiki":true,"has_pages":true,"forks_count":0,"mirror_url":null,"open_issues_count":1,"forks":0,"open_issues":1,"watchers":0,"default_branch":"master"}
         */

        private PullRequestBean.BaseBean base;
        /**
         * self : {"href":"https://api.github.com/repos/baxterthehacker/public-repo/pulls/1"}
         * html : {"href":"https://github.com/baxterthehacker/public-repo/pull/1"}
         * issue : {"href":"https://api.github.com/repos/baxterthehacker/public-repo/issues/1"}
         * comments : {"href":"https://api.github.com/repos/baxterthehacker/public-repo/issues/1/comments"}
         * review_comments : {"href":"https://api.github.com/repos/baxterthehacker/public-repo/pulls/1/comments"}
         * review_comment : {"href":"https://api.github.com/repos/baxterthehacker/public-repo/pulls/comments{/number}"}
         * commits : {"href":"https://api.github.com/repos/baxterthehacker/public-repo/pulls/1/commits"}
         * statuses : {"href":"https://api.github.com/repos/baxterthehacker/public-repo/statuses/0d1a26e67d8f5eaf1f6ba5c57fc3c7d91ac0fd1c"}
         */

        private PullRequestBean.LinksBean _links;
        private boolean merged;
        private Object mergeable;
        private String mergeable_state;
        private Object merged_by;
        private int comments;
        private int review_comments;
        private int commits;
        private int additions;
        private int deletions;
        private int changed_files;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getHtml_url() {
            return html_url;
        }

        public void setHtml_url(String html_url) {
            this.html_url = html_url;
        }

        public String getDiff_url() {
            return diff_url;
        }

        public void setDiff_url(String diff_url) {
            this.diff_url = diff_url;
        }

        public String getPatch_url() {
            return patch_url;
        }

        public void setPatch_url(String patch_url) {
            this.patch_url = patch_url;
        }

        public String getIssue_url() {
            return issue_url;
        }

        public void setIssue_url(String issue_url) {
            this.issue_url = issue_url;
        }

        public int getNumber() {
            return number;
        }

        public void setNumber(int number) {
            this.number = number;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public boolean isLocked() {
            return locked;
        }

        public void setLocked(boolean locked) {
            this.locked = locked;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public PullRequestBean.UserBean getUser() {
            return user;
        }

        public void setUser(PullRequestBean.UserBean user) {
            this.user = user;
        }

        public String getBody() {
            return body;
        }

        public void setBody(String body) {
            this.body = body;
        }

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }

        public String getUpdated_at() {
            return updated_at;
        }

        public void setUpdated_at(String updated_at) {
            this.updated_at = updated_at;
        }

        public Object getClosed_at() {
            return closed_at;
        }

        public void setClosed_at(Object closed_at) {
            this.closed_at = closed_at;
        }

        public Object getMerged_at() {
            return merged_at;
        }

        public void setMerged_at(Object merged_at) {
            this.merged_at = merged_at;
        }

        public Object getMerge_commit_sha() {
            return merge_commit_sha;
        }

        public void setMerge_commit_sha(Object merge_commit_sha) {
            this.merge_commit_sha = merge_commit_sha;
        }

        public Object getAssignee() {
            return assignee;
        }

        public void setAssignee(Object assignee) {
            this.assignee = assignee;
        }

        public Object getMilestone() {
            return milestone;
        }

        public void setMilestone(Object milestone) {
            this.milestone = milestone;
        }

        public String getCommits_url() {
            return commits_url;
        }

        public void setCommits_url(String commits_url) {
            this.commits_url = commits_url;
        }

        public String getReview_comments_url() {
            return review_comments_url;
        }

        public void setReview_comments_url(String review_comments_url) {
            this.review_comments_url = review_comments_url;
        }

        public String getReview_comment_url() {
            return review_comment_url;
        }

        public void setReview_comment_url(String review_comment_url) {
            this.review_comment_url = review_comment_url;
        }

        public String getComments_url() {
            return comments_url;
        }

        public void setComments_url(String comments_url) {
            this.comments_url = comments_url;
        }

        public String getStatuses_url() {
            return statuses_url;
        }

        public void setStatuses_url(String statuses_url) {
            this.statuses_url = statuses_url;
        }

        public PullRequestBean.HeadBean getHead() {
            return head;
        }

        public void setHead(PullRequestBean.HeadBean head) {
            this.head = head;
        }

        public PullRequestBean.BaseBean getBase() {
            return base;
        }

        public void setBase(PullRequestBean.BaseBean base) {
            this.base = base;
        }

        public PullRequestBean.LinksBean get_links() {
            return _links;
        }

        public void set_links(PullRequestBean.LinksBean _links) {
            this._links = _links;
        }

        public boolean isMerged() {
            return merged;
        }

        public void setMerged(boolean merged) {
            this.merged = merged;
        }

        public Object getMergeable() {
            return mergeable;
        }

        public void setMergeable(Object mergeable) {
            this.mergeable = mergeable;
        }

        public String getMergeable_state() {
            return mergeable_state;
        }

        public void setMergeable_state(String mergeable_state) {
            this.mergeable_state = mergeable_state;
        }

        public Object getMerged_by() {
            return merged_by;
        }

        public void setMerged_by(Object merged_by) {
            this.merged_by = merged_by;
        }

        public int getComments() {
            return comments;
        }

        public void setComments(int comments) {
            this.comments = comments;
        }

        public int getReview_comments() {
            return review_comments;
        }

        public void setReview_comments(int review_comments) {
            this.review_comments = review_comments;
        }

        public int getCommits() {
            return commits;
        }

        public void setCommits(int commits) {
            this.commits = commits;
        }

        public int getAdditions() {
            return additions;
        }

        public void setAdditions(int additions) {
            this.additions = additions;
        }

        public int getDeletions() {
            return deletions;
        }

        public void setDeletions(int deletions) {
            this.deletions = deletions;
        }

        public int getChanged_files() {
            return changed_files;
        }

        public void setChanged_files(int changed_files) {
            this.changed_files = changed_files;
        }

        public static class UserBean {
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

        public static class HeadBean {
            private String label;
            private String ref;
            private String sha;
            /**
             * login : baxterthehacker
             * id : 6752317
             * avatar_url : https://avatars.githubusercontent.com/u/6752317?v=3
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

            private PullRequestBean.HeadBean.UserBean user;
            /**
             * id : 35129377
             * name : public-repo
             * full_name : baxterthehacker/public-repo
             * owner : {"login":"baxterthehacker","id":6752317,"avatar_url":"https://avatars.githubusercontent.com/u/6752317?v=3","gravatar_id":"","url":"https://api.github.com/users/baxterthehacker","html_url":"https://github.com/baxterthehacker","followers_url":"https://api.github.com/users/baxterthehacker/followers","following_url":"https://api.github.com/users/baxterthehacker/following{/other_user}","gists_url":"https://api.github.com/users/baxterthehacker/gists{/gist_id}","starred_url":"https://api.github.com/users/baxterthehacker/starred{/owner}{/repo}","subscriptions_url":"https://api.github.com/users/baxterthehacker/subscriptions","organizations_url":"https://api.github.com/users/baxterthehacker/orgs","repos_url":"https://api.github.com/users/baxterthehacker/repos","events_url":"https://api.github.com/users/baxterthehacker/events{/privacy}","received_events_url":"https://api.github.com/users/baxterthehacker/received_events","type":"User","site_admin":false}
             * private : false
             * html_url : https://github.com/baxterthehacker/public-repo
             * description :
             * fork : false
             * url : https://api.github.com/repos/baxterthehacker/public-repo
             * forks_url : https://api.github.com/repos/baxterthehacker/public-repo/forks
             * keys_url : https://api.github.com/repos/baxterthehacker/public-repo/keys{/key_id}
             * collaborators_url : https://api.github.com/repos/baxterthehacker/public-repo/collaborators{/collaborator}
             * teams_url : https://api.github.com/repos/baxterthehacker/public-repo/teams
             * hooks_url : https://api.github.com/repos/baxterthehacker/public-repo/hooks
             * issue_events_url : https://api.github.com/repos/baxterthehacker/public-repo/issues/events{/number}
             * events_url : https://api.github.com/repos/baxterthehacker/public-repo/events
             * assignees_url : https://api.github.com/repos/baxterthehacker/public-repo/assignees{/user}
             * branches_url : https://api.github.com/repos/baxterthehacker/public-repo/branches{/branch}
             * tags_url : https://api.github.com/repos/baxterthehacker/public-repo/tags
             * blobs_url : https://api.github.com/repos/baxterthehacker/public-repo/git/blobs{/sha}
             * git_tags_url : https://api.github.com/repos/baxterthehacker/public-repo/git/tags{/sha}
             * git_refs_url : https://api.github.com/repos/baxterthehacker/public-repo/git/refs{/sha}
             * trees_url : https://api.github.com/repos/baxterthehacker/public-repo/git/trees{/sha}
             * statuses_url : https://api.github.com/repos/baxterthehacker/public-repo/statuses/{sha}
             * languages_url : https://api.github.com/repos/baxterthehacker/public-repo/languages
             * stargazers_url : https://api.github.com/repos/baxterthehacker/public-repo/stargazers
             * contributors_url : https://api.github.com/repos/baxterthehacker/public-repo/contributors
             * subscribers_url : https://api.github.com/repos/baxterthehacker/public-repo/subscribers
             * subscription_url : https://api.github.com/repos/baxterthehacker/public-repo/subscription
             * commits_url : https://api.github.com/repos/baxterthehacker/public-repo/commits{/sha}
             * git_commits_url : https://api.github.com/repos/baxterthehacker/public-repo/git/commits{/sha}
             * comments_url : https://api.github.com/repos/baxterthehacker/public-repo/comments{/number}
             * issue_comment_url : https://api.github.com/repos/baxterthehacker/public-repo/issues/comments{/number}
             * contents_url : https://api.github.com/repos/baxterthehacker/public-repo/contents/{+path}
             * compare_url : https://api.github.com/repos/baxterthehacker/public-repo/compare/{base}...{head}
             * merges_url : https://api.github.com/repos/baxterthehacker/public-repo/merges
             * archive_url : https://api.github.com/repos/baxterthehacker/public-repo/{archive_format}{/ref}
             * downloads_url : https://api.github.com/repos/baxterthehacker/public-repo/downloads
             * issues_url : https://api.github.com/repos/baxterthehacker/public-repo/issues{/number}
             * pulls_url : https://api.github.com/repos/baxterthehacker/public-repo/pulls{/number}
             * milestones_url : https://api.github.com/repos/baxterthehacker/public-repo/milestones{/number}
             * notifications_url : https://api.github.com/repos/baxterthehacker/public-repo/notifications{?since,all,participating}
             * labels_url : https://api.github.com/repos/baxterthehacker/public-repo/labels{/name}
             * releases_url : https://api.github.com/repos/baxterthehacker/public-repo/releases{/id}
             * created_at : 2015-05-05T23:40:12Z
             * updated_at : 2015-05-05T23:40:12Z
             * pushed_at : 2015-05-05T23:40:26Z
             * git_url : git://github.com/baxterthehacker/public-repo.git
             * ssh_url : git@github.com:baxterthehacker/public-repo.git
             * clone_url : https://github.com/baxterthehacker/public-repo.git
             * svn_url : https://github.com/baxterthehacker/public-repo
             * homepage : null
             * size : 0
             * stargazers_count : 0
             * watchers_count : 0
             * language : null
             * has_issues : true
             * has_downloads : true
             * has_wiki : true
             * has_pages : true
             * forks_count : 0
             * mirror_url : null
             * open_issues_count : 1
             * forks : 0
             * open_issues : 1
             * watchers : 0
             * default_branch : master
             */

            private PullRequestBean.HeadBean.RepoBean repo;

            public String getLabel() {
                return label;
            }

            public void setLabel(String label) {
                this.label = label;
            }

            public String getRef() {
                return ref;
            }

            public void setRef(String ref) {
                this.ref = ref;
            }

            public String getSha() {
                return sha;
            }

            public void setSha(String sha) {
                this.sha = sha;
            }

            public PullRequestBean.HeadBean.UserBean getUser() {
                return user;
            }

            public void setUser(PullRequestBean.HeadBean.UserBean user) {
                this.user = user;
            }

            public PullRequestBean.HeadBean.RepoBean getRepo() {
                return repo;
            }

            public void setRepo(PullRequestBean.HeadBean.RepoBean repo) {
                this.repo = repo;
            }

            public static class UserBean {
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

            public static class RepoBean {
                private int id;
                private String name;
                private String full_name;
                /**
                 * login : baxterthehacker
                 * id : 6752317
                 * avatar_url : https://avatars.githubusercontent.com/u/6752317?v=3
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

                private PullRequestBean.HeadBean.RepoBean.OwnerBean owner;
                @SerializedName("private")
                private boolean privateX;
                private String html_url;
                private String description;
                private boolean fork;
                private String url;
                private String forks_url;
                private String keys_url;
                private String collaborators_url;
                private String teams_url;
                private String hooks_url;
                private String issue_events_url;
                private String events_url;
                private String assignees_url;
                private String branches_url;
                private String tags_url;
                private String blobs_url;
                private String git_tags_url;
                private String git_refs_url;
                private String trees_url;
                private String statuses_url;
                private String languages_url;
                private String stargazers_url;
                private String contributors_url;
                private String subscribers_url;
                private String subscription_url;
                private String commits_url;
                private String git_commits_url;
                private String comments_url;
                private String issue_comment_url;
                private String contents_url;
                private String compare_url;
                private String merges_url;
                private String archive_url;
                private String downloads_url;
                private String issues_url;
                private String pulls_url;
                private String milestones_url;
                private String notifications_url;
                private String labels_url;
                private String releases_url;
                private String created_at;
                private String updated_at;
                private String pushed_at;
                private String git_url;
                private String ssh_url;
                private String clone_url;
                private String svn_url;
                private Object homepage;
                private int size;
                private int stargazers_count;
                private int watchers_count;
                private Object language;
                private boolean has_issues;
                private boolean has_downloads;
                private boolean has_wiki;
                private boolean has_pages;
                private int forks_count;
                private Object mirror_url;
                private int open_issues_count;
                private int forks;
                private int open_issues;
                private int watchers;
                private String default_branch;

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

                public String getFull_name() {
                    return full_name;
                }

                public void setFull_name(String full_name) {
                    this.full_name = full_name;
                }

                public PullRequestBean.HeadBean.RepoBean.OwnerBean getOwner() {
                    return owner;
                }

                public void setOwner(PullRequestBean.HeadBean.RepoBean.OwnerBean owner) {
                    this.owner = owner;
                }

                public boolean isPrivateX() {
                    return privateX;
                }

                public void setPrivateX(boolean privateX) {
                    this.privateX = privateX;
                }

                public String getHtml_url() {
                    return html_url;
                }

                public void setHtml_url(String html_url) {
                    this.html_url = html_url;
                }

                public String getDescription() {
                    return description;
                }

                public void setDescription(String description) {
                    this.description = description;
                }

                public boolean isFork() {
                    return fork;
                }

                public void setFork(boolean fork) {
                    this.fork = fork;
                }

                public String getUrl() {
                    return url;
                }

                public void setUrl(String url) {
                    this.url = url;
                }

                public String getForks_url() {
                    return forks_url;
                }

                public void setForks_url(String forks_url) {
                    this.forks_url = forks_url;
                }

                public String getKeys_url() {
                    return keys_url;
                }

                public void setKeys_url(String keys_url) {
                    this.keys_url = keys_url;
                }

                public String getCollaborators_url() {
                    return collaborators_url;
                }

                public void setCollaborators_url(String collaborators_url) {
                    this.collaborators_url = collaborators_url;
                }

                public String getTeams_url() {
                    return teams_url;
                }

                public void setTeams_url(String teams_url) {
                    this.teams_url = teams_url;
                }

                public String getHooks_url() {
                    return hooks_url;
                }

                public void setHooks_url(String hooks_url) {
                    this.hooks_url = hooks_url;
                }

                public String getIssue_events_url() {
                    return issue_events_url;
                }

                public void setIssue_events_url(String issue_events_url) {
                    this.issue_events_url = issue_events_url;
                }

                public String getEvents_url() {
                    return events_url;
                }

                public void setEvents_url(String events_url) {
                    this.events_url = events_url;
                }

                public String getAssignees_url() {
                    return assignees_url;
                }

                public void setAssignees_url(String assignees_url) {
                    this.assignees_url = assignees_url;
                }

                public String getBranches_url() {
                    return branches_url;
                }

                public void setBranches_url(String branches_url) {
                    this.branches_url = branches_url;
                }

                public String getTags_url() {
                    return tags_url;
                }

                public void setTags_url(String tags_url) {
                    this.tags_url = tags_url;
                }

                public String getBlobs_url() {
                    return blobs_url;
                }

                public void setBlobs_url(String blobs_url) {
                    this.blobs_url = blobs_url;
                }

                public String getGit_tags_url() {
                    return git_tags_url;
                }

                public void setGit_tags_url(String git_tags_url) {
                    this.git_tags_url = git_tags_url;
                }

                public String getGit_refs_url() {
                    return git_refs_url;
                }

                public void setGit_refs_url(String git_refs_url) {
                    this.git_refs_url = git_refs_url;
                }

                public String getTrees_url() {
                    return trees_url;
                }

                public void setTrees_url(String trees_url) {
                    this.trees_url = trees_url;
                }

                public String getStatuses_url() {
                    return statuses_url;
                }

                public void setStatuses_url(String statuses_url) {
                    this.statuses_url = statuses_url;
                }

                public String getLanguages_url() {
                    return languages_url;
                }

                public void setLanguages_url(String languages_url) {
                    this.languages_url = languages_url;
                }

                public String getStargazers_url() {
                    return stargazers_url;
                }

                public void setStargazers_url(String stargazers_url) {
                    this.stargazers_url = stargazers_url;
                }

                public String getContributors_url() {
                    return contributors_url;
                }

                public void setContributors_url(String contributors_url) {
                    this.contributors_url = contributors_url;
                }

                public String getSubscribers_url() {
                    return subscribers_url;
                }

                public void setSubscribers_url(String subscribers_url) {
                    this.subscribers_url = subscribers_url;
                }

                public String getSubscription_url() {
                    return subscription_url;
                }

                public void setSubscription_url(String subscription_url) {
                    this.subscription_url = subscription_url;
                }

                public String getCommits_url() {
                    return commits_url;
                }

                public void setCommits_url(String commits_url) {
                    this.commits_url = commits_url;
                }

                public String getGit_commits_url() {
                    return git_commits_url;
                }

                public void setGit_commits_url(String git_commits_url) {
                    this.git_commits_url = git_commits_url;
                }

                public String getComments_url() {
                    return comments_url;
                }

                public void setComments_url(String comments_url) {
                    this.comments_url = comments_url;
                }

                public String getIssue_comment_url() {
                    return issue_comment_url;
                }

                public void setIssue_comment_url(String issue_comment_url) {
                    this.issue_comment_url = issue_comment_url;
                }

                public String getContents_url() {
                    return contents_url;
                }

                public void setContents_url(String contents_url) {
                    this.contents_url = contents_url;
                }

                public String getCompare_url() {
                    return compare_url;
                }

                public void setCompare_url(String compare_url) {
                    this.compare_url = compare_url;
                }

                public String getMerges_url() {
                    return merges_url;
                }

                public void setMerges_url(String merges_url) {
                    this.merges_url = merges_url;
                }

                public String getArchive_url() {
                    return archive_url;
                }

                public void setArchive_url(String archive_url) {
                    this.archive_url = archive_url;
                }

                public String getDownloads_url() {
                    return downloads_url;
                }

                public void setDownloads_url(String downloads_url) {
                    this.downloads_url = downloads_url;
                }

                public String getIssues_url() {
                    return issues_url;
                }

                public void setIssues_url(String issues_url) {
                    this.issues_url = issues_url;
                }

                public String getPulls_url() {
                    return pulls_url;
                }

                public void setPulls_url(String pulls_url) {
                    this.pulls_url = pulls_url;
                }

                public String getMilestones_url() {
                    return milestones_url;
                }

                public void setMilestones_url(String milestones_url) {
                    this.milestones_url = milestones_url;
                }

                public String getNotifications_url() {
                    return notifications_url;
                }

                public void setNotifications_url(String notifications_url) {
                    this.notifications_url = notifications_url;
                }

                public String getLabels_url() {
                    return labels_url;
                }

                public void setLabels_url(String labels_url) {
                    this.labels_url = labels_url;
                }

                public String getReleases_url() {
                    return releases_url;
                }

                public void setReleases_url(String releases_url) {
                    this.releases_url = releases_url;
                }

                public String getCreated_at() {
                    return created_at;
                }

                public void setCreated_at(String created_at) {
                    this.created_at = created_at;
                }

                public String getUpdated_at() {
                    return updated_at;
                }

                public void setUpdated_at(String updated_at) {
                    this.updated_at = updated_at;
                }

                public String getPushed_at() {
                    return pushed_at;
                }

                public void setPushed_at(String pushed_at) {
                    this.pushed_at = pushed_at;
                }

                public String getGit_url() {
                    return git_url;
                }

                public void setGit_url(String git_url) {
                    this.git_url = git_url;
                }

                public String getSsh_url() {
                    return ssh_url;
                }

                public void setSsh_url(String ssh_url) {
                    this.ssh_url = ssh_url;
                }

                public String getClone_url() {
                    return clone_url;
                }

                public void setClone_url(String clone_url) {
                    this.clone_url = clone_url;
                }

                public String getSvn_url() {
                    return svn_url;
                }

                public void setSvn_url(String svn_url) {
                    this.svn_url = svn_url;
                }

                public Object getHomepage() {
                    return homepage;
                }

                public void setHomepage(Object homepage) {
                    this.homepage = homepage;
                }

                public int getSize() {
                    return size;
                }

                public void setSize(int size) {
                    this.size = size;
                }

                public int getStargazers_count() {
                    return stargazers_count;
                }

                public void setStargazers_count(int stargazers_count) {
                    this.stargazers_count = stargazers_count;
                }

                public int getWatchers_count() {
                    return watchers_count;
                }

                public void setWatchers_count(int watchers_count) {
                    this.watchers_count = watchers_count;
                }

                public Object getLanguage() {
                    return language;
                }

                public void setLanguage(Object language) {
                    this.language = language;
                }

                public boolean isHas_issues() {
                    return has_issues;
                }

                public void setHas_issues(boolean has_issues) {
                    this.has_issues = has_issues;
                }

                public boolean isHas_downloads() {
                    return has_downloads;
                }

                public void setHas_downloads(boolean has_downloads) {
                    this.has_downloads = has_downloads;
                }

                public boolean isHas_wiki() {
                    return has_wiki;
                }

                public void setHas_wiki(boolean has_wiki) {
                    this.has_wiki = has_wiki;
                }

                public boolean isHas_pages() {
                    return has_pages;
                }

                public void setHas_pages(boolean has_pages) {
                    this.has_pages = has_pages;
                }

                public int getForks_count() {
                    return forks_count;
                }

                public void setForks_count(int forks_count) {
                    this.forks_count = forks_count;
                }

                public Object getMirror_url() {
                    return mirror_url;
                }

                public void setMirror_url(Object mirror_url) {
                    this.mirror_url = mirror_url;
                }

                public int getOpen_issues_count() {
                    return open_issues_count;
                }

                public void setOpen_issues_count(int open_issues_count) {
                    this.open_issues_count = open_issues_count;
                }

                public int getForks() {
                    return forks;
                }

                public void setForks(int forks) {
                    this.forks = forks;
                }

                public int getOpen_issues() {
                    return open_issues;
                }

                public void setOpen_issues(int open_issues) {
                    this.open_issues = open_issues;
                }

                public int getWatchers() {
                    return watchers;
                }

                public void setWatchers(int watchers) {
                    this.watchers = watchers;
                }

                public String getDefault_branch() {
                    return default_branch;
                }

                public void setDefault_branch(String default_branch) {
                    this.default_branch = default_branch;
                }

                public static class OwnerBean {
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
            }
        }

        public static class BaseBean {
            private String label;
            private String ref;
            private String sha;
            /**
             * login : baxterthehacker
             * id : 6752317
             * avatar_url : https://avatars.githubusercontent.com/u/6752317?v=3
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

            private PullRequestBean.BaseBean.UserBean user;
            /**
             * id : 35129377
             * name : public-repo
             * full_name : baxterthehacker/public-repo
             * owner : {"login":"baxterthehacker","id":6752317,"avatar_url":"https://avatars.githubusercontent.com/u/6752317?v=3","gravatar_id":"","url":"https://api.github.com/users/baxterthehacker","html_url":"https://github.com/baxterthehacker","followers_url":"https://api.github.com/users/baxterthehacker/followers","following_url":"https://api.github.com/users/baxterthehacker/following{/other_user}","gists_url":"https://api.github.com/users/baxterthehacker/gists{/gist_id}","starred_url":"https://api.github.com/users/baxterthehacker/starred{/owner}{/repo}","subscriptions_url":"https://api.github.com/users/baxterthehacker/subscriptions","organizations_url":"https://api.github.com/users/baxterthehacker/orgs","repos_url":"https://api.github.com/users/baxterthehacker/repos","events_url":"https://api.github.com/users/baxterthehacker/events{/privacy}","received_events_url":"https://api.github.com/users/baxterthehacker/received_events","type":"User","site_admin":false}
             * private : false
             * html_url : https://github.com/baxterthehacker/public-repo
             * description :
             * fork : false
             * url : https://api.github.com/repos/baxterthehacker/public-repo
             * forks_url : https://api.github.com/repos/baxterthehacker/public-repo/forks
             * keys_url : https://api.github.com/repos/baxterthehacker/public-repo/keys{/key_id}
             * collaborators_url : https://api.github.com/repos/baxterthehacker/public-repo/collaborators{/collaborator}
             * teams_url : https://api.github.com/repos/baxterthehacker/public-repo/teams
             * hooks_url : https://api.github.com/repos/baxterthehacker/public-repo/hooks
             * issue_events_url : https://api.github.com/repos/baxterthehacker/public-repo/issues/events{/number}
             * events_url : https://api.github.com/repos/baxterthehacker/public-repo/events
             * assignees_url : https://api.github.com/repos/baxterthehacker/public-repo/assignees{/user}
             * branches_url : https://api.github.com/repos/baxterthehacker/public-repo/branches{/branch}
             * tags_url : https://api.github.com/repos/baxterthehacker/public-repo/tags
             * blobs_url : https://api.github.com/repos/baxterthehacker/public-repo/git/blobs{/sha}
             * git_tags_url : https://api.github.com/repos/baxterthehacker/public-repo/git/tags{/sha}
             * git_refs_url : https://api.github.com/repos/baxterthehacker/public-repo/git/refs{/sha}
             * trees_url : https://api.github.com/repos/baxterthehacker/public-repo/git/trees{/sha}
             * statuses_url : https://api.github.com/repos/baxterthehacker/public-repo/statuses/{sha}
             * languages_url : https://api.github.com/repos/baxterthehacker/public-repo/languages
             * stargazers_url : https://api.github.com/repos/baxterthehacker/public-repo/stargazers
             * contributors_url : https://api.github.com/repos/baxterthehacker/public-repo/contributors
             * subscribers_url : https://api.github.com/repos/baxterthehacker/public-repo/subscribers
             * subscription_url : https://api.github.com/repos/baxterthehacker/public-repo/subscription
             * commits_url : https://api.github.com/repos/baxterthehacker/public-repo/commits{/sha}
             * git_commits_url : https://api.github.com/repos/baxterthehacker/public-repo/git/commits{/sha}
             * comments_url : https://api.github.com/repos/baxterthehacker/public-repo/comments{/number}
             * issue_comment_url : https://api.github.com/repos/baxterthehacker/public-repo/issues/comments{/number}
             * contents_url : https://api.github.com/repos/baxterthehacker/public-repo/contents/{+path}
             * compare_url : https://api.github.com/repos/baxterthehacker/public-repo/compare/{base}...{head}
             * merges_url : https://api.github.com/repos/baxterthehacker/public-repo/merges
             * archive_url : https://api.github.com/repos/baxterthehacker/public-repo/{archive_format}{/ref}
             * downloads_url : https://api.github.com/repos/baxterthehacker/public-repo/downloads
             * issues_url : https://api.github.com/repos/baxterthehacker/public-repo/issues{/number}
             * pulls_url : https://api.github.com/repos/baxterthehacker/public-repo/pulls{/number}
             * milestones_url : https://api.github.com/repos/baxterthehacker/public-repo/milestones{/number}
             * notifications_url : https://api.github.com/repos/baxterthehacker/public-repo/notifications{?since,all,participating}
             * labels_url : https://api.github.com/repos/baxterthehacker/public-repo/labels{/name}
             * releases_url : https://api.github.com/repos/baxterthehacker/public-repo/releases{/id}
             * created_at : 2015-05-05T23:40:12Z
             * updated_at : 2015-05-05T23:40:12Z
             * pushed_at : 2015-05-05T23:40:26Z
             * git_url : git://github.com/baxterthehacker/public-repo.git
             * ssh_url : git@github.com:baxterthehacker/public-repo.git
             * clone_url : https://github.com/baxterthehacker/public-repo.git
             * svn_url : https://github.com/baxterthehacker/public-repo
             * homepage : null
             * size : 0
             * stargazers_count : 0
             * watchers_count : 0
             * language : null
             * has_issues : true
             * has_downloads : true
             * has_wiki : true
             * has_pages : true
             * forks_count : 0
             * mirror_url : null
             * open_issues_count : 1
             * forks : 0
             * open_issues : 1
             * watchers : 0
             * default_branch : master
             */

            private PullRequestBean.BaseBean.RepoBean repo;

            public String getLabel() {
                return label;
            }

            public void setLabel(String label) {
                this.label = label;
            }

            public String getRef() {
                return ref;
            }

            public void setRef(String ref) {
                this.ref = ref;
            }

            public String getSha() {
                return sha;
            }

            public void setSha(String sha) {
                this.sha = sha;
            }

            public PullRequestBean.BaseBean.UserBean getUser() {
                return user;
            }

            public void setUser(PullRequestBean.BaseBean.UserBean user) {
                this.user = user;
            }

            public PullRequestBean.BaseBean.RepoBean getRepo() {
                return repo;
            }

            public void setRepo(PullRequestBean.BaseBean.RepoBean repo) {
                this.repo = repo;
            }

            public static class UserBean {
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

            public static class RepoBean {
                private int id;
                private String name;
                private String full_name;
                /**
                 * login : baxterthehacker
                 * id : 6752317
                 * avatar_url : https://avatars.githubusercontent.com/u/6752317?v=3
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

                private PullRequestBean.BaseBean.RepoBean.OwnerBean owner;
                @SerializedName("private")
                private boolean privateX;
                private String html_url;
                private String description;
                private boolean fork;
                private String url;
                private String forks_url;
                private String keys_url;
                private String collaborators_url;
                private String teams_url;
                private String hooks_url;
                private String issue_events_url;
                private String events_url;
                private String assignees_url;
                private String branches_url;
                private String tags_url;
                private String blobs_url;
                private String git_tags_url;
                private String git_refs_url;
                private String trees_url;
                private String statuses_url;
                private String languages_url;
                private String stargazers_url;
                private String contributors_url;
                private String subscribers_url;
                private String subscription_url;
                private String commits_url;
                private String git_commits_url;
                private String comments_url;
                private String issue_comment_url;
                private String contents_url;
                private String compare_url;
                private String merges_url;
                private String archive_url;
                private String downloads_url;
                private String issues_url;
                private String pulls_url;
                private String milestones_url;
                private String notifications_url;
                private String labels_url;
                private String releases_url;
                private String created_at;
                private String updated_at;
                private String pushed_at;
                private String git_url;
                private String ssh_url;
                private String clone_url;
                private String svn_url;
                private Object homepage;
                private int size;
                private int stargazers_count;
                private int watchers_count;
                private Object language;
                private boolean has_issues;
                private boolean has_downloads;
                private boolean has_wiki;
                private boolean has_pages;
                private int forks_count;
                private Object mirror_url;
                private int open_issues_count;
                private int forks;
                private int open_issues;
                private int watchers;
                private String default_branch;

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

                public String getFull_name() {
                    return full_name;
                }

                public void setFull_name(String full_name) {
                    this.full_name = full_name;
                }

                public PullRequestBean.BaseBean.RepoBean.OwnerBean getOwner() {
                    return owner;
                }

                public void setOwner(PullRequestBean.BaseBean.RepoBean.OwnerBean owner) {
                    this.owner = owner;
                }

                public boolean isPrivateX() {
                    return privateX;
                }

                public void setPrivateX(boolean privateX) {
                    this.privateX = privateX;
                }

                public String getHtml_url() {
                    return html_url;
                }

                public void setHtml_url(String html_url) {
                    this.html_url = html_url;
                }

                public String getDescription() {
                    return description;
                }

                public void setDescription(String description) {
                    this.description = description;
                }

                public boolean isFork() {
                    return fork;
                }

                public void setFork(boolean fork) {
                    this.fork = fork;
                }

                public String getUrl() {
                    return url;
                }

                public void setUrl(String url) {
                    this.url = url;
                }

                public String getForks_url() {
                    return forks_url;
                }

                public void setForks_url(String forks_url) {
                    this.forks_url = forks_url;
                }

                public String getKeys_url() {
                    return keys_url;
                }

                public void setKeys_url(String keys_url) {
                    this.keys_url = keys_url;
                }

                public String getCollaborators_url() {
                    return collaborators_url;
                }

                public void setCollaborators_url(String collaborators_url) {
                    this.collaborators_url = collaborators_url;
                }

                public String getTeams_url() {
                    return teams_url;
                }

                public void setTeams_url(String teams_url) {
                    this.teams_url = teams_url;
                }

                public String getHooks_url() {
                    return hooks_url;
                }

                public void setHooks_url(String hooks_url) {
                    this.hooks_url = hooks_url;
                }

                public String getIssue_events_url() {
                    return issue_events_url;
                }

                public void setIssue_events_url(String issue_events_url) {
                    this.issue_events_url = issue_events_url;
                }

                public String getEvents_url() {
                    return events_url;
                }

                public void setEvents_url(String events_url) {
                    this.events_url = events_url;
                }

                public String getAssignees_url() {
                    return assignees_url;
                }

                public void setAssignees_url(String assignees_url) {
                    this.assignees_url = assignees_url;
                }

                public String getBranches_url() {
                    return branches_url;
                }

                public void setBranches_url(String branches_url) {
                    this.branches_url = branches_url;
                }

                public String getTags_url() {
                    return tags_url;
                }

                public void setTags_url(String tags_url) {
                    this.tags_url = tags_url;
                }

                public String getBlobs_url() {
                    return blobs_url;
                }

                public void setBlobs_url(String blobs_url) {
                    this.blobs_url = blobs_url;
                }

                public String getGit_tags_url() {
                    return git_tags_url;
                }

                public void setGit_tags_url(String git_tags_url) {
                    this.git_tags_url = git_tags_url;
                }

                public String getGit_refs_url() {
                    return git_refs_url;
                }

                public void setGit_refs_url(String git_refs_url) {
                    this.git_refs_url = git_refs_url;
                }

                public String getTrees_url() {
                    return trees_url;
                }

                public void setTrees_url(String trees_url) {
                    this.trees_url = trees_url;
                }

                public String getStatuses_url() {
                    return statuses_url;
                }

                public void setStatuses_url(String statuses_url) {
                    this.statuses_url = statuses_url;
                }

                public String getLanguages_url() {
                    return languages_url;
                }

                public void setLanguages_url(String languages_url) {
                    this.languages_url = languages_url;
                }

                public String getStargazers_url() {
                    return stargazers_url;
                }

                public void setStargazers_url(String stargazers_url) {
                    this.stargazers_url = stargazers_url;
                }

                public String getContributors_url() {
                    return contributors_url;
                }

                public void setContributors_url(String contributors_url) {
                    this.contributors_url = contributors_url;
                }

                public String getSubscribers_url() {
                    return subscribers_url;
                }

                public void setSubscribers_url(String subscribers_url) {
                    this.subscribers_url = subscribers_url;
                }

                public String getSubscription_url() {
                    return subscription_url;
                }

                public void setSubscription_url(String subscription_url) {
                    this.subscription_url = subscription_url;
                }

                public String getCommits_url() {
                    return commits_url;
                }

                public void setCommits_url(String commits_url) {
                    this.commits_url = commits_url;
                }

                public String getGit_commits_url() {
                    return git_commits_url;
                }

                public void setGit_commits_url(String git_commits_url) {
                    this.git_commits_url = git_commits_url;
                }

                public String getComments_url() {
                    return comments_url;
                }

                public void setComments_url(String comments_url) {
                    this.comments_url = comments_url;
                }

                public String getIssue_comment_url() {
                    return issue_comment_url;
                }

                public void setIssue_comment_url(String issue_comment_url) {
                    this.issue_comment_url = issue_comment_url;
                }

                public String getContents_url() {
                    return contents_url;
                }

                public void setContents_url(String contents_url) {
                    this.contents_url = contents_url;
                }

                public String getCompare_url() {
                    return compare_url;
                }

                public void setCompare_url(String compare_url) {
                    this.compare_url = compare_url;
                }

                public String getMerges_url() {
                    return merges_url;
                }

                public void setMerges_url(String merges_url) {
                    this.merges_url = merges_url;
                }

                public String getArchive_url() {
                    return archive_url;
                }

                public void setArchive_url(String archive_url) {
                    this.archive_url = archive_url;
                }

                public String getDownloads_url() {
                    return downloads_url;
                }

                public void setDownloads_url(String downloads_url) {
                    this.downloads_url = downloads_url;
                }

                public String getIssues_url() {
                    return issues_url;
                }

                public void setIssues_url(String issues_url) {
                    this.issues_url = issues_url;
                }

                public String getPulls_url() {
                    return pulls_url;
                }

                public void setPulls_url(String pulls_url) {
                    this.pulls_url = pulls_url;
                }

                public String getMilestones_url() {
                    return milestones_url;
                }

                public void setMilestones_url(String milestones_url) {
                    this.milestones_url = milestones_url;
                }

                public String getNotifications_url() {
                    return notifications_url;
                }

                public void setNotifications_url(String notifications_url) {
                    this.notifications_url = notifications_url;
                }

                public String getLabels_url() {
                    return labels_url;
                }

                public void setLabels_url(String labels_url) {
                    this.labels_url = labels_url;
                }

                public String getReleases_url() {
                    return releases_url;
                }

                public void setReleases_url(String releases_url) {
                    this.releases_url = releases_url;
                }

                public String getCreated_at() {
                    return created_at;
                }

                public void setCreated_at(String created_at) {
                    this.created_at = created_at;
                }

                public String getUpdated_at() {
                    return updated_at;
                }

                public void setUpdated_at(String updated_at) {
                    this.updated_at = updated_at;
                }

                public String getPushed_at() {
                    return pushed_at;
                }

                public void setPushed_at(String pushed_at) {
                    this.pushed_at = pushed_at;
                }

                public String getGit_url() {
                    return git_url;
                }

                public void setGit_url(String git_url) {
                    this.git_url = git_url;
                }

                public String getSsh_url() {
                    return ssh_url;
                }

                public void setSsh_url(String ssh_url) {
                    this.ssh_url = ssh_url;
                }

                public String getClone_url() {
                    return clone_url;
                }

                public void setClone_url(String clone_url) {
                    this.clone_url = clone_url;
                }

                public String getSvn_url() {
                    return svn_url;
                }

                public void setSvn_url(String svn_url) {
                    this.svn_url = svn_url;
                }

                public Object getHomepage() {
                    return homepage;
                }

                public void setHomepage(Object homepage) {
                    this.homepage = homepage;
                }

                public int getSize() {
                    return size;
                }

                public void setSize(int size) {
                    this.size = size;
                }

                public int getStargazers_count() {
                    return stargazers_count;
                }

                public void setStargazers_count(int stargazers_count) {
                    this.stargazers_count = stargazers_count;
                }

                public int getWatchers_count() {
                    return watchers_count;
                }

                public void setWatchers_count(int watchers_count) {
                    this.watchers_count = watchers_count;
                }

                public Object getLanguage() {
                    return language;
                }

                public void setLanguage(Object language) {
                    this.language = language;
                }

                public boolean isHas_issues() {
                    return has_issues;
                }

                public void setHas_issues(boolean has_issues) {
                    this.has_issues = has_issues;
                }

                public boolean isHas_downloads() {
                    return has_downloads;
                }

                public void setHas_downloads(boolean has_downloads) {
                    this.has_downloads = has_downloads;
                }

                public boolean isHas_wiki() {
                    return has_wiki;
                }

                public void setHas_wiki(boolean has_wiki) {
                    this.has_wiki = has_wiki;
                }

                public boolean isHas_pages() {
                    return has_pages;
                }

                public void setHas_pages(boolean has_pages) {
                    this.has_pages = has_pages;
                }

                public int getForks_count() {
                    return forks_count;
                }

                public void setForks_count(int forks_count) {
                    this.forks_count = forks_count;
                }

                public Object getMirror_url() {
                    return mirror_url;
                }

                public void setMirror_url(Object mirror_url) {
                    this.mirror_url = mirror_url;
                }

                public int getOpen_issues_count() {
                    return open_issues_count;
                }

                public void setOpen_issues_count(int open_issues_count) {
                    this.open_issues_count = open_issues_count;
                }

                public int getForks() {
                    return forks;
                }

                public void setForks(int forks) {
                    this.forks = forks;
                }

                public int getOpen_issues() {
                    return open_issues;
                }

                public void setOpen_issues(int open_issues) {
                    this.open_issues = open_issues;
                }

                public int getWatchers() {
                    return watchers;
                }

                public void setWatchers(int watchers) {
                    this.watchers = watchers;
                }

                public String getDefault_branch() {
                    return default_branch;
                }

                public void setDefault_branch(String default_branch) {
                    this.default_branch = default_branch;
                }

                public static class OwnerBean {
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
            }
        }

        public static class LinksBean {
            /**
             * href : https://api.github.com/repos/baxterthehacker/public-repo/pulls/1
             */

            private PullRequestBean.LinksBean.SelfBean self;
            /**
             * href : https://github.com/baxterthehacker/public-repo/pull/1
             */

            private PullRequestBean.LinksBean.HtmlBean html;
            /**
             * href : https://api.github.com/repos/baxterthehacker/public-repo/issues/1
             */

            private PullRequestBean.LinksBean.IssueBean issue;
            /**
             * href : https://api.github.com/repos/baxterthehacker/public-repo/issues/1/comments
             */

            private PullRequestBean.LinksBean.CommentsBean comments;
            /**
             * href : https://api.github.com/repos/baxterthehacker/public-repo/pulls/1/comments
             */

            private PullRequestBean.LinksBean.ReviewCommentsBean review_comments;
            /**
             * href : https://api.github.com/repos/baxterthehacker/public-repo/pulls/comments{/number}
             */

            private PullRequestBean.LinksBean.ReviewCommentBean review_comment;
            /**
             * href : https://api.github.com/repos/baxterthehacker/public-repo/pulls/1/commits
             */

            private PullRequestBean.LinksBean.CommitsBean commits;
            /**
             * href : https://api.github.com/repos/baxterthehacker/public-repo/statuses/0d1a26e67d8f5eaf1f6ba5c57fc3c7d91ac0fd1c
             */

            private PullRequestBean.LinksBean.StatusesBean statuses;

            public PullRequestBean.LinksBean.SelfBean getSelf() {
                return self;
            }

            public void setSelf(PullRequestBean.LinksBean.SelfBean self) {
                this.self = self;
            }

            public PullRequestBean.LinksBean.HtmlBean getHtml() {
                return html;
            }

            public void setHtml(PullRequestBean.LinksBean.HtmlBean html) {
                this.html = html;
            }

            public PullRequestBean.LinksBean.IssueBean getIssue() {
                return issue;
            }

            public void setIssue(PullRequestBean.LinksBean.IssueBean issue) {
                this.issue = issue;
            }

            public PullRequestBean.LinksBean.CommentsBean getComments() {
                return comments;
            }

            public void setComments(PullRequestBean.LinksBean.CommentsBean comments) {
                this.comments = comments;
            }

            public PullRequestBean.LinksBean.ReviewCommentsBean getReview_comments() {
                return review_comments;
            }

            public void setReview_comments(PullRequestBean.LinksBean.ReviewCommentsBean review_comments) {
                this.review_comments = review_comments;
            }

            public PullRequestBean.LinksBean.ReviewCommentBean getReview_comment() {
                return review_comment;
            }

            public void setReview_comment(PullRequestBean.LinksBean.ReviewCommentBean review_comment) {
                this.review_comment = review_comment;
            }

            public PullRequestBean.LinksBean.CommitsBean getCommits() {
                return commits;
            }

            public void setCommits(PullRequestBean.LinksBean.CommitsBean commits) {
                this.commits = commits;
            }

            public PullRequestBean.LinksBean.StatusesBean getStatuses() {
                return statuses;
            }

            public void setStatuses(PullRequestBean.LinksBean.StatusesBean statuses) {
                this.statuses = statuses;
            }

            public static class SelfBean {
                private String href;

                public String getHref() {
                    return href;
                }

                public void setHref(String href) {
                    this.href = href;
                }
            }

            public static class HtmlBean {
                private String href;

                public String getHref() {
                    return href;
                }

                public void setHref(String href) {
                    this.href = href;
                }
            }

            public static class IssueBean {
                private String href;

                public String getHref() {
                    return href;
                }

                public void setHref(String href) {
                    this.href = href;
                }
            }

            public static class CommentsBean {
                private String href;

                public String getHref() {
                    return href;
                }

                public void setHref(String href) {
                    this.href = href;
                }
            }

            public static class ReviewCommentsBean {
                private String href;

                public String getHref() {
                    return href;
                }

                public void setHref(String href) {
                    this.href = href;
                }
            }

            public static class ReviewCommentBean {
                private String href;

                public String getHref() {
                    return href;
                }

                public void setHref(String href) {
                    this.href = href;
                }
            }

            public static class CommitsBean {
                private String href;

                public String getHref() {
                    return href;
                }

                public void setHref(String href) {
                    this.href = href;
                }
            }

            public static class StatusesBean {
                private String href;

                public String getHref() {
                    return href;
                }

                public void setHref(String href) {
                    this.href = href;
                }
            }
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
}
