package com.zpauly.githubapp.view.viewholder;

import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.widget.LinearLayout;

import com.zpauly.githubapp.R;
import com.zpauly.githubapp.base.BaseRecyclerViewViewHolder;

import butterknife.BindView;

/**
 * Created by zpauly on 2016/10/29.
 */

public class BranchViewHolder extends BaseRecyclerViewViewHolder {
    @BindView(R.id.branch_item_layout) public LinearLayout mLayout;

    @BindView(R.id.branch_name_TV) public AppCompatTextView mNameTV;

    @BindView(R.id.branch_is_default_TV) public AppCompatTextView mIsDefaultTV;

    public BranchViewHolder(View itemView) {
        super(itemView);
    }
}
