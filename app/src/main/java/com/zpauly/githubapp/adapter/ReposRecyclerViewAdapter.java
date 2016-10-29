package com.zpauly.githubapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zpauly.githubapp.R;
import com.zpauly.githubapp.entity.response.repos.RepositoriesBean;
import com.zpauly.githubapp.utils.TextUtil;
import com.zpauly.githubapp.view.repositories.RepoContentActivity;
import com.zpauly.githubapp.view.viewholder.ReposViewHolder;

/**
 * Created by zpauly on 16-7-15.
 */

public class ReposRecyclerViewAdapter extends
        LoadMoreRecyclerViewAdapter<RepositoriesBean, ReposViewHolder> {
    public ReposRecyclerViewAdapter(Context context) {
        super(context);
    }
    @Override
    public ReposViewHolder createContentViewHolder(ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(getContext()).inflate(R.layout.item_recyclerview_repositories, parent, false);
        ReposViewHolder holder = new ReposViewHolder(mView);
        return holder;
    }

    @Override
    public void bindContentViewHolder(ReposViewHolder holder, int position) {
        final RepositoriesBean repo = getData().get(position);
        holder.mReposForksTV.setText(String.valueOf(repo.getForks_count()));
        holder.mReposStarsTV.setText(String.valueOf(repo.getStargazers_count()));
        holder.mReposTechLanguageTV.setText(repo.getLanguage());
        holder.mReposTitleTV.setText(repo.getFull_name());
        holder.mReposUpdateTimeTV.setText(TextUtil.timeConverter(repo.getUpdated_at()));
        holder.mLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RepoContentActivity.launchActivity(getContext(), repo.getFull_name(), repo.getName(),
                        repo.getUrl(), repo.getOwner().getLogin());
            }
        });
    }
}
