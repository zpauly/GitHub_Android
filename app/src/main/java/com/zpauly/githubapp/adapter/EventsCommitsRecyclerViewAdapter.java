package com.zpauly.githubapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zpauly.githubapp.R;
import com.zpauly.githubapp.base.BaseAdapter;
import com.zpauly.githubapp.entity.response.events.Payload;
import com.zpauly.githubapp.view.viewholder.EventsCommitsViewHolder;

/**
 * Created by zpauly on 16-7-24.
 */

public class EventsCommitsRecyclerViewAdapter extends BaseAdapter<Payload.CommitsBean, EventsCommitsViewHolder> {
    public EventsCommitsRecyclerViewAdapter(Context context) {
        super(context);
    }

    @Override
    public EventsCommitsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(getContext()).inflate(R.layout.item_recyclerview_event_commits, parent, false);
        EventsCommitsViewHolder viewHolder = new EventsCommitsViewHolder(mView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(EventsCommitsViewHolder holder, int position) {
        Payload.CommitsBean bean = getData().get(position);
        holder.mShaTV.setText(bean.getSha().substring(0, 7));
        holder.mMessageTV.setText(bean.getMessage());
    }
}
