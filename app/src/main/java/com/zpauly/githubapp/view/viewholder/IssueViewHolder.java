package com.zpauly.githubapp.view.viewholder;

import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.zpauly.githubapp.R;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by zpauly on 16/9/4.
 */
public class IssueViewHolder extends RecyclerView.ViewHolder {
    public final LinearLayout mLayout;

    public final CircleImageView mAvatar;

    public final AppCompatTextView mNum;

    public final AppCompatTextView mUsername;

    public final AppCompatTextView mTitle;

    public final AppCompatTextView mTime;

    public final AppCompatTextView mCommentCount;

    public IssueViewHolder(View itemView) {
        super(itemView);

        mLayout = (LinearLayout) itemView.findViewById(R.id.issue_item_layout);
        mAvatar = (CircleImageView) itemView.findViewById(R.id.issue_item_avatar);
        mNum = (AppCompatTextView) itemView.findViewById(R.id.issue_item_num);
        mUsername = (AppCompatTextView) itemView.findViewById(R.id.issue_item_username);
        mTitle = (AppCompatTextView) itemView.findViewById(R.id.issue_item_title);
        mTime = (AppCompatTextView) itemView.findViewById(R.id.issue_item_time);
        mCommentCount = (AppCompatTextView) itemView.findViewById(R.id.issue_item_comment_count);
    }
}
