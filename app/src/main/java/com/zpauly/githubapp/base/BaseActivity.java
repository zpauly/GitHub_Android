package com.zpauly.githubapp.base;

import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.transition.Fade;
import android.transition.Slide;
import android.util.Log;
import android.view.View;
import android.view.Window;

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
    private final String TAG = getClass().getName();

    protected String username;

    protected View mView;
    protected Map<String, ViewManager> viewManagerMap = new HashMap<>();

    private Slide slide;
    private Fade fade;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        if (BaseApplication.getDayNightMode() == BaseApplication.DAY_MODE) {
            Log.i(TAG, "day mode");
            getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        } else if (BaseApplication.getDayNightMode() == BaseApplication.NIGHT_MODE) {
            Log.i(TAG, "night mode");
            getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        username = getIntent().getStringExtra(OthersActivity.USERNAME);

        initContent();

        initViews();

        setupWindowAnimations();

        for (ViewManager viewManager : viewManagerMap.values()) {
            viewManager.manager();
        }
    }

    private void setupWindowAnimations() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Log.i(TAG, "setup transitions");
            slide = new Slide();
            slide.setDuration(5000);
            fade = new Fade();
            fade.setDuration(1000);
            getWindow().setExitTransition(slide);
            getWindow().setEnterTransition(fade);
            getWindow().setReenterTransition(fade);
            getWindow().setReturnTransition(slide);
        }
    }

    public void setViewManager(ViewManager... viewManagers) {
        String key;
        for (ViewManager viewManager : viewManagers) {
            key = getKey(viewManager);
            viewManagerMap.put(key, viewManager);
        }
    }

    public void addViewManager(ViewManager viewManager) {
        String key;
        key = getKey(viewManager);
        viewManagerMap.put(key, viewManager);
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
