package com.zpauly.githubapp.view.viewholder;

import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.zpauly.githubapp.R;

/**
 * Created by zpauly on 16-7-15.
 */

public class ReposViewHolder extends RecyclerView.ViewHolder {
    private AppCompatTextView mReposTitleTV;

    private AppCompatTextView mReposUpdateTimeTV;

    private AppCompatTextView mReposTechLanguageTV;

    private AppCompatTextView mReposStarsTV;

    private AppCompatTextView mReposForksTV;

    public ReposViewHolder(View itemView) {
        super(itemView);

        mReposTitleTV = (AppCompatTextView) itemView.findViewById(R.id.repos_title_TV);
        mReposUpdateTimeTV = (AppCompatTextView) itemView.findViewById(R.id.repos_update_time_TV);
        mReposTechLanguageTV = (AppCompatTextView) itemView.findViewById(R.id.repos_tech_language_TV);
        mReposStarsTV = (AppCompatTextView) itemView.findViewById(R.id.repos_stars_TV);
        mReposForksTV = (AppCompatTextView) itemView.findViewById(R.id.repos_forks_TV);
    }

    public AppCompatTextView getmReposTitleTV() {
        return mReposTitleTV;
    }

    public AppCompatTextView getmReposForksTV() {
        return mReposForksTV;
    }

    public AppCompatTextView getmReposStarsTV() {
        return mReposStarsTV;
    }

    public AppCompatTextView getmReposTechLanguageTV() {
        return mReposTechLanguageTV;
    }

    public AppCompatTextView getmReposUpdateTimeTV() {
        return mReposUpdateTimeTV;
    }
}
