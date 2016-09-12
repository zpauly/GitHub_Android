package com.zpauly.githubapp.view.issues;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.zpauly.githubapp.R;
import com.zpauly.githubapp.view.ToolbarActivity;

/**
 * Created by zpauly on 16/9/12.
 */
public class IssueCreateActivity extends ToolbarActivity {
    private final String TAG = getClass().getName();

    public static final String USERNAME = "USERNAME";
    public static final String REPONAME = "REPONAME";

    private String username;
    private String repoName;

    @Override
    public void initViews() {
        getAttrs();

        setFragment();
    }

    private void getAttrs() {
        username = getIntent().getStringExtra(USERNAME);
        repoName = getIntent().getStringExtra(REPONAME);
    }

    private void setFragment() {
        Bundle bundle = new Bundle();
        bundle.putString(USERNAME, username);
        bundle.putString(REPONAME, repoName);
        IssueCreateFragment fragment = new IssueCreateFragment();
        fragment.setArguments(bundle);
        setContent(fragment);
    }

    @Override
    protected void setToolbar() {
        super.setToolbar();
        setToolbarTitle(R.string.create_issue);
        setOnToolbarNavClickedListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    public static void launchActivity(Context context, String username, String repoName) {
        Intent intent = new Intent();
        intent.putExtra(USERNAME, username);
        intent.putExtra(REPONAME, repoName);
        intent.setClass(context, IssueCreateActivity.class);
        context.startActivity(intent);
//        ((Activity) context).finish();
    }
}
