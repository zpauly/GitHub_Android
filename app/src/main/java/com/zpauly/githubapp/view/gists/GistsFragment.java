package com.zpauly.githubapp.view.gists;

import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.zpauly.githubapp.R;
import com.zpauly.githubapp.base.BaseFragment;

/**
 * Created by zpauly on 16-6-14.
 */
public class GistsFragment extends BaseFragment {
    private SwipeRefreshLayout mSwpieRefreshLayout;

    private RecyclerView mContentRV;

    @Override
    protected void initViews() {
        mSwpieRefreshLayout = (SwipeRefreshLayout) mView.findViewById(R.id.gists_SRLayout);
        mContentRV = (RecyclerView) mView.findViewById(R.id.gists_content_RV);
    }

    @Override
    protected void setContentView(LayoutInflater inflater, @Nullable ViewGroup container) {
        mView = inflater.inflate(R.layout.fragment_gists, container);
    }
}
