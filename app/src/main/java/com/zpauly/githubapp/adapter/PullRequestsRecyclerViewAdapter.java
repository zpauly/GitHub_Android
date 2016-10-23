package com.zpauly.githubapp.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zpauly.githubapp.R;
import com.zpauly.githubapp.entity.response.PullRequestBean;
import com.zpauly.githubapp.utils.ImageUtil;
import com.zpauly.githubapp.utils.TextUtil;
import com.zpauly.githubapp.view.issues.PullRequestContentActivity;
import com.zpauly.githubapp.view.viewholder.IssueViewHolder;

/**
 * Created by zpauly on 2016/10/22.
 */

public class PullRequestsRecyclerViewAdapter extends LoadMoreRecyclerViewAdapter<PullRequestBean, IssueViewHolder> {
    private final String TAG = getClass().getName();

    public PullRequestsRecyclerViewAdapter(Context context) {
        super(context);
    }

    @Override
    public IssueViewHolder createContentViewHolder(ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(getContext()).inflate(R.layout.item_recyclerview_issue, parent, false);
        IssueViewHolder viewHolder = new IssueViewHolder(mView);
        return viewHolder;
    }

    @Override
    public void bindContentViewHolder(IssueViewHolder holder, int position) {
        final PullRequestBean data = getData().get(position);
        Log.i(TAG, "comments count = " + String.valueOf(data.getComments()));
        holder.mCommentCount.setText(String.valueOf(data.getComments()));
        holder.mTitle.setText(data.getTitle());
        holder.mTime.setText(TextUtil.timeConverter(data.getCreated_at()));
        holder.mUsername.setText(data.getUser().getLogin());
        holder.mNum.setText("#" + data.getNumber());
        ImageUtil.loadAvatarImageFromUrl(getContext(), data.getUser().getAvatar_url(), holder.mAvatar);
        holder.mLayout.setClickable(true);
        holder.mLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PullRequestContentActivity.launchActivity(getContext(), data);
            }
        });
    }
}
