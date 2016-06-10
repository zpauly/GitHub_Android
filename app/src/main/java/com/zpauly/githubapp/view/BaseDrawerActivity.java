package com.zpauly.githubapp.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.zpauly.githubapp.R;

/**
 * Created by zpauly on 16-6-9.
 */
public abstract class BaseDrawerActivity extends BaseToolbarActivity {
    protected DrawerLayout mDrawerLayout;
    protected NavigationView mNavigationView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initNavDrawer();
    }

    private void initNavDrawer() {
        mDrawerLayout = (DrawerLayout) findViewById(R.id.nav_drawer_layout);
        mNavigationView = (NavigationView) findViewById(R.id.nav_view);

        if (mToolbar != null) {
            Log.i("BaseDrawerActivity", "not null");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            mToolbar.setNavigationIcon(R.drawable.ic_menu_white_24dp);
            setOnToolbarNavClickedListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mDrawerLayout.openDrawer(GravityCompat.START);
                }
            });

            initNavView();
        }
    }

    private void initNavView() {
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_item_1:
                        Snackbar.make(mNavigationView, "item 1", Snackbar.LENGTH_SHORT).show();
                        break;
                }
                return false;
            }
        });
    }

    @Override
    public void initContent() {
        setContentView(R.layout.drawer_layout);
    }
}
