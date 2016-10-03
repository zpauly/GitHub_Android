package com.zpauly.githubapp.view.files;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.NestedScrollView;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.LinearLayout;

import com.protectsoft.webviewcode.Codeview;
import com.protectsoft.webviewcode.Settings;
import com.zpauly.githubapp.R;
import com.zpauly.githubapp.adapter.FileDirRecyclerViewAdapter;
import com.zpauly.githubapp.adapter.PathRecyclerViewAdapter;
import com.zpauly.githubapp.base.BaseFragment;
import com.zpauly.githubapp.db.FileDirDao;
import com.zpauly.githubapp.db.FileDirModel;
import com.zpauly.githubapp.entity.response.repos.RepositoryContentBean;
import com.zpauly.githubapp.listener.OnDirItemClickListener;
import com.zpauly.githubapp.presenter.files.FilesContract;
import com.zpauly.githubapp.presenter.files.FilesPresenter;
import com.zpauly.githubapp.ui.DividerItemDecoration;
import com.zpauly.githubapp.utils.HtmlImageGetter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zpauly on 16-8-5.
 */

public class FilesFragment extends BaseFragment implements FilesContract.View {
    private final String TAG = getClass().getName();

    private FilesContract.Presenter mPresenter;

    private String repo;
    private String owner;
    private String branch;
    private String url;
    private String path;

    private String sha;

    private FileDirRecyclerViewAdapter mDirAdapter;
    private PathRecyclerViewAdapter mPathAdapter;

    private List<FileDirModel> list;
    private String fileContent;
    private boolean isFileLoading = false;

    private SwipeRefreshLayout mSRLayout;
    private RecyclerView mPathRV;
    private RecyclerView mContentRV;
    private WebView mCodeWB;
    private LinearLayout mContentLayout;
    private NestedScrollView mFileLayout;
    private AppCompatTextView mFileTV;

    @Override
    public void onStop() {
        mPresenter.stop();
        super.onStop();
    }

    @Override
    protected void initViews(View view) {
        new FilesPresenter(getContext().getApplicationContext(), this);

        getAttrs();

        mSRLayout = (SwipeRefreshLayout) view.findViewById(R.id.files_SRLayout);
        mPathRV = (RecyclerView) view.findViewById(R.id.files_path_RV);
        mContentRV = (RecyclerView) view.findViewById(R.id.files_content_RV);
        mCodeWB = (WebView) view.findViewById(R.id.files_code_WB);
        mContentLayout = (LinearLayout) view.findViewById(R.id.files_file_content_layout);
        mFileLayout = (NestedScrollView) view.findViewById(R.id.files_file_NSV);
        mFileTV = (AppCompatTextView) view.findViewById(R.id.files_file_TV);

        setupRecyclerView();
        setupSwipeRefreshLayout();

        mSRLayout.setRefreshing(true);
        getContents();
    }

    @Override
    protected View setContentView(LayoutInflater inflater, @Nullable ViewGroup container) {
        return inflater.inflate(R.layout.fragment_files, container, false);
    }

    private void getAttrs() {
        Bundle bundle = getArguments();
        repo = bundle.getString(FilesActivity.REPO);
        owner = bundle.getString(FilesActivity.OWNER);
        branch = bundle.getString(FilesActivity.BRANCH);
        url = bundle.getString(FilesActivity.URL);
        path = "";
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
        mDirAdapter = new FileDirRecyclerViewAdapter(getActivity());
        mPathAdapter = new PathRecyclerViewAdapter(getActivity());

        mPathRV.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        mPathRV.setAdapter(mPathAdapter);

        mContentRV.setLayoutManager(new LinearLayoutManager(getActivity()));
        mContentRV.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST));
        mContentRV.setAdapter(mDirAdapter);
        mPathAdapter.setOnItemClickListener(new OnDirItemClickListener() {
            @Override
            public void onClick(View v, String p, String type, String sha) {
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
            public void onClick(View v, String p, String type, String sha) {
                FilesFragment.this.sha = sha;
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
                        mPathAdapter.swapAllData(paths);
                        mContentRV.setVisibility(View.GONE);
                        Codeview.with(getContext())
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
            mPathAdapter.swapAllData(paths);
            mDirAdapter.swapAllData(list);
            mSRLayout.setRefreshing(false);
            mSRLayout.setEnabled(false);
        }
    }

    private void getContents() {
        mPresenter.getContentFromCache("root system/" + path);
    }

    private void loadFile() {
        mPresenter.loadFile(owner, repo, path, sha);
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
        } else if (path.endsWith(".xml")) {
            fileContent = fileContent.replaceAll("<", "&lt;");
            fileContent = fileContent.replaceAll(">", "&gt;");
            fileContent = "<div class=\"plain\"><pre>" + fileContent + "</pre></div>";
        } else {
            language = Settings.MimeType.TEXT_HTML;
        }
        setCode(language);
    }

    private void setImageOrFile(boolean isImage) {
        if (isImage) {
            mCodeWB.setVisibility(View.VISIBLE);
            mFileLayout.setVisibility(View.GONE);
            mCodeWB.loadDataWithBaseURL(null,
                    "<html><head></head><body><table style=\"width:100%; height:100%;\"><tr><td style=\"vertical-align:middle;\"><img src=\""
                            + url + "/raw/" + branch + "/" + path + "\"></td></tr></table></body></html>",
                    "html/css",
                    "utf-8",
                    null);
        } else {
            mCodeWB.setVisibility(View.GONE);
            mFileLayout.setVisibility(View.VISIBLE);
            HtmlImageGetter imageGetter = new HtmlImageGetter(mFileTV, getContext(),
                    url + "/raw/" + branch);
            Spanned htmlSpann = Html.fromHtml(fileContent, imageGetter, null);
            mFileTV.setText(htmlSpann);
            mFileTV.setMovementMethod(LinkMovementMethod.getInstance());
        }
    }

    private void setCode(String lang) {
        mCodeWB.setVisibility(View.VISIBLE);
        mFileLayout.setVisibility(View.GONE);
        Log.i(TAG, fileContent);
        Codeview.with(getContext().getApplicationContext())
                .withCode(fileContent)
                .setStyle(Settings.WithStyle.GITHUB)
                .setLang(lang)
                .into(mCodeWB);
    }
}
