package com.zpauly.githubapp.view.viewholder;

import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.zpauly.githubapp.R;
import com.zpauly.githubapp.base.BaseRecyclerViewViewHolder;

import butterknife.BindView;

/**
 * Created by zpauly on 16-8-1.
 */

public class FileDirViewHolder extends BaseRecyclerViewViewHolder {
    @BindView(R.id.file_item_layout) public LinearLayout mLayout;

    @BindView(R.id.file_type_IV) public ImageView mTypeIV;

    @BindView(R.id.file_name_TV) public AppCompatTextView mFileNameTV;

    public FileDirViewHolder(View itemView) {
        super(itemView);
    }
}
