package com.zpauly.githubapp.view.explore;

import android.support.annotation.Nullable;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.zpauly.githubapp.R;
import com.zpauly.githubapp.adapter.RepositoriesRecyclerViewAdapter;
import com.zpauly.githubapp.adapter.UsersRecyclerViewAdapter;
import com.zpauly.githubapp.base.BaseFragment;
import com.zpauly.githubapp.entity.response.repos.RepositoriesBean;
import com.zpauly.githubapp.entity.response.UserBean;
import com.zpauly.githubapp.entity.response.search.SearchCodeBean;
import com.zpauly.githubapp.entity.response.search.SearchReposBean;
import com.zpauly.githubapp.entity.response.search.SearchUsersBean;
import com.zpauly.githubapp.network.search.SearchService;
import com.zpauly.githubapp.presenter.explore.ExploreContract;
import com.zpauly.githubapp.presenter.explore.ExplorePresenter;
import com.zpauly.githubapp.utils.viewmanager.LoadMoreInSwipeRefreshLayoutMoreManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zpauly on 16-8-9.
 */

public class ExploreFragment extends BaseFragment implements ExploreContract.View {
    private final String TAG = getClass().getName();

    private static final int TYPE_REPOS = 0;
    private static final int TYPE_CODE = 1;
    private static final int TYPE_USERS = 2;

    private ExploreContract.Presenter mPresenter;

    private SwipeRefreshLayout mExploreSRLayout;
    private RecyclerView mExploreRV;

    private RepositoriesRecyclerViewAdapter mReposAdapter;
    private UsersRecyclerViewAdapter mUsersAdapter;

    private List<RepositoriesBean> reposList = new ArrayList<>();
    private List<UserBean> usersList = new ArrayList<>();

    private String sort;
    private String order;
    private int typeTag = TYPE_REPOS;

    private String query;

    private LoadMoreInSwipeRefreshLayoutMoreManager loadMoreInSwipeRefreshLayoutMoreManager;

    @Override
    public void onStop() {
        mPresenter.stop();
        super.onStop();
    }

    @Override
    protected void initViews(View view) {
        new ExplorePresenter(getContext(), this);

        mExploreSRLayout = (SwipeRefreshLayout) view.findViewById(R.id.explore_SRLayout);
        mExploreRV = (RecyclerView) view.findViewById(R.id.explore_RV);

        setupSwipeRefreshLayout();
        setupRecyclerView();

        setViewManager(new LoadMoreInSwipeRefreshLayoutMoreManager(mExploreRV, mExploreSRLayout) {
            @Override
            public void load() {
                searchRepos();
            }
        });

        loadMoreInSwipeRefreshLayoutMoreManager = getViewManager(LoadMoreInSwipeRefreshLayoutMoreManager.class);
    }

    private void setupSwipeRefreshLayout() {
        mExploreSRLayout.measure(View.MEASURED_SIZE_MASK, View.MEASURED_HEIGHT_STATE_SHIFT);
        mExploreSRLayout.setColorSchemeResources(R.color.colorAccent);
        mExploreSRLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (query == null) {
                    mExploreSRLayout.setRefreshing(false);
                } else {
                    mPresenter.setPageId(1);
                    switch (typeTag) {
                        case TYPE_REPOS:
                            loadMoreInSwipeRefreshLayoutMoreManager.setSwipeRefreshLayoutRefreshing(mReposAdapter);
                            break;
                        case TYPE_USERS:
                            loadMoreInSwipeRefreshLayoutMoreManager.setSwipeRefreshLayoutRefreshing(mUsersAdapter);
                            break;
                        case TYPE_CODE:
                            break;
                        default:
                            break;
                    }
                }
            }
        });
    }

    private void setupRecyclerView() {
        mReposAdapter = new RepositoriesRecyclerViewAdapter(getContext());
        mUsersAdapter = new UsersRecyclerViewAdapter(getContext());
        mExploreRV.setLayoutManager(new LinearLayoutManager(getContext()));
        mExploreRV.setAdapter(mReposAdapter);
        mReposAdapter.hideLoadingView();
        mUsersAdapter.hideLoadingView();
    }

    private void searchRepos() {
        switch (typeTag) {
            case TYPE_REPOS:
                mPresenter.getSearchRepos();
                break;
            case TYPE_CODE:
                mPresenter.getSearchCode();
                break;
            case TYPE_USERS:
                mPresenter.getSearchUsers();
                break;
            default:
                break;
        }
    }

    @Override
    protected View setContentView(LayoutInflater inflater, @Nullable ViewGroup container) {
        return inflater.inflate(R.layout.fragment_explore, container, false);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        item.setChecked(true);
        switch (item.getItemId()) {
            case R.id.explore_repos:
                typeTag = TYPE_REPOS;
                mExploreRV.setAdapter(mReposAdapter);
                break;
            case R.id.explore_repos_sort_best_match:
                sort = null;
                order = null;
                break;
            case R.id.explore_repos_sort_most_stars:
                sort = SearchService.SORT_STARS;
                order = SearchService.ORDER_DESC;
                break;
            case R.id.explore_repos_sort_fewest_stars:
                sort = SearchService.SORT_STARS;
                order = SearchService.ORDER_ASC;
                break;
            case R.id.explore_repos_sort_most_forks:
                sort = SearchService.SORT_FORKS;
                order = SearchService.ORDER_DESC;
                break;
            case R.id.explore_repos_sort_fewest_forks:
                sort = SearchService.SORT_FORKS;
                order = SearchService.ORDER_ASC;
                break;
            case R.id.explore_repos_sort_recently_updated:
                sort = SearchService.SORT_UPDATED;
                order = SearchService.ORDER_DESC;
                break;
            case R.id.explore_repos_sort_least_recently_updated:
                sort = SearchService.SORT_UPDATED;
                order = SearchService.ORDER_ASC;
                break;
            //---------------------------------------------------
            case R.id.explore_code:
                typeTag = TYPE_CODE;
                break;
            case R.id.explore_code_sort_best_match:
                sort = null;
                order = null;
                break;
            case R.id.explore_code_sort_recently_indexed:
                sort = SearchService.SORT_INDEXED;
                order = SearchService.ORDER_DESC;
                break;
            case R.id.explore_code_sort_least_recently_indexed:
                sort = SearchService.SORT_INDEXED;
                order = SearchService.ORDER_ASC;
                break;
            //---------------------------------------------------
            case R.id.explore_users:
                typeTag = TYPE_USERS;
                mExploreRV.setAdapter(mUsersAdapter);
                break;
            case R.id.explore_users_sort_best_match:
                sort = null;
                order = null;
                break;
            case R.id.explore_users_sort_most_followers:
                sort = SearchService.SORT_FOLLOWERS;
                order = SearchService.ORDER_DESC;
                break;
            case R.id.explore_users_sort_fewest_followers:
                sort = SearchService.SORT_FOLLOWERS;
                order = SearchService.ORDER_ASC;
                break;
            case R.id.explore_users_sort_most_recently_joined:
                sort = SearchService.SORT_JOINED;
                order = SearchService.ORDER_DESC;
                break;
            case R.id.explore_users_sort_least_recently_joined:
                sort = SearchService.SORT_JOINED;
                order = SearchService.ORDER_ASC;
                break;
            case R.id.explore_users_sort_most_repositories:
                sort = SearchService.SORT_REPOSITORIES;
                order = SearchService.ORDER_DESC;
                break;
            case R.id.explore_users_sort_fewest_repositories:
                sort = SearchService.SORT_REPOSITORIES;
                order = SearchService.ORDER_ASC;
                break;
            default:
                break;
        }
        return true;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.explore_toolbar_menu, menu);

        MenuItem searchItem = menu.findItem(R.id.explore_search);
        MenuItem sortItem = menu.findItem(R.id.explore_sort);

        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                ExploreFragment.this.query = query;
                mExploreSRLayout.setRefreshing(true);
                switch (typeTag) {
                    case TYPE_REPOS:
                        break;
                    case TYPE_CODE:
                        break;
                    case TYPE_USERS:
                        break;
                    default:
                        break;
                }
                searchRepos();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

    @Override
    public String getSort() {
        return sort;
    }

    @Override
    public String getOrder() {
        return order;
    }

    @Override
    public String getQuery() {
        return query;
    }

    @Override
    public void searchingUsers(SearchUsersBean bean) {
        Log.i(TAG, String.valueOf(bean.getItems().size()));
        loadMoreInSwipeRefreshLayoutMoreManager.hasNoMoreData(bean.getItems(), mUsersAdapter);
        usersList.addAll(bean.getItems());
    }

    @Override
    public void searchingCode(SearchCodeBean bean) {
        Log.i(TAG, String.valueOf(bean.getItems().size()));
    }

    @Override
    public void searchingRepos(SearchReposBean bean) {
        Log.i(TAG, String.valueOf(bean.getItems().size()));
        loadMoreInSwipeRefreshLayoutMoreManager.hasNoMoreData(bean.getItems(), mReposAdapter);
        reposList.addAll(bean.getItems());
    }

    @Override
    public void searchSuccess() {
        mExploreSRLayout.setRefreshing(false);
        switch (typeTag) {
            case TYPE_REPOS:
                mReposAdapter.swapAllData(reposList);
                break;
            case TYPE_CODE:
                break;
            case TYPE_USERS:
                mUsersAdapter.swapAllData(usersList);
                break;
            default:
                break;
        }
    }

    @Override
    public void searchFail() {
        mExploreSRLayout.setRefreshing(false);
    }

    @Override
    public void setPresenter(ExploreContract.Presenter presenter) {
        mPresenter = presenter;
    }
}
