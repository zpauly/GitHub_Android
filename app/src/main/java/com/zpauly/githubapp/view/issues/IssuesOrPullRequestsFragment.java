package com.zpauly.githubapp.view.issues;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.zpauly.githubapp.R;
import com.zpauly.githubapp.adapter.IssuesRecyclerViewAdapter;
import com.zpauly.githubapp.adapter.PullRequestsRecyclerViewAdapter;
import com.zpauly.githubapp.entity.response.PullRequestBean;
import com.zpauly.githubapp.entity.response.issues.IssueBean;
import com.zpauly.githubapp.listener.OnMenuItemSelectedListener;
import com.zpauly.githubapp.network.issues.IssuesService;
import com.zpauly.githubapp.network.pullRequests.PullRequestsService;
import com.zpauly.githubapp.presenter.issues.IssuesOrPullRequestsContract;
import com.zpauly.githubapp.presenter.issues.IssuesOrPullRequestsPresenter;
import com.zpauly.githubapp.ui.FloatingActionButton;
import com.zpauly.githubapp.ui.RefreshView;
import com.zpauly.githubapp.utils.viewmanager.LoadMoreInSwipeRefreshLayoutMoreManager;
import com.zpauly.githubapp.utils.viewmanager.RefreshViewManager;
import com.zpauly.githubapp.view.ToolbarMenuFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zpauly on 16/9/4.
 */
public class IssuesOrPullRequestsFragment extends ToolbarMenuFragment implements IssuesOrPullRequestsContract.View {
    private final String TAG = getClass().getName();

    private IssuesOrPullRequestsContract.Presenter mPresenter;

    private RefreshView mRefreshView;
    private SwipeRefreshLayout mSRLayout;
    private RecyclerView mIssuesRV;
    private FloatingActionButton mIssueCreateFAB;

    private MenuItem mState;
    private MenuItem mFilter;
    private MenuItem mMore;
    private MenuItem mSort;

    //issue params
    private String state = IssuesService.STATE_OPEN;
    private String filter = IssuesService.FILTER_CREATED;
    private String sort = IssuesService.SORT_CREATED;
    private String direction = IssuesService.DIRECTION_DESC;

    //pull requests params
    private String pull_request_state = PullRequestsService.OPEN;
    private String pull_request_sort = PullRequestsService.CREATED;
    private String pull_request_direction = PullRequestsService.DESC;

    private IssuesRecyclerViewAdapter mIssuesAdapter;
    private PullRequestsRecyclerViewAdapter mPullRequestsAdapter;

    private List<IssueBean> issueList = new ArrayList<>();
    private List<PullRequestBean> pullRequestList = new ArrayList<>();

    private int dataType = IssuesOrPullRequestsActivity.USER_ISSUES;
    private String username;
    private String repoName;
    private String orgName;

    private LoadMoreInSwipeRefreshLayoutMoreManager loadMoreInSwipeRefreshLayoutMoreManager;
    private RefreshViewManager refreshViewManager;

    private DrawerLayout mRightDrawer;

    private boolean isIssues;

    @Override
    public void onStop() {
        mPresenter.stop();
        super.onStop();
    }

    @Override
    public void inflateMenu() {
        if (dataType == IssuesOrPullRequestsActivity.USER_ISSUES ||
                dataType == IssuesOrPullRequestsActivity.REPO_ISSUES ||
                dataType == IssuesOrPullRequestsActivity.ORG_ISSUES) {
            inflateMenu(R.menu.issue_toolbar_menu);
        } else if (dataType == IssuesOrPullRequestsActivity.REPO_PULL_REQUESTS ||
                dataType == IssuesOrPullRequestsActivity.USER_PULL_REQUESTS) {
            inflateMenu(R.menu.pull_requests_toolbar_menu);
        }
    }

    @Override
    public void createMenu(Menu menu) {
        if (dataType == IssuesOrPullRequestsActivity.USER_ISSUES ||
                dataType == IssuesOrPullRequestsActivity.REPO_ISSUES ||
                dataType == IssuesOrPullRequestsActivity.ORG_ISSUES) {
            setupIssuesMenu(menu);
        } else if (dataType == IssuesOrPullRequestsActivity.REPO_PULL_REQUESTS ||
                dataType == IssuesOrPullRequestsActivity.USER_PULL_REQUESTS) {
            setupPullRequestsMenu(menu);
        }
    }

    private void setupIssuesMenu(Menu menu) {
        mState = menu.findItem(R.id.issue_toolbar_state);
        mFilter = menu.findItem(R.id.issue_toolbar_filter);
        mMore = menu.findItem(R.id.issue_toolbar_selection);
        mSort = menu.findItem(R.id.issue_toolbar_sort);
        if (dataType == IssuesOrPullRequestsActivity.REPO_ISSUES) {
            mFilter.setVisible(false);
            mMore.setVisible(true);
        } else {
            mFilter.setVisible(true);
            mMore.setVisible(false);
        }
        setOnMenuItemSelectedListener(new OnMenuItemSelectedListener() {
            @Override
            public void onItemSelected(MenuItem item) {
                if (!mRefreshView.isRefreshSuccess())
                    return;
                Log.i(TAG, "menu item clicked");
                switch (item.getItemId()) {
                    case R.id.issue_toolbar_state:
                        if (state.equals(IssuesService.STATE_OPEN)) {
                            state = IssuesService.STATE_CLOSED;
                            mState.setIcon(R.drawable.ic_check);
                        } else if (state.equals(IssuesService.STATE_CLOSED)) {
                            state = IssuesService.STATE_OPEN;
                            mState.setIcon(R.drawable.ic_info_outline);
                        }
                        mSRLayout.setRefreshing(true);
                        setSwipeRefreshLayoutRefreshing();
                        break;
                    case R.id.issue_filter_created:
                        filter = IssuesService.FILTER_CREATED;
                        mSRLayout.setRefreshing(true);
                        setSwipeRefreshLayoutRefreshing();
                        break;
                    case R.id.issue_filter_assigned:
                        filter = IssuesService.FILTER_ASSIGNED;
                        mSRLayout.setRefreshing(true);
                        setSwipeRefreshLayoutRefreshing();
                        break;
                    case R.id.issue_filter_mentioned:
                        filter = IssuesService.FILTER_MENTIONED;
                        mSRLayout.setRefreshing(true);
                        setSwipeRefreshLayoutRefreshing();
                        break;
                    case R.id.issue_sort_newest:
                        menuSortChanged(IssuesService.SORT_CREATED, IssuesService.DIRECTION_DESC);
                        break;
                    case R.id.issue_sort_oldest:
                        menuSortChanged(IssuesService.SORT_CREATED, IssuesService.DIRECTION_ASC);
                        break;
                    case R.id.issue_sort_most_commented:
                        menuSortChanged(IssuesService.SORT_COMMENTS, IssuesService.DIRECTION_DESC);
                        break;
                    case R.id.issue_sort_least_commented:
                        menuSortChanged(IssuesService.SORT_COMMENTS, IssuesService.DIRECTION_ASC);
                        break;
                    case R.id.issue_sort_recently_updated:
                        menuSortChanged(IssuesService.SORT_UPDATED, IssuesService.DIRECTION_DESC);
                        break;
                    case R.id.issue_sort_least_recently_updated:
                        menuSortChanged(IssuesService.SORT_UPDATED, IssuesService.DIRECTION_ASC);
                        break;
                    case R.id.issue_toolbar_selection:
                        Log.i(TAG, "selection clicked");
                        if (mRightDrawer != null) {
                            mRightDrawer.openDrawer(GravityCompat.END);
                        }
                        break;
                    default:
                        break;
                }

            }
        });
    }

    private void setupPullRequestsMenu(final Menu menu) {
        mState = menu.findItem(R.id.pull_requests_toolbar_state);
        setOnMenuItemSelectedListener(new OnMenuItemSelectedListener() {
            @Override
            public void onItemSelected(MenuItem item) {
                if (!mRefreshView.isRefreshSuccess() || mSRLayout.isRefreshing()) {
                    return;
                }
                switch (item.getItemId()) {
                    case R.id.pull_requests_toolbar_state:
                        if (pull_request_state.equals(PullRequestsService.OPEN)) {
                            pull_request_state = PullRequestsService.CLOSED;
                            mState.setIcon(R.drawable.ic_check);
                        } else if (pull_request_state.equals(PullRequestsService.CLOSED)) {
                            pull_request_state = PullRequestsService.OPEN;
                            mState.setIcon(R.drawable.ic_info_outline);
                        }
                        mSRLayout.setRefreshing(true);
                        setSwipeRefreshLayoutRefreshing();
                        break;
                    case R.id.pull_requests_sort_newest:
                        menuSortChanged(PullRequestsService.CREATED, PullRequestsService.DESC);
                        break;
                    case R.id.pull_requests_sort_oldest:
                        menuSortChanged(PullRequestsService.CREATED, PullRequestsService.ASC);
                        break;
                    case R.id.pull_requests_sort_most_commented:
                        menuSortChanged(PullRequestsService.POPULARITY, PullRequestsService.DESC);
                        break;
                    case R.id.pull_requests_sort_least_commented:
                        menuSortChanged(PullRequestsService.POPULARITY, PullRequestsService.ASC);
                        break;
                    case R.id.pull_requests_sort_recently_updated:
                        menuSortChanged(PullRequestsService.UPDATED, PullRequestsService.DESC);
                        break;
                    case R.id.pull_requests_sort_least_recently_updated:
                        menuSortChanged(PullRequestsService.UPDATED, PullRequestsService.ASC);
                        break;
                    default:
                        break;
                }
            }
        });
    }

    private void menuSortChanged(String sort, String direction) {
        if (isIssues) {
            this.sort = sort;
            this.direction = direction;
        } else {
            this.pull_request_sort = sort;
            this.pull_request_direction = direction;
        }
        mSRLayout.setRefreshing(true);
        setSwipeRefreshLayoutRefreshing();
    }

    private void setSwipeRefreshLayoutRefreshing() {
        mPresenter.setPageId(1);
        if (isIssues) {
            loadMoreInSwipeRefreshLayoutMoreManager.setSwipeRefreshLayoutRefreshing(mIssuesAdapter);
        } else {
            loadMoreInSwipeRefreshLayoutMoreManager.setSwipeRefreshLayoutRefreshing(mPullRequestsAdapter);
        }
    }

    @Override
    protected void initViews(View view) {
        getAttrs();

        new IssuesOrPullRequestsPresenter(getContext(), this);

        mRefreshView = (RefreshView) view.findViewById(R.id.issue_RefreshView);
        mSRLayout = (SwipeRefreshLayout) view.findViewById(R.id.issue_SRLayout);
        mIssuesRV = (RecyclerView) view.findViewById(R.id.issue_RV);
        mIssueCreateFAB = (FloatingActionButton) view.findViewById(R.id.issue_create_FAB);

        mRightDrawer = (DrawerLayout) getActivity().findViewById(R.id.nav_drawer_layout);

        setupSwipeRefreshLayout();
        setupRecyclerView();
        setupFloatingActionButton();

        setViewManager(new LoadMoreInSwipeRefreshLayoutMoreManager(mIssuesRV, mSRLayout) {
            @Override
            public void load() {
                getData();
            }
        }, new RefreshViewManager(mRefreshView, mSRLayout) {
            @Override
            public void load() {

            }

            @Override
            public void setRefreshView(final RefreshView refreshView, final View... otherView) {
                refreshView.setOnRefreshStateListener(new RefreshView.OnRefreshStateListener() {
                    @Override
                    public void beforeRefreshing() {
                        getData();
                    }

                    @Override
                    public void onRefreshSuccess() {
                        refreshView.setVisibility(View.GONE);
                        mSRLayout.setVisibility(View.VISIBLE);
                        if (dataType == IssuesOrPullRequestsActivity.REPO_ISSUES)
                            mIssueCreateFAB.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onRefreshFail() {
                        refreshView.setVisibility(View.VISIBLE);
                        mSRLayout.setVisibility(View.GONE);
                        if (dataType == IssuesOrPullRequestsActivity.REPO_ISSUES)
                            mIssueCreateFAB.setVisibility(View.GONE);
                    }
                });
            }
        });

         loadMoreInSwipeRefreshLayoutMoreManager = getViewManager(LoadMoreInSwipeRefreshLayoutMoreManager.class);
        refreshViewManager = getViewManager(RefreshViewManager.class);
    }

    @Override
    protected View setContentView(LayoutInflater inflater, @Nullable ViewGroup container) {
        return inflater.inflate(R.layout.fragment_issue, container, false);
    }

    private void getAttrs() {
        Bundle bundle = getArguments();
        if (bundle != null) {
            dataType = bundle.getInt(IssuesOrPullRequestsActivity.DATA_TYPE, IssuesOrPullRequestsActivity.USER_ISSUES);
            username = bundle.getString(IssuesOrPullRequestsActivity.USERNAME);
            repoName = bundle.getString(IssuesOrPullRequestsActivity.REPO_NAME);
            orgName = bundle.getString(IssuesOrPullRequestsActivity.ORG_NAME);
            if (dataType == IssuesOrPullRequestsActivity.USER_ISSUES ||
                    dataType == IssuesOrPullRequestsActivity.ORG_ISSUES ||
                    dataType == IssuesOrPullRequestsActivity.REPO_ISSUES) {
                isIssues = true;
            } else if (dataType == IssuesOrPullRequestsActivity.USER_PULL_REQUESTS ||
                    dataType == IssuesOrPullRequestsActivity.REPO_PULL_REQUESTS) {
                isIssues = false;
            }
        }
    }

    private void setupFloatingActionButton() {
        mIssueCreateFAB.attachButtonToRecyclerView(mIssuesRV);
        mIssueCreateFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IssueCreateActivity.launchActivity(getContext(), username, repoName);
            }
        });
    }

    private void setupSwipeRefreshLayout() {
        mSRLayout.measure(View.MEASURED_SIZE_MASK, View.MEASURED_HEIGHT_STATE_SHIFT);
        mSRLayout.setColorSchemeResources(R.color.colorAccent);
        mSRLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                setSwipeRefreshLayoutRefreshing();
            }
        });
    }

    private void setupRecyclerView() {
        mIssuesRV.setLayoutManager(new LinearLayoutManager(getContext()));
        if (isIssues) {
            mIssuesAdapter = new IssuesRecyclerViewAdapter(getContext());
            mIssuesAdapter.setOwner(username);
            mIssuesRV.setAdapter(mIssuesAdapter);
        } else {
            mPullRequestsAdapter = new PullRequestsRecyclerViewAdapter(getContext());
            mIssuesRV.setAdapter(mPullRequestsAdapter);
        }
    }

    private void getData() {
        if (isIssues) {
            getIssues();
        } else {
            getPullRequests();
        }
    }

    private void getPullRequests() {
        mPresenter.getPullRequests();
    }

    private void getIssues() {
        mPresenter.getIssues();
    }

    @Override
    public String getState() {
        if (isIssues) {
            return state;
        } else {
            return pull_request_state;
        }
    }

    @Override
    public String getFilter() {
        return filter;
    }

    @Override
    public String getSort() {
        if (isIssues) {
            return sort;
        } else {
            return pull_request_sort;
        }
    }

    @Override
    public String getDirection() {
        if (isIssues) {
            return direction;
        } else {
            return pull_request_direction;
        }
    }

    @Override
    public int getIssueType() {
        return dataType;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getRepoName() {
        return repoName;
    }

    @Override
    public String getOrgName() {
        return orgName;
    }

    @Override
    public void getIssueSuccess() {
        mSRLayout.setRefreshing(false);
        mIssuesAdapter.swapAllData(issueList);
        if (!mRefreshView.isRefreshSuccess()) {
            mRefreshView.refreshSuccess();
        }
    }

    @Override
    public void getIssueFail() {
        mSRLayout.setRefreshing(false);
        mRefreshView.refreshFail();
        if (!mRefreshView.isRefreshSuccess()) {
            mPresenter.setPageId(1);
        }
    }

    @Override
    public void gettingIssues(List<IssueBean> list) {
        if (mSRLayout.isRefreshing()) {
            this.issueList.clear();
        }
        Log.i(TAG, String.valueOf(list.size()));
        loadMoreInSwipeRefreshLayoutMoreManager.hasNoMoreData(list, mIssuesAdapter);
        this.issueList.addAll(list);
    }

    @Override
    public void getPullRequestsSuccess() {
        mSRLayout.setRefreshing(false);
        mPullRequestsAdapter.swapAllData(pullRequestList);
        if (!mRefreshView.isRefreshSuccess()) {
            mRefreshView.refreshSuccess();
        }
    }

    @Override
    public void getPullRequestsFail() {
        mSRLayout.setRefreshing(false);
        if (!mRefreshView.isRefreshSuccess()) {
            mRefreshView.refreshFail();
        } else {
            Snackbar.make(mRefreshView, R.string.error_occurred, Snackbar.LENGTH_SHORT);
        }
    }

    @Override
    public void gettingPullRequests(List<PullRequestBean> pullRequestBeen) {
        if (mSRLayout.isRefreshing()) {
            this.pullRequestList.clear();
        }
        Log.i(TAG, String.valueOf(pullRequestBeen.size()));
        loadMoreInSwipeRefreshLayoutMoreManager.hasNoMoreData(pullRequestBeen, mPullRequestsAdapter);
        this.pullRequestList.addAll(pullRequestBeen);
    }

    @Override
    public void setPresenter(IssuesOrPullRequestsContract.Presenter presenter) {
        this.mPresenter = presenter;
    }
}
