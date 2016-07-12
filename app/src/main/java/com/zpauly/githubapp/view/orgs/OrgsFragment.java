package com.zpauly.githubapp.view.orgs;

import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.zpauly.githubapp.R;
import com.zpauly.githubapp.base.BaseFragment;
import com.zpauly.githubapp.view.home.HomeFragment;

/**
 * Created by zpauly on 16-6-14.
 */
public class OrgsFragment extends HomeFragment {
    private SwipeRefreshLayout mSwpieRefreshLayout;

    private RecyclerView mContentRV;

    @Override
    protected void initViews() {
        mSwpieRefreshLayout = (SwipeRefreshLayout) mView.findViewById(R.id.orgs_SRLayout);
        mContentRV = (RecyclerView) mView.findViewById(R.id.orgs_content_RV);
    }

    @Override
    protected void setContentView(LayoutInflater inflater, @Nullable ViewGroup container) {
        mView = inflater.inflate(R.layout.fragment_orgs, container);
    }

    @Override
    protected void onPullDownRefreshListener() {

    }
}
