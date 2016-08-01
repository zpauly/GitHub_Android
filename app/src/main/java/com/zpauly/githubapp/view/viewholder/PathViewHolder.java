package com.zpauly.githubapp.view.viewholder;

import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.zpauly.githubapp.R;

/**
 * Created by zpauly on 16-8-1.
 */

public class PathViewHolder extends RecyclerView.ViewHolder {
    public final LinearLayout mLayout;

    public final AppCompatTextView mPathTV;

    public PathViewHolder(View itemView) {
        super(itemView);

        mLayout = (LinearLayout) itemView.findViewById(R.id.dirpath_item_layout);
        mPathTV = (AppCompatTextView) itemView.findViewById(R.id.path_name_TV);
    }
}
