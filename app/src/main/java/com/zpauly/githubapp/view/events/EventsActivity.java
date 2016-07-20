package com.zpauly.githubapp.view.events;

import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import com.zpauly.githubapp.R;
import com.zpauly.githubapp.view.ToolbarActivity;

/**
 * Created by zpauly on 16-7-20.
 */

public class EventsActivity extends ToolbarActivity {
    private final String TAG = getClass().getName();

    private AppBarLayout mEventsABLayout;
    private TabLayout mEventsTBLayout;
    private ViewPager mEventsVP;

    @Override
    public void initViews() {

    }

    @Override
    public void initContent() {
        setContentView(R.layout.activity_events);
    }
}
