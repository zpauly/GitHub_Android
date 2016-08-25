package com.zpauly.githubapp.view;

import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zpauly.githubapp.R;
import com.zpauly.githubapp.base.BaseFragment;

/**
 * Created by zpauly on 16/8/25.
 */
public class ClickToRefreshFragment extends BaseFragment {
    private AppCompatTextView mRefreshTV;

    @Override
    protected void initViews(View view) {
        mRefreshTV = (AppCompatTextView) view.findViewById(R.id.refresh_TV);
    }

    @Override
    protected View setContentView(LayoutInflater inflater, @Nullable ViewGroup container) {
        return inflater.inflate(R.layout.fragment_click_to_refresh, container, false);
    }
}
