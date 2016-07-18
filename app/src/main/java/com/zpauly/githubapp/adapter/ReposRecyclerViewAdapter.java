package com.zpauly.githubapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zpauly.githubapp.R;
import com.zpauly.githubapp.entity.response.RepositoriesBean;
import com.zpauly.githubapp.entity.response.StarredRepositories;
import com.zpauly.githubapp.view.viewholder.ReposViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zpauly on 16-7-15.
 */

public class ReposRecyclerViewAdapter extends RecyclerView.Adapter<ReposViewHolder> {
    public static final int STARREDREPOSITORIES_ID = 0;
    public static final int REPOSITORIESBEAN_ID = 1;

    private int dataId;

    private Context mContext;

    private List<StarredRepositories> mStarredReposData = new ArrayList<>();
    private List<RepositoriesBean> mReposData = new ArrayList<>();

    public ReposRecyclerViewAdapter(Context context, int dataId) {
        mContext = context;
        this.dataId = dataId;
    }

    public void addReposData(RepositoriesBean data) {
        mReposData.add(data);
        notifyDataSetChanged();
    }

    public void addAllReposData(List<RepositoriesBean> list) {
        mReposData.addAll(list);
        notifyDataSetChanged();
    }

    public void swapReposData(List<RepositoriesBean> list) {
        mReposData.clear();
        mReposData.addAll(list);
        notifyDataSetChanged();
    }

    public void addData(StarredRepositories data) {
        mStarredReposData.add(data);
        notifyDataSetChanged();
    }

    public void addAllData(List<StarredRepositories> list) {
        mStarredReposData.addAll(list);
        notifyDataSetChanged();
    }

    public void swapData(List<StarredRepositories> list) {
        mStarredReposData.clear();
        mStarredReposData.addAll(list);
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
        switch (dataId) {
            case STARREDREPOSITORIES_ID:
                StarredRepositories repo = mStarredReposData.get(position);
                holder.mReposForksTV.setText(String.valueOf(repo.getForks_count()));
                holder.mReposStarsTV.setText(String.valueOf(repo.getStargazers_count()));
                holder.mReposTechLanguageTV.setText(repo.getLanguage());
                holder.mReposTitleTV.setText(repo.getFull_name());
                holder.mReposUpdateTimeTV.setText(repo.getUpdated_at());
                break;
            case REPOSITORIESBEAN_ID:
                RepositoriesBean bean = mReposData.get(position);
                holder.mReposForksTV.setText(String.valueOf(bean.getForks_count()));
                holder.mReposStarsTV.setText(String.valueOf(bean.getStargazers_count()));
                holder.mReposTechLanguageTV.setText(bean.getLanguage());
                holder.mReposTitleTV.setText(bean.getFull_name());
                holder.mReposUpdateTimeTV.setText(bean.getUpdated_at());
                break;
            default:
                break;
        }
    }

    @Override
    public int getItemCount() {
        return mStarredReposData.size() > mReposData.size() ? mStarredReposData.size() : mReposData.size();
    }
}
