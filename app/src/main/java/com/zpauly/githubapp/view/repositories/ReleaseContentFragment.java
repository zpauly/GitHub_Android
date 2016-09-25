package com.zpauly.githubapp.view.repositories;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zpauly.githubapp.R;
import com.zpauly.githubapp.base.BaseFragment;
import com.zpauly.githubapp.entity.response.repos.ReleaseBean;
import com.zpauly.githubapp.utils.ImageUtil;
import com.zpauly.githubapp.utils.TextUtil;
import com.zpauly.githubapp.utils.viewmanager.LoadMoreInSwipeRefreshLayoutMoreManager;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by zpauly on 16/9/25.
 */

public class ReleaseContentFragment extends BaseFragment {
    private final String TAG = getClass().getName();

    private CircleImageView mAvatarIV;
    private AppCompatTextView mUsernameTV;
    private AppCompatTextView mTagTV;
    private AppCompatTextView mTimeTV;
    private AppCompatTextView mBodyTV;

    private ReleaseBean releaseBean;

    @Override
    protected void initViews(View view) {
        getAttrs();

        mUsernameTV = (AppCompatTextView) view.findViewById(R.id.release_username_TV);
        mAvatarIV = (CircleImageView) view.findViewById(R.id.release_avatar_IV);
        mTagTV = (AppCompatTextView) view.findViewById(R.id.release_tag_TV);
        mTimeTV = (AppCompatTextView) view.findViewById(R.id.release_time_TV);
        mBodyTV = (AppCompatTextView) view.findViewById(R.id.release_body_TV);

        if (releaseBean != null) {
            AppCompatActivity activity = (AppCompatActivity) getActivity();
            if (activity.getSupportActionBar() != null) {
                activity.getSupportActionBar().setTitle(releaseBean.getName());
            }
            mUsernameTV.setText(releaseBean.getAuthor().getLogin());
            mTagTV.setText(releaseBean.getTag_name());
            mTimeTV.setText(TextUtil.timeConverter(releaseBean.getCreated_at()));
            mBodyTV.setText(releaseBean.getBody());
            ImageUtil.loadAvatarImageFromUrl(getContext(), releaseBean.getAuthor().getAvatar_url(),
                    mAvatarIV);
        }
    }

    private void getAttrs() {
        Bundle bundle = getArguments();
        if (bundle != null) {
            releaseBean = bundle.getParcelable(ReleaseContentActivity.RELEASE);
        }
    }

    @Override
    protected View setContentView(LayoutInflater inflater, @Nullable ViewGroup container) {
        return inflater.inflate(R.layout.fragment_release_content, container, false);
    }
}
