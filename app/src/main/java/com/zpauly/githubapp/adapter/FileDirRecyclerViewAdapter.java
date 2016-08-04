package com.zpauly.githubapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zpauly.githubapp.R;
import com.zpauly.githubapp.base.BaseAdapter;
import com.zpauly.githubapp.db.FileDirModel;
import com.zpauly.githubapp.listener.OnDirItemClickListener;
import com.zpauly.githubapp.view.viewholder.FileDirViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by root on 16-8-1.
 */

public class FileDirRecyclerViewAdapter extends BaseAdapter<FileDirModel, FileDirViewHolder> {
    private final String TAG = getClass().getName();

    private OnDirItemClickListener mOnDirItemClickListener;

    public FileDirRecyclerViewAdapter(Context context) {
        super(context);
    }

    public void setOnItemClickListener(OnDirItemClickListener listener) {
        mOnDirItemClickListener = listener;
    }

    @Override
    public FileDirViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(getContext()).inflate(R.layout.item_recyclerview_filedir, parent, false);
        FileDirViewHolder viewHolder = new FileDirViewHolder(mView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(FileDirViewHolder holder, int position) {
        final FileDirModel data = getData().get(position);
        String[] strs = data.getPath().split("/");
        holder.mFileNameTV.setText(strs[strs.length - 1]);
        if (data.getType().equals("dir")) {
            holder.mTypeIV.setImageResource(R.mipmap.ic_dir);
        } else if (data.getType().equals("file") || data.getType().equals("symlink")) {
            holder.mTypeIV.setImageResource(R.mipmap.ic_file);
        }
        holder.mLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnDirItemClickListener != null) {
                    mOnDirItemClickListener.onClick(v, data.getPath(), data.getType(), data.getSha());
                }
            }
        });
    }
}
