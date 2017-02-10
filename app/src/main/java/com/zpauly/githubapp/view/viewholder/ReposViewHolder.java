package com.zpauly.githubapp.view.viewholder;

import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.widget.LinearLayout;

import com.zpauly.githubapp.R;
import com.zpauly.githubapp.base.BaseRecyclerViewViewHolder;

import butterknife.BindView;

/**
 * Created by zpauly on 16-7-15.
 */

public class ReposViewHolder extends BaseRecyclerViewViewHolder {
    @BindView(R.id.repos_layout) public LinearLayout mLayout;

    @BindView(R.id.repos_title_TV) public AppCompatTextView mReposTitleTV;

    @BindView(R.id.repos_desc_TV) public AppCompatTextView mReposDescTV;

    @BindView(R.id.repos_update_time_TV) public AppCompatTextView mReposUpdateTimeTV;

    @BindView(R.id.repos_tech_language_TV) public AppCompatTextView mReposTechLanguageTV;

    @BindView(R.id.repos_stars_TV) public AppCompatTextView mReposStarsTV;

    @BindView(R.id.repos_forks_TV) public AppCompatTextView mReposForksTV;

    public ReposViewHolder(View itemView) {
        super(itemView);
    }
}
