package com.zpauly.githubapp.view.issues;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.zpauly.githubapp.R;
import com.zpauly.githubapp.adapter.IssuesRecyclerViewAdapter;
import com.zpauly.githubapp.entity.response.issues.IssueBean;
import com.zpauly.githubapp.listener.OnMenuItemSelectedListener;
import com.zpauly.githubapp.network.issues.IssuesService;
import com.zpauly.githubapp.presenter.issues.IssuesContract;
import com.zpauly.githubapp.presenter.issues.IssuesPresenter;
import com.zpauly.githubapp.ui.FloatingActionButton;
import com.zpauly.githubapp.ui.RefreshView;
import com.zpauly.githubapp.view.ToolbarMenuFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zpauly on 16/9/4.
 */
public class IssuesFragment extends ToolbarMenuFragment implements IssuesContract.View {
    private final String TAG = getClass().getName();

    private IssuesContract.Presenter mPresenter;

    private RefreshView mRefreshView;
    private SwipeRefreshLayout mSRLayout;
    private RecyclerView mIssuesRV;
    private FloatingActionButton mIssueCreateFAB;

    private MenuItem mState;
    private MenuItem mFilter;
    private MenuItem mSort;

    private String state = IssuesService.STATE_OPEN;
    private String filter = IssuesService.FILTER_CREATED;
    private String sort = IssuesService.SORT_CREATED;
    private String direction = IssuesService.DIRECTION_DESC;

    private IssuesRecyclerViewAdapter mIssuesAdapter;

    private List<IssueBean> list = new ArrayList<>();

    private int issueType = IssuesActivity.USER_ISSUES;
    private String username;
    private String repoName;
    private String orgName;

    @Override
    public void onStop() {
        mPresenter.stop();
        super.onStop();
    }

    @Override
    public void inflateMenu() {
        inflateMenu(R.menu.issue_toolbar_menu);
    }

    @Override
    public void createMenu(Menu menu) {
        mState = menu.findItem(R.id.issue_toolbar_state);
        mFilter = menu.findItem(R.id.issue_toolbar_filter);
        mSort = menu.findItem(R.id.issue_toolbar_sort);
        if (issueType == IssuesActivity.REPO_ISSUES) {
            mFilter.setVisible(false);
        }
        setOnMenuItemSelectedListener(new OnMenuItemSelectedListener() {
            @Override
            public void onItemSelected(MenuItem item) {
                if (!mRefreshView.isRefreshSuccess())
                    return;
                item.setChecked(true);
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
                        sort = IssuesService.SORT_CREATED;
                        direction = IssuesService.DIRECTION_DESC;
                        mSRLayout.setRefreshing(true);
                        setSwipeRefreshLayoutRefreshing();
                        break;
                    case R.id.issue_sort_oldest:
                        sort = IssuesService.SORT_CREATED;
                        direction = IssuesService.DIRECTION_ASC;
                        mSRLayout.setRefreshing(true);
                        setSwipeRefreshLayoutRefreshing();
                        break;
                    case R.id.issue_sort_most_commented:
                        sort = IssuesService.SORT_COMMENTS;
                        direction = IssuesService.DIRECTION_DESC;
                        mSRLayout.setRefreshing(true);
                        setSwipeRefreshLayoutRefreshing();
                        break;
                    case R.id.issue_sort_least_commented:
                        sort = IssuesService.SORT_COMMENTS;
                        direction = IssuesService.DIRECTION_ASC;
                        mSRLayout.setRefreshing(true);
                        setSwipeRefreshLayoutRefreshing();
                        break;
                    case R.id.issue_sort_recently_updated:
                        sort = IssuesService.SORT_UPDATED;
                        direction = IssuesService.DIRECTION_DESC;
                        mSRLayout.setRefreshing(true);
                        setSwipeRefreshLayoutRefreshing();
                        break;
                    case R.id.issue_sort_least_recently_updated:
                        sort = IssuesService.SORT_UPDATED;
                        direction = IssuesService.DIRECTION_ASC;
                        mSRLayout.setRefreshing(true);
                        setSwipeRefreshLayoutRefreshing();
                        break;
                    default:
                        break;
                }

            }
        });
    }

    private void setSwipeRefreshLayoutRefreshing() {
        mIssuesAdapter.setHasLoading(true);
        mPresenter.setPageId(1);
        getIssues();
    }

    @Override
    protected void initViews(View view) {
        getAttrs();

        new IssuesPresenter(getContext(), this);

        mRefreshView = (RefreshView) view.findViewById(R.id.issue_RefreshView);
        mSRLayout = (SwipeRefreshLayout) view.findViewById(R.id.issue_SRLayout);
        mIssuesRV = (RecyclerView) view.findViewById(R.id.issue_RV);
        mIssueCreateFAB = (FloatingActionButton) view.findViewById(R.id.issue_create_FAB);

        setupSwipeRefreshLayout();
        setupRecyclerView();
        setupFloatingActionButton();

        mRefreshView.setOnRefreshStateListener(new RefreshView.OnRefreshStateListener() {
            @Override
            public void beforeRefreshing() {
                mIssuesAdapter.setHasLoading(false);
                getIssues();
            }

            @Override
            public void onRefreshSuccess() {
                mRefreshView.setVisibility(View.GONE);
                mSRLayout.setVisibility(View.VISIBLE);
                if (!(issueType == IssuesActivity.USER_ISSUES))
                    mIssueCreateFAB.setVisibility(View.VISIBLE);
            }

            @Override
            public void onRefreshFail() {
                mRefreshView.setVisibility(View.VISIBLE);
                mSRLayout.setVisibility(View.GONE);
                if (!(issueType == IssuesActivity.USER_ISSUES))
                    mIssueCreateFAB.setVisibility(View.GONE);
            }
        });
        mRefreshView.startRefresh();
    }

    @Override
    protected View setContentView(LayoutInflater inflater, @Nullable ViewGroup container) {
        return inflater.inflate(R.layout.fragment_issue, container, false);
    }

    private void getAttrs() {
        Bundle bundle = getArguments();
        if (bundle != null) {
            issueType = bundle.getInt(IssuesActivity.ISSUE_TYPE);
            username = bundle.getString(IssuesActivity.USERNAME);
            repoName = bundle.getString(IssuesActivity.REPO_NAME);
            orgName = bundle.getString(IssuesActivity.ORG_NAME);
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
        mIssuesAdapter = new IssuesRecyclerViewAdapter(getContext());
        mIssuesAdapter.setOwner(username);
        mIssuesRV.setLayoutManager(new LinearLayoutManager(getContext()));
        mIssuesRV.setAdapter(mIssuesAdapter);

        final LinearLayoutManager manager = (LinearLayoutManager) mIssuesRV.getLayoutManager();
        mIssuesRV.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int lastItemPosition = manager.findLastCompletelyVisibleItemPosition();
                int firstItemPosition = manager.findFirstCompletelyVisibleItemPosition();
                if (lastItemPosition == mIssuesAdapter.getItemCount() - 1
                        && firstItemPosition != mIssuesAdapter.getItemCount() - 1
                        && mIssuesAdapter.isHasMoreData()) {
                    if (!mSRLayout.isRefreshing()) {
                        getIssues();
                    }
                }
            }
        });
    }

    private void getIssues() {
        mPresenter.getIssues();
    }

    @Override
    public String getState() {
        return state;
    }

    @Override
    public String getFilter() {
        return filter;
    }

    @Override
    public String getSort() {
        return sort;
    }

    @Override
    public String getDirection() {
        return direction;
    }

    @Override
    public int getIssueType() {
        return issueType;
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
        mIssuesAdapter.swapAllData(list);
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
            this.list.clear();
        }
        if (list == null || list.size() == 0)
            mIssuesAdapter.setHasLoading(false);
        this.list.addAll(list);
    }

    @Override
    public void setPresenter(IssuesContract.Presenter presenter) {
        this.mPresenter = presenter;
    }
}
