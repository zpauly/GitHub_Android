package com.zpauly.githubapp.view.viewholder;

import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.zpauly.githubapp.R;

/**
 * Created by zpauly on 16/9/22.
 */

public class PatchesViewHolder extends RecyclerView.ViewHolder {
    public final LinearLayout mLayout;

    public final AppCompatTextView mFilenameTV;

    public final AppCompatTextView mAdditionCountTV;

    public final AppCompatTextView mDeletionCountTV;

    public PatchesViewHolder(View itemView) {
        super(itemView);

        mLayout = (LinearLayout) itemView.findViewById(R.id.patch_item_layout);
        mFilenameTV = (AppCompatTextView) itemView.findViewById(R.id.patch_item_filename_TV);
        mAdditionCountTV = (AppCompatTextView) itemView.findViewById(R.id.patch_item_addition_count_TV);
        mDeletionCountTV = (AppCompatTextView) itemView.findViewById(R.id.patch_item_deletion_count_TV);
    }
}
