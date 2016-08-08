package com.zpauly.githubapp.view.viewholder;

import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.zpauly.githubapp.R;

/**
 * Created by zpauly on 16-8-6.
 */

public class GistFileViewHolder extends RecyclerView.ViewHolder {
    public LinearLayout mLayout;

    public AppCompatTextView mNameTV;

    public GistFileViewHolder(View itemView) {
        super(itemView);

        mLayout = (LinearLayout) itemView.findViewById(R.id.gist_file_layout);
        mNameTV = (AppCompatTextView) itemView.findViewById(R.id.gist_file_TV);
    }
}
