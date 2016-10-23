package com.zpauly.githubapp.entity.response;

import android.os.Parcel;
import android.os.Parcelable;

import com.zpauly.githubapp.entity.response.repos.RepositoriesBean;

/**
 * Created by zpauly on 2016/10/22.
 */

public class PullRequestBean implements Parcelable {
    /**
     * id : 1
     * url : https://api.github.com/repos/octocat/Hello-World/pulls/1347
     * html_url : https://github.com/octocat/Hello-World/pull/1347
     * diff_url : https://github.com/octocat/Hello-World/pull/1347.diff
     * patch_url : https://github.com/octocat/Hello-World/pull/1347.patch
     * issue_url : https://api.github.com/repos/octocat/Hello-World/issues/1347
     * commits_url : https://api.github.com/repos/octocat/Hello-World/pulls/1347/commits
     * review_comments_url : https://api.github.com/repos/octocat/Hello-World/pulls/1347/comments
     * review_comment_url : https://api.github.com/repos/octocat/Hello-World/pulls/comments{/number}
     * comments_url : https://api.github.com/repos/octocat/Hello-World/issues/1347/comments
     * statuses_url : https://api.github.com/repos/octocat/Hello-World/statuses/6dcb09b5b57875f334f61aebed695e2e4193db5e
     * number : 1347
     * state : open
     * title : new-feature
     * body : Please pull these awesome changes
     * assignee : {"login":"octocat","id":1,"avatar_url":"https://github.com/images/error/octocat_happy.gif","gravatar_id":"","url":"https://api.github.com/users/octocat","html_url":"https://github.com/octocat","followers_url":"https://api.github.com/users/octocat/followers","following_url":"https://api.github.com/users/octocat/following{/other_user}","gists_url":"https://api.github.com/users/octocat/gists{/gist_id}","starred_url":"https://api.github.com/users/octocat/starred{/owner}{/repo}","subscriptions_url":"https://api.github.com/users/octocat/subscriptions","organizations_url":"https://api.github.com/users/octocat/orgs","repos_url":"https://api.github.com/users/octocat/repos","events_url":"https://api.github.com/users/octocat/events{/privacy}","received_events_url":"https://api.github.com/users/octocat/received_events","type":"User","site_admin":false}
     * milestone : {"url":"https://api.github.com/repos/octocat/Hello-World/milestones/1","html_url":"https://github.com/octocat/Hello-World/milestones/v1.0","labels_url":"https://api.github.com/repos/octocat/Hello-World/milestones/1/labels","id":1002604,"number":1,"state":"open","title":"v1.0","description":"Tracking milestone for version 1.0","creator":{"login":"octocat","id":1,"avatar_url":"https://github.com/images/error/octocat_happy.gif","gravatar_id":"","url":"https://api.github.com/users/octocat","html_url":"https://github.com/octocat","followers_url":"https://api.github.com/users/octocat/followers","following_url":"https://api.github.com/users/octocat/following{/other_user}","gists_url":"https://api.github.com/users/octocat/gists{/gist_id}","starred_url":"https://api.github.com/users/octocat/starred{/owner}{/repo}","subscriptions_url":"https://api.github.com/users/octocat/subscriptions","organizations_url":"https://api.github.com/users/octocat/orgs","repos_url":"https://api.github.com/users/octocat/repos","events_url":"https://api.github.com/users/octocat/events{/privacy}","received_events_url":"https://api.github.com/users/octocat/received_events","type":"User","site_admin":false},"open_issues":4,"closed_issues":8,"created_at":"2011-04-10T20:09:31Z","updated_at":"2014-03-03T18:58:10Z","closed_at":"2013-02-12T13:22:01Z","due_on":"2012-10-09T23:39:01Z"}
     * locked : false
     * created_at : 2011-01-26T19:01:12Z
     * updated_at : 2011-01-26T19:01:12Z
     * closed_at : 2011-01-26T19:01:12Z
     * merged_at : 2011-01-26T19:01:12Z
     * head : {"label":"new-topic","ref":"new-topic","sha":"6dcb09b5b57875f334f61aebed695e2e4193db5e","user":{"login":"octocat","id":1,"avatar_url":"https://github.com/images/error/octocat_happy.gif","gravatar_id":"","url":"https://api.github.com/users/octocat","html_url":"https://github.com/octocat","followers_url":"https://api.github.com/users/octocat/followers","following_url":"https://api.github.com/users/octocat/following{/other_user}","gists_url":"https://api.github.com/users/octocat/gists{/gist_id}","starred_url":"https://api.github.com/users/octocat/starred{/owner}{/repo}","subscriptions_url":"https://api.github.com/users/octocat/subscriptions","organizations_url":"https://api.github.com/users/octocat/orgs","repos_url":"https://api.github.com/users/octocat/repos","events_url":"https://api.github.com/users/octocat/events{/privacy}","received_events_url":"https://api.github.com/users/octocat/received_events","type":"User","site_admin":false},"repo":{"id":1296269,"owner":{"login":"octocat","id":1,"avatar_url":"https://github.com/images/error/octocat_happy.gif","gravatar_id":"","url":"https://api.github.com/users/octocat","html_url":"https://github.com/octocat","followers_url":"https://api.github.com/users/octocat/followers","following_url":"https://api.github.com/users/octocat/following{/other_user}","gists_url":"https://api.github.com/users/octocat/gists{/gist_id}","starred_url":"https://api.github.com/users/octocat/starred{/owner}{/repo}","subscriptions_url":"https://api.github.com/users/octocat/subscriptions","organizations_url":"https://api.github.com/users/octocat/orgs","repos_url":"https://api.github.com/users/octocat/repos","events_url":"https://api.github.com/users/octocat/events{/privacy}","received_events_url":"https://api.github.com/users/octocat/received_events","type":"User","site_admin":false},"name":"Hello-World","full_name":"octocat/Hello-World","description":"This your first repo!","private":false,"fork":false,"url":"https://api.github.com/repos/octocat/Hello-World","html_url":"https://github.com/octocat/Hello-World","archive_url":"http://api.github.com/repos/octocat/Hello-World/{archive_format}{/ref}","assignees_url":"http://api.github.com/repos/octocat/Hello-World/assignees{/user}","blobs_url":"http://api.github.com/repos/octocat/Hello-World/git/blobs{/sha}","branches_url":"http://api.github.com/repos/octocat/Hello-World/branches{/branch}","clone_url":"https://github.com/octocat/Hello-World.git","collaborators_url":"http://api.github.com/repos/octocat/Hello-World/collaborators{/collaborator}","comments_url":"http://api.github.com/repos/octocat/Hello-World/comments{/number}","commits_url":"http://api.github.com/repos/octocat/Hello-World/commits{/sha}","compare_url":"http://api.github.com/repos/octocat/Hello-World/compare/{base}...{head}","contents_url":"http://api.github.com/repos/octocat/Hello-World/contents/{+path}","contributors_url":"http://api.github.com/repos/octocat/Hello-World/contributors","deployments_url":"http://api.github.com/repos/octocat/Hello-World/deployments","downloads_url":"http://api.github.com/repos/octocat/Hello-World/downloads","events_url":"http://api.github.com/repos/octocat/Hello-World/events","forks_url":"http://api.github.com/repos/octocat/Hello-World/forks","git_commits_url":"http://api.github.com/repos/octocat/Hello-World/git/commits{/sha}","git_refs_url":"http://api.github.com/repos/octocat/Hello-World/git/refs{/sha}","git_tags_url":"http://api.github.com/repos/octocat/Hello-World/git/tags{/sha}","git_url":"git:github.com/octocat/Hello-World.git","hooks_url":"http://api.github.com/repos/octocat/Hello-World/hooks","issue_comment_url":"http://api.github.com/repos/octocat/Hello-World/issues/comments{/number}","issue_events_url":"http://api.github.com/repos/octocat/Hello-World/issues/events{/number}","issues_url":"http://api.github.com/repos/octocat/Hello-World/issues{/number}","keys_url":"http://api.github.com/repos/octocat/Hello-World/keys{/key_id}","labels_url":"http://api.github.com/repos/octocat/Hello-World/labels{/name}","languages_url":"http://api.github.com/repos/octocat/Hello-World/languages","merges_url":"http://api.github.com/repos/octocat/Hello-World/merges","milestones_url":"http://api.github.com/repos/octocat/Hello-World/milestones{/number}","mirror_url":"git:git.example.com/octocat/Hello-World","notifications_url":"http://api.github.com/repos/octocat/Hello-World/notifications{?since, all, participating}","pulls_url":"http://api.github.com/repos/octocat/Hello-World/pulls{/number}","releases_url":"http://api.github.com/repos/octocat/Hello-World/releases{/id}","ssh_url":"git@github.com:octocat/Hello-World.git","stargazers_url":"http://api.github.com/repos/octocat/Hello-World/stargazers","statuses_url":"http://api.github.com/repos/octocat/Hello-World/statuses/{sha}","subscribers_url":"http://api.github.com/repos/octocat/Hello-World/subscribers","subscription_url":"http://api.github.com/repos/octocat/Hello-World/subscription","svn_url":"https://svn.github.com/octocat/Hello-World","tags_url":"http://api.github.com/repos/octocat/Hello-World/tags","teams_url":"http://api.github.com/repos/octocat/Hello-World/teams","trees_url":"http://api.github.com/repos/octocat/Hello-World/git/trees{/sha}","homepage":"https://github.com","language":null,"forks_count":9,"stargazers_count":80,"watchers_count":80,"size":108,"default_branch":"master","open_issues_count":0,"has_issues":true,"has_wiki":true,"has_pages":false,"has_downloads":true,"pushed_at":"2011-01-26T19:06:43Z","created_at":"2011-01-26T19:01:12Z","updated_at":"2011-01-26T19:14:43Z","permissions":{"admin":false,"push":false,"pull":true}}}
     * base : {"label":"master","ref":"master","sha":"6dcb09b5b57875f334f61aebed695e2e4193db5e","user":{"login":"octocat","id":1,"avatar_url":"https://github.com/images/error/octocat_happy.gif","gravatar_id":"","url":"https://api.github.com/users/octocat","html_url":"https://github.com/octocat","followers_url":"https://api.github.com/users/octocat/followers","following_url":"https://api.github.com/users/octocat/following{/other_user}","gists_url":"https://api.github.com/users/octocat/gists{/gist_id}","starred_url":"https://api.github.com/users/octocat/starred{/owner}{/repo}","subscriptions_url":"https://api.github.com/users/octocat/subscriptions","organizations_url":"https://api.github.com/users/octocat/orgs","repos_url":"https://api.github.com/users/octocat/repos","events_url":"https://api.github.com/users/octocat/events{/privacy}","received_events_url":"https://api.github.com/users/octocat/received_events","type":"User","site_admin":false},"repo":{"id":1296269,"owner":{"login":"octocat","id":1,"avatar_url":"https://github.com/images/error/octocat_happy.gif","gravatar_id":"","url":"https://api.github.com/users/octocat","html_url":"https://github.com/octocat","followers_url":"https://api.github.com/users/octocat/followers","following_url":"https://api.github.com/users/octocat/following{/other_user}","gists_url":"https://api.github.com/users/octocat/gists{/gist_id}","starred_url":"https://api.github.com/users/octocat/starred{/owner}{/repo}","subscriptions_url":"https://api.github.com/users/octocat/subscriptions","organizations_url":"https://api.github.com/users/octocat/orgs","repos_url":"https://api.github.com/users/octocat/repos","events_url":"https://api.github.com/users/octocat/events{/privacy}","received_events_url":"https://api.github.com/users/octocat/received_events","type":"User","site_admin":false},"name":"Hello-World","full_name":"octocat/Hello-World","description":"This your first repo!","private":false,"fork":false,"url":"https://api.github.com/repos/octocat/Hello-World","html_url":"https://github.com/octocat/Hello-World","archive_url":"http://api.github.com/repos/octocat/Hello-World/{archive_format}{/ref}","assignees_url":"http://api.github.com/repos/octocat/Hello-World/assignees{/user}","blobs_url":"http://api.github.com/repos/octocat/Hello-World/git/blobs{/sha}","branches_url":"http://api.github.com/repos/octocat/Hello-World/branches{/branch}","clone_url":"https://github.com/octocat/Hello-World.git","collaborators_url":"http://api.github.com/repos/octocat/Hello-World/collaborators{/collaborator}","comments_url":"http://api.github.com/repos/octocat/Hello-World/comments{/number}","commits_url":"http://api.github.com/repos/octocat/Hello-World/commits{/sha}","compare_url":"http://api.github.com/repos/octocat/Hello-World/compare/{base}...{head}","contents_url":"http://api.github.com/repos/octocat/Hello-World/contents/{+path}","contributors_url":"http://api.github.com/repos/octocat/Hello-World/contributors","deployments_url":"http://api.github.com/repos/octocat/Hello-World/deployments","downloads_url":"http://api.github.com/repos/octocat/Hello-World/downloads","events_url":"http://api.github.com/repos/octocat/Hello-World/events","forks_url":"http://api.github.com/repos/octocat/Hello-World/forks","git_commits_url":"http://api.github.com/repos/octocat/Hello-World/git/commits{/sha}","git_refs_url":"http://api.github.com/repos/octocat/Hello-World/git/refs{/sha}","git_tags_url":"http://api.github.com/repos/octocat/Hello-World/git/tags{/sha}","git_url":"git:github.com/octocat/Hello-World.git","hooks_url":"http://api.github.com/repos/octocat/Hello-World/hooks","issue_comment_url":"http://api.github.com/repos/octocat/Hello-World/issues/comments{/number}","issue_events_url":"http://api.github.com/repos/octocat/Hello-World/issues/events{/number}","issues_url":"http://api.github.com/repos/octocat/Hello-World/issues{/number}","keys_url":"http://api.github.com/repos/octocat/Hello-World/keys{/key_id}","labels_url":"http://api.github.com/repos/octocat/Hello-World/labels{/name}","languages_url":"http://api.github.com/repos/octocat/Hello-World/languages","merges_url":"http://api.github.com/repos/octocat/Hello-World/merges","milestones_url":"http://api.github.com/repos/octocat/Hello-World/milestones{/number}","mirror_url":"git:git.example.com/octocat/Hello-World","notifications_url":"http://api.github.com/repos/octocat/Hello-World/notifications{?since, all, participating}","pulls_url":"http://api.github.com/repos/octocat/Hello-World/pulls{/number}","releases_url":"http://api.github.com/repos/octocat/Hello-World/releases{/id}","ssh_url":"git@github.com:octocat/Hello-World.git","stargazers_url":"http://api.github.com/repos/octocat/Hello-World/stargazers","statuses_url":"http://api.github.com/repos/octocat/Hello-World/statuses/{sha}","subscribers_url":"http://api.github.com/repos/octocat/Hello-World/subscribers","subscription_url":"http://api.github.com/repos/octocat/Hello-World/subscription","svn_url":"https://svn.github.com/octocat/Hello-World","tags_url":"http://api.github.com/repos/octocat/Hello-World/tags","teams_url":"http://api.github.com/repos/octocat/Hello-World/teams","trees_url":"http://api.github.com/repos/octocat/Hello-World/git/trees{/sha}","homepage":"https://github.com","language":null,"forks_count":9,"stargazers_count":80,"watchers_count":80,"size":108,"default_branch":"master","open_issues_count":0,"has_issues":true,"has_wiki":true,"has_pages":false,"has_downloads":true,"pushed_at":"2011-01-26T19:06:43Z","created_at":"2011-01-26T19:01:12Z","updated_at":"2011-01-26T19:14:43Z","permissions":{"admin":false,"push":false,"pull":true}}}
     * _links : {"self":{"href":"https://api.github.com/repos/octocat/Hello-World/pulls/1347"},"html":{"href":"https://github.com/octocat/Hello-World/pull/1347"},"issue":{"href":"https://api.github.com/repos/octocat/Hello-World/issues/1347"},"comments":{"href":"https://api.github.com/repos/octocat/Hello-World/issues/1347/comments"},"review_comments":{"href":"https://api.github.com/repos/octocat/Hello-World/pulls/1347/comments"},"review_comment":{"href":"https://api.github.com/repos/octocat/Hello-World/pulls/comments{/number}"},"commits":{"href":"https://api.github.com/repos/octocat/Hello-World/pulls/1347/commits"},"statuses":{"href":"https://api.github.com/repos/octocat/Hello-World/statuses/6dcb09b5b57875f334f61aebed695e2e4193db5e"}}
     * user : {"login":"octocat","id":1,"avatar_url":"https://github.com/images/error/octocat_happy.gif","gravatar_id":"","url":"https://api.github.com/users/octocat","html_url":"https://github.com/octocat","followers_url":"https://api.github.com/users/octocat/followers","following_url":"https://api.github.com/users/octocat/following{/other_user}","gists_url":"https://api.github.com/users/octocat/gists{/gist_id}","starred_url":"https://api.github.com/users/octocat/starred{/owner}{/repo}","subscriptions_url":"https://api.github.com/users/octocat/subscriptions","organizations_url":"https://api.github.com/users/octocat/orgs","repos_url":"https://api.github.com/users/octocat/repos","events_url":"https://api.github.com/users/octocat/events{/privacy}","received_events_url":"https://api.github.com/users/octocat/received_events","type":"User","site_admin":false}
     * merge_commit_sha : e5bd3914e2e596debea16f433f57875b5b90bcd6
     * merged : false
     * mergeable : true
     * merged_by : {"login":"octocat","id":1,"avatar_url":"https://github.com/images/error/octocat_happy.gif","gravatar_id":"","url":"https://api.github.com/users/octocat","html_url":"https://github.com/octocat","followers_url":"https://api.github.com/users/octocat/followers","following_url":"https://api.github.com/users/octocat/following{/other_user}","gists_url":"https://api.github.com/users/octocat/gists{/gist_id}","starred_url":"https://api.github.com/users/octocat/starred{/owner}{/repo}","subscriptions_url":"https://api.github.com/users/octocat/subscriptions","organizations_url":"https://api.github.com/users/octocat/orgs","repos_url":"https://api.github.com/users/octocat/repos","events_url":"https://api.github.com/users/octocat/events{/privacy}","received_events_url":"https://api.github.com/users/octocat/received_events","type":"User","site_admin":false}
     * comments : 10
     * commits : 3
     * additions : 100
     * deletions : 3
     * changed_files : 5
     */

    private int id;
    private String url;
    private String html_url;
    private String diff_url;
    private String patch_url;
    private String issue_url;
    private String commits_url;
    private String review_comments_url;
    private String review_comment_url;
    private String comments_url;
    private String statuses_url;
    private int number;
    private String state;
    private String title;
    private String body;
    /**
     * login : octocat
     * id : 1
     * avatar_url : https://github.com/images/error/octocat_happy.gif
     * gravatar_id :
     * url : https://api.github.com/users/octocat
     * html_url : https://github.com/octocat
     * followers_url : https://api.github.com/users/octocat/followers
     * following_url : https://api.github.com/users/octocat/following{/other_user}
     * gists_url : https://api.github.com/users/octocat/gists{/gist_id}
     * starred_url : https://api.github.com/users/octocat/starred{/owner}{/repo}
     * subscriptions_url : https://api.github.com/users/octocat/subscriptions
     * organizations_url : https://api.github.com/users/octocat/orgs
     * repos_url : https://api.github.com/users/octocat/repos
     * events_url : https://api.github.com/users/octocat/events{/privacy}
     * received_events_url : https://api.github.com/users/octocat/received_events
     * type : User
     * site_admin : false
     */

    private AssigneeBean assignee;
    /**
     * url : https://api.github.com/repos/octocat/Hello-World/milestones/1
     * html_url : https://github.com/octocat/Hello-World/milestones/v1.0
     * labels_url : https://api.github.com/repos/octocat/Hello-World/milestones/1/labels
     * id : 1002604
     * number : 1
     * state : open
     * title : v1.0
     * description : Tracking milestone for version 1.0
     * creator : {"login":"octocat","id":1,"avatar_url":"https://github.com/images/error/octocat_happy.gif","gravatar_id":"","url":"https://api.github.com/users/octocat","html_url":"https://github.com/octocat","followers_url":"https://api.github.com/users/octocat/followers","following_url":"https://api.github.com/users/octocat/following{/other_user}","gists_url":"https://api.github.com/users/octocat/gists{/gist_id}","starred_url":"https://api.github.com/users/octocat/starred{/owner}{/repo}","subscriptions_url":"https://api.github.com/users/octocat/subscriptions","organizations_url":"https://api.github.com/users/octocat/orgs","repos_url":"https://api.github.com/users/octocat/repos","events_url":"https://api.github.com/users/octocat/events{/privacy}","received_events_url":"https://api.github.com/users/octocat/received_events","type":"User","site_admin":false}
     * open_issues : 4
     * closed_issues : 8
     * created_at : 2011-04-10T20:09:31Z
     * updated_at : 2014-03-03T18:58:10Z
     * closed_at : 2013-02-12T13:22:01Z
     * due_on : 2012-10-09T23:39:01Z
     */

    private MilestoneBean milestone;
    private boolean locked;
    private String created_at;
    private String updated_at;
    private String closed_at;
    private String merged_at;
    /**
     * label : new-topic
     * ref : new-topic
     * sha : 6dcb09b5b57875f334f61aebed695e2e4193db5e
     * user : {"login":"octocat","id":1,"avatar_url":"https://github.com/images/error/octocat_happy.gif","gravatar_id":"","url":"https://api.github.com/users/octocat","html_url":"https://github.com/octocat","followers_url":"https://api.github.com/users/octocat/followers","following_url":"https://api.github.com/users/octocat/following{/other_user}","gists_url":"https://api.github.com/users/octocat/gists{/gist_id}","starred_url":"https://api.github.com/users/octocat/starred{/owner}{/repo}","subscriptions_url":"https://api.github.com/users/octocat/subscriptions","organizations_url":"https://api.github.com/users/octocat/orgs","repos_url":"https://api.github.com/users/octocat/repos","events_url":"https://api.github.com/users/octocat/events{/privacy}","received_events_url":"https://api.github.com/users/octocat/received_events","type":"User","site_admin":false}
     * repo : {"id":1296269,"owner":{"login":"octocat","id":1,"avatar_url":"https://github.com/images/error/octocat_happy.gif","gravatar_id":"","url":"https://api.github.com/users/octocat","html_url":"https://github.com/octocat","followers_url":"https://api.github.com/users/octocat/followers","following_url":"https://api.github.com/users/octocat/following{/other_user}","gists_url":"https://api.github.com/users/octocat/gists{/gist_id}","starred_url":"https://api.github.com/users/octocat/starred{/owner}{/repo}","subscriptions_url":"https://api.github.com/users/octocat/subscriptions","organizations_url":"https://api.github.com/users/octocat/orgs","repos_url":"https://api.github.com/users/octocat/repos","events_url":"https://api.github.com/users/octocat/events{/privacy}","received_events_url":"https://api.github.com/users/octocat/received_events","type":"User","site_admin":false},"name":"Hello-World","full_name":"octocat/Hello-World","description":"This your first repo!","private":false,"fork":false,"url":"https://api.github.com/repos/octocat/Hello-World","html_url":"https://github.com/octocat/Hello-World","archive_url":"http://api.github.com/repos/octocat/Hello-World/{archive_format}{/ref}","assignees_url":"http://api.github.com/repos/octocat/Hello-World/assignees{/user}","blobs_url":"http://api.github.com/repos/octocat/Hello-World/git/blobs{/sha}","branches_url":"http://api.github.com/repos/octocat/Hello-World/branches{/branch}","clone_url":"https://github.com/octocat/Hello-World.git","collaborators_url":"http://api.github.com/repos/octocat/Hello-World/collaborators{/collaborator}","comments_url":"http://api.github.com/repos/octocat/Hello-World/comments{/number}","commits_url":"http://api.github.com/repos/octocat/Hello-World/commits{/sha}","compare_url":"http://api.github.com/repos/octocat/Hello-World/compare/{base}...{head}","contents_url":"http://api.github.com/repos/octocat/Hello-World/contents/{+path}","contributors_url":"http://api.github.com/repos/octocat/Hello-World/contributors","deployments_url":"http://api.github.com/repos/octocat/Hello-World/deployments","downloads_url":"http://api.github.com/repos/octocat/Hello-World/downloads","events_url":"http://api.github.com/repos/octocat/Hello-World/events","forks_url":"http://api.github.com/repos/octocat/Hello-World/forks","git_commits_url":"http://api.github.com/repos/octocat/Hello-World/git/commits{/sha}","git_refs_url":"http://api.github.com/repos/octocat/Hello-World/git/refs{/sha}","git_tags_url":"http://api.github.com/repos/octocat/Hello-World/git/tags{/sha}","git_url":"git:github.com/octocat/Hello-World.git","hooks_url":"http://api.github.com/repos/octocat/Hello-World/hooks","issue_comment_url":"http://api.github.com/repos/octocat/Hello-World/issues/comments{/number}","issue_events_url":"http://api.github.com/repos/octocat/Hello-World/issues/events{/number}","issues_url":"http://api.github.com/repos/octocat/Hello-World/issues{/number}","keys_url":"http://api.github.com/repos/octocat/Hello-World/keys{/key_id}","labels_url":"http://api.github.com/repos/octocat/Hello-World/labels{/name}","languages_url":"http://api.github.com/repos/octocat/Hello-World/languages","merges_url":"http://api.github.com/repos/octocat/Hello-World/merges","milestones_url":"http://api.github.com/repos/octocat/Hello-World/milestones{/number}","mirror_url":"git:git.example.com/octocat/Hello-World","notifications_url":"http://api.github.com/repos/octocat/Hello-World/notifications{?since, all, participating}","pulls_url":"http://api.github.com/repos/octocat/Hello-World/pulls{/number}","releases_url":"http://api.github.com/repos/octocat/Hello-World/releases{/id}","ssh_url":"git@github.com:octocat/Hello-World.git","stargazers_url":"http://api.github.com/repos/octocat/Hello-World/stargazers","statuses_url":"http://api.github.com/repos/octocat/Hello-World/statuses/{sha}","subscribers_url":"http://api.github.com/repos/octocat/Hello-World/subscribers","subscription_url":"http://api.github.com/repos/octocat/Hello-World/subscription","svn_url":"https://svn.github.com/octocat/Hello-World","tags_url":"http://api.github.com/repos/octocat/Hello-World/tags","teams_url":"http://api.github.com/repos/octocat/Hello-World/teams","trees_url":"http://api.github.com/repos/octocat/Hello-World/git/trees{/sha}","homepage":"https://github.com","language":null,"forks_count":9,"stargazers_count":80,"watchers_count":80,"size":108,"default_branch":"master","open_issues_count":0,"has_issues":true,"has_wiki":true,"has_pages":false,"has_downloads":true,"pushed_at":"2011-01-26T19:06:43Z","created_at":"2011-01-26T19:01:12Z","updated_at":"2011-01-26T19:14:43Z","permissions":{"admin":false,"push":false,"pull":true}}
     */

    private HeadBean head;
    /**
     * label : master
     * ref : master
     * sha : 6dcb09b5b57875f334f61aebed695e2e4193db5e
     * user : {"login":"octocat","id":1,"avatar_url":"https://github.com/images/error/octocat_happy.gif","gravatar_id":"","url":"https://api.github.com/users/octocat","html_url":"https://github.com/octocat","followers_url":"https://api.github.com/users/octocat/followers","following_url":"https://api.github.com/users/octocat/following{/other_user}","gists_url":"https://api.github.com/users/octocat/gists{/gist_id}","starred_url":"https://api.github.com/users/octocat/starred{/owner}{/repo}","subscriptions_url":"https://api.github.com/users/octocat/subscriptions","organizations_url":"https://api.github.com/users/octocat/orgs","repos_url":"https://api.github.com/users/octocat/repos","events_url":"https://api.github.com/users/octocat/events{/privacy}","received_events_url":"https://api.github.com/users/octocat/received_events","type":"User","site_admin":false}
     * repo : {"id":1296269,"owner":{"login":"octocat","id":1,"avatar_url":"https://github.com/images/error/octocat_happy.gif","gravatar_id":"","url":"https://api.github.com/users/octocat","html_url":"https://github.com/octocat","followers_url":"https://api.github.com/users/octocat/followers","following_url":"https://api.github.com/users/octocat/following{/other_user}","gists_url":"https://api.github.com/users/octocat/gists{/gist_id}","starred_url":"https://api.github.com/users/octocat/starred{/owner}{/repo}","subscriptions_url":"https://api.github.com/users/octocat/subscriptions","organizations_url":"https://api.github.com/users/octocat/orgs","repos_url":"https://api.github.com/users/octocat/repos","events_url":"https://api.github.com/users/octocat/events{/privacy}","received_events_url":"https://api.github.com/users/octocat/received_events","type":"User","site_admin":false},"name":"Hello-World","full_name":"octocat/Hello-World","description":"This your first repo!","private":false,"fork":false,"url":"https://api.github.com/repos/octocat/Hello-World","html_url":"https://github.com/octocat/Hello-World","archive_url":"http://api.github.com/repos/octocat/Hello-World/{archive_format}{/ref}","assignees_url":"http://api.github.com/repos/octocat/Hello-World/assignees{/user}","blobs_url":"http://api.github.com/repos/octocat/Hello-World/git/blobs{/sha}","branches_url":"http://api.github.com/repos/octocat/Hello-World/branches{/branch}","clone_url":"https://github.com/octocat/Hello-World.git","collaborators_url":"http://api.github.com/repos/octocat/Hello-World/collaborators{/collaborator}","comments_url":"http://api.github.com/repos/octocat/Hello-World/comments{/number}","commits_url":"http://api.github.com/repos/octocat/Hello-World/commits{/sha}","compare_url":"http://api.github.com/repos/octocat/Hello-World/compare/{base}...{head}","contents_url":"http://api.github.com/repos/octocat/Hello-World/contents/{+path}","contributors_url":"http://api.github.com/repos/octocat/Hello-World/contributors","deployments_url":"http://api.github.com/repos/octocat/Hello-World/deployments","downloads_url":"http://api.github.com/repos/octocat/Hello-World/downloads","events_url":"http://api.github.com/repos/octocat/Hello-World/events","forks_url":"http://api.github.com/repos/octocat/Hello-World/forks","git_commits_url":"http://api.github.com/repos/octocat/Hello-World/git/commits{/sha}","git_refs_url":"http://api.github.com/repos/octocat/Hello-World/git/refs{/sha}","git_tags_url":"http://api.github.com/repos/octocat/Hello-World/git/tags{/sha}","git_url":"git:github.com/octocat/Hello-World.git","hooks_url":"http://api.github.com/repos/octocat/Hello-World/hooks","issue_comment_url":"http://api.github.com/repos/octocat/Hello-World/issues/comments{/number}","issue_events_url":"http://api.github.com/repos/octocat/Hello-World/issues/events{/number}","issues_url":"http://api.github.com/repos/octocat/Hello-World/issues{/number}","keys_url":"http://api.github.com/repos/octocat/Hello-World/keys{/key_id}","labels_url":"http://api.github.com/repos/octocat/Hello-World/labels{/name}","languages_url":"http://api.github.com/repos/octocat/Hello-World/languages","merges_url":"http://api.github.com/repos/octocat/Hello-World/merges","milestones_url":"http://api.github.com/repos/octocat/Hello-World/milestones{/number}","mirror_url":"git:git.example.com/octocat/Hello-World","notifications_url":"http://api.github.com/repos/octocat/Hello-World/notifications{?since, all, participating}","pulls_url":"http://api.github.com/repos/octocat/Hello-World/pulls{/number}","releases_url":"http://api.github.com/repos/octocat/Hello-World/releases{/id}","ssh_url":"git@github.com:octocat/Hello-World.git","stargazers_url":"http://api.github.com/repos/octocat/Hello-World/stargazers","statuses_url":"http://api.github.com/repos/octocat/Hello-World/statuses/{sha}","subscribers_url":"http://api.github.com/repos/octocat/Hello-World/subscribers","subscription_url":"http://api.github.com/repos/octocat/Hello-World/subscription","svn_url":"https://svn.github.com/octocat/Hello-World","tags_url":"http://api.github.com/repos/octocat/Hello-World/tags","teams_url":"http://api.github.com/repos/octocat/Hello-World/teams","trees_url":"http://api.github.com/repos/octocat/Hello-World/git/trees{/sha}","homepage":"https://github.com","language":null,"forks_count":9,"stargazers_count":80,"watchers_count":80,"size":108,"default_branch":"master","open_issues_count":0,"has_issues":true,"has_wiki":true,"has_pages":false,"has_downloads":true,"pushed_at":"2011-01-26T19:06:43Z","created_at":"2011-01-26T19:01:12Z","updated_at":"2011-01-26T19:14:43Z","permissions":{"admin":false,"push":false,"pull":true}}
     */

    private BaseBean base;
    /**
     * self : {"href":"https://api.github.com/repos/octocat/Hello-World/pulls/1347"}
     * html : {"href":"https://github.com/octocat/Hello-World/pull/1347"}
     * issue : {"href":"https://api.github.com/repos/octocat/Hello-World/issues/1347"}
     * comments : {"href":"https://api.github.com/repos/octocat/Hello-World/issues/1347/comments"}
     * review_comments : {"href":"https://api.github.com/repos/octocat/Hello-World/pulls/1347/comments"}
     * review_comment : {"href":"https://api.github.com/repos/octocat/Hello-World/pulls/comments{/number}"}
     * commits : {"href":"https://api.github.com/repos/octocat/Hello-World/pulls/1347/commits"}
     * statuses : {"href":"https://api.github.com/repos/octocat/Hello-World/statuses/6dcb09b5b57875f334f61aebed695e2e4193db5e"}
     */

    private LinksBean _links;
    /**
     * login : octocat
     * id : 1
     * avatar_url : https://github.com/images/error/octocat_happy.gif
     * gravatar_id :
     * url : https://api.github.com/users/octocat
     * html_url : https://github.com/octocat
     * followers_url : https://api.github.com/users/octocat/followers
     * following_url : https://api.github.com/users/octocat/following{/other_user}
     * gists_url : https://api.github.com/users/octocat/gists{/gist_id}
     * starred_url : https://api.github.com/users/octocat/starred{/owner}{/repo}
     * subscriptions_url : https://api.github.com/users/octocat/subscriptions
     * organizations_url : https://api.github.com/users/octocat/orgs
     * repos_url : https://api.github.com/users/octocat/repos
     * events_url : https://api.github.com/users/octocat/events{/privacy}
     * received_events_url : https://api.github.com/users/octocat/received_events
     * type : User
     * site_admin : false
     */

    private UserBean user;
    private String merge_commit_sha;
    private boolean merged;
    private boolean mergeable;
    /**
     * login : octocat
     * id : 1
     * avatar_url : https://github.com/images/error/octocat_happy.gif
     * gravatar_id :
     * url : https://api.github.com/users/octocat
     * html_url : https://github.com/octocat
     * followers_url : https://api.github.com/users/octocat/followers
     * following_url : https://api.github.com/users/octocat/following{/other_user}
     * gists_url : https://api.github.com/users/octocat/gists{/gist_id}
     * starred_url : https://api.github.com/users/octocat/starred{/owner}{/repo}
     * subscriptions_url : https://api.github.com/users/octocat/subscriptions
     * organizations_url : https://api.github.com/users/octocat/orgs
     * repos_url : https://api.github.com/users/octocat/repos
     * events_url : https://api.github.com/users/octocat/events{/privacy}
     * received_events_url : https://api.github.com/users/octocat/received_events
     * type : User
     * site_admin : false
     */

    private UserBean merged_by;
    private int comments;
    private int commits;
    private int additions;
    private int deletions;
    private int changed_files;

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public AssigneeBean getAssignee() {
        return assignee;
    }

    public void setAssignee(AssigneeBean assignee) {
        this.assignee = assignee;
    }

    public MilestoneBean getMilestone() {
        return milestone;
    }

    public void setMilestone(MilestoneBean milestone) {
        this.milestone = milestone;
    }

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
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

    public String getClosed_at() {
        return closed_at;
    }

    public void setClosed_at(String closed_at) {
        this.closed_at = closed_at;
    }

    public String getMerged_at() {
        return merged_at;
    }

    public void setMerged_at(String merged_at) {
        this.merged_at = merged_at;
    }

    public HeadBean getHead() {
        return head;
    }

    public void setHead(HeadBean head) {
        this.head = head;
    }

    public BaseBean getBase() {
        return base;
    }

    public void setBase(BaseBean base) {
        this.base = base;
    }

    public LinksBean get_links() {
        return _links;
    }

    public void set_links(LinksBean _links) {
        this._links = _links;
    }

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }

    public String getMerge_commit_sha() {
        return merge_commit_sha;
    }

    public void setMerge_commit_sha(String merge_commit_sha) {
        this.merge_commit_sha = merge_commit_sha;
    }

    public boolean isMerged() {
        return merged;
    }

    public void setMerged(boolean merged) {
        this.merged = merged;
    }

    public boolean isMergeable() {
        return mergeable;
    }

    public void setMergeable(boolean mergeable) {
        this.mergeable = mergeable;
    }

    public UserBean getMerged_by() {
        return merged_by;
    }

    public void setMerged_by(UserBean merged_by) {
        this.merged_by = merged_by;
    }

    public int getComments() {
        return comments;
    }

    public void setComments(int comments) {
        this.comments = comments;
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

    public static class HeadBean implements Parcelable {
        private String label;
        private String ref;
        private String sha;
        /**
         * login : octocat
         * id : 1
         * avatar_url : https://github.com/images/error/octocat_happy.gif
         * gravatar_id :
         * url : https://api.github.com/users/octocat
         * html_url : https://github.com/octocat
         * followers_url : https://api.github.com/users/octocat/followers
         * following_url : https://api.github.com/users/octocat/following{/other_user}
         * gists_url : https://api.github.com/users/octocat/gists{/gist_id}
         * starred_url : https://api.github.com/users/octocat/starred{/owner}{/repo}
         * subscriptions_url : https://api.github.com/users/octocat/subscriptions
         * organizations_url : https://api.github.com/users/octocat/orgs
         * repos_url : https://api.github.com/users/octocat/repos
         * events_url : https://api.github.com/users/octocat/events{/privacy}
         * received_events_url : https://api.github.com/users/octocat/received_events
         * type : User
         * site_admin : false
         */

        private UserBean user;
        /**
         * id : 1296269
         * owner : {"login":"octocat","id":1,"avatar_url":"https://github.com/images/error/octocat_happy.gif","gravatar_id":"","url":"https://api.github.com/users/octocat","html_url":"https://github.com/octocat","followers_url":"https://api.github.com/users/octocat/followers","following_url":"https://api.github.com/users/octocat/following{/other_user}","gists_url":"https://api.github.com/users/octocat/gists{/gist_id}","starred_url":"https://api.github.com/users/octocat/starred{/owner}{/repo}","subscriptions_url":"https://api.github.com/users/octocat/subscriptions","organizations_url":"https://api.github.com/users/octocat/orgs","repos_url":"https://api.github.com/users/octocat/repos","events_url":"https://api.github.com/users/octocat/events{/privacy}","received_events_url":"https://api.github.com/users/octocat/received_events","type":"User","site_admin":false}
         * name : Hello-World
         * full_name : octocat/Hello-World
         * description : This your first repo!
         * private : false
         * fork : false
         * url : https://api.github.com/repos/octocat/Hello-World
         * html_url : https://github.com/octocat/Hello-World
         * archive_url : http://api.github.com/repos/octocat/Hello-World/{archive_format}{/ref}
         * assignees_url : http://api.github.com/repos/octocat/Hello-World/assignees{/user}
         * blobs_url : http://api.github.com/repos/octocat/Hello-World/git/blobs{/sha}
         * branches_url : http://api.github.com/repos/octocat/Hello-World/branches{/branch}
         * clone_url : https://github.com/octocat/Hello-World.git
         * collaborators_url : http://api.github.com/repos/octocat/Hello-World/collaborators{/collaborator}
         * comments_url : http://api.github.com/repos/octocat/Hello-World/comments{/number}
         * commits_url : http://api.github.com/repos/octocat/Hello-World/commits{/sha}
         * compare_url : http://api.github.com/repos/octocat/Hello-World/compare/{base}...{head}
         * contents_url : http://api.github.com/repos/octocat/Hello-World/contents/{+path}
         * contributors_url : http://api.github.com/repos/octocat/Hello-World/contributors
         * deployments_url : http://api.github.com/repos/octocat/Hello-World/deployments
         * downloads_url : http://api.github.com/repos/octocat/Hello-World/downloads
         * events_url : http://api.github.com/repos/octocat/Hello-World/events
         * forks_url : http://api.github.com/repos/octocat/Hello-World/forks
         * git_commits_url : http://api.github.com/repos/octocat/Hello-World/git/commits{/sha}
         * git_refs_url : http://api.github.com/repos/octocat/Hello-World/git/refs{/sha}
         * git_tags_url : http://api.github.com/repos/octocat/Hello-World/git/tags{/sha}
         * git_url : git:github.com/octocat/Hello-World.git
         * hooks_url : http://api.github.com/repos/octocat/Hello-World/hooks
         * issue_comment_url : http://api.github.com/repos/octocat/Hello-World/issues/comments{/number}
         * issue_events_url : http://api.github.com/repos/octocat/Hello-World/issues/events{/number}
         * issues_url : http://api.github.com/repos/octocat/Hello-World/issues{/number}
         * keys_url : http://api.github.com/repos/octocat/Hello-World/keys{/key_id}
         * labels_url : http://api.github.com/repos/octocat/Hello-World/labels{/name}
         * languages_url : http://api.github.com/repos/octocat/Hello-World/languages
         * merges_url : http://api.github.com/repos/octocat/Hello-World/merges
         * milestones_url : http://api.github.com/repos/octocat/Hello-World/milestones{/number}
         * mirror_url : git:git.example.com/octocat/Hello-World
         * notifications_url : http://api.github.com/repos/octocat/Hello-World/notifications{?since, all, participating}
         * pulls_url : http://api.github.com/repos/octocat/Hello-World/pulls{/number}
         * releases_url : http://api.github.com/repos/octocat/Hello-World/releases{/id}
         * ssh_url : git@github.com:octocat/Hello-World.git
         * stargazers_url : http://api.github.com/repos/octocat/Hello-World/stargazers
         * statuses_url : http://api.github.com/repos/octocat/Hello-World/statuses/{sha}
         * subscribers_url : http://api.github.com/repos/octocat/Hello-World/subscribers
         * subscription_url : http://api.github.com/repos/octocat/Hello-World/subscription
         * svn_url : https://svn.github.com/octocat/Hello-World
         * tags_url : http://api.github.com/repos/octocat/Hello-World/tags
         * teams_url : http://api.github.com/repos/octocat/Hello-World/teams
         * trees_url : http://api.github.com/repos/octocat/Hello-World/git/trees{/sha}
         * homepage : https://github.com
         * language : null
         * forks_count : 9
         * stargazers_count : 80
         * watchers_count : 80
         * size : 108
         * default_branch : master
         * open_issues_count : 0
         * has_issues : true
         * has_wiki : true
         * has_pages : false
         * has_downloads : true
         * pushed_at : 2011-01-26T19:06:43Z
         * created_at : 2011-01-26T19:01:12Z
         * updated_at : 2011-01-26T19:14:43Z
         * permissions : {"admin":false,"push":false,"pull":true}
         */

        private RepositoriesBean repo;

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

        public UserBean getUser() {
            return user;
        }

        public void setUser(UserBean user) {
            this.user = user;
        }

        public RepositoriesBean getRepo() {
            return repo;
        }

        public void setRepo(RepositoriesBean repo) {
            this.repo = repo;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.label);
            dest.writeString(this.ref);
            dest.writeString(this.sha);
            dest.writeParcelable(this.user, flags);
            dest.writeParcelable(this.repo, flags);
        }

        public HeadBean() {
        }

        protected HeadBean(Parcel in) {
            this.label = in.readString();
            this.ref = in.readString();
            this.sha = in.readString();
            this.user = in.readParcelable(UserBean.class.getClassLoader());
            this.repo = in.readParcelable(RepositoriesBean.class.getClassLoader());
        }

        public static final Creator<HeadBean> CREATOR = new Creator<HeadBean>() {
            @Override
            public HeadBean createFromParcel(Parcel source) {
                return new HeadBean(source);
            }

            @Override
            public HeadBean[] newArray(int size) {
                return new HeadBean[size];
            }
        };
    }

    public static class BaseBean implements Parcelable {
        private String label;
        private String ref;
        private String sha;
        /**
         * login : octocat
         * id : 1
         * avatar_url : https://github.com/images/error/octocat_happy.gif
         * gravatar_id :
         * url : https://api.github.com/users/octocat
         * html_url : https://github.com/octocat
         * followers_url : https://api.github.com/users/octocat/followers
         * following_url : https://api.github.com/users/octocat/following{/other_user}
         * gists_url : https://api.github.com/users/octocat/gists{/gist_id}
         * starred_url : https://api.github.com/users/octocat/starred{/owner}{/repo}
         * subscriptions_url : https://api.github.com/users/octocat/subscriptions
         * organizations_url : https://api.github.com/users/octocat/orgs
         * repos_url : https://api.github.com/users/octocat/repos
         * events_url : https://api.github.com/users/octocat/events{/privacy}
         * received_events_url : https://api.github.com/users/octocat/received_events
         * type : User
         * site_admin : false
         */

        private UserBean user;
        /**
         * id : 1296269
         * owner : {"login":"octocat","id":1,"avatar_url":"https://github.com/images/error/octocat_happy.gif","gravatar_id":"","url":"https://api.github.com/users/octocat","html_url":"https://github.com/octocat","followers_url":"https://api.github.com/users/octocat/followers","following_url":"https://api.github.com/users/octocat/following{/other_user}","gists_url":"https://api.github.com/users/octocat/gists{/gist_id}","starred_url":"https://api.github.com/users/octocat/starred{/owner}{/repo}","subscriptions_url":"https://api.github.com/users/octocat/subscriptions","organizations_url":"https://api.github.com/users/octocat/orgs","repos_url":"https://api.github.com/users/octocat/repos","events_url":"https://api.github.com/users/octocat/events{/privacy}","received_events_url":"https://api.github.com/users/octocat/received_events","type":"User","site_admin":false}
         * name : Hello-World
         * full_name : octocat/Hello-World
         * description : This your first repo!
         * private : false
         * fork : false
         * url : https://api.github.com/repos/octocat/Hello-World
         * html_url : https://github.com/octocat/Hello-World
         * archive_url : http://api.github.com/repos/octocat/Hello-World/{archive_format}{/ref}
         * assignees_url : http://api.github.com/repos/octocat/Hello-World/assignees{/user}
         * blobs_url : http://api.github.com/repos/octocat/Hello-World/git/blobs{/sha}
         * branches_url : http://api.github.com/repos/octocat/Hello-World/branches{/branch}
         * clone_url : https://github.com/octocat/Hello-World.git
         * collaborators_url : http://api.github.com/repos/octocat/Hello-World/collaborators{/collaborator}
         * comments_url : http://api.github.com/repos/octocat/Hello-World/comments{/number}
         * commits_url : http://api.github.com/repos/octocat/Hello-World/commits{/sha}
         * compare_url : http://api.github.com/repos/octocat/Hello-World/compare/{base}...{head}
         * contents_url : http://api.github.com/repos/octocat/Hello-World/contents/{+path}
         * contributors_url : http://api.github.com/repos/octocat/Hello-World/contributors
         * deployments_url : http://api.github.com/repos/octocat/Hello-World/deployments
         * downloads_url : http://api.github.com/repos/octocat/Hello-World/downloads
         * events_url : http://api.github.com/repos/octocat/Hello-World/events
         * forks_url : http://api.github.com/repos/octocat/Hello-World/forks
         * git_commits_url : http://api.github.com/repos/octocat/Hello-World/git/commits{/sha}
         * git_refs_url : http://api.github.com/repos/octocat/Hello-World/git/refs{/sha}
         * git_tags_url : http://api.github.com/repos/octocat/Hello-World/git/tags{/sha}
         * git_url : git:github.com/octocat/Hello-World.git
         * hooks_url : http://api.github.com/repos/octocat/Hello-World/hooks
         * issue_comment_url : http://api.github.com/repos/octocat/Hello-World/issues/comments{/number}
         * issue_events_url : http://api.github.com/repos/octocat/Hello-World/issues/events{/number}
         * issues_url : http://api.github.com/repos/octocat/Hello-World/issues{/number}
         * keys_url : http://api.github.com/repos/octocat/Hello-World/keys{/key_id}
         * labels_url : http://api.github.com/repos/octocat/Hello-World/labels{/name}
         * languages_url : http://api.github.com/repos/octocat/Hello-World/languages
         * merges_url : http://api.github.com/repos/octocat/Hello-World/merges
         * milestones_url : http://api.github.com/repos/octocat/Hello-World/milestones{/number}
         * mirror_url : git:git.example.com/octocat/Hello-World
         * notifications_url : http://api.github.com/repos/octocat/Hello-World/notifications{?since, all, participating}
         * pulls_url : http://api.github.com/repos/octocat/Hello-World/pulls{/number}
         * releases_url : http://api.github.com/repos/octocat/Hello-World/releases{/id}
         * ssh_url : git@github.com:octocat/Hello-World.git
         * stargazers_url : http://api.github.com/repos/octocat/Hello-World/stargazers
         * statuses_url : http://api.github.com/repos/octocat/Hello-World/statuses/{sha}
         * subscribers_url : http://api.github.com/repos/octocat/Hello-World/subscribers
         * subscription_url : http://api.github.com/repos/octocat/Hello-World/subscription
         * svn_url : https://svn.github.com/octocat/Hello-World
         * tags_url : http://api.github.com/repos/octocat/Hello-World/tags
         * teams_url : http://api.github.com/repos/octocat/Hello-World/teams
         * trees_url : http://api.github.com/repos/octocat/Hello-World/git/trees{/sha}
         * homepage : https://github.com
         * language : null
         * forks_count : 9
         * stargazers_count : 80
         * watchers_count : 80
         * size : 108
         * default_branch : master
         * open_issues_count : 0
         * has_issues : true
         * has_wiki : true
         * has_pages : false
         * has_downloads : true
         * pushed_at : 2011-01-26T19:06:43Z
         * created_at : 2011-01-26T19:01:12Z
         * updated_at : 2011-01-26T19:14:43Z
         * permissions : {"admin":false,"push":false,"pull":true}
         */

        private RepositoriesBean repo;

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

        public UserBean getUser() {
            return user;
        }

        public void setUser(UserBean user) {
            this.user = user;
        }

        public RepositoriesBean getRepo() {
            return repo;
        }

        public void setRepo(RepositoriesBean repo) {
            this.repo = repo;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.label);
            dest.writeString(this.ref);
            dest.writeString(this.sha);
            dest.writeParcelable(this.user, flags);
            dest.writeParcelable(this.repo, flags);
        }

        public BaseBean() {
        }

        protected BaseBean(Parcel in) {
            this.label = in.readString();
            this.ref = in.readString();
            this.sha = in.readString();
            this.user = in.readParcelable(UserBean.class.getClassLoader());
            this.repo = in.readParcelable(RepositoriesBean.class.getClassLoader());
        }

        public static final Creator<BaseBean> CREATOR = new Creator<BaseBean>() {
            @Override
            public BaseBean createFromParcel(Parcel source) {
                return new BaseBean(source);
            }

            @Override
            public BaseBean[] newArray(int size) {
                return new BaseBean[size];
            }
        };
    }

    public static class LinksBean implements Parcelable {
        /**
         * href : https://api.github.com/repos/octocat/Hello-World/pulls/1347
         */

        private HrefBean self;
        /**
         * href : https://github.com/octocat/Hello-World/pull/1347
         */

        private HrefBean html;
        /**
         * href : https://api.github.com/repos/octocat/Hello-World/issues/1347
         */

        private HrefBean issue;
        /**
         * href : https://api.github.com/repos/octocat/Hello-World/issues/1347/comments
         */

        private HrefBean comments;
        /**
         * href : https://api.github.com/repos/octocat/Hello-World/pulls/1347/comments
         */

        private HrefBean review_comments;
        /**
         * href : https://api.github.com/repos/octocat/Hello-World/pulls/comments{/number}
         */

        private HrefBean review_comment;
        /**
         * href : https://api.github.com/repos/octocat/Hello-World/pulls/1347/commits
         */

        private HrefBean commits;
        /**
         * href : https://api.github.com/repos/octocat/Hello-World/statuses/6dcb09b5b57875f334f61aebed695e2e4193db5e
         */

        private HrefBean statuses;

        public HrefBean getSelf() {
            return self;
        }

        public void setSelf(HrefBean self) {
            this.self = self;
        }

        public HrefBean getHtml() {
            return html;
        }

        public void setHtml(HrefBean html) {
            this.html = html;
        }

        public HrefBean getIssue() {
            return issue;
        }

        public void setIssue(HrefBean issue) {
            this.issue = issue;
        }

        public HrefBean getComments() {
            return comments;
        }

        public void setComments(HrefBean comments) {
            this.comments = comments;
        }

        public HrefBean getReview_comments() {
            return review_comments;
        }

        public void setReview_comments(HrefBean review_comments) {
            this.review_comments = review_comments;
        }

        public HrefBean getReview_comment() {
            return review_comment;
        }

        public void setReview_comment(HrefBean review_comment) {
            this.review_comment = review_comment;
        }

        public HrefBean getCommits() {
            return commits;
        }

        public void setCommits(HrefBean commits) {
            this.commits = commits;
        }

        public HrefBean getStatuses() {
            return statuses;
        }

        public void setStatuses(HrefBean statuses) {
            this.statuses = statuses;
        }

        public static class HrefBean implements Parcelable {
            private String href;

            public void setHref(String href) {
                this.href = href;
            }

            public String getHref() {
                return href;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(this.href);
            }

            public HrefBean() {
            }

            protected HrefBean(Parcel in) {
                this.href = in.readString();
            }

            public static final Creator<HrefBean> CREATOR = new Creator<HrefBean>() {
                @Override
                public HrefBean createFromParcel(Parcel source) {
                    return new HrefBean(source);
                }

                @Override
                public HrefBean[] newArray(int size) {
                    return new HrefBean[size];
                }
            };
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeParcelable(this.self, flags);
            dest.writeParcelable(this.html, flags);
            dest.writeParcelable(this.issue, flags);
            dest.writeParcelable(this.comments, flags);
            dest.writeParcelable(this.review_comments, flags);
            dest.writeParcelable(this.review_comment, flags);
            dest.writeParcelable(this.commits, flags);
            dest.writeParcelable(this.statuses, flags);
        }

        public LinksBean() {
        }

        protected LinksBean(Parcel in) {
            this.self = in.readParcelable(HrefBean.class.getClassLoader());
            this.html = in.readParcelable(HrefBean.class.getClassLoader());
            this.issue = in.readParcelable(HrefBean.class.getClassLoader());
            this.comments = in.readParcelable(HrefBean.class.getClassLoader());
            this.review_comments = in.readParcelable(HrefBean.class.getClassLoader());
            this.review_comment = in.readParcelable(HrefBean.class.getClassLoader());
            this.commits = in.readParcelable(HrefBean.class.getClassLoader());
            this.statuses = in.readParcelable(HrefBean.class.getClassLoader());
        }

        public static final Creator<LinksBean> CREATOR = new Creator<LinksBean>() {
            @Override
            public LinksBean createFromParcel(Parcel source) {
                return new LinksBean(source);
            }

            @Override
            public LinksBean[] newArray(int size) {
                return new LinksBean[size];
            }
        };
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.url);
        dest.writeString(this.html_url);
        dest.writeString(this.diff_url);
        dest.writeString(this.patch_url);
        dest.writeString(this.issue_url);
        dest.writeString(this.commits_url);
        dest.writeString(this.review_comments_url);
        dest.writeString(this.review_comment_url);
        dest.writeString(this.comments_url);
        dest.writeString(this.statuses_url);
        dest.writeInt(this.number);
        dest.writeString(this.state);
        dest.writeString(this.title);
        dest.writeString(this.body);
        dest.writeParcelable(this.assignee, flags);
        dest.writeParcelable(this.milestone, flags);
        dest.writeByte(this.locked ? (byte) 1 : (byte) 0);
        dest.writeString(this.created_at);
        dest.writeString(this.updated_at);
        dest.writeString(this.closed_at);
        dest.writeString(this.merged_at);
        dest.writeParcelable(this.head, flags);
        dest.writeParcelable(this.base, flags);
        dest.writeParcelable(this._links, flags);
        dest.writeParcelable(this.user, flags);
        dest.writeString(this.merge_commit_sha);
        dest.writeByte(this.merged ? (byte) 1 : (byte) 0);
        dest.writeByte(this.mergeable ? (byte) 1 : (byte) 0);
        dest.writeParcelable(this.merged_by, flags);
        dest.writeInt(this.comments);
        dest.writeInt(this.commits);
        dest.writeInt(this.additions);
        dest.writeInt(this.deletions);
        dest.writeInt(this.changed_files);
    }

    public PullRequestBean() {
    }

    protected PullRequestBean(Parcel in) {
        this.id = in.readInt();
        this.url = in.readString();
        this.html_url = in.readString();
        this.diff_url = in.readString();
        this.patch_url = in.readString();
        this.issue_url = in.readString();
        this.commits_url = in.readString();
        this.review_comments_url = in.readString();
        this.review_comment_url = in.readString();
        this.comments_url = in.readString();
        this.statuses_url = in.readString();
        this.number = in.readInt();
        this.state = in.readString();
        this.title = in.readString();
        this.body = in.readString();
        this.assignee = in.readParcelable(AssigneeBean.class.getClassLoader());
        this.milestone = in.readParcelable(MilestoneBean.class.getClassLoader());
        this.locked = in.readByte() != 0;
        this.created_at = in.readString();
        this.updated_at = in.readString();
        this.closed_at = in.readString();
        this.merged_at = in.readString();
        this.head = in.readParcelable(HeadBean.class.getClassLoader());
        this.base = in.readParcelable(BaseBean.class.getClassLoader());
        this._links = in.readParcelable(LinksBean.class.getClassLoader());
        this.user = in.readParcelable(UserBean.class.getClassLoader());
        this.merge_commit_sha = in.readString();
        this.merged = in.readByte() != 0;
        this.mergeable = in.readByte() != 0;
        this.merged_by = in.readParcelable(UserBean.class.getClassLoader());
        this.comments = in.readInt();
        this.commits = in.readInt();
        this.additions = in.readInt();
        this.deletions = in.readInt();
        this.changed_files = in.readInt();
    }

    public static final Parcelable.Creator<PullRequestBean> CREATOR = new Parcelable.Creator<PullRequestBean>() {
        @Override
        public PullRequestBean createFromParcel(Parcel source) {
            return new PullRequestBean(source);
        }

        @Override
        public PullRequestBean[] newArray(int size) {
            return new PullRequestBean[size];
        }
    };
}
