package com.zpauly.githubapp.view.repositories;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.zpauly.githubapp.R;
import com.zpauly.githubapp.entity.response.repos.RepositoriesBean;
import com.zpauly.githubapp.view.ToolbarActivity;

/**
 * Created by zpauly on 16/9/20.
 */

public class RepoCommitActivity extends ToolbarActivity {
    private final String TAG = getClass().getName();

    public static final String REPONAME = "REPONAME";
    public static final String OWNER = "OWNER";

    private String repoName;
    private String owner;

    private void getAttrs() {
        repoName = getIntent().getStringExtra(REPONAME);
        owner = getIntent().getStringExtra(OWNER);
    }

    @Override
    public void initViews() {
        getAttrs();

        setFragment();
    }

    @Override
    protected void setToolbar() {
        super.setToolbar();
        setToolbarTitle(R.string.commits);
        setOnToolbarNavClickedListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void setFragment() {
        RepoCommitFragment fragment = new RepoCommitFragment();
        Bundle bundle = new Bundle();
        bundle.putString(REPONAME, repoName);
        bundle.putString(OWNER, owner);
        fragment.setArguments(bundle);
        setContent(fragment);
    }

    public static void launchActiivty(Context context, String owner, String repoName) {
        Intent intent = new Intent();
        intent.putExtra(REPONAME, repoName);
        intent.putExtra(OWNER, owner);
        intent.setClass(context, RepoCommitActivity.class);
        context.startActivity(intent);
    }
}
