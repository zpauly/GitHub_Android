package com.zpauly.githubapp.view.repositories;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.zpauly.githubapp.entity.response.repos.ReleaseBean;
import com.zpauly.githubapp.view.ToolbarActivity;

/**
 * Created by zpauly on 16/9/25.
 */

public class ReleaseContentActivity extends ToolbarActivity {
    private final String TAG = getClass().getName();

    public static final String RELEASE = "RELEASE";

    private ReleaseBean releaseBean;

    @Override
    public void initViews() {
        getAttrs();

        setFragment();
    }

    private void getAttrs() {
        releaseBean = getIntent().getParcelableExtra(RELEASE);
    }

    @Override
    protected void setToolbar() {
        super.setToolbar();
        setToolbarTitle("");
        setOnToolbarNavClickedListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void setFragment() {
        Bundle bundle = new Bundle();
        bundle.putParcelable(RELEASE, releaseBean);
        ReleaseContentFragment fragment = new ReleaseContentFragment();
        fragment.setArguments(bundle);
        setContent(fragment);
    }

    public static void launchActivity(Context context, ReleaseBean releaseBean) {
        Intent intent = new Intent();
        intent.putExtra(RELEASE, releaseBean);
        intent.setClass(context, ReleaseContentActivity.class);
        context.startActivity(intent);
    }
}
