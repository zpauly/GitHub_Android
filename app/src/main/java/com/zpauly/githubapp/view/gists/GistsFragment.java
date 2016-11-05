package com.zpauly.githubapp.view.gists;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zpauly.githubapp.R;
import com.zpauly.githubapp.adapter.GistsRecyclerViewAdapter;
import com.zpauly.githubapp.base.BaseFragment;
import com.zpauly.githubapp.entity.response.gists.GistsBean;
import com.zpauly.githubapp.presenter.gists.GistsContract;
import com.zpauly.githubapp.presenter.gists.GistsPresenter;
import com.zpauly.githubapp.ui.RefreshView;
import com.zpauly.githubapp.utils.viewmanager.LoadMoreInSwipeRefreshLayoutMoreManager;
import com.zpauly.githubapp.utils.viewmanager.RefreshViewManager;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by zpauly on 16-8-6.
 */

public class GistsFragment extends BaseFragment implements GistsContract.View {
    private final String TAG = getClass().getName();

    public static final String GISTS_ID = "gists_id";
    public static final int GISTS = 0;
    public static final int PUBLIC_GISTS = 1;
    public static final int STARRED_GISTS = 2;

    private GistsContract.Presenter mPresenter;

    private SwipeRefreshLayout mSRLayout;
    private RecyclerView mGistsRV;

    private RefreshView mRefreshView;

    private GistsRecyclerViewAdapter mGistsRVAdapter;

    private List<GistsBean> list = new ArrayList<>();

    private int gists_id = 0;

    private LoadMoreInSwipeRefreshLayoutMoreManager loadMoreInSwipeRefreshLayoutMoreManager;
    private RefreshViewManager refreshViewManager;

    @Override
    public void onStop() {
        mPresenter.stop();
        super.onStop();
    }

    @Override
    protected void initViews(View view) {
        getAttrs();

        new GistsPresenter(getContext(), this);

        mRefreshView = (RefreshView) view.findViewById(R.id.gists_RefreshView);

        mSRLayout = (SwipeRefreshLayout) view.findViewById(R.id.gists_SRLayout);
        mGistsRV = (RecyclerView) view.findViewById(R.id.gists_RV);

        setupRecyclerView();
        setupSwipeRefreshLayout();

        setViewManager(new LoadMoreInSwipeRefreshLayoutMoreManager(mGistsRV, mSRLayout) {
            @Override
            public void load() {
                loadGists();
            }
        }, new RefreshViewManager(mRefreshView, mSRLayout) {
            @Override
            public void load() {
                loadGists();
            }
        });

        loadMoreInSwipeRefreshLayoutMoreManager = getViewManager(LoadMoreInSwipeRefreshLayoutMoreManager.class);
        refreshViewManager = getViewManager(RefreshViewManager.class);
//
//        mRefreshView.setOnRefreshStateListener(new RefreshView.OnRefreshStateListener() {
//            @Override
//            public void beforeRefreshing() {
//
//            }
//
//            @Override
//            public void onRefreshSuccess() {
//                mRefreshView.setVisibility(View.GONE);
//                mSRLayout.setVisibility(View.VISIBLE);
//            }
//
//            @Override
//            public void onRefreshFail() {
//                mRefreshView.setVisibility(View.VISIBLE);
//                mSRLayout.setVisibility(View.GONE);
//            }
//        });
//        mRefreshView.startRefresh();
    }

    private void getAttrs() {
        Bundle bundle = getArguments();
        if (bundle != null) {
            gists_id = bundle.getInt(GISTS_ID);
        }
    }

    private void setupSwipeRefreshLayout() {
        mSRLayout.measure(View.MEASURED_SIZE_MASK, View.MEASURED_HEIGHT_STATE_SHIFT);
        mSRLayout.setColorSchemeResources(R.color.colorAccent);
        mSRLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                GistsPresenter presenter = (GistsPresenter) mPresenter;
                presenter.setPageId(1);

                loadMoreInSwipeRefreshLayoutMoreManager.setSwipeRefreshLayoutRefreshing(mGistsRVAdapter);
            }
        });
    }

    private void setupRecyclerView() {
        mGistsRVAdapter = new GistsRecyclerViewAdapter(getContext());

        mGistsRV.setLayoutManager(new LinearLayoutManager(getContext()));
        mGistsRV.setAdapter(mGistsRVAdapter);

//        final LinearLayoutManager manager = (LinearLayoutManager) mGistsRV.getLayoutManager();
//        mGistsRV.addOnScrollListener(new RecyclerView.OnScrollListener() {
//            @Override
//            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
//                int lastVisiableCompletePosition = manager.findLastVisibleItemPosition();
//                int firstVisiableCompletePosition = manager.findFirstVisibleItemPosition();
//                if (lastVisiableCompletePosition == mGistsRVAdapter.getItemCount() - 1
//                        && firstVisiableCompletePosition != mGistsRVAdapter.getItemCount() - 1
//                        && mGistsRVAdapter.isHasMoreData()) {
//                    if (!mSRLayout.isRefreshing()) {
//                        mGistsRVAdapter.setHasLoading(true);
//                        loadGists();
//                    }
//                }
//            }
//        });
    }

    @Override
    protected View setContentView(LayoutInflater inflater, @Nullable ViewGroup container) {
        return inflater.inflate(R.layout.fragment_gists, container, false);
    }

    private void loadGists() {
        switch (gists_id) {
            case GISTS:
                mPresenter.loadGists();
                break;
            case PUBLIC_GISTS:
                mPresenter.loadPublicGists();
                break;
            case STARRED_GISTS:
                mPresenter.loadStarredGists();
                break;
            default:
                break;
        }
    }

    @Override
    public void loadFail() {
        mSRLayout.setRefreshing(false);
        if (!mRefreshView.isRefreshSuccess()) {
            mRefreshView.refreshFail();
        } else {
            Snackbar.make(mRefreshView, R.string.error_occurred, Snackbar.LENGTH_SHORT);
        }
    }

    @Override
    public void loadSuccess() {
        mSRLayout.setRefreshing(false);
        mGistsRVAdapter.swapAllData(list);
        if (!mRefreshView.isRefreshSuccess()) {
            mRefreshView.refreshSuccess();
        }
    }

    @Override
    public void loadingGists(List<GistsBean> list) {
        if (mSRLayout.isRefreshing()) {
            this.list.clear();
        }
        if (!loadMoreInSwipeRefreshLayoutMoreManager.hasNoMoreData(list, mGistsRVAdapter)) {
            Iterator<GistsBean> iterator = list.iterator();
            while (iterator.hasNext()) {
                GistsBean bean = iterator.next();
                if (bean.getOwner() == null) {
                    iterator.remove();
                }
            }
            this.list.addAll(list);
        }
//        if (list == null || list.size() == 0) {
//            mGistsRVAdapter.setHasLoading(false);
//        } else {
//
//        }
    }

    @Override
    public void setPresenter(GistsContract.Presenter presenter) {
        this.mPresenter = presenter;
    }
}
