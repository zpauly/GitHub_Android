package com.zpauly.githubapp.view.repositories;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.zpauly.githubapp.entity.response.repos.RepositoriesBean;
import com.zpauly.githubapp.view.ToolbarActivity;

/**
 * Created by zpauly on 16/9/20.
 */

public class RepoCommitActivity extends ToolbarActivity {
    private final String TAG = getClass().getName();

    public static final String REPO = "REPO";

    private RepositoriesBean repo;

    private void getAttrs() {
        repo = getIntent().getParcelableExtra(REPO);
    }

    @Override
    public void initViews() {
        getAttrs();

        setFragment();
    }

    private void setFragment() {
        RepoCommitFragment fragment = new RepoCommitFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(REPO, repo);
        fragment.setArguments(bundle);
        setContent(fragment);
    }

    public static void launchActiivty(Context context, RepositoriesBean bean) {
        Intent intent = new Intent();
        intent.putExtra(REPO, bean);
        intent.setClass(context, RepoCommitActivity.class);
        context.startActivity(intent);
    }
}
