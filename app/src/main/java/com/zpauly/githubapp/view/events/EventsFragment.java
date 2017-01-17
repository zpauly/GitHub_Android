package com.zpauly.githubapp.view.events;

import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
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
import com.zpauly.githubapp.utils.viewmanager.LoadMoreInSwipeRefreshLayoutMoreManager;
import com.zpauly.githubapp.utils.viewmanager.RefreshViewManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by zpauly on 16-7-20.
 */

public class EventsFragment extends BaseFragment implements EventsContract.View {
    private final String TAG = getClass().getName();

    private EventsContract.Presenter mPresenter;

    @BindView(R.id.events_RefreshView) public RefreshView mRefreshView;
    @BindView(R.id.events_SRLayout) public SwipeRefreshLayout mEventsSRLayout;
    @BindView(R.id.events_RV) public RecyclerView mEventsRV;

    private EventsRecyclerViewAdapter mAdapter;

    private int events_id = -1;

    private List<EventsBean> list = new ArrayList<>();

    private LoadMoreInSwipeRefreshLayoutMoreManager loadMoreInSwipeRefreshLayoutMoreManager;
    private RefreshViewManager refreshViewManager;

    @Override
    public void onStop() {
        if (mPresenter != null) {
            mPresenter.stop();
        }
        super.onStop();
    }

    @Override
    protected void getParams() {
        events_id = getArguments().getInt(EventsActivity.EVENTS_ID);
    }

    @Override
    protected void initViews(View view) {
        new EventsPresenter(getContext(), this);

        setupSwipeRefreshLayout();
        setupRecyclerView();

        setViewManager(new LoadMoreInSwipeRefreshLayoutMoreManager(mEventsRV, mEventsSRLayout) {
            @Override
            public void load() {
                loadData();
            }
        }, new RefreshViewManager(mRefreshView, mEventsSRLayout) {
            @Override
            public void load() {
                loadData();
            }
        });

        loadMoreInSwipeRefreshLayoutMoreManager = getViewManager(LoadMoreInSwipeRefreshLayoutMoreManager.class);
        refreshViewManager = getViewManager(RefreshViewManager.class);
    }

    private void setupSwipeRefreshLayout() {
        mEventsSRLayout.measure(View.MEASURED_SIZE_MASK, View.MEASURED_HEIGHT_STATE_SHIFT);
        mEventsSRLayout.setColorSchemeResources(R.color.colorAccent);
        mEventsSRLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                EventsPresenter presenter = (EventsPresenter) mPresenter;
                presenter.setPageId(1);
                loadMoreInSwipeRefreshLayoutMoreManager.setSwipeRefreshLayoutRefreshing(mAdapter);
            }
        });
    }

    private void setupRecyclerView() {
        mAdapter = new EventsRecyclerViewAdapter(getContext());
        mEventsRV.setLayoutManager(new LinearLayoutManager(getContext()));
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
        loadMoreInSwipeRefreshLayoutMoreManager.hasNoMoreData(eventsBeanList, mAdapter);
        if (mEventsSRLayout.isRefreshing()) {
            list.clear();
        }
        list.addAll(eventsBeanList);
    }

    @Override
    public void loadFail() {
        mEventsSRLayout.setRefreshing(false);
        if (!mRefreshView.isRefreshSuccess()) {
            mRefreshView.refreshFail();
        } else {
            Snackbar.make(mRefreshView, R.string.error_occurred, Snackbar.LENGTH_SHORT);
        }
    }

    @Override
    public void loadSuccess() {
        mAdapter.swapAllData(list);
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
