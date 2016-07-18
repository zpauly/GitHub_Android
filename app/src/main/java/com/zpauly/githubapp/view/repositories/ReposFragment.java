package com.zpauly.githubapp.view.repositories;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zpauly.githubapp.R;
import com.zpauly.githubapp.adapter.ReposRecyclerViewAdapter;
import com.zpauly.githubapp.base.BaseFragment;
import com.zpauly.githubapp.db.ReposDao;
import com.zpauly.githubapp.db.ReposModel;
import com.zpauly.githubapp.ui.DividerItemDecoration;

import java.util.List;

/**
 * Created by root on 16-7-18.
 */

public class ReposFragment extends BaseFragment {
    public static final String FRAGMENT_TAG = "FRAGMENT_TAG";

    public static final String PUBLIC = "PUBLIC";
    public static final String PRIVATE = "PRIVATE";
    public static final String SOURCE = "SOURCE";
    public static final String FORK = "FORK";

    private String fragmentTag;

    private RecyclerView mReposRV;
    private ReposRecyclerViewAdapter mReposRVAdapter;

    @Override
    protected void initViews(View view) {
        fragmentTag = getArguments().getString(FRAGMENT_TAG);

        mReposRV = (RecyclerView) view.findViewById(R.id.repos_RV);
        setupRecyclerView();
    }

    private void setupRecyclerView() {
        mReposRV.setLayoutManager(new LinearLayoutManager(getContext()));
        mReposRV.setAdapter(mReposRVAdapter);
        mReposRV.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL_LIST));

        List<ReposModel> list = null;
        switch (fragmentTag) {
            case PUBLIC:
                list = ReposDao.queryRepos("private", "false");
                break;
            case PRIVATE:
                list = ReposDao.queryRepos("private", "true");
                break;
            case SOURCE:
                list = ReposDao.queryRepos("fork", "false");
                break;
            case FORK:
                list = ReposDao.queryRepos("fork", "true");
                break;
            default:
                break;
        }
        mReposRVAdapter.addAllData(list);
    }

    @Override
    protected View setContentView(LayoutInflater inflater, @Nullable ViewGroup container) {
        return inflater.inflate(R.layout.fragment_repos, container, false);
    }
}
