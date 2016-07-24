package com.zpauly.githubapp.view.events;

import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zpauly.githubapp.R;
import com.zpauly.githubapp.adapter.EventsRecyclerViewAdapter;
import com.zpauly.githubapp.base.BaseFragment;
import com.zpauly.githubapp.entity.response.events.EventsBean;
import com.zpauly.githubapp.presenter.events.EventsContract;
import com.zpauly.githubapp.presenter.events.EventsPresenter;
import com.zpauly.githubapp.ui.DividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zpauly on 16-7-20.
 */

public class EventsFragment extends BaseFragment implements EventsContract.View {
    private final String TAG = getClass().getName();

    private EventsContract.Presenter mPresenter;

    private SwipeRefreshLayout mEventsSRLayout;
    private RecyclerView mEventsRV;

    private EventsRecyclerViewAdapter mAdapter;
    private int item_count = 0;

    private int events_id = -1;

    private List<EventsBean> list = new ArrayList<>();

    @Override
    protected void initViews(View view) {
        events_id = getArguments().getInt(EventsActivity.EVENTS_ID);

        new EventsPresenter(getContext(), this);

        mEventsSRLayout = (SwipeRefreshLayout) view.findViewById(R.id.events_SRLayout);
        mEventsRV = (RecyclerView) view.findViewById(R.id.events_RV);

        setupSwipeRefreshLayout();
        setupRecyclerView();


        mEventsSRLayout.setRefreshing(true);
        loadData();
    }

    private void setupSwipeRefreshLayout() {
        mEventsSRLayout.measure(View.MEASURED_SIZE_MASK, View.MEASURED_HEIGHT_STATE_SHIFT);
        mEventsSRLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadData();
            }
        });
    }

    private void setupRecyclerView() {
        mAdapter = new EventsRecyclerViewAdapter(getContext());
        mEventsRV.setLayoutManager(new LinearLayoutManager(getContext()));
        mEventsRV.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL_LIST));
        mEventsRV.setAdapter(mAdapter);
    }

    private void loadData() {
        switch (events_id) {
            case -1:
                mEventsSRLayout.setRefreshing(false);
                break;
            case EventsActivity.RECEIVED_EVENTS:
                mPresenter.getReceivedEvents();
                break;
            case EventsActivity.USER_EVENTS:
                mPresenter.getUserEvents();
                break;
            default:
                break;
        }
    }

    @Override
    protected View setContentView(LayoutInflater inflater, @Nullable ViewGroup container) {
        return inflater.inflate(R.layout.fragment_events, container, false);
    }

    @Override
    public void loadEvents(List<EventsBean> eventsBeanList) {
        list = eventsBeanList;
        if (list.get(0).getPayload() == null) {
            Log.i(TAG, "payload is null");
        }
    }

    @Override
    public void loadFail() {
        mEventsSRLayout.setRefreshing(false);
    }

    @Override
    public void loadSuccess() {
        item_count += 10;
        mAdapter.swapData(list);
        mEventsSRLayout.setRefreshing(false);
    }

    @Override
    public void setPresenter(EventsContract.Presenter presenter) {
        this.mPresenter = presenter;
    }
}
