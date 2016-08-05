package com.zpauly.githubapp.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.zpauly.githubapp.R;
import com.zpauly.githubapp.base.BaseActivity;

/**
 * Created by zpauly on 16-6-9.
 */
public abstract class ToolbarActivity extends BaseActivity {
    protected Toolbar mToolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initToolbar();
    }

    private void initToolbar() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        if (mToolbar != null) {
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
    }

    protected void setContent(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.toolbar_layout_content, fragment);
        fragmentTransaction.commit();
    }
}
