package com.zpauly.githubapp.view.viewholder;

import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.widget.LinearLayout;

import com.zpauly.githubapp.R;
import com.zpauly.githubapp.base.BaseRecyclerViewViewHolder;

import butterknife.BindView;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by zpauly on 16/9/24.
 */

public class ReleaseViewHolder extends BaseRecyclerViewViewHolder {
    @BindView(R.id.releases_item_layout) public LinearLayout mLayout;

    @BindView(R.id.releases_item_avatar_IV) public CircleImageView mAvatarIV;

    @BindView(R.id.releases_item_name_TV) public AppCompatTextView mNameTV;

    @BindView(R.id.releases_item_username_TV) public AppCompatTextView mUsernameTV;

    @BindView(R.id.releases_item_tag_TV) public AppCompatTextView mTagTV;

    @BindView(R.id.releases_item_time_TV) public AppCompatTextView mTimeTV;

    public ReleaseViewHolder(View itemView) {
        super(itemView);
    }
}
