package com.zpauly.githubapp.view.commit;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.zpauly.githubapp.entity.response.repos.FileBean;
import com.zpauly.githubapp.view.ToolbarActivity;

/**
 * Created by zpauly on 16/9/22.
 */

public class CommitFilePatchActivity extends ToolbarActivity {
    private final String TAG = getClass().getName();

    public static final String FILE = "FILE";

    private FileBean fileBean;

    @Override
    public void initViews() {
        getAttrs();

        setFragment();
    }

    private void getAttrs() {
        fileBean = getIntent().getParcelableExtra(FILE);
    }

    @Override
    protected void setToolbar() {
        super.setToolbar();
        setToolbarTitle(fileBean.getSha().substring(0, 8));
        setOnToolbarNavClickedListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void setFragment() {
        Bundle bundle = new Bundle();
        bundle.putParcelable(FILE, fileBean);
        CommitFilePatchFragment fragment = new CommitFilePatchFragment();
        fragment.setArguments(bundle);
        setContent(fragment);
    }

    public static void launchActivity(Context context, FileBean fileBean) {
        Intent intent = new Intent();
        intent.putExtra(FILE, fileBean);
        intent.setClass(context, CommitFilePatchActivity.class);
        context.startActivity(intent);
    }
}
