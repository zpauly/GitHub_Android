package com.zpauly.githubapp.view.comment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.zpauly.githubapp.R;
import com.zpauly.githubapp.view.ToolbarActivity;

/**
 * Created by zpauly on 16/9/22.
 */

public class CommentActivity extends ToolbarActivity {
    private final String TAG = getClass().getName();

    public static final String COMMENT_TYPE = "COMMENT_TYPE";
    public static final int TYPE_COMMIT = 0;

    public static final String OWNER = "OWNER";
    public static final String REPO = "REPO";
    public static final String REF = "REF";

    private int commentType;

    private String owner;
    private String repo;
    private String ref;

    private void getAttrs(){
        commentType = getIntent().getIntExtra(COMMENT_TYPE, -1);
        switch (commentType) {
            case TYPE_COMMIT:
                owner = getIntent().getStringExtra(OWNER);
                repo = getIntent().getStringExtra(REPO);
                ref = getIntent().getStringExtra(REF);
                break;
            default:
                break;
        }

    }

    @Override
    public void initViews() {
        getAttrs();

        setFragment();
    }

    @Override
    protected void setToolbar() {
        super.setToolbar();
        setToolbarTitle(R.string.comments);
        setOnToolbarNavClickedListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void setFragment() {
        Bundle bundle = new Bundle();
        bundle.putInt(COMMENT_TYPE, commentType);
        bundle.putString(OWNER, owner);
        bundle.putString(REPO, repo);
        bundle.putString(REF, ref);
        CommentFragment fragment = new CommentFragment();
        fragment.setArguments(bundle);
        setContent(fragment);
    }

    public static void launchCommitCommentActivity(Context context, String owner,
                                                   String repo, String ref) {
        Intent intent = new Intent();
        intent.putExtra(COMMENT_TYPE, TYPE_COMMIT);
        intent.putExtra(OWNER, owner);
        intent.putExtra(REPO, repo);
        intent.putExtra(REF, ref);
        intent.setClass(context, CommentActivity.class);
        context.startActivity(intent);
    }
}
