package com.zpauly.githubapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zpauly.githubapp.R;
import com.zpauly.githubapp.entity.response.events.Payload;
import com.zpauly.githubapp.view.viewholder.EventsCommitsViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zpauly on 16-7-24.
 */

public class EventsCommitsRecyclerViewAdapter extends RecyclerView.Adapter<EventsCommitsViewHolder> {
    private Context mContext;

    private List<Payload.CommitsBean> mData;

    public EventsCommitsRecyclerViewAdapter(Context context) {
        mContext = context;
        mData = new ArrayList<>();
    }

    public void swapData(List<Payload.CommitsBean> list) {
        mData.clear();
        this.mData.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public EventsCommitsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(mContext).inflate(R.layout.item_recyclerview_event_commits, parent, false);
        EventsCommitsViewHolder viewHolder = new EventsCommitsViewHolder(mView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(EventsCommitsViewHolder holder, int position) {
        Payload.CommitsBean bean = mData.get(position);
        holder.mShaTV.setText(bean.getSha().substring(0, 7));
        holder.mMessageTV.setText(bean.getMessage());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }
}
