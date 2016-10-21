package com.zpauly.githubapp.view.issues;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.zpauly.githubapp.R;
import com.zpauly.githubapp.listener.OnMenuItemSelectedListener;
import com.zpauly.githubapp.listener.OnNavItemClickListener;
import com.zpauly.githubapp.view.RightDrawerActivity;
import com.zpauly.githubapp.view.ToolbarActivity;

/**
 * Created by zpauly on 16/9/5.
 */
public class IssuesActivity extends RightDrawerActivity {
    private final String TAG = getClass().getName();

    public static final String ISSUE_NAME = "ISSUE_NAME";
    public static final String ISSUE_TYPE = "ISSUE_TYPE";
    public static final int REPO_ISSUES = 0;
    public static final int ORG_ISSUES = 1;
    public static final int USER_ISSUES = 2;

    public static final String REPO_NAME = "REPO_NAME";
    public static final String ORG_NAME = "ORG_NAME";
    public static final String USERNAME = "USERNAME";

    private int issueType;
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
        issueType = getIntent().getIntExtra(ISSUE_TYPE, USER_ISSUES);
        repoName = getIntent().getStringExtra(REPO_NAME);
        orgName = getIntent().getStringExtra(ORG_NAME);
        username = getIntent().getStringExtra(USERNAME);
        switch (issueType) {
            case USER_ISSUES:
                toolbarTitle = getString(R.string.issues);
                break;
            case REPO_ISSUES:
                toolbarTitle = repoName;
                break;
            case ORG_ISSUES:
                toolbarTitle = orgName;
                break;
            default:
                break;
        }
    }

    private void setFragment() {
        Bundle bundle = new Bundle();
        bundle.putInt(ISSUE_TYPE, issueType);
        bundle.putString(REPO_NAME, repoName);
        bundle.putString(ORG_NAME, orgName);
        bundle.putString(USERNAME, username);
        IssuesFragment issuesFragment = new IssuesFragment();
        issuesFragment.setArguments(bundle);
        setContent(issuesFragment);
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
        intent.putExtra(ISSUE_TYPE, USER_ISSUES);
        intent.setClass(context, IssuesActivity.class);
        context.startActivity(intent);
    }

    public static void launchRepoIssuesActivity(Context context, String username, String repoName) {
        Intent intent = new Intent();
        intent.putExtra(ISSUE_TYPE, REPO_ISSUES);
        intent.putExtra(USERNAME, username);
        intent.putExtra(REPO_NAME, repoName);
        intent.setClass(context, IssuesActivity.class);
        context.startActivity(intent);
//        ((Activity) context).finish();
    }

    public static void launchOrgIssuesActivity(Context context, String orgName) {
        Intent intent = new Intent();
        intent.putExtra(ISSUE_TYPE, ORG_ISSUES);
        intent.putExtra(ORG_NAME, orgName);
        intent.setClass(context, IssuesActivity.class);
        context.startActivity(intent);
    }

    @Override
    public void initNavMenu() {
        inflaterNavMenu(R.menu.repo_issus_right_nav_drawer_menu);
        setOnNavItemClickListener(new OnNavItemClickListener() {
            @Override
            public void onItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.repo_issue_drawer_labels:
                        LabelsActivity.launchActivity(IssuesActivity.this, repoName, username);
                        break;
                    case R.id.repo_issue_drawer_milestones:
                        MilestoneActivity.launchActivity(IssuesActivity.this, repoName, username);
                        break;
                    default:
                        break;
                }
            }
        });
//        Menu menu = getNav().getMenu();
//        MenuItem label = menu.findItem(R.id.repo_issue_drawer_labels);
//        label.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
//            @Override
//            public boolean onMenuItemClick(MenuItem item) {
//
//                return false;
//            }
//        });
//        MenuItem milestone = menu.findItem(R.id.repo_issue_drawer_milestones);
//        milestone.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
//            @Override
//            public boolean onMenuItemClick(MenuItem item) {
//                closeDrawer();
//                return false;
//            }
//        });
    }
}
