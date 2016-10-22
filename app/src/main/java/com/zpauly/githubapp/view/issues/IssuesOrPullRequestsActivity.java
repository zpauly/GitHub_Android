package com.zpauly.githubapp.view.issues;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.MenuItem;
import android.view.View;

import com.zpauly.githubapp.R;
import com.zpauly.githubapp.listener.OnNavItemClickListener;
import com.zpauly.githubapp.view.RightDrawerActivity;

/**
 * Created by zpauly on 16/9/5.
 */
public class IssuesOrPullRequestsActivity extends RightDrawerActivity {
    private final String TAG = getClass().getName();

    public static final String ISSUE_NAME = "ISSUE_NAME";
    public static final String DATA_TYPE = "ISSUE_TYPE";
    public static final int REPO_ISSUES = 0;
    public static final int ORG_ISSUES = 1;
    public static final int USER_ISSUES = 2;
    public static final int REPO_PULL_REQUESTS = 3;
    public static final int USER_PULL_REQUESTS = 4;

    public static final String REPO_NAME = "REPO_NAME";
    public static final String ORG_NAME = "ORG_NAME";
    public static final String USERNAME = "USERNAME";

    private int dataType;
    private String toolbarTitle;
    private String repoName;
    private String orgName;
    private String username;

    @Override
    public void initViews() {
        getAttrs();

        setFragment();
    }

    @Override
    protected void setToolbar() {
        super.setToolbar();
        setToolbarTitle(toolbarTitle);
        setOnToolbarNavClickedListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    private void getAttrs() {
        dataType = getIntent().getIntExtra(DATA_TYPE, USER_ISSUES);
        repoName = getIntent().getStringExtra(REPO_NAME);
        orgName = getIntent().getStringExtra(ORG_NAME);
        username = getIntent().getStringExtra(USERNAME);
        switch (dataType) {
            case USER_ISSUES:
                toolbarTitle = getString(R.string.issues);
                break;
            case REPO_ISSUES:
                toolbarTitle = repoName;
                break;
            case ORG_ISSUES:
                toolbarTitle = orgName;
                break;
            case REPO_PULL_REQUESTS:
                toolbarTitle = repoName;
                break;
            case USER_PULL_REQUESTS:
                toolbarTitle = getString(R.string.pull_requests);
                break;
            default:
                break;
        }
    }

    private void setFragment() {
        Bundle bundle = new Bundle();
        bundle.putInt(DATA_TYPE, dataType);
        bundle.putString(REPO_NAME, repoName);
        bundle.putString(ORG_NAME, orgName);
        bundle.putString(USERNAME, username);
        IssuesOrPullRequestsFragment issuesOrPullRequestsFragment = new IssuesOrPullRequestsFragment();
        issuesOrPullRequestsFragment.setArguments(bundle);
        setContent(issuesOrPullRequestsFragment);
    }

    @Override
    protected void setContent(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.nav_content, fragment);
        fragmentTransaction.commit();
    }

    public static void launchActivity(Context context) {
        Intent intent = new Intent();
        intent.putExtra(DATA_TYPE, USER_ISSUES);
        intent.setClass(context, IssuesOrPullRequestsActivity.class);
        context.startActivity(intent);
    }

    public static void launchRepoIssuesActivity(Context context, String username, String repoName) {
        Intent intent = new Intent();
        intent.putExtra(DATA_TYPE, REPO_ISSUES);
        intent.putExtra(USERNAME, username);
        intent.putExtra(REPO_NAME, repoName);
        intent.setClass(context, IssuesOrPullRequestsActivity.class);
        context.startActivity(intent);
//        ((Activity) context).finish();
    }

    public static void launchOrgIssuesActivity(Context context, String orgName) {
        Intent intent = new Intent();
        intent.putExtra(DATA_TYPE, ORG_ISSUES);
        intent.putExtra(ORG_NAME, orgName);
        intent.setClass(context, IssuesOrPullRequestsActivity.class);
        context.startActivity(intent);
    }

    public static void launchPullRequestsActivity(Context context, String owner,
                                                  String repo) {
        Intent intent = new Intent();
        intent.putExtra(REPO_NAME, repo);
        intent.putExtra(USERNAME, owner);
        intent.putExtra(DATA_TYPE, REPO_PULL_REQUESTS);
        intent.setClass(context, IssuesOrPullRequestsActivity.class);
        context.startActivity(intent);
    }

    @Override
    public void initNavMenu() {
        if (dataType == 0 || dataType == 1 || dataType == 2) {
            inflaterNavMenu(R.menu.repo_issus_right_nav_drawer_menu);
            setOnNavItemClickListener(new OnNavItemClickListener() {
                @Override
                public void onItemClick(MenuItem item) {
                    switch (item.getItemId()) {
                        case R.id.repo_issue_drawer_labels:
                            LabelsActivity.launchActivity(IssuesOrPullRequestsActivity.this, repoName, username);
                            break;
                        case R.id.repo_issue_drawer_milestones:
                            MilestoneActivity.launchActivity(IssuesOrPullRequestsActivity.this, repoName, username);
                            break;
                        default:
                            break;
                    }
                }
            });
        } else if (dataType == 3 || dataType == 4) {

        }
    }
}
