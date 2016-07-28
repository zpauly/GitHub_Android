package com.zpauly.githubapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.zpauly.githubapp.R;
import com.zpauly.githubapp.entity.response.FollowersBean;
import com.zpauly.githubapp.view.others.OthersActivity;
import com.zpauly.githubapp.view.viewholder.FollowersViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zpauly on 16-6-15.
 */
public class FollowersRecyclerViewAdapter extends LoadMoreRecyclerViewAdapter<FollowersViewHolder> {
    private View mView;

    private List<FollowersBean> mData;

    public FollowersRecyclerViewAdapter(Context context) {
        super(context);
        mData = new ArrayList<>();
    }

    public void swapData(List<FollowersBean> list) {
        mData.clear();
        mData.addAll(list);
        notifyDataSetChanged();
    }

    public void addData(List<FollowersBean> list) {
        mData.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public FollowersViewHolder createContentViewHolder(ViewGroup parent, int viewType) {
        mView = LayoutInflater.from(mContext).inflate(R.layout.item_recyclerview_followers, parent, false);
        FollowersViewHolder viewHolder = new FollowersViewHolder(mView);
        return viewHolder;
    }

    @Override
    public void bindContentViewHolder(FollowersViewHolder holder, int position) {
        final FollowersBean bean = mData.get(position);
        holder.mUsernameTV.setText(bean.getLogin());
        Glide.with(mContext)
                .load(Uri.parse(bean.getAvatar_url()))
                .centerCrop()
                .crossFade()
                .into(holder.mAvatarIV);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(mContext, OthersActivity.class);
                intent.putExtra(OthersActivity.USERNAME, bean.getLogin());
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size() + 1;
    }
}
