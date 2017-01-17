package com.zpauly.githubapp.base;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import butterknife.ButterKnife;

/**
 * Created by zpauly on 2017/1/17.
 */

public class BaseRecyclerViewViewHolder extends RecyclerView.ViewHolder {
    public BaseRecyclerViewViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
