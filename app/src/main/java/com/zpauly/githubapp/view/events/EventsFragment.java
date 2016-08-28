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
import com.zpauly.githubapp.ui.RefreshView;

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

    private RefreshView mRefreshView;

    private int events_id = -1;

    private List<EventsBean> list = new ArrayList<>();

    @Override
    public void onStop() {
        if (mPresenter != null) {
            mPresenter.stop();
        }
        super.onStop();
    }

    @Override
    protected void initViews(View view) {
        events_id = getArguments().getInt(EventsActivity.EVENTS_ID);

        new EventsPresenter(getContext(), this);

        mRefreshView = (RefreshView) view.findViewById(R.id.events_RefreshView);
        mEventsSRLayout = (SwipeRefreshLayout) view.findViewById(R.id.events_SRLayout);
        mEventsRV = (RecyclerView) view.findViewById(R.id.events_RV);

        setupSwipeRefreshLayout();
        setupRecyclerView();


        mRefreshView.setOnRefreshStateListener(new RefreshView.OnRefreshStateListener() {
            @Override
            public void beforeRefreshing() {
                loadData();
            }

            @Override
            public void onRefreshSuccess() {
                mRefreshView.setVisibility(View.GONE);
                mEventsSRLayout.setVisibility(View.VISIBLE);
            }

            @Override
            public void onRefreshFail() {
                mRefreshView.setVisibility(View.VISIBLE);
                mEventsSRLayout.setVisibility(View.GONE);
            }
        });
        mRefreshView.startRefresh();
    }

    private void setupSwipeRefreshLayout() {
        mEventsSRLayout.measure(View.MEASURED_SIZE_MASK, View.MEASURED_HEIGHT_STATE_SHIFT);
        mEventsSRLayout.setColorSchemeResources(R.color.colorAccent);
        mEventsSRLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                EventsPresenter presenter = (EventsPresenter) mPresenter;
                presenter.setPageId(1);
                mAdapter.setHasLoading(true);
                mAdapter.hideLoadingView();
                loadData();
                mAdapter.swapAllData(new ArrayList<EventsBean>());
            }
        });
    }

    private void setupRecyclerView() {
        mAdapter = new EventsRecyclerViewAdapter(getContext());
        mEventsRV.setLayoutManager(new LinearLayoutManager(getContext()));
        mEventsRV.setAdapter(mAdapter);

        final LinearLayoutManager manager = (LinearLayoutManager) mEventsRV.getLayoutManager();
        mEventsRV.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int lastItemPosition = manager.findLastCompletelyVisibleItemPosition();
                int firstItemPosition = manager.findFirstCompletelyVisibleItemPosition();
                if (lastItemPosition == mAdapter.getItemCount() - 1
                        && firstItemPosition != mAdapter.getItemCount() - 1
                        && mAdapter.isHasMoreData()) {
                    if (!mEventsSRLayout.isRefreshing()) {
                        mAdapter.setHasLoading(true);
                        loadData();
                    }
                }
            }
        });
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
        list.clear();
        list.addAll(eventsBeanList);
    }

    @Override
    public void loadFail() {
        mEventsSRLayout.setRefreshing(false);
        mRefreshView.refreshFail();
    }

    @Override
    public void loadSuccess() {
        if (list == null || list.size() == 0) {
            mAdapter.setHasLoading(false);
        }
        mAdapter.addAllData(list);
        mEventsSRLayout.setRefreshing(false);
        if (!mRefreshView.isRefreshSuccess()) {
            mRefreshView.refreshSuccess();
        }
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public void setPresenter(EventsContract.Presenter presenter) {
        this.mPresenter = presenter;
    }
}
