package com.zpauly.githubapp.view.repositories;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.zpauly.githubapp.Constants;
import com.zpauly.githubapp.R;
import com.zpauly.githubapp.adapter.BranchesRecyclerViewAdapter;
import com.zpauly.githubapp.entity.response.repos.BranchBean;
import com.zpauly.githubapp.network.repositories.RepositoriesMethod;
import com.zpauly.githubapp.ui.RefreshView;
import com.zpauly.githubapp.utils.SPUtil;
import com.zpauly.githubapp.utils.viewmanager.LoadMoreInSwipeRefreshLayoutMoreManager;
import com.zpauly.githubapp.utils.viewmanager.RefreshViewManager;
import com.zpauly.githubapp.view.ToolbarActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import rx.Subscriber;

/**
 * Created by zpauly on 2016/10/29.
 */

public class BranchesActivity extends ToolbarActivity {
    private final String TAG = getClass().getName();

    public static final String REPO = "REPO";
    public static final String OWNER = "OWNER";
    public static final String DEFAULT_BRANCH = "DEFAULT_BRANCH";

    @BindView(R.id.RefreshView) public RefreshView mRefreshView;
    @BindView(R.id.SRLayout) public SwipeRefreshLayout mSRLayout;
    @BindView(R.id.branch_RV) public RecyclerView mBranchRV;

    private BranchesRecyclerViewAdapter mBranchAdapter;

    private String auth;
    private String defaultBranch;
    private String repo;
    private String owner;

    private Subscriber<List<BranchBean>> branchesSubscriber;
    private RepositoriesMethod repositoriesMethod;

    private RefreshViewManager refreshViewManager;

    private List<BranchBean> branchList = new ArrayList<>();

    @Override
    protected void onStop() {
        if (branchesSubscriber != null) {
            branchesSubscriber.unsubscribe();
        }
        super.onStop();
    }

    @Override
    public void initViews() {
        repositoriesMethod = RepositoriesMethod.getInstance();

        setupSwipeRefreshLayout();
        setupRecyclerView();

        refreshViewManager = new RefreshViewManager(mRefreshView, mSRLayout) {
            @Override
            public void load() {
                getBranches();
            }
        };

        branchesSubscriber = new Subscriber<List<BranchBean>>() {
            @Override
            public void onCompleted() {
                mBranchAdapter.swapAllData(branchList);
                mSRLayout.setRefreshing(false);
                if (!mRefreshView.isRefreshSuccess()) {
                    mRefreshView.refreshSuccess();
                }
            }

            @Override
            public void onError(Throwable e) {
                mSRLayout.setRefreshing(false);
                e.printStackTrace();
                if (!mRefreshView.isRefreshSuccess()) {
                    mRefreshView.refreshFail();
                } else {
                    Snackbar.make(mRefreshView, R.string.error_occurred, Snackbar.LENGTH_SHORT);
                }
            }

            @Override
            public void onNext(List<BranchBean> branchBeen) {
                branchList.addAll(branchBeen);
            }
        };

        setViewManager(refreshViewManager);
    }

    @Override
    protected void setToolbar() {
        super.setToolbar();
        setToolbarTitle(R.string.branches);
        setOnToolbarNavClickedListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void getBranches() {
        repositoriesMethod.getBranches(branchesSubscriber, auth, repo, owner);
    }

    private void setupSwipeRefreshLayout() {
        mSRLayout.measure(View.MEASURED_SIZE_MASK, View.MEASURED_HEIGHT_STATE_SHIFT);
        mSRLayout.setColorSchemeResources(R.color.colorAccent);
        mSRLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getBranches();
            }
        });
    }

    private void setupRecyclerView() {
        mBranchAdapter = new BranchesRecyclerViewAdapter(this, defaultBranch);
        mBranchRV.setLayoutManager(new LinearLayoutManager(this));
        mBranchRV.setAdapter(mBranchAdapter);
    }

    @Override
    public void initContent() {
        super.initContent();
        setContent(R.layout.content_branches);
    }

    @Override
    protected void getParams() {
        defaultBranch = getIntent().getStringExtra(DEFAULT_BRANCH);
        repo = getIntent().getStringExtra(REPO);
        owner = getIntent().getStringExtra(OWNER);
        auth = SPUtil.getString(this, Constants.USER_INFO, Constants.USER_AUTH, null);
        Log.i(TAG, "default branch = " + defaultBranch);
    }

    public static void launchActivity(Context context, String repo, String owner, String defaultBranch) {
        Intent intent = new Intent();
        intent.putExtra(REPO, repo);
        intent.putExtra(OWNER, owner);
        intent.putExtra(DEFAULT_BRANCH, defaultBranch);
        intent.setClass(context, BranchesActivity.class);
        context.startActivity(intent);
    }
}
