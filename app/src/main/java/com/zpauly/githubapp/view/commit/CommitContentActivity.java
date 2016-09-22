package com.zpauly.githubapp.view.commit;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.zpauly.githubapp.view.ToolbarActivity;

/**
 * Created by zpauly on 16/9/22.
 */

public class CommitContentActivity extends ToolbarActivity {
    private final String TAG = getClass().getName();

    public static final String OWNER = "OWNER";
    public static final String REPO = "REPO";
    public static final String SHA = "SHA";

    private String owner;
    private String repo;
    private String sha;

    @Override
    public void initViews() {
        getAttrs();

        setFragment();
    }

    @Override
    protected void setToolbar() {
        super.setToolbar();
        setToolbarTitle(sha.substring(0, 8));
        setOnToolbarNavClickedListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void getAttrs() {
        owner = getIntent().getStringExtra(OWNER);
        repo = getIntent().getStringExtra(REPO);
        sha = getIntent().getStringExtra(SHA);
    }

    private void setFragment() {
        Bundle bundle = new Bundle();
        bundle.putString(OWNER, owner);
        bundle.putString(REPO, repo);
        bundle.putString(SHA, sha);
        CommitContentFragment fragment = new CommitContentFragment();
        fragment.setArguments(bundle);
        setContent(fragment);
    }

    public static void launchActivity(Context context, String owner, String repo, String sha) {
        Intent intent = new Intent();
        intent.putExtra(OWNER, owner);
        intent.putExtra(REPO, repo);
        intent.putExtra(SHA, sha);
        intent.setClass(context, CommitContentActivity.class);
        context.startActivity(intent);
    }
}
