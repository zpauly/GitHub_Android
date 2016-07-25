package com.zpauly.githubapp.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.zpauly.githubapp.R;
import com.zpauly.githubapp.entity.response.FollowersBean;
import com.zpauly.githubapp.view.viewholder.FollowersViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zpauly on 16-6-15.
 */
public class FollowersRecyclerViewAdapter extends RecyclerView.Adapter<FollowersViewHolder> {
    private Context mContext;

    private View mView;

    private List<FollowersBean> mData;

    public FollowersRecyclerViewAdapter(Context context) {
        mContext = context;
        mData = new ArrayList<>();
    }

    public void swapData(List<FollowersBean> list) {
        mData.clear();
        mData.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public FollowersViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mView = LayoutInflater.from(mContext).inflate(R.layout.item_recyclerview_followers, parent, false);
        FollowersViewHolder viewHolder = new FollowersViewHolder(mView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(FollowersViewHolder holder, int position) {
        FollowersBean bean = mData.get(position);
        holder.mUsernameTV.setText(bean.getLogin());
        Glide.with(mContext)
                .load(Uri.parse(bean.getAvatar_url()))
                .centerCrop()
                .crossFade()
                .into(holder.mAvatarIV);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }
}
