package com.zpauly.githubapp.view.viewholder;

import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.zpauly.githubapp.R;

/**
 * Created by zpauly on 16-7-15.
 */

public class ReposViewHolder extends RecyclerView.ViewHolder {
    public final LinearLayout mLayout;

    public final AppCompatTextView mReposTitleTV;

    public final AppCompatTextView mReposUpdateTimeTV;

    public final AppCompatTextView mReposTechLanguageTV;

    public final AppCompatTextView mReposStarsTV;

    public final AppCompatTextView mReposForksTV;

    public ReposViewHolder(View itemView) {
        super(itemView);

        mLayout = (LinearLayout) itemView.findViewById(R.id.repos_layout);
        mReposTitleTV = (AppCompatTextView) itemView.findViewById(R.id.repos_title_TV);
        mReposUpdateTimeTV = (AppCompatTextView) itemView.findViewById(R.id.repos_update_time_TV);
        mReposTechLanguageTV = (AppCompatTextView) itemView.findViewById(R.id.repos_tech_language_TV);
        mReposStarsTV = (AppCompatTextView) itemView.findViewById(R.id.repos_stars_TV);
        mReposForksTV = (AppCompatTextView) itemView.findViewById(R.id.repos_forks_TV);
    }
}
