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
import android.webkit.WebView;
import android.widget.LinearLayout;

import com.protectsoft.webviewcode.Codeview;
import com.protectsoft.webviewcode.Settings;
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
    private WebView mCodeWB;
    private LinearLayout mContentLayout;
    private NestedScrollView mFileLayout;
    private AppCompatTextView mFileTV;

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
        mCodeWB = (WebView) findViewById(R.id.files_code_WB);
        mContentLayout = (LinearLayout) findViewById(R.id.files_file_content_layout);
        mFileLayout = (NestedScrollView) findViewById(R.id.files_file_NSV);
        mFileTV = (AppCompatTextView) findViewById(R.id.files_file_TV);

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
                mSRLayout.setEnabled(true);
                isFileLoading = false;
                mContentLayout.setVisibility(View.GONE);
                path = p;
                mSRLayout.setRefreshing(true);
                getContents();
            }
        });
        mDirAdapter.setOnItemClickListener(new OnDirItemClickListener() {
            @Override
            public void onClick(View v, String p, String type) {
                mSRLayout.setEnabled(true);
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
                        Codeview.with(getApplicationContext())
                                .withCode("")
                                .setStyle(Settings.WithStyle.GITHUB)
                                .setLang(Settings.Lang.JAVA)
                                .into(mCodeWB);
                        mFileTV.setText("");
                    } else {
                        isFileLoading = false;
                        mSRLayout.setRefreshing(false);
                    }
                }
            }
        });
        mFileTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "textview clicked");
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
            mSRLayout.setEnabled(false);
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
        this.list = arrangeList(list);
    }

    @Override
    public void loadFileSuccess() {
        mSRLayout.setEnabled(false);
        mContentLayout.setVisibility(View.VISIBLE);
        setFileContent();
        mSRLayout.setRefreshing(false);
    }

    @Override
    public void loadFileFail() {
        mSRLayout.setRefreshing(false);
    }

    @Override
    public void loadingFile(String file) {
        fileContent = file + "\n" + "\n" + "\n" + "\n";
    }

    private List<FileDirModel> arrangeList(List<FileDirModel> list) {
        List<FileDirModel> dirs = new ArrayList<>();
        List<FileDirModel> files = new ArrayList<>();
        List<FileDirModel> symlinks = new ArrayList<>();
        for (FileDirModel model : list) {
            if (model.getType().equals("dir")) {
                dirs.add(model);
            } else if (model.getType().equals("file")) {
                files.add(model);
            } else if (model.getType().equals("symlinks")) {
                symlinks.add(model);
            }
        }
        List<FileDirModel> newList = new ArrayList<>();
        newList.addAll(dirs);
        newList.addAll(files);
        newList.addAll(symlinks);
        return newList;
    }

    private void setFileContent() {
        String language = null;
        if (path.endsWith(".java")) {
            language = Settings.Lang.JAVA;
        } else if (path.endsWith(".c") || path.endsWith(".cpp")) {
            language = Settings.Lang.CPLUSPLUS;
        } else if (path.endsWith(".cs")) {
            language = Settings.Lang.CSHARP;
        } else if (path.endsWith(".js")) {
            language = Settings.Lang.JAVASCRIPT;
        } else if (path.endsWith(".py")) {
            language = Settings.Lang.PYTHON;
        } else if (path.endsWith(".rb")) {
            language = Settings.Lang.RUBY;
        } else if (path.endsWith(".php")) {
            language = Settings.Lang.PHP;
        }  else if (path.endsWith(".jpg") || path.endsWith(".JPG")
                || path.endsWith(".GIF") || path.endsWith(".gif")
                || path.endsWith(".png") || path.endsWith(".PNG")){
            setImageOrFile(true);
            return;
        } else if (path.endsWith(".md")){
            setImageOrFile(false);
            return;
        } else {
            language = Settings.MimeType.TEXT_HTML;
        }
        setCode(language);
    }

    private void setImageOrFile(boolean isImage) {
        if (isImage) {
            mCodeWB.setVisibility(View.VISIBLE);
            mFileLayout.setVisibility(View.GONE);
            /*Log.i(TAG, "load image");
            if (path.endsWith(".jpg") || path.endsWith(".JPG") || path.endsWith("ipeg")) {
                mCodeWB.loadData("<image src=\"" + url + "/raw/" + branch + "/" + path + "\" width=\"100%\"/>",
                        "image/jpeg", null);
            } else if (path.endsWith(".GIF") || path.endsWith(".gif")) {
                mCodeWB.loadData("<image src=\"" + url + "/raw/" + branch + "/" + path + "\" width=\"100%\"/>",
                        "image/gif", null);
            } else if (path.endsWith(".png") || path.endsWith(".PNG")) {
                mCodeWB.loadData("<image src=\"" + url + "/raw/" + branch + "/" + path + "\" width=\"100%\"/>",
                        "image/png", null);
            }*/
            mCodeWB.loadUrl(url + "/raw/" + branch + "/" + path);
            /*String s = "<image src='" + url + "/raw/" + branch + "/" + path + "'/>";
            Log.i(TAG, s);
            HtmlImageGetter imageGetter = new HtmlImageGetter(mFileTV, this,
                    null);
            Spanned htmlSpann = Html.fromHtml(s, imageGetter, null);
            mFileTV.setText(htmlSpann);*/
        } else {
            mCodeWB.setVisibility(View.GONE);
            mFileLayout.setVisibility(View.VISIBLE);
            HtmlImageGetter imageGetter = new HtmlImageGetter(mFileTV, this,
                    url + "/raw/" + branch);
            Spanned htmlSpann = Html.fromHtml(fileContent, imageGetter, null);
            mFileTV.setText(htmlSpann);
        }
    }

    private void setCode(String lang) {
        mCodeWB.setVisibility(View.VISIBLE);
        mFileLayout.setVisibility(View.GONE);
        Codeview.with(getApplicationContext())
                .withCode(fileContent)
                .setStyle(Settings.WithStyle.GITHUB)
                .setLang(lang)
                .into(mCodeWB);
    }
}
