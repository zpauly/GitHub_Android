package com.zpauly.githubapp.view.viewholder;

import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.zpauly.githubapp.R;
import com.zpauly.githubapp.base.BaseRecyclerViewViewHolder;

import butterknife.BindView;

/**
 * Created by zpauly on 16-6-14.
 */
public class EventsViewHolder extends BaseRecyclerViewViewHolder {
    @BindView(R.id.events_item_layout) public LinearLayout mLayout;

    @BindView(R.id.events_avatar_IV) public ImageView mUserAvatarIV;

    @BindView(R.id.events_loginname_TV) public AppCompatTextView mUsernameTV;

    @BindView(R.id.events_action_TV) public AppCompatTextView mActionTV;

    @BindView(R.id.events_repo_TV) public AppCompatTextView mRepoTV;

    @BindView(R.id.events_type_IV) public ImageView mTypeIV;

    @BindView(R.id.events_commits_RV) public RecyclerView mCommitsRV;

    @BindView(R.id.events_time_TV) public AppCompatTextView mTimeTV;

    @BindView(R.id.events_comment_TV) public AppCompatTextView mCommentTV;

    public EventsViewHolder(View itemView) {
        super(itemView);
    }
}
