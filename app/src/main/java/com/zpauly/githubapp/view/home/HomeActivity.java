package com.zpauly.githubapp.view.home;

import com.zpauly.githubapp.R;
import com.zpauly.githubapp.view.BaseDrawerActivity;

/**
 * Created by zpauly on 16-6-9.
 */
public class HomeActivity extends BaseDrawerActivity {


    @Override
    public void initViews() {

    }

    @Override
    protected void setToolbar() {
        super.setToolbar();
        setToolbarTitle(R.string.profile);
    }
}
