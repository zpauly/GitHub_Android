package com.zpauly.githubapp.view;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;

import com.zpauly.githubapp.R;
import com.zpauly.githubapp.adapter.ViewPagerAdapter;

/**
 * Created by root on 16-8-6.
 */

public class TabLayoutActivity extends ToolbarActivity {
    private TabLayout mTBLayout;

    private SwipeRefreshLayout mSRLayout;

    private ViewPager mVP;

    private ViewPagerAdapter mVPAdapter;

    private SwipeRefreshLayout.OnRefreshListener mOnRefreshListener;

    public void setOnLayoutRefreshListener(SwipeRefreshLayout.OnRefreshListener listener) {
        this.mOnRefreshListener = listener;
    }

    @Override
    public void initViews() {
        mTBLayout = (TabLayout) findViewById(R.id.TBLayout);
        mSRLayout = (SwipeRefreshLayout) findViewById(R.id.SRLayout);
        mVP = (ViewPager) findViewById(R.id.VP);

        mVPAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        mVP.setAdapter(mVPAdapter);

        mTBLayout.setupWithViewPager(mVP);

        mSRLayout.measure(View.MEASURED_SIZE_MASK, View.MEASURED_HEIGHT_STATE_SHIFT);
        mSRLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (mOnRefreshListener != null) {
                    mOnRefreshListener.onRefresh();
                }
            }
        });
    }

    @Override
    public void initContent() {
        setContentView(R.layout.activity_tablayout);
    }

    public TabLayout getTabLayout() {
        return mTBLayout;
    }

    public SwipeRefreshLayout getSwipeRefreshLayout() {
        return mSRLayout;
    }

    public ViewPager getViewPager() {
        return mVP;
    }
}
