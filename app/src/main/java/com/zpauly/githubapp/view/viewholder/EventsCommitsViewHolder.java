package com.zpauly.githubapp.view.viewholder;

import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.zpauly.githubapp.R;
import com.zpauly.githubapp.base.BaseRecyclerViewViewHolder;

import butterknife.BindView;

/**
 * Created by zpauly on 16-7-24.
 */

public class EventsCommitsViewHolder extends BaseRecyclerViewViewHolder {
    @BindView(R.id.events_commits_sha_TV) public AppCompatTextView mShaTV;

    @BindView(R.id.events_commits_message_TV) public AppCompatTextView mMessageTV;

    public EventsCommitsViewHolder(View itemView) {
        super(itemView);

        mShaTV = (AppCompatTextView) itemView.findViewById(R.id.events_commits_sha_TV);
        mMessageTV = (AppCompatTextView) itemView.findViewById(R.id.events_commits_message_TV);
    }
}
