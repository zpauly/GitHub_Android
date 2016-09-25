package com.zpauly.githubapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zpauly.githubapp.R;
import com.zpauly.githubapp.entity.response.repos.ContributorBean;
import com.zpauly.githubapp.utils.ImageUtil;
import com.zpauly.githubapp.view.viewholder.ContributorViewHolder;

/**
 * Created by zpauly on 16/9/25.
 */

public class ContributorsRecyclerViewAdapter extends LoadMoreRecyclerViewAdapter<ContributorBean, ContributorViewHolder> {
    private final String TAG = getClass().getName();

    public ContributorsRecyclerViewAdapter(Context context) {
        super(context);
    }

    @Override
    public ContributorViewHolder createContentViewHolder(ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(getContext()).inflate(R.layout.item_recyclerview_contributor, parent, false);
        ContributorViewHolder viewHolder = new ContributorViewHolder(mView);
        return viewHolder;
    }

    @Override
    public void bindContentViewHolder(ContributorViewHolder holder, int position) {
        ContributorBean data = getData().get(position);
        holder.mUsernameTV.setText(data.getLogin());
        holder.mCommitCountTV.setText(String.valueOf(data.getContributions() + " "
                + getContext().getString(R.string.commits)));
        ImageUtil.loadAvatarImageFromUrl(getContext(), data.getAvatar_url(), holder.mAvatarIV);
        holder.mLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
