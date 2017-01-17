package com.zpauly.githubapp.view.viewholder;

import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.widget.LinearLayout;

import com.zpauly.githubapp.R;
import com.zpauly.githubapp.base.BaseRecyclerViewViewHolder;

import butterknife.BindView;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by zpauly on 16/9/4.
 */
public class IssueViewHolder extends BaseRecyclerViewViewHolder {
    @BindView(R.id.issue_item_layout) public LinearLayout mLayout;

    @BindView(R.id.issue_item_avatar) public CircleImageView mAvatar;

    @BindView(R.id.issue_item_num) public AppCompatTextView mNum;

    @BindView(R.id.issue_item_username) public AppCompatTextView mUsername;

    @BindView(R.id.issue_item_title) public AppCompatTextView mTitle;

    @BindView(R.id.issue_item_time) public AppCompatTextView mTime;

    @BindView(R.id.issue_item_comment_count) public AppCompatTextView mCommentCount;

    public IssueViewHolder(View itemView) {
        super(itemView);
    }
}
