package com.zpauly.githubapp.view.viewholder;

import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.widget.LinearLayout;

import com.zpauly.githubapp.R;
import com.zpauly.githubapp.base.BaseRecyclerViewViewHolder;

import butterknife.BindView;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by zpauly on 16/9/6.
 */
public class CommentsViewHolder extends BaseRecyclerViewViewHolder {
    @BindView(R.id.comment_item_layout) public LinearLayout mLayout;

    @BindView(R.id.comment_item_avatar) public CircleImageView mAvatarIV;

    @BindView(R.id.comment_item_username) public AppCompatTextView mUsernameTV;

    @BindView(R.id.comment_item_time) public AppCompatTextView mTimeTV;

    @BindView(R.id.comment_item_body) public AppCompatTextView mBodyTV;


    public CommentsViewHolder(View itemView) {
        super(itemView);
    }
}
