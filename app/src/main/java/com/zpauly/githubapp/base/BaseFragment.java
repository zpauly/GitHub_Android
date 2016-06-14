package com.zpauly.githubapp.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by zpauly on 16-6-10.
 */
public abstract class BaseFragment extends Fragment {
    protected View mView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setContentView(inflater, container);
        initViews();
        return mView;
    }

    protected abstract void initViews();

    protected abstract void setContentView(LayoutInflater inflater, @Nullable ViewGroup container);
}
