package com.zpauly.githubapp.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

import com.zpauly.githubapp.R;
import com.zpauly.githubapp.base.BaseActivity;
import com.zpauly.githubapp.listener.OnMenuItemSelectedListener;

import java.util.IllegalFormatCodePointException;
import java.util.IllegalFormatConversionException;

/**
 * Created by zpauly on 16-6-9.
 */
public abstract class ToolbarActivity extends BaseActivity {
    private final String TAG = getClass().getName();

    protected Toolbar mToolbar;
    protected FrameLayout mContentRoot;

    protected OnMenuItemSelectedListener mOnMenuItemSelectedListener;

    protected ProgressBar mToolbarPB;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initToolbar();
    }

    private void initToolbar() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        if (mToolbar != null) {
            mToolbarPB = (ProgressBar) mToolbar.findViewById(R.id.toolbar_progress_bar);
            mToolbarPB.setVisibility(View.GONE);
            setSupportActionBar(mToolbar);

            //back down when click nav by default
            ActionBar actionBar = getSupportActionBar();
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDefaultDisplayHomeAsUpEnabled(true);
            setToolbar();
        }
    }

    protected void setOnToolbarNavClickedListener(View.OnClickListener onToolbarNavClickedListener) {
        mToolbar.setNavigationOnClickListener(onToolbarNavClickedListener);
    }

    protected void setToolbarTitle(String title) {
        getSupportActionBar().setTitle(title);
    }

    protected void setToolbarTitle(int titleRes) {
        setToolbarTitle(getString(titleRes));
    }

    protected void setToolbar() {}

    @Override
    public void initContent() {
        setContentView(R.layout.activity_toolbar);
        mContentRoot = (FrameLayout) findViewById(R.id.toolbar_layout_content);
    }

    public void inflateMenu(MenuInflater inflater, Menu menu) {}

    public void createMenu(Menu menu) {}

    protected void setOnMenuItemSelectedListener(OnMenuItemSelectedListener listener) {
        mOnMenuItemSelectedListener = listener;
    }

    protected void setContent(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.toolbar_layout_content, fragment);
        fragmentTransaction.commit();
    }

    protected void setContent(int layoutResId) {
        View contentView = LayoutInflater.from(this).inflate(layoutResId, mContentRoot, false);
        mContentRoot.addView(contentView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflateMenu(inflater, menu);
        createMenu(menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mOnMenuItemSelectedListener != null) {
            mOnMenuItemSelectedListener.onItemSelected(item);
        }
        return super.onOptionsItemSelected(item);
    }
}
