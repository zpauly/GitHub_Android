package com.zpauly.githubapp.view.stars;

import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zpauly.githubapp.R;
import com.zpauly.githubapp.base.BaseFragment;

/**
 * Created by zpauly on 16-7-16.
 */

public class StarsFragment extends BaseFragment {
    @Override
    protected void initViews(View view) {

    }

    @Override
    protected View setContentView(LayoutInflater inflater, @Nullable ViewGroup container) {
        return inflater.inflate(R.layout.fragment_stars, container, false);
    }
}
