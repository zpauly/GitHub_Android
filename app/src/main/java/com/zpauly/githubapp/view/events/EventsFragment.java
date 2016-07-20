package com.zpauly.githubapp.view.events;

import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zpauly.githubapp.R;
import com.zpauly.githubapp.base.BaseFragment;

/**
 * Created by zpauly on 16-7-20.
 */

public class EventsFragment extends BaseFragment {
    private final String TAG = getClass().getName();

    private SwipeRefreshLayout mEventsSRLayout;
    private RecyclerView mEventsRV;

    @Override
    protected void initViews(View view) {
        mEventsSRLayout = (SwipeRefreshLayout) view.findViewById(R.id.events_SRLayout);
        mEventsRV = (RecyclerView) view.findViewById(R.id.events_RV);
    }

    @Override
    protected View setContentView(LayoutInflater inflater, @Nullable ViewGroup container) {
        return inflater.inflate(R.layout.fragment_events, container, false);
    }
}
