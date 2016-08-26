package com.zpauly.githubapp.view.repositories;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.zpauly.githubapp.view.ToolbarActivity;

/**
 * Created by zpauly on 16-7-31.
 */

public class RepoContentActivity extends ToolbarActivity {
    private final String TAG = getClass().getName();

    public static final String FULL_NAME = "FULL_NAME";
    public static final String NAME = "NAME";
    public static final String REPO_URL = "REPO_URL";
    public static final String LOGIN = "LOGIN";

    private String full_name;
    private String name;
    private String login;
    private String repo_url;

    @Override
    public void initViews() {
        getAttrs();

        setFragment();
    }

    private void getAttrs() {
        Intent intent = getIntent();
        full_name = intent.getStringExtra(FULL_NAME);
        name = intent.getStringExtra(NAME);
        repo_url = intent.getStringExtra(REPO_URL);
        login = intent.getStringExtra(LOGIN);
    }

    private void setFragment() {
        Bundle bundle = new Bundle();
        bundle.putString(FULL_NAME, full_name);
        bundle.putString(NAME, name);
        bundle.putString(REPO_URL, repo_url);
        bundle.putString(LOGIN, login);
        RepoContentFragment repoContentFragment = new RepoContentFragment();
        repoContentFragment.setArguments(bundle);
        setContent(repoContentFragment);
    }

    @Override
    protected void setToolbar() {
        super.setToolbar();
        setToolbarTitle(name);
        setOnToolbarNavClickedListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    public static void launchActivity(Context context, String full_name, String name,
                                      String repo_url, String login) {
        Intent intent = new Intent();
        intent.putExtra(FULL_NAME, full_name);
        intent.putExtra(NAME, name);
        intent.putExtra(REPO_URL, repo_url);
        intent.putExtra(LOGIN, login);
        intent.setClass(context, RepoContentActivity.class);
        context.startActivity(intent);
//        ((Activity) context).finish();
    }
}
