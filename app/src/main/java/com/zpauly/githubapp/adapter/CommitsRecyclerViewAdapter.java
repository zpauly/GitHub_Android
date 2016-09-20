package com.zpauly.githubapp.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zpauly.githubapp.R;
import com.zpauly.githubapp.entity.response.repos.SingleCommitBean;
import com.zpauly.githubapp.utils.ImageUtil;
import com.zpauly.githubapp.utils.TextUtil;
import com.zpauly.githubapp.view.viewholder.CommitViewHolder;

/**
 * Created by zpauly on 16/9/20.
 */

public class CommitsRecyclerViewAdapter extends LoadMoreRecyclerViewAdapter<SingleCommitBean, CommitViewHolder> {
    private final String TAG = getClass().getName();

    public CommitsRecyclerViewAdapter(Context context) {
        super(context);
    }

    @Override
    public CommitViewHolder createContentViewHolder(ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(getContext()).inflate(R.layout.item_recyclerview_commit, parent, false);
        CommitViewHolder viewHolder = new CommitViewHolder(mView);
        return viewHolder;
    }

    @Override
    public void bindContentViewHolder(CommitViewHolder holder, int position) {
        SingleCommitBean data = getData().get(position);
        if (data.getAuthor() != null) {
            ImageUtil.loadAvatarImageFromUrl(getContext(), data.getAuthor().getAvatar_url(),
                    holder.mAvatarIV);
            holder.mLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        } else {

        }
        if (data.getCommit() != null) {
            holder.mUsernameTV.setText(data.getCommit().getCommitter().getName());
            holder.mMessageTV.setText(data.getCommit().getMessage());
            holder.mTimeTV.setText(TextUtil.timeConverter(data.getCommit().getCommitter().getDate()));
            holder.mCommentCountTV.setText(String.valueOf(data.getCommit().getComment_count()));
        }
    }
}
