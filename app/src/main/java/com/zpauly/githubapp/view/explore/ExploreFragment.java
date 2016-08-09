package com.zpauly.githubapp.view.explore;

import android.support.annotation.Nullable;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.zpauly.githubapp.R;
import com.zpauly.githubapp.adapter.ReposRecyclerViewAdapter;
import com.zpauly.githubapp.base.BaseFragment;

/**
 * Created by zpauly on 16-8-9.
 */

public class ExploreFragment extends BaseFragment {
    private final String TAG = getClass().getName();

    private SwipeRefreshLayout mExploreSRLayout;
    private RecyclerView mExploreRV;

    private ReposRecyclerViewAdapter mReposAdapter;

    @Override
    protected void initViews(View view) {
        mExploreSRLayout = (SwipeRefreshLayout) view.findViewById(R.id.explore_SRLayout);
        mExploreRV = (RecyclerView) view.findViewById(R.id.explore_RV);

        setupSwipeRefreshLayout();
        setupRecyclerView();
    }

    private void setupSwipeRefreshLayout() {
        mExploreSRLayout.measure(View.MEASURED_SIZE_MASK, View.MEASURED_HEIGHT_STATE_SHIFT);
        mExploreSRLayout.setColorSchemeResources(R.color.colorAccent);
        mExploreSRLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

            }
        });
    }

    private void setupRecyclerView() {
        mReposAdapter = new ReposRecyclerViewAdapter(getContext());
        mExploreRV.setLayoutManager(new LinearLayoutManager(getContext()));
        mExploreRV.setAdapter(mReposAdapter);
    }

    @Override
    protected View setContentView(LayoutInflater inflater, @Nullable ViewGroup container) {
        return inflater.inflate(R.layout.fragment_explore, container, false);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.toolbar_menu, menu);

        final MenuItem menuItem = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(menuItem);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }
}
