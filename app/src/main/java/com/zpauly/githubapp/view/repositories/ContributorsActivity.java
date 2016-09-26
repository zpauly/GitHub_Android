package com.zpauly.githubapp.view.repositories;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.zpauly.githubapp.R;
import com.zpauly.githubapp.utils.viewmanager.LoadMoreInSwipeRefreshLayoutMoreManager;
import com.zpauly.githubapp.view.ToolbarActivity;

/**
 * Created by zpauly on 16/9/25.
 */

public class ContributorsActivity extends ToolbarActivity {
    private final String TAG = getClass().getName();

    public static final String OWNER = "OWNER";
    public static final String REPO = "REPO";

    private String owner;
    private String repo;

    @Override
    public void initViews() {
        getAttrs();

        setFragment();
    }

    private void getAttrs() {
        owner = getIntent().getStringExtra(OWNER);
        repo = getIntent().getStringExtra(REPO);
    }

    @Override
    protected void setToolbar() {
        super.setToolbar();
        setToolbarTitle(R.string.contributor);
        setOnToolbarNavClickedListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void setFragment() {
        Bundle bundle = new Bundle();
        bundle.putString(OWNER, owner);
        bundle.putString(REPO, repo);
        ContributorsFragment fragment = new ContributorsFragment();
        fragment.setArguments(bundle);
        setContent(fragment);
    }

    public static void launchActivity(Context context, String owner, String repo) {
        Intent intent = new Intent();
        intent.putExtra(OWNER, owner);
        intent.putExtra(REPO, repo);
        intent.setClass(context, ContributorsActivity.class);
        context.startActivity(intent);
    }
}
