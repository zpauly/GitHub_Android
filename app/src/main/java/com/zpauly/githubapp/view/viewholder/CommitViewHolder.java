package com.zpauly.githubapp.view.viewholder;

import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.widget.LinearLayout;

import com.zpauly.githubapp.R;
import com.zpauly.githubapp.base.BaseRecyclerViewViewHolder;

import butterknife.BindView;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by zpauly on 16/9/20.
 */

public class CommitViewHolder extends BaseRecyclerViewViewHolder {
    @BindView(R.id.commit_item_layout) public LinearLayout mLayout;

    @BindView(R.id.commit_item_avatar) public CircleImageView mAvatarIV;

    @BindView(R.id.commit_item_username_TV) public AppCompatTextView mUsernameTV;

    @BindView(R.id.commit_item_message_TV) public AppCompatTextView mMessageTV;

    @BindView(R.id.commit_item_time_TV) public AppCompatTextView mTimeTV;

    @BindView(R.id.commit_item_comment_count_TV) public AppCompatTextView mCommentCountTV;

    public CommitViewHolder(View itemView) {
        super(itemView);
    }
}
