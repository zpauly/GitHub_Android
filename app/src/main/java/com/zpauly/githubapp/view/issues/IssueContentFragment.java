package com.zpauly.githubapp.view.issues;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zpauly.githubapp.R;
import com.zpauly.githubapp.adapter.CommentsRecyclerViewAdapter;
import com.zpauly.githubapp.base.BaseFragment;
import com.zpauly.githubapp.entity.response.issues.IssueBean;
import com.zpauly.githubapp.ui.RefreshView;
import com.zpauly.githubapp.utils.ImageUtil;
import com.zpauly.githubapp.utils.TextUtil;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by zpauly on 16/9/5.
 */
public class IssueContentFragment extends BaseFragment {
    private final String TAG = getClass().getName();

    private RefreshView mRefreshView;
    private SwipeRefreshLayout mSRLayout;
    private AppCompatTextView mTitleTV;
    private CircleImageView mUserAvatarIV;
    private AppCompatTextView mUsernameTV;
    private AppCompatTextView mOpenTimeTV;
    private AppCompatTextView mBodyTV;
    private RecyclerView mCommentsRV;

    private IssueBean issueBean;

    private CommentsRecyclerViewAdapter mCommentsAdapter;

    @Override
    protected void initViews(View view) {
        getAttrs();

        mRefreshView = (RefreshView) view.findViewById(R.id.issue_content_RefreshView);
        mSRLayout = (SwipeRefreshLayout) view.findViewById(R.id.issue_content_SRLayout);
        mTitleTV = (AppCompatTextView) view.findViewById(R.id.issue_content_title);
        mUserAvatarIV = (CircleImageView) view.findViewById(R.id.issue_content_user_avatar);
        mUsernameTV = (AppCompatTextView) view.findViewById(R.id.issue_content_username);
        mOpenTimeTV = (AppCompatTextView) view.findViewById(R.id.issue_content_open_time);
        mBodyTV = (AppCompatTextView) view.findViewById(R.id.issue_content_body);
        mCommentsRV = (RecyclerView) view.findViewById(R.id.issue_content_comments_RV);

        setupSwipeRefreshLayout();
        setupRecyclerView();

        setupViews();
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

    private void setupViews() {
        mTitleTV.setText(issueBean.getTitle());
        ImageUtil.loadAvatarImageFromUrl(getContext(), issueBean.getUser().getAvatar_url(), mUserAvatarIV);
        mUsernameTV.setText(issueBean.getUser().getLogin());
        mOpenTimeTV.setText("at " + TextUtil.timeConverter(issueBean.getCreated_at()));
        mBodyTV.setText(issueBean.getBody());
    }

    private void setupSwipeRefreshLayout() {
        mSRLayout.measure(View.MEASURED_SIZE_MASK, View.MEASURED_HEIGHT_STATE_SHIFT);
        mSRLayout.setColorSchemeResources(R.color.colorAccent);
        mSRLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    private void setupRecyclerView() {
        mCommentsAdapter = new CommentsRecyclerViewAdapter(getContext());
        mCommentsRV.setLayoutManager(new LinearLayoutManager(getContext()));
        mCommentsRV.setAdapter(mCommentsAdapter);
    }
}
