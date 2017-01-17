package com.zpauly.githubapp.view.viewholder;

import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.widget.LinearLayout;

import com.zpauly.githubapp.R;
import com.zpauly.githubapp.base.BaseRecyclerViewViewHolder;

import butterknife.BindView;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by zpauly on 16/9/25.
 */

public class ContributorViewHolder extends BaseRecyclerViewViewHolder {
    @BindView(R.id.contributor_item_layout) public LinearLayout mLayout;

    @BindView(R.id.contributor_item_avatar_IV) public CircleImageView mAvatarIV;

    @BindView(R.id.contributor_item_username_TV) public AppCompatTextView mUsernameTV;

    @BindView(R.id.contributor_item_commit_count_TV) public AppCompatTextView mCommitCountTV;

    public ContributorViewHolder(View itemView) {
        super(itemView);
    }
}
