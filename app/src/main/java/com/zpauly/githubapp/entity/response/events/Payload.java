package com.zpauly.githubapp.entity.response.events;

import com.google.gson.annotations.SerializedName;
import com.zpauly.githubapp.entity.response.CommentBean;
import com.zpauly.githubapp.entity.response.OrganizationBean;
import com.zpauly.githubapp.entity.response.PullRequestBean;
import com.zpauly.githubapp.entity.response.UserBean;
import com.zpauly.githubapp.entity.response.issues.IssueBean;
import com.zpauly.githubapp.entity.response.repos.ReleaseBean;
import com.zpauly.githubapp.entity.response.repos.RepositoriesBean;

import java.util.List;

/**
 * Created by zpauly on 16-7-23.
 */

public class Payload {
    private String action;
    private RepositoriesBean repository;
    private UserBean sender;
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

    public UserBean getSender() {
        return sender;
    }

    public void setSender(UserBean sender) {
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

    public UserBean getMember() {
        return member;
    }

    public void setMember(UserBean member) {
        this.member = member;
    }

    private String ref;
    private String head;
    private String before;
    private List<CommitsBean> commits;
    private RepositoriesBean forkee;
    private UserBean member;

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
}
