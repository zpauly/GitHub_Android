package com.zpauly.githubapp.view.viewholder;

import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.zpauly.githubapp.R;

/**
 * Created by zpauly on 16-8-1.
 */

public class FileDirViewHolder extends RecyclerView.ViewHolder {
    public final LinearLayout mLayout;

    public final ImageView mTypeIV;

    public final AppCompatTextView mFileNameTV;

    public FileDirViewHolder(View itemView) {
        super(itemView);

        mLayout = (LinearLayout) itemView.findViewById(R.id.file_item_layout);
        mTypeIV = (ImageView) itemView.findViewById(R.id.file_type_IV);
        mFileNameTV = (AppCompatTextView) itemView.findViewById(R.id.file_name_TV);
    }
}
