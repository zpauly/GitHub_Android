package com.zpauly.githubapp.view.comment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.zpauly.githubapp.R;
import com.zpauly.githubapp.view.ToolbarActivity;

/**
 * Created by zpauly on 16/9/8.
 */
public class CommentCreateActivity extends ToolbarActivity {
    private final String TAG = getClass().getName();

    public static final String COMMENT_TYPE = "COMMENT_TYPE";
    public static final int TYPE_COMMIT = 0;
    public static final int TYPE_ISSUE = 1;

    public static final String OWNER = "OWNER";
    public static final String REPO = "REPO";
    public static final String NUM = "NUM";
    public static final String SHA = "SHA";

    private int commentType;
    private String owner;
    private String repo;
    private String sha;
    private int number;

    @Override
    public void initViews() {
        getAttrs();

        setFragment();
    }

    private void getAttrs() {
        commentType = getIntent().getIntExtra(COMMENT_TYPE, -1);
        owner = getIntent().getStringExtra(OWNER);
        repo = getIntent().getStringExtra(REPO);
        number = getIntent().getIntExtra(NUM, -1);
        sha = getIntent().getStringExtra(SHA);
    }

    private void setFragment() {
        Bundle bundle = new Bundle();
        bundle.putInt(COMMENT_TYPE, commentType);
        bundle.putString(REPO, repo);
        bundle.putString(OWNER, owner);
        bundle.putInt(NUM, number);
        bundle.putString(SHA, sha);
        CommentCreateFragment fragment = new CommentCreateFragment();
        fragment.setArguments(bundle);
        setContent(fragment);
    }

    @Override
    protected void setToolbar() {
        super.setToolbar();
        setToolbarTitle(R.string.create_comment);
        setOnToolbarNavClickedListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    public static void launchIssueCommentActivity(Context context, String owner, String repo, int number) {
        Intent intent = new Intent();
        intent.putExtra(COMMENT_TYPE, TYPE_ISSUE);
        intent.putExtra(OWNER, owner);
        intent.putExtra(REPO, repo);
        intent.putExtra(NUM, number);
        intent.setClass(context, CommentCreateActivity.class);
        context.startActivity(intent);
    }

    public static void launchCommitCommentActivity(Context context, String owner, String repo,
                                                   String sha) {
        Intent intent = new Intent();
        intent.putExtra(COMMENT_TYPE, TYPE_COMMIT);
        intent.putExtra(OWNER, owner);
        intent.putExtra(REPO, repo);
        intent.putExtra(SHA, sha);
        intent.setClass(context, CommentCreateActivity.class);
        context.startActivity(intent);
    }
}
