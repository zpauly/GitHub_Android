package com.zpauly.githubapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zpauly.githubapp.R;
import com.zpauly.githubapp.view.viewholder.ReposViewHolder;

/**
 * Created by zpauly on 16-7-15.
 */

public class ReposRecyclerViewAdapter extends RecyclerView.Adapter<ReposViewHolder> {
    private Context mContext;

    public ReposRecyclerViewAdapter(Context context) {
        mContext = context;
    }

    @Override
    public ReposViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(mContext).inflate(R.layout.item_recyclerview_repositories, parent, false);
        ReposViewHolder holder = new ReposViewHolder(mView);
        return holder;
    }

    @Override
    public void onBindViewHolder(ReposViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
