package com.zpauly.githubapp.view.gists;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.zpauly.githubapp.view.ToolbarActivity;

/**
 * Created by zpauly on 16-8-7.
 */

public class GistFileActivity extends ToolbarActivity {
    private final String TAG = getClass().getName();

    public static final String FILE_ID = "FILE_ID";
    public static final String FILE_NAME = "FILE_NAME";

    private String fileName;
    private String id;

    @Override
    public void initViews() {
        getAttrs();

        setFragment();
    }

    private void getAttrs() {
        fileName = getIntent().getStringExtra(FILE_NAME);
        id = getIntent().getStringExtra(FILE_ID);
    }

    private void setFragment() {
        Bundle bundle = new Bundle();
        bundle.putString(FILE_NAME, fileName);
        bundle.putString(FILE_ID, id);
        GistFileFragment fragment = new GistFileFragment();
        fragment.setArguments(bundle);
        setContent(fragment);
    }

    @Override
    protected void setToolbar() {
        super.setToolbar();
        setToolbarTitle(fileName);
        setOnToolbarNavClickedListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    public static void launchActivity(Context context, String fileName, String id) {
        Intent intent = new Intent();
        intent.putExtra(FILE_NAME, fileName);
        intent.putExtra(FILE_ID, id);
        intent.setClass(context, GistFileActivity.class);
        context.startActivity(intent);
        ((Activity) context).finish();
    }
}
