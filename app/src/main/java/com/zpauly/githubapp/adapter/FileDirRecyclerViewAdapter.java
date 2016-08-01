package com.zpauly.githubapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zpauly.githubapp.R;
import com.zpauly.githubapp.db.FileDirModel;
import com.zpauly.githubapp.view.viewholder.FileDirViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by root on 16-8-1.
 */

public class FileDirRecyclerViewAdapter extends RecyclerView.Adapter<FileDirViewHolder> {
    private final String TAG = getClass().getName();
    private Context mContext;

    private List<FileDirModel> mData;

    public FileDirRecyclerViewAdapter(Context context) {
        this.mContext = context;
        mData = new ArrayList<>();
    }

    public void addData(List<FileDirModel> list) {
        mData.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public FileDirViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(mContext).inflate(R.layout.item_recyclerview_filedir, parent, false);
        FileDirViewHolder viewHolder = new FileDirViewHolder(mView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(FileDirViewHolder holder, int position) {
        FileDirModel data = mData.get(position);
        String[] strs = data.getPath().split("/");
        holder.mFileNameTV.setText(strs[strs.length - 1]);
        Log.i(TAG, data.getType());
        if (data.getType().equals("dir")) {
            holder.mTypeIV.setImageResource(R.mipmap.ic_dir);
        } else if (data.getType().equals("file") || data.getType().equals("symlink")) {
            holder.mTypeIV.setImageResource(R.mipmap.ic_file);
        }
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }
}
