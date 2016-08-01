package com.zpauly.githubapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.zpauly.githubapp.listener.OnItemClickListener;
import com.zpauly.githubapp.presenter.follow.FollowContract;
import com.zpauly.githubapp.view.viewholder.PathViewHolder;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by zpauly on 16-8-1.
 */

public class PathRecyclerViewAdapter extends RecyclerView.Adapter<PathViewHolder> {
    private final String TAG = getClass().getName();

    private Context mContext;
    private List<String> mData = new ArrayList<>();

    private OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    public PathRecyclerViewAdapter(Context context) {
        this.mContext = context;
        mData.add("root system");
    }

    @Override
    public PathViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(PathViewHolder holder, int position) {
        holder.mPathTV.setText(mData.get(position));
        holder.mLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.onClick(v);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }
}
