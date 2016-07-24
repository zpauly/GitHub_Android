package com.zpauly.githubapp.view.viewholder;

import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.zpauly.githubapp.R;

/**
 * Created by zpauly on 16-7-24.
 */

public class EventsCommitsViewHolder extends RecyclerView.ViewHolder {
    public final AppCompatTextView mShaTV;

    public final AppCompatTextView mMessageTV;

    public EventsCommitsViewHolder(View itemView) {
        super(itemView);

        mShaTV = (AppCompatTextView) itemView.findViewById(R.id.events_commits_sha_TV);
        mMessageTV = (AppCompatTextView) itemView.findViewById(R.id.events_commits_message_TV);
    }
}
