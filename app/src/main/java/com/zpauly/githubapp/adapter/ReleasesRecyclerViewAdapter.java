package com.zpauly.githubapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zpauly.githubapp.R;
import com.zpauly.githubapp.entity.response.repos.ReleaseBean;
import com.zpauly.githubapp.utils.ImageUtil;
import com.zpauly.githubapp.utils.TextUtil;
import com.zpauly.githubapp.view.viewholder.ReleaseViewHolder;

/**
 * Created by zpauly on 16/9/24.
 */

public class ReleasesRecyclerViewAdapter extends LoadMoreRecyclerViewAdapter<ReleaseBean, ReleaseViewHolder> {
    private final String TAG = getClass().getName();

    public ReleasesRecyclerViewAdapter(Context context) {
        super(context);
    }

    @Override
    public ReleaseViewHolder createContentViewHolder(ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(getContext()).inflate(R.layout.item_recyclerview_release,
                parent, false);
        ReleaseViewHolder viewHolder = new ReleaseViewHolder(mView);
        return viewHolder;
    }

    @Override
    public void bindContentViewHolder(ReleaseViewHolder holder, final int position) {
        ReleaseBean data = getData().get(position);
        holder.mNameTV.setText(data.getName());
        holder.mUsernameTV.setText(data.getAuthor().getLogin());
        holder.mTagTV.setText(data.getTag_name());
        holder.mTimeTV.setText(TextUtil.timeConverter(data.getCreated_at()));
        ImageUtil.loadAvatarImageFromUrl(getContext(), data.getAuthor().getAvatar_url(),
                holder.mAvatarIV);
        holder.mLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getItemClickedListener() != null) {
                    getItemClickedListener().onItemClicked(v, position);
                }
            }
        });
    }
}
