package com.zpauly.githubapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zpauly.githubapp.R;
import com.zpauly.githubapp.view.viewholder.FollowersViewHolder;

/**
 * Created by zpauly on 16-6-15.
 */
public class FollowersRecyclerViewAdapter extends RecyclerView.Adapter<FollowersViewHolder> {
    private Context mContext;

    private View mView;

    public FollowersRecyclerViewAdapter(Context context) {
        mContext = context;
    }

    @Override
    public FollowersViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mView = LayoutInflater.from(mContext).inflate(R.layout.item_recyclerview_followers, parent, false);
        FollowersViewHolder viewHolder = new FollowersViewHolder(mView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(FollowersViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
