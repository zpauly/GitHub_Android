package com.zpauly.githubapp.view.issues;

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
import com.zpauly.githubapp.listener.OnMenuItemSelectedListener;
import com.zpauly.githubapp.network.issues.IssuesService;
import com.zpauly.githubapp.presenter.issues.IssuesContract;
import com.zpauly.githubapp.ui.RefreshView;
import com.zpauly.githubapp.view.ToolbarMenuFragment;

/**
 * Created by zpauly on 16/9/4.
 */
public class IssuesFragment extends ToolbarMenuFragment implements IssuesContract.View {
    private final String TAG = getClass().getName();

    private IssuesContract.Presenter mPresenter;

    private RefreshView mRefreshView;
    private SwipeRefreshLayout mSRLayout;
    private RecyclerView mIssuesRV;

    private MenuItem mState;
    private MenuItem mFilter;
    private MenuItem mSort;

    private String state = IssuesService.STATE_OPEN;
    private String filter = IssuesService.FILTER_CREATED;
    private String sort = IssuesService.SORT_CREATED;
    private String direction = IssuesService.DIRECTION_DESC;

    private IssuesRecyclerViewAdapter mIssuesAdapter;

    @Override
    public void inflateMenu() {
        inflateMenu(R.menu.issue_toolbar_menu);
    }

    @Override
    public void createMenu(Menu menu) {
        mState = menu.findItem(R.id.issue_toolbar_state);
        mFilter = menu.findItem(R.id.issue_toolbar_filter);
        mSort = menu.findItem(R.id.issue_toolbar_sort);
        setOnMenuItemSelectedListener(new OnMenuItemSelectedListener() {
            @Override
            public void onItemSelected(MenuItem item) {
                item.setChecked(true);
                switch (item.getItemId()) {
                    case R.id.issue_toolbar_state:
                        if (state.equals(IssuesService.STATE_OPEN)) {
                            state = IssuesService.STATE_CLOSED;
                            mState.setIcon(R.drawable.ic_check);
                        } else if (state.equals(IssuesService.STATE_CLOSED)) {
                            state = IssuesService.STATE_CLOSED;
                            mState.setIcon(R.drawable.ic_info_outline);
                        }
                        break;
                    case R.id.issue_filter_created:
                        filter = IssuesService.FILTER_CREATED;
                        break;
                    case R.id.issue_filter_assigned:
                        filter = IssuesService.FILTER_ASSIGNED;
                        break;
                    case R.id.issue_filter_mentioned:
                        filter = IssuesService.FILTER_MENTIONED;
                        break;
                    case R.id.issue_sort_newest:
                        sort = IssuesService.SORT_CREATED;
                        direction = IssuesService.DIRECTION_DESC;
                        break;
                    case R.id.issue_sort_oldest:
                        sort = IssuesService.SORT_CREATED;
                        direction = IssuesService.DIRECTION_ASC;
                        break;
                    case R.id.issue_sort_most_commented:
                        sort = IssuesService.SORT_COMMENTS;
                        direction = IssuesService.DIRECTION_DESC;
                        break;
                    case R.id.issue_sort_least_commented:
                        sort = IssuesService.SORT_COMMENTS;
                        direction = IssuesService.DIRECTION_ASC;
                        break;
                    case R.id.issue_sort_recently_updated:
                        sort = IssuesService.SORT_UPDATED;
                        direction = IssuesService.DIRECTION_DESC;
                        break;
                    case R.id.issue_sort_least_recently_updated:
                        sort = IssuesService.SORT_UPDATED;
                        direction = IssuesService.DIRECTION_ASC;
                        break;
                    default:
                        break;
                }
            }
        });
    }

    @Override
    protected void initViews(View view) {
        mRefreshView = (RefreshView) view.findViewById(R.id.issue_RefreshView);
        mSRLayout = (SwipeRefreshLayout) view.findViewById(R.id.issue_SRLayout);
        mIssuesRV = (RecyclerView) view.findViewById(R.id.issue_RV);

        setupSwipeRefreshLayout();
        setupRecyclerView();
    }

    @Override
    protected View setContentView(LayoutInflater inflater, @Nullable ViewGroup container) {
        return inflater.inflate(R.layout.fragment_issue, container, false);
    }

    private void setupSwipeRefreshLayout() {
        mSRLayout.measure(View.MEASURED_SIZE_MASK, View.MEASURED_HEIGHT_STATE_SHIFT);
        mSRLayout.setColorSchemeResources(R.color.colorAccent);
        mSRLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

            }
        });
    }

    private void setupRecyclerView() {
        mIssuesAdapter = new IssuesRecyclerViewAdapter(getContext());
        mIssuesRV.setLayoutManager(new LinearLayoutManager(getContext()));
        mIssuesRV.setAdapter(mIssuesAdapter);
    }

    private void getIssues() {

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
    public void getIssueSuccess() {

    }

    @Override
    public void getIssueFail() {

    }

    @Override
    public void setPresenter(IssuesContract.Presenter presenter) {

    }
}
