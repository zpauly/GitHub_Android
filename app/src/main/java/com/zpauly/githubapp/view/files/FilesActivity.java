package com.zpauly.githubapp.view.files;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.AppBarLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.Spanned;
import android.util.Log;
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
import com.zpauly.githubapp.utils.HtmlImageGetter;
import com.zpauly.githubapp.view.ToolbarActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zpauly on 16-8-1.
 */

public class FilesActivity extends ToolbarActivity implements FilesContract.View {
    private final String TAG = getClass().getName();

    private FilesContract.Presenter mPresenter;

    public static final String REPO = "REPO";
    public static final String OWNER = "OWNER";
    public static final String BRANCH = "BRANCH";
    public static final String URL = "URL";

    private AppBarLayout mABLayout;
    private SwipeRefreshLayout mSRLayout;
    private RecyclerView mPathRV;
    private RecyclerView mContentRV;
    private NestedScrollView mFileContentLayout;
    private AppCompatTextView mFileContentTV;

    private String repo;
    private String owner;
    private String branch;
    private String url;
    private String path;

    private FileDirRecyclerViewAdapter mDirAdapter;
    private PathRecyclerViewAdapter mPathAdapter;

    private List<FileDirModel> list;
    private String fileContent;

    private boolean isFileLoading = false;

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
        mFileContentLayout = (NestedScrollView) findViewById(R.id.files_file_content_layout);
        mFileContentTV = (AppCompatTextView) findViewById(R.id.files_file_content_TV);

        setupRecyclerView();
        setupSwipeRefreshLayout();

        mSRLayout.setRefreshing(true);
        getContents();
    }

    private void setupSwipeRefreshLayout() {
        mSRLayout.setColorSchemeResources(R.color.colorAccent);
        mSRLayout.measure(View.MEASURED_SIZE_MASK, View.MEASURED_HEIGHT_STATE_SHIFT);
        mSRLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (isFileLoading) {
                    getContents();
                } else {
                    loadFile();
                }
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
        mPathAdapter.setOnItemClickListener(new OnDirItemClickListener() {
            @Override
            public void onClick(View v, String p, String type) {
                if (p.equals(path)) {
                    return;
                }
                isFileLoading = false;
                mFileContentLayout.setVisibility(View.GONE);
                path = p;
                mSRLayout.setRefreshing(true);
                getContents();
            }
        });
        mDirAdapter.setOnItemClickListener(new OnDirItemClickListener() {
            @Override
            public void onClick(View v, String p, String type) {
                String[] strs = p.split("/");
                if (p.equals("root system/" + path + "/" + strs[strs.length - 1])) {
                    return;
                }
                path = p;
                Log.i(TAG, p);
                if (type != null ) {
                    mSRLayout.setRefreshing(true);
                    if (type.equals("dir")) {
                        isFileLoading = false;
                        getContents();
                    } else if (type.equals("file")) {
                        isFileLoading = true;
                        loadFile();
                        String[] s = p.split("/");
                        List<String> paths = new ArrayList<>();
                        for (int i = 0; i < s.length; i ++) {
                            paths.add(s[i]);
                        }
                        mPathAdapter.swapData(paths);
                        mContentRV.setVisibility(View.GONE);
                        mFileContentTV.setText("");
                        mFileContentLayout.setVisibility(View.VISIBLE);
                    } else {
                        isFileLoading = false;
                        mSRLayout.setRefreshing(false);
                    }
                }
            }
        });
    }

    private void getAttrs() {
        repo = getIntent().getStringExtra(REPO);
        owner = getIntent().getStringExtra(OWNER);
        branch = getIntent().getStringExtra(BRANCH);
        url = getIntent().getStringExtra(URL);
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
            mContentRV.setVisibility(View.VISIBLE);
            mPathAdapter.swapData(paths);
            mDirAdapter.swapData(list);
            mSRLayout.setRefreshing(false);
        }
    }

    private void getContents() {
        mPresenter.getContentFromCache("root system/" + path);
    }

    private void loadFile() {
        mPresenter.loadFile(owner, repo, path);
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

    public static void launchActivity(Context context, String owner, String repo, String branch, String url) {
        Intent intent = new Intent();
        intent.putExtra(REPO, repo);
        intent.putExtra(OWNER, owner);
        intent.putExtra(BRANCH, branch);
        intent.putExtra(URL, url);
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
        getContents();
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

    @Override
    public void loadFileSuccess() {
        HtmlImageGetter imageGetter = new HtmlImageGetter(mFileContentTV, this,
                url + "/raw/" + branch);
        Spanned htmlSpann = Html.fromHtml(fileContent, imageGetter, null);
        mFileContentTV.setText(htmlSpann);
        mSRLayout.setRefreshing(false);
        Log.i(TAG, fileContent);
    }

    @Override
    public void loadFileFail() {
        mSRLayout.setRefreshing(false);
    }

    @Override
    public void loadingFile(String file) {
        fileContent = file;
    }
}
