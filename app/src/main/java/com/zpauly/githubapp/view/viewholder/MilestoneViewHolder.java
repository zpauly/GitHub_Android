package com.zpauly.githubapp.view.viewholder;

import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.zpauly.githubapp.R;
import com.zpauly.githubapp.base.BaseRecyclerViewViewHolder;

import butterknife.BindView;

/**
 * Created by zpauly on 2016/10/11.
 */

public class MilestoneViewHolder extends BaseRecyclerViewViewHolder {
    @BindView(R.id.milestone_item_layout) public LinearLayout mLayout;

    @BindView(R.id.milestone_item_title_TV) public AppCompatTextView mTitleTV;

    @BindView(R.id.milestone_item_due_on_IV) public ImageView mDueOnIV;

    @BindView(R.id.milestone_item_due_on_TV) public AppCompatTextView mDueOnTV;

    @BindView(R.id.milestone_item_update_on_TV) public AppCompatTextView mUpdateOnTV;

    @BindView(R.id.milestone_item_description_TV) public AppCompatTextView mDescTV;

    @BindView(R.id.milestone_item_completeness_PB) public ProgressBar mCompletenessPB;

    @BindView(R.id.milestone_item_complete_TV) public AppCompatTextView mCompleteTV;

    @BindView(R.id.milestone_item_open_TV) public AppCompatTextView mOpenTV;

    @BindView(R.id.milestone_item_closed_TV) public AppCompatTextView mClosedTV;

    public MilestoneViewHolder(View itemView) {
        super(itemView);
    }
}
