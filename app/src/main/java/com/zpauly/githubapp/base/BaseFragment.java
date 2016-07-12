package com.zpauly.githubapp.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zpauly.githubapp.db.UserDao;
import com.zpauly.githubapp.db.UserModel;

/**
 * Created by zpauly on 16-6-10.
 */
public abstract class BaseFragment extends Fragment {
    protected View mView;

    protected UserModel userInfo;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setContentView(inflater, container);
        userInfo = UserDao.queryUser();
        initViews();
        return mView;
    }

    protected abstract void initViews();

    protected abstract void setContentView(LayoutInflater inflater, @Nullable ViewGroup container);
}
