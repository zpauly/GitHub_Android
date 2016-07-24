package com.zpauly.githubapp.adapter;

import android.content.Context;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.zpauly.githubapp.R;
import com.zpauly.githubapp.entity.response.events.EventsBean;
import com.zpauly.githubapp.entity.response.events.Payload;
import com.zpauly.githubapp.view.viewholder.EventsViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zpauly on 16-6-14.
 */
public class EventsRecyclerViewAdapter extends RecyclerView.Adapter<EventsViewHolder> {
    private final String TAG = getClass().getName();

    private Context mContext;
    private List<EventsBean> mData;

    private EventsCommitsRecyclerViewAdapter mAdapter;

    public EventsRecyclerViewAdapter(Context context) {
        this.mContext = context;
        mData = new ArrayList<>();
    }

    public void addAllData(List<EventsBean> list) {
        mData.addAll(list);
        notifyDataSetChanged();
    }

    public void swapData(List<EventsBean> list) {
        mData.clear();
        mData.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public EventsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(mContext).inflate(R.layout.item_recylcleview_events, parent, false);
        EventsViewHolder holder = new EventsViewHolder(mView);
        holder.mCommitsRV.setLayoutManager(new LinearLayoutManager(mContext));
        mAdapter = new EventsCommitsRecyclerViewAdapter(mContext);
        holder.mCommitsRV.setAdapter(mAdapter);
        return holder;
    }

    @Override
    public void onBindViewHolder(EventsViewHolder holder, int position) {
        EventsBean data = mData.get(position);
        Glide.with(mContext)
                .load(data.getActor().getAvatar_url())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .crossFade()
                .into(holder.mUserAvatarIV);
        holder.mRepoTV.setText(data.getRepo().getName());
        holder.mUsernameTV.setText(data.getActor().getLogin());
        holder.mTimeTV.setText(data.getCreated_at());
        setAction(data.getType(), data.getPayload(), holder);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    private void setAction(String type, Payload payloadBean, EventsViewHolder holder) {
        switch (type) {
            case "WatchEvent":
                holder.mActionTV.setText("starred ");
                holder.mTypeIV.setImageResource(R.mipmap.ic_star);
                break;
            case "PushEvent":
                String[] str = payloadBean.getRef().split("/");
                String branch = str[str.length - 1];
                holder.mActionTV.setText("pushed to " + branch + " at ");
                holder.mTypeIV.setImageResource(R.mipmap.ic_commit);
                holder.mCommitsRV.setVisibility(View.VISIBLE);
                mAdapter.swapData(payloadBean.getCommits());
                holder.mCommitsRV.setEnabled(false);
                break;
            case "CreateEvent":
                if (payloadBean.getRef() == null) {
                    holder.mActionTV.setText("create " + payloadBean.getRef_type());
                    holder.mTypeIV.setImageResource(R.mipmap.ic_repos);
                } else {
                    holder.mActionTV.setText("create branch " + payloadBean.getMaster_branch() + " at");
                    holder.mTypeIV.setImageResource(R.mipmap.ic_fork);
                }
                break;
            case "ForkEvent":
                holder.mActionTV.setText("forked");
                holder.mTypeIV.setImageResource(R.mipmap.ic_fork);
                break;
            default:
                break;
        }
    }
}
