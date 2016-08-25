package com.zpauly.githubapp.view;

import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.zpauly.githubapp.R;
import com.zpauly.githubapp.base.BaseFragment;

/**
 * Created by zpauly on 16/8/25.
 */
public class RefreshFragment extends BaseFragment {
    private ProgressBar mRefreshPB;

    @Override
    protected void initViews(View view) {
        mRefreshPB = (ProgressBar) view.findViewById(R.id.refresh_PB);
    }

    @Override
    protected View setContentView(LayoutInflater inflater, @Nullable ViewGroup container) {
        return inflater.inflate(R.layout.fragment_refresh, container, false);
    }
}
