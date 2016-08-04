package com.zpauly.githubapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zpauly.githubapp.R;
import com.zpauly.githubapp.base.BaseAdapter;
import com.zpauly.githubapp.listener.OnDirItemClickListener;
import com.zpauly.githubapp.view.viewholder.PathViewHolder;

import java.util.List;


/**
 * Created by zpauly on 16-8-1.
 */

public class PathRecyclerViewAdapter extends BaseAdapter<String, PathViewHolder> {
    private final String TAG = getClass().getName();

    private OnDirItemClickListener mOnDirItemClickListener;

    public void setOnItemClickListener(OnDirItemClickListener listener) {
        this.mOnDirItemClickListener = listener;
    }

    public PathRecyclerViewAdapter(Context context) {
        super(context);
    }

    @Override
    public PathViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(mContext).inflate(R.layout.item_recyclerview_dirpath, parent, false);
        PathViewHolder viewHolder = new PathViewHolder(mView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(PathViewHolder holder, int position) {
        String file = mData.get(position);
        holder.mPathTV.setText(file);
        final StringBuffer path = new StringBuffer();
        if (mData.size() > 1) {
            for (int i = 1; i <= position; i ++) {
                path.append(mData.get(i));
                if (i != position) {
                    path.append("/");
                }
            }
        }
        holder.mLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnDirItemClickListener != null) {
                    mOnDirItemClickListener.onClick(v, path.toString(), null, null);
                }
            }
        });
    }
}
