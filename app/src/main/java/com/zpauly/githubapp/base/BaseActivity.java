package com.zpauly.githubapp.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.zpauly.githubapp.db.UserDao;
import com.zpauly.githubapp.db.UserModel;
import com.zpauly.githubapp.utils.viewmanager.LoadMoreInSwipeRefreshLayoutMoreManager;
import com.zpauly.githubapp.utils.viewmanager.LoadMoreManager;
import com.zpauly.githubapp.utils.viewmanager.RefreshViewManager;
import com.zpauly.githubapp.utils.viewmanager.ViewManager;
import com.zpauly.githubapp.view.profile.OthersActivity;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zpauly on 16-6-8.
 */
public abstract class BaseActivity extends AppCompatActivity {
    protected UserModel userInfo;
    protected String username;

    protected View mView;
    protected Map<String, ViewManager> viewManagerMap = new HashMap<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        userInfo = UserDao.queryUser();
        username = getIntent().getStringExtra(OthersActivity.USERNAME);

        initContent();

        initViews();

        for (ViewManager viewManager : viewManagerMap.values()) {
            viewManager.manager();
        }
    }

    public void setViewManager(ViewManager... viewManagers) {
        String key;
        for (ViewManager viewManager : viewManagers) {
            key = getKey(viewManager);
            viewManagerMap.put(key, viewManager);
        }
    }

    private String getKey(ViewManager viewManager) {
        if (viewManager instanceof LoadMoreInSwipeRefreshLayoutMoreManager) {
            return LoadMoreInSwipeRefreshLayoutMoreManager.class.getCanonicalName();
        }
        if (viewManager instanceof RefreshViewManager) {
            return RefreshViewManager.class.getCanonicalName();
        }
        if (viewManager instanceof LoadMoreManager) {
            return LoadMoreManager.class.getCanonicalName();
        }
        return null;
    }

    public <T extends ViewManager> T getViewManager(Class<? extends ViewManager> clazz) {
        ViewManager result;
        if ((result = viewManagerMap.get(clazz.getName())) == null) {
            return null;
        }
        return (T) result;
    }

    public abstract void initViews();

    public abstract void initContent();
}
