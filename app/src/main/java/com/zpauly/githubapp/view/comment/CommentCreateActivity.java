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

    public static final String OWNER = "OWNER";
    public static final String REPO = "REPO";
    public static final String NUM = "NUM";

    private String owner;
    private String repo;
    private int number;

    @Override
    public void initViews() {
        getAttrs();

        setFragment();
    }

    private void getAttrs() {
        owner = getIntent().getStringExtra(OWNER);
        repo = getIntent().getStringExtra(REPO);
        number = getIntent().getIntExtra(NUM, -1);
    }

    private void setFragment() {
        Bundle bundle = new Bundle();
        bundle.putString(REPO, repo);
        bundle.putString(OWNER, owner);
        bundle.putInt(NUM, number);
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

    public static void launchActivity(Context context, String owner, String repo, int number) {
        Intent intent = new Intent();
        intent.putExtra(OWNER, owner);
        intent.putExtra(REPO, repo);
        intent.putExtra(NUM, number);
        intent.setClass(context, CommentCreateActivity.class);
        context.startActivity(intent);
    }
}
