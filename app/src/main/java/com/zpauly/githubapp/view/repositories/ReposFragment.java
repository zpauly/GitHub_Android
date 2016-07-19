package com.zpauly.githubapp.view.repositories;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zpauly.githubapp.R;
import com.zpauly.githubapp.adapter.ReposRecyclerViewAdapter;
import com.zpauly.githubapp.base.BaseFragment;
import com.zpauly.githubapp.db.ReposDao;
import com.zpauly.githubapp.db.ReposModel;
import com.zpauly.githubapp.ui.DividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by root on 16-7-18.
 */

public class ReposFragment extends BaseFragment {
    private final String TAG = getClass().getName();

    public static final String FRAGMENT_TAG = "FRAGMENT_TAG";

    public static final int ALL = 0;
    public static final int PUBLIC = 1;
    public static final int PRIVATE = 2;
    public static final int SOURCE = 3;
    public static final int FORK = 4;

    private int fragmentTag;

    private RecyclerView mReposRV;
    private ReposRecyclerViewAdapter mReposRVAdapter;

    @Override
    protected void initViews(View view) {
        fragmentTag = getArguments().getInt(FRAGMENT_TAG);

        mReposRV = (RecyclerView) view.findViewById(R.id.repos_RV);
        setupRecyclerView();
    }

    private void setupRecyclerView() {
        mReposRVAdapter = new ReposRecyclerViewAdapter(getContext());
        mReposRV.setLayoutManager(new LinearLayoutManager(getContext()));
        mReposRV.setAdapter(mReposRVAdapter);
        mReposRV.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL_LIST));

        loadData();
    }

    private void loadData() {
        List<ReposModel> list = new ArrayList<>();
        switch (fragmentTag) {
            case ALL:
                list = ReposDao.queryRepos();
                Log.i(TAG, String.valueOf(list.get(0).isPrivateX()));
                break;
            case PUBLIC:
                list = ReposDao.queryRepos("privatex", String.valueOf(0));
                break;
            case PRIVATE:
                list = ReposDao.queryRepos("privatex", String.valueOf(1));
                break;
            case SOURCE:
                list = ReposDao.queryRepos("fork", String.valueOf(0));
                break;
            case FORK:
                list = ReposDao.queryRepos("fork", String.valueOf(1));
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
