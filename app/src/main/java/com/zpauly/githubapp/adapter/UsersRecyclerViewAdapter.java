package com.zpauly.githubapp.adapter;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.zpauly.githubapp.R;
import com.zpauly.githubapp.entity.response.FollowersBean;
import com.zpauly.githubapp.view.profile.OthersActivity;
import com.zpauly.githubapp.view.viewholder.UsersViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zpauly on 16-6-15.
 */
public class UsersRecyclerViewAdapter extends LoadMoreRecyclerViewAdapter<UsersViewHolder> {
    private final String TAG = getClass().getName();

    private View mView;

    private List<FollowersBean> mData;

    public UsersRecyclerViewAdapter(Context context) {
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
    public UsersViewHolder createContentViewHolder(ViewGroup parent, int viewType) {
        mView = LayoutInflater.from(mContext).inflate(R.layout.item_recyclerview_users, parent, false);
        UsersViewHolder viewHolder = new UsersViewHolder(mView);
        return viewHolder;
    }

    @Override
    public void bindContentViewHolder(UsersViewHolder holder, int position) {
        final FollowersBean bean = mData.get(position);
        holder.mLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OthersActivity.lanuchActivity(mContext, bean.getLogin());
            }
        });
        holder.mUsernameTV.setText(bean.getLogin());
        Glide.with(mContext)
                .load(Uri.parse(bean.getAvatar_url()))
                .centerCrop()
                .crossFade()
                .into(holder.mAvatarIV);
        Log.i(TAG, "item" + position);
    }

    @Override
    public int getItemCount() {
        return mData.size() + 1;
    }
}
