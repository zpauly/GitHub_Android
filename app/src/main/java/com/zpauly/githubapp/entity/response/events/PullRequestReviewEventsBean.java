package com.zpauly.githubapp.entity.response.events;

import com.google.gson.annotations.SerializedName;

/**
 * Created by root on 16-7-19.
 */

public class PullRequestReviewEventsBean extends EventsBean.PayloadBean {
    /**
     * action : created
     * comment : {"url":"https://api.github.com/repos/baxterthehacker/public-repo/pulls/comments/29724692","id":29724692,"diff_hunk":"@@ -1 +1 @@\n-# public-repo","path":"README.md","position":1,"original_position":1,"commit_id":"0d1a26e67d8f5eaf1f6ba5c57fc3c7d91ac0fd1c","original_commit_id":"0d1a26e67d8f5eaf1f6ba5c57fc3c7d91ac0fd1c","user":{"login":"baxterthehacker","id":6752317,"avatar_url":"https://avatars.githubusercontent.com/u/6752317?v=3","gravatar_id":"","url":"https://api.github.com/users/baxterthehacker","html_url":"https://github.com/baxterthehacker","followers_url":"https://api.github.com/users/baxterthehacker/followers","following_url":"https://api.github.com/users/baxterthehacker/following{/other_user}","gists_url":"https://api.github.com/users/baxterthehacker/gists{/gist_id}","starred_url":"https://api.github.com/users/baxterthehacker/starred{/owner}{/repo}","subscriptions_url":"https://api.github.com/users/baxterthehacker/subscriptions","organizations_url":"https://api.github.com/users/baxterthehacker/orgs","repos_url":"https://api.github.com/users/baxterthehacker/repos","events_url":"https://api.github.com/users/baxterthehacker/events{/privacy}","received_events_url":"https://api.github.com/users/baxterthehacker/received_events","type":"User","site_admin":false},"body":"Maybe you should use more emojji on this line.","created_at":"2015-05-05T23:40:27Z","updated_at":"2015-05-05T23:40:27Z","html_url":"https://github.com/baxterthehacker/public-repo/pull/1#discussion_r29724692","pull_request_url":"https://api.github.com/repos/baxterthehacker/public-repo/pulls/1","_links":{"self":{"href":"https://api.github.com/repos/baxterthehacker/public-repo/pulls/comments/29724692"},"html":{"href":"https://github.com/baxterthehacker/public-repo/pull/1#discussion_r29724692"},"pull_request":{"href":"https://api.github.com/repos/baxterthehacker/public-repo/pulls/1"}}}
     * pull_request : {"url":"https://api.github.com/repos/baxterthehacker/public-repo/pulls/1","id":34778301,"html_url":"https://github.com/baxterthehacker/public-repo/pull/1","diff_url":"https://github.com/baxterthehacker/public-repo/pull/1.diff","patch_url":"https://github.com/baxterthehacker/public-repo/pull/1.patch","issue_url":"https://api.github.com/repos/baxterthehacker/public-repo/issues/1","number":1,"state":"open","locked":false,"title":"Update the README with new information","user":{"login":"baxterthehacker","id":6752317,"avatar_url":"https://avatars.githubusercontent.com/u/6752317?v=3","gravatar_id":"","url":"https://api.github.com/users/baxterthehacker","html_url":"https://github.com/baxterthehacker","followers_url":"https://api.github.com/users/baxterthehacker/followers","following_url":"https://api.github.com/users/baxterthehacker/following{/other_user}","gists_url":"https://api.github.com/users/baxterthehacker/gists{/gist_id}","starred_url":"https://api.github.com/users/baxterthehacker/starred{/owner}{/repo}","subscriptions_url":"https://api.github.com/users/baxterthehacker/subscriptions","organizations_url":"https://api.github.com/users/baxterthehacker/orgs","repos_url":"https://api.github.com/users/baxterthehacker/repos","events_url":"https://api.github.com/users/baxterthehacker/events{/privacy}","received_events_url":"https://api.github.com/users/baxterthehacker/received_events","type":"User","site_admin":false},"body":"This is a pretty simple change that we need to pull into master.","created_at":"2015-05-05T23:40:27Z","updated_at":"2015-05-05T23:40:27Z","closed_at":null,"merged_at":null,"merge_commit_sha":"18721552ba489fb84e12958c1b5694b5475f7991","assignee":null,"milestone":null,"commits_url":"https://api.github.com/repos/baxterthehacker/public-repo/pulls/1/commits","review_comments_url":"https://api.github.com/repos/baxterthehacker/public-repo/pulls/1/comments","review_comment_url":"https://api.github.com/repos/baxterthehacker/public-repo/pulls/comments{/number}","comments_url":"https://api.github.com/repos/baxterthehacker/public-repo/issues/1/comments","statuses_url":"https://api.github.com/repos/baxterthehacker/public-repo/statuses/0d1a26e67d8f5eaf1f6ba5c57fc3c7d91ac0fd1c","head":{"label":"baxterthehacker:changes","ref":"changes","sha":"0d1a26e67d8f5eaf1f6ba5c57fc3c7d91ac0fd1c","user":{"login":"baxterthehacker","id":6752317,"avatar_url":"https://avatars.githubusercontent.com/u/6752317?v=3","gravatar_id":"","url":"https://api.github.com/users/baxterthehacker","html_url":"https://github.com/baxterthehacker","followers_url":"https://api.github.com/users/baxterthehacker/followers","following_url":"https://api.github.com/users/baxterthehacker/following{/other_user}","gists_url":"https://api.github.com/users/baxterthehacker/gists{/gist_id}","starred_url":"https://api.github.com/users/baxterthehacker/starred{/owner}{/repo}","subscriptions_url":"https://api.github.com/users/baxterthehacker/subscriptions","organizations_url":"https://api.github.com/users/baxterthehacker/orgs","repos_url":"https://api.github.com/users/baxterthehacker/repos","events_url":"https://api.github.com/users/baxterthehacker/events{/privacy}","received_events_url":"https://api.github.com/users/baxterthehacker/received_events","type":"User","site_admin":false},"repo":{"id":35129377,"name":"public-repo","full_name":"baxterthehacker/public-repo","owner":{"login":"baxterthehacker","id":6752317,"avatar_url":"https://avatars.githubusercontent.com/u/6752317?v=3","gravatar_id":"","url":"https://api.github.com/users/baxterthehacker","html_url":"https://github.com/baxterthehacker","followers_url":"https://api.github.com/users/baxterthehacker/followers","following_url":"https://api.github.com/users/baxterthehacker/following{/other_user}","gists_url":"https://api.github.com/users/baxterthehacker/gists{/gist_id}","starred_url":"https://api.github.com/users/baxterthehacker/starred{/owner}{/repo}","subscriptions_url":"https://api.github.com/users/baxterthehacker/subscriptions","organizations_url":"https://api.github.com/users/baxterthehacker/orgs","repos_url":"https://api.github.com/users/baxterthehacker/repos","events_url":"https://api.github.com/users/baxterthehacker/events{/privacy}","received_events_url":"https://api.github.com/users/baxterthehacker/received_events","type":"User","site_admin":false},"private":false,"html_url":"https://github.com/baxterthehacker/public-repo","description":"","fork":false,"url":"https://api.github.com/repos/baxterthehacker/public-repo","forks_url":"https://api.github.com/repos/baxterthehacker/public-repo/forks","keys_url":"https://api.github.com/repos/baxterthehacker/public-repo/keys{/key_id}","collaborators_url":"https://api.github.com/repos/baxterthehacker/public-repo/collaborators{/collaborator}","teams_url":"https://api.github.com/repos/baxterthehacker/public-repo/teams","hooks_url":"https://api.github.com/repos/baxterthehacker/public-repo/hooks","issue_events_url":"https://api.github.com/repos/baxterthehacker/public-repo/issues/events{/number}","events_url":"https://api.github.com/repos/baxterthehacker/public-repo/events","assignees_url":"https://api.github.com/repos/baxterthehacker/public-repo/assignees{/user}","branches_url":"https://api.github.com/repos/baxterthehacker/public-repo/branches{/branch}","tags_url":"https://api.github.com/repos/baxterthehacker/public-repo/tags","blobs_url":"https://api.github.com/repos/baxterthehacker/public-repo/git/blobs{/sha}","git_tags_url":"https://api.github.com/repos/baxterthehacker/public-repo/git/tags{/sha}","git_refs_url":"https://api.github.com/repos/baxterthehacker/public-repo/git/refs{/sha}","trees_url":"https://api.github.com/repos/baxterthehacker/public-repo/git/trees{/sha}","statuses_url":"https://api.github.com/repos/baxterthehacker/public-repo/statuses/{sha}","languages_url":"https://api.github.com/repos/baxterthehacker/public-repo/languages","stargazers_url":"https://api.github.com/repos/baxterthehacker/public-repo/stargazers","contributors_url":"https://api.github.com/repos/baxterthehacker/public-repo/contributors","subscribers_url":"https://api.github.com/repos/baxterthehacker/public-repo/subscribers","subscription_url":"https://api.github.com/repos/baxterthehacker/public-repo/subscription","commits_url":"https://api.github.com/repos/baxterthehacker/public-repo/commits{/sha}","git_commits_url":"https://api.github.com/repos/baxterthehacker/public-repo/git/commits{/sha}","comments_url":"https://api.github.com/repos/baxterthehacker/public-repo/comments{/number}","issue_comment_url":"https://api.github.com/repos/baxterthehacker/public-repo/issues/comments{/number}","contents_url":"https://api.github.com/repos/baxterthehacker/public-repo/contents/{+path}","compare_url":"https://api.github.com/repos/baxterthehacker/public-repo/compare/{base}...{head}","merges_url":"https://api.github.com/repos/baxterthehacker/public-repo/merges","archive_url":"https://api.github.com/repos/baxterthehacker/public-repo/{archive_format}{/ref}","downloads_url":"https://api.github.com/repos/baxterthehacker/public-repo/downloads","issues_url":"https://api.github.com/repos/baxterthehacker/public-repo/issues{/number}","pulls_url":"https://api.github.com/repos/baxterthehacker/public-repo/pulls{/number}","milestones_url":"https://api.github.com/repos/baxterthehacker/public-repo/milestones{/number}","notifications_url":"https://api.github.com/repos/baxterthehacker/public-repo/notifications{?since,all,participating}","labels_url":"https://api.github.com/repos/baxterthehacker/public-repo/labels{/name}","releases_url":"https://api.github.com/repos/baxterthehacker/public-repo/releases{/id}","created_at":"2015-05-05T23:40:12Z","updated_at":"2015-05-05T23:40:12Z","pushed_at":"2015-05-05T23:40:27Z","git_url":"git://github.com/baxterthehacker/public-repo.git","ssh_url":"git@github.com:baxterthehacker/public-repo.git","clone_url":"https://github.com/baxterthehacker/public-repo.git","svn_url":"https://github.com/baxterthehacker/public-repo","homepage":null,"size":0,"stargazers_count":0,"watchers_count":0,"language":null,"has_issues":true,"has_downloads":true,"has_wiki":true,"has_pages":true,"forks_count":0,"mirror_url":null,"open_issues_count":1,"forks":0,"open_issues":1,"watchers":0,"default_branch":"master"}},"base":{"label":"baxterthehacker:master","ref":"master","sha":"9049f1265b7d61be4a8904a9a27120d2064dab3b","user":{"login":"baxterthehacker","id":6752317,"avatar_url":"https://avatars.githubusercontent.com/u/6752317?v=3","gravatar_id":"","url":"https://api.github.com/users/baxterthehacker","html_url":"https://github.com/baxterthehacker","followers_url":"https://api.github.com/users/baxterthehacker/followers","following_url":"https://api.github.com/users/baxterthehacker/following{/other_user}","gists_url":"https://api.github.com/users/baxterthehacker/gists{/gist_id}","starred_url":"https://api.github.com/users/baxterthehacker/starred{/owner}{/repo}","subscriptions_url":"https://api.github.com/users/baxterthehacker/subscriptions","organizations_url":"https://api.github.com/users/baxterthehacker/orgs","repos_url":"https://api.github.com/users/baxterthehacker/repos","events_url":"https://api.github.com/users/baxterthehacker/events{/privacy}","received_events_url":"https://api.github.com/users/baxterthehacker/received_events","type":"User","site_admin":false},"repo":{"id":35129377,"name":"public-repo","full_name":"baxterthehacker/public-repo","owner":{"login":"baxterthehacker","id":6752317,"avatar_url":"https://avatars.githubusercontent.com/u/6752317?v=3","gravatar_id":"","url":"https://api.github.com/users/baxterthehacker","html_url":"https://github.com/baxterthehacker","followers_url":"https://api.github.com/users/baxterthehacker/followers","following_url":"https://api.github.com/users/baxterthehacker/following{/other_user}","gists_url":"https://api.github.com/users/baxterthehacker/gists{/gist_id}","starred_url":"https://api.github.com/users/baxterthehacker/starred{/owner}{/repo}","subscriptions_url":"https://api.github.com/users/baxterthehacker/subscriptions","organizations_url":"https://api.github.com/users/baxterthehacker/orgs","repos_url":"https://api.github.com/users/baxterthehacker/repos","events_url":"https://api.github.com/users/baxterthehacker/events{/privacy}","received_events_url":"https://api.github.com/users/baxterthehacker/received_events","type":"User","site_admin":false},"private":false,"html_url":"https://github.com/baxterthehacker/public-repo","description":"","fork":false,"url":"https://api.github.com/repos/baxterthehacker/public-repo","forks_url":"https://api.github.com/repos/baxterthehacker/public-repo/forks","keys_url":"https://api.github.com/repos/baxterthehacker/public-repo/keys{/key_id}","collaborators_url":"https://api.github.com/repos/baxterthehacker/public-repo/collaborators{/collaborator}","teams_url":"https://api.github.com/repos/baxterthehacker/public-repo/teams","hooks_url":"https://api.github.com/repos/baxterthehacker/public-repo/hooks","issue_events_url":"https://api.github.com/repos/baxterthehacker/public-repo/issues/events{/number}","events_url":"https://api.github.com/repos/baxterthehacker/public-repo/events","assignees_url":"https://api.github.com/repos/baxterthehacker/public-repo/assignees{/user}","branches_url":"https://api.github.com/repos/baxterthehacker/public-repo/branches{/branch}","tags_url":"https://api.github.com/repos/baxterthehacker/public-repo/tags","blobs_url":"https://api.github.com/repos/baxterthehacker/public-repo/git/blobs{/sha}","git_tags_url":"https://api.github.com/repos/baxterthehacker/public-repo/git/tags{/sha}","git_refs_url":"https://api.github.com/repos/baxterthehacker/public-repo/git/refs{/sha}","trees_url":"https://api.github.com/repos/baxterthehacker/public-repo/git/trees{/sha}","statuses_url":"https://api.github.com/repos/baxterthehacker/public-repo/statuses/{sha}","languages_url":"https://api.github.com/repos/baxterthehacker/public-repo/languages","stargazers_url":"https://api.github.com/repos/baxterthehacker/public-repo/stargazers","contributors_url":"https://api.github.com/repos/baxterthehacker/public-repo/contributors","subscribers_url":"https://api.github.com/repos/baxterthehacker/public-repo/subscribers","subscription_url":"https://api.github.com/repos/baxterthehacker/public-repo/subscription","commits_url":"https://api.github.com/repos/baxterthehacker/public-repo/commits{/sha}","git_commits_url":"https://api.github.com/repos/baxterthehacker/public-repo/git/commits{/sha}","comments_url":"https://api.github.com/repos/baxterthehacker/public-repo/comments{/number}","issue_comment_url":"https://api.github.com/repos/baxterthehacker/public-repo/issues/comments{/number}","contents_url":"https://api.github.com/repos/baxterthehacker/public-repo/contents/{+path}","compare_url":"https://api.github.com/repos/baxterthehacker/public-repo/compare/{base}...{head}","merges_url":"https://api.github.com/repos/baxterthehacker/public-repo/merges","archive_url":"https://api.github.com/repos/baxterthehacker/public-repo/{archive_format}{/ref}","downloads_url":"https://api.github.com/repos/baxterthehacker/public-repo/downloads","issues_url":"https://api.github.com/repos/baxterthehacker/public-repo/issues{/number}","pulls_url":"https://api.github.com/repos/baxterthehacker/public-repo/pulls{/number}","milestones_url":"https://api.github.com/repos/baxterthehacker/public-repo/milestones{/number}","notifications_url":"https://api.github.com/repos/baxterthehacker/public-repo/notifications{?since,all,participating}","labels_url":"https://api.github.com/repos/baxterthehacker/public-repo/labels{/name}","releases_url":"https://api.github.com/repos/baxterthehacker/public-repo/releases{/id}","created_at":"2015-05-05T23:40:12Z","updated_at":"2015-05-05T23:40:12Z","pushed_at":"2015-05-05T23:40:27Z","git_url":"git://github.com/baxterthehacker/public-repo.git","ssh_url":"git@github.com:baxterthehacker/public-repo.git","clone_url":"https://github.com/baxterthehacker/public-repo.git","svn_url":"https://github.com/baxterthehacker/public-repo","homepage":null,"size":0,"stargazers_count":0,"watchers_count":0,"language":null,"has_issues":true,"has_downloads":true,"has_wiki":true,"has_pages":true,"forks_count":0,"mirror_url":null,"open_issues_count":1,"forks":0,"open_issues":1,"watchers":0,"default_branch":"master"}},"_links":{"self":{"href":"https://api.github.com/repos/baxterthehacker/public-repo/pulls/1"},"html":{"href":"https://github.com/baxterthehacker/public-repo/pull/1"},"issue":{"href":"https://api.github.com/repos/baxterthehacker/public-repo/issues/1"},"comments":{"href":"https://api.github.com/repos/baxterthehacker/public-repo/issues/1/comments"},"review_comments":{"href":"https://api.github.com/repos/baxterthehacker/public-repo/pulls/1/comments"},"review_comment":{"href":"https://api.github.com/repos/baxterthehacker/public-repo/pulls/comments{/number}"},"commits":{"href":"https://api.github.com/repos/baxterthehacker/public-repo/pulls/1/commits"},"statuses":{"href":"https://api.github.com/repos/baxterthehacker/public-repo/statuses/0d1a26e67d8f5eaf1f6ba5c57fc3c7d91ac0fd1c"}}}
     * repository : {"id":35129377,"name":"public-repo","full_name":"baxterthehacker/public-repo","owner":{"login":"baxterthehacker","id":6752317,"avatar_url":"https://avatars.githubusercontent.com/u/6752317?v=3","gravatar_id":"","url":"https://api.github.com/users/baxterthehacker","html_url":"https://github.com/baxterthehacker","followers_url":"https://api.github.com/users/baxterthehacker/followers","following_url":"https://api.github.com/users/baxterthehacker/following{/other_user}","gists_url":"https://api.github.com/users/baxterthehacker/gists{/gist_id}","starred_url":"https://api.github.com/users/baxterthehacker/starred{/owner}{/repo}","subscriptions_url":"https://api.github.com/users/baxterthehacker/subscriptions","organizations_url":"https://api.github.com/users/baxterthehacker/orgs","repos_url":"https://api.github.com/users/baxterthehacker/repos","events_url":"https://api.github.com/users/baxterthehacker/events{/privacy}","received_events_url":"https://api.github.com/users/baxterthehacker/received_events","type":"User","site_admin":false},"private":false,"html_url":"https://github.com/baxterthehacker/public-repo","description":"","fork":false,"url":"https://api.github.com/repos/baxterthehacker/public-repo","forks_url":"https://api.github.com/repos/baxterthehacker/public-repo/forks","keys_url":"https://api.github.com/repos/baxterthehacker/public-repo/keys{/key_id}","collaborators_url":"https://api.github.com/repos/baxterthehacker/public-repo/collaborators{/collaborator}","teams_url":"https://api.github.com/repos/baxterthehacker/public-repo/teams","hooks_url":"https://api.github.com/repos/baxterthehacker/public-repo/hooks","issue_events_url":"https://api.github.com/repos/baxterthehacker/public-repo/issues/events{/number}","events_url":"https://api.github.com/repos/baxterthehacker/public-repo/events","assignees_url":"https://api.github.com/repos/baxterthehacker/public-repo/assignees{/user}","branches_url":"https://api.github.com/repos/baxterthehacker/public-repo/branches{/branch}","tags_url":"https://api.github.com/repos/baxterthehacker/public-repo/tags","blobs_url":"https://api.github.com/repos/baxterthehacker/public-repo/git/blobs{/sha}","git_tags_url":"https://api.github.com/repos/baxterthehacker/public-repo/git/tags{/sha}","git_refs_url":"https://api.github.com/repos/baxterthehacker/public-repo/git/refs{/sha}","trees_url":"https://api.github.com/repos/baxterthehacker/public-repo/git/trees{/sha}","statuses_url":"https://api.github.com/repos/baxterthehacker/public-repo/statuses/{sha}","languages_url":"https://api.github.com/repos/baxterthehacker/public-repo/languages","stargazers_url":"https://api.github.com/repos/baxterthehacker/public-repo/stargazers","contributors_url":"https://api.github.com/repos/baxterthehacker/public-repo/contributors","subscribers_url":"https://api.github.com/repos/baxterthehacker/public-repo/subscribers","subscription_url":"https://api.github.com/repos/baxterthehacker/public-repo/subscription","commits_url":"https://api.github.com/repos/baxterthehacker/public-repo/commits{/sha}","git_commits_url":"https://api.github.com/repos/baxterthehacker/public-repo/git/commits{/sha}","comments_url":"https://api.github.com/repos/baxterthehacker/public-repo/comments{/number}","issue_comment_url":"https://api.github.com/repos/baxterthehacker/public-repo/issues/comments{/number}","contents_url":"https://api.github.com/repos/baxterthehacker/public-repo/contents/{+path}","compare_url":"https://api.github.com/repos/baxterthehacker/public-repo/compare/{base}...{head}","merges_url":"https://api.github.com/repos/baxterthehacker/public-repo/merges","archive_url":"https://api.github.com/repos/baxterthehacker/public-repo/{archive_format}{/ref}","downloads_url":"https://api.github.com/repos/baxterthehacker/public-repo/downloads","issues_url":"https://api.github.com/repos/baxterthehacker/public-repo/issues{/number}","pulls_url":"https://api.github.com/repos/baxterthehacker/public-repo/pulls{/number}","milestones_url":"https://api.github.com/repos/baxterthehacker/public-repo/milestones{/number}","notifications_url":"https://api.github.com/repos/baxterthehacker/public-repo/notifications{?since,all,participating}","labels_url":"https://api.github.com/repos/baxterthehacker/public-repo/labels{/name}","releases_url":"https://api.github.com/repos/baxterthehacker/public-repo/releases{/id}","created_at":"2015-05-05T23:40:12Z","updated_at":"2015-05-05T23:40:12Z","pushed_at":"2015-05-05T23:40:27Z","git_url":"git://github.com/baxterthehacker/public-repo.git","ssh_url":"git@github.com:baxterthehacker/public-repo.git","clone_url":"https://github.com/baxterthehacker/public-repo.git","svn_url":"https://github.com/baxterthehacker/public-repo","homepage":null,"size":0,"stargazers_count":0,"watchers_count":0,"language":null,"has_issues":true,"has_downloads":true,"has_wiki":true,"has_pages":true,"forks_count":0,"mirror_url":null,"open_issues_count":1,"forks":0,"open_issues":1,"watchers":0,"default_branch":"master"}
     * sender : {"login":"baxterthehacker","id":6752317,"avatar_url":"https://avatars.githubusercontent.com/u/6752317?v=3","gravatar_id":"","url":"https://api.github.com/users/baxterthehacker","html_url":"https://github.com/baxterthehacker","followers_url":"https://api.github.com/users/baxterthehacker/followers","following_url":"https://api.github.com/users/baxterthehacker/following{/other_user}","gists_url":"https://api.github.com/users/baxterthehacker/gists{/gist_id}","starred_url":"https://api.github.com/users/baxterthehacker/starred{/owner}{/repo}","subscriptions_url":"https://api.github.com/users/baxterthehacker/subscriptions","organizations_url":"https://api.github.com/users/baxterthehacker/orgs","repos_url":"https://api.github.com/users/baxterthehacker/repos","events_url":"https://api.github.com/users/baxterthehacker/events{/privacy}","received_events_url":"https://api.github.com/users/baxterthehacker/received_events","type":"User","site_admin":false}
     */

    private String action;
    /**
     * url : https://api.github.com/repos/baxterthehacker/public-repo/pulls/comments/29724692
     * id : 29724692
     * diff_hunk : @@ -1 +1 @@
     -# public-repo
     * path : README.md
     * position : 1
     * original_position : 1
     * commit_id : 0d1a26e67d8f5eaf1f6ba5c57fc3c7d91ac0fd1c
     * original_commit_id : 0d1a26e67d8f5eaf1f6ba5c57fc3c7d91ac0fd1c
     * user : {"login":"baxterthehacker","id":6752317,"avatar_url":"https://avatars.githubusercontent.com/u/6752317?v=3","gravatar_id":"","url":"https://api.github.com/users/baxterthehacker","html_url":"https://github.com/baxterthehacker","followers_url":"https://api.github.com/users/baxterthehacker/followers","following_url":"https://api.github.com/users/baxterthehacker/following{/other_user}","gists_url":"https://api.github.com/users/baxterthehacker/gists{/gist_id}","starred_url":"https://api.github.com/users/baxterthehacker/starred{/owner}{/repo}","subscriptions_url":"https://api.github.com/users/baxterthehacker/subscriptions","organizations_url":"https://api.github.com/users/baxterthehacker/orgs","repos_url":"https://api.github.com/users/baxterthehacker/repos","events_url":"https://api.github.com/users/baxterthehacker/events{/privacy}","received_events_url":"https://api.github.com/users/baxterthehacker/received_events","type":"User","site_admin":false}
     * body : Maybe you should use more emojji on this line.
     * created_at : 2015-05-05T23:40:27Z
     * updated_at : 2015-05-05T23:40:27Z
     * html_url : https://github.com/baxterthehacker/public-repo/pull/1#discussion_r29724692
     * pull_request_url : https://api.github.com/repos/baxterthehacker/public-repo/pulls/1
     * _links : {"self":{"href":"https://api.github.com/repos/baxterthehacker/public-repo/pulls/comments/29724692"},"html":{"href":"https://github.com/baxterthehacker/public-repo/pull/1#discussion_r29724692"},"pull_request":{"href":"https://api.github.com/repos/baxterthehacker/public-repo/pulls/1"}}
     */

    private CommentBean comment;
    /**
     * url : https://api.github.com/repos/baxterthehacker/public-repo/pulls/1
     * id : 34778301
     * html_url : https://github.com/baxterthehacker/public-repo/pull/1
     * diff_url : https://github.com/baxterthehacker/public-repo/pull/1.diff
     * patch_url : https://github.com/baxterthehacker/public-repo/pull/1.patch
     * issue_url : https://api.github.com/repos/baxterthehacker/public-repo/issues/1
     * number : 1
     * state : open
     * locked : false
     * title : Update the README with new information
     * user : {"login":"baxterthehacker","id":6752317,"avatar_url":"https://avatars.githubusercontent.com/u/6752317?v=3","gravatar_id":"","url":"https://api.github.com/users/baxterthehacker","html_url":"https://github.com/baxterthehacker","followers_url":"https://api.github.com/users/baxterthehacker/followers","following_url":"https://api.github.com/users/baxterthehacker/following{/other_user}","gists_url":"https://api.github.com/users/baxterthehacker/gists{/gist_id}","starred_url":"https://api.github.com/users/baxterthehacker/starred{/owner}{/repo}","subscriptions_url":"https://api.github.com/users/baxterthehacker/subscriptions","organizations_url":"https://api.github.com/users/baxterthehacker/orgs","repos_url":"https://api.github.com/users/baxterthehacker/repos","events_url":"https://api.github.com/users/baxterthehacker/events{/privacy}","received_events_url":"https://api.github.com/users/baxterthehacker/received_events","type":"User","site_admin":false}
     * body : This is a pretty simple change that we need to pull into master.
     * created_at : 2015-05-05T23:40:27Z
     * updated_at : 2015-05-05T23:40:27Z
     * closed_at : null
     * merged_at : null
     * merge_commit_sha : 18721552ba489fb84e12958c1b5694b5475f7991
     * assignee : null
     * milestone : null
     * commits_url : https://api.github.com/repos/baxterthehacker/public-repo/pulls/1/commits
     * review_comments_url : https://api.github.com/repos/baxterthehacker/public-repo/pulls/1/comments
     * review_comment_url : https://api.github.com/repos/baxterthehacker/public-repo/pulls/comments{/number}
     * comments_url : https://api.github.com/repos/baxterthehacker/public-repo/issues/1/comments
     * statuses_url : https://api.github.com/repos/baxterthehacker/public-repo/statuses/0d1a26e67d8f5eaf1f6ba5c57fc3c7d91ac0fd1c
     * head : {"label":"baxterthehacker:changes","ref":"changes","sha":"0d1a26e67d8f5eaf1f6ba5c57fc3c7d91ac0fd1c","user":{"login":"baxterthehacker","id":6752317,"avatar_url":"https://avatars.githubusercontent.com/u/6752317?v=3","gravatar_id":"","url":"https://api.github.com/users/baxterthehacker","html_url":"https://github.com/baxterthehacker","followers_url":"https://api.github.com/users/baxterthehacker/followers","following_url":"https://api.github.com/users/baxterthehacker/following{/other_user}","gists_url":"https://api.github.com/users/baxterthehacker/gists{/gist_id}","starred_url":"https://api.github.com/users/baxterthehacker/starred{/owner}{/repo}","subscriptions_url":"https://api.github.com/users/baxterthehacker/subscriptions","organizations_url":"https://api.github.com/users/baxterthehacker/orgs","repos_url":"https://api.github.com/users/baxterthehacker/repos","events_url":"https://api.github.com/users/baxterthehacker/events{/privacy}","received_events_url":"https://api.github.com/users/baxterthehacker/received_events","type":"User","site_admin":false},"repo":{"id":35129377,"name":"public-repo","full_name":"baxterthehacker/public-repo","owner":{"login":"baxterthehacker","id":6752317,"avatar_url":"https://avatars.githubusercontent.com/u/6752317?v=3","gravatar_id":"","url":"https://api.github.com/users/baxterthehacker","html_url":"https://github.com/baxterthehacker","followers_url":"https://api.github.com/users/baxterthehacker/followers","following_url":"https://api.github.com/users/baxterthehacker/following{/other_user}","gists_url":"https://api.github.com/users/baxterthehacker/gists{/gist_id}","starred_url":"https://api.github.com/users/baxterthehacker/starred{/owner}{/repo}","subscriptions_url":"https://api.github.com/users/baxterthehacker/subscriptions","organizations_url":"https://api.github.com/users/baxterthehacker/orgs","repos_url":"https://api.github.com/users/baxterthehacker/repos","events_url":"https://api.github.com/users/baxterthehacker/events{/privacy}","received_events_url":"https://api.github.com/users/baxterthehacker/received_events","type":"User","site_admin":false},"private":false,"html_url":"https://github.com/baxterthehacker/public-repo","description":"","fork":false,"url":"https://api.github.com/repos/baxterthehacker/public-repo","forks_url":"https://api.github.com/repos/baxterthehacker/public-repo/forks","keys_url":"https://api.github.com/repos/baxterthehacker/public-repo/keys{/key_id}","collaborators_url":"https://api.github.com/repos/baxterthehacker/public-repo/collaborators{/collaborator}","teams_url":"https://api.github.com/repos/baxterthehacker/public-repo/teams","hooks_url":"https://api.github.com/repos/baxterthehacker/public-repo/hooks","issue_events_url":"https://api.github.com/repos/baxterthehacker/public-repo/issues/events{/number}","events_url":"https://api.github.com/repos/baxterthehacker/public-repo/events","assignees_url":"https://api.github.com/repos/baxterthehacker/public-repo/assignees{/user}","branches_url":"https://api.github.com/repos/baxterthehacker/public-repo/branches{/branch}","tags_url":"https://api.github.com/repos/baxterthehacker/public-repo/tags","blobs_url":"https://api.github.com/repos/baxterthehacker/public-repo/git/blobs{/sha}","git_tags_url":"https://api.github.com/repos/baxterthehacker/public-repo/git/tags{/sha}","git_refs_url":"https://api.github.com/repos/baxterthehacker/public-repo/git/refs{/sha}","trees_url":"https://api.github.com/repos/baxterthehacker/public-repo/git/trees{/sha}","statuses_url":"https://api.github.com/repos/baxterthehacker/public-repo/statuses/{sha}","languages_url":"https://api.github.com/repos/baxterthehacker/public-repo/languages","stargazers_url":"https://api.github.com/repos/baxterthehacker/public-repo/stargazers","contributors_url":"https://api.github.com/repos/baxterthehacker/public-repo/contributors","subscribers_url":"https://api.github.com/repos/baxterthehacker/public-repo/subscribers","subscription_url":"https://api.github.com/repos/baxterthehacker/public-repo/subscription","commits_url":"https://api.github.com/repos/baxterthehacker/public-repo/commits{/sha}","git_commits_url":"https://api.github.com/repos/baxterthehacker/public-repo/git/commits{/sha}","comments_url":"https://api.github.com/repos/baxterthehacker/public-repo/comments{/number}","issue_comment_url":"https://api.github.com/repos/baxterthehacker/public-repo/issues/comments{/number}","contents_url":"https://api.github.com/repos/baxterthehacker/public-repo/contents/{+path}","compare_url":"https://api.github.com/repos/baxterthehacker/public-repo/compare/{base}...{head}","merges_url":"https://api.github.com/repos/baxterthehacker/public-repo/merges","archive_url":"https://api.github.com/repos/baxterthehacker/public-repo/{archive_format}{/ref}","downloads_url":"https://api.github.com/repos/baxterthehacker/public-repo/downloads","issues_url":"https://api.github.com/repos/baxterthehacker/public-repo/issues{/number}","pulls_url":"https://api.github.com/repos/baxterthehacker/public-repo/pulls{/number}","milestones_url":"https://api.github.com/repos/baxterthehacker/public-repo/milestones{/number}","notifications_url":"https://api.github.com/repos/baxterthehacker/public-repo/notifications{?since,all,participating}","labels_url":"https://api.github.com/repos/baxterthehacker/public-repo/labels{/name}","releases_url":"https://api.github.com/repos/baxterthehacker/public-repo/releases{/id}","created_at":"2015-05-05T23:40:12Z","updated_at":"2015-05-05T23:40:12Z","pushed_at":"2015-05-05T23:40:27Z","git_url":"git://github.com/baxterthehacker/public-repo.git","ssh_url":"git@github.com:baxterthehacker/public-repo.git","clone_url":"https://github.com/baxterthehacker/public-repo.git","svn_url":"https://github.com/baxterthehacker/public-repo","homepage":null,"size":0,"stargazers_count":0,"watchers_count":0,"language":null,"has_issues":true,"has_downloads":true,"has_wiki":true,"has_pages":true,"forks_count":0,"mirror_url":null,"open_issues_count":1,"forks":0,"open_issues":1,"watchers":0,"default_branch":"master"}}
     * base : {"label":"baxterthehacker:master","ref":"master","sha":"9049f1265b7d61be4a8904a9a27120d2064dab3b","user":{"login":"baxterthehacker","id":6752317,"avatar_url":"https://avatars.githubusercontent.com/u/6752317?v=3","gravatar_id":"","url":"https://api.github.com/users/baxterthehacker","html_url":"https://github.com/baxterthehacker","followers_url":"https://api.github.com/users/baxterthehacker/followers","following_url":"https://api.github.com/users/baxterthehacker/following{/other_user}","gists_url":"https://api.github.com/users/baxterthehacker/gists{/gist_id}","starred_url":"https://api.github.com/users/baxterthehacker/starred{/owner}{/repo}","subscriptions_url":"https://api.github.com/users/baxterthehacker/subscriptions","organizations_url":"https://api.github.com/users/baxterthehacker/orgs","repos_url":"https://api.github.com/users/baxterthehacker/repos","events_url":"https://api.github.com/users/baxterthehacker/events{/privacy}","received_events_url":"https://api.github.com/users/baxterthehacker/received_events","type":"User","site_admin":false},"repo":{"id":35129377,"name":"public-repo","full_name":"baxterthehacker/public-repo","owner":{"login":"baxterthehacker","id":6752317,"avatar_url":"https://avatars.githubusercontent.com/u/6752317?v=3","gravatar_id":"","url":"https://api.github.com/users/baxterthehacker","html_url":"https://github.com/baxterthehacker","followers_url":"https://api.github.com/users/baxterthehacker/followers","following_url":"https://api.github.com/users/baxterthehacker/following{/other_user}","gists_url":"https://api.github.com/users/baxterthehacker/gists{/gist_id}","starred_url":"https://api.github.com/users/baxterthehacker/starred{/owner}{/repo}","subscriptions_url":"https://api.github.com/users/baxterthehacker/subscriptions","organizations_url":"https://api.github.com/users/baxterthehacker/orgs","repos_url":"https://api.github.com/users/baxterthehacker/repos","events_url":"https://api.github.com/users/baxterthehacker/events{/privacy}","received_events_url":"https://api.github.com/users/baxterthehacker/received_events","type":"User","site_admin":false},"private":false,"html_url":"https://github.com/baxterthehacker/public-repo","description":"","fork":false,"url":"https://api.github.com/repos/baxterthehacker/public-repo","forks_url":"https://api.github.com/repos/baxterthehacker/public-repo/forks","keys_url":"https://api.github.com/repos/baxterthehacker/public-repo/keys{/key_id}","collaborators_url":"https://api.github.com/repos/baxterthehacker/public-repo/collaborators{/collaborator}","teams_url":"https://api.github.com/repos/baxterthehacker/public-repo/teams","hooks_url":"https://api.github.com/repos/baxterthehacker/public-repo/hooks","issue_events_url":"https://api.github.com/repos/baxterthehacker/public-repo/issues/events{/number}","events_url":"https://api.github.com/repos/baxterthehacker/public-repo/events","assignees_url":"https://api.github.com/repos/baxterthehacker/public-repo/assignees{/user}","branches_url":"https://api.github.com/repos/baxterthehacker/public-repo/branches{/branch}","tags_url":"https://api.github.com/repos/baxterthehacker/public-repo/tags","blobs_url":"https://api.github.com/repos/baxterthehacker/public-repo/git/blobs{/sha}","git_tags_url":"https://api.github.com/repos/baxterthehacker/public-repo/git/tags{/sha}","git_refs_url":"https://api.github.com/repos/baxterthehacker/public-repo/git/refs{/sha}","trees_url":"https://api.github.com/repos/baxterthehacker/public-repo/git/trees{/sha}","statuses_url":"https://api.github.com/repos/baxterthehacker/public-repo/statuses/{sha}","languages_url":"https://api.github.com/repos/baxterthehacker/public-repo/languages","stargazers_url":"https://api.github.com/repos/baxterthehacker/public-repo/stargazers","contributors_url":"https://api.github.com/repos/baxterthehacker/public-repo/contributors","subscribers_url":"https://api.github.com/repos/baxterthehacker/public-repo/subscribers","subscription_url":"https://api.github.com/repos/baxterthehacker/public-repo/subscription","commits_url":"https://api.github.com/repos/baxterthehacker/public-repo/commits{/sha}","git_commits_url":"https://api.github.com/repos/baxterthehacker/public-repo/git/commits{/sha}","comments_url":"https://api.github.com/repos/baxterthehacker/public-repo/comments{/number}","issue_comment_url":"https://api.github.com/repos/baxterthehacker/public-repo/issues/comments{/number}","contents_url":"https://api.github.com/repos/baxterthehacker/public-repo/contents/{+path}","compare_url":"https://api.github.com/repos/baxterthehacker/public-repo/compare/{base}...{head}","merges_url":"https://api.github.com/repos/baxterthehacker/public-repo/merges","archive_url":"https://api.github.com/repos/baxterthehacker/public-repo/{archive_format}{/ref}","downloads_url":"https://api.github.com/repos/baxterthehacker/public-repo/downloads","issues_url":"https://api.github.com/repos/baxterthehacker/public-repo/issues{/number}","pulls_url":"https://api.github.com/repos/baxterthehacker/public-repo/pulls{/number}","milestones_url":"https://api.github.com/repos/baxterthehacker/public-repo/milestones{/number}","notifications_url":"https://api.github.com/repos/baxterthehacker/public-repo/notifications{?since,all,participating}","labels_url":"https://api.github.com/repos/baxterthehacker/public-repo/labels{/name}","releases_url":"https://api.github.com/repos/baxterthehacker/public-repo/releases{/id}","created_at":"2015-05-05T23:40:12Z","updated_at":"2015-05-05T23:40:12Z","pushed_at":"2015-05-05T23:40:27Z","git_url":"git://github.com/baxterthehacker/public-repo.git","ssh_url":"git@github.com:baxterthehacker/public-repo.git","clone_url":"https://github.com/baxterthehacker/public-repo.git","svn_url":"https://github.com/baxterthehacker/public-repo","homepage":null,"size":0,"stargazers_count":0,"watchers_count":0,"language":null,"has_issues":true,"has_downloads":true,"has_wiki":true,"has_pages":true,"forks_count":0,"mirror_url":null,"open_issues_count":1,"forks":0,"open_issues":1,"watchers":0,"default_branch":"master"}}
     * _links : {"self":{"href":"https://api.github.com/repos/baxterthehacker/public-repo/pulls/1"},"html":{"href":"https://github.com/baxterthehacker/public-repo/pull/1"},"issue":{"href":"https://api.github.com/repos/baxterthehacker/public-repo/issues/1"},"comments":{"href":"https://api.github.com/repos/baxterthehacker/public-repo/issues/1/comments"},"review_comments":{"href":"https://api.github.com/repos/baxterthehacker/public-repo/pulls/1/comments"},"review_comment":{"href":"https://api.github.com/repos/baxterthehacker/public-repo/pulls/comments{/number}"},"commits":{"href":"https://api.github.com/repos/baxterthehacker/public-repo/pulls/1/commits"},"statuses":{"href":"https://api.github.com/repos/baxterthehacker/public-repo/statuses/0d1a26e67d8f5eaf1f6ba5c57fc3c7d91ac0fd1c"}}
     */

    private PullRequestBean pull_request;
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
     * pushed_at : 2015-05-05T23:40:27Z
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

    private RepositoryBean repository;
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

    private SenderBean sender;

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public CommentBean getComment() {
        return comment;
    }

    public void setComment(CommentBean comment) {
        this.comment = comment;
    }

    public PullRequestBean getPull_request() {
        return pull_request;
    }

    public void setPull_request(PullRequestBean pull_request) {
        this.pull_request = pull_request;
    }

    public RepositoryBean getRepository() {
        return repository;
    }

    public void setRepository(RepositoryBean repository) {
        this.repository = repository;
    }

    public SenderBean getSender() {
        return sender;
    }

    public void setSender(SenderBean sender) {
        this.sender = sender;
    }

    public static class CommentBean {
        private String url;
        private int id;
        private String diff_hunk;
        private String path;
        private int position;
        private int original_position;
        private String commit_id;
        private String original_commit_id;
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

        private UserBean user;
        private String body;
        private String created_at;
        private String updated_at;
        private String html_url;
        private String pull_request_url;
        /**
         * self : {"href":"https://api.github.com/repos/baxterthehacker/public-repo/pulls/comments/29724692"}
         * html : {"href":"https://github.com/baxterthehacker/public-repo/pull/1#discussion_r29724692"}
         * pull_request : {"href":"https://api.github.com/repos/baxterthehacker/public-repo/pulls/1"}
         */

        private LinksBean _links;

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

        public String getDiff_hunk() {
            return diff_hunk;
        }

        public void setDiff_hunk(String diff_hunk) {
            this.diff_hunk = diff_hunk;
        }

        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
        }

        public int getPosition() {
            return position;
        }

        public void setPosition(int position) {
            this.position = position;
        }

        public int getOriginal_position() {
            return original_position;
        }

        public void setOriginal_position(int original_position) {
            this.original_position = original_position;
        }

        public String getCommit_id() {
            return commit_id;
        }

        public void setCommit_id(String commit_id) {
            this.commit_id = commit_id;
        }

        public String getOriginal_commit_id() {
            return original_commit_id;
        }

        public void setOriginal_commit_id(String original_commit_id) {
            this.original_commit_id = original_commit_id;
        }

        public UserBean getUser() {
            return user;
        }

        public void setUser(UserBean user) {
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

        public String getHtml_url() {
            return html_url;
        }

        public void setHtml_url(String html_url) {
            this.html_url = html_url;
        }

        public String getPull_request_url() {
            return pull_request_url;
        }

        public void setPull_request_url(String pull_request_url) {
            this.pull_request_url = pull_request_url;
        }

        public LinksBean get_links() {
            return _links;
        }

        public void set_links(LinksBean _links) {
            this._links = _links;
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

        public static class LinksBean {
            /**
             * href : https://api.github.com/repos/baxterthehacker/public-repo/pulls/comments/29724692
             */

            private SelfBean self;
            /**
             * href : https://github.com/baxterthehacker/public-repo/pull/1#discussion_r29724692
             */

            private HtmlBean html;
            /**
             * href : https://api.github.com/repos/baxterthehacker/public-repo/pulls/1
             */

            private PullRequestBean pull_request;

            public SelfBean getSelf() {
                return self;
            }

            public void setSelf(SelfBean self) {
                this.self = self;
            }

            public HtmlBean getHtml() {
                return html;
            }

            public void setHtml(HtmlBean html) {
                this.html = html;
            }

            public PullRequestBean getPull_request() {
                return pull_request;
            }

            public void setPull_request(PullRequestBean pull_request) {
                this.pull_request = pull_request;
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

            public static class PullRequestBean {
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

        private UserBean user;
        private String body;
        private String created_at;
        private String updated_at;
        private Object closed_at;
        private Object merged_at;
        private String merge_commit_sha;
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
         * repo : {"id":35129377,"name":"public-repo","full_name":"baxterthehacker/public-repo","owner":{"login":"baxterthehacker","id":6752317,"avatar_url":"https://avatars.githubusercontent.com/u/6752317?v=3","gravatar_id":"","url":"https://api.github.com/users/baxterthehacker","html_url":"https://github.com/baxterthehacker","followers_url":"https://api.github.com/users/baxterthehacker/followers","following_url":"https://api.github.com/users/baxterthehacker/following{/other_user}","gists_url":"https://api.github.com/users/baxterthehacker/gists{/gist_id}","starred_url":"https://api.github.com/users/baxterthehacker/starred{/owner}{/repo}","subscriptions_url":"https://api.github.com/users/baxterthehacker/subscriptions","organizations_url":"https://api.github.com/users/baxterthehacker/orgs","repos_url":"https://api.github.com/users/baxterthehacker/repos","events_url":"https://api.github.com/users/baxterthehacker/events{/privacy}","received_events_url":"https://api.github.com/users/baxterthehacker/received_events","type":"User","site_admin":false},"private":false,"html_url":"https://github.com/baxterthehacker/public-repo","description":"","fork":false,"url":"https://api.github.com/repos/baxterthehacker/public-repo","forks_url":"https://api.github.com/repos/baxterthehacker/public-repo/forks","keys_url":"https://api.github.com/repos/baxterthehacker/public-repo/keys{/key_id}","collaborators_url":"https://api.github.com/repos/baxterthehacker/public-repo/collaborators{/collaborator}","teams_url":"https://api.github.com/repos/baxterthehacker/public-repo/teams","hooks_url":"https://api.github.com/repos/baxterthehacker/public-repo/hooks","issue_events_url":"https://api.github.com/repos/baxterthehacker/public-repo/issues/events{/number}","events_url":"https://api.github.com/repos/baxterthehacker/public-repo/events","assignees_url":"https://api.github.com/repos/baxterthehacker/public-repo/assignees{/user}","branches_url":"https://api.github.com/repos/baxterthehacker/public-repo/branches{/branch}","tags_url":"https://api.github.com/repos/baxterthehacker/public-repo/tags","blobs_url":"https://api.github.com/repos/baxterthehacker/public-repo/git/blobs{/sha}","git_tags_url":"https://api.github.com/repos/baxterthehacker/public-repo/git/tags{/sha}","git_refs_url":"https://api.github.com/repos/baxterthehacker/public-repo/git/refs{/sha}","trees_url":"https://api.github.com/repos/baxterthehacker/public-repo/git/trees{/sha}","statuses_url":"https://api.github.com/repos/baxterthehacker/public-repo/statuses/{sha}","languages_url":"https://api.github.com/repos/baxterthehacker/public-repo/languages","stargazers_url":"https://api.github.com/repos/baxterthehacker/public-repo/stargazers","contributors_url":"https://api.github.com/repos/baxterthehacker/public-repo/contributors","subscribers_url":"https://api.github.com/repos/baxterthehacker/public-repo/subscribers","subscription_url":"https://api.github.com/repos/baxterthehacker/public-repo/subscription","commits_url":"https://api.github.com/repos/baxterthehacker/public-repo/commits{/sha}","git_commits_url":"https://api.github.com/repos/baxterthehacker/public-repo/git/commits{/sha}","comments_url":"https://api.github.com/repos/baxterthehacker/public-repo/comments{/number}","issue_comment_url":"https://api.github.com/repos/baxterthehacker/public-repo/issues/comments{/number}","contents_url":"https://api.github.com/repos/baxterthehacker/public-repo/contents/{+path}","compare_url":"https://api.github.com/repos/baxterthehacker/public-repo/compare/{base}...{head}","merges_url":"https://api.github.com/repos/baxterthehacker/public-repo/merges","archive_url":"https://api.github.com/repos/baxterthehacker/public-repo/{archive_format}{/ref}","downloads_url":"https://api.github.com/repos/baxterthehacker/public-repo/downloads","issues_url":"https://api.github.com/repos/baxterthehacker/public-repo/issues{/number}","pulls_url":"https://api.github.com/repos/baxterthehacker/public-repo/pulls{/number}","milestones_url":"https://api.github.com/repos/baxterthehacker/public-repo/milestones{/number}","notifications_url":"https://api.github.com/repos/baxterthehacker/public-repo/notifications{?since,all,participating}","labels_url":"https://api.github.com/repos/baxterthehacker/public-repo/labels{/name}","releases_url":"https://api.github.com/repos/baxterthehacker/public-repo/releases{/id}","created_at":"2015-05-05T23:40:12Z","updated_at":"2015-05-05T23:40:12Z","pushed_at":"2015-05-05T23:40:27Z","git_url":"git://github.com/baxterthehacker/public-repo.git","ssh_url":"git@github.com:baxterthehacker/public-repo.git","clone_url":"https://github.com/baxterthehacker/public-repo.git","svn_url":"https://github.com/baxterthehacker/public-repo","homepage":null,"size":0,"stargazers_count":0,"watchers_count":0,"language":null,"has_issues":true,"has_downloads":true,"has_wiki":true,"has_pages":true,"forks_count":0,"mirror_url":null,"open_issues_count":1,"forks":0,"open_issues":1,"watchers":0,"default_branch":"master"}
         */

        private HeadBean head;
        /**
         * label : baxterthehacker:master
         * ref : master
         * sha : 9049f1265b7d61be4a8904a9a27120d2064dab3b
         * user : {"login":"baxterthehacker","id":6752317,"avatar_url":"https://avatars.githubusercontent.com/u/6752317?v=3","gravatar_id":"","url":"https://api.github.com/users/baxterthehacker","html_url":"https://github.com/baxterthehacker","followers_url":"https://api.github.com/users/baxterthehacker/followers","following_url":"https://api.github.com/users/baxterthehacker/following{/other_user}","gists_url":"https://api.github.com/users/baxterthehacker/gists{/gist_id}","starred_url":"https://api.github.com/users/baxterthehacker/starred{/owner}{/repo}","subscriptions_url":"https://api.github.com/users/baxterthehacker/subscriptions","organizations_url":"https://api.github.com/users/baxterthehacker/orgs","repos_url":"https://api.github.com/users/baxterthehacker/repos","events_url":"https://api.github.com/users/baxterthehacker/events{/privacy}","received_events_url":"https://api.github.com/users/baxterthehacker/received_events","type":"User","site_admin":false}
         * repo : {"id":35129377,"name":"public-repo","full_name":"baxterthehacker/public-repo","owner":{"login":"baxterthehacker","id":6752317,"avatar_url":"https://avatars.githubusercontent.com/u/6752317?v=3","gravatar_id":"","url":"https://api.github.com/users/baxterthehacker","html_url":"https://github.com/baxterthehacker","followers_url":"https://api.github.com/users/baxterthehacker/followers","following_url":"https://api.github.com/users/baxterthehacker/following{/other_user}","gists_url":"https://api.github.com/users/baxterthehacker/gists{/gist_id}","starred_url":"https://api.github.com/users/baxterthehacker/starred{/owner}{/repo}","subscriptions_url":"https://api.github.com/users/baxterthehacker/subscriptions","organizations_url":"https://api.github.com/users/baxterthehacker/orgs","repos_url":"https://api.github.com/users/baxterthehacker/repos","events_url":"https://api.github.com/users/baxterthehacker/events{/privacy}","received_events_url":"https://api.github.com/users/baxterthehacker/received_events","type":"User","site_admin":false},"private":false,"html_url":"https://github.com/baxterthehacker/public-repo","description":"","fork":false,"url":"https://api.github.com/repos/baxterthehacker/public-repo","forks_url":"https://api.github.com/repos/baxterthehacker/public-repo/forks","keys_url":"https://api.github.com/repos/baxterthehacker/public-repo/keys{/key_id}","collaborators_url":"https://api.github.com/repos/baxterthehacker/public-repo/collaborators{/collaborator}","teams_url":"https://api.github.com/repos/baxterthehacker/public-repo/teams","hooks_url":"https://api.github.com/repos/baxterthehacker/public-repo/hooks","issue_events_url":"https://api.github.com/repos/baxterthehacker/public-repo/issues/events{/number}","events_url":"https://api.github.com/repos/baxterthehacker/public-repo/events","assignees_url":"https://api.github.com/repos/baxterthehacker/public-repo/assignees{/user}","branches_url":"https://api.github.com/repos/baxterthehacker/public-repo/branches{/branch}","tags_url":"https://api.github.com/repos/baxterthehacker/public-repo/tags","blobs_url":"https://api.github.com/repos/baxterthehacker/public-repo/git/blobs{/sha}","git_tags_url":"https://api.github.com/repos/baxterthehacker/public-repo/git/tags{/sha}","git_refs_url":"https://api.github.com/repos/baxterthehacker/public-repo/git/refs{/sha}","trees_url":"https://api.github.com/repos/baxterthehacker/public-repo/git/trees{/sha}","statuses_url":"https://api.github.com/repos/baxterthehacker/public-repo/statuses/{sha}","languages_url":"https://api.github.com/repos/baxterthehacker/public-repo/languages","stargazers_url":"https://api.github.com/repos/baxterthehacker/public-repo/stargazers","contributors_url":"https://api.github.com/repos/baxterthehacker/public-repo/contributors","subscribers_url":"https://api.github.com/repos/baxterthehacker/public-repo/subscribers","subscription_url":"https://api.github.com/repos/baxterthehacker/public-repo/subscription","commits_url":"https://api.github.com/repos/baxterthehacker/public-repo/commits{/sha}","git_commits_url":"https://api.github.com/repos/baxterthehacker/public-repo/git/commits{/sha}","comments_url":"https://api.github.com/repos/baxterthehacker/public-repo/comments{/number}","issue_comment_url":"https://api.github.com/repos/baxterthehacker/public-repo/issues/comments{/number}","contents_url":"https://api.github.com/repos/baxterthehacker/public-repo/contents/{+path}","compare_url":"https://api.github.com/repos/baxterthehacker/public-repo/compare/{base}...{head}","merges_url":"https://api.github.com/repos/baxterthehacker/public-repo/merges","archive_url":"https://api.github.com/repos/baxterthehacker/public-repo/{archive_format}{/ref}","downloads_url":"https://api.github.com/repos/baxterthehacker/public-repo/downloads","issues_url":"https://api.github.com/repos/baxterthehacker/public-repo/issues{/number}","pulls_url":"https://api.github.com/repos/baxterthehacker/public-repo/pulls{/number}","milestones_url":"https://api.github.com/repos/baxterthehacker/public-repo/milestones{/number}","notifications_url":"https://api.github.com/repos/baxterthehacker/public-repo/notifications{?since,all,participating}","labels_url":"https://api.github.com/repos/baxterthehacker/public-repo/labels{/name}","releases_url":"https://api.github.com/repos/baxterthehacker/public-repo/releases{/id}","created_at":"2015-05-05T23:40:12Z","updated_at":"2015-05-05T23:40:12Z","pushed_at":"2015-05-05T23:40:27Z","git_url":"git://github.com/baxterthehacker/public-repo.git","ssh_url":"git@github.com:baxterthehacker/public-repo.git","clone_url":"https://github.com/baxterthehacker/public-repo.git","svn_url":"https://github.com/baxterthehacker/public-repo","homepage":null,"size":0,"stargazers_count":0,"watchers_count":0,"language":null,"has_issues":true,"has_downloads":true,"has_wiki":true,"has_pages":true,"forks_count":0,"mirror_url":null,"open_issues_count":1,"forks":0,"open_issues":1,"watchers":0,"default_branch":"master"}
         */

        private BaseBean base;
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

        private LinksBean _links;

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

        public UserBean getUser() {
            return user;
        }

        public void setUser(UserBean user) {
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

        public String getMerge_commit_sha() {
            return merge_commit_sha;
        }

        public void setMerge_commit_sha(String merge_commit_sha) {
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

            private UserBean user;
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
             * pushed_at : 2015-05-05T23:40:27Z
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

            private RepoBean repo;

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

            public RepoBean getRepo() {
                return repo;
            }

            public void setRepo(RepoBean repo) {
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

                private OwnerBean owner;
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

                public OwnerBean getOwner() {
                    return owner;
                }

                public void setOwner(OwnerBean owner) {
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

            private UserBean user;
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
             * pushed_at : 2015-05-05T23:40:27Z
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

            private RepoBean repo;

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

            public RepoBean getRepo() {
                return repo;
            }

            public void setRepo(RepoBean repo) {
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

                private OwnerBean owner;
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

                public OwnerBean getOwner() {
                    return owner;
                }

                public void setOwner(OwnerBean owner) {
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

            private SelfBean self;
            /**
             * href : https://github.com/baxterthehacker/public-repo/pull/1
             */

            private HtmlBean html;
            /**
             * href : https://api.github.com/repos/baxterthehacker/public-repo/issues/1
             */

            private IssueBean issue;
            /**
             * href : https://api.github.com/repos/baxterthehacker/public-repo/issues/1/comments
             */

            private CommentsBean comments;
            /**
             * href : https://api.github.com/repos/baxterthehacker/public-repo/pulls/1/comments
             */

            private ReviewCommentsBean review_comments;
            /**
             * href : https://api.github.com/repos/baxterthehacker/public-repo/pulls/comments{/number}
             */

            private ReviewCommentBean review_comment;
            /**
             * href : https://api.github.com/repos/baxterthehacker/public-repo/pulls/1/commits
             */

            private CommitsBean commits;
            /**
             * href : https://api.github.com/repos/baxterthehacker/public-repo/statuses/0d1a26e67d8f5eaf1f6ba5c57fc3c7d91ac0fd1c
             */

            private StatusesBean statuses;

            public SelfBean getSelf() {
                return self;
            }

            public void setSelf(SelfBean self) {
                this.self = self;
            }

            public HtmlBean getHtml() {
                return html;
            }

            public void setHtml(HtmlBean html) {
                this.html = html;
            }

            public IssueBean getIssue() {
                return issue;
            }

            public void setIssue(IssueBean issue) {
                this.issue = issue;
            }

            public CommentsBean getComments() {
                return comments;
            }

            public void setComments(CommentsBean comments) {
                this.comments = comments;
            }

            public ReviewCommentsBean getReview_comments() {
                return review_comments;
            }

            public void setReview_comments(ReviewCommentsBean review_comments) {
                this.review_comments = review_comments;
            }

            public ReviewCommentBean getReview_comment() {
                return review_comment;
            }

            public void setReview_comment(ReviewCommentBean review_comment) {
                this.review_comment = review_comment;
            }

            public CommitsBean getCommits() {
                return commits;
            }

            public void setCommits(CommitsBean commits) {
                this.commits = commits;
            }

            public StatusesBean getStatuses() {
                return statuses;
            }

            public void setStatuses(StatusesBean statuses) {
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

    public static class RepositoryBean {
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

        private OwnerBean owner;
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

        public OwnerBean getOwner() {
            return owner;
        }

        public void setOwner(OwnerBean owner) {
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
