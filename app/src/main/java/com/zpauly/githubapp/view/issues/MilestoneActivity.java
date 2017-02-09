package com.zpauly.githubapp.view.issues;

import android.content.Context;
import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.zpauly.githubapp.R;
import com.zpauly.githubapp.adapter.MilestonesRecyclerViewAdapter;
import com.zpauly.githubapp.entity.response.MilestoneBean;
import com.zpauly.githubapp.listener.OnMenuItemSelectedListener;
import com.zpauly.githubapp.network.issues.IssuesService;
import com.zpauly.githubapp.presenter.issues.MilestoneContract;
import com.zpauly.githubapp.presenter.issues.MilestonePresenter;
import com.zpauly.githubapp.widget.FloatingActionButton;
import com.zpauly.githubapp.widget.RefreshView;
import com.zpauly.githubapp.utils.viewmanager.LoadMoreInSwipeRefreshLayoutMoreManager;
import com.zpauly.githubapp.utils.viewmanager.RefreshViewManager;
import com.zpauly.githubapp.view.ToolbarActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by zpauly on 2016/10/11.
 */

public class MilestoneActivity extends ToolbarActivity implements MilestoneContract.View {
    private final String TAG = getClass().getName();

    public static final String REPO = "REPO";
    public static final String OWNER = "OWNER";

    private MilestoneContract.Presenter mPresenter;

    @BindView(R.id.milestone_RefreshView) public RefreshView mRefreshView;
    @BindView(R.id.milestone_SRLayout) public SwipeRefreshLayout mSRLayout;
    @BindView(R.id.milestone_RV) public RecyclerView mMilestoneRV;
    @BindView(R.id.milestone_FAB) public FloatingActionButton mMilestoneFAB;

    private MilestonesRecyclerViewAdapter mMilestoneAdapter;

    private LoadMoreInSwipeRefreshLayoutMoreManager loadMoreInSwipeRefreshLayoutMoreManager;
    private RefreshViewManager refreshViewManager;

    private MenuItem mStateItem;

    private String owner;
    private String repo;

    private String state = IssuesService.STATE_OPEN;
    private String sort;
    private String direction;

    private List<MilestoneBean> milestoneList = new ArrayList<>();

    @Override
    protected void onStop() {
        mPresenter.stop();
        super.onStop();
    }

    @Override
    public void initViews() {
        new MilestonePresenter(this, this);

        setupSwipeRefreshLayout();
        setupRecyclerView();

        setViewManager(new LoadMoreInSwipeRefreshLayoutMoreManager(mMilestoneRV, mSRLayout) {
            @Override
            public void load() {
                getMilestones();
            }
        }, new RefreshViewManager(mRefreshView, mSRLayout) {
            @Override
            public void load() {
                getMilestones();
            }
        });

        loadMoreInSwipeRefreshLayoutMoreManager = getViewManager(LoadMoreInSwipeRefreshLayoutMoreManager.class);
        refreshViewManager = getViewManager(RefreshViewManager.class);
    }

    @Override
    protected void setToolbar() {
        super.setToolbar();
        setToolbarTitle(R.string.milestone);
        setOnToolbarNavClickedListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    @Override
    public void initContent() {
        super.initContent();
        setContent(R.layout.content_milestone);
    }

    @Override
    protected void getParams() {
        repo = getIntent().getStringExtra(REPO);
        owner = getIntent().getStringExtra(OWNER);
    }

    public static void launchActivity(Context context, String repo, String owner) {
        Intent intent = new Intent();
        intent.putExtra(REPO, repo);
        intent.putExtra(OWNER, owner);
        intent.setClass(context, MilestoneActivity.class);
        context.startActivity(intent);
    }

    private void setupSwipeRefreshLayout() {
        mSRLayout.measure(View.MEASURED_SIZE_MASK, View.MEASURED_HEIGHT_STATE_SHIFT);
        mSRLayout.setColorSchemeResources(R.color.colorAccent);
        mSRLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.setPageId(1);
                loadMoreInSwipeRefreshLayoutMoreManager.setSwipeRefreshLayoutRefreshing(mMilestoneAdapter);
            }
        });
    }

    private void setupRecyclerView() {
        mMilestoneAdapter = new MilestonesRecyclerViewAdapter(this);
        mMilestoneRV.setLayoutManager(new LinearLayoutManager(this));
        mMilestoneRV.setAdapter(mMilestoneAdapter);
    }

    private void getMilestones() {
        mPresenter.getMilestones();
    }

    @Override
    public void getMilestonesSuccess() {
        mSRLayout.setRefreshing(false);
        mMilestoneAdapter.swapAllData(milestoneList);
        if (!mRefreshView.isRefreshSuccess()) {
            mRefreshView.refreshSuccess();
        }
    }

    @Override
    public void getMilestoneFail() {
        mSRLayout.setRefreshing(false);
        if (!mRefreshView.isRefreshSuccess()) {
            mRefreshView.refreshFail();
        }
    }

    @Override
    public void gettingMilestones(List<MilestoneBean> milestoneBeen) {
        loadMoreInSwipeRefreshLayoutMoreManager.hasNoMoreData(milestoneBeen, mMilestoneAdapter);
        milestoneList.addAll(milestoneBeen);
    }

    @Override
    public String getRepo() {
        return repo;
    }

    @Override
    public String getOwner() {
        return owner;
    }

    @Override
    public String getState() {
        return state;
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
    public void setPresenter(MilestoneContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void inflateMenu(MenuInflater inflater, Menu menu) {
        inflater.inflate(R.menu.milestone_toolbar_menu, menu);
    }

    @Override
    public void createMenu(Menu menu) {
        mStateItem = menu.findItem(R.id.milestone_toolbar_state);
        setOnMenuItemSelectedListener(new OnMenuItemSelectedListener() {
            @Override
            public void onItemSelected(MenuItem item) {
                item.setChecked(true);
                switch (item.getItemId()) {
                    case R.id.milestone_toolbar_state:
                        mPresenter.setPageId(1);
                        milestoneList.clear();
                        if (state.equals(IssuesService.STATE_OPEN)) {
                            state = IssuesService.STATE_CLOSED;
                            mStateItem.setIcon(R.drawable.ic_check);
                        } else if (state.equals(IssuesService.STATE_CLOSED)) {
                            state = IssuesService.STATE_OPEN;
                            mStateItem.setIcon(R.drawable.ic_info_outline);
                        }
                        break;
                    case R.id.milestone_sort_recently_updated:
                        sort = null;
                        direction = null;
                        break;
                    case R.id.milestone_sort_furthest_due_date:
                        sort = IssuesService.DUE_ON;
                        direction = IssuesService.DIRECTION_DESC;
                        break;
                    case R.id.milestone_sort_closest_due_date:
                        sort = IssuesService.DUE_ON;
                        direction = IssuesService.DIRECTION_ASC;
                        break;
                    case R.id.milestone_sort_least_complete:
                        sort = IssuesService.COMPLETENESS;
                        direction = IssuesService.DIRECTION_DESC;
                        break;
                    case R.id.milestone_sort_most_complete:
                        sort = IssuesService.COMPLETENESS;
                        direction = IssuesService.DIRECTION_ASC;
                        break;
                    default:
                        break;
                }
                mSRLayout.setRefreshing(true);
                getMilestones();
            }
        });
    }
}
