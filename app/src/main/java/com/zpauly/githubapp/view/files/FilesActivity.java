package com.zpauly.githubapp.view.files;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.design.widget.AppBarLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.ListPopupWindow;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.zpauly.githubapp.R;
import com.zpauly.githubapp.adapter.FileDirRecyclerViewAdapter;
import com.zpauly.githubapp.adapter.PathRecyclerViewAdapter;
import com.zpauly.githubapp.db.FileDirDao;
import com.zpauly.githubapp.db.FileDirModel;
import com.zpauly.githubapp.entity.response.RepositoryContentBean;
import com.zpauly.githubapp.listener.OnDirItemClickListener;
import com.zpauly.githubapp.presenter.files.FilesContract;
import com.zpauly.githubapp.presenter.files.FilesPresenter;
import com.zpauly.githubapp.ui.DividerItemDecoration;
import com.zpauly.githubapp.view.ToolbarActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by zpauly on 16-8-1.
 */

public class FilesActivity extends ToolbarActivity implements FilesContract.View {
    private final String TAG = getClass().getName();

    private FilesContract.Presenter mPresenter;

    public static final String REPO = "REPO";
    public static final String OWNER = "OWNER";

    private AppBarLayout mABLayout;
    private SwipeRefreshLayout mSRLayout;
    private RecyclerView mPathRV;
    private RecyclerView mContentRV;

    private String repo;
    private String owner;
    private String path;

    private FileDirRecyclerViewAdapter mDirAdapter;
    private PathRecyclerViewAdapter mPathAdapter;

    private List<FileDirModel> list;

    @Override
    protected void onStop() {
        mPresenter.stop();
        super.onStop();
    }

    @Override
    public void initViews() {
        new FilesPresenter(this, this);

        getAttrs();

        mABLayout = (AppBarLayout) findViewById(R.id.files_ABLayout);
        mSRLayout = (SwipeRefreshLayout) findViewById(R.id.files_SRLayout);
        mPathRV = (RecyclerView) findViewById(R.id.files_path_RV);
        mContentRV = (RecyclerView) findViewById(R.id.files_content_RV);

        setupRecyclerView();
        setupSwipeRefreshLayout();

        mSRLayout.setRefreshing(true);
        getContents(path);
    }

    private void setupSwipeRefreshLayout() {
        mSRLayout.setColorSchemeResources(R.color.colorAccent);
        mSRLayout.measure(View.MEASURED_SIZE_MASK, View.MEASURED_HEIGHT_STATE_SHIFT);
        mSRLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getContents(path);
            }
        });
    }

    private void setupRecyclerView() {
        mDirAdapter = new FileDirRecyclerViewAdapter(this);
        mPathAdapter = new PathRecyclerViewAdapter(this);

        mPathRV.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        mPathRV.setAdapter(mPathAdapter);

        mContentRV.setLayoutManager(new LinearLayoutManager(this));
        mContentRV.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
        mContentRV.setAdapter(mDirAdapter);
        mDirAdapter.setOnItemClickListener(new OnDirItemClickListener() {
            @Override
            public void onClick(View v, String p) {
                path = p;
                mSRLayout.setRefreshing(true);
                getContents(path);
            }
        });
    }

    private void getAttrs() {
        repo = getIntent().getStringExtra(REPO);
        owner = getIntent().getStringExtra(OWNER);
        path = "";
    }

    private void loadContents() {
        if (list == null || list.size() == 0) {
            mPresenter.loadContent(owner, repo, path);
        } else {
            String[] strs = list.get(0).getPath().split("/");
            List<String> paths = new ArrayList<>();
            for (int i = 0; i < strs.length - 1; i ++) {
                paths.add(strs[i]);
            }
            mPathAdapter.swapData(paths);
            mDirAdapter.swapData(list);
            mSRLayout.setRefreshing(false);
        }
    }

    private void getContents(String path) {
        mPresenter.getContentFromCache("root system/" + path);
    }

    @Override
    protected void setToolbar() {
        super.setToolbar();
        setToolbarTitle(repo);
        setOnToolbarNavClickedListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    @Override
    public void initContent() {
        setContentView(R.layout.activity_files);
    }

    public static void launchActivity(Context context, String owner, String repo) {
        Intent intent = new Intent();
        intent.putExtra(REPO, repo);
        intent.putExtra(OWNER, owner);
        intent.setClass(context, FilesActivity.class);
        context.startActivity(intent);
//        ((Activity) context).finish();
    }

    @Override
    public void setPresenter(FilesContract.Presenter presenter) {
        this.mPresenter = presenter;
    }

    @Override
    public void loadContentSuccess() {
        getContents(path);
    }

    @Override
    public void loadContentFail() {
        mSRLayout.setRefreshing(false);
    }

    @Override
    public void loadingContent(List<RepositoryContentBean> beanList) {
        for (RepositoryContentBean bean : beanList) {
            FileDirDao.insert(bean, path);
        }
    }

    @Override
    public void getContentSuccess() {
        loadContents();
    }

    @Override
    public void getContentFail() {
        mSRLayout.setRefreshing(false);
    }

    @Override
    public void gettingContent(List<FileDirModel> list) {
        this.list = list;
    }
}
