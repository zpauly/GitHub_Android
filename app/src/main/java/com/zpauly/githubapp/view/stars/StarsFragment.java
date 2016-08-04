package com.zpauly.githubapp.view.stars;

import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zpauly.githubapp.R;
import com.zpauly.githubapp.adapter.ReposRecyclerViewAdapter;
import com.zpauly.githubapp.base.BaseFragment;
import com.zpauly.githubapp.db.ReposDao;
import com.zpauly.githubapp.db.ReposModel;
import com.zpauly.githubapp.entity.response.RepositoriesBean;
import com.zpauly.githubapp.presenter.star.StarContract;
import com.zpauly.githubapp.presenter.star.StarPresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zpauly on 16-7-16.
 */

public class StarsFragment extends BaseFragment implements StarContract.View {
    private final String TAG = getClass().getName();

    private StarContract.Presenter mPresenter;

    private SwipeRefreshLayout mStarredReposSRLayout;

    private RecyclerView mStarredReposRV;

    private ReposRecyclerViewAdapter mAdapter;

    private List<ReposModel> list = new ArrayList<>();

    @Override
    public void onStop() {
        if (mPresenter != null) {
            mPresenter.stop();
        }
        super.onStop();
    }

    @Override
    protected void initViews(View view) {
        new StarPresenter(getContext(), this);
        mPresenter.start();

        mStarredReposSRLayout = (SwipeRefreshLayout) view.findViewById(R.id.starred_repos_SRLayout);
        mStarredReposRV = (RecyclerView) view.findViewById(R.id.starred_repos_RV);

        setupRecyclerView();
        setupSwipeRefreshLayout();

        mStarredReposSRLayout.setRefreshing(true);
        ReposDao.deleteRepos();
        loadStarredRepositories();
    }

    private void setupSwipeRefreshLayout() {
        mStarredReposSRLayout.measure(View.MEASURED_SIZE_MASK, View.MEASURED_HEIGHT_STATE_SHIFT);
        mStarredReposSRLayout.setColorSchemeResources(R.color.colorAccent);
        mStarredReposSRLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                ReposDao.deleteRepos();
                mAdapter.setHasLoading(true);
                StarPresenter presenter = (StarPresenter) mPresenter;
                presenter.setPageId(1);
                loadStarredRepositories();
            }
        });
    }

    private void setupRecyclerView() {
        mAdapter = new ReposRecyclerViewAdapter(getContext());
        mStarredReposRV.setLayoutManager(new LinearLayoutManager(getContext()));
        mStarredReposRV.setAdapter(mAdapter);

        final LinearLayoutManager manager = (LinearLayoutManager) mStarredReposRV.getLayoutManager();
        mStarredReposRV.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int lastItemPosition = manager.findLastCompletelyVisibleItemPosition();
                int firstItemPosition = manager.findFirstCompletelyVisibleItemPosition();
                if (lastItemPosition == mAdapter.getItemCount() - 1
                        && firstItemPosition != mAdapter.getItemCount() - 1
                        && mAdapter.isHasMoreData()) {
                    if (!mStarredReposSRLayout.isRefreshing()) {
                        mAdapter.setHasLoading(true);
                        loadStarredRepositories();
                    }
                }
            }
        });
    }

    private void loadStarredRepositories() {
        mPresenter.getUserStarredRepos();
    }

    @Override
    protected View setContentView(LayoutInflater inflater, @Nullable ViewGroup container) {
        return inflater.inflate(R.layout.fragment_stars, container, false);
    }

    @Override
    public void setPresenter(StarContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void loading(List<RepositoriesBean> starredRepositories) {
        for (RepositoriesBean repo : starredRepositories) {
            ReposDao.insertRepo(repo);
        }
        Log.i(TAG, String.valueOf(starredRepositories.size()));
        if (starredRepositories == null || starredRepositories.size() == 0) {
            mAdapter.setHasLoading(false);
        }
        list.clear();
        list.addAll(ReposDao.queryRepos());
    }

    @Override
    public void loadFail() {
        mStarredReposSRLayout.setRefreshing(false);
    }

    @Override
    public void loadSuccess() {
        if (mStarredReposSRLayout.isRefreshing()) {
            mAdapter.swapAllData(new ArrayList<ReposModel>());
        }
        mAdapter.swapAllData(list);
        mStarredReposSRLayout.setRefreshing(false);
    }

    @Override
    public String getUsername() {
        return username;
    }
}
