package com.zpauly.githubapp.view.viewholder;

import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.widget.LinearLayout;

import com.zpauly.githubapp.R;
import com.zpauly.githubapp.base.BaseRecyclerViewViewHolder;

import butterknife.BindView;

/**
 * Created by zpauly on 16/9/22.
 */

public class PatchesViewHolder extends BaseRecyclerViewViewHolder {
    @BindView(R.id.patch_item_layout) public LinearLayout mLayout;

    @BindView(R.id.patch_item_filename_TV) public AppCompatTextView mFilenameTV;

    @BindView(R.id.patch_item_addition_count_TV) public AppCompatTextView mAdditionCountTV;

    @BindView(R.id.patch_item_deletion_count_TV) public AppCompatTextView mDeletionCountTV;

    public PatchesViewHolder(View itemView) {
        super(itemView);
    }
}
