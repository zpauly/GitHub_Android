package com.zpauly.githubapp.view.viewholder;

import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.zpauly.githubapp.R;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by zpauly on 16/9/24.
 */

public class ReleaseViewHolder extends RecyclerView.ViewHolder {
    public final LinearLayout mLayout;

    public final CircleImageView mAvatarIV;

    public final AppCompatTextView mNameTV;

    public final AppCompatTextView mUsernameTV;

    public final AppCompatTextView mTagTV;

    public final AppCompatTextView mTimeTV;

    public ReleaseViewHolder(View itemView) {
        super(itemView);

        mLayout = (LinearLayout) itemView.findViewById(R.id.releases_item_layout);
        mAvatarIV = (CircleImageView) itemView.findViewById(R.id.releases_item_avatar_IV);
        mNameTV = (AppCompatTextView) itemView.findViewById(R.id.releases_item_name_TV);
        mUsernameTV = (AppCompatTextView) itemView.findViewById(R.id.releases_item_username_TV);
        mTagTV = (AppCompatTextView) itemView.findViewById(R.id.releases_item_tag_TV);
        mTimeTV = (AppCompatTextView) itemView.findViewById(R.id.releases_item_time_TV);
    }
}
