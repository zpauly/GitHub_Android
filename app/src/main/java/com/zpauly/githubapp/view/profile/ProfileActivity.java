package com.zpauly.githubapp.view.profile;

import android.support.design.widget.AppBarLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

import com.zpauly.githubapp.R;
import com.zpauly.githubapp.view.ToolbarActivity;

/**
 * Created by zpauly on 16-7-30.
 */

public class ProfileActivity extends ToolbarActivity {

    private AppBarLayout mABLayout;

    private FragmentManager manager;
    private FragmentTransaction transaction;

    @Override
    public void initViews() {
        mABLayout = (AppBarLayout) findViewById(R.id.profile_ABLayout);

        manager = getSupportFragmentManager();
        transaction = manager.beginTransaction();
        transaction.replace(R.id.profile_content, new ProfileFragment());
        transaction.commit();
    }

    @Override
    protected void setToolbar() {
        super.setToolbar();
        setToolbarTitle(R.string.profile);
        setOnToolbarNavClickedListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    @Override
    public void initContent() {
        setContentView(R.layout.activity_profile);
    }
}
