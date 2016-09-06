package com.zpauly.githubapp.view.issues;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.zpauly.githubapp.entity.response.issues.IssueBean;
import com.zpauly.githubapp.view.ToolbarActivity;

/**
 * Created by zpauly on 16/9/5.
 */
public class IssueContentActivity extends ToolbarActivity {
    private final String TAG = getClass().getName();

    public static final String ISSUE = "ISSUE";
    public static final String ISSUE_OWNER = "ISSUE_OWNER";

    private IssueBean issue;
    private String owner;

    @Override
    public void initViews() {
        getAttrs();

        setFragment();
    }

    @Override
    protected void setToolbar() {
        super.setToolbar();
        setToolbarTitle("ISSUE #" + issue.getNumber());
        setOnToolbarNavClickedListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    private void setFragment() {
        Bundle bundle = new Bundle();
        bundle.putParcelable(ISSUE, issue);
        bundle.putString(ISSUE_OWNER, owner);
        IssueContentFragment fragment = new IssueContentFragment();
        fragment.setArguments(bundle);
        setContent(fragment);
    }

    private void getAttrs() {
        issue = getIntent().getParcelableExtra(ISSUE);
        owner = getIntent().getStringExtra(ISSUE_OWNER);
    }

    public static void launchActivity(Context context, IssueBean issue, String owner) {
        Intent intent = new Intent();
        intent.putExtra(ISSUE, issue);
        intent.putExtra(ISSUE_OWNER, owner);
        intent.setClass(context, IssueContentActivity.class);
        context.startActivity(intent);
    }
}
