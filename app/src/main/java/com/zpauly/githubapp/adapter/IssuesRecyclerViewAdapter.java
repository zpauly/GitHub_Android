package com.zpauly.githubapp.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.zpauly.githubapp.R;
import com.zpauly.githubapp.entity.response.issues.IssueBean;
import com.zpauly.githubapp.utils.ImageUtil;
import com.zpauly.githubapp.utils.TextUtil;
import com.zpauly.githubapp.view.issues.IssueContentActivity;
import com.zpauly.githubapp.view.viewholder.IssueViewHolder;

/**
 * Created by zpauly on 16/9/4.
 */
public class IssuesRecyclerViewAdapter extends LoadMoreRecyclerViewAdapter<IssueBean, IssueViewHolder> {
    private final String TAG = getClass().getName();

    public IssuesRecyclerViewAdapter(Context context) {
        super(context);
    }

    @Override
    public IssueViewHolder createContentViewHolder(ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(getContext()).inflate(R.layout.item_recyclerview_issue, parent, false);
        return new IssueViewHolder(mView);
    }

    @Override
    public void bindContentViewHolder(IssueViewHolder holder, int position) {
        final IssueBean data = getData().get(position);
        ImageUtil.loadAvatarImageFromUrl(getContext(), data.getUser().getAvatar_url(), holder.mAvatar);
        holder.mCommentCount.setText(String.valueOf(data.getComments()));
        holder.mNum.setText("#" + String.valueOf(data.getNumber()));
        holder.mUsername.setText(data.getUser().getLogin());
        holder.mTitle.setText(data.getTitle());
        holder.mTime.setText(TextUtil.timeConverter(data.getCreated_at()));
        holder.mLayout.setClickable(true);
        holder.mLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IssueContentActivity.launchActivity(getContext(), data);
            }
        });
    }
}
