package com.zpauly.githubapp.view.repositories;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zpauly.githubapp.R;
import com.zpauly.githubapp.base.BaseFragment;
import com.zpauly.githubapp.entity.response.repos.RepositoriesBean;

/**
 * Created by zpauly on 16/9/20.
 */

public class RepoCommitFragment extends BaseFragment {
    private final String TAG = getClass().getName();

    private RepositoriesBean repositoriesBean;

    private void getAttrs() {
        Bundle bundle = getArguments();
        if (bundle != null) {
            repositoriesBean = bundle.getParcelable(RepoCommitActivity.REPO);
        }
    }

    @Override
    protected void initViews(View view) {
        getAttrs();
    }

    @Override
    protected View setContentView(LayoutInflater inflater, @Nullable ViewGroup container) {
        return inflater.inflate(R.layout.fragment_repo_commit, container, false);
    }
}
