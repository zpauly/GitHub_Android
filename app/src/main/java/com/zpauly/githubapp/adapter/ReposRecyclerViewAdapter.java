package com.zpauly.githubapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zpauly.githubapp.R;
import com.zpauly.githubapp.entity.response.StarredRepositories;
import com.zpauly.githubapp.view.viewholder.ReposViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zpauly on 16-7-15.
 */

public class ReposRecyclerViewAdapter extends RecyclerView.Adapter<ReposViewHolder> {
    private Context mContext;

    private List<StarredRepositories> mData = new ArrayList<>();

    public ReposRecyclerViewAdapter(Context context) {
        mContext = context;
    }

    public void addData(StarredRepositories data) {
        mData.add(data);
        notifyDataSetChanged();
    }

    public void addAllData(List<StarredRepositories> list) {
        mData.addAll(list);
        notifyDataSetChanged();
    }

    public void swapData(List<StarredRepositories> list) {
        mData.clear();
        mData.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public ReposViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(mContext).inflate(R.layout.item_recyclerview_repositories, parent, false);
        ReposViewHolder holder = new ReposViewHolder(mView);
        return holder;
    }

    @Override
    public void onBindViewHolder(ReposViewHolder holder, int position) {
        StarredRepositories repo = mData.get(position);
        holder.mReposForksTV.setText(String.valueOf(repo.getForks_count()));
        holder.mReposStarsTV.setText(String.valueOf(repo.getStargazers_count()));
        holder.mReposTechLanguageTV.setText(repo.getLanguage());
        holder.mReposTitleTV.setText(repo.getFull_name());
        holder.mReposUpdateTimeTV.setText(repo.getUpdated_at());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }
}
