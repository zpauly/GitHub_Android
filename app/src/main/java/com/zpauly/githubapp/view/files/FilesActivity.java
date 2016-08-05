package com.zpauly.githubapp.view.files;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.zpauly.githubapp.view.ToolbarActivity;

/**
 * Created by zpauly on 16-8-1.
 */

public class FilesActivity extends ToolbarActivity {
    private final String TAG = getClass().getName();


    public static final String REPO = "REPO";
    public static final String OWNER = "OWNER";
    public static final String BRANCH = "BRANCH";
    public static final String URL = "URL";

    private String repo;
    private String owner;
    private String branch;
    private String url;
    private String path;

    @Override
    public void initViews() {

        getAttrs();

        setFragment();
    }


    private void getAttrs() {
        repo = getIntent().getStringExtra(REPO);
        owner = getIntent().getStringExtra(OWNER);
        branch = getIntent().getStringExtra(BRANCH);
        url = getIntent().getStringExtra(URL);
        path = "";
    }

    private void setFragment() {
        FilesFragment filesFragment = new FilesFragment();
        Bundle bundle = new Bundle();
        bundle.putString(REPO, repo);
        bundle.putString(OWNER, owner);
        bundle.putString(BRANCH, branch);
        bundle.putString(URL, url);
        filesFragment.setArguments(bundle);
        setContent(filesFragment);
    }

    @Override
    protected void setToolbar() {
        super.setToolbar();
        setToolbarTitle(repo);
        setOnToolbarNavClickedListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    public static void launchActivity(Context context, String owner, String repo, String branch, String url) {
        Intent intent = new Intent();
        intent.putExtra(REPO, repo);
        intent.putExtra(OWNER, owner);
        intent.putExtra(BRANCH, branch);
        intent.putExtra(URL, url);
        intent.setClass(context, FilesActivity.class);
        context.startActivity(intent);
//        ((Activity) context).finish();
    }
}
