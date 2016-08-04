package com.zpauly.githubapp.view.events;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

import com.zpauly.githubapp.R;
import com.zpauly.githubapp.view.ToolbarActivity;
import com.zpauly.githubapp.view.profile.OthersActivity;

/**
 * Created by zpauly on 16-7-20.
 */

public class EventsActivity extends ToolbarActivity {
    private final String TAG = getClass().getName();

    public static final String EVENTS_ID = "EVENTS_ID";
    public static final int RECEIVED_EVENTS = 0;
    public static final int USER_EVENTS = 1;

    private int eventsId;

    private AppBarLayout mEventsABLayout;

    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    @Override
    public void initViews() {
        eventsId = getIntent().getIntExtra(EVENTS_ID, -1);
        fragmentManager = getSupportFragmentManager();

        setFragment();
    }

    @Override
    protected void setToolbar() {
        super.setToolbar();
        setToolbarTitle(R.string.events);
        setOnToolbarNavClickedListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    public static void launchActivity(Context context, int eventsId) {
        Intent intent = new Intent();
        intent.putExtra(EVENTS_ID, eventsId);
        intent.setClass(context, EventsActivity.class);
        context.startActivity(intent);
    }

    @Override
    public void initContent() {
        setContentView(R.layout.activity_events);
    }

    private void setFragment() {
        switch (eventsId) {
            case -1:
                break;
            case RECEIVED_EVENTS:
                break;
            case USER_EVENTS:
                EventsFragment userFragment = new EventsFragment();
                Bundle bundle = new Bundle();
                bundle.putInt(EVENTS_ID, USER_EVENTS);
                bundle.putString(OthersActivity.USERNAME, username);
                userFragment.setArguments(bundle);
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.events_content, userFragment);
                fragmentTransaction.commit();
                break;
            default:
                break;
        }
    }
}
