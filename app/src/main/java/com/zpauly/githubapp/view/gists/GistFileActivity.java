package com.zpauly.githubapp.view.gists;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.webkit.WebView;

import com.protectsoft.webviewcode.Settings;
import com.zpauly.githubapp.Constants;
import com.zpauly.githubapp.R;
import com.zpauly.githubapp.entity.response.gists.GistFileBean;
import com.zpauly.githubapp.entity.response.gists.GistFileMapBean;
import com.zpauly.githubapp.entity.response.gists.GistsBean;
import com.zpauly.githubapp.network.gists.GistsMethod;
import com.zpauly.githubapp.widget.RefreshView;
import com.zpauly.githubapp.utils.DisplayUtil;
import com.zpauly.githubapp.utils.SPUtil;
import com.zpauly.githubapp.view.ToolbarActivity;

import butterknife.BindView;
import rx.Subscriber;

/**
 * Created by zpauly on 16-8-7.
 */

public class GistFileActivity extends ToolbarActivity {
    private final String TAG = getClass().getName();

    public static final String FILE_ID = "FILE_ID";
    public static final String FILE_NAME = "FILE_NAME";

    @BindView(R.id.gist_file_WB) public WebView mWB;
    @BindView(R.id.gist_file_RefreshView) public RefreshView mRefreshView;

    private Subscriber<GistsBean> gistFileSubscriber;

    private String auth;
    private GistsMethod method;

    private String fileName;
    private String id;

    private String content;

    @Override
    protected void onStop() {
        if (gistFileSubscriber != null) {
            if (gistFileSubscriber.isUnsubscribed()) {
                gistFileSubscriber.unsubscribe();
            }
        }
        super.onStop();
    }

    @Override
    public void initViews() {
        auth = SPUtil.getString(this, Constants.USER_INFO, Constants.USER_AUTH, null);
        method = GistsMethod.getInstance();

        mRefreshView.setOnRefreshStateListener(new RefreshView.OnRefreshStateListener() {
            @Override
            public void beforeRefreshing() {
                loadFile();
            }

            @Override
            public void onRefreshSuccess() {
                mRefreshView.setVisibility(View.GONE);
            }

            @Override
            public void onRefreshFail() {
                mRefreshView.setVisibility(View.VISIBLE);
            }
        });
        mRefreshView.startRefresh();
    }

    @Override
    protected void getParams() {
        fileName = getIntent().getStringExtra(FILE_NAME);
        id = getIntent().getStringExtra(FILE_ID);
    }

    @Override
    public void initContent() {
        super.initContent();
        setContent(R.layout.content_gist_file);
    }

    @Override
    protected void setToolbar() {
        super.setToolbar();
        setToolbarTitle(fileName);
        setOnToolbarNavClickedListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    public static void launchActivity(Context context, String fileName, String id) {
        Intent intent = new Intent();
        intent.putExtra(FILE_NAME, fileName);
        intent.putExtra(FILE_ID, id);
        intent.setClass(context, GistFileActivity.class);
        context.startActivity(intent);
//        ((Activity) context).finish();
    }

    private void loadFile() {
        gistFileSubscriber = new Subscriber<GistsBean>() {
            @Override
            public void onCompleted() {
                setContent();
                if (!mRefreshView.isRefreshSuccess()) {
                    mRefreshView.refreshSuccess();
                }
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
                mRefreshView.refreshFail();
            }

            @Override
            public void onNext(GistsBean gistsBean) {
                GistFileMapBean mapBean = gistsBean.getFiles();
                GistFileBean file = mapBean.get(fileName);
                if (file != null) {
                    content = file.getContent();
                }
            }
        };
        method.getASingleGIst(gistFileSubscriber, auth, id);
    }

    private void setContent() {
        String lang;
        if (fileName.endsWith(".java")) {
            lang = Settings.Lang.JAVA;
        } else if (fileName.endsWith(".java")) {
            lang = Settings.Lang.JAVA;
        } else if (fileName.endsWith(".php")) {
            lang = Settings.Lang.PHP;
        } else if (fileName.endsWith(".py")) {
            lang = Settings.Lang.PYTHON;
        } else if (fileName.endsWith(".c") || fileName.endsWith(".cpp") || fileName.endsWith("h")) {
            lang = Settings.Lang.CPLUSPLUS;
        } else if (fileName.endsWith(".cs")) {
            lang = Settings.Lang.CSHARP;
        } else if (fileName.endsWith(".rb")) {
            lang = Settings.Lang.RUBY;
        } else if (fileName.endsWith(".js")) {
            lang = Settings.Lang.JAVASCRIPT;
        } else if (fileName.endsWith(".xml") || fileName.endsWith(".html")) {
            lang = Settings.MimeType.TEXT_HTML;
        } else {
            lang = Settings.MimeType.TEXT_PLAIN;
        }

        DisplayUtil.showCode(mWB, this, content, lang);
    }
}
