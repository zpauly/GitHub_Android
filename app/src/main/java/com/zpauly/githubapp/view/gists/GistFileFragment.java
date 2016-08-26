package com.zpauly.githubapp.view.gists;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.protectsoft.webviewcode.Codeview;
import com.protectsoft.webviewcode.Settings;
import com.zpauly.githubapp.Constants;
import com.zpauly.githubapp.R;
import com.zpauly.githubapp.base.BaseFragment;
import com.zpauly.githubapp.entity.response.gists.GistFileBean;
import com.zpauly.githubapp.entity.response.gists.GistFileMapBean;
import com.zpauly.githubapp.entity.response.gists.GistsBean;
import com.zpauly.githubapp.network.gists.GistsMethod;
import com.zpauly.githubapp.ui.RefreshView;
import com.zpauly.githubapp.utils.SPUtil;

import rx.Subscriber;

/**
 * Created by root on 16-8-8.
 */

public class GistFileFragment extends BaseFragment {
    private final String TAG = getClass().getName();

    private WebView mWB;

    private RefreshView mRefreshView;

    private Subscriber<GistsBean> gistFileSubscriber;

    private String auth;
    private GistsMethod method;

    private String fileName;
    private String id;

    private String content;

    @Override
    public void onStop() {
        if (gistFileSubscriber != null) {
            if (gistFileSubscriber.isUnsubscribed()) {
                gistFileSubscriber.unsubscribe();
            }
        }
        super.onStop();
    }

    @Override
    protected void initViews(View view) {
        getAttrs();

        auth = SPUtil.getString(getContext(), Constants.USER_INFO, Constants.USER_AUTH, null);
        method = GistsMethod.getInstance();

        mRefreshView = (RefreshView) view.findViewById(R.id.gist_file_RefreshView);
        mWB = (WebView) view.findViewById(R.id.gist_file_WB);

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
        } else {
            lang = Settings.MimeType.TEXT_HTML;
        }

        Codeview.with(getContext())
                .setStyle(Settings.WithStyle.GITHUBGIST)
                .setLang(lang)
                .withCode(content)
                .into(mWB);
    }

    private void getAttrs() {
        Bundle bundle = getArguments();
        if (bundle != null) {
            id = bundle.getString(GistFileActivity.FILE_ID);
            fileName = bundle.getString(GistFileActivity.FILE_NAME);
        }
    }

    @Override
    protected View setContentView(LayoutInflater inflater, @Nullable ViewGroup container) {
        return inflater.inflate(R.layout.fragment_gist_file, container, false);
    }
}
