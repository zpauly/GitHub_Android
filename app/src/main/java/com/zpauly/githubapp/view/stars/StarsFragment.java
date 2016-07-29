package com.zpauly.githubapp.view.stars;

import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zpauly.githubapp.R;
import com.zpauly.githubapp.adapter.ReposRecyclerViewAdapter;
import com.zpauly.githubapp.base.BaseFragment;
import com.zpauly.githubapp.db.ReposDao;
import com.zpauly.githubapp.db.ReposModel;
import com.zpauly.githubapp.entity.response.StarredRepositories;
import com.zpauly.githubapp.presenter.star.StarContract;
import com.zpauly.githubapp.presenter.star.StarPresenter;
import com.zpauly.githubapp.ui.DividerItemDecoration;

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
        loadStarredRepositories();
    }

    private void setupSwipeRefreshLayout() {
        mStarredReposSRLayout.measure(View.MEASURED_SIZE_MASK, View.MEASURED_HEIGHT_STATE_SHIFT);
        mStarredReposSRLayout.setColorSchemeResources(R.color.colorAccent);
        mStarredReposSRLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadStarredRepositories();
            }
        });
    }

    private void setupRecyclerView() {
        mAdapter = new ReposRecyclerViewAdapter(getContext());
        mStarredReposRV.setLayoutManager(new LinearLayoutManager(getContext()));
        mStarredReposRV.setAdapter(mAdapter);
        mStarredReposRV.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL_LIST));
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
    public void loading(List<StarredRepositories> starredRepositories) {
        ReposDao.deleteRepos();
        for (StarredRepositories repo : starredRepositories) {
            ReposDao.insertRepo(repo);
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
        mAdapter.swapData(list);
        mStarredReposSRLayout.setRefreshing(false);
    }
}
