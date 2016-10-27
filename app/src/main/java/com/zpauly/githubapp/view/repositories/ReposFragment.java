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
import com.zpauly.githubapp.entity.response.repos.RepositoriesBean;

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

    private List<RepositoriesBean> reposList = new ArrayList<>();

    @Override
    protected void initViews(View view) {
        Bundle bundle = getArguments();
        if (bundle != null) {
            fragmentTag = bundle.getInt(FRAGMENT_TAG);
            reposList = bundle.getParcelableArrayList(ReposActivity.REPOS);
        }

        mReposRV = (RecyclerView) view.findViewById(R.id.repos_RV);

        setupRecyclerView();
    }

    private void setupRecyclerView() {
        mReposRVAdapter = new ReposRecyclerViewAdapter(getContext());
        mReposRV.setLayoutManager(new LinearLayoutManager(getContext()));
        mReposRV.setAdapter(mReposRVAdapter);
        mReposRVAdapter.setHasLoading(false);

        loadData();
    }

    private void loadData() {
        List<RepositoriesBean> list = new ArrayList<>();
        switch (fragmentTag) {
            case ALL:
                list.addAll(reposList);
                break;
            case PUBLIC:
                list.addAll(queryList("privatex", false));
                break;
            case PRIVATE:
                list.addAll(queryList("privatex", true));
                break;
            case SOURCE:
                list.addAll(queryList("fork", false));
                break;
            case FORK:
                list.addAll(queryList("fork", true));
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

    private List<RepositoriesBean> queryList(String query, boolean data) {
        List<RepositoriesBean> list = new ArrayList<>();
        for (RepositoriesBean repo : reposList) {
            if (query.equals("privatex")) {
                if (repo.isPrivateX() == data) {
                    list.add(repo);
                }
            }
            if (query.equals("fork")) {
                if (repo.isFork() == data) {
                    list.add(repo);
                }
            }
        }
        return list;
    }
}
