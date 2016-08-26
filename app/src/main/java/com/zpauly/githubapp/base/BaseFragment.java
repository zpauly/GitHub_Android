package com.zpauly.githubapp.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zpauly.githubapp.db.UserDao;
import com.zpauly.githubapp.db.UserModel;
import com.zpauly.githubapp.view.profile.OthersActivity;

/**
 * Created by zpauly on 16-6-10.
 */
public abstract class BaseFragment extends Fragment {
    protected View mView;

    protected UserModel userInfo;
    protected String username;

    private Context context;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = setContentView(inflater, container);
        userInfo = UserDao.queryUser();
        if (getArguments() != null) {
            username = getArguments().getString(OthersActivity.USERNAME, null);
        }
        initViews(mView);
        return mView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public Context getContext() {
        return context == null ? BaseApplication.getInstance() : context;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    protected abstract void initViews(View view);

    protected abstract View setContentView(LayoutInflater inflater, @Nullable ViewGroup container);
}
