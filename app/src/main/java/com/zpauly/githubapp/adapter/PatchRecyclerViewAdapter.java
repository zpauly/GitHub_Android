package com.zpauly.githubapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zpauly.githubapp.R;
import com.zpauly.githubapp.base.BaseAdapter;
import com.zpauly.githubapp.entity.response.repos.FileBean;
import com.zpauly.githubapp.view.commit.CommitFilePatchActivity;
import com.zpauly.githubapp.view.viewholder.PatchesViewHolder;

/**
 * Created by zpauly on 16/9/22.
 */

public class PatchRecyclerViewAdapter extends BaseAdapter<FileBean, PatchesViewHolder> {
    private final String TAG = getClass().getName();

    public PatchRecyclerViewAdapter(Context context) {
        super(context);
    }

    @Override
    public PatchesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(getContext()).inflate(R.layout.item_recyclerview_patch, parent, false);
        PatchesViewHolder viewHolder = new PatchesViewHolder(mView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(PatchesViewHolder holder, int position) {
        final FileBean data = getData().get(position);
        holder.mFilenameTV.setText(data.getFilename());
        holder.mAdditionCountTV.setText(String.valueOf(data.getAdditions()));
        holder.mDeletionCountTV.setText(String.valueOf(data.getDeletions()));
        holder.mLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CommitFilePatchActivity.launchActivity(getContext(), data);
            }
        });
    }
}
