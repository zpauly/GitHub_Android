package com.zpauly.githubapp.view.repositories;

import android.content.Context;
import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.zpauly.githubapp.Constants;
import com.zpauly.githubapp.R;
import com.zpauly.githubapp.adapter.ReposRecyclerViewAdapter;
import com.zpauly.githubapp.entity.response.repos.RepositoriesBean;
import com.zpauly.githubapp.listener.OnMenuItemSelectedListener;
import com.zpauly.githubapp.network.repositories.RepositoriesMethod;
import com.zpauly.githubapp.network.repositories.RepositoriesService;
import com.zpauly.githubapp.ui.RefreshView;
import com.zpauly.githubapp.utils.SPUtil;
import com.zpauly.githubapp.utils.viewmanager.LoadMoreInSwipeRefreshLayoutMoreManager;
import com.zpauly.githubapp.utils.viewmanager.RefreshViewManager;
import com.zpauly.githubapp.view.ToolbarActivity;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;

/**
 * Created by zpauly on 2016/10/29.
 */

public class ForksActivity extends ToolbarActivity {
    private final String TAG = getClass().getName();

    public static final String REPO = "REPO";
    public static final String OWNER = "OWNER";

    private String repo;
    private String owner;
    private String auth;

    private RepositoriesMethod repoMethod;

    private RefreshView mRefreshView;
    private SwipeRefreshLayout mSRLayout;
    private RecyclerView mForksRV;
    private ReposRecyclerViewAdapter mForksAdapter;

    private LoadMoreInSwipeRefreshLayoutMoreManager loadMoreInSwipeRefreshLayoutMoreManager;
    private RefreshViewManager refreshViewManager;

    private Subscriber<List<RepositoriesBean>> forksSubscriber;
    private List<RepositoriesBean> forksList = new ArrayList<>();

    private String sort = RepositoriesService.NEWEST;
    private int pageId = 1;

    @Override
    protected void onStop() {
        if (forksSubscriber != null) {
            forksSubscriber.unsubscribe();
        }
        super.onStop();
    }

    @Override
    public void initViews() {
        getParams();
        setContent(R.layout.content_forks);

        mRefreshView = (RefreshView) findViewById(R.id.RefreshView);
        mSRLayout = (SwipeRefreshLayout) findViewById(R.id.SRLayout);
        mForksRV = (RecyclerView) findViewById(R.id.forks_RV);

        setupRecyclerView();
        setupSwipeRefreshLayout();

        loadMoreInSwipeRefreshLayoutMoreManager = new LoadMoreInSwipeRefreshLayoutMoreManager(mForksRV, mSRLayout) {
            @Override
            public void load() {
                getForks();
            }
        };
        refreshViewManager = new RefreshViewManager(mRefreshView, mSRLayout) {
            @Override
            public void load() {
                getForks();
            }
        };
        setViewManager(loadMoreInSwipeRefreshLayoutMoreManager, refreshViewManager);
    }

    @Override
    protected void setToolbar() {
        super.setToolbar();
        setToolbarTitle(R.string.forks);
        setOnToolbarNavClickedListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void getParams() {
        repo = getIntent().getStringExtra(REPO);
        owner = getIntent().getStringExtra(OWNER);
        auth = SPUtil.getString(this, Constants.USER_INFO, Constants.USER_AUTH, null);
        repoMethod = RepositoriesMethod.getInstance();
    }

    private void setupSwipeRefreshLayout() {
        mSRLayout.measure(View.MEASURED_SIZE_MASK, View.MEASURED_HEIGHT_STATE_SHIFT);
        mSRLayout.setColorSchemeResources(R.color.colorAccent);
        mSRLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refresh();
            }
        });
    }

    private void refresh() {
        pageId = 1;
        loadMoreInSwipeRefreshLayoutMoreManager.setSwipeRefreshLayoutRefreshing(mForksAdapter);
    }

    private void getForks() {
        forksSubscriber = new Subscriber<List<RepositoriesBean>>() {
            @Override
            public void onCompleted() {
                pageId++;
                mSRLayout.setRefreshing(false);
                mForksAdapter.swapAllData(forksList);
                if (!mRefreshView.isRefreshSuccess()) {
                    mRefreshView.refreshSuccess();
                }
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
                if (!mRefreshView.isRefreshSuccess()) {
                    mRefreshView.refreshFail();
                }
            }

            @Override
            public void onNext(List<RepositoriesBean> repositoriesBeen) {
                loadMoreInSwipeRefreshLayoutMoreManager.hasNoMoreData(repositoriesBeen, mForksAdapter);
                if (mSRLayout.isRefreshing()) {
                    forksList.clear();
                }
                forksList.addAll(repositoriesBeen);
            }
        };
        repoMethod.getForks(forksSubscriber, auth, owner, repo, sort, pageId);
    }

    private void setupRecyclerView() {
        mForksAdapter = new ReposRecyclerViewAdapter(this);
        mForksRV.setLayoutManager(new LinearLayoutManager(this));
        mForksRV.setAdapter(mForksAdapter);
    }

    public static void launchActivity(Context context, String owner, String repo) {
        Intent intent = new Intent();
        intent.putExtra(OWNER, owner);
        intent.putExtra(REPO, repo);
        intent.setClass(context, ForksActivity.class);
        context.startActivity(intent);
    }

    @Override
    public void inflateMenu(MenuInflater inflater, Menu menu) {
        super.inflateMenu(inflater, menu);
        inflater.inflate(R.menu.fork_toolbar_menu, menu);
    }

    @Override
    public void createMenu(Menu menu) {
        super.createMenu(menu);
        setOnMenuItemSelectedListener(new OnMenuItemSelectedListener() {
            @Override
            public void onItemSelected(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.fork_menu_sort_newest:
                        sort = RepositoriesService.NEWEST;
                        mSRLayout.setRefreshing(true);
                        refresh();
                        break;
                    case R.id.fork_menu_sort_oldest:
                        sort = RepositoriesService.OLDEST;
                        mSRLayout.setRefreshing(true);
                        refresh();
                        break;
                    case R.id.fork_menu_sort_stargazers:
                        sort = RepositoriesService.STARGAZERS;
                        mSRLayout.setRefreshing(true);
                        refresh();
                        break;
                    default:
                        break;
                }
            }
        });
    }
}
