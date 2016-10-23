package com.zpauly.githubapp.view.issues;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.zpauly.githubapp.R;
import com.zpauly.githubapp.adapter.ViewPagerAdapter;
import com.zpauly.githubapp.entity.response.PullRequestBean;
import com.zpauly.githubapp.view.ToolbarActivity;

/**
 * Created by zpauly on 2016/10/22.
 */

public class PullRequestContentActivity extends ToolbarActivity {
    private final String TAG = getClass().getName();

    public static final String PULL_REQUEST = "PULL_REQUEST";

    private TabLayout mTabLayout;
    private ViewPager mViewPager;

    private ViewPagerAdapter mAdapter;

    private PullRequestBean pullRequestBean;

    @Override
    public void initViews() {
        getParams();

        setContent(R.layout.content_pull_request_content);

        mTabLayout = (TabLayout) findViewById(R.id.tablayout);
        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        mTabLayout.setVisibility(View.VISIBLE);

        mAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        PullRequestsConversationsFragment pullRequestsConversationsFragment =
                new PullRequestsConversationsFragment();
        PullRequestsCommitsFragment pullRequestsCommitsFragment =
                new PullRequestsCommitsFragment();
        PullRequestsFilesFragment pullRequestsFilesFragment =
                new PullRequestsFilesFragment();
        setupFragments(pullRequestsConversationsFragment, getString(R.string.conversations));
        setupFragments(pullRequestsCommitsFragment, getString(R.string.commits));
        setupFragments(pullRequestsFilesFragment, getString(R.string.files));
        mViewPager.setAdapter(mAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    @Override
    protected void setToolbar() {
        super.setToolbar();
        setToolbarTitle("#" + pullRequestBean.getNumber());
        setOnToolbarNavClickedListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void setupFragments(Fragment fragment, String title) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(PULL_REQUEST, pullRequestBean);
        fragment.setArguments(bundle);
        mAdapter.addFragment(fragment, title);
    }

    private void getParams() {
        pullRequestBean = getIntent().getParcelableExtra(PULL_REQUEST);
    }

    public static void launchActivity(Context context, PullRequestBean pullRequestBean) {
        Intent intent = new Intent();
        intent.putExtra(PULL_REQUEST, pullRequestBean);
        intent.setClass(context, PullRequestContentActivity.class);
        context.startActivity(intent);
    }
}
