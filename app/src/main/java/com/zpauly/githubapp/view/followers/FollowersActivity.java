package com.zpauly.githubapp.view.followers;

import android.support.design.widget.AppBarLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;

import com.zpauly.githubapp.R;
import com.zpauly.githubapp.view.ToolbarActivity;

/**
 * Created by zpauly on 16-6-15.
 */
public class FollowersActivity extends ToolbarActivity {
    private final String TAG = getClass().getName();

    public static final String FOLLOW_ID = "FOLLOW_ID";
    public static final int FOLLOWERS = 0;
    public static final int FOLLOWING = 1;

    private int followId;

    private AppBarLayout mABLayout;
    private SwipeRefreshLayout mSWLayout;
    private RecyclerView mContentRV;

    @Override
    public void initViews() {
        followId = getIntent().getIntExtra(FOLLOW_ID, -1);

        mABLayout = (AppBarLayout) findViewById(R.id.followers_ABLayout);
        mSWLayout = (SwipeRefreshLayout) findViewById(R.id.followers_SWLayout);
        mContentRV = (RecyclerView) findViewById(R.id.followers_content_RV);
    }

    @Override
    protected void setToolbar() {
        super.setToolbar();
        if (followId == FOLLOWERS) {
            setToolbarTitle(R.string.followers);
        } else if (followId == FOLLOWING) {
            setToolbarTitle(R.string.following);
        }
    }

    @Override
    public void initContent() {
        setContentView(R.layout.activity_followers);
    }
}
