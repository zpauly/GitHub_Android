package com.zpauly.githubapp.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.zpauly.githubapp.R;
import com.zpauly.githubapp.entity.response.gists.GistFileBean;
import com.zpauly.githubapp.entity.response.gists.GistFileMapBean;
import com.zpauly.githubapp.entity.response.gists.GistsBean;
import com.zpauly.githubapp.utils.ImageUtil;
import com.zpauly.githubapp.utils.TextUtil;
import com.zpauly.githubapp.view.gists.GistContentActivity;
import com.zpauly.githubapp.view.viewholder.GistViewHolder;

import java.util.ArrayList;

/**
 * Created by zpauly on 16-8-5.
 */

public class GistsRecyclerViewAdapter extends LoadMoreRecyclerViewAdapter<GistsBean, GistViewHolder> {
    public GistsRecyclerViewAdapter(Context context) {
        super(context);
    }

    @Override
    public GistViewHolder createContentViewHolder(ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(getContext()).inflate(R.layout.item_recyclerview_gist, parent, false);
        GistViewHolder viewHolder = new GistViewHolder(mView);
        return viewHolder;
    }

    @Override
    public void bindContentViewHolder(GistViewHolder holder, int position) {
        final GistsBean data = getData().get(position);
        final GistFileMapBean map = data.getFiles();
        final ArrayList<GistFileBean> list = new ArrayList<>();
        for (String s : map.keySet()) {
            list.add(map.get(s));
        }
        holder.mTitleTV.setText(data.getOwner().getLogin() + "/" + list.get(0).getFilename());
        holder.mDescTV.setText(data.getDescription());
        holder.mTimeTV.setText(TextUtil.timeConverter(data.getUpdated_at()));
        ImageUtil.loadAvatarImageFromUrl(getContext(), data.getOwner().getAvatar_url(),
                holder.mAvatarIV);
        holder.mLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GistContentActivity.launchActivity(getContext(), data, list);
            }
        });
    }
}
