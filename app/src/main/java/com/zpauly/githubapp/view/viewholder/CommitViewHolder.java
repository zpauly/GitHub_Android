package com.zpauly.githubapp.view.viewholder;

import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.zpauly.githubapp.R;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by zpauly on 16/9/20.
 */

public class CommitViewHolder extends RecyclerView.ViewHolder {
    public final LinearLayout mLayout;

    public final CircleImageView mAvatarIV;

    public final AppCompatTextView mUsernameTV;

    public final AppCompatTextView mMessageTV;

    public final AppCompatTextView mTimeTV;

    public final AppCompatTextView mCommentCountTV;

    public CommitViewHolder(View itemView) {
        super(itemView);

        mLayout = (LinearLayout) itemView.findViewById(R.id.commit_item_layout);
        mAvatarIV = (CircleImageView) itemView.findViewById(R.id.commit_item_avatar);
        mUsernameTV = (AppCompatTextView) itemView.findViewById(R.id.commit_item_username_TV);
        mMessageTV = (AppCompatTextView) itemView.findViewById(R.id.commit_item_message_TV);
        mTimeTV = (AppCompatTextView) itemView.findViewById(R.id.commit_item_time_TV);
        mCommentCountTV = (AppCompatTextView) itemView.findViewById(R.id.commit_item_comment_count_TV);
    }
}
