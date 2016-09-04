package com.zpauly.githubapp.view.issues;

import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.zpauly.githubapp.R;
import com.zpauly.githubapp.listener.OnMenuItemSelectedListener;
import com.zpauly.githubapp.network.issues.IssuesService;
import com.zpauly.githubapp.ui.RefreshView;
import com.zpauly.githubapp.view.ToolbarMenuFragment;

/**
 * Created by zpauly on 16/9/4.
 */
public class IssuesFragment extends ToolbarMenuFragment {
    private final String TAG = getClass().getName();

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
                        } else if (state.equals(IssuesService.STATE_CLOSED)) {
                            state = IssuesService.STATE_CLOSED;
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
    }

    @Override
    protected View setContentView(LayoutInflater inflater, @Nullable ViewGroup container) {
        return inflater.inflate(R.layout.fragment_issue, container, false);
    }
}
