package com.zpauly.githubapp.view.viewholder;

import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.zpauly.githubapp.R;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by zpauly on 16/9/25.
 */

public class ContributorViewHolder extends RecyclerView.ViewHolder {
    public final LinearLayout mLayout;

    public final CircleImageView mAvatarIV;

    public final AppCompatTextView mUsernameTV;

    public final AppCompatTextView mCommitCountTV;

    public ContributorViewHolder(View itemView) {
        super(itemView);

        mLayout = (LinearLayout) itemView.findViewById(R.id.contributor_item_layout);
        mAvatarIV = (CircleImageView) itemView.findViewById(R.id.contributor_item_avatar_IV);
        mUsernameTV = (AppCompatTextView) itemView.findViewById(R.id.contributor_item_username_TV);
        mCommitCountTV = (AppCompatTextView) itemView.findViewById(R.id.contributor_item_commit_count_TV);
    }
}
