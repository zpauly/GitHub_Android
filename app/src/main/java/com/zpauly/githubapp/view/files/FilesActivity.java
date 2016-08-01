package com.zpauly.githubapp.view.files;

import android.support.design.widget.AppBarLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;

import com.zpauly.githubapp.R;
import com.zpauly.githubapp.view.ToolbarActivity;

/**
 * Created by zpauly on 16-8-1.
 */

public class FilesActivity extends ToolbarActivity {
    private final String TAG = getClass().getName();

    private AppBarLayout mABLayout;
    private SwipeRefreshLayout mSRLayout;
    private RecyclerView mPathRV;
    private RecyclerView mContentRV;

    @Override
    public void initViews() {
        mABLayout = (AppBarLayout) findViewById(R.id.files_ABLayout);
        mSRLayout = (SwipeRefreshLayout) findViewById(R.id.files_SRLayout);
        mPathRV = (RecyclerView) findViewById(R.id.files_path_RV);
        mContentRV = (RecyclerView) findViewById(R.id.files_content_RV);
    }

    @Override
    public void initContent() {
        setContentView(R.layout.activity_files);
    }
}
