package com.zpauly.githubapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zpauly.githubapp.R;
import com.zpauly.githubapp.base.BaseAdapter;
import com.zpauly.githubapp.entity.response.gists.GistFileBean;
import com.zpauly.githubapp.view.gists.GistFileActivity;
import com.zpauly.githubapp.view.viewholder.GistFileViewHolder;

/**
 * Created by zpauly on 16-8-6.
 */

public class GistFileRecyclerViewAdapter extends BaseAdapter<GistFileBean, GistFileViewHolder> {
    public GistFileRecyclerViewAdapter(Context context){
        super(context);
    }

    private String id;

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public GistFileViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(getContext()).inflate(R.layout.item_recyclerview_gist_files, parent, false);
        GistFileViewHolder viewHolder = new GistFileViewHolder(mView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(GistFileViewHolder holder, int position) {
        final GistFileBean bean = getData().get(position);
        holder.mNameTV.setText(bean.getFilename());
        holder.mLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GistFileActivity.launchActivity(getContext(), bean.getFilename(), id);
            }
        });
    }
}
