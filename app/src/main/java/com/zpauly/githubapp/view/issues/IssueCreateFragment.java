package com.zpauly.githubapp.view.issues;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v7.widget.AppCompatButton;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.zpauly.githubapp.R;
import com.zpauly.githubapp.base.BaseFragment;
import com.zpauly.githubapp.ui.FloatingActionButton;

/**
 * Created by zpauly on 16/9/12.
 */
public class IssueCreateFragment extends BaseFragment {
    private final String TAG = getClass().getName();

    private LinearLayout mChooseLayout;

    private AppCompatButton mMilestoneBTN;
    private AppCompatButton mAssigneesBTN;
    private AppCompatButton mLabelsBTN;

    private TextInputEditText mTitleET;
    private TextInputEditText mBodyET;

    private FloatingActionButton mSendFAB;

    private String username;
    private String repoName;

    @Override
    protected void initViews(View view) {
        getAttrs();

        mChooseLayout = (LinearLayout) view.findViewById(R.id.issue_create_choose_layout);
        mMilestoneBTN = (AppCompatButton) view.findViewById(R.id.issue_create_milestone_TV);
        mAssigneesBTN = (AppCompatButton) view.findViewById(R.id.issue_create_assignees_TV);
        mLabelsBTN = (AppCompatButton) view.findViewById(R.id.issue_create_labels_TV);

        mTitleET = (TextInputEditText) view.findViewById(R.id.issue_create_title_ET);
        mBodyET = (TextInputEditText) view.findViewById(R.id.issue_create_body_ET);

        mSendFAB = (FloatingActionButton) view.findViewById(R.id.issue_create_send_FAB);
    }

    @Override
    protected View setContentView(LayoutInflater inflater, @Nullable ViewGroup container) {
        return inflater.inflate(R.layout.fragment_create_issue, container, false);
    }

    private void getAttrs() {
        Bundle bundle = getArguments();
        if (bundle != null) {
            username = bundle.getString(IssueCreateActivity.USERNAME);
            repoName = bundle.getString(IssueCreateActivity.REPONAME);
        }
    }
}
