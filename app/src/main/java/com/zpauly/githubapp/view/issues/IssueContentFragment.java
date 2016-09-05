package com.zpauly.githubapp.view.issues;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zpauly.githubapp.R;
import com.zpauly.githubapp.base.BaseFragment;
import com.zpauly.githubapp.entity.response.issues.IssueBean;

/**
 * Created by zpauly on 16/9/5.
 */
public class IssueContentFragment extends BaseFragment {
    private final String TAG = getClass().getName();
    
    private IssueBean issueBean;

    @Override
    protected void initViews(View view) {
        getAttrs();
    }

    @Override
    protected View setContentView(LayoutInflater inflater, @Nullable ViewGroup container) {
        return inflater.inflate(R.layout.fragment_issue_content, container, false);
    }

    private void getAttrs() {
        Bundle bundle = getArguments();
        if (bundle != null) {
            issueBean = bundle.getParcelable(IssueContentActivity.ISSUE);
        }
    }
}
