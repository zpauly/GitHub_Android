package com.zpauly.githubapp.view.viewholder;

import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.zpauly.githubapp.R;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by zpauly on 16-8-5.
 */

public class GistViewHolder extends RecyclerView.ViewHolder {
    public final LinearLayout mLayout;

    public final CircleImageView mAvatarIV;

    public final AppCompatTextView mTitleTV;

    public final AppCompatTextView mTimeTV;

    public final AppCompatTextView mDescTV;

    public GistViewHolder(View itemView) {
        super(itemView);

        mLayout = (LinearLayout) itemView.findViewById(R.id.gist_item_layout);
        mAvatarIV = (CircleImageView) itemView.findViewById(R.id.gist_item_avatar_IV);
        mTitleTV = (AppCompatTextView) itemView.findViewById(R.id.gist_item_title_TV);
        mTimeTV = (AppCompatTextView) itemView.findViewById(R.id.gist_item_time_TV);
        mDescTV = (AppCompatTextView) itemView.findViewById(R.id.gist_item_desc_TV);
    }
}
