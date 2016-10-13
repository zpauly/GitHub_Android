package com.zpauly.githubapp.view.issues;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.zpauly.githubapp.R;
import com.zpauly.githubapp.view.ToolbarActivity;

/**
 * Created by zpauly on 2016/10/13.
 */

public class LabelsActivity extends ToolbarActivity {
    private final String TAG = getClass().getName();

    public static final String REPO = "REPO";
    public static final String OWNER = "OWNER";

    private String repo;
    private String owner;

    @Override
    public void initViews() {
        getParams();

        setFragment();
    }

    @Override
    protected void setToolbar() {
        super.setToolbar();
        setToolbarTitle(R.string.labels);
        setOnToolbarNavClickedListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void getParams() {
        repo = getIntent().getStringExtra(REPO);
        owner = getIntent().getStringExtra(OWNER);
    }

    private void setFragment() {
        Bundle bundle = new Bundle();
        bundle.putString(REPO, repo);
        bundle.putString(OWNER, owner);
        LabelsFragment fragment = new LabelsFragment();
        fragment.setArguments(bundle);
        setContent(fragment);
    }

    public static void launchActivity(Context context, String repo, String owner) {
        Intent intent = new Intent();
        intent.putExtra(REPO, repo);
        intent.putExtra(OWNER, owner);
        intent.setClass(context, LabelsActivity.class);
        context.startActivity(intent);
    }
}
