package com.zpauly.githubapp.view;

/**
 * Created by zpauly on 16/8/25.
 */
public class RefreshActivity extends ToolbarActivity {
    private final String TAG = getClass().getName();

    private ClickToRefreshFragment mClickRefreshFragment;
    private RefreshFragment mRefreshFragment;

    @Override
    public void initViews() {
        mClickRefreshFragment = new ClickToRefreshFragment();
        mRefreshFragment = new RefreshFragment();

        setContent(mClickRefreshFragment);
    }


}
