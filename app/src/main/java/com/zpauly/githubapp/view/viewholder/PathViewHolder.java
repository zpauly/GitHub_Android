package com.zpauly.githubapp.view.viewholder;

import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.widget.LinearLayout;

import com.zpauly.githubapp.R;
import com.zpauly.githubapp.base.BaseRecyclerViewViewHolder;

import butterknife.BindView;

/**
 * Created by zpauly on 16-8-1.
 */

public class PathViewHolder extends BaseRecyclerViewViewHolder {
    @BindView(R.id.dirpath_item_layout) public LinearLayout mLayout;

    @BindView(R.id.path_name_TV) public AppCompatTextView mPathTV;

    public PathViewHolder(View itemView) {
        super(itemView);
    }
}
